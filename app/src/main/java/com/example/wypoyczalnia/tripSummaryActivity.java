package com.example.wypoyczalnia;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class tripSummaryActivity extends AppCompatActivity {


    TextView tripDataLabel;
    TextView tripTimeLabel;
    TextView tripDistanceLabel;
    TextView tripCostLabel;
    String date;
    int time;
    int distance;
    float cost;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_summary);

        tripDataLabel = (TextView)findViewById(R.id.tripDataLabel);
        tripTimeLabel = (TextView)findViewById(R.id.tripTimeLabel);
        tripDistanceLabel = (TextView)findViewById(R.id.tripDistanceLabel);
        tripCostLabel = (TextView)findViewById(R.id.tripCostLabel);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            //todo: get parameters after adding rental method
            date = extras.getString("date");
            time = extras.getInt("time");
            distance = extras.getInt("distance");
            cost = extras.getFloat("cost");

            tripDataLabel.setText(date);
            tripTimeLabel.setText(time + " min");
            tripDistanceLabel.setText(distance + " km");
            tripCostLabel.setText(cost + " zł");

        }
    }

    public void returnToMainMenu(View view) {
        startActivity(new Intent(this, Menu.class));
    }

    public void makeAComplaint(View view) {
        Intent i = new Intent(this, ComplaintActivity.class);
        i.putExtra("date", date);
        startActivity(i);

    }
}