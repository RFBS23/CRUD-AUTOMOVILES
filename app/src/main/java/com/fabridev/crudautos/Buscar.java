package com.fabridev.crudautos;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fabridev.crudautos.utilidades.ConexionSQL;

public class Buscar extends AppCompatActivity {

    EditText cajabuscar, marca, modelo, combustible, precio, color;
    Button btnBuscar, btnReiniciar;
    ConexionSQL conexion; //objeto conexion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar);
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
    }

    private void loadUI(){
        btnBuscar = findViewById(R.id.btnBuscar);
        btnReiniciar = findViewById(R.id.btnReiniciar);

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
            Toast.makeText(this, "La marca del auto no EXISTE", Toast.LENGTH_SHORT).show();
        }
    }
}