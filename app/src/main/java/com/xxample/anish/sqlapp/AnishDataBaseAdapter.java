package com.xxample.anish.sqlapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by Ishan on 01-May-15.
 */
public class AnishDataBaseAdapter {

    AnishHelper helper;

    public AnishDataBaseAdapter(Context context) {
        helper = new AnishHelper(context);
    }

    public long insertData(String name, String password){

        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(AnishHelper.USERNAME , name);
        contentValues.put(AnishHelper.PASSWORD , password);
        long id = db.insert(AnishHelper.TABLE_NAME , null , contentValues);
        return id;
    }

    public String getData(){

        SQLiteDatabase db = helper.getWritableDatabase();
        String[] columns = {AnishHelper.UID,
                AnishHelper.USERNAME,
                AnishHelper.PASSWORD
        };

        Cursor cursor = db.query(AnishHelper.TABLE_NAME , columns , null,null , null ,null ,null);

        StringBuffer buffer = new StringBuffer();

        while(cursor.moveToNext()){
            int cid = cursor.getInt(0);
            String name = cursor.getString(1);
            String password = cursor.getString(2);
            buffer.append(cid+" "+name+" "+password+"\n");
        }

        return buffer.toString();
    }

    public String gettheData(String name , String pass){

        SQLiteDatabase db = helper.getWritableDatabase();
        String[] columns = {AnishHelper.UID,
                           // AnishHelper.USERNAME,
                           // AnishHelper.PASSWORD
        };
        String[] selectionArgs = {name , pass};

        Cursor cursor = db.query(AnishHelper.TABLE_NAME , columns , AnishHelper.USERNAME+" =? AND "+AnishHelper.PASSWORD+" =?"
                ,selectionArgs , null ,null ,null);

        StringBuffer buffer = new StringBuffer();

        while(cursor.moveToNext()){

            int index0 = cursor.getColumnIndex(AnishHelper.UID);
            int cid = cursor.getInt(index0);
//            int index1 = cursor.getColumnIndex(AnishHelper.USERNAME);
//            int index2 = cursor.getColumnIndex(AnishHelper.PASSWORD);
//            String personname = cursor.getString(index1);
//            String password = cursor.getString(index2);
            buffer.append(cid+" \n");
        }

        return buffer.toString();
    }

    static class AnishHelper extends SQLiteOpenHelper {

        private static final String DATABASE_NAME = "ansishdatabase";
        private static final String TABLE_NAME = "ANISH";
        private static final String UID = "_id";
        private static final String USERNAME = "username";
        private static final String PASSWORD = "password";
        private static final int DATABASE_VERSION = 7;
        private static final String CREATE_TABLE =
                "CREATE TABLE " + TABLE_NAME + "(" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                        + USERNAME + " VARCHAR(255) , " + PASSWORD + " VARCHAR(255));";
        private static final String DROP_TABLE =
                "DROP TABLE IF EXISTS "+TABLE_NAME;
        Context context;

        public AnishHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            this.context = context;
            Toast.makeText(context, "Constructor", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL(CREATE_TABLE);
                Toast.makeText(context, "onCreate", Toast.LENGTH_LONG).show();
            } catch (SQLException e) {
                Toast.makeText(context, ""+e, Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                Toast.makeText(context, "onUpgrade", Toast.LENGTH_LONG).show();
                db.execSQL(DROP_TABLE);
                onCreate(db);
            } catch (SQLException e) {
                Toast.makeText(context, ""+e, Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
        }
    }
}
