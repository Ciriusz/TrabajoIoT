package com.example.trabajo.model;

public class Sensor {
    private String nombre;
    private String descripcion;
    private float ideal;

    // Constructor
    public Sensor(String nombre, String descripcion, float ideal) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.ideal = ideal;
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public float getIdeal() {
        return ideal;
    }

    // mostrar
    @Override
    public String toString() {
        return "Nombre del sensor: " + nombre + "\nDescripción: " + descripcion + "\nIdeal: " + ideal;
    }
}
