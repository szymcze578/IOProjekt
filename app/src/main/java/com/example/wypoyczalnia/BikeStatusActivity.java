package com.example.wypoyczalnia;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class BikeStatusActivity extends AppCompatActivity {

    Bike bike;
    EditText bikeStatus;
    TextView number;
    RadioGroup group;
    RadioButton radioButtonUnavailable;
    RadioButton radioButtonAvailable;

    private String status;
    int available;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bike_status);

        Intent i = getIntent();
        bike = (Bike)i.getSerializableExtra("bikeObject");
        status = bike.getCondition();
        available = bike.getAvailable() ? 1:0;
        number = findViewById(R.id.statusBikeNumber);
        number.setText("Rower nr. "+ bike.getBikeID());

        radioButtonAvailable = findViewById(R.id.availableButton);
        radioButtonUnavailable = findViewById(R.id.unavailableButton);

        bikeStatus = findViewById(R.id.statusChange);
        group = findViewById(R.id.radioGroup);

    }

    public void changeStatus(View view){
        String bikeStatusField = bikeStatus.getText().toString();
        if(!bikeStatusField.isEmpty()){
            status = bikeStatusField;
        }

        if(radioButtonAvailable.isChecked()){
            available = 1;
            //Toast.makeText(this,"Status roweru: "+ available, Toast.LENGTH_SHORT).show();
        }
        if(radioButtonUnavailable.isChecked()){
            available = 0;
            //Toast.makeText(this,"Status roweru: "+ available, Toast.LENGTH_SHORT).show();
        }

        Connection con = null;
        con = Database.mycon();

        try {

            String sql = "UPDATE rower SET stan_techniczny=?, dostepny=? WHERE ID_roweru=?";
            PreparedStatement myStmt = con.prepareStatement(sql);

            myStmt.setString(1,status);
            myStmt.setInt(2, available);
            myStmt.setInt(3,bike.getBikeID());
            myStmt.executeUpdate();

            Toast.makeText(this,"Status roweru zaktualizowany...",Toast.LENGTH_SHORT).show();

        } catch (SQLException e) {
            Toast.makeText(this,"Wystąpił błąd - przepraszamy!",Toast.LENGTH_SHORT).show();
            e.printStackTrace();

        }

        finish();
    }

    public void checkButton(View view){


    }
}
