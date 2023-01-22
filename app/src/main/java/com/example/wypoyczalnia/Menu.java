package com.example.wypoyczalnia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class Menu extends AppCompatActivity {

    private Account user;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        user = new Customer(1, "email", "333");

        NavigationView navigationView = findViewById(R.id.navigationView);
        navigationView.setItemIconTintList(null);

    }

    public void openNavigationMenu(View view){
        final DrawerLayout navigation = findViewById(R.id.drawerLayout);
        navigation.openDrawer(GravityCompat.START);

        /* Setting username in slided menu from.*/
        TextView Username = (TextView) navigation.findViewById(R.id.navheader_username);
        username = getIntent().getStringExtra("Username");
        Username.setText(username);

    }


    public void openMapActivity(View view){

        user.map.showMap("Customer", view.getContext());
    }

    public void openTripSummaryActivity(View view) {
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

    public void openDamageReportActivity(View view) {
        startActivity(new Intent(this, ReportDamageActivity.class));
    }

    public void showRentalHistory(View view){
        Toast.makeText(this,"Wystąpił błąd - przepraszamy!",Toast.LENGTH_SHORT).show();
    }
}