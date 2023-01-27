package com.example.wypoyczalnia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class bikeManagementActivity extends AppCompatActivity {

    private ArrayList<Bike> bikesArray;
    private RecyclerView recyclerView;
    private String stationNumber;
    private RecyclerAdapter.RecyclerViewClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bike_management);
        recyclerView = findViewById(R.id.recyclerView);

        bikesArray = new ArrayList<>();
        stationNumber = getIntent().getStringExtra("stationNumber");

        filStations();
        setAdapter();
    }

    private void setAdapter() {
        setOnClickListener();
        RecyclerAdapter adapter = new RecyclerAdapter(bikesArray, listener);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    private void setOnClickListener() {
        listener = new RecyclerAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View V, int position) {
                Intent intent = new Intent(getApplicationContext(),BikeStatusActivity.class);
                int number = bikesArray.get(position).getBikeID();
                intent.putExtra("bikeNumber",String.valueOf(number));
                startActivity(intent);
            }
        };
    }

    private void filStations() {

        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pst = null;
        con = Database.mycon();
        Pair para = new Pair<>(0.0,0.0);
        try {
            String sql = "SELECT * FROM rower WHERE stacje_ID_stacji=?";
            pst = con.prepareCall(sql);
            pst.setString(1,stationNumber);
            rs = pst.executeQuery();
            while(rs.next()){
                bikesArray.add(
                        new Bike(rs.getBoolean("dostepny"),
                                rs.getInt("ID_roweru"),
                                rs.getInt("stacje_ID_stacji"),
                                        para));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}