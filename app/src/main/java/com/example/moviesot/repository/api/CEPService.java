package com.example.moviesot.repository.api;

import com.example.moviesot.model.CEP;

import retrofit2.Call;
import retrofit2.http.GET;

//interface de teste do retrofit, ela será apagaa posteriormente
//essa classe faz toda a configuração para que consigamos recuperar os dados
public interface CEPService {

    //Call é chmada ao servidor web, e apos finalizar essa chamada nós
    //pode ser GET, DELETE,POST....
    @GET("json/")
    Call<CEP> recuperarCEP();

}