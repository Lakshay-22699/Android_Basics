package com.example.day3task;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity0 extends AppCompatActivity {

    ListView lv;
    ArrayList<String> arrayList;
    ArrayAdapter<String> arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main0);

        lv = findViewById(R.id.lstview);


        arrayList=new ArrayList<String>();
        arrayList.add("Contact Details Sign Up Page");//0
        arrayList.add("Video Play both raw and url");//1
        arrayList.add("Google Maps");//2
        arrayList.add("Gmail Login");//3
        arrayList.add("Notification");//4
        arrayList.add("Fragment");//5
        arrayList.add("SQLite Database");//6
        arrayList.add("Blood Bank");//7
        arrayList.add("Web Api JSON");//8

        arrayAdapter = new ArrayAdapter<String>(MainActivity0.this, android.R.layout.simple_list_item_1, arrayList);

        lv.setAdapter(arrayAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity0.this, "You have selected " + arrayList.get(position), Toast.LENGTH_SHORT).show();


                switch (position) {
                    case 0:
                        startActivity(new Intent(MainActivity0.this, MainActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(MainActivity0.this, Video_Play.class));
                        break;
                    case 2:
                        startActivity(new Intent(MainActivity0.this, Google_maps.class));
                        break;
                    case 3:
                        startActivity(new Intent(MainActivity0.this, Gmail_Login.class));
                        break;
                    case 4:
                        startActivity(new Intent(MainActivity0.this, Notification.class));
                        break;
                    case 5:
                        startActivity(new Intent(MainActivity0.this, Fragments.class));
                        break;
                    case 6:
                        startActivity(new Intent(MainActivity0.this, SQLite_Database.class));
                        break;
                    case 7:
                        startActivity(new Intent(MainActivity0.this, Blood_Bank.class));
                        break;
                    case 8:
                        startActivity(new Intent(MainActivity0.this, webapijson.class));
                        break;
                    default:
                        break;
                }
            }
        });
    }
}