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

            Customer user = UserHolder.getInstance().getCustomer();
            int id_klienta = user.getID();

            try {

                String sql = "INSERT INTO awarie (data, klient_id_klienta, opis, stan_awari, rower_ID_roweru) VALUES (?,?,?,?,?)";

                PreparedStatement myStmt = con.prepareStatement(sql);

                myStmt.setString(1, String.valueOf(date)); //date
                myStmt.setInt(2, id_klienta);   //todo: customerID
                myStmt.setString(3, damageDescription); // decription
                myStmt.setString(4, "Awaria");   //status
                myStmt.setInt(5,1);    //rower_ID_roweru

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