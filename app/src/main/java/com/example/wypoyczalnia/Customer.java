package com.example.wypoyczalnia;

import android.util.Pair;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * A class which represents customer account
 */
public class Customer extends Account {

    private Wallet wallet;
    private Hire hire;
    /**
     * Default constructor method
     */
    public Customer() {

    }

    /**
     * Parameterized constructor method
     * @param id customer id
     * @param email customer email
     * @param phoneNumber customer phone number
     */
    public Customer(int id, String email, String phoneNumber, double value) {
        super(id, email, phoneNumber);
        wallet = new Wallet(value);
        hire = null;
    }

    /**
     * A method that returns wallet of the user
     * @return user's wallet
     */
    public Wallet getWallet() {
        return wallet;
    }

    /**
     * A method that returns hire of the user
     * @return user's hire
     */
    public Hire getHire() {
        return hire;
    }

    /**
     * A method by which user can cancel a bike reservation
     */
    public void cancelReservation() {

    }

    /**
     * A method by which user can book a bike
     */
    public void bookBike() {

    }

    /**
     * A method by which user can contact with customer service
     */
    public void contactService() {

    }

    /**
     * A method by which user can rent a bike
     */
    boolean rentBike(int bikeID) {

        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pst = null;
        Statement s = null;
        con = Database.mycon();

        try {
            String sql = "SELECT dostepny FROM rower WHERE rower.ID_roweru = " + bikeID;
            pst = con.prepareCall(sql);
            s = con.createStatement();
            rs = pst.executeQuery();
            while(rs.next()){

                if(rs.getBoolean("dostepny")) {
                    hire = new Hire(bikeID);
                    hire.setCustomerID(this.getID());

                    sql = "INSERT INTO `wypozyczenia` (`id_wypozyczenia`, `czas`, `data`, `dystans`, `kwota`, `id_klienta`, `klient_id_klienta`, `id_roweru`, `reklamacje_id_reklamacji`) VALUES (" +
                            "NULL" + ", " + //id_wypozyczenia
                            "NULL" + ", " + //czas
                            "\"" + hire.getDate() + "\"" + ", " + //data
                            "NULL" + ", " + //dystans
                            "NULL" + ", " + //kwota
                            "NULL" + ", " + //id_klienta
                            this.getID() + ", " + //klient_id_klienta
                            bikeID + ", " + //id_roweru
                            "NULL" + //reklamacje_id_reklamacji
                            ");";

                    s.executeUpdate(sql);
                    return true;
                }
                else
                    return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * A method by which user can report an accident or bike damage
     */
    void reportAccident() {

    }

    /**
     * A method by which user can make a complaint
     */
    void makeComplaint() {

    }

    /**
     * A method which returns a rented bike
     */
    void returnABike() {
        this.hire.endHire(3);

        Connection con = null;
        Statement s = null;
        con = Database.mycon();
        String sql = null;

        try {
            s = con.createStatement();
            sql = "UPDATE `wypozyczenia` SET " + "czas = " + hire.getTime() + ", dystans = " + hire.getLength() + ", kwota = " + hire.getPayment() + " WHERE id_wypozyczenia = " + hire.getHireID() + ";";

            s.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.hire = null;
    }
}
