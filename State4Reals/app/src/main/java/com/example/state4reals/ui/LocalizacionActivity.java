package com.example.state4reals.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.example.state4reals.R;
import com.example.state4reals.model.Casa;
import com.example.state4reals.viewModel.CasaViewModel;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class LocalizacionActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private CasaViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_localizacion);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        viewModel = ViewModelProviders.of(this).get(CasaViewModel.class);
        viewModel.getListCasas().observe(this, new Observer<List<Casa>>() {
            @Override
            public void onChanged(@Nullable List<Casa> casas) {
                for (Casa c : casas){
                    if (!c.getLoc().isEmpty()){
                        if(c.getLoc().contains(", ")){
                            String[] part = c.getLoc().split(", ");
                            double part1 = Double.parseDouble(part[0]);
                            double part2 = Double.parseDouble(part[1]);
                            LatLng temp = new LatLng(part1, part2);

                            MarkerOptions markerOptions = new MarkerOptions()
                                    .position(temp)
                                    .title(c.getTitle())
                                    .snippet(c.getDescription())
                                    .draggable(false);
                         Marker marker = mMap.addMarker(markerOptions);
                          marker.showInfoWindow();

                        } else {
                            String[] part = c.getLoc().split(",");
                            double part1 = Double.parseDouble(part[0]);
                            double part2 = Double.parseDouble(part[1]);
                            LatLng temp = new LatLng(part1, part2);

                            MarkerOptions markerOptions = new MarkerOptions()
                                    .position(temp)
                                    .title(c.getTitle())
                                    .snippet(c.getDescription())
                                    .draggable(false);
                            Marker marker = mMap.addMarker(markerOptions);
                            marker.showInfoWindow();
                        }
                    }
                }
            }
        });

        LatLng test = new LatLng(37.38629, -6002002);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(test, 13));
    }
}
