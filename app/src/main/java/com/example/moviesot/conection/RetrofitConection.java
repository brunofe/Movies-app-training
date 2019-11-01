package com.example.moviesot.conection;

import com.example.moviesot.repository.api.UsersService;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitConection{


    public UsersService createUserService(){
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

        return retrofit.create( UsersService.class );
    }
}
