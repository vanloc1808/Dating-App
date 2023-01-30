package com.example.datingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.dimorinny.position.ShowCasePosition;
import com.dimorinny.position.ViewPosition;
import com.example.datingapp.Utils.TopNavigationViewHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;

// https://www.youtube.com/playlist?list=PLoPTqokme6cVV_xMic2sMURVwatCfcaX4
// paused at https://www.youtube.com/watch?v=GhO3nLBGPGM&list=PLoPTqokme6cVV_xMic2sMURVwatCfcaX4&index=12
public class MainActivity extends AppCompatActivity {

    private boolean firstStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void setupTopNavigationView() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.top_navigation_view_bar);

        TopNavigationViewHelper.setupTopNavigationView(bottomNavigationView);
        TopNavigationViewHelper.enableNavigation(MainActivity.this, bottomNavigationView);

        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);

        View profileView = findViewById(R.id.icon_profile);
        View matchedView = findViewById(R.id.icon_matched);

        if (firstStart) {
            showToolTipProfile(new ViewPosition(profileView));
        }

        SharedPreferences newPreferences = getSharedPreferences("prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = newPreferences.edit();
        editor.putBoolean("firstStart", false);
        editor.apply();
    }

    private void showToolTipProfile(ShowCasePosition showCasePosition) {

    }


    public void onDislikeClicked(View view) {
    }

    public void onLikeClicked(View view) {

    }
}