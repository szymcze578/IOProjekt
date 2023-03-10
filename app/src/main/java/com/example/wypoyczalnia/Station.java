package com.example.wypoyczalnia;

import android.util.Pair;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * A class which represents bike station
 */
public class Station {

    /**
     * Station ID
     */
    private int stationID;

    /**
     * Available free space
     */
    private int freeSpace;

    /**
     * Station coordinates
     */
    Pair<Double, Double> coordinates;

    /**
     * Default constructor method
     */
    public Station(){

    }

    /**
     * Parameterized constructor method
     * @param id stationID
     * @param freeSpace freeSpace
     * @param coordinates station coordinates
     */
    public Station(int id, int freeSpace, Pair<Double,Double> coordinates) {
        this.stationID = id;
        this.freeSpace = freeSpace;
        this.coordinates = coordinates;
    }

    /**
     * A method which sets station id
     * @param id stationID
     */
    public void setStationID(int id) {
        this.stationID = id;
    }

    /**
     * A method which returns stationID
     * @return stationID
     */
    public int getStationID() {
        return this.stationID;
    }

    /**
     * A method which sets free space
     * @param freeSpace free space
     */
    public void setFreeSpace(int freeSpace) {
        this.freeSpace = freeSpace;
    }

    /**
     * A method which returns free space
     * @return freeSpace
     */
    public int getFreeSpace(){
        return this.freeSpace;
    }

    /**
     * A method which returns number of available bikes
     * @return freeSpace
     */
    public int getAvailableBikes(){

        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pst = null;
        con = Database.mycon();

        try {
            String sql = "SELECT COUNT(*) FROM `rower` WHERE dostepny=1 AND stacje_ID_stacji=" + this.getStationID() + ";";
            pst = con.prepareCall(sql);
            rs = pst.executeQuery();
            while(rs.next()){
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    /**
     * A method which returns number of damaged bikes
     * @return freeSpace
     */
    public int getDamagedBikes(){

        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pst = null;
        con = Database.mycon();

        try {
            String sql = "SELECT COUNT(*) FROM `rower` WHERE stan_techniczny != " + "\'sprawny\'" + " AND stacje_ID_stacji=" + this.getStationID() + ";";
            pst = con.prepareCall(sql);
            rs = pst.executeQuery();
            while(rs.next()){
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    /**
     * A method which sets station coordinates
     * @param coordinates station coordinates
     */
    public void setCoordinates(Pair<Double, Double> coordinates) {
        this.coordinates = coordinates;
    }

    /**
     * A method which returns station coordinates
     * @return station coordinates
     */
    public Pair<Double, Double> getCoordinates() {
        return this.coordinates;
    }

    /**
     * A function which allows to add bike to station
     * @param bikeID bike ID
     */
    public void addBike(int bikeID) {

    }

    /**
     * A function which allows to remove bike from the station
     * @param bikeID Bike ID
     */
    public void removeBike(int bikeID) {

    }
}
