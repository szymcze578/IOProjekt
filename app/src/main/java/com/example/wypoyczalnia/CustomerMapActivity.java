package com.example.wypoyczalnia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Bitmap;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.content.res.Resources;


import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerMapActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener {

    private static final String TAG = CustomerMapActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map2);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.maps);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        try {
            // Customise the styling of the base map using a JSON object defined
            // in a raw resource file.
            boolean success = googleMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                            this, R.raw.map_style));

            if (!success) {
                Log.e(TAG, "Style parsing failed.");
            }
        } catch (Resources.NotFoundException e) {
            Log.e(TAG, "Can't find style. Error: ", e);
        }

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(50.2541165, 19.0232932), 15));


        ArrayList<Station> stations = new ArrayList<Station>();

        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pst = null;
        con = Database.mycon();

        try {
            String sql = "SELECT * FROM stacje";
            pst = con.prepareCall(sql);
            rs = pst.executeQuery();
            while(rs.next()){
                stations.add(
                        new Station(rs.getInt("ID_stacji"),
                                rs.getInt("wolne_miejsca"),
                                new Pair<> (rs.getDouble("szer geograficzna"),
                                        rs.getDouble("dl geograficzna"))));
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }

        Drawable vectorDrawable = ContextCompat.getDrawable(CustomerMapActivity.this, R.drawable.bicycle_parking_svgrepo_com);
        vectorDrawable.setBounds(0, 0, vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight());
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.draw(canvas);
        BitmapDescriptor icon = BitmapDescriptorFactory.fromBitmap(bitmap);


        for (Station station: stations) {

            Pair<Double, Double> coordinates = station.getCoordinates();
            LatLng pos = new LatLng(coordinates.first, coordinates.second);

            googleMap.addMarker(
                    new MarkerOptions()
                            .position(pos)
                            .title("Stacja nr. " + Integer.toString(station.getStationID())).snippet("Dostępne rowery: " + Integer.toString(station.getAvailableBikes()))
                            .icon(icon));

        }

        googleMap.setOnInfoWindowClickListener(this);

    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        Intent intent = new Intent(this, StationActivity.class);
        startActivity(intent);
    }


}