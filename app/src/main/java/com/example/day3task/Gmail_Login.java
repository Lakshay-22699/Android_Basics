package com.example.day3task;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class Gmail_Login extends AppCompatActivity {
    Button btn,nxt;
    EditText e1,e2;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Email_Database email_database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gmail_login);

        btn= findViewById(R.id.crtacc);
        nxt= findViewById(R.id.button2);
        e1=findViewById(R.id.editTextTextPersonName);
        e2=findViewById(R.id.editTextTextPassword3);

        sharedPreferences=getSharedPreferences("personalinfo",MODE_PRIVATE);
        editor=sharedPreferences.edit();
        email_database=new Email_Database(this);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this, "Create Account Page is Loading", Toast.LENGTH_SHORT).show();
                //Intent obj1=new Intent(MainActivity.this,MainActivity2.class);
                //startActivity(obj1);
                startActivity(new Intent(Gmail_Login.this,Gmail_SignUp.class));
                // will go to second activity
                //commit second
            }
        });

        nxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1,s2;
                s1=e1.getText().toString();
                s2=e2.getText().toString();
                String passch=Email_Database.getpass(s1);
                if(s2.equals(passch))
                {
                    List<String> l1=email_database.getdetails(s1);
                    editor.putString("fnam",l1.get(0));
                    editor.putString("lnam", l1.get(1));
                    editor.putString("email", l1.get(2));
                    editor.putString("mobil", l1.get(3));
                    editor.commit();
                    startActivity(new Intent(Gmail_Login.this,Personal_Details.class));
                    Toast.makeText(Gmail_Login.this, "SUCCESSFULL LOGIN", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(Gmail_Login.this, "Invalid Password or Email", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}