package com.example.wypoyczalnia;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MainActivity extends AppCompatActivity {

    EditText username, password;
    Button login, register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.INTERNET}, PackageManager.PERMISSION_GRANTED);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.loginBTN);
        register = findViewById(R.id.signupBTN);

        Connection con = null;
        final ResultSet[] rs = {null};
        final PreparedStatement[] pst = {null};
        con = Database.mycon();


        Connection finalCon = con;
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String us = username.getText().toString();
                String ps = password.getText().toString();

                if(us.equals("") || ps.equals("")){
                    Toast.makeText(MainActivity.this,"Please enter username and password!",Toast.LENGTH_SHORT).show();
                }else{
                    try {
                        String sql = "SELECT * FROM klient WHERE nazwa_uzytkownika=? AND haslo=?";
                        pst[0] = finalCon.prepareCall(sql);
                        pst[0].setString(1,us); //user
                        pst[0].setString(2, ps);   //password

                        rs[0] = pst[0].executeQuery();
                        if(rs[0].next()){

                            int id = rs[0].getInt(1);
                            String email = rs[0].getString(2);
                            String number = rs[0].getString(3);
                            double wallet = rs[0].getFloat(4);


                            Toast.makeText(MainActivity.this,"Logging in...",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity.this, Menu.class);

                            /*Sent username to next activity, user should be created here also.*/
                            intent.putExtra("ID", id);
                            intent.putExtra("email",email);
                            intent.putExtra("phoneNumber",number);
                            intent.putExtra("wallet",wallet);
                            intent.putExtra("Username", us);
                            startActivity(intent);

                        }
                        else if(!rs[0].next()){
                            String question = "SELECT * FROM serwisanci WHERE nazwa_uzytkownika=? AND haslo=?";
                            pst[0]  = finalCon.prepareCall(question);
                            pst[0].setString(1,us);
                            pst[0].setString(2,ps);
                            rs[0] = pst[0].executeQuery();
                            if(rs[0].next()){
                                Toast.makeText(MainActivity.this,"Logging in as serviceman...",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(MainActivity.this, Serviceman_menu.class);
                                intent.putExtra("Username", us);
                                startActivity(intent);
                            }
                            else {
                                Toast.makeText(MainActivity.this,"Failed to log in",Toast.LENGTH_SHORT).show();
                            }
                        }

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

            }

        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Register.class);
                startActivity(intent);
            }
        });
    }


    //Do usuniecia (prawdopodobnie)
    //public void openMainMenuActivity(View view){
    //     Intent intent = new Intent(this, Menu.class);
    //       startActivity(intent);
    //  }

    // Do usuniecia prawdopodobnie
    //  public void openRegisterActivity(View view){
    //       Intent intent = new Intent(this, Register.class);
    //      startActivity(intent);
}
//}

