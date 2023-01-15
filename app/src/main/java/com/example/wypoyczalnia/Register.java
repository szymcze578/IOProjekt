package com.example.wypoyczalnia;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.Statement;

public class Register extends AppCompatActivity {

    EditText username, E_mail, password;
    Button register;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        username = findViewById(R.id.username);
        E_mail = findViewById(R.id.email);
        password = findViewById(R.id.password);
        register = findViewById(R.id.signupBTN);

        Connection con = null;
        con = Database.mycon();
        Connection finalCon = con;


        Connection finalCon1 = con;
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uname = username.getText().toString();
                String email = E_mail.getText().toString();
                String pass = password.getText().toString();

                if(email.equals("")||pass.equals("")||uname.equals("")){
                    Toast.makeText(Register.this,"Please enter all information!",Toast.LENGTH_SHORT).show();
                }else{
                    try{

                        final Statement s = finalCon1.createStatement();
                        s.executeUpdate("INSERT INTO klient (adres_email,haslo,nazwa_uzytkownika) VALUES ('"+email+"','"+pass+"','"+uname+"') ");

                        Toast.makeText(Register.this,"Your account created succesfully. Log in Now",Toast.LENGTH_SHORT).show();

                    }catch (Exception e){
                        System.out.println(e);
                    }
                }
                username.setText("");
                E_mail.setText("");
                password.setText("");
                finish();
            }

        });

    }

    public void openLoginActivity(View view){
        this.finish();
    }
}
