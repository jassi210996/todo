package com.example.kingj.todo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TaskOpenHolder extends SQLiteOpenHelper {

    public static int version =1;
    public static final String DATABASE_NAME ="task_db";

    private static TaskOpenHolder holder;

    public static TaskOpenHolder getHolder(Context context) {
         if(holder== null)
         {
             holder=new TaskOpenHolder(context.getApplicationContext());
         }
        return holder;
    }


    private TaskOpenHolder(Context context) {
        super(context, DATABASE_NAME, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {


        String taskSql = " CREATE TABLE " + Contract.Tasks.TABLE_NAME +
                         " ( " + Contract.Tasks.ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " +
                           Contract.Tasks.COLUMN_TITLE + " TEXT , " +
                           Contract.Tasks.DUE_DATE + " TEXT )";

        sqLiteDatabase.execSQL(taskSql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
