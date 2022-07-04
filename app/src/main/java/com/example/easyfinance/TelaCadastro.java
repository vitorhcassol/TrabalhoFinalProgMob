package com.example.easyfinance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.ConditionVariable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TelaCadastro extends AppCompatActivity {
    DBHelper helper = new DBHelper(this);
    private EditText edtUsername;
    private EditText edtNome;
    private EditText edtSenha;
    private EditText edtConfirmarSenha;
    private Button bttCadastrar;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro);

        edtUsername = findViewById(R.id.textUserCadastra);
        edtNome = findViewById(R.id.textNomeCadastra);
        edtSenha = findViewById(R.id.textSenhaCadastra);
        edtConfirmarSenha = findViewById(R.id.textConfirmaCadastra);
        bttCadastrar = findViewById(R.id.buttonTelaCadastro);

        usuario = new Usuario();

        bttCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cadastrar(view);
            }
        });
    }

    //Método que cadastra um novo usuário
    public void cadastrar(View view) {
        String username = edtUsername.getText().toString();
        String nome = edtNome.getText().toString();
        String senha = edtSenha.getText().toString();
        String confirmaSenha = edtConfirmarSenha.getText().toString();
        Boolean confirmaUsername = helper.buscarUsername(username);
        if(senha.length()>=6){
            if(!senha.equals(confirmaSenha)){
            Toast toast = Toast.makeText(TelaCadastro.this,
                    "Senha diferente da confirmação de senha!",
                    Toast.LENGTH_SHORT);
            toast.show();
            edtSenha.setText("");
            edtConfirmarSenha.setText("");
        } else if (confirmaUsername.equals(true)) {
            Toast toast = Toast.makeText(TelaCadastro.this,
                    "Username já existente!",
                    Toast.LENGTH_SHORT);
            toast.show();
            edtUsername.setText("");
        } else {
            usuario.setUsername(username);
            usuario.setNome(nome);
            usuario.setSenha(senha);

            helper.insereUsuario(usuario);
            Toast toast = Toast.makeText(TelaCadastro.this,
                    "Usuário cadastrado com sucesso", Toast.LENGTH_LONG);
            toast.show();
            limpar();
            finish();
        }
        }else{
            Toast toast = Toast.makeText(TelaCadastro.this,
                    "Senha deve conter no minimo 6 caracteres",
                    Toast.LENGTH_SHORT);

        }

    }

    public void limpar(){
        edtUsername.setText("");
        edtNome.setText("");
        edtSenha.setText("");
        edtConfirmarSenha.setText("");
    }
}
