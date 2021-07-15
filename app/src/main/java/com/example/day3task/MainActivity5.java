package com.example.day3task;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity5 extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    TextView t1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        t1=findViewById(R.id.textView4);
        sharedPreferences=getSharedPreferences("data",MODE_PRIVATE);
        t1.setText(sharedPreferences.getString("alldata","String Not present"));
    }
}