package com.example.khaddan_zakaria_exam_m1_iibdcc_23;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private MapView mapView;
    private GoogleMap map;
    private String LatLand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        mapView=findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync( this);
        Intent intent=getIntent();
        LatLand=intent.getStringExtra("LatLand");
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        map=googleMap;
        String Lat=LatLand.split(",")[0];
        String Long=LatLand.split(",")[1];
        LatLng location=new LatLng(Double.parseDouble(Lat),Double.parseDouble(Long));
        map.addMarker(new MarkerOptions().position(location));
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(location,10));
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

}