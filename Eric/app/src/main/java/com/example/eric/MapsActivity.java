package com.example.eric;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private static final int REQUEST_LOCATION_PERMISSION = 0;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(31.639954, 74.828261);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Boys Hostel II"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        LatLng gh = new LatLng(31.635795677366428, 74.82133180100344);
        mMap.addMarker(new MarkerOptions().position(gh).title("Girls Hostel II"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(gh));

        LatLng uit = new LatLng(31.642877481298985, 74.81504524612662);
        mMap.addMarker(new MarkerOptions().position(uit).title("U.I.T"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(uit));

        LatLng mv = new LatLng(31.63199407073232, 74.82547442628768);
        mMap.addMarker(new MarkerOptions().position(mv).title("Motor Vehicle Parking"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(mv));

        LatLng fc = new LatLng(31.634139521007295, 74.82581265936194);
        mMap.addMarker(new MarkerOptions().position(fc).title("Fountain Chowk"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(fc));

        LatLng bdl = new LatLng(31.63592495859865, 74.82538986799683);
        mMap.addMarker(new MarkerOptions().position(bdl).title("Bhai Gurdas Library"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(bdl));

        LatLng ld = new LatLng(31.63743679427499, 74.8275545597862);
        mMap.addMarker(new MarkerOptions().position(ld).title("Law Dept."));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(ld));

        enableMyLocation();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.maptypes, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Change the map type based on the user's selection.
        switch (item.getItemId()) {
            case R.id.normal_map:
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                return true;
            case R.id.hybrid_map:
                mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                return true;
            case R.id.satellite_map:
                mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                return true;
            case R.id.terrain_map:
                mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        // Check if location permissions are granted and if so enable the
        // location data layer.
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_LOCATION_PERMISSION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    enableMyLocation();
                    break;
                }
        }
    }

    private void enableMyLocation() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        } else {
            ActivityCompat.requestPermissions(this, new String[]
                            {Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_LOCATION_PERMISSION);
        }
       }


}