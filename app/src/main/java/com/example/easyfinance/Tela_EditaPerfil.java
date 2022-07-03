package com.example.easyfinance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

public class Tela_EditaPerfil extends AppCompatActivity {
    private EditText edtUsuario, edtNome, edtSenha, edtConfirmarSenha;
    private Usuario user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_edita_perfil);

        Intent it=getIntent();
        user = (Usuario) it.getSerializableExtra("chave_user");

        edtUsuario = findViewById(R.id.textUserSalvar);
        edtNome = findViewById(R.id.textNomeSalvar);
        edtSenha = findViewById(R.id.textSenhaSalvar);
        edtConfirmarSenha = findViewById(R.id.textConfirmaSalvar);

        edtUsuario.setText(user.getUsername());
        edtNome.setText(user.getNome());
        edtSenha.setText(user.getSenha());
    }
}