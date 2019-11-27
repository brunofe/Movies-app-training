package com.example.moviesot.utils;

import com.example.moviesot.model.Movie;

public interface MovieManager {
    interface OnDeleteMovie {
         void deleteMovie(String imdbID);
    }
    interface OnInsertFavorite {
         void insertFavorite(Movie movie);
    }
    interface OnOpenDetailMovie {
         void openMovieDetail (String imdbID);
    }
}
