package com.example.moviesot.login.loginScreen;

import android.app.Activity;
import android.os.Bundle;

import com.example.moviesot.R;

public class LoginActivity extends Activity implements LoginContract.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
}
