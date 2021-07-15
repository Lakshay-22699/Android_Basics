package com.example.day3task;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class webapijson extends AppCompatActivity {
//    ListView t1;
    String API;
    String name1,name2,res,usertxt,fin;
//    ArrayList arrayList;
//    ArrayAdapter arrayAdapter;
    EditText e1,e2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webapijson);

//        t1=findViewById(R.id.textView10);
        e1=findViewById(R.id.editTextTextPersonName7);
        e2=findViewById(R.id.editTextTextPersonName8);

//        arrayList=new ArrayList();
    }

    public void ftch(View view) {
        //t1.setText("Hi");
        Details d=new Details();
        usertxt=e1.getText().toString();
        API="https://api.openweathermap.org/data/2.5/weather?q="+usertxt+"&appid=024dae0a4346719ea826eac1de23eaa7";
        d.execute();
    }
    private  class Details extends AsyncTask {
        @Override
        protected Object doInBackground(Object[] objects){
            GetDat gt=new GetDat();
//            e2.setText(usertxt);
//            Log.d("abc1"," checking res   "+usertxt);
            try {
                res = gt.makeServiceCall(API);
                JSONObject wholeonj=new JSONObject(res);
//                JSONArray js=wholeonj.getJSONArray("contacts");
                fin=wholeonj.getString("name")+"\n";
//                JSONObject obj=wholeonj.getJSONObject("");
//                for(int i=0;i<js.length();i++) {
//                    JSONObject obj6 = js.getJSONObject(i);
//                    JSONObject obj7=obj6.getJSONObject("phone");
//                    name1 = obj6.getString("name");
//                    name2=obj7.getString("home");
//
//                    arrayList.add("Name : "+name1+"\n"+"Home No : "+name2);
//
//                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return "";
        }
        @Override
        protected void onPostExecute(Object o){
            super.onPostExecute(o);
//            arrayAdapter=new ArrayAdapter(webapijson.this,android.R.layout.simple_list_item_1 ,arrayList);
//            t1.setAdapter(arrayAdapter);
            e2.setText(fin);
        }
        
    }
}