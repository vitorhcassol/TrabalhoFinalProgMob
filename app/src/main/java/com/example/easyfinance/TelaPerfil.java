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
import android.widget.TextView;

public class TelaPerfil extends AppCompatActivity {
    DBHelper helper = new DBHelper(this);

    private Button bttEdit;
    private Button bttHist;
    private Button bttAdd;
    private Button bttCamera;
    private TextView txtViewNomeUsuario;
    private Usuario user;
    private de.hdodenhof.circleimageview.CircleImageView image;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_perfil);

        Bundle args = getIntent().getExtras();
        String username = args.getString("chave_usuario");

        user = helper.buscaUsuario(username);

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
                Intent intent = new Intent(TelaPerfil.this,Tela_EditaPerfil.class);
                intent.putExtra("chave_user",user);
                startActivity(intent);
            }
        });

        bttHist = findViewById(R.id.buttonHistorico);
        bttHist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TelaPerfil.this,Tela_Historico.class);
                intent.putExtra("chave_user",user);
                startActivity(intent);
            }
        });

        bttAdd = findViewById(R.id.Add);
        bttAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TelaPerfil.this,Tela_Adicionar.class);
                intent.putExtra("chave_user",user);
                startActivity(intent);
            }
        });

        txtViewNomeUsuario = findViewById(R.id.NomeUsuario);
        txtViewNomeUsuario.setText(username + ".");
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap photo = (Bitmap) data.getExtras().get("data");
        image.setImageBitmap(photo);
    }
}