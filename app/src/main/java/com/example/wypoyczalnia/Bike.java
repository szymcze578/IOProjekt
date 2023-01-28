package com.example.wypoyczalnia;

import android.util.Pair;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * A class which represents a bike
 */
@SuppressWarnings("serial")
public class Bike implements Serializable {

    /**
     * Variable indicating whether bike is available
     */
    private Boolean available;

    /**
     * Bike ID
     */
    private int bikeID;

    /**
     * Station ID
     */
    private int stationID;

    /**
     * Bike condition
     */
    private Enum condition; //todo: set and get

    /**
     * Bike coordinates
     */
    private Pair<Double,Double> coordinates;

    /**
     * Default contructor method
     */
    public Bike() {

    }

    /**
     * Parameterized ontructor method
     * @param bikeID bikeID
     */
    public Bike(Boolean a, int bikeID, int stationID, Pair<Double,Double> coordinates) {
        this.available = a;
        this.bikeID = bikeID;
        this.stationID = stationID;
        this.coordinates = coordinates;
        //todo: ENUM
    }
    public Bike(int BikeID)
    {
        this.bikeID = BikeID;
    }

    /**
     * A method which sets bikeID
     * @param id bike ID
     */
    public void setBikeID(int id){
        this.bikeID = id;
    }

    /**
     * A method which returns bikeID
     * @return bikeID
     */
    public int getBikeID() {
        return this.bikeID;
    }

    /**
     * A method which sets bike availability
     * @param a bike availability
     */
    public void setAvailable(Boolean a){
        this.available = a;
        int x = a ? 1 : 0;
        Connection con = null;
        String sql = null;
        ResultSet rs = null;
        PreparedStatement pst = null;
        Statement s = null;
        con = Database.mycon();

        sql = "UPDATE `rower` SET `dostepny` = " + x + " WHERE `rower`.`ID_roweru` = " + this.bikeID + ";";

        try{
            s = con.createStatement();
            s.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    /**
     * A method which returns bike availability
     * @return bike availability
     */
    public Boolean getAvailable() {
        return this.available;
    }

    /**
     * A method which sets stationID
     * @param id stationID
     */
    public void setStationID(int id) {
        this.stationID = id;
    }

    /**
     * A method which returns stationID
     * @return  stationID
     */
    public int getStationID() {
        return this.stationID;
    }

    /**
     * A method which sets bike coordinates
     * @param coordinates bike coordinates
     */
    public void setCoordinates(Pair<Double,Double> coordinates) {
        this.coordinates = coordinates;
    }

    /**
     * A method which returns bike coordinates
     * @return bike coordinates
     */
    public Pair<Double, Double> getCoordinates() {
        return this.coordinates;
    }

}
