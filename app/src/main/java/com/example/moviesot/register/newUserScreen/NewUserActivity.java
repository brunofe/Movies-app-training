package com.example.moviesot.register.newUser;

import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.moviesot.R;
import com.example.moviesot.utils.GeneralUtils;

public class NewUserActivity extends Activity implements NewUserContract.View {

    TextView title;
    TextView subTitle_newUser;
    EditText complete_name;
    EditText user_name;
    EditText passWord;
    Button bt_next;
    ScrollView sv;

    GeneralUtils utils;
    NewUserContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        title = findViewById(R.id.tv_title_newUser);
        subTitle_newUser = findViewById(R.id.tv_subTitle_newUser);
        complete_name = findViewById(R.id.et_new_complete_name);
        user_name = findViewById(R.id.et_new_user_name);
        passWord = findViewById(R.id.et_new_passWord);
        bt_next = findViewById(R.id.bt_next_new_account);
        sv=findViewById(R.id.sv_newUser);


        utils = new GeneralUtils();
        mPresenter = new NewUserPresenter(this);
        mPresenter.setUpView();
    }

    @Override
    public void setFonts() {
        utils.setFont(this, title, R.font.dosis_bold);
        utils.setFont(this, bt_next, R.font.dosis_bold);
    }

    @Override
    public void configureUpBar() {
        Toolbar toolbar = findViewById(R.id.top_goback_bar);
        setActionBar(toolbar);
        /*** Percisa setar no AndroidManifest.xml para quem essa tela vai voltar:
         <activity
         android:name=".login.loginScreen.LoginActivity"
         android:parentActivityName=".activity.*nome da activity*">

         *
         *
         * ***/
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setTitle("");

    }

    @Override
    public void configForSoftInputMode() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        LinearLayout rootView = findViewById(R.id.rootView_newUser);

        utils.addAutomaticScrollDownToScrollView(rootView,sv);
    }

}
