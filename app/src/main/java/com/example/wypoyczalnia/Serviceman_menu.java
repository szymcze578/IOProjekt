package com.example.wypoyczalnia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Serviceman_menu extends AppCompatActivity {

    private Serviceman serviceman;
    private String serviceman_name;
    private boolean doubletap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serviceman_menu);

        serviceman = UserHolder.getInstance().getServiceman();
        serviceman_name = UserHolder.getInstance().getUsername();

        TextView technician_login_name = findViewById(R.id.technicianID);
        technician_login_name.setText(serviceman_name);

    }

    public void openMapActivity(View view){

        serviceman.map.showMap("Serviceman", view.getContext());
    }

    public void openBikeManagement(View view){
        Intent intent = new Intent(this, bikeManagementActivity.class);
        startActivity(intent);
    }

    public void openAddBikeActivity(View view){
        startActivity(new Intent(this, activity_serviceman_addbike.class));
    }


    @Override
    public void onBackPressed(){
        if(doubletap){
            super.onBackPressed();
        }
        else{
            Toast.makeText(this, "Naciśnij jeszcze raz by wyłączyć aplikację!", Toast.LENGTH_SHORT).show();
            doubletap = true;
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    doubletap = false;
                }
            },500);
        }
    }

}


