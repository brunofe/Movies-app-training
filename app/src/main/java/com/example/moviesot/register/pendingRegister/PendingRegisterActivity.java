package com.example.moviesot.register.pendingRegister;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.moviesot.R;
import com.example.moviesot.utils.GeneralUtils;

public class PendingRegisterActivity extends Activity implements PendingRegisterContract.View {

    PendingRegisterPresenter mPresenter;
    GeneralUtils utils = new GeneralUtils();
    TextView title;
    Button bt_next;
    TextView tv_didnt_receive_the_code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_register);

        mPresenter = new PendingRegisterPresenter(this);
        title = findViewById(R.id.tv_title_pending_register);
        bt_next = findViewById(R.id.bt_next_pending_register);
        tv_didnt_receive_the_code = findViewById(R.id.tv_didnt_receive_the_code);

        mPresenter.setUpView();
    }

    @Override
    public void setFonts() {
        utils.setFont(this, title, R.font.dosis_bold);
        utils.setFont(this, bt_next, R.font.dosis_bold);

        tv_didnt_receive_the_code.setText(utils.makeChangesInText(
                getResources().getString(R.string.didnt_receive_the_code),
                getResources().getString(R.string.send_again),
                true,
                getResources().getColor(R.color.redAlertDefault)
        ));
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
