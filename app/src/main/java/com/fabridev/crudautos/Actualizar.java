package com.fabridev.crudautos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fabridev.crudautos.utilidades.ConexionSQL;
import com.fabridev.crudautos.utilidades.DateTime;

public class Actualizar extends AppCompatActivity {
    EditText cajabuscar, marca, modelo, combustible, precio, color;
    Button btnBuscar, btnReiniciar, btnActualizar;

    ConexionSQL conexion; //objeto conexion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar);

        loadUI();
        conexion = new ConexionSQL(this, "crudAutos", null, 1);
        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buscarAutos();
            }
        });
        btnReiniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetUI();
                cajabuscar.requestFocus();
            }
        });
        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actualizarAuto();
            }
        });
    }

    private void loadUI(){
        btnBuscar = findViewById(R.id.btnBuscar);
        btnReiniciar = findViewById(R.id.btnReiniciar);
        btnActualizar = findViewById(R.id.btnActualizar);

        cajabuscar = findViewById(R.id.cajabuscar);
        marca = findViewById(R.id.marca);
        modelo = findViewById(R.id.modelo);
        combustible = findViewById(R.id.combustible);
        precio = findViewById(R.id.precio);
        color = findViewById(R.id.color);
    }
    private void resetUI(){
        cajabuscar.setText(null);
        marca.setText(null);
        modelo.setText(null);
        combustible.setText(null);
        precio.setText(null);
        color.setText(null);
    }
    private void buscarAutos(){
        //permisos
        SQLiteDatabase db = conexion.getReadableDatabase();
        //campos utilizando como filtro
        String[] campoFiltro = { cajabuscar.getText().toString() };
        //campos que vamos a obtener (consulta)
        String[] campos = { "marca",
                "modelo",
                "tipoCombustible",
                "precio",
                "color"
        };
        //manejador de excepciones (en ocaciones no encontramos datos)
        try{
            //ejecutar almacenar
            Cursor cursor = db.query("automoviles", campos, "marca=?", campoFiltro, null, null, null);
            cursor.moveToFirst();
            //enviamos el resultado a la cjas de texto
            marca.setText(cursor.getString(0));
            modelo.setText(cursor.getString(1));
            combustible.setText(cursor.getString(2));
            precio.setText(cursor.getString(3));
            color.setText(cursor.getString(4));
            cursor.close();
        } catch (Exception e){
            resetUI();
            Toast.makeText(this, "La marca que buscas no EXISTE", Toast.LENGTH_SHORT).show();
        }
    }

    private void actualizarAuto(){
        SQLiteDatabase db = conexion.getWritableDatabase();
        String[] campoCriterio = {
                cajabuscar.getText().toString()
        };
        if(marca.getText().toString().equalsIgnoreCase("") ||
                modelo.getText().toString().equalsIgnoreCase("") ||
                combustible.getText().toString().equalsIgnoreCase("") ||
                precio.getText().toString().equalsIgnoreCase("") ||
                color.getText().toString().equalsIgnoreCase("")
        ){
            Toast.makeText(this, "Debe Buscar un Automovil para Actualizar Los Datos", Toast.LENGTH_SHORT).show();
        } else  {
            ContentValues parametros = new ContentValues();
            parametros.put("marca", marca.getText().toString());
            parametros.put("modelo", modelo.getText().toString());
            parametros.put("tipoCombustible", combustible.getText().toString());
            parametros.put("precio", precio.getText().toString());
            parametros.put("color", color.getText().toString());
            parametros.put("update_at", DateTime.getDateTimeFormat("yyyy-MM-dd hh:mm"));
            db.update("automoviles", parametros, "marca=?", campoCriterio);
            db.close();
            resetUI();
            Toast.makeText(this, "Los Datos Fueron Actualizados Correctamente", Toast.LENGTH_SHORT).show();
        }

    }
}