package com.example.day3task;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class SQLite_Database extends AppCompatActivity {
    EditText e1,e2,p1,email,pass,cpass;
    TextView t1;
    String s1,s2,s3,s4,s5,s6;
    DBhlpr dBhlpr;
    Spinner spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_database);
        e1=findViewById(R.id.editTextTextPersonName3);
        e2=findViewById(R.id.editTextTextPersonName5);
        p1=findViewById(R.id.editTextPhone2);
        email=findViewById(R.id.editTextTextEmailAddress2);
        pass=findViewById(R.id.editTextTextPassword);
        cpass=findViewById(R.id.editTextTextPassword2);
        spinner=findViewById(R.id.spinner);
        t1=findViewById(R.id.textView6);
        dBhlpr=new DBhlpr(this);
    }

    public void sav(View view) {
        s1=e1.getText().toString();
        s2=e2.getText().toString();
        s3=p1.getText().toString();
        s4=email.getText().toString();
        s5=pass.getText().toString();
        s6=cpass.getText().toString();
        if(s5.equals(s6)) {
            Toast.makeText(this, s1 + "\n" + s2 + "\n" + s3 + "\n" + s4 + "\n" + s5 , Toast.LENGTH_SHORT).show();
            dBhlpr.savedata(s1, s2, s3, s4, s5);
            Toast.makeText(this, "Save Success", Toast.LENGTH_SHORT).show();
            loadspinner();
        }
        else
        {
            Toast.makeText(this, "Pass and Confirm pass are not matching", Toast.LENGTH_SHORT).show();
        }
    }
    public void loadspinner()
    {
        //DBhlpr db1 = new DBhlpr(getApplicationContext());

        List<String> labels = dBhlpr.getAllLabels();

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, labels);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }

    public void chck(View view) {
        s3=p1.getText().toString();
        s5=pass.getText().toString();
        String passch=dBhlpr.getpass(s3);
        if(s5.equals(passch))
        {
            t1.setText("SUCCESS LOGIN");
            startActivity(new Intent(SQLite_Database.this,MainActivity.class));
            Toast.makeText(this, "SUCCESSFULL LOGIN", Toast.LENGTH_SHORT).show();
        }
        else
        {
            t1.setText("INVALID");
        }
    }

    public void updte(View view) {
        s1=e1.getText().toString();
        s2=e2.getText().toString();
        long chk=dBhlpr.updt(s1,s2);
        if(chk!=0)
        {
            Toast.makeText(this, "UPDATE SUCCESSFULL", Toast.LENGTH_SHORT).show();
        }
    }

    public void dlte(View view) {
        long chk=dBhlpr.dlt(e1.getText().toString());
        if(chk!=0)
        {
            Toast.makeText(this, "DELETE SUCCESSFULL", Toast.LENGTH_SHORT).show();
        }
    }
}