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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class ComplaintActivity extends AppCompatActivity {

    EditText complaintContent;
    String date;
    int hire_id;

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
                 hire_id = extras.getInt("id_wypozyczenia");
            }

            Connection con = null;
            con = Database.mycon();


            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            String date = dtf.format(now);

            Customer user = UserHolder.getInstance().getCustomer();
            int id_klienta = user.getID();

            try {

                String sql = "INSERT INTO reklamacje (data, klient_id_klienta, opis, stan_reklamacji, wypozyczenia_id_wypozyczenia) VALUES (?,?,?,?,?)";

                PreparedStatement myStmt = con.prepareStatement(sql);

                myStmt.setString(1, String.valueOf(date)); // date
                myStmt.setInt(2, id_klienta); //customer id
                myStmt.setString(3, complaint); // complaint description
                myStmt.setString(4, "Nierozpatrzona");   // complaint status
                myStmt.setInt(5,hire_id); //hire id

                myStmt.executeUpdate();

                Toast.makeText(this,"Reklamacja złożona!",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, Menu.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            } catch (SQLException e) {
                Toast.makeText(this, "Wystąpił błąd - przepraszamy!",Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }
    }
}