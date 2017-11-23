package com.example.justovanderwerf.todolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Justo van der Werf on 11/20/2017.
 */

public class TodoDatabase extends SQLiteOpenHelper {
    private static TodoDatabase instance;

    private TodoDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);

    }

    public static TodoDatabase getInstance(Context context) {
        if (instance == null) {
            // call the private constructor
            instance = new TodoDatabase(context, "name", null, 1);
        }
        return instance;
    }

    public Cursor selectAll() {
        SQLiteDatabase db = instance.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM todos", new String[]{});
        return cursor;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE 'todos' ('title' TEXT, 'completed' BOOLEAN, " +
                "'_id' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL)");
        sqLiteDatabase.execSQL("INSERT INTO 'todos' ('title','completed','_id') VALUES ('Laundy','0',NULL)");
        sqLiteDatabase.execSQL("INSERT INTO 'todos' ('title','completed','_id') VALUES ('To do','0',NULL)");
        sqLiteDatabase.execSQL("INSERT INTO 'todos' ('title','completed','_id') VALUES ('dishes','0',NULL)");


    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS 'todos'");
        onCreate(sqLiteDatabase);
    }

    public void insert(String title, int done) {
        SQLiteDatabase db = instance.getWritableDatabase();
        ContentValues newTodo = new ContentValues();
        newTodo.put("title", title);
        newTodo.put("completed", done);
        db.insert("todos", null, newTodo);

    }

    public void update(long id, int done) {
        SQLiteDatabase db = instance.getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put("completed", done);
        db.update("todos", content, "_id = " + id, new String[]{});

    }

    public void delete(long id) {

        SQLiteDatabase db =  instance.getWritableDatabase();
        db.delete("todos", "_id = " + id,  new String []{});

    }
}