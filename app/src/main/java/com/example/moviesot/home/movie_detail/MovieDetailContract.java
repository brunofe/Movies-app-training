package com.example.moviesot.home.movie_detail;

import com.example.moviesot.bases.BaseContract;
import com.example.moviesot.model.ListMovie;
import com.example.moviesot.model.MovieDetail;

public interface MovieDetailContract {
    interface View extends BaseContract.View {

        void getDetailMovie(MovieDetail movieDetail);

        void setFirstPage(ListMovie listMovie);

        void setOthersPage(ListMovie listMovie);
    }

    interface Presenter extends BaseContract.Presenter {

        void getDetailMovie(String id);

        void getListMovie(String name, final String page);
    }
}
