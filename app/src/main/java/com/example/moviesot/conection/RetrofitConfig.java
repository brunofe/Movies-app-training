package com.example.moviesot.conection;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Header;

public class  RetrofitConfig {

    private final Retrofit retrofit;
    //private final Retrofit retrofitHome;
    public final static String VALUE_SERVICE =
            "6ac69f9d8101f54784e9a9795baaab97";
    public final static String NAME_SERVICE =
            "api_key";
    public final static String URL_SERVICE =
            "https://api.themoviedb.org/3/";

    private static String tokenHeader;

    public RetrofitConfig() {

        OkHttpClient.Builder client = new OkHttpClient.Builder();
        OkHttpClient.Builder homeService = new OkHttpClient.Builder();
        Header getFirstHeader = null;

        client.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();

                HttpUrl url = request.url().newBuilder().addQueryParameter(NAME_SERVICE, VALUE_SERVICE).build();
                request = request.newBuilder().url(url).build();
                Response response = chain.proceed(request);

                return response;
            }
        }).connectTimeout(30, TimeUnit.SECONDS) // connect timeout
                .writeTimeout(30, TimeUnit.SECONDS) // write timeout
                .readTimeout(30, TimeUnit.SECONDS);
        client.addInterceptor(logger);

        homeService.addInterceptor(logger);

        this.retrofit = new Retrofit.Builder()
                .baseUrl(URL_SERVICE)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client.build())
                .build();
    }

    private static HttpLoggingInterceptor logger =
            new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);


    public MovieService getMovieService() {
        return this.retrofit.create(MovieService.class);

    }

}
