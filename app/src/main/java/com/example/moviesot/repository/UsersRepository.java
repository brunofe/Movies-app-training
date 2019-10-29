package com.example.moviesot.repository;

import com.example.moviesot.login.startScreen.StartActivity;
import com.example.moviesot.model.User;
import com.example.moviesot.repository.api.UsersService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UsersRepository {

    public void getUser(String email){

        String urlBase="https://ole.dev.gateway.zup.me/client-training/v1/users/";

        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(urlBase)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UsersService userService = retrofit.create( UsersService.class );

        /** PATH **/
        Call<User> call = userService.getUser(email);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                    if(response.isSuccessful()){
                        User user = response.body();

                        StartActivity startActivityView = new StartActivity();
                        startActivityView.criaDilog(user);
                    }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                        User user = new User();
                        user.setEmail("erro");

                StartActivity startActivityView = new StartActivity();
                startActivityView.criaDilog(user);
            }
        });
    }


}
