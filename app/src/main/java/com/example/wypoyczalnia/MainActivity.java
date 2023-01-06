package com.example.wypoyczalnia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.os.StrictMode;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.INTERNET}, PackageManager.PERMISSION_GRANTED);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pst = null;
        con = Database.mycon();


            try {
                String sql = "SELECT * FROM klient";
                pst = con.prepareCall(sql);
                rs = pst.executeQuery();
                while(rs.next()){
                    System.out.println(rs.getInt("id_klienta"));
                    System.out.println(rs.getString("adres_email"));
                    System.out.println(rs.getString("nr_telefonu"));
                    System.out.println(rs.getFloat("stan_konta"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    public void openMainMenuActivity(View view){
        Intent intent = new Intent(this, Menu.class);
        startActivity(intent);
    }

    public void openRegisterActivity(View view){
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }
}