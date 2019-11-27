package com.example.moviesot.repository.homeRepository;

import android.app.Application;
import android.os.AsyncTask;
import androidx.lifecycle.LiveData;
import com.example.moviesot.RoomDB.FavoriteMovieDataBase;
import com.example.moviesot.RoomDB.MovieDAO;
import com.example.moviesot.model.Movie;

import java.util.List;

public class RoomRepository {

    private MovieDAO movieDAO;
    private LiveData<List<Movie>> allMovie;


   public RoomRepository(Application application){
        FavoriteMovieDataBase db = FavoriteMovieDataBase.getDatabase(application);

        movieDAO = db.movieDAO();
        allMovie = movieDAO.getAllCompromissos();

    }

    public LiveData<List<Movie>> getAllCompromissos() {

        System.out.println("PRINTAD GEALL REPOSRIOTY");
       return allMovie;
    }


    public void insert (Movie movie) {

       new insertAsyncTask(movieDAO).execute(movie);
    }

    public void deleteMovie(String idMovie)  {

        new deleteMovieAsyncTask(movieDAO).execute(idMovie);
    }

    private static class insertAsyncTask extends AsyncTask<Movie, Void, Void> {

        private MovieDAO mAsyncTaskDao;

        insertAsyncTask(MovieDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Movie... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class deleteMovieAsyncTask extends AsyncTask<String, Void, Void> {
        private MovieDAO mAsyncTaskDao;

        deleteMovieAsyncTask(MovieDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final String... params) {
            mAsyncTaskDao.deleteMovie(params[0]);
            return null;
        }
    }
}
