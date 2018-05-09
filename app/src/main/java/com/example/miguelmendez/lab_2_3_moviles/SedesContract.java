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


    public static long insertarSede(Context context, SedesTEC sede){
        SedesDbHelper dbHelper  = new SedesDbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(nombre, sede.getNombre());
        value.put(latitud,sede.getLatitud());
        value.put(longitud,sede.getLongitud());
        value.put(descripcion,sede.getLongitud());
        return db.insert(TABLE_NAME,null,value);
    }

    public static ArrayList<SedesTEC> listaSedes(Context context){
        String  query  =  "SELECT    *  FROM  "  +  TABLE_NAME;
        SedesDbHelper DatabaseHelper = new SedesDbHelper(context);
        SQLiteDatabase db = DatabaseHelper.getWritableDatabase();
        Cursor cursor  = db.rawQuery(query, null);
        ArrayList<SedesTEC> arrayList  =  new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                SedesTEC list = new SedesTEC(
                        cursor.getString(cursor.getColumnIndexOrThrow(nombre)),
                        cursor.getDouble(cursor.getColumnIndexOrThrow(latitud)),
                        cursor.getDouble(cursor.getColumnIndexOrThrow(longitud)),
                        cursor.getString(cursor.getColumnIndexOrThrow(descripcion)));
                arrayList.add(list);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return arrayList;
    }


}
