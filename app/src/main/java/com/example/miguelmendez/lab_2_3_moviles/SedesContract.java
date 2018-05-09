package com.example.miguelmendez.lab_2_3_moviles;

import android.provider.BaseColumns;

public class SedesContract {
    public static abstract class SedesEntry implements BaseColumns {
        public static final String TABLE_NAME ="sedes";
        public static final String nombre = "nombre";
        public static final String latitud = "latitud";
        public static final String longitud = "longitud";
        public static final String descripcion = "descripcion";
    }
}
