package com.example.day3task;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class Email_Database extends SQLiteOpenHelper {
    static SQLiteDatabase sqLitedatabase;

    public Email_Database(Context context) {
        super(context, "personaldetailss.db", null, 1);
        sqLitedatabase=this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table pdetails(First_Name Text , Last_Name Text, Email Text , Password Text, Mobile Text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void savedata(String s1, String s2, String s3, String s4, String s6) {
        ContentValues values=new ContentValues();
        values.put("First_Name",s1);
        values.put("Last_Name",s2);
        values.put("Email",s3);
        values.put("Password",s4);
        values.put("Mobile",s6);
        sqLitedatabase.insert("pdetails",null,values);
//        long a=sqLitedatabase.insert("pdetails",null,values);
    }
    public static String getpass(String s1) {
        Cursor c;
        c = sqLitedatabase.query("pdetails",null,"Email=?",new String[]{s1},null,null,null);
        c.moveToFirst();
        if(c.getCount()<1){
            return "Not Exists";
        }
        return c.getString(c.getColumnIndex("Password"));
    }

    public List<String> getdetails(String s1) {
        List<String> list = new ArrayList<String>();

        Cursor c;
        c=sqLitedatabase.query("pdetails",null,"Email=?",new String[]{s1},null,null,null);

        c.moveToFirst();
        list.add(c.getString(0));
        list.add(c.getString(1));
        list.add(c.getString(2));
        list.add(c.getString(4));

        c.close();
        return list;
    }
}
