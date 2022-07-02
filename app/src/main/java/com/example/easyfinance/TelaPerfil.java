package com.example.easyfinance;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;

public class TelaPerfil extends AppCompatActivity {

    private Button bttEdit;
    private Button bttHist;
    private Button bttAdd;
    private Button bttCamera;
    private de.hdodenhof.circleimageview.CircleImageView image;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_perfil);


        if (ContextCompat.checkSelfPermission(TelaPerfil.this,
                Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(TelaPerfil.this,
                    new String[]{
                            Manifest.permission.CAMERA
                    },
                    100);
        }

        image = findViewById(R.id.imageUser);
        bttCamera = findViewById(R.id.ButtonChangeImage);
        bttCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent open_camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(open_camera,100);
            }
        });

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

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap photo = (Bitmap) data.getExtras().get("data");
        image.setImageBitmap(photo);
    }
}