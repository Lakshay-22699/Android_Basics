package com.example.day3task;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Common_Bgrp extends AppCompatActivity {

    ListView l1;
    ArrayList al1,al2;
    ArrayAdapter arrayAdapter;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String bgrp;
    Blood_Bank_Database blood_bank_database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_bgrp);
        l1=findViewById(R.id.bbcbglv);
        al1= new ArrayList();
        al2= new ArrayList();

        sharedPreferences=getSharedPreferences("bbdetails",MODE_PRIVATE);
        bgrp=sharedPreferences.getString("bbbgrp","String Not present");
        Log.d("bgr12","Blood group :"+bgrp);
        editor=sharedPreferences.edit();

        al2=blood_bank_database.getdetals(bgrp);

        for(int i=0;i<al2.size();i+=2)
        {
            al1.add(al2.get(i));
        }

        arrayAdapter=new ArrayAdapter(Common_Bgrp.this, android.R.layout.simple_list_item_1,al1);
        l1.setAdapter(arrayAdapter);

        l1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                editor.putString("bbnumber",al2.get((position+1)*2-1).toString());
                startActivity(new Intent(Common_Bgrp.this,BB_Personal_Details.class));
            }
        });
    }
}