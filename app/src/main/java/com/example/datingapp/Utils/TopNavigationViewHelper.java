package com.example.datingapp.Utils;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.example.datingapp.MatchesActivity;
import com.example.datingapp.R;
import com.example.datingapp.SettingsActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class TopNavigationViewHelper {
    private static final String TAG = "TopNavigationViewHelper";

    public static void setupTopNavigationView(BottomNavigationView tv) {
        Log.d(TAG, "setupTopNavigationView: setting up navigation view");
    }

    public static void enableNavigation(final Context context, BottomNavigationView view) {
        view.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.icon_profile:
                        Intent intent1 = new Intent(context, SettingsActivity.class);
                        context.startActivity(intent1);
                        break;

                    case R.id.icon_matched:
                        Intent intent2 = new Intent(context, MatchesActivity.class);
                        context.startActivity(intent2);
                        break;
                }

                return false;
            }
        });
    }
}
