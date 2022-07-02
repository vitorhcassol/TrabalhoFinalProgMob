package com.example.easyfinance;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class Tela_Historico extends AppCompatActivity {

    private Spinner filter;
    String[] opcoes ={"Mês atual", "3 meses", "6 meses", "1 ano"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_historico);

        filter = findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Tela_Historico.this, android.R.layout.simple_spinner_dropdown_item,opcoes);
        filter.setAdapter(adapter);



    }


}