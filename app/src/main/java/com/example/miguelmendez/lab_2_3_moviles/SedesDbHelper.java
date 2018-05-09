package com.example.miguelmendez.lab_2_3_moviles;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

public class SedesDbHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "sedes.db";

    public SedesDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + SedesContract.TABLE_NAME + " ("
                    + SedesContract._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + SedesContract.nombre + " TEXT NOT NULL,"
                    + SedesContract.latitud + " REAL NOT NULL,"
                    + SedesContract.longitud + " REAL NOT NULL,"
                    + SedesContract.descripcion + " TEXT NOT NULL"+")";

        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // No hay operaciones
    }
}
