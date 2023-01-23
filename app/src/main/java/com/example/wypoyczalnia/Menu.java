package com.example.wypoyczalnia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DecimalFormat;

public class Menu extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private Customer user;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        //Get logged in user - testing with id number for now
        user = new Customer(getIntent().getIntExtra("ID",0),
                getIntent().getStringExtra("email"),
                getIntent().getStringExtra("phoneNumber"),
                getIntent().getDoubleExtra("wallet",0.0));

        //Set funds label
        TextView wallet = (TextView) findViewById(R.id.account);
        DecimalFormat dform = new DecimalFormat("#.##");
        wallet.setText(String.valueOf(dform.format(user.getWallet().getFunds())));

        //Operating on navigation view
        NavigationView navigationView = findViewById(R.id.navigationView);
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.m_recharge) {
           accountRecharge();
        }
        if(id == R.id.m_rides){

        }
        return true;
    }

    public void openNavigationMenu(View view){
        final DrawerLayout navigation = findViewById(R.id.drawerLayout);
        navigation.openDrawer(GravityCompat.START);

        /* Setting username in slided menu from.*/
        TextView Username = (TextView) navigation.findViewById(R.id.navheader_username);
        username = getIntent().getStringExtra("Username");
        Username.setText(username);

    }

    public void openMapActivity(View view){

        user.map.showMap("Customer", view.getContext());
    }

    public void openDamageReportActivity(View view) {
        startActivity(new Intent(this, ReportDamageActivity.class));
    }

    public void showRentalHistory(View view){
        Toast.makeText(this,"Wystąpił błąd - przepraszamy!",Toast.LENGTH_SHORT).show();
    }

    public void openCameraActivity(View view){

        Intent intent = new Intent(view.getContext(), CameraActivity.class);
        view.getContext().startActivity(intent);
    }

    public void accountRecharge() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.custom_dialog, null);
        EditText recharge;
        recharge = view.findViewById(R.id.accountRecharge);

        Button submit = view.findViewById(R.id.doładuj);
        builder.setView(view);
        AlertDialog dialog = builder.create();

        submit.setOnClickListener(new View.OnClickListener(){
        @Override
            public void onClick(View view){
            String value = recharge.getText().toString();
            double income = Double.valueOf(value);
            user.getWallet().addFunds(income);
            updateFoundsInDB();
            resetFunds();
            dialog.dismiss();
        }
        });
        dialog.show();

    }

    /**
     * A method to reset textview with funds.
     */
    private void resetFunds(){
        TextView wallet = (TextView) findViewById(R.id.account);
        DecimalFormat dform = new DecimalFormat("#.##");
        wallet.setText(String.valueOf(dform.format(user.getWallet().getFunds())));
    }

    /**
     * A method to update funds in database after transaction.
     */
    private void updateFoundsInDB(){
        Connection con = null;
        con = Database.mycon();

        int ID = user.getID();
        double funds = user.getWallet().getFunds();

        try {

            String sql = "UPDATE klient SET stan_konta=? WHERE id_klienta=?";

            PreparedStatement myStmt = con.prepareStatement(sql);

            myStmt.setDouble(1,funds);
            myStmt.setInt(2, ID);

            myStmt.executeUpdate();

            Toast.makeText(this,"Twój portfel został zaktualizowany..",Toast.LENGTH_SHORT).show();

        } catch (SQLException e) {
            Toast.makeText(this,"Wystąpił błąd - przepraszamy!",Toast.LENGTH_SHORT).show();
            e.printStackTrace();

        }
    }

}

