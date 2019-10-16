package com.example.moviesot.login.startScreen;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.core.content.res.ResourcesCompat;

import com.example.moviesot.R;
import com.example.moviesot.utils.GeneralUtils;

public class startActivity extends Activity implements startContract.View{

    TextView testeFont;
    Button bt_next;
    GeneralUtils utils = new GeneralUtils();
    startContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        testeFont = findViewById(R.id.textView);
        bt_next = findViewById(R.id.bt_next_start);

        mPresenter.setUpView();
    }


    @Override
    public void setFonts() {
        utils.setFont(this,testeFont,R.font.dosis_bold);
        utils.setFont(this,bt_next,R.font.dosis_bold);
    }
}
