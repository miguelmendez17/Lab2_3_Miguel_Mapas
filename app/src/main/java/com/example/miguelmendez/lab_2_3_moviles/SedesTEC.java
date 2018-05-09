package com.example.miguelmendez.lab_2_3_moviles;

public class SedesTEC {
    private String Nombre;
    private double latitud;
    private double longitud;
    private String descripcion;

    public SedesTEC(String name, double lt, double longi, String desc){
        this.Nombre = name;
        this.latitud = lt;
        this.longitud = longi;
        this.descripcion = desc;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }



}
