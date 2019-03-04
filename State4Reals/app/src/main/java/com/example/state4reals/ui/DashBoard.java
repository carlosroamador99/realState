package com.example.state4reals.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.state4reals.R;
import com.example.state4reals.model.Casa;
import com.example.state4reals.retrofit.UtilToken;
import com.example.state4reals.retrofit.services.CasaInteractionListener;
import com.example.state4reals.retrofit.services.OnCasaFavInteractionListener;
import com.example.state4reals.retrofit.services.OnMyCasaInteractionListener;
import com.example.state4reals.retrofit.services.UserInteractionListener;
import com.example.state4reals.ui.fragments.CasasFavFragment;
import com.example.state4reals.ui.fragments.CasasFragment;
import com.example.state4reals.ui.fragments.MyCasasFragment;
import com.example.state4reals.ui.fragments.UserFragment;
import com.example.state4reals.viewModel.CasaViewModel;

public class DashBoard extends AppCompatActivity implements CasaInteractionListener, UserInteractionListener, OnCasaFavInteractionListener, OnMyCasaInteractionListener {

    private TextView mTextMessage;


    private Fragment fCasas, fUsuarios;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment f = null;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    f = new CasasFragment();
                    //mTextMessage.setText(R.string.title_home);
                    break;
                case R.id.navigation_map:
                    Intent intent = new Intent(DashBoard.this, LocalizacionActivity.class);
                    startActivity(intent);
                    //mTextMessage.setText(R.string.title_dashboard);
                    break;
                case R.id.casas_fav:
                    f = new CasasFavFragment();
                    break;
                case R.id.mis_casas:
                    if(UtilToken.getToken(DashBoard.this) == null) {
                        startActivity(new Intent(DashBoard.this, Login.class));
                        finish();
                    }else{
                        f = new MyCasasFragment();
                    }
                    break;
                case R.id.usuario:
                    if(UtilToken.getToken(DashBoard.this) == null) {
                        startActivity(new Intent(DashBoard.this, Login.class));
                        finish();
                    }else{
                        f = new UserFragment();
                    }

                    break;
            }
            if (f != null){
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, f)
                        .commit();
            }

            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        fCasas = new CasasFragment();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, fCasas)
                .commit();

    }

    @Override
    public void onClickFav(String id) {
        CasaViewModel viewModel = ViewModelProviders.of(this).get(CasaViewModel.class);
        
        if (UtilToken.getToken(this) != null){
            viewModel.setFavCasa(UtilToken.getToken(DashBoard.this), id);
            Toast.makeText(this, "Añadido a Favoritos", Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(this, "Logeate en Usuario", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClickView(String casaId) {
        Intent intent = new Intent(DashBoard.this, CasaDetalles.class );
        intent.putExtra("id", casaId);
        startActivity(intent);
    }

    @Override
    public void onUserClick() {

    }
}
