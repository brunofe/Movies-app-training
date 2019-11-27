package com.example.moviesot.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.moviesot.model.ListMovie;
import com.example.moviesot.repository.homeRepository.HomeRepositoryImpl;

public class SearchFragmentViewModel extends AndroidViewModel {

    private HomeRepositoryImpl homeRepositoryImpl;
    private MutableLiveData<ListMovie> listMovie = new MutableLiveData<>();

    public SearchFragmentViewModel(@NonNull Application application) {
        super(application);

        this.homeRepositoryImpl = new HomeRepositoryImpl();

    }

//    public void init(String name, String page) {
//
//
//        homeRepositoryImpl.getMovieList(name,page).observeForever(new Observer<ListMovie>() {
//            @Override
//            public void onChanged(@Nullable ListMovie listMovie) {
//                getListMovie().setValue(listMovie);
//            }
//        });
//    }

    public void verify(){

    }

    public MutableLiveData<ListMovie> getListMovie() {
        return listMovie;
    }
}
