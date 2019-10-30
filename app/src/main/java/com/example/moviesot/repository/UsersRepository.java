package com.example.moviesot.repository;

import android.content.Context;

import com.example.moviesot.login.startScreen.StartActivity;
import com.example.moviesot.model.User;
import com.example.moviesot.repository.api.UsersService;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UsersRepository {

    public void getUser(String email, final Context contextDeTeste){

        OkHttpClient.Builder okhttpBuilder = new OkHttpClient.Builder();
        okhttpBuilder.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {

                Request request = chain.request();

                Request.Builder newRequest = request.newBuilder().header("x-application-key","593c3280aedd01364c73000d3ac06d76");

                return chain.proceed(newRequest.build());
            }
        });


        /** criação do objeto retrofit **/
       String urlBase="https://ole.dev.gateway.zup.me/client-training/v1/users/";

       Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(urlBase)
                .client(okhttpBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UsersService userService = retrofit.create( UsersService.class );

        /** PATH/Aplicationkey **/
        Call<User> call = userService.getUser(email);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                    if(response.isSuccessful()){
                        User user = response.body();

                        StartActivity startActivityView = new StartActivity();
                        startActivityView.criaDilog(user, contextDeTeste);

                    }else{
                        User user = new User();
                        user.setEmail("erro");
                        StartActivity startActivityView = new StartActivity();
                        startActivityView.criaDilog(user, contextDeTeste);
                    }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                        User user = new User();
                        user.setEmail("erro");

                StartActivity startActivityView = new StartActivity();
                startActivityView.criaDilog(user, contextDeTeste);
            }
        });
    }


}
