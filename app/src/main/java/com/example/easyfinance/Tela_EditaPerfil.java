package com.example.easyfinance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Tela_EditaPerfil extends AppCompatActivity {
    private EditText edtUsuario, edtNome, edtSenha, edtConfirmarSenha;
    private Usuario user;
    private Button bttSalvar;
    DBHelper helper = new DBHelper(this);

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

        bttSalvar = findViewById(R.id.buttonTelaSalvar);
        bttSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String senha = edtSenha.getText().toString();
                String confSenha = edtConfirmarSenha.getText().toString();

                if(!senha.equals(confSenha)){
                    Toast toast = Toast.makeText(Tela_EditaPerfil.this,
                            "Senha diferente da confirmação de senha!",
                            Toast.LENGTH_SHORT);
                    toast.show();

                }else{
                    user.setNome(edtNome.getText().toString());
                    user.setSenha(edtSenha.getText().toString());

                    helper.atualizarUsuario(user);

                    String name = user.getUsername();
                    Intent intent2 = new Intent(Tela_EditaPerfil.this, TelaPerfil.class);
                    intent2.putExtra("chave_usuario", name);
                    startActivity(intent2);

                }



            }
        });



    }
}