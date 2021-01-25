package com.androidavanzado.bookingaitor.usuario;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.androidavanzado.bookingaitor.R;
import com.androidavanzado.bookingaitor.beans.Usuario;
import com.androidavanzado.bookingaitor.ciudad.listCiudad.findAll.view.ListCiudadActivity;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {
    private EditText etEmail, etPassword;
    private Button btnLogin;
    private LoginPresenter presenter;
    private ArrayList<Usuario> usuarios;
    private String etEmailValue, etPasswordValue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        presenter = new LoginPresenter(this);
        initComponents();

        btnLogin.setOnClickListener(v -> {
            etEmailValue = etEmail.getText().toString();
            etPasswordValue = etPassword.getText().toString();
            Usuario usuario = new Usuario();
            usuario.setEmail(etEmailValue);
            usuario.setPassword(etPasswordValue);
            presenter.doLoginPresenter(usuario);
        });

    }

    public void initComponents(){
        etEmail = findViewById(R.id.tvEmail);
        etPassword = findViewById(R.id.tvPassword);
        btnLogin = findViewById(R.id.btnLogin);
    }

    @Override
    public void onSuccess(Usuario usuario) {
        Toast.makeText(this, "Hola de nuevo " + usuario.getEmail(), Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, ListCiudadActivity.class);
        startActivity(intent);
    }

    @Override
    public void onFailure(String error) {
        Toast.makeText(this, "INCORRECTO", Toast.LENGTH_LONG).show();
    }
}
