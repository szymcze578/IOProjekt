package com.example.wypoyczalnia;


import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;

/**
 * A class which represents map
 */
public class Map extends AppCompatActivity {

    Intent intent;

    /**
     * Default constructor method
     */
    public Map() {

    }

    /**
     * A function which shows map to the user
     */
    public void showMap(String accountType, Context context) {

        if(accountType.equals("Customer"))
            intent = new Intent(context, CustomerMapActivity.class);
        else if(accountType.equals("Serviceman"))
            intent = new Intent(context, ServicemanMapActivity.class);
        context.startActivity(intent);

    }
}

