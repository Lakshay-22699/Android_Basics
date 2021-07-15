package com.example.day3task;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class BB_Personal_Details extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    TextView t1;
    String pno,details;
    Blood_Bank_Database blood_bank_database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bb_personal_details);
        sharedPreferences=getSharedPreferences("bbdetails",MODE_PRIVATE);
        pno=sharedPreferences.getString("bbnumber","String Not present")+"\n";

        details=blood_bank_database.fetchdetails(pno);

        t1=findViewById(R.id.bbpdtv);
        t1.setText(details);
    }

    public void bbpdcall(View view) {
        makeCall();
    }
    protected void makeCall() {
        String d = "tel:" + pno ;
        Log.i("Make call", "");
        Intent phoneIntent = new Intent(Intent.ACTION_CALL);
        phoneIntent.setData(Uri.parse("tel:09130000000"));
        try {
            startActivity(phoneIntent);
            finish();
            Log.i("Finished making a call", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this, "Call faild, please try again later.", Toast.LENGTH_SHORT).show();
        }
    }
}