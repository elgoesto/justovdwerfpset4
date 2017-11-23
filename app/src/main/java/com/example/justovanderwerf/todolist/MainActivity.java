package com.example.justovanderwerf.todolist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    TodoDatabase db;
    TodoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = TodoDatabase.getInstance(getApplicationContext());

        adapter = new TodoAdapter(getApplicationContext(), db.selectAll());

        ListView ToDoListView = findViewById(R.id.ToDoListView);

        ToDoListView.setAdapter(adapter);

        ToDoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                CheckBox checktodo = view.findViewById(R.id.checkBoxTodo);
                if (checktodo.isChecked()) {
                    db.update(id, 0);
                } else {
                    db.update(id, 1);
                }
                updateData();
            }
        });

        ToDoListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int pos,
                                           long id){
                db.delete(id);
                updateData();
                return true;
            }
        });
    }

    public void addItem(View view) {
        EditText todoEdit = findViewById(R.id.ToDoEditText);
        String title = todoEdit.getText().toString();

        db.insert(title, 0);
        updateData();
        todoEdit.setText("");

    }

    private void updateData(){
        adapter.swapCursor(db.selectAll());
    }


}
