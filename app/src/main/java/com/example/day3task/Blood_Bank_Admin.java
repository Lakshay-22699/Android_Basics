package com.example.day3task;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import www.sanju.motiontoast.MotionToast;

public class Blood_Bank_Admin extends AppCompatActivity {
    EditText e1,e2;
    String s1,s2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_bank_admin);
        e1=findViewById(R.id.editTextTextPersonName9);
        e2=findViewById(R.id.editTextTextPersonName10);
    }

    public void admlogin(View view) {
        s1=e1.getText().toString();
        s2=e2.getText().toString();
        if(s1.equals("8433275201")&&s2.equals("lakshay")) {
            startActivity(new Intent(Blood_Bank_Admin.this,Blood_Bank_Admin_AddProfiles.class));
        }
        else
        {
            MotionToast.Companion.darkToast(Blood_Bank_Admin.this,
                    "Error!!!!!!","Please fill all the Credentials Right!",
                    MotionToast.TOAST_WARNING,
                    MotionToast.GRAVITY_BOTTOM,
                    MotionToast.LONG_DURATION,
                    ResourcesCompat.getFont(this, R.font.helvetica_regular));
        }
    }
}