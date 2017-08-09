package com.example.android.todo;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.todo.data.ToDoContract;
import com.example.android.todo.data.ToDoDbHelper;

public class EditorActivity extends AppCompatActivity {

    ToDoDbHelper toDoDbHelper;
    EditText editText;
    Cursor cursor;
    int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        toDoDbHelper = new ToDoDbHelper(this);
        editText = (EditText) findViewById(R.id.tv_update);

        Intent intent = getIntent();

        pos = intent.getIntExtra("pos", 0);
        String item = intent.getStringExtra("item");

        editText.setText(item);


    }

    public void updateTask(View view) {
        SQLiteDatabase sqLiteDatabase = toDoDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        String u = editText.getText().toString();

        values.put(ToDoContract.ToDoEntry.COLUMN_item, u);


        String selection = ToDoContract.ToDoEntry._ID + "=?";
        String[] selectionArgs = new String[]{String.valueOf(pos)};

        sqLiteDatabase.update(ToDoContract.ToDoEntry.TABLE_NAME, values, selection, selectionArgs);

        Toast.makeText(EditorActivity.this,"Task Updated Succesfully",Toast.LENGTH_SHORT).show();
        finish();

    }

    public void deleteTask(View view) {

        SQLiteDatabase sqLiteDatabase = toDoDbHelper.getWritableDatabase();
        String selection = ToDoContract.ToDoEntry._ID + "=?";
        String[] selectionArgs = new String[]{String.valueOf(pos)};
        sqLiteDatabase.delete(ToDoContract.ToDoEntry.TABLE_NAME, selection, selectionArgs);
        Toast.makeText(EditorActivity.this,"Task Deleted Succesfully",Toast.LENGTH_SHORT).show();
        finish();


    }
}
