package com.example.wypoyczalnia;

import android.util.Pair;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * A class which represents customer account
 */
public class Customer extends Account {

    private Wallet wallet;
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
    }

    /**
     * A method that returns wallet of the user
     * @return user's wallet
     */
    public Wallet getWallet() {
        return wallet;
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
        con = Database.mycon();

        try {
            String sql = "SELECT dostepny FROM rower WHERE rower.ID_roweru = " + bikeID;
            pst = con.prepareCall(sql);
            rs = pst.executeQuery();
            while(rs.next()){

                if(rs.getBoolean("dostepny")) {
                    //stwórz wypozyczenie
                }
                return true;
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

    }
}
