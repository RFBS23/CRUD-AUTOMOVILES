package com.fabridev.crudautos;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fabridev.crudautos.utilidades.ConexionSQL;
import com.fabridev.crudautos.utilidades.DateTime;

public class Registrar extends AppCompatActivity {

    EditText txtmarca, txtModelo, txtTpCombustible, txtPrecio, txtColor;
    Button btnregistrar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);
        loadUI();
        btnregistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                preguntar();
            }
        });
    }

    private void loadUI(){
        txtmarca = findViewById(R.id.txtmarca);
        txtModelo = findViewById(R.id.txtModelo);
        txtTpCombustible= findViewById(R.id.txtTpCombustible);
        txtPrecio = findViewById(R.id.txtPrecio);
        txtColor = findViewById(R.id.txtColor);
        btnregistrar = findViewById(R.id.btnregistrar);
    }
    private void resetUI(){
        txtmarca.setText(null);
        txtModelo.setText(null);
        txtTpCombustible.setText(null);
        txtPrecio.setText(null);
        txtColor.setText(null);
    }
    private void registrar(){
        //Instanciamos nuestra clase conexion
        ConexionSQL conexion = new ConexionSQL(this, "crudAutos", null, 1);
        //permisos (lectura - escritura)
        SQLiteDatabase db = conexion.getWritableDatabase();
        //preparar los valores enviados (key > value)
        ContentValues parametros = new ContentValues();
        if(txtmarca.getText().toString().equalsIgnoreCase("") ||
                txtModelo.getText().toString().equalsIgnoreCase("") ||
                txtTpCombustible.getText().toString().equalsIgnoreCase("") ||
                txtPrecio.getText().toString().equalsIgnoreCase("") ||
                txtColor.getText().toString().equalsIgnoreCase("")
        ){
            Toast.makeText(this, "debe Completar el formulario", Toast.LENGTH_SHORT).show();
        } else {
            parametros.put("marca", txtmarca.getText().toString());
            parametros.put("modelo", txtModelo.getText().toString());
            parametros.put("tipoCombustible", txtTpCombustible.getText().toString());
            parametros.put("precio", txtPrecio.getText().toString());
            parametros.put("color", txtColor.getText().toString());
            parametros.put("create_at", DateTime.getDateTimeFormat("yyyy-MM-dd"));
            long idautomovil = db.insert("automoviles","idautomovil", parametros);
            resetUI();
            //mensaje confirmacion
            Toast.makeText(this, "Automovil Registrado Correctamente", Toast.LENGTH_SHORT).show();
        }
    }

    private void preguntar(){
        AlertDialog.Builder pregunta = new AlertDialog.Builder(this);
        pregunta.setTitle("CRUD SENATI - FABRIDEV")
                .setIcon(R.mipmap.notificacion)
                .setMessage("¿Estas Seguro de Agregar El Automovil?")
                .setNegativeButton("cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        registrar();
                    }
                });
        pregunta.show();
    }
}