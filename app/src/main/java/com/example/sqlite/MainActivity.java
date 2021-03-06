package com.example.sqlite;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // aqui se controla los items del menu
        switch (item.getItemId()){
            case R.id.mnuAlta:
                Intent invocarAlta = new Intent(this, AltaActivity.class);
                startActivity(invocarAlta);
                break;
            case R.id.mnuLista:
                Intent invocarLista = new Intent(this, ListaActivity.class);
                startActivity(invocarLista);
                break;
            case R.id.mnuBuscar:
                Intent buscar = new Intent(this, BuscarActivity.class);
                startActivity(buscar);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}