package com.example.moviesot.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.moviesot.model.ListMovie;
import com.example.moviesot.model.MovieDetail;
import com.example.moviesot.repository.homeRepository.HomeRepositoryImpl;

public class MovieDetailViewModel extends AndroidViewModel {

    private HomeRepositoryImpl homeRepositoryImpl;
    private MutableLiveData<MovieDetail> movieDetail = new MutableLiveData<>();
    private MutableLiveData<ListMovie> listMovie = new MutableLiveData<>();

    public MovieDetailViewModel(@NonNull Application application) {
        super(application);
        this.homeRepositoryImpl = new HomeRepositoryImpl();
    }

    public void init(String id) {

    }

    public void initMovieList(String id) {
        homeRepositoryImpl.getMovieListDetail(id ).observeForever(new Observer<ListMovie>() {
            @Override
            public void onChanged(@Nullable ListMovie listMovie) {
                getListMovie().setValue(listMovie);
            }
        });
    }

    public MutableLiveData<MovieDetail> getMovieDetail() {
        return movieDetail;
    }

    public MutableLiveData<ListMovie> getListMovie() {
        return listMovie;
    }
}
