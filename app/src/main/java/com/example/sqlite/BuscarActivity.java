package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sqlite.asistentes.Controller;
import com.example.sqlite.modelos.ModeloItem;

public class BuscarActivity extends AppCompatActivity {

    EditText etIDBusca;
    Button btBuscar, btNuevoBuscar;
    TextView tvRecuperado;
    Controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar);

        etIDBusca = findViewById(R.id.etIDBuscar);
        btBuscar = findViewById(R.id.btBuscar);
        btNuevoBuscar = findViewById(R.id.btNuevoBuscar);
        tvRecuperado = findViewById(R.id.tvRecuperado);
        controller = new Controller(BuscarActivity.this);

        btBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModeloItem objBus = new ModeloItem("", -1, Long.parseLong(etIDBusca.getText().toString()));
                ModeloItem resultado = controller.buscaProducto(objBus);
                if(resultado != null){
                    Toast.makeText(getApplicationContext(),"Encontrado " + resultado.getNombre(), Toast.LENGTH_LONG).show();
                    tvRecuperado.setText(resultado.toString());
                }
                else
                    Toast.makeText(getApplicationContext(), "ERROR: id inexistente", Toast.LENGTH_SHORT).show();
            }
        });

        btNuevoBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etIDBusca.setText(null);
                tvRecuperado.setText(null);
                etIDBusca.requestFocus();
            }
        });
    }
}