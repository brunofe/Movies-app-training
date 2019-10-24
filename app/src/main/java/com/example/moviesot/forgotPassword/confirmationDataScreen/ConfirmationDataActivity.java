package com.example.moviesot.forgotPassword.confirmationDataScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.moviesot.R;
import com.example.moviesot.utils.GeneralUtils;

public class ConfirmationDataActivity extends Activity implements ConfirmationDataContract.View {
    ConfirmationDataContract.Presenter mPresenter = new ConfirmationDataPresenter(this);
    GeneralUtils utils = new GeneralUtils();

    TextView title;
    Button bt_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation_data);

        title = findViewById(R.id.tv_title_confirmation_data);
        bt_next = findViewById(R.id.bt_next_confirmation_data);

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
    }
}
