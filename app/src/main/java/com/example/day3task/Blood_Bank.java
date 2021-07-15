package com.example.day3task;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Blood_Bank extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_bank);
    }

    public void bbadmn(View view) {
        startActivity(new Intent(Blood_Bank.this,Blood_Bank_Admin.class));

    }

    public void bbsup(View view) {
        startActivity(new Intent(Blood_Bank.this,Blood_Bank_Sign_up.class));
    }

    public void bbsin(View view) {
    }
}