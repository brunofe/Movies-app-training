package com.example.moviesot.conection;

import com.example.moviesot.model.ListMovie;
import com.example.moviesot.model.MovieDetail;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieService {

    @GET("search/movie")
    Call<ListMovie> getListMovie(@Query("query") String name, @Query("page") String page);

    @GET("movie/{movie_id}/similar")
    Call<ListMovie> getListMovieDetail(@Path("movie_id") String movie_id);

    @GET("movie/{movie_id}")
    Call<MovieDetail> getDetailMovie(@Path("movie_id") String movie_id);

}
