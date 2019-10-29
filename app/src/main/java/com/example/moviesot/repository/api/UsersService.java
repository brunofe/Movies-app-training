package com.example.moviesot.repository.api;

import com.example.moviesot.model.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface UsersService {

    @GET("{email}")
    Call<User> getUser(@Path("email") String email);
}