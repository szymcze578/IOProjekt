package com.example.wypoyczalnia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;

public class ComplaintActivity extends AppCompatActivity {

    EditText complaintContent;
    String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint);
    }

    public void sendComplaint(View view) {
        complaintContent = (EditText)findViewById(R.id.complaintContent);
        String complaint = String.valueOf(complaintContent.getText());

        if(complaint.isEmpty()) {
            Toast.makeText(this,"Reklamacja nie może być pusta!",Toast.LENGTH_SHORT).show();
        } else {
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                //todo: add other parameters
                //example: date = extras.getString("date");
            }

            Connection con = null;
            con = Database.mycon();

            Date date = Calendar.getInstance().getTime();

            try {

                String sql = "INSERT INTO reklamacje (data, klient_id_klienta, opis, stan_reklamacji, id_wypozyczenia) VALUES (?,?,?,?,?)";

                PreparedStatement myStmt = con.prepareStatement(sql);

                myStmt.setString(1, String.valueOf(date)); // date
                myStmt.setInt(2, 10);   // todo: customer id
                myStmt.setString(3, complaint); // complaint description
                myStmt.setString(4, "Nierozpatrzona");   // complaint status
                myStmt.setInt(5,23); // todo: rent id

                myStmt.executeUpdate();

                Toast.makeText(this,"Reklamacja złożona!",Toast.LENGTH_SHORT).show();

                startActivity(new Intent(this, Menu.class));
            } catch (SQLException e) {
                Toast.makeText(this,"Wystąpił błąd - przepraszamy!",Toast.LENGTH_SHORT).show();
                e.printStackTrace();

            }
        }
    }
}