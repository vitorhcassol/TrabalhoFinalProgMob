package com.example.easyfinance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Tela_EditaPerfil extends AppCompatActivity {
    private EditText edtNome, edtSenha, edtConfirmarSenha;
    private TextView txtViewUsuario;
    private Usuario user;
    private Usuario altUser;
    private Button bttSalvar, bttExcluir;
    DBHelper helper = new DBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_edita_perfil);

        Intent it=getIntent();
        user = (Usuario) it.getSerializableExtra("chave_user");

        txtViewUsuario = findViewById(R.id.textUserSalvar);
        edtNome = findViewById(R.id.textNomeSalvar);
        edtSenha = findViewById(R.id.textSenhaSalvar);
        edtConfirmarSenha = findViewById(R.id.textConfirmaSalvar);

        txtViewUsuario.setText(user.getUsername());
        edtNome.setText(user.getNome());
        edtSenha.setText(user.getSenha());

        //Botões e suas seguintes funções
        bttExcluir = findViewById(R.id.buttonGoToExclui);
        bttExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3 = new Intent(Tela_EditaPerfil.this, Tela_Exclusao.class);
                intent3.putExtra("chave_usuario", user);
                startActivity(intent3);
            }
        });

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
                    altUser = new Usuario();
                    altUser.setUsername(user.getUsername());
                    altUser.setNome(edtNome.getText().toString());
                    altUser.setSenha(edtSenha.getText().toString());

                    helper.atualizarUsuario(altUser);

                    Toast toast = Toast.makeText(Tela_EditaPerfil.this,
                            "Usuário atualizado com sucesso!",
                            Toast.LENGTH_LONG);
                    toast.show();

                    String name = altUser.getUsername();
                    Intent intent2 = new Intent(Tela_EditaPerfil.this, TelaPerfil.class);
                    intent2.putExtra("chave_usuario", name);
                    startActivity(intent2);
                }
            }
        });



    }
}