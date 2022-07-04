package com.example.easyfinance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Tela_Exclusao extends AppCompatActivity {
    DBHelper helper = new DBHelper(this);
    Usuario user;
    Button bttCancelar, bttExcluir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_exclusao);

        Intent it = getIntent();
        user = (Usuario) it.getSerializableExtra("chave_usuario");

        bttCancelar = findViewById(R.id.buttonCancelar);
        bttCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Tela_Exclusao.this, Tela_EditaPerfil.class);
                intent.putExtra("chave_user", user);
                startActivity(intent);
            }
        });

        bttExcluir = findViewById(R.id.buttonExcluir);
        bttExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                helper.excluiUsuario(user);

                Toast toast = Toast.makeText(Tela_Exclusao.this,
                        "Usuário deletado com sucesso!",
                        Toast.LENGTH_SHORT);
                toast.show();

                Intent intent = new Intent(Tela_Exclusao.this, TelaLogin.class);
                startActivity(intent);
            }
        });

    }
}