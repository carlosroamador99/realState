package com.example.state4reals.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.state4reals.R;
import com.example.state4reals.model.LoginResponse;
import com.example.state4reals.model.Usuario;
import com.example.state4reals.retrofit.UtilToken;
import com.example.state4reals.retrofit.generator.ServiceGenerator;
import com.example.state4reals.retrofit.services.LoginService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

    EditText email, password;
    Button btnLogin,btnRegistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        btnLogin = findViewById(R.id.login);
        btnRegistro = findViewById(R.id.registro);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email_txt = email.getText().toString();
                String password_txt = password.getText().toString();

                LoginService service = ServiceGenerator.createService(LoginService.class, email_txt, password_txt);
                Call<LoginResponse> call = service.doLogin();

                call.enqueue(new Callback<LoginResponse>(){
                    @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response){
                        if (response.code() != 201) {
                            Log.e("Reques Error", response.message());
                        } else {
                            Usuario tmp = response.body().getUser();
                            UtilToken.setToken(Login.this, response.body().getToken());
                            UtilToken.setuserData(Login.this, tmp.getName(), tmp.getEmail(),tmp.getPicture());

                            Log.i("Prueba", "Evento click");
                            Intent i = new Intent(
                                Login.this,
                                    DashBoard.class
                            );
                            startActivity(i);
                            finish();
                        }
                    }
                public void onFailure(Call<LoginResponse> call, Throwable t){
                    Log.e("Network Failure", t.getMessage());

                }
                });
            }
        });

        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Registro.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
