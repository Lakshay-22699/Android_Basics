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
import java.util.jar.Attributes;

public class DBhlpr extends SQLiteOpenHelper {
    SQLiteDatabase sqLitedatabase;

    public DBhlpr(Context context) {
        super(context, "Detailss.db", null, 1);
        sqLitedatabase=this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table students(Name Text , Location Text,Phone Text , Email Text,Password Text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public  void savedata(String s1,String s2,String s3,String s4,String s5){
        ContentValues values=new ContentValues();
        values.put("Name",s1);
        values.put("Location",s2);
        values.put("Phone",s3);
        values.put("Email",s4);
        values.put("Password",s5);
        long a=sqLitedatabase.insert("students",null,values);
        //Log.d("abc123",a+"");
    }

    public List<String> getAllLabels(){
        List<String> list = new ArrayList<String>();

        String selectQuery = "SELECT  * FROM " + "students";

        SQLiteDatabase db1 = this.getReadableDatabase();
        Cursor cursor = db1.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                db1.delete("students", "name=?", new String[]{""});
            } while (cursor.moveToNext());
        }
        if (cursor.moveToFirst()) {
            do {
                list.add(cursor.getString(0)+"   "+cursor.getString(1)+"   "+cursor.getString(2)+"   "+cursor.getString(3)+"   "+cursor.getString(4));
            } while (cursor.moveToNext());
        }

        cursor.close();
        //db1.close();

        return list;
    }

    public String getpass(String s){
        Cursor c;
        c=sqLitedatabase.query("students",null,"Phone=?",new String[]{s},null,null,null);
        c.moveToFirst();
        if(c.getCount()<1){
            return "Not Exists";
        }
        return c.getString(c.getColumnIndex("Password"));
    }

    public long updt(String s1, String s2) {
        ContentValues cv = new ContentValues();
        cv.put("Location",s2);
        return sqLitedatabase.update("students", cv, "Name = ?", new String[]{s1});
    }

    public long dlt(String toString) {
        return sqLitedatabase.delete("students","Name = '"+toString+"'",null);
    }
}
