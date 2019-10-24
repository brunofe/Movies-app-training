package com.example.moviesot.forgotPassword.newPasswordScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.moviesot.R;
import com.example.moviesot.utils.GeneralUtils;

public class NewPasswordActivity extends Activity implements NewPasswordContract.View {
    NewPasswordContract.Presenter mPresenter = new NewPasswordPresenter(this);
    GeneralUtils utils = new GeneralUtils();

    TextView title;
    TextView titleNewPassWord;
    TextView tv_didnt_receive_the_code;
    Button bt_next;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_password);

        title = findViewById(R.id.tv_title_newPassword);
        titleNewPassWord = findViewById(R.id.tv_newPassword);
        tv_didnt_receive_the_code = findViewById(R.id.tv_didnt_receive_the_code);
        bt_next = findViewById(R.id.bt_next_forgot_newPassword);

        mPresenter.setUpView();
    }

    @Override
    public void setFonts() {
        utils.setFont(this, title, R.font.dosis_bold);
        utils.setFont(this, titleNewPassWord, R.font.dosis_bold);
        utils.setFont(this, bt_next, R.font.dosis_bold);

        tv_didnt_receive_the_code.setText(utils.makeChangesInText(
                getResources().getString(R.string.didnt_receive_the_code),
                getResources().getString(R.string.send_again),
                true,
                getResources().getColor(R.color.redAlertDefault)));

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

        LinearLayout rootView = findViewById(R.id.rootView_lastForgot_screen);
        ScrollView sv = findViewById(R.id.sv_lastForgot_screen);

        utils.addAutomaticScrollDownToScrollView(rootView,sv);
        utils.setGoneWhenKeyboardOpen(rootView, titleNewPassWord);

    }
}
