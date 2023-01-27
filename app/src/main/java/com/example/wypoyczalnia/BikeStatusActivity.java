package com.example.wypoyczalnia;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class BikeStatusActivity extends AppCompatActivity {

    String bikeNumber;
    EditText bikeStatus;
    TextView number;
    RadioGroup group;
    RadioButton available;
    RadioButton unavailable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bike_status);

        bikeNumber = getIntent().getStringExtra("bikeNumber");

        number = findViewById(R.id.statusBikeNumber);
        number.setText("Rower nr. "+bikeNumber);

        bikeStatus = findViewById(R.id.statusChange);
        group = findViewById(R.id.radioGroup);
        available = findViewById(R.id.availableButton);
        unavailable = findViewById(R.id.unavailableButton);
    }
}
