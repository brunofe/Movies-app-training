package com.example.moviesot.fragments.Search;


import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.moviesot.R;
import com.example.moviesot.adapter.ListMovieAdapter;
import com.example.moviesot.home.movie_detail.MovieDetailActivity;
import com.example.moviesot.model.ListMovie;
import com.example.moviesot.model.Movie;
import com.example.moviesot.utils.MovieManager;
import com.example.moviesot.utils.PaginationScrollListener;
import com.example.moviesot.viewModel.RoomViewModel;
import com.example.moviesot.viewModel.SearchFragmentViewModel;

import java.util.Timer;
import java.util.TimerTask;


public class SearchFragment extends Fragment implements MovieManager.OnOpenDetailMovie, MovieManager.OnInsertFavorite, SearchContract.View{


    private SearchFragmentViewModel searchFragmentViewModel;
    private TextView textView;
    String teste;
    private EditText editText,userAvarage;
    private boolean isLoading = false;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerView mRecyclerView;
    private RoomViewModel roomViewModel;
    private ImageView backButton;
    private ListMovieAdapter mAdapter;
    private Timer timer;
    View view;
    private ProgressBar progressBar;
    private int page = 1;
    private String titleMovie;
    private AlertDialog alerta;
    public static final String EXTRA_MESSAGE_OBJECT =
            "ID";
    public static final String EXTRA_TILE_OBJECT =
            "TITLE";

    private SearchContract.Presenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_search, container, false);

        editText = v.findViewById(R.id.edit_search);

        backButton = v.findViewById(R.id.back_search);
        progressBar = v.findViewById(R.id.progress_bar);

        searchFragmentViewModel = ViewModelProviders.of(this).get(SearchFragmentViewModel.class);
        roomViewModel = ViewModelProviders.of(this).get(RoomViewModel.class);


        mPresenter = new SearchPresenter(this);


        editText.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //here is your code
                if (timer != null) {
                    timer.cancel();
                }

            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // TODO Auto-generated method stub


            }

            @Override
            public void afterTextChanged(Editable s) {
                timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        titleMovie = editText.getText().toString();

                        mPresenter.getListMovie(editText.getText().toString(), "1");

                    }
                }, 750);
            }
        });


// Set an EditText view to get user input
//        final EditText input = new EditText(getActivity());
//        alert.setView(input);
//
//        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int whichButton) {
//
//                // Do something with value!
//            }
//        });
//
//        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int whichButton) {
//                // Canceled.
//            }
//        });

        mAdapter = new ListMovieAdapter(v.getContext());
        mRecyclerView = v.findViewById(R.id.recyclerview_search);
        linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
//        searchFragmentViewModel.getListMovie().observe(this, observerMovieList);
        mAdapter.setOpenDetailMovie(this);
        mAdapter.setInsertFavorite(this);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addOnScrollListener(new PaginationScrollListener(linearLayoutManager) {
            @Override
            protected void loadMoreItems() {
                isLoading = true;
                page++;
                mPresenter.getListMovie(titleMovie, String.valueOf(page));

            }

            @Override
            public int getTotalPageCount() {
                return 10;
            }

            @Override
            public boolean isLastPage() {
                return false;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });

        return v;
    }


    @Override
    public void openMovieDetail(String id) {

        Intent intent = new Intent(getActivity(), MovieDetailActivity.class);
        intent.putExtra(EXTRA_MESSAGE_OBJECT, id);
        startActivity(intent);
    }

    @Override
    public void insertFavorite(Movie movie) {

        alertUserAvarege(movie);


    }


    private void alertUserAvarege(final Movie movie){

        String oi;
        LayoutInflater li = getLayoutInflater();
        view = li.inflate(R.layout.alert_user_avarage, null);

        view.findViewById(R.id.bt_dimiss).setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                //exibe um Toast informativo.
                //desfaz o alerta.
                alerta.dismiss();
            }
        });

        view.findViewById(R.id.bt_accept).setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                userAvarage =  view.findViewById(R.id.user_avarage);
                teste = userAvarage.getText().toString();
                movie.setVote_average(teste);
                roomViewModel.insert(movie);
                alerta.dismiss();

            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Titulo");
        builder.setView(view);
        alerta = builder.create();
        alerta.show();
    }


    public boolean isLoading() {
        return isLoading;
    }

    public void setLoading(boolean loading) {
        isLoading = loading;
    }

    @Override
    public void teste() {

    }

    @Override
    public void setFirstPage(ListMovie listMovie) {
        mAdapter.setMovieList(listMovie);
        mAdapter.addLoadingFooter();
        isLoading = false;
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void setOthersPage(ListMovie listMovie) {
        mAdapter.removeLoadingFooter();
        mAdapter.addAll(listMovie);
        mAdapter.addLoadingFooter();
        isLoading = false;
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

}
