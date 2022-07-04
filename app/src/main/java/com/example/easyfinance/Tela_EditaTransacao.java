package com.example.easyfinance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Tela_EditaTransacao extends AppCompatActivity {
    DBHelper helper = new DBHelper(this);
    private Transacao transacao, altTransacao;
    private Usuario user;
    private EditText edtNome, edtValor, edtDescricao, edtDataTransacao;
    private Button bttSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_edita_transacao);

        Intent intent = getIntent();
        transacao = (Transacao) intent.getSerializableExtra("chave_transacao");
        user = (Usuario) intent.getSerializableExtra("chave_user");

        edtNome = findViewById(R.id.textInputEditTextEdNomeT);
        edtValor = findViewById(R.id.textInputEditTextEdValor);
        edtDescricao = findViewById(R.id.textInputEditTextEdDesc);
        edtDataTransacao = findViewById(R.id.textInputEditTextEdData);
        bttSalvar = findViewById(R.id.buttonAdSalva);

        int teste = transacao.getValor();
        String result = String. valueOf(teste);

        //settando os valores para edição
        edtNome.setText(transacao.getNomeTransacao());
        edtValor.setText(result);
        edtDescricao.setText(transacao.getDescricao());
        edtDataTransacao.setText(transacao.getData());

        /*
        //Botões e suas respectivas funções
        bttSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                altTransacao = new Transacao();
                altTransacao.setNomeTransacao(edtNome.getText().toString());
                int result = Integer. valueOf(edtValor.getText().toString());
                altTransacao.setValor(result);
                altTransacao.setDescricao(edtDescricao.getText().toString());
                altTransacao.setData(edtDataTransacao.getText().toString());

                helper.atualizarTransacao(altTransacao);

                Toast toast = Toast.makeText(Tela_EditaTransacao.this,
                        altTransacao.getValor(),
                        Toast.LENGTH_LONG);
                toast.show();


                Intent intent2 = new Intent(Tela_EditaTransacao.this, TelaPerfil.class);
                intent2.putExtra("chave_usuario", user.getUsername());
                startActivity(intent2);
            }
        });
         */
    }
}
