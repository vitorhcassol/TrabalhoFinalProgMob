package com.example.easyfinance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TelaLogin extends AppCompatActivity {
    DBHelper helper = new DBHelper(this);
    private EditText edtUsername, edtSenha;
    private Button bttLogin, bttCadastra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_login);
        edtUsername = findViewById(R.id.textInputEditText2);
        edtSenha = findViewById(R.id.textInputEditText);
        bttLogin = findViewById(R.id.buttonLogin);
        bttCadastra = findViewById(R.id.buttonCadastro);

        bttLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                conectar(view);
            }
        });

        bttCadastra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                telaCadastrar(view);
            }
        });
    }

    //Método de login
    public void conectar (View view) {
        String user = edtUsername.getText().toString();
        String senha = edtSenha.getText().toString();
        String password = helper.buscaSenha(user);
        if(senha.equals(password)) {
            Intent intent = new Intent(this, TelaPerfil.class);
            startActivity(intent);
        } else {
            Toast toast = Toast.makeText(TelaLogin.this,
                    "Usuário ou senha inválidos", Toast.LENGTH_LONG);
            toast.show();
        }
    }

    //Função que leva até a tela de cadastro
    public void telaCadastrar(View view) {
        Intent intent = new Intent(this, TelaCadastro.class);
        startActivity(intent);
    }

}