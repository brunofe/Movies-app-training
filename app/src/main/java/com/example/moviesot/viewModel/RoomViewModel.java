package com.example.moviesot.viewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.moviesot.model.Movie;
import com.example.moviesot.repository.homeRepository.RoomRepository;

import java.util.List;

public class RoomViewModel extends AndroidViewModel {

    private RoomRepository repository;

    private LiveData<List<Movie>> allMovie;
    private LiveData<Integer> listSize;

    public RoomViewModel(Application application){
        super(application);
        repository = new RoomRepository (application);
        allMovie = repository.getAllCompromissos();

    }

    public LiveData<List<Movie>> getAllMovie() { return allMovie; }



    public void insert(Movie movie) { repository.insert(movie); }
    public void delete(String idMovie) {repository.deleteMovie(idMovie);}
}
