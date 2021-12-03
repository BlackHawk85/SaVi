package com.savi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class BuscarMedicoActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private FusedLocationProviderClient fusedLocationClient;
    SupportMapFragment mapFragment;
    private static final String TAG = BuscarMedicoActivity.class.getSimpleName();

    private final LatLng HCONC = new LatLng(18.85299, -97.09402);
    private final LatLng SANCOL = new LatLng(18.85161, -97.09702);
    private final LatLng CENMEDCON = new LatLng(18.85063, -97.09248);

    private Marker markerHConc;
    private Marker markerSanCol;
    private Marker markerCenMedCon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_buscar_medico);

        // Get a handle to the fragment and register the callback.
        mapFragment = (SupportMapFragment) getSupportFragmentManager ()
                .findFragmentById (R.id.map);
        mapFragment.getMapAsync (this);

        fusedLocationClient = LocationServices.getFusedLocationProviderClient (this);
        if (ActivityCompat.checkSelfPermission (this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission (this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            getCurrentLocation ();

        }else {
            ActivityCompat.requestPermissions (BuscarMedicoActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
        }
    }

    public void getCurrentLocation() {

        @SuppressLint("MissingPermission") Task<Location> task= fusedLocationClient.getLastLocation ();
        task.addOnSuccessListener (this, new OnSuccessListener<Location> () {
            @Override
            public void onSuccess(Location location) {
                // Got last known location. In some rare situations this can be null.
                if (location != null) {
                    mapFragment.getMapAsync (new OnMapReadyCallback () {
                        @Override
                        public void onMapReady(GoogleMap googleMap) {
                            LatLng latLng = new LatLng (location.getLatitude (), location.getLongitude ());
                            MarkerOptions options = new MarkerOptions ().position (latLng).title ("Estoy aquí");
                            googleMap.animateCamera (CameraUpdateFactory.newLatLngZoom (latLng, 10));
                            googleMap.addMarker (options);
                        }
                    });
                }
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==44){
            if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                getCurrentLocation ();
            }
        }

    }

    // Get a handle to the GoogleMap object and display marker.
    @Override
    public void onMapReady(GoogleMap map) {
        markerHConc=map.addMarker (new MarkerOptions ().position (HCONC).title ("Hospital Concordia").icon (BitmapDescriptorFactory.fromResource(R.drawable.icono_hosp)));
        markerHConc.setTag (0);

        markerCenMedCon=map.addMarker (new MarkerOptions ().position (CENMEDCON).title ("Centro Médico Concordia").icon (BitmapDescriptorFactory.fromResource(R.drawable.icono_hosp)));
        markerCenMedCon.setTag (0);

        markerSanCol=map.addMarker (new MarkerOptions ().position (SANCOL).title ("Sanatorio Colón").icon (BitmapDescriptorFactory.fromResource(R.drawable.icono_hosp)));
        markerSanCol.setTag (0);

        map.setOnMarkerClickListener (this);
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }
}