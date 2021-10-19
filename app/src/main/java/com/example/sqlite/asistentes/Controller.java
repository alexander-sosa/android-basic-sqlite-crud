package com.example.sqlite.asistentes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.sqlite.modelos.ModeloItem;

import java.util.ArrayList;

public class Controller {
    // aqui va a estar toda la logica de la BDD

    private BDDHelper ayudanteBaseDeDatos;
    private String NOMBRE_TABLA = "productos";

    public Controller(Context contexto) {
        ayudanteBaseDeDatos = new BDDHelper(contexto);
    }

    public int eliminaProducto(ModeloItem producto) {

        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getWritableDatabase();
        String[] argumentos = {String.valueOf(producto.getId())};
        return baseDeDatos.delete(NOMBRE_TABLA, "id = ?", argumentos);
    }

    public long nuevoProducto(ModeloItem producto) {
        // writable porque vamos a insertar
        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getWritableDatabase();
        ContentValues valoresParaInsertar = new ContentValues();
        valoresParaInsertar.put("nombre", producto.getNombre());
        valoresParaInsertar.put("existe", producto.getExistencia());
        return baseDeDatos.insert(NOMBRE_TABLA, null, valoresParaInsertar);
    }

    public int cambioProducto(ModeloItem productoEditado) {
        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getWritableDatabase();
        ContentValues valoresParaActualizar = new ContentValues();
        valoresParaActualizar.put("nombre", productoEditado.getNombre());
        valoresParaActualizar.put("existe", productoEditado.getExistencia());
        // where id...
        String campoParaActualizar = "id = ?";

        String[] argumentosParaActualizar = {String.valueOf(productoEditado.getId())};
        return baseDeDatos.update(NOMBRE_TABLA, valoresParaActualizar, campoParaActualizar, argumentosParaActualizar);
    }

    public int bajaProducto(ModeloItem productoEditado) {
        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getWritableDatabase();
        ContentValues valoresParaActualizar = new ContentValues();
        valoresParaActualizar.put("nombre",productoEditado.getNombre());
        valoresParaActualizar.put("existe", productoEditado.getExistencia());


        // where id...
        String campoParaActualizar = "id = ?";

        String[] argumentosParaActualizar = {String.valueOf(productoEditado.getId())};


        //return baseDeDatos.update(NOMBRE_TABLA, valoresParaActualizar, campoParaActualizar, argumentosParaActualizar);
        return baseDeDatos.delete(NOMBRE_TABLA,  campoParaActualizar, argumentosParaActualizar);
    }

    public ArrayList<ModeloItem> obtenerProductos() {

        ArrayList<ModeloItem> listaProductos = new ArrayList<>();

        // readable porque no vamos a modificar, solamente leer
        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getReadableDatabase();
        // SELECT nombre, edad, id
        String[] columnasAConsultar = {"nombre", "existe", "id"};
        Cursor cursor = baseDeDatos.query(
                NOMBRE_TABLA,
                columnasAConsultar,
                null,
                null,
                null,
                null,
                null
        );

        if (cursor == null) {

            // Salimos aquí porque hubo un error, regresar
            // lista vacía

            return listaProductos;

        }
        // Si no hay datos, igualmente regresamos la lista vacía
        if (!cursor.moveToFirst()) return listaProductos;

        Log.i("jta", "por aca estamos, ves el mensaje  ");
        // En caso de que sí haya, iteramos y vamos agregando los
        // datos a la lista de productos
        do {
            // El 0 es el número de la columna, como seleccionamos
            // nombre, edad,id entonces el nombre es 0, edad 1 e id es 2
            String nombreObtenidoDeBD = cursor.getString(0);
            int existeObtenidaDeBD = cursor.getInt(1);
            long idProducto = cursor.getLong(2);
            ModeloItem productoObtenidaDeBD = new ModeloItem(nombreObtenidoDeBD, existeObtenidaDeBD, idProducto);
            listaProductos.add(productoObtenidaDeBD);
        } while (cursor.moveToNext());


        cursor.close();
        return listaProductos;

    }

    public ModeloItem buscaProducto(ModeloItem reg){
        SQLiteDatabase db = ayudanteBaseDeDatos.getReadableDatabase();
        //Cursor c = db.rawQuery("SELECT id, nombre, edad FROM NOMBRE_TABLA", null);
        Cursor c=db.rawQuery("SELECT nombre, existe , id  FROM "+NOMBRE_TABLA+" WHERE id = '"+ reg.getId()+"' ",null);
        ModeloItem res = null;



        if (c != null) {
            try {
                c.moveToFirst();
                String nombre = c.getString(c.getColumnIndex("nombre"));
                int existe = c.getInt(c.getColumnIndex("existe"));
                int id = c.getInt(c.getColumnIndex("id"));
                res = new ModeloItem(nombre, existe, id);
            }catch (Exception e){

            }


        }else{

        }



        //Cerramos el cursor y la conexion con la base de datos
        c.close();
        db.close();
        return res;
    }
}
