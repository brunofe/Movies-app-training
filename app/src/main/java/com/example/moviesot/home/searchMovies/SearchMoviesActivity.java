package com.example.moviesot.home.searchMovies;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toolbar;

import com.example.moviesot.R;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

public class SearchMoviesActivity extends Activity implements SearchMoviesContract.View {

    SearchMoviesContract.Presenter mPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_movies);

        mPresenter = new SearchMoviesPresenter(this);
        mPresenter.setUpView();
    }

    @Override
    public void setFonts() {

    }

    @Override
    public void configureUpBar() {
        Toolbar toolbar = findViewById(R.id.top_goback_bar);
        toolbar.setVisibility(View.GONE);

        MaterialSearchView serchView = findViewById(R.id.search_view);
        serchView.setVisibility(View.VISIBLE);
        serchView.setHint("Buscar filmes...");
        serchView.action


        /*** Percisa setar no AndroidManifest.xml para quem essa tela vai voltar:
         <activity
         android:name=".login.loginScreen.LoginActivity"
         android:parentActivityName=".activity.*nome da activity*">

         *
         *
         * ***/


    }

    @Override
    public void configForSoftInputMode() {

    }
}
