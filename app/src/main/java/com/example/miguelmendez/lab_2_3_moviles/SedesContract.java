package com.example.miguelmendez.lab_2_3_moviles;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import java.util.ArrayList;

public class SedesContract implements BaseColumns {
    public static String TABLE_NAME ="sedes.db";
    public static String nombre = "nombre";
    public static String latitud = "latitud";
    public static String longitud = "longitud";
    public static String descripcion = "descripcion";
}
