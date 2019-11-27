package com.example.moviesot.repository.homeRepository;

import com.example.moviesot.model.ListMovie;
import com.example.moviesot.model.MovieDetail;
import com.example.moviesot.utils.CallBack;

public interface HomeRepository {


    void getMovieList(String name, String page, MovieListaCallBack callBack);

    interface MovieListaCallBack extends CallBack<ListMovie> {

    }

    void getMovieDetail(String id, MovieDetailCallBack callBack);

    interface MovieDetailCallBack extends CallBack<MovieDetail>{

    }

   void getMovieListDetail(String id, MovieListaCallBack callBack);

}
