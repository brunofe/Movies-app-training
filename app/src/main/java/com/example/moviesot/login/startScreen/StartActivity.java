package com.example.moviesot.login.startScreen;

import android.app.Activity;
import android.app.AlertDialog;
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
import com.example.moviesot.repository.api.CEPService;
import com.example.moviesot.model.CEP;
import com.example.moviesot.utils.GeneralUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class StartActivity extends Activity implements StartContract.View{

    TextView testeFont;
    Button bt_next;
    EditText et_start;
    GeneralUtils utils;
    StartContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        testeFont = findViewById(R.id.textView);
        bt_next = findViewById(R.id.bt_next_start);
        et_start = findViewById(R.id.et_start);
        utils = new GeneralUtils();
        mPresenter = new StartPresenter(this);

        mPresenter.setUpView();

        testRetrofit();
    }

    /** metodo de teste retrofit **/
    public void testRetrofit(){



        bt_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String entrada;
                entrada = et_start.getText().toString();

                //recuperarCEPRetrofit(entrada);
                UsersRepository repository = new UsersRepository();
                repository.getUser(entrada);

            }
        });
    }

    /** metodo de teste retrofit **/
    private void recuperarCEPRetrofit(String entrada){

        String urlBase="https://viacep.com.br/ws/"+entrada+"/";

        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(urlBase)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CEPService cepService = retrofit.create( CEPService.class);
        Call<CEP> call = cepService.recuperarCEP();

        call.enqueue(new Callback<CEP>() {
            @Override
            public void onResponse(Call<CEP> call, Response<CEP> response) {
                if( response.isSuccessful() ){
                    CEP cep = response.body();
                    criaDilog(cep);
                }
            }

            @Override
            public void onFailure(Call<CEP> call, Throwable t) {
                CEP cep = new CEP();
                cep.setCep("erro");
                cep.setComplemento("erro");
                cep.setLogradouro("erro");
                criaDilog(cep);
            }
        });

    }


    /** metodo de teste retrofit **/
    public void criaDilog(CEP cep){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(StartActivity.this);

        //configuracoes   do dialog
        alertDialog.setTitle(cep.getCep());
        alertDialog.setMessage(cep.getComplemento()+"\n"+cep.getLogradouro()+"\n");
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

    /** metodo de teste apiFilmes **/
    public void criaDilog(User user){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(StartActivity.this);

        //configuracoes   do dialog
        alertDialog.setTitle(user.getEmail());
        alertDialog.setMessage("Complete_name= "+user.getComplete_name()+"\n" +
                                "Password= "+user.getPassword()+"\n"+
                               "Username= "+user.getUsername()+"\n"+
                                "Registration_status= "+user.getRegistration_status());
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
    public void configForSoftInputMode() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
    }
}
