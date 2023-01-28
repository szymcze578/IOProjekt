package com.example.wypoyczalnia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class activity_serviceman_addbike extends AppCompatActivity {



    EditText id_stacji, dostepnosc,stan_tech;
    Button addBike;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serviceman_addbike);

    }

    public void addBikeToDB(View view) {
        id_stacji = (EditText)findViewById(R.id.id_stacji);
        String stationID = String.valueOf(id_stacji.getText());
        dostepnosc = (EditText)findViewById(R.id.dostepnosc);
        String availability = String.valueOf(dostepnosc.getText());
        stan_tech = (EditText)findViewById(R.id.stan_tech);
        String techCondition = String.valueOf(stan_tech.getText());

        if(stationID.isEmpty() || availability.isEmpty() || techCondition.isEmpty()) {
            Toast.makeText(this,"Prosze wypelnic wszystkie pole",Toast.LENGTH_SHORT).show();
        }
        else{
            Connection con = null;
            con = Database.mycon();

            try{
                String sql = "INSERT INTO rower (stacje_ID_stacji, dostepny, stan_techniczny) VALUES (?,?,?)";

                PreparedStatement mySTMT = con.prepareStatement(sql);
                mySTMT.setString(1,stationID);
                mySTMT.setString(2,availability);
                mySTMT.setString(3,techCondition);

                mySTMT.executeUpdate();

                Toast.makeText(this,"rower dodany poprawnie",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this,Serviceman_menu.class));
            }catch (Exception e){
                Toast.makeText(this,"blad dodanie roweru",Toast.LENGTH_SHORT).show();
                System.out.println(e);
            }

        }


    }



}