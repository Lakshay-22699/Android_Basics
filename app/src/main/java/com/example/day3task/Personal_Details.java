package com.example.day3task;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Personal_Details extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    TextView t1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_details);
        sharedPreferences=getSharedPreferences("personalinfo",MODE_PRIVATE);
        String s="Email :"+sharedPreferences.getString("email","String Not present")+"\n"+"First Name :"+sharedPreferences.getString("fnam","String Not present")+"\n"+"Last Name :"+sharedPreferences.getString("lnam","String Not present")+"\n"+"Mobile Number :"+sharedPreferences.getString("mobil","String Not present")+"\n";
        t1=findViewById(R.id.textView9);
        t1.setText(s);
    }

    public void gotosgnuppage(View view) {
        startActivity(new Intent(Personal_Details.this,Gmail_Login.class));
    }
}