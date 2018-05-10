package com.example.miguelmendez.lab_2_3_moviles;


import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private static final int LOCATION_REQUEST_CODE = 101;
    private GoogleMap mMap;
    Marker markerPais;
    public  static final  String EXTRA_LATITUD="";
    public  static final  String EXTRA_LONGITUD="";
    SedesDbHelper MyBd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        MyBd = new SedesDbHelper(this);
    }

    public void insertarDatos(){
        MyBd.insertarSede(new SedesTEC("TEC Sede Santa Clara", "10.3652482",
                        "-84.5133537", "Esta sede, ubicada en Santa Clara de San Carlos, en la región tropical húmeda"));

        MyBd.insertarSede(new SedesTEC("TEC Sede Central Cartago", "9.8564963",
                        "-83.9125516", "La Sede Central del Tecnológico de Costa Rica se encuentra en Cartago, " +
                "una ciudad que se ubica 24 kilómetros al sureste de San José,"));

        MyBd.insertarSede(new SedesTEC("Centro Académico Limón", "9.8400319",
                    "-83.6980679", "La provincia de Limón se encuentra en un momento de expansión producto" +
                        " de la inversión pública y privada."));

        MyBd.insertarSede(new SedesTEC("Centro Académico San José", "9.9380554",
                "-84.0775682", "El Centro Académico de San José se ubica en Barrio Amón, entre calles 5 y 7 y entre avenidas 9 y 11."));

        MyBd.insertarSede(new SedesTEC("Centro académico Alajuela", "10.0198805",
                "-84.1994593", "El Centro Académico se ubica en Desamparados de Alajuela, a 21 kilómetros de San José."));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case LOCATION_REQUEST_CODE: {

                if (grantResults.length == 0
                        || grantResults[0] !=
                        PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this,
                            "Unable to show location - permission required",
                            Toast.LENGTH_LONG).show();
                } else {

                    SupportMapFragment mapFragment =
                            (SupportMapFragment) getSupportFragmentManager()
                                    .findFragmentById(R.id.map);
                    mapFragment.getMapAsync(this);
                }
            }
        }

    }

    @Override
    public boolean onMarkerClick(Marker marker) {

        if (marker.equals(markerPais)) {
            Intent intent = new Intent(this, MarkerDetailActivity.class);
            intent.putExtra(EXTRA_LATITUD, marker.getPosition().latitude);
            intent.putExtra(EXTRA_LONGITUD, marker.getPosition().longitude);

            startActivity(intent);
        }

        return false;
    }

    protected void requestPermission(String permissionType,
                                     int requestCode) {

        ActivityCompat.requestPermissions(this,
                new String[]{permissionType}, requestCode
        );

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        agregarMarkers(mMap);
        if (mMap != null) {
            int permission = ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION);

            if (permission == PackageManager.PERMISSION_GRANTED) {
                mMap.setMyLocationEnabled(true);
            } else {
                requestPermission(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        LOCATION_REQUEST_CODE);
            }
        }

        // Posicion Costa Rica
       LatLng costarica = new LatLng(9.6,-83.9660188);

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(costarica,7));
        // Eventos
        googleMap.setOnMarkerClickListener(this);
        //evento para agregar los markers de las sedes
    }

    public void agregarMarkers(GoogleMap googleMap){
        ArrayList<SedesTEC> array = MyBd.listaSedes();
        for(int a = 0; a<array.size();a++){
            LatLng latLng = new LatLng(Double.parseDouble(array.get(a).getLatitud()), Double.parseDouble(array.get(a).getLongitud()));
            googleMap.addMarker(new MarkerOptions().position(latLng).title(array.get(a).getNombre()));
        }
    }

    public void clickInfo(Marker marker){
        ArrayList<SedesTEC> arraySedes = MyBd.listaSedes();
        DialogCustom.newInstance(marker.getTitle(),marker.getSnippet());
    }

}
