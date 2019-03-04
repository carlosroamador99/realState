package com.example.state4reals.retrofit.services;

import com.example.state4reals.model.ResponseContainer;
import com.example.state4reals.model.Usuario;

import retrofit2.Call;
import retrofit2.http.GET;

public interface UserService {

    @GET("/me")
    Call<ResponseContainer<Usuario>> misDetalles();
}
