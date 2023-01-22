package com.example.wypoyczalnia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Serviceman_menu extends AppCompatActivity {

    private Account serviceman;
    private String serviceman_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serviceman_menu);
        serviceman = new Serviceman(1, "email", "333");

        TextView technician_login_name = findViewById(R.id.technicianID);
        serviceman_name = getIntent().getStringExtra("Username");
        technician_login_name.setText(serviceman_name);

    }

    public void openMapActivity(View view){

        serviceman.map.showMap("Serviceman", view.getContext());
    }
}


