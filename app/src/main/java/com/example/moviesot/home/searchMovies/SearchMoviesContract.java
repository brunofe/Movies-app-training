package com.example.moviesot.home.searchMovies;

public interface SearchMoviesContract {
    interface View {
        void setFonts();
        void configureUpBar();
        void configForSoftInputMode();
    }
    interface Presenter{
        void setUpView();
    }
}
