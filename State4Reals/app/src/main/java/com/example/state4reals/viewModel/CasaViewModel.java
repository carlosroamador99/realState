package com.example.state4reals.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.state4reals.R;
import com.example.state4reals.model.AnotherResponseContainer;
import com.example.state4reals.model.Casa;
import com.example.state4reals.model.ResponseContainer;
import com.example.state4reals.retrofit.generator.ServiceGenerator;
import com.example.state4reals.retrofit.services.CasaService;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CasaViewModel extends AndroidViewModel {

    private MutableLiveData<List<Casa>> listCasas = new MutableLiveData<List<Casa>>();
    private MutableLiveData<List<Casa>> listMisCasas = new MutableLiveData<List<Casa>>();
    private MutableLiveData<Casa> casa = new MutableLiveData<Casa>();
    private MutableLiveData<List<Casa>> casasFav = new MutableLiveData<List<Casa>>();

    private CasaService service;

    public CasaViewModel(@NonNull Application application) {
        super(application);
        getAllCasas();
    }

    public void getMyCasas(final String token){
       service = ServiceGenerator.createService(CasaService.class, token);
       Call<ResponseContainer<Casa>> call = service.misCasas();
       call.enqueue(new Callback<ResponseContainer<Casa>>() {
           @Override
           public void onResponse(Call<ResponseContainer<Casa>> call, Response<ResponseContainer<Casa>> response) {
               try{
                   ResponseContainer<Casa> data = response.body();
                   listMisCasas.setValue(data.getRows());
               } catch (Exception e){
                   Log.e("onResponse", response.message());
               }
           }

           @Override
           public void onFailure(Call<ResponseContainer<Casa>> call, Throwable t) {
                    Log.e("Network Failure", t.getMessage());
           }
       });
   }

   public void setFavCasa(String token, String casaId){
        service = ServiceGenerator.createService(CasaService.class, token);
        Call<ResponseBody> call = service.setFavCasa(casaId);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d("onResponseCasaFav", response.message());
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("onFailureSetFav", t.getMessage());

            }
        });
   }

    public void setOffFavCasa(String token, String casaId){
        service = ServiceGenerator.createService(CasaService.class, token);
        Call<ResponseBody> call = service.deleteFav(casaId);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d("onResponseCasaOffFav", response.message());
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("onFailureSetOffFav", t.getMessage());

            }
        });
    }

   public void getFavCasas(String token){
        service = ServiceGenerator.createService(CasaService.class, token);
        Call<ResponseContainer<Casa>> call = service.listFavCasas();
        call.enqueue(new Callback<ResponseContainer<Casa>>() {
            @Override
            public void onResponse(Call<ResponseContainer<Casa>> call, Response<ResponseContainer<Casa>> response) {
                try {
                    ResponseContainer<Casa> data = response.body();
                    ArrayList<Casa> temp = new ArrayList<>();
                    for (Casa c : data.getRows()){
                        if(c.isFav()){
                            temp.add(c);
                        }
                    }
                    casasFav.setValue(temp);
                } catch (Exception e){
                    Log.e("Response", e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<ResponseContainer<Casa>> call, Throwable t) {
                Log.e("Network Failure", t.getMessage());

            }
        });
   }

    public void getCasaDetails(String id){
        service = ServiceGenerator.createService(CasaService.class);
        Call<AnotherResponseContainer<Casa>> call = service.unaCasa(id);
        call.enqueue(new Callback<AnotherResponseContainer<Casa>>() {
            @Override
            public void onResponse(Call<AnotherResponseContainer<Casa>> call, Response<AnotherResponseContainer<Casa>> response) {
                try {
                    AnotherResponseContainer<Casa> data = response.body();
                    casa.setValue(data.getRows());

                } catch (Exception e){
                    Log.e("onResponse", response.message());
                }
            }

            @Override
            public void onFailure(Call<AnotherResponseContainer<Casa>> call, Throwable t) {

            }
        });
    }

    public void getAllCasas(){
        service = ServiceGenerator.createService(CasaService.class);
        Call<ResponseContainer<Casa>> call = service.listCasa();
        call.enqueue(new Callback<ResponseContainer<Casa>>() {
            @Override
            public void onResponse(Call<ResponseContainer<Casa>> call, Response<ResponseContainer<Casa>> response) {
                try {
                    ResponseContainer<Casa> data = response.body();
                    listCasas.setValue(data.getRows());
                } catch (Exception e){
                    Log.d("onResponse", "Error here");
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseContainer<Casa>> call, Throwable t) {
                Log.e("NetworkError", t.getMessage());
            }
        });
    }

    public MutableLiveData<List<Casa>> getListCasas(){
        return listCasas;
    }
    public MutableLiveData<List<Casa>> getCasasFav(){
        return casasFav;
    }
    public MutableLiveData<Casa> getCasa(){
        return casa;
    }
    public MutableLiveData<List<Casa>> getListMisCasas(){
        return listMisCasas;
    }
}
