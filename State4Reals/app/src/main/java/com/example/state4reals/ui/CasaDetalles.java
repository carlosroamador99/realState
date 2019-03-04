package com.example.state4reals.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Property;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.state4reals.R;
import com.example.state4reals.model.Casa;
import com.example.state4reals.retrofit.services.CasaInteractionListener;
import com.example.state4reals.viewModel.CasaViewModel;

import java.util.List;

public class CasaDetalles extends AppCompatActivity {

    String casaId;
    Casa temp;
    private CasaViewModel viewModel;
    TextView titulo,
            descripcion,
            direccion,
            precio,
            habitaciones,
            tamanio,
            codPost,
            ciudad,
            localizacion,
            provincia;
    ImageView foto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_casa_detalles);

        casaId = getIntent().getStringExtra("id");

        titulo = findViewById(R.id.title);
        descripcion = findViewById(R.id.descripction);
        direccion = findViewById(R.id.adress);
        precio = findViewById(R.id.price);
        habitaciones = findViewById(R.id.rooms);
        tamanio = findViewById(R.id.size);
        codPost = findViewById(R.id.zipcode);
        ciudad = findViewById(R.id.city);
        localizacion = findViewById(R.id.loc);
        provincia = findViewById(R.id.province);
        foto = findViewById(R.id.foto);


        viewModel = ViewModelProviders.of(this).get(CasaViewModel.class);
        viewModel.getCasaDetails(casaId);

        viewModel.getCasa().observe(this, new Observer<Casa>() {
            @Override
            public void onChanged(@Nullable Casa casa) {
                temp = casa;
                titulo.setText(casa.getTitle());
                descripcion.setText(casa.getDescription());
                direccion.setText(casa.getAddress());
                precio.setText(Integer.toString(casa.getPrice()));
                habitaciones.setText(Integer.toString(casa.getRoom()));
                tamanio.setText(Integer.toString(casa.getSize()));
                codPost.setText(casa.getZipcode());
                ciudad.setText(casa.getCity());
                localizacion.setText(casa.getLoc());
                provincia.setText(casa.getProvince());
                if (casa.getPhotos() != null) {
                    Glide
                            .with(CasaDetalles.this)
                            .load(casa.getPhotos()[0])
                            .into(foto);
                }

            }
        });

    }
}
