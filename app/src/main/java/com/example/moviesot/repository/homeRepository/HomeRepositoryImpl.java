package com.example.moviesot.repository.homeRepository;

import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.moviesot.conection.RetrofitConfig;
import com.example.moviesot.model.ListMovie;
import com.example.moviesot.model.MovieDetail;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeRepositoryImpl implements HomeRepository {

    @Override
    public void getMovieList(String name, String page, final MovieListaCallBack callBack) {

        Call<ListMovie> call = new RetrofitConfig().getMovieService().getListMovie(name,page);

        call.enqueue(new Callback<ListMovie>() {
            @Override
            public void onResponse(Call<ListMovie> call, Response<ListMovie> response) {
                callBack.onSuccess(response.body());

            }

            @Override
            public void onFailure(Call<ListMovie> call, Throwable t) {
                Log.e("EMAILService", "Erro ao buscar o email:" + t.getMessage());
            }
        });
    }

    @Override
    public void getMovieDetail(String id, final MovieDetailCallBack callBack) {
        Call<MovieDetail> call = new RetrofitConfig().getMovieService().getDetailMovie(id);

        call.enqueue(new Callback<MovieDetail>() {
            @Override
            public void onResponse(Call<MovieDetail> call, Response<MovieDetail> response) {
                MovieDetail movieDetail = response.body();
                callBack.onSuccess(response.body());

            }

            @Override
            public void onFailure(Call<MovieDetail> call, Throwable t) {
                Log.e("EMAILService   ", "Erro ao buscar o email:" + t.getMessage());
                System.out.println("NAO DEU CERTOOOO");
            }
        });

    }

    @Override
    public void getMovieListDetail(String id, final MovieListaCallBack callBack) {
        Call<ListMovie> call = new RetrofitConfig().getMovieService().getListMovieDetail(id);

        call.enqueue(new Callback<ListMovie>() {
            @Override
            public void onResponse(Call<ListMovie> call, Response<ListMovie> response) {
               callBack.onSuccess(response.body());

            }

            @Override
            public void onFailure(Call<ListMovie> call, Throwable t) {
                Log.e("EMAILService", "Erro ao buscar o email:" + t.getMessage());
            }
        });
    }

    public LiveData<ListMovie> getMovieListDetail(String id) {

        Call<ListMovie> call = new RetrofitConfig().getMovieService().getListMovieDetail(id);
        final MutableLiveData<ListMovie> data = new MutableLiveData<>();

        call.enqueue(new Callback<ListMovie>() {
            @Override
            public void onResponse(Call<ListMovie> call, Response<ListMovie> response) {
                data.setValue(response.body());

            }

            @Override
            public void onFailure(Call<ListMovie> call, Throwable t) {
                Log.e("EMAILService", "Erro ao buscar o email:" + t.getMessage());
            }
        });

        return data;
    }

}
