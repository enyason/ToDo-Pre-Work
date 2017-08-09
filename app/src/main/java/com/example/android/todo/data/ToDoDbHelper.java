package com.example.android.todo.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.android.todo.data.ToDoContract.ToDoEntry;

/**
 * Created by Enyason on 7/29/2017.
 */

public class ToDoDbHelper extends SQLiteOpenHelper {
    private static final String DATA_BASE_NAME = "toDoList.db";
   private static final  int DATA_BASE_VERSION = 1;


    public ToDoDbHelper(Context context) {
        super(context, DATA_BASE_NAME, null, DATA_BASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create a String that contains the SQL statement to create the pets table
        String SQL_CREATE_TODO_TABLE = "CREATE TABLE " + ToDoEntry.TABLE_NAME + " ("
                + ToDoEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ToDoEntry.COLUMN_item + " TEXT NOT NULL"+")";


        // Execute the SQL statement
        db.execSQL(SQL_CREATE_TODO_TABLE);


    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ToDoEntry.TABLE_NAME);
        onCreate(db);
    }
}
