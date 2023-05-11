package com.fabridev.crudautos;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fabridev.crudautos.utilidades.ConexionSQL;

public class Eliminar extends AppCompatActivity {
    EditText cajabuscar, marca, modelo, combustible, precio, color;
    Button btnBuscar, btnEliminar;
    ConexionSQL conexion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar);
        conexion = new ConexionSQL(this, "crudAutos", null, 1);
        loadUI();
        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buscarAutos();
            }
        });
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eliminarAuto();
            }
        });
    }

    private void loadUI(){
        btnBuscar = findViewById(R.id.btnBuscar);
        btnEliminar = findViewById(R.id.btnEliminar);

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

    private void eliminarAuto(){
        AlertDialog.Builder preguntar = new AlertDialog.Builder(this);
        preguntar.setTitle("CRUD AUTOS")
                .setIcon(R.mipmap.notificacion)
                .setMessage("¿Estas Seguro de Eliminar El auto?")
                .setPositiveButton("si, Eliminar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        SQLiteDatabase db = conexion.getWritableDatabase();
                        String[] campoCriticado = {
                                cajabuscar.getText().toString()
                        };
                        if(marca.getText().toString().equalsIgnoreCase("") ||
                                modelo.getText().toString().equalsIgnoreCase("") ||
                                combustible.getText().toString().equalsIgnoreCase("") ||
                                precio.getText().toString().equalsIgnoreCase("") ||
                                color.getText().toString().equalsIgnoreCase("")
                        ){
                            Toast.makeText(getApplicationContext(), "Debe Ingresar El Nombre del Auto Para poder Los Datos", Toast.LENGTH_SHORT).show();
                        } else {
                            db.delete("automoviles", "marca=?", campoCriticado);
                            db.close();
                            resetUI();
                            Toast.makeText(getApplicationContext(), "El auto Fue Eliminado Correctamente", Toast.LENGTH_SHORT).show();
                        }

                    }
                })
                .setNegativeButton("No, cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        preguntar.show();
    }
}