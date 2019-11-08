package com.example.moviesot.home.searchMovies;

public class SearchMoviesPresenter implements SearchMoviesContract.Presenter{

    SearchMoviesContract.View mView;

    SearchMoviesPresenter(SearchMoviesContract.View view){
        this.mView = view;
    }

    @Override
    public void setUpView() {
        mView.setFonts();
        mView.configureUpBar();
        mView.configForSoftInputMode();
    }
}
