package com.fabridev.crudautos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MenuOpciones extends AppCompatActivity {
    TextView txtRegistrar, txtBuscar;
    ImageView imgRegistrar, imgBuscar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_opciones);
        txtRegistrar = findViewById(R.id.txtRegistrar);
        imgRegistrar = findViewById(R.id.imgRegistrar);
        txtBuscar = findViewById(R.id.txtBuscar);
        imgBuscar = findViewById(R.id.imgBuscar);

        txtRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrarAutos();
            }
        });
        imgRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrarAutos();
            }
        });
        txtBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buscarAutos();
            }
        });
        imgBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buscarAutos();
            }
        });
    }
    private void registrarAutos(){
        Intent intent = new Intent(this, Registrar.class);
        startActivity(intent);
    }

    private void buscarAutos(){
        Intent intent = new Intent(this, Buscar.class);
        startActivity(intent);
    }
}