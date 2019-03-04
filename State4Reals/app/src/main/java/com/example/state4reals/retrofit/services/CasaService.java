package com.example.state4reals.retrofit.services;

import com.example.state4reals.model.Casa;
import com.example.state4reals.model.AnotherResponseContainer;
import com.example.state4reals.model.ResponseContainer;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface CasaService {

    @GET("/properties")
    Call<ResponseContainer<Casa>> listCasa();

    @GET("/properties/{id}")
    Call<AnotherResponseContainer<Casa>> unaCasa(@Path("id") String id);

    @GET("/properties/mine")
    Call<ResponseContainer<Casa>> misCasas();

    @POST("/properties/fav/{id}")
    Call<ResponseBody> setFavCasa(@Path("id") String id);

    @GET("/properties/auth")
    Call<ResponseContainer<Casa>> listFavCasas();

    @DELETE("/properties/fav/{id}")
    Call<ResponseBody> deleteFav(@Path("id") String id);
}
