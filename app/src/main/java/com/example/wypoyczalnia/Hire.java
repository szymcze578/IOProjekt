package com.example.wypoyczalnia;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.Timer;
/**
 * A class which represents bike rent
 */
@SuppressWarnings("serial")
public class Hire implements Serializable {

    /**
     * A rented bike
     */
    private Bike bike;

    /**
     * Total time elapsed in seconds
     */
    private int time;

    /**
     * Total distance traveled in meters
     */
    private int length;

    /**
     * Hire ID
     */
    private int hireID;

    /**
     * Customer ID
     */
    private int customerID;

    /**
     * Total amount of money which should be paid for the loan
     */
    private float payment;

    /**
     * Date when the hire was made
     */
    private String date;

    /**
     * Random number generator for simulating traveled distance
     */
    private Random rand;

    /**
     * Default constructor method
     */
    public Hire() {

    }

    /**
     * Parameterized constructor method
     * @param bikeID bikeID
     */
    public Hire(int bikeID) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        date = dtf.format(now);

        rand = new Random();
        bike = new Bike(bikeID);
        bike.setAvailable(false);
        time = 0;
        length = 0;

        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pst = null;
        Statement s = null;
        con = Database.mycon();

        try {
            String sql = "SELECT MAX(id_wypozyczenia) FROM wypozyczenia;";
            pst = con.prepareCall(sql);
            s = con.createStatement();
            rs = pst.executeQuery();
            while (rs.next()) {
                this.setHireID(rs.getInt(1)+1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * Parameterized constructor method
     * @param id hireID
     * @param time time
     * @param length hire length
     * @param customerID customerID
     * @param payment payment
     * @param date hire date
     */
    public Hire(int id, int time, int length, int customerID, int payment, String date) {
        this.hireID = id;
        this.time = time;
        this.length = length;
        this.customerID = customerID;
        this.payment = payment;
        this.date = date;
    }

    /**
     * A method which sets hireID
     * @param id hireID
     */
    public void setHireID(int id) {
        this.hireID = id;
    }

    /**
     * A method which returns hireID
     * @return hireID
     */
    public int getHireID() {
        return this.hireID;
    }

    /**
     * A method which sets hire time
     * @param time hire time
     */
    public void setTime(int time) {
        this.time = time;
    }

    /**
     * A method which returns hire time
     * @return hire time
     */
    public int getTime() {
        return this.time;
    }

    /**
     * A method which sets hire length
     * @param length hire length
     */
    public void setLength(int length) {
        this.length = length;
    }

    /**
     * A method which returns hire length
     * @return hire length
     */
    public int getLength() {
        return this.length;
    }

    /**
     * A method which sets customerID
     * @param id customer id
     */
    public void setCustomerID(int id){
        this.customerID = id;
    }

    /**
     * A method which returns customerID
     * @return customerID
     */
    public int getCustomerID() {
        return this.customerID;
    }

    /**
     * A method which sets payment
     * @param payment payment
     */
    public void setPayment(float payment) {
        this.payment = payment;
    }

    /**
     * A method which returns payment
     * @return payment
     */
    public float getPayment() {
        return this.payment;
    }

    /**
     * A method which sets hire date
     * @param date hire date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * A method which returns hire date
     * @return hire date
     */
    public String getDate() {
        return this.date;
    }

    /**
     * A method which returns hired bike
     * @return hire date
     */
    public Bike getBike() {
        return this.bike;
    }

    /**
     * A function which shows information about the hire
     */
    public void showHire() {

    }

    public void endHire(int stationID) {
        this.bike.changeStationID(stationID);
        this.bike.setAvailable(true);
    }

    public void update() {
        time++;
        length+=(rand.nextInt(8-3+1) + 3);
    }

}
