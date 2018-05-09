package com.example.miguelmendez.lab_2_3_moviles;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SedesDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "sedes.db";

    public SedesDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + SedesContract.SedesEntry.TABLE_NAME + " ("
                + SedesContract.SedesEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + SedesContract.SedesEntry.nombre + " TEXT NOT NULL,"
                + SedesContract.SedesEntry.latitud + " REAL NOT NULL,"
                + SedesContract.SedesEntry.longitud + " REAL NOT NULL,"
                + SedesContract.SedesEntry.descripcion + " TEXT NOT NULL)");

        SedesTEC sancarlos = new SedesTEC("TEC Sede Santa Clara", 10.3594215,
                -84.5133537, "Esta sede, ubicada en Santa Clara de San Carlos, en la región tropical húmeda, se encuentra a 105 kilómetros de San José y a 170 metros sobre el nivel " +
                "del mar, con una temperatura media anual de 26ºC.");

        SedesTEC cartago = new SedesTEC("TEC Sede Central Cartago", 9.8564963,
                -83.9125516, "La Sede Central del Tecnológico de Costa Rica se encuentra en Cartago, una ciudad que se ubica 24 kilómetros al sureste de San José," +
                " a una altitud de 1,435 metros sobre el nivel del mar y con un clima tropical húmedo, aunque suele ser más templado debido a su ubicación geográfica y altura, " +
                "con lluvias moderadas y temperaturas frescas que varían entre 15 y 26 grados centígrados la mayor parte del año.");

        SedesTEC limon = new SedesTEC("Centro Académico Limón", 9.8400319,
                -83.6980679, "La provincia de Limón se encuentra en un momento de expansión producto de la inversión pública y privada. La región Huetar Caribe contará" +
                " con obras de infraestructura que apuntan hacia un desarrollo social, entre ellas: la Megaterminal APM, la nueva refinadora de Recope, la Planta Hidroeléctrica del Reventazón y la ampliación de la ruta 32.");

        SedesTEC sanjose = new SedesTEC("Centro Académico San José", 9.9380554,
                -84.0775682, "El Centro Académico de San José se ubica en Barrio Amón, entre calles 5 y 7 y entre avenidas 9 y 11.  Sus aulas, oficinas administrativas, biblioteca y áreas recreativas se distribuyen "+
        "entre edificios nuevos y casas antiguas con el valor histórico propio del barrio.");

        SedesTEC alajuela = new SedesTEC("Centro académico Alajuela", 10.0198805,
                -84.1994593, "El Centro Académico se ubica en Desamparados de Alajuela, a 21 kilómetros de San José. Se trata de una localidad en la región intertropical, " +
                "a 960 metros sobre el nivel del mar y con una temperatura media anual de 22,3ºC. " +
                "La precipitación pluvial media es de 2.069 mm. anuales, con una estación seca de enero a mediados de marzo y una estación lluviosa de mediados de marzo a diciembre.");

        ContentValues values = new ContentValues();
        values.put(SedesContract.SedesEntry.nombre, sancarlos.getNombre());
        values.put(SedesContract.SedesEntry.latitud, sancarlos.getLatitud());
        values.put(SedesContract.SedesEntry.longitud, sancarlos.getLongitud());
        values.put(SedesContract.SedesEntry.descripcion, sancarlos.getDescripcion());

        ContentValues values1 = new ContentValues();
        values.put(SedesContract.SedesEntry.nombre, cartago.getNombre());
        values.put(SedesContract.SedesEntry.latitud, cartago.getLatitud());
        values.put(SedesContract.SedesEntry.longitud, cartago.getLongitud());
        values.put(SedesContract.SedesEntry.descripcion, cartago.getDescripcion());

        ContentValues values2 = new ContentValues();
        values.put(SedesContract.SedesEntry.nombre, limon.getNombre());
        values.put(SedesContract.SedesEntry.latitud, limon.getLatitud());
        values.put(SedesContract.SedesEntry.longitud, limon.getLongitud());
        values.put(SedesContract.SedesEntry.descripcion, limon.getDescripcion());

        ContentValues values3= new ContentValues();
        values.put(SedesContract.SedesEntry.nombre, sanjose.getNombre());
        values.put(SedesContract.SedesEntry.latitud, sanjose.getLatitud());
        values.put(SedesContract.SedesEntry.longitud, sanjose.getLongitud());
        values.put(SedesContract.SedesEntry.descripcion, sanjose.getDescripcion());
        ContentValues values4 = new ContentValues();
        values.put(SedesContract.SedesEntry.nombre, alajuela.getNombre());
        values.put(SedesContract.SedesEntry.latitud, alajuela.getLatitud());
        values.put(SedesContract.SedesEntry.longitud, alajuela.getLongitud());
        values.put(SedesContract.SedesEntry.descripcion, alajuela.getDescripcion());

        sqLiteDatabase.insert(SedesContract.SedesEntry.TABLE_NAME, null, values);
        sqLiteDatabase.insert(SedesContract.SedesEntry.TABLE_NAME, null, values1);
        sqLiteDatabase.insert(SedesContract.SedesEntry.TABLE_NAME, null, values2);
        sqLiteDatabase.insert(SedesContract.SedesEntry.TABLE_NAME, null, values3);
        sqLiteDatabase.insert(SedesContract.SedesEntry.TABLE_NAME, null, values4);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // No hay operaciones
    }
}
