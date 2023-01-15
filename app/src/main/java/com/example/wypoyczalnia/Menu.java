package com.example.wypoyczalnia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

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
}