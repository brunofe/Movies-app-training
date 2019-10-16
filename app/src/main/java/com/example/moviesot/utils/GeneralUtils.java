package com.example.moviesot.utils;

import android.content.Context;
import android.graphics.Typeface;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.core.content.res.ResourcesCompat;

public class GeneralUtils {

    public void setFont(Context context, TextView viewItem, Integer font){
        Typeface typeface = ResourcesCompat.getFont(context, font);
        viewItem.setTypeface(typeface);
    }
}
