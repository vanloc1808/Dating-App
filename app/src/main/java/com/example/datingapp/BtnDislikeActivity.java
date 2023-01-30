package com.example.datingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.datingapp.Utils.TopNavigationViewHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BtnDislikeActivity extends AppCompatActivity {
    private static final String TAG = "BtnDislikeActivity";

    private static final int ACTIVITY_NUM = 1;

    private Context context = BtnDislikeActivity.this;

    private ImageView imageViewDislike;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_btn_dislike);

        setupTopNavigationView();

        imageViewDislike = findViewById(R.id.image_view_like);

        Intent intent = getIntent();
        String profilePictureURL = intent.getStringExtra("URL");

        if ("default".equals(profilePictureURL)) {
            Glide.with(context).load(R.drawable.profile).into(imageViewDislike);
        } else {
            Glide.with(context).load(profilePictureURL).into(imageViewDislike);
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Intent mainIntent = new Intent(BtnDislikeActivity.this, MainActivity.class);
                startActivity(mainIntent);
            }
        }).start();

    }

    private void setupTopNavigationView() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.top_navigation_view_bar);

        TopNavigationViewHelper.setupTopNavigationView(bottomNavigationView);
        TopNavigationViewHelper.enableNavigation(context, bottomNavigationView);

        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }

    public void onLikeImageButtonClicked(View view) {

    }
}