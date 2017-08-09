package com.example.android.todo;

import android.content.ContentValues;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.android.todo.data.ToDoContract;
import com.example.android.todo.data.ToDoContract.ToDoEntry;
import com.example.android.todo.data.ToDoDbHelper;



public class MainActivity extends AppCompatActivity {

    EditText textItem;
    String toDoItem;

    ToDoDbHelper toDoDbHelper;

    Cursor cursor;
    ListView listView;
    ToDoCursorAdapter cursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toDoDbHelper = new ToDoDbHelper(this);





        listView = (ListView)findViewById(R.id.list_toDo);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                alertDialog.setIcon(R.mipmap.ic_launcher);
                alertDialog.setTitle("Add Task");

                ViewGroup parent = (LinearLayout)findViewById(R.id.linearLayout);
                final View v = LayoutInflater.from(MainActivity.this).inflate(R.layout.alert_view,parent,false);

                alertDialog.setView(v);
                alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//
                        textItem = (EditText)v.findViewById(R.id.alert);
                        toDoItem = textItem.getText().toString().trim();

                        if (toDoItem.isEmpty()) {
                            Toast.makeText(MainActivity.this,"No Task Entered!",Toast.LENGTH_SHORT).show();
                        }else {
                            insert();
                            displayToDo();
                            Toast.makeText(MainActivity.this,"New Task Entered!",Toast.LENGTH_SHORT).show();
                        }


                    }
                });
                alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                    }
                });
                alertDialog.show();


            }
        });
//
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {



                int itemColumnIndex = cursor.getColumnIndex(ToDoEntry.COLUMN_item);
                int idColumnIndex = cursor.getColumnIndex(ToDoEntry._ID);
                int mId = cursor.getInt(idColumnIndex);
                String item = cursor.getString(itemColumnIndex);

                Intent intent = new Intent(MainActivity.this,EditorActivity.class);
                intent.putExtra("pos",mId);
                intent.putExtra("item",item);
                startActivity(intent);

            }
        });


    }


    public void insert() {
;

        SQLiteDatabase sqLiteDatabase = toDoDbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();


        contentValues.put(ToDoEntry.COLUMN_item,toDoItem);


        sqLiteDatabase.insert(ToDoEntry.TABLE_NAME,null,contentValues);



    }

    public void displayToDo() {
        String[] projection = {ToDoEntry._ID,
                ToDoEntry.COLUMN_item,};

        SQLiteDatabase sqLiteDatabase = toDoDbHelper.getReadableDatabase();

        // to get a Cursor that contains all rows from the pets table.
        cursor = sqLiteDatabase.query(ToDoEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null);

        cursorAdapter = new ToDoCursorAdapter(this,cursor);
            listView.setAdapter(cursorAdapter);





    }
    

    public void deleteAllPets(){

        SQLiteDatabase sqLiteDatabase = toDoDbHelper.getWritableDatabase();
        sqLiteDatabase.delete(ToDoContract.ToDoEntry.TABLE_NAME, null, null);
        Toast.makeText(MainActivity.this,"All Task Deleted",Toast.LENGTH_SHORT).show();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.clear:
                deleteAllPets();
                displayToDo();

                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        displayToDo();

    }


   }
