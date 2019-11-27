package com.example.moviesot.repository.homeRepository;

import com.example.moviesot.model.ListMovie;
import com.example.moviesot.model.MovieDetail;
import com.example.moviesot.utils.CallBack;

public interface HomeRepository {


    interface MovieListaCallBack extends CallBack<ListMovie>{

    }

    void getMovieList(String name,String page, MovieListaCallBack callBack);

    void getMovieListDetail(String id, MovieListaCallBack callBack);

    interface MovieDetailCallBack extends CallBack<MovieDetail>{

    }
    void getMovieDetail(String id, MovieDetailCallBack callBack);

}
