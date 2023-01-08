package com.example.wypoyczalnia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;

import com.google.android.material.navigation.NavigationView;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        NavigationView navigationView = findViewById(R.id.navigationView);
        navigationView.setItemIconTintList(null);
    }

    public void openNavigationMenu(View view){
        final DrawerLayout navigation = findViewById(R.id.drawerLayout);
        navigation.openDrawer(GravityCompat.START);
    }


    public void openMapActivity(View view){

        Intent intent = new Intent(this, MapActivity2.class);
        startActivity(intent);
    }
}