package com.example.wypoyczalnia;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class CurrentHireActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_hire);
    }

    public void endHire(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Czy na pewno chcesz zakończyć wypożyczenie?");
        builder.setCancelable(true);

        builder.setPositiveButton(
                "Tak",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
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

    public void openTripSummaryActivity() {
        // todo: rental method
        String date = "22.01.2023";
        int time = 54;
        int distance = 17;
        float cost = 27.0f;


        Intent i = new Intent(this, tripSummaryActivity.class);
        i.putExtra("date", date);
        i.putExtra("time", time);
        i.putExtra("distance", distance);
        i.putExtra("cost", cost);
        startActivity(i);
    }
}

