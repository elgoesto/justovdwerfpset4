package com.example.justovanderwerf.todolist;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ResourceCursorAdapter;

/**
 * Created by Justo van der Werf on 11/20/2017.
 */

public class TodoAdapter extends ResourceCursorAdapter {
    public TodoAdapter(Context context, Cursor cursor){
        super(context, R.layout.row_todo, cursor, 0);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        int todoIndex = cursor.getColumnIndex("title");
        int doneIndex = cursor.getColumnIndex("completed");
        boolean done;
        String title = cursor.getString(todoIndex);

        if(cursor.getInt(doneIndex) == 1) {
            done = true;
        } else{
            done = false;
        }

        CheckBox rowblabla = view.findViewById(R.id.checkBoxTodo);

        rowblabla.setText(title);
        rowblabla.setChecked(done);
    }
}
