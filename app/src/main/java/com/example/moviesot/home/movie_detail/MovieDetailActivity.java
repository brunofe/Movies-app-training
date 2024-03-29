package com.example.moviesot.home.movie_detail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.moviesot.R;
import com.example.moviesot.adapter.ListMovieAdapterDetail;
import com.example.moviesot.model.ListMovie;
import com.example.moviesot.model.MovieDetail;
import com.example.moviesot.utils.MovieManager;
import com.example.moviesot.viewModel.MovieDetailViewModel;

public class MovieDetailActivity extends AppCompatActivity implements MovieManager.OnOpenDetailMovie, MovieDetailContract.View{

    private String id;
    private String title;
    int page = 1;
    TextView titleActionBar;
    private TextView description;
    private boolean isLoading = false;
    private ImageView bannerMovie, posterMovie;
    private MovieDetailViewModel movieDetailViewModel;
    String text = "<font color=#FFFFFF>MOVIES</font><font color=#FFFFFF><b>OT</b></font>";
    private RecyclerView mRecyclerView;
    private ListMovieAdapterDetail mAdapter;
    private LinearLayoutManager linearLayoutManager;
    public static final String EXTRA_MESSAGE_OBJECT =
            "ID";
    private MovieDetailContract.Presenter mPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);


        Toolbar myToolbar = findViewById(R.id.my_toolbar_movie_detail);
        mPresenter = new MovieDetailPresenter(this);
        setSupportActionBar(myToolbar);
        titleActionBar = (TextView) findViewById(R.id.titleActionBar_movie_detail);
        titleActionBar.setText(Html.fromHtml(text));
        movieDetailViewModel = ViewModelProviders.of(this).get(MovieDetailViewModel.class);

        Intent intent = getIntent();

        id = intent.getStringExtra("ID");
        title = intent.getStringExtra("TITLE");

//        movieDetailViewModel.init(String.valueOf(id));
//        movieDetailViewModel.getMovieDetail().observe(this, observerMovieDetail);

        mPresenter.getDetailMovie(id);
        mPresenter.getListMovie(id,"1");


        mAdapter = new ListMovieAdapterDetail(this);
        mRecyclerView = findViewById(R.id.recyclerview_movie_detail);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
//        movieDetailViewModel.getListMovie().observe(this, observerMovieList);
        mAdapter.setOpenDetailMovie(this);
        mRecyclerView.setAdapter(mAdapter);


    }


    public boolean isLoading() {
        return isLoading;
    }

    public void setLoading(boolean loading) {
        isLoading = loading;
    }


    @Override
    public void openMovieDetail(String id) {

        Intent intent = new Intent(this, MovieDetailActivity.class);
        intent.putExtra(EXTRA_MESSAGE_OBJECT, id);

        startActivity(intent);
    }

    @Override
    public void getDetailMovie(MovieDetail movieDetail) {
        mAdapter.setMovieDetail(movieDetail);
    }


    @Override
    public void setFirstPage(ListMovie listMovie) {
        mAdapter.setMovieList(listMovie);
        isLoading = false;

    }

    @Override
    public void setOthersPage(ListMovie listMovie) {
        mAdapter.addAll(listMovie);
        isLoading = false;

    }

    public void back_activity(View view) {
        this.finish();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }
}
