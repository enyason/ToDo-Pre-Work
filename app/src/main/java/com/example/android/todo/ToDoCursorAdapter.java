package com.example.android.todo;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.todo.data.ToDoContract;

/**
 * Created by Enyason on 7/30/2017.
 */

public class ToDoCursorAdapter extends CursorAdapter {
    public ToDoCursorAdapter(Context context, Cursor c) {
        super(context, c);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
       return LayoutInflater.from(context).inflate(R.layout.list_item,parent,false);

    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView tvItem = (TextView)view.findViewById(R.id.tv_item);
//        TextView tvStatus = (TextView)view.findViewById(R.id.tv_status);

//        String item = cursor.getString(cursor.getColumnIndexOrThrow(ToDoContract.ToDoEntry.COLUMN_item));
        int itemColumnIndex = cursor.getColumnIndex(ToDoContract.ToDoEntry.COLUMN_item);
        String item = cursor.getString(itemColumnIndex);
        tvItem.setText(item);



    }
}
