package com.example.moviesot.login.startScreen;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.moviesot.R;
import com.example.moviesot.model.User;
import com.example.moviesot.repository.UsersRepository;
import com.example.moviesot.utils.GeneralUtils;

public class StartActivity extends Activity implements StartContract.View{

    TextView testeFont;
    Button bt_next;
    EditText et_start;
    GeneralUtils utils;
    StartContract.Presenter mPresenter;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        testeFont = findViewById(R.id.textView);
        bt_next = findViewById(R.id.bt_next_start);
        et_start = findViewById(R.id.et_start);
        utils = new GeneralUtils();
        mPresenter = new StartPresenter(this);
        context = this;
        mPresenter.setUpView();

    }

    /** metodo de teste apiFilmes **/
    public void criaDilog(User user, Context contextEecute){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(contextEecute);

        //configuracoes   do dialog
        alertDialog.setTitle(user.getEmail());
        alertDialog.setMessage("Complete_name= "+user.getCompleteName()+"\n" +
                               "Password= "+user.getPassword()+"\n"+
                               "Username= "+user.getUsername()+"\n"+
                               "Registration_status= "+user.getRegistrationStatus());
        alertDialog.setCancelable(false);

        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        alertDialog.create();
        alertDialog.show();
    }

    @Override
    public void setFonts() {
        utils.setFont(this,testeFont,R.font.dosis_bold);
        utils.setFont(this,bt_next,R.font.dosis_bold);
    }

    @Override
    public void configureUpBar() {
        //configurando a top_goaback_bar
        Toolbar toolbar = findViewById(R.id.top_goback_bar);
        setActionBar(toolbar);
        getActionBar().setDisplayHomeAsUpEnabled(false);
        getActionBar().setTitle("");
    }

    @Override
    public void configureButton() {
        bt_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userInput= et_start.getText().toString();

                mPresenter.getUserInfo(userInput);
            }
        });
    }

    @Override
    public void configForSoftInputMode() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
    }

    @Override
    public void goToFirstScreenFlow(String flow, String email) {

    }


}
