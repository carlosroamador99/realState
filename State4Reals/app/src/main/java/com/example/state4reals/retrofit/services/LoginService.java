package com.example.state4reals.retrofit.services;

import com.example.state4reals.model.LoginResponse;
import com.example.state4reals.model.RegistroResponse;
import com.example.state4reals.ui.Registro;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginService {

    @POST("/auth")
    Call<LoginResponse> doLogin();

    @POST("/users")
    Call<LoginResponse> doRegister(@Body RegistroResponse registro);
}
