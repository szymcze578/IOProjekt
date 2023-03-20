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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class ReportDamageActivity extends AppCompatActivity {

    EditText damageReportContent;
    EditText bikeIdInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_damage);
    }

    public void sendDamageReport(View view) {
        damageReportContent = (EditText)findViewById(R.id.damageReportContent);
        String damageDescription = String.valueOf(damageReportContent.getText());

        bikeIdInput = (EditText)findViewById(R.id.bikeIdInput);
        String bikeId = String.valueOf(bikeIdInput.getText());

        if(damageDescription.isEmpty() || bikeId.isEmpty()) {
            Toast.makeText(this,"Wypełnij wszystkie pola!",Toast.LENGTH_SHORT).show();
        } else {
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                //example: date = extras.getString("date");
            }

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            String date = dtf.format(now);

            Connection con = null;
            con = Database.mycon();

            Customer user = UserHolder.getInstance().getCustomer();
            int id_klienta = user.getID();
            int bike = Integer.parseInt(bikeId);
            try {

                String sql = "INSERT INTO awarie (data, klient_id_klienta, opis, stan_awari, rower_ID_roweru) VALUES (?,?,?,?,?)";

                PreparedStatement myStmt = con.prepareStatement(sql);

                myStmt.setString(1, String.valueOf(date)); //date
                myStmt.setInt(2, id_klienta); //client id
                myStmt.setString(3, damageDescription); // decription
                myStmt.setString(4, "Oczekująca");   //status
                myStmt.setInt(5,bike);    //rower_ID_roweru

                myStmt.executeUpdate();

                Toast.makeText(this,"Awaria zgłoszona!",Toast.LENGTH_SHORT).show();

                //startActivity(new Intent(this, Menu.class));
                finish();
            } catch (SQLException e) {
                Toast.makeText(this,"Wystąpił błąd - przepraszamy!",Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }
    }
}