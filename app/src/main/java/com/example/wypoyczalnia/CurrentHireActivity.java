package com.example.wypoyczalnia;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_hire);

        lengthLabel = findViewById(R.id.lengthLabel);
        timeLabel = findViewById(R.id.timeLabel);

        Intent i = getIntent();
        user = (Customer)i.getSerializableExtra("userObject");
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

    public void endHire(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Czy na pewno chcesz zakończyć wypożyczenie?");
        builder.setCancelable(true);

        builder.setPositiveButton(
                "Tak",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        timer.cancel();
                        dialog.cancel();
                        openTripSummaryActivity();
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
        // todo: rental method
        String date = user.getHire().getDate();
        float cost = (user.getHire().getTime()/10)*0.49f;
        int hire_id = user.getHire().getHireID();

        user.getHire().setPayment(cost);
        user.returnABike();

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

