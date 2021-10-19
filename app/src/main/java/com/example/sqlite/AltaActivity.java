package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sqlite.asistentes.Controller;
import com.example.sqlite.modelos.ModeloItem;

public class AltaActivity extends AppCompatActivity {

    EditText etNombre, etExistencia;
    Button btGuardar, btSalir;
    Controller controller;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta);

        etNombre = findViewById(R.id.etNombre);
        etExistencia = findViewById(R.id.etExistencia);
        btGuardar = findViewById(R.id.btGuardar);
        btSalir = findViewById(R.id.btSalir);
        controller = new Controller(AltaActivity.this);

        btGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = etNombre.getText().toString();
                int existencia = Integer.parseInt(etExistencia.getText().toString());

                ModeloItem nuevoProducto = new ModeloItem(nombre, existencia);
                long res = controller.nuevoProducto(nuevoProducto);
                if(res == -1){
                    // hay error
                    Toast.makeText(getApplicationContext(), "ERROR: el registro no se ha guardado", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "SUCCESS", Toast.LENGTH_SHORT).show();
                    limpiaCampos();
                }
            }
        });

        btSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "SALIR", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    void limpiaCampos(){
        etNombre.setText(null);
        etExistencia.setText(null);
        etNombre.requestFocus();
    }
}