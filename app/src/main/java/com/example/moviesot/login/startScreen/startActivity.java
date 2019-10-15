package com.example.moviesot.login.startScreen;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

import com.example.moviesot.R;

public class startActivity extends Activity implements startContract.View{

    //TextView testeFont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        //testeFont = findViewById(R.id.textView);
        //Typeface typeface = getResources().getFont(R.font.dosis_bold);
       // testeFont.setTypeface(typeface);
    }
}
