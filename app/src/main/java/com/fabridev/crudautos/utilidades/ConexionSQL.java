package com.fabridev.crudautos.utilidades;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ConexionSQL extends SQLiteOpenHelper {

    //private static final int DATABASE_VERSION = 1; //incrementa la version al hacer cambios
    private static final String DATABASE_NOMBRE = "crud.db"; //se guarda con el nombre crud.db
    //private static final String TABLE_PERSONAS = "personas"; //creamos el nombre de la tabla

    final String TABLA_AUTOMOVIL = "" +
            "CREATE TABLE 'automoviles'("+
            "'idautomovil'      INTEGER     NOT NULL," +
            "'marca'            TEXT        NOT NULL," +
            "'modelo'           TEXT        NOT NULL," +
            "'tipoCombustible'  TEXT        NOT NULL," +
            "'precio'           REAL        NOT NULL," +
            "'color'            TEXT        NOT NULL," +
            "'create_at'        TEXT        NOT NULL," +
            "'update_at'        TEXT," +
            "PRIMARY KEY ('idautomovil' AUTOINCREMENT)" +
            ");";

    public ConexionSQL(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TABLA_AUTOMOVIL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //sqLiteDatabase.execSQL("DROP TABLE" + TABLE_PERSONAS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS automoviles");
        onCreate(sqLiteDatabase);
    }
}
