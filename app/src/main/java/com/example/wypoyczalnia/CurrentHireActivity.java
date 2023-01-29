package com.example.wypoyczalnia;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class CurrentHireActivity extends AppCompatActivity{

    private Customer user;
    private Timer timer;
    private TextView lengthLabel;
    private TextView timeLabel;
    private boolean updateLength;
    private String time;
    private String length;
    private EditText text;
    private int endStation;
    private ArrayList<Integer> availableStations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_hire);
        text = findViewById(R.id.enterStation);

        getAvailableStations();

        lengthLabel = findViewById(R.id.lengthLabel);
        timeLabel = findViewById(R.id.timeLabel);

        //Intent i = getIntent();
        user = UserHolder.getInstance().getCustomer();
        endStation = user.getHire().getBike().getStationID();
        updateLength = true;
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                updateLength = !updateLength;
                updateHire();
            }
        }, 0, 1000);
    }

    private void getAvailableStations() {

        availableStations = new ArrayList<>();

        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pst = null;
        Statement s = null;
        con = Database.mycon();

        try {
            String sql = "SELECT ID_stacji from stacje; ";
            pst = con.prepareCall(sql);
            s = con.createStatement();
            rs = pst.executeQuery();
            while (rs.next()) {
                availableStations.add(rs.getInt("ID_stacji"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void endHire(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Czy na pewno chcesz zakończyć wypożyczenie?");
        builder.setCancelable(true);

        builder.setPositiveButton(
                "Tak",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        String selectedStation = text.getText().toString();
                        if(!selectedStation.isEmpty()) {
                            if(availableStations.contains(Integer.parseInt(selectedStation))) {
                                endStation = Integer.parseInt(selectedStation);
                                timer.cancel();
                                dialog.cancel();
                                openTripSummaryActivity();
                            }
                            else {
                                Toast.makeText(view.getContext(),"Podano nieistniejącą stację.",Toast.LENGTH_SHORT).show();
                                dialog.cancel();
                            }
                        }
                        else {
                            timer.cancel();
                            dialog.cancel();
                            openTripSummaryActivity();
                        }


                    }
                });

        builder.setNegativeButton(
                "Nie",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder.create();
        alert11.show();
    }

    public void updateHire() {
        user.getHire().update();
        int currentTime = user.getHire().getTime();
        int currentLength = user.getHire().getLength();

        if((currentTime/60.0) > 1) {
            time = Integer.toString(currentTime/60) + "min " + Integer.toString(currentTime%60) + "s";
        }
        else
            time = Integer.toString(currentTime) + "s";

        if((currentLength/1000.0) > 1) {
            length = Integer.toString(currentLength/1000) + "." + Integer.toString(currentLength%100) + "km";
        }
        else
            length = Integer.toString(currentLength) + "m";

        timeLabel.setText(time);
        if(updateLength)
            lengthLabel.setText(length);
    }

    public void openTripSummaryActivity() {
        String date = user.getHire().getDate();
        float cost = (user.getHire().getTime()/10)*0.49f;
        int hire_id = user.getHire().getHireID();

        user.getHire().setPayment(cost);
        user.returnABike(endStation);

        Intent i = new Intent(this, tripSummaryActivity.class);
        i.putExtra("date", date);
        i.putExtra("time", time);
        i.putExtra("distance", length);
        i.putExtra("cost", cost);
        i.putExtra("id_wypozyczenia",hire_id);

        i.putExtra("userObject", user);
        startActivity(i);
    }
}

