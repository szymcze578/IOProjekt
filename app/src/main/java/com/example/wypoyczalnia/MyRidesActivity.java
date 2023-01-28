package com.example.wypoyczalnia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MyRidesActivity extends AppCompatActivity {

    TextView lpLabel;
    TextView dateLabel;
    TextView timeLabel;
    TextView distanceLabel;
    TextView costLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_rides);

        lpLabel = (TextView)findViewById(R.id.lpLabel);
        dateLabel = (TextView)findViewById(R.id.dateLabel);
        timeLabel = (TextView)findViewById(R.id.timeLabel);
        distanceLabel = (TextView)findViewById(R.id.distanceLabel);
        costLabel = (TextView)findViewById(R.id.costLabel);

        String lpResult = "Lp.\n\n";
        String dateResult = "Data\n\n";
        String timeResult = "Czas\n\n";
        String distanceResult = "Dystans\n\n";
        String costResult = "Koszt\n\n";

        Connection con = null;
        ResultSet rs = null;
        final PreparedStatement[] pst = {null};
        con = Database.mycon();


        Connection finalCon = con;

        Customer user = UserHolder.getInstance().getCustomer();
        int id_klienta = user.getID();

        try {


            String sql = "SELECT data, czas, dystans, kwota FROM wypozyczenia WHERE klient_id_klienta =?";

            pst[0] = con.prepareCall(sql);
            pst[0].setInt(1, id_klienta); //user

            rs = pst[0].executeQuery();
            int i = 0;
            while(rs.next()) {
                String date = rs.getString("data");
                int time = rs.getInt("czas");
                int distance = rs.getInt("dystans");
                Float cost = rs.getFloat("kwota");

                lpResult += ++i + ".\n\n";
                dateResult += date + "\n\n";
                timeResult += time + "\n\n";
                distanceResult += distance + "\n\n";
                costResult += cost + " zł\n\n";

            }

            lpLabel.setText(lpResult);
            dateLabel.setText(dateResult);
            timeLabel.setText(timeResult);
            distanceLabel.setText(distanceResult);
            costLabel.setText(costResult);

        } catch (SQLException e) {
            Toast.makeText(this,"Wystąpił błąd - przepraszamy!",Toast.LENGTH_SHORT).show();
            //Toast.makeText(this,e.toString(),Toast.LENGTH_SHORT).show();
            e.printStackTrace();

        }


    }

    public void backToMenu(View view) {
        this.finish();
    }
}