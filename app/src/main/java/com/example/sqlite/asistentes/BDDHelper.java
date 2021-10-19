package com.example.sqlite.asistentes;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BDDHelper extends SQLiteOpenHelper {

    private static final String NOMBRE_BASE_DE_DATOS = "miDB",
            NOMBRE_TABLA_PRODUCTOS = "productos";
    private static final int VERSION_BASE_DE_DATOS = 1;
    // cambiar version de bdd si se agregan tablas

    public BDDHelper(@Nullable Context context) {
        super(context, NOMBRE_BASE_DE_DATOS, null, VERSION_BASE_DE_DATOS);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(String.format("CREATE TABLE IF NOT EXISTS %s(id integer primary key autoincrement, nombre text, existe int)", NOMBRE_TABLA_PRODUCTOS));
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + NOMBRE_TABLA_PRODUCTOS);
        onCreate(sqLiteDatabase);
    }
}
