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

    private Customer user;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        //Get logged in user - testing with id number for now
        user = new Customer(getIntent().getIntExtra("ID",0),
                getIntent().getStringExtra("email"),
                getIntent().getStringExtra("phoneNumber"),
                getIntent().getDoubleExtra("wallet",0.0));

        TextView wallet = (TextView) findViewById(R.id.account);
        wallet.setText(String.valueOf(user.getWallet().getFunds()));

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