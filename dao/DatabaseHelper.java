package com.messwalaapp.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.messwalaapp.bean.SignIn;

/**
 * Created by Aakash Jaiswal on 3/3/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper  {

    //declaration of Database fields and variables
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "mess1.db";
    private static final String TABLE_NAME = "SignUp";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_PHONENO = "phone";

    SQLiteDatabase db;
    //creation of Table Query
    private static final String TABLE_CREATE = "create table "+TABLE_NAME+"(id integer primary key not null , "+
            "email text not null , name text not null , password text not null , phone text not null);";

    public DatabaseHelper(Context context)
    {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        //Table creation
        db.execSQL(TABLE_CREATE);
        this.db=db;

    }

    public void insertDetails(SignIn c){
        db =this.getWritableDatabase();

        //Inserting data inside Table from CustomerSignInPage
        ContentValues values = new ContentValues();
        String query = "select * from "+TABLE_NAME;
        Cursor cursor= db.rawQuery(query,null);
        int count =cursor.getCount();
        values.put(COLUMN_ID,count);
        values.put(COLUMN_NAME,c.getName());
        values.put(COLUMN_EMAIL,c.getEmail());
        values.put(COLUMN_PASSWORD,c.getPassword());
        values.put(COLUMN_PHONENO,c.getPhone());
        db.insert(TABLE_NAME,null,values);
        db.close();


    }

    public String searchpass(String email){
        //Searching Password For The Email from CustomerLoginPage
        db= this.getReadableDatabase();
        String searchquery = "select email,password from "+TABLE_NAME;
        Cursor cursor = db.rawQuery(searchquery,null);
        String semail,spass;
        spass="..";
        //Using Cursor to loop through database
        if(cursor.moveToFirst()) {
            do {
                semail = cursor.getString(0);

                if (semail.equals(email)) {
                    spass = cursor.getString(1);
                    break;
                }
            } while (cursor.moveToNext());
        }
        return spass;
        //cursor.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Drop Table if Already exist
        String query = "DROP TABLE IF EXISTS"+TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);
    }


}
