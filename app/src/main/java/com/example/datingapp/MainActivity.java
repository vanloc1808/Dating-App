package com.example.datingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

// https://www.youtube.com/playlist?list=PLoPTqokme6cVV_xMic2sMURVwatCfcaX4
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, LoginOrRegisterActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2000);
    }
}