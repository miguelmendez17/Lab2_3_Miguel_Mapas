package com.example.miguelmendez.lab_2_3_moviles;

public class SedesTEC {
    public String nombre;
    public String latitud;
    public String longitud;
    public String descripcion;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public SedesTEC(String name, String lt, String longi, String desc){
        this.nombre = name;
        this.latitud = lt;
        this.longitud = longi;
        this.descripcion = desc;
    }

}
