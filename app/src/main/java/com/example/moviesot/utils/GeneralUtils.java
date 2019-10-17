package com.example.moviesot.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;

import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.content.res.ResourcesCompat;

import com.example.moviesot.R;

public class GeneralUtils {

    public void setFont(Context context, TextView viewItem, Integer font){
        Typeface typeface = ResourcesCompat.getFont(context, font);
        viewItem.setTypeface(typeface);
    }


    public void buttonAnimation(Button bt, LinearLayout linearLayout){
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                convertDpToPixel(50)
        );

        //android:layout_marginTop="-60dp"
        //(int left, int top, int right, int bottom)

        AppCompatImageView backImage = linearLayout.findViewById(R.id.behind_the_button) ;

        params.setMargins(0,convertDpToPixel(-50),0,0);
        bt.setLayoutParams(params);
        backImage.getLayoutParams().height=convertDpToPixel(50);
    }

    public static int convertDpToPixel(int dp){
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);
        return Math.round(px);
    }



}
