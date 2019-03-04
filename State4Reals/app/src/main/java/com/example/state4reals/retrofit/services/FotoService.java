package com.example.state4reals.retrofit.services;

import com.example.state4reals.model.Foto;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface FotoService {

    @GET("/photos")
    Call<List<Foto>> listFoto();

    @DELETE("/photos/{id}")
    Call<ResponseBody> delete(@Path("id")String id);
}
