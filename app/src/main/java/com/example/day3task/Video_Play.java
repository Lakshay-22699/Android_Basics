package com.example.day3task;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.VideoView;

public class Video_Play extends AppCompatActivity {
    VideoView vw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_play);

        vw = (VideoView)findViewById(R.id.videoView);
    }

    public void rawvdeo(View view) {
        String path = "android.resource://" + getPackageName() + "/" + R.raw.earth;
        vw.setVideoURI(Uri.parse(path));
        vw.start();
    }

    public void urlvdeo(View view) {
//        startActivity(new Intent(Video_Play.this,Video_Play_url.class));
        vw.setVideoPath("http://videocdn.bodybuilding.com/video/mp4/62000/62792m.mp4");
//        vw.setVideoPath("https://www.youtube.com/watch?v=DUwlGduupRI");
        vw.start();

    }
}