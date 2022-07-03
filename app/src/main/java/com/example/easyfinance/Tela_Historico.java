package com.example.easyfinance;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

public class Tela_Historico extends AppCompatActivity {

    private Spinner filter;
    private ListView viewTransacao;
    private Usuario u;
    DBHelper helper = new DBHelper(this);
    String[] opcoes ={"Mês atual", "3 meses", "6 meses", "1 ano"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_historico);

        filter = findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Tela_Historico.this, android.R.layout.simple_spinner_dropdown_item,opcoes);
        filter.setAdapter(adapter);

        viewTransacao = findViewById(R.id.listaItens);
        ArrayAdapter<Transacao> adapter_spinner = new ArrayAdapter<Transacao>(this, android.R.layout.simple_list_item_1,helper.listaTransacoes(u));
        viewTransacao.setAdapter(adapter_spinner);



    }


}