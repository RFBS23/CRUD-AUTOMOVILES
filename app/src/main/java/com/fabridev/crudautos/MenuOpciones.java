package com.fabridev.crudautos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MenuOpciones extends AppCompatActivity {
    TextView txtRegistrar, txtBuscar, txtActualizar, txtEliminar, txtinfo;
    ImageView imgRegistrar, imgBuscar, imgActualizar, imgEliminar, imginformacion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_opciones);
        txtRegistrar = findViewById(R.id.txtRegistrar);
        imgRegistrar = findViewById(R.id.imgRegistrar);
        txtBuscar = findViewById(R.id.txtBuscar);
        imgBuscar = findViewById(R.id.imgBuscar);
        txtActualizar = findViewById(R.id.txtActualizar);
        imgActualizar = findViewById(R.id.imgActualizar);
        txtEliminar = findViewById(R.id.txtEliminar);
        imgEliminar = findViewById(R.id.imgEliminar);
        txtinfo = findViewById(R.id.txtinfo);
        imginformacion = findViewById(R.id.imginformacion);

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
        txtActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actualizarAutos();
            }
        });
        imgActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actualizarAutos();
            }
        });
        txtEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eliminarAutos();
            }
        });
        imgEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eliminarAutos();
            }
        });
        txtinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                informacion();
            }
        });
        imginformacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                informacion();
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
    private void  actualizarAutos(){
        Intent intent = new Intent(this, Actualizar.class);
        startActivity(intent);
    }
    private void eliminarAutos(){
        Intent intent = new Intent(this, Eliminar.class);
        startActivity(intent);
    }
    private void informacion(){
        Intent intent = new Intent(this, informacion.class);
        startActivity(intent);
    }
}