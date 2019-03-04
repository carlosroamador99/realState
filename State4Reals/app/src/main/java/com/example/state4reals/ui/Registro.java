package com.example.state4reals.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.state4reals.R;
import com.example.state4reals.model.LoginResponse;
import com.example.state4reals.model.RegistroResponse;
import com.example.state4reals.retrofit.UtilToken;
import com.example.state4reals.retrofit.generator.ServiceGenerator;
import com.example.state4reals.retrofit.services.LoginService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Registro extends AppCompatActivity {

    EditText txt_email,txt_username,txt_password;
    Button btn_registro, btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        txt_email = findViewById(R.id.email);
        txt_username = findViewById(R.id.username);
        txt_password = findViewById(R.id.password);
        btn_login = findViewById(R.id.login);
        btn_registro = findViewById(R.id.registrarse);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Registro.this, Login.class);
                startActivity(intent);
                finish();
            }
        });

        btn_registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = txt_username.getText().toString().trim();
                String email = txt_email.getText().toString().trim();
                String password = txt_password.getText().toString().trim();

                RegistroResponse registro = new RegistroResponse(email, password, name, "user");

                LoginService service = ServiceGenerator.createService(LoginService.class);

                Call<LoginResponse> loginResponseCall = service.doRegister(registro);

                loginResponseCall.enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        if (response.code() == 201){
                            UtilToken.setToken(Registro.this, response.body().getToken());
                            startActivity(new Intent(Registro.this, DashBoard.class));
                        }else{
                            Toast.makeText(Registro.this, "Error en el registro", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        Log.e("NetworkFailure", t.getMessage());
                    }
                });
            }
        });
    }
}
