package com.example.wypoyczalnia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class StationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station);
        TextView name = (TextView)findViewById(R.id.station);
        name.setText("Stacja nr.  ");
    }
}