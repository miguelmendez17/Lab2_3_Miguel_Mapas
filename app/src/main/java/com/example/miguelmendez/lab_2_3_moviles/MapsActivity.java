package com.example.miguelmendez.lab_2_3_moviles;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
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

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private static final int LOCATION_REQUEST_CODE = 101;
    private GoogleMap mMap;
    Marker markerSC, markerCar, markerLi, markerAmon, markerAla,markerPais;
    public  static final  String EXTRA_LATITUD="";
    public  static final  String EXTRA_LONGITUD="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        SedesDbHelper db = new SedesDbHelper(getApplicationContext());

        String q = "SELECT * FROM sedes";
        Cursor mCursor = db.rawQuery(q, null);

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


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
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
        agregarMarkers();
    }


    public void agregarMarkers(){
        LatLng santaclara = new LatLng(10.3652482,-84.5057934);
        LatLng cartago = new LatLng(9.8564963,-83.9125516);
        LatLng limon = new LatLng(10.1026622,-83.6893576);
        LatLng amon = new LatLng(9.9378868,-84.0755203);
        LatLng alajuela = new LatLng(10.0198026,-84.1971997);
        markerSC = mMap.addMarker(new MarkerOptions()
                .position(santaclara)
                .title("TEC Sede Regional San Carlos"));
        markerCar = mMap.addMarker(new MarkerOptions()
                .position(cartago)
                .title("TEC Sede Central Cartago"));
        markerLi = mMap.addMarker(new MarkerOptions()
                .position(limon)
                .title("TEC Centro Académico Limón"));
        markerAmon = mMap.addMarker(new MarkerOptions()
                .position(amon)
                .title("TEC Centro Académico San José"));
        markerAla = mMap.addMarker(new MarkerOptions()
                .position(alajuela)
                .title("TEC Centro Académico Alajuela"));
    }
}
