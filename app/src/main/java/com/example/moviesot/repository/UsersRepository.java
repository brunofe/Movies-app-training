package com.example.moviesot.repository;

import com.example.moviesot.conection.RetrofitConection;
import com.example.moviesot.login.startScreen.StartPresenter;
import com.example.moviesot.model.User;
import com.example.moviesot.repository.api.UsersService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class UsersRepository {
    RetrofitConection retrofitConection = new RetrofitConection();

    public void getUser(final String email){
       UsersService userService = retrofitConection.createUserService() ;

        Call<User> call = userService.getUser(email);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                    if(response.isSuccessful()){
                        User user = response.body();

                        /** Send User status to presenter **/
                        StartPresenter presenter = new StartPresenter();
                        presenter.goToNextScreen(user.getRegistrationStatus(),email);


                    } else {
                        User user = new User();
                        user.setEmail("erro");

                    }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                        User user = new User();
                        user.setEmail("erro");
            }
        });
    }
}
