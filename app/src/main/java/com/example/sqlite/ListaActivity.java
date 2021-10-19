package com.example.sqlite;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sqlite.asistentes.Controller;
import com.example.sqlite.modelos.ModeloItem;

import java.util.ArrayList;
import java.util.List;

public class ListaActivity extends AppCompatActivity {

    List<ModeloItem> lista;
    Adaptador adaptador;
    RecyclerView rvProductos;
    Controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        lista = new ArrayList<>();
        adaptador = new Adaptador(lista);
        rvProductos = findViewById(R.id.rvProductos);
        controller = new Controller(ListaActivity.this);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rvProductos.setLayoutManager(mLayoutManager);
        rvProductos.setItemAnimator(new DefaultItemAnimator());
        rvProductos.setAdapter(adaptador);

        refrescarListaDeProductos();
        rvProductos.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                return false;
            }

            @Override
            public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
        rvProductos.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), rvProductos, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Toast.makeText(getApplicationContext(),"un toque", Toast.LENGTH_SHORT).show();
                ModeloItem prodElegido = lista.get(position);
                baja(prodElegido);
            }

            @Override
            public void onLongClick(View view, int position) {
                Toast.makeText(getApplicationContext(),"Toque largo", Toast.LENGTH_SHORT).show();
                ModeloItem prodElegido = lista.get(position);
                cambio(prodElegido);
            }
        }));
    }

    public void refrescarListaDeProductos() {

        if (adaptador == null) return;
        lista = controller.obtenerProductos();
        adaptador.setListaDeProductos((ArrayList<ModeloItem>) lista);
        adaptador.notifyDataSetChanged();
    }

    void baja(final ModeloItem prodele){
        AlertDialog.Builder alerta = new AlertDialog.Builder(ListaActivity.this);
        alerta.setTitle("Esta seguro de eliminar");
        alerta.setCancelable(false);
        alerta.setMessage("nombre: "+prodele.getNombre()+"\nexiste: "+prodele.getExistencia()+"\nId: "+prodele.getId());
        alerta.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                controller.bajaProducto(prodele);
                refrescarListaDeProductos();;
            }
        });
        alerta.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        //AlertDialog dialog  = builder.create();
        //dialog.show();
        alerta.create().show();

    }

    void cambio(final ModeloItem prodele){
        LayoutInflater inflador = LayoutInflater.from(this);
        View subView = inflador.inflate(R.layout.layout_cambio, null);

        final EditText etnuevonombre = subView.findViewById(R.id.etNuevoNombre);
        final EditText etnuevexiste = subView.findViewById(R.id.etNuevoExistencia);


        AlertDialog.Builder alerta = new AlertDialog.Builder(ListaActivity.this);
        alerta.setCancelable(false);
        alerta.setTitle("Esta seguro de actualizar los siguientes campos");
        alerta.setMessage(prodele.toString());
        alerta.setView(subView);
        alerta.setPositiveButton("Actualizar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ModeloItem newObj = new ModeloItem(etnuevonombre.getText().toString(),Integer.parseInt(etnuevexiste.getText().toString()), prodele.getId());
                int filasAfectadas = controller.cambioProducto(newObj);
                if(filasAfectadas > 0){
                    refrescarListaDeProductos();;
                    Toast.makeText(getApplicationContext(),"Exito en el cambio", Toast.LENGTH_SHORT).show();


                }else{
                    Toast.makeText(getApplicationContext(),"Error no se realizo el cambio", Toast.LENGTH_SHORT).show();
                }
            }
        });
        alerta.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        alerta.create().show();
    }
}