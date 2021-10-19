package com.example.sqlite.modelos;

public class ModeloItem {
    private String nombre;
    private int existencia;
    private long id;

    public ModeloItem(String nombre, int existencia) {
        this.nombre = nombre;
        this.existencia = existencia;
    }

    public ModeloItem(String nombre, int existencia, long id) {
        this.nombre = nombre;
        this.existencia = existencia;
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getExistencia() {
        return existencia;
    }

    public void setExistencia(int existencia) {
        this.existencia = existencia;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ModeloItem{" +
                "nombre='" + nombre + '\'' +
                ", existencia=" + existencia +
                ", id=" + id +
                '}';
    }
}
