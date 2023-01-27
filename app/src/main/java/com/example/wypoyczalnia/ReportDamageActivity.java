package com.example.wypoyczalnia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

public class ReportDamageActivity extends AppCompatActivity {

    EditText damageReportContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_damage);
    }

    public void sendDamageReport(View view) {
        damageReportContent = (EditText)findViewById(R.id.damageReportContent);
        String damageDescription = String.valueOf(damageReportContent.getText());

        if(damageDescription.isEmpty()) {
            Toast.makeText(this,"Opisz napotkaną awarię!",Toast.LENGTH_SHORT).show();
        } else {
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                //todo: add other parameters
                //example: date = extras.getString("date");
            }



            Date date = Calendar.getInstance().getTime();

            Connection con = null;
            con = Database.mycon();

            try {

                String sql = "INSERT INTO reklamacje (data, klient_id_klienta, opis, stan_reklamacji, id_wypozyczenia) VALUES (?,?,?,?,?)";

                PreparedStatement myStmt = con.prepareStatement(sql);

                myStmt.setString(1, String.valueOf(date)); //date
                myStmt.setInt(2, 10);   //todo: customerID
                myStmt.setString(3, damageDescription); // decription
                myStmt.setString(4, "Awaria");   //status
                myStmt.setInt(5,23); //todo: delete after adding table

                myStmt.executeUpdate();

                Toast.makeText(this,"Awaria zgłoszona!",Toast.LENGTH_SHORT).show();

                startActivity(new Intent(this, Menu.class));
            } catch (SQLException e) {
                Toast.makeText(this,"Wystąpił błąd - przepraszamy!",Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }
    }
}