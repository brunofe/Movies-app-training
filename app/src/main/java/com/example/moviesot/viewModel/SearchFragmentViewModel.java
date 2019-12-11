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

    public void verify(){

    }

    public MutableLiveData<ListMovie> getListMovie() {
        return listMovie;
    }
}
