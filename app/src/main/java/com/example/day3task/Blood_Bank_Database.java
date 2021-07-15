package com.example.day3task;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Blood_Bank_Database extends SQLiteOpenHelper {
    SQLiteDatabase sqLitedatabase;

    public Blood_Bank_Database(Context context) {
        super(context, "bbcandidates.db", null, 1);
        sqLitedatabase=this.getWritableDatabase();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table details(Name Text , Phone Text,Email Text , Address Text,Bgrp Text,LBDD Text,Gender Text,Dob Text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void svedata(String s, String s1, String s2, String s3, String s4, String s5, String s6, String s7) {
        ContentValues values=new ContentValues();
        values.put("Name",s);
        values.put("Phone",s1);
        values.put("Email",s2);
        values.put("Address",s3);
        values.put("Bgrp",s4);
        values.put("LBDD",s5);
        values.put("Gender",s6);
        values.put("Dob",s7);
        long sc=sqLitedatabase.insert("details",null,values);
        Log.d("abc123","save data " +sc);
    }
    public ArrayList getdetals(String s) {
        ArrayList list = new ArrayList();
        Log.d("getds","List size "+s);

        Cursor c;
        c=sqLitedatabase.query("details",null,"Bgrp=?",new String[]{s},null,null,null);

//        c.moveToFirst();

        if (c.moveToFirst()) {
            Log.d("123get","Before do ");

            do {
                Log.d("gt12","After do 1 ");

                list.add("Name : "+ c.getString(0) +"\n"+"Address : "+c.getString(3));
                list.add(c.getString(1));

                Log.d("g13","After do 2"+c.getString(0) + c.getString(3) + c.getString(1));

            } while (c.moveToNext());
        }

        c.close();
        return list;
    }

    public String fetchdetails(String pno) {
        Cursor c;
        c=sqLitedatabase.query("details",null,"Phone=?",new String[]{pno},null,null,null);
        c.moveToFirst();
        String s="Name : "+ c.getString(0) +"\n"+"Phone : "+ c.getString(1) +"\n"+"Email : "+ c.getString(2) +"\n"+
                "Address : "+ c.getString(3) +"\n"+"Blood Group : "+ c.getString(4) +"\n"+
                "Last Blood Donated Date : "+ c.getString(5) +"\n"+"Gender : "+ c.getString(6) +"\n"+
                "Date of Birth : "+ c.getString(0) +"\n";
        return s;
    }
}
