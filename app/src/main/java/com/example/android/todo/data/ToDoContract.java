package com.example.android.todo.data;

import android.provider.BaseColumns;

/**
 * Created by Enyason on 7/29/2017.
 */

public class ToDoContract {

    public ToDoContract() {
    }

    public static final  class ToDoEntry implements BaseColumns{

        public final static String TABLE_NAME = "toDo";
        public final static  String _ID = BaseColumns._ID;
        public final static String COLUMN_item = "item";

    }
}
