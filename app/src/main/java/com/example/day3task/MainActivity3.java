package com.example.day3task;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity3 extends AppCompatActivity {
    WebView wv;
    String loc;
    private ProgressDialog progDailog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        loc= getIntent().getStringExtra("Location1");
        wv=findViewById(R.id.we);

        String url="https://www.google.com/search?q="+loc;

        wv.setWebViewClient(new WebViewClient());
        wv.loadUrl(url);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu1,menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void gotoActivity4(MenuItem item) {
        startActivity(new Intent(MainActivity3.this,MainActivity4.class));
    }

    public void gotoActivity1(MenuItem item) {
        startActivity(new Intent(MainActivity3.this,MainActivity.class));
    }
}