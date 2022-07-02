package com.example.easyfinance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TelaPerfil extends AppCompatActivity {

    private Button bttEdit;
    private Button bttHist;
    private Button bttAdd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_perfil);

        bttEdit = findViewById(R.id.buttonEdit);
        bttEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeEdit();
            }
        });

        bttHist = findViewById(R.id.buttonHistorico);
        bttHist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeHist();
            }
        });

        bttAdd = findViewById(R.id.Add);
        bttAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeAdd();
            }
        });



    }

    private void changeEdit() {
        Intent intent = new Intent(this, Tela_EditaPerfil.class);
        startActivity(intent);
    }
    private void changeHist() {
        Intent intent = new Intent(this, Tela_Historico.class);
        startActivity(intent);
    }
    private void changeAdd() {
        Intent intent = new Intent(this, Tela_Adicionar.class);
        startActivity(intent);
    }
}