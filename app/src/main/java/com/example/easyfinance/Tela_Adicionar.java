package com.example.easyfinance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Tela_Adicionar extends AppCompatActivity {
    private EditText editNomeT;
    private EditText editValor;
    private EditText editDesc;
    private EditText editData;
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
        editData = findViewById(R.id.textInputEditTextData);
        bttAd = findViewById(R.id.buttonTelaAdiciona);

        t = new Transacao();
        t.setUsuario(u.getUsername());

        bttAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int valor = Integer.parseInt(editValor.toString());
                t.setNomeTransacao(editNomeT.getText().toString());
                t.setValor(valor);
                t.setDescricao(editDesc.getText().toString());
                t.setData(editData.getText().toString());
                helper.insereTransacao(t);

                Intent intent = new Intent(Tela_Adicionar.this,TelaPerfil.class);
                intent.putExtra("chave_user",u);
                startActivity(intent);

            }
        });





    }
}