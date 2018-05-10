package com.example.miguelmendez.lab_2_3_moviles;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

public class SedesDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NOMBRE = "sedesBD.db";
    public static final String TABLE_NOMBRE = "sedes";
    public static final String nombre = "nombre";
    public static final String latitud = "latitud";
    public static final String longitud = "longitud";
    public static final String descripcion = "descripcion";

    public SedesDbHelper(Context context) {
        super(context, DATABASE_NOMBRE, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + TABLE_NOMBRE + " ("
                    + nombre + " TEXT PRIMARY KEY NOT NULL,"
                    + latitud + " TEXT NOT NULL,"
                    + longitud + " TEXT NOT NULL,"
                    + descripcion + " TEXT NOT NULL)";
        sqLiteDatabase.execSQL(query);
    }

    //hay que especificar la vieja versión y la nueva versión
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("drop table if exists " + TABLE_NOMBRE);
        onCreate(sqLiteDatabase);
    }

    public void insertarSede(SedesTEC sede){
       // SedesDbHelper dbHelper  = new SedesDbHelper(context);
        //SQLiteDatabase db = dbHelper.getWritableDatabase();
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(nombre, sede.getNombre());
        value.put(latitud,sede.getLatitud());
        value.put(longitud,sede.getLongitud());
        value.put(descripcion,sede.getDescripcion());
        db.insert(TABLE_NOMBRE,null,value);
        db.close();
      //  return true;
    }


    public ArrayList<SedesTEC> listaSedes(){
        String selectQuery = "SELECT  * FROM " + TABLE_NOMBRE;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        ArrayList<SedesTEC> arrayListData = new ArrayList<>();
        if (cursor.moveToFirst()){
            do {
                SedesTEC list = new SedesTEC(cursor.getString(0),
                                            cursor.getString(1),
                                            cursor.getString(2),
                                            cursor.getString(3));
                arrayListData.add(list);
            } while (cursor.moveToNext());
        }
        return arrayListData;
    }


}
