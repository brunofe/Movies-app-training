package com.example.moviesot.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.ColorSpace;
import android.graphics.Typeface;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
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

    public void addAutomaticScrollDownToScrollView(final LinearLayout rootView, final ScrollView sv){
        sv.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int heightDiff = rootView.getRootView().getHeight() - rootView.getHeight();

                if (heightDiff > 100) {
                    scrollDown(sv);
                    Log.i("Keyboard", "keyboard aberto");
                } else {
                    Log.i("Keyboard", "keyboard fexado");
                }
            }
        });
    }

    public void scrollDown(final ScrollView sv){
        sv.post(new Runnable() {

            @Override
            public void run() {
                sv.fullScroll(ScrollView.FOCUS_DOWN);
            }
        });
    }

    //seta bold em uma string e uma cor("valor hexadecimal") em uma palavra especifica no meio de um texto
    public SpannableStringBuilder makeChangesInText(String text, String middleText, boolean bold, int color){
        final SpannableStringBuilder sb = new SpannableStringBuilder(text);

        StyleSpan boldSpan = new StyleSpan(Typeface.BOLD);
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(color);

        Integer indexOf = text.indexOf(middleText);
        Integer lastIndexOf = text.indexOf(middleText)+middleText.length();

        sb.setSpan(colorSpan, indexOf, lastIndexOf, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        if(bold) sb.setSpan(boldSpan, indexOf, lastIndexOf, Spannable.SPAN_INCLUSIVE_INCLUSIVE);

        return sb;
    }


}
