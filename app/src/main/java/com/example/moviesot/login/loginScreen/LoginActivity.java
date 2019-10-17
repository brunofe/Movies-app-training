package com.example.moviesot.login.loginScreen;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.appcompat.widget.AppCompatImageView;



import com.example.moviesot.R;
import com.example.moviesot.utils.GeneralUtils;

public class LoginActivity extends Activity implements LoginContract.View {

    TextView tv_title_Login;
    Button bt_login;
    GeneralUtils utils;
    LoginContract.Presenter mPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tv_title_Login = findViewById(R.id.tv_title_Login);
        bt_login = findViewById(R.id.bt_login);
        utils = new GeneralUtils();
        mPresenter = new LoginPresenter(this);

        mPresenter.setUpView();

        //para teste de animação
        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                utils.buttonAnimation(bt_login, (LinearLayout)findViewById(R.id.l_bt_login));
            }
        });
    }

    @Override
    public void setFonts() {
        utils.setFont(this, tv_title_Login, R.font.dosis_bold);
        utils.setFont(this, bt_login, R.font.dosis_bold);
    }

    @Override
    public void configureUpBar() {
        //configurando a top_goaback_bar
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
    }

}
