package com.example.easyfinance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Tela_Adicionar extends AppCompatActivity {
    private EditText editNomeT;
    private EditText editValor;
    private EditText editDesc;
    private EditText editdia;
    private EditText editmes;
    private EditText editano;
    private Button bttAd;
    private Usuario u;
    DBHelper helper = new DBHelper(this);
    private Transacao t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_adicionar);

        Intent it=getIntent();
        u = (Usuario) it.getSerializableExtra("chave_user");

        editNomeT = findViewById(R.id.textInputEditTextNomeT);
        editValor = findViewById(R.id.textInputEditTextValor);
        editDesc = findViewById(R.id.textInputEditTextDesc);
        editdia = findViewById(R.id.editDia);
        editmes = findViewById(R.id.editTextMes);
        editano = findViewById(R.id.editTextAno);

        bttAd = findViewById(R.id.buttonTelaAdiciona);

        bttAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ano = editano.getText().toString();
                String mes = editmes.getText().toString();
                String dia = editdia.getText().toString();

                String data = dia+"/"+mes+"/"+ano;
                t = new Transacao();
                t.setUsuario(u.getUsername());
                int valor = Integer.parseInt(editValor.getText().toString());
                t.setNomeTransacao(editNomeT.getText().toString());
                t.setValor(valor);
                t.setDescricao(editDesc.getText().toString());
                t.setData(data);
                helper.insereTransacao(t);

                Toast toast = Toast.makeText(Tela_Adicionar.this,
                        "Transação adicionada com sucesso!",
                        Toast.LENGTH_LONG);
                toast.show();

                Intent intent = new Intent(Tela_Adicionar.this, TelaPerfil.class);
                intent.putExtra("chave_usuario", u.getUsername());
                startActivity(intent);
            }
        });
    }
}