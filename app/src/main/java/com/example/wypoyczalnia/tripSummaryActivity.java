package com.example.wypoyczalnia;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class tripSummaryActivity extends AppCompatActivity {

    TextView tripDataLabel;
    TextView tripTimeLabel;
    TextView tripDistanceLabel;
    TextView tripCostLabel;
    String date;
    String time;
    String distance;
    float cost;
    Customer user;
    Integer hire_id;

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
            user = (Customer)getIntent().getSerializableExtra("userObject");

            date = extras.getString("date");
            time = extras.getString("time");
            distance = extras.getString("distance");
            cost = extras.getFloat("cost");
            hire_id = extras.getInt("id_wypozyczenia");

            tripDataLabel.setText(date);
            tripTimeLabel.setText(time);
            tripDistanceLabel.setText(distance);
            tripCostLabel.setText(cost + " zł");

        }
    }

    public void returnToMainMenu(View view) {
        Intent intent = new Intent(this, Menu.class);
        intent.putExtra("userObject", user);
        takeFundsFromUser();
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void makeAComplaint(View view) {
        Intent i = new Intent(this, ComplaintActivity.class);
        i.putExtra("date", date);
        i.putExtra("id_wypozyczenia",hire_id);
        takeFundsFromUser();
        startActivity(i);
    }

    public void takeFundsFromUser(){
        user.getWallet().takeFunds((double)cost);
        user.updateFoundsInDB(getApplicationContext());
        Toast.makeText(this,"Pobrano kwote w wysokości: "+ cost +" zł.",Toast.LENGTH_SHORT).show();
    }
}