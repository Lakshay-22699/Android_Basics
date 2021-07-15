package com.example.day3task;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.sql.Array;
import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {
    String fin,no,loc;
    ListView l1;
    ArrayList al1;
    ArrayAdapter arrayAdapter;
//    ImageView i1;
//    Bitmap pic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

//        i1=findViewById(R.id.avtr);

        fin= getIntent().getStringExtra("send1");
        no= getIntent().getStringExtra("number");
        loc= getIntent().getStringExtra("location");
//        pic = (Bitmap) getIntent().getParcelableExtra("BitmapImage");
//
//        i1.setImageBitmap(pic);

        String lines[] = fin.split("\\r?\\n");

        l1=findViewById(R.id.lv);

        al1= new ArrayList();

        for(int i=0;i< lines.length;i++)
        {
            al1.add(lines[i]);
        }

//        for(int i=0;i<al1.size();i++)
//            System.out.println(al1.get(i));

        arrayAdapter=new ArrayAdapter(MainActivity2.this, android.R.layout.simple_list_item_1,al1);
        l1.setAdapter(arrayAdapter);

        l1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(MainActivity2.this, "Hi " +al1.get(position), Toast.LENGTH_SHORT).show();

                switch (position)
                {
                    case 1:msgsend();
                        Toast.makeText(MainActivity2.this, "Message Sent", Toast.LENGTH_SHORT).show();
                        break;
                    case 3 :
                        //new Intent(MainActivity2.this,MainActivity3.class);
                        Toast.makeText(MainActivity2.this, loc, Toast.LENGTH_SHORT).show();
                        Intent obj = new Intent(MainActivity2.this, MainActivity3.class);
                        obj.putExtra("Location1", loc);
                        startActivity(obj);
                        break;

                    case 4:
                        startActivity(new Intent(MainActivity2.this,MainActivity5.class));
                        break;
                    default: break;
                }
            }
        });

    }
    public void msgsend()
    {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED)
        {
            try
            {
                SmsManager smsMgrVar = SmsManager.getDefault();
                smsMgrVar.sendTextMessage(no, null, fin, null, null);
                Toast.makeText(getApplicationContext(), "Message Sent",
                        Toast.LENGTH_LONG).show();
            }
            catch (Exception ErrVar)
            {
                Toast.makeText(getApplicationContext(),ErrVar.getMessage().toString(),
                        Toast.LENGTH_LONG).show();
                ErrVar.printStackTrace();
            }
        }
        else
        {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            {
                requestPermissions(new String[]{Manifest.permission.SEND_SMS}, 10);
            }
        }
    }


}