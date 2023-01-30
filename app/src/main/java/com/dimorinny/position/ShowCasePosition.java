package com.dimorinny.position;


import android.app.Activity;
import android.graphics.Point;
import android.graphics.PointF;;
import android.widget.ScrollView;
import androidx.annotation.Nullable;

public interface ShowCasePosition {

    PointF getPosition(Activity activity);

    @Nullable
    Point getScrollPosition(@Nullable ScrollView scrollView);
}

