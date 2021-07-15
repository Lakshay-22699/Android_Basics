package com.example.day3task;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

public class Gmail_SignUp extends AppCompatActivity {

    Button b1,b2;
    CheckBox c1;
    EditText e1,e2,e3,e4,e5,e6;
    String s1,s2,s3,s4,s5,s6;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Email_Database email_database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gmail_sign_up);
        b1=findViewById(R.id.sgningmail);
        c1=findViewById(R.id.shpgmail);
        e1=findViewById(R.id.passgmail);
        e2=findViewById(R.id.confirmpassgmail);
        b2=findViewById(R.id.button2);
        e3=findViewById(R.id.fname);
        e4=findViewById(R.id.lname);
        e5=findViewById(R.id.username);
        e6=findViewById(R.id.mobile1);

        email_database=new Email_Database(this);

        sharedPreferences=getSharedPreferences("personalinfo",MODE_PRIVATE);
        editor=sharedPreferences.edit();

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Gmail_SignUp.this,Gmail_Login.class));
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s1=e3.getText().toString();
                s2=e4.getText().toString();
                s3=e5.getText().toString();
                s4=e1.getText().toString();
                s5=e2.getText().toString();
                s6=e6.getText().toString();
                if(s4.equals(s5)) {
                    editor.putString("fnam",s1);
                    editor.putString("lnam",s2);
                    editor.putString("email",s3);
                    editor.putString("pass",s4);
                    editor.putString("mobil",s6);
                    editor.commit();
                    Toast.makeText(Gmail_SignUp.this, s1 + "\n" + s2 + "\n" + s3 + "\n" + s4+"\n"+s6 , Toast.LENGTH_SHORT).show();
                    email_database.savedata(s1, s2, s3, s4,s6);
                    Toast.makeText(Gmail_SignUp.this, "Save Success", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Gmail_SignUp.this, Personal_Details.class));
                }
                else
                {
                    Toast.makeText(Gmail_SignUp.this, "PASSWORDS ARE NOT MATCHING", Toast.LENGTH_SHORT).show();
                }
            }
        });

        c1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    e1.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    e2.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                else
                {
                    e1.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    e2.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
    }
}