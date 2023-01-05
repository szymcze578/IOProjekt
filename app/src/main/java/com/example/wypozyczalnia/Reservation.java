package com.example.wypoyczalnia;

/**
 * A class which represents bike reservation
 */
public class Reservation {

    /**
     * Reservation ID
     */
    private int reservationID;

    /**
     * Customer id
     */
    private int customerID;

    /**
     * Variable indicating whether the reservation has been executed
     */
    private Boolean reservationExecuted;

    /**
     * Reservation start date
     */
    private String startDate;

    /**
     * Reservation end date
     */
    private String endDate;

    /**
     * Default constructor method
     */
    public Reservation() {

    }

    /**
     * Parameterized constructor method
     * @param id reservation id
     * @param customerID customer id
     * @param reservationExecuted reservation executed
     * @param startDate reservation start date
     * @param endDate reservation end date
     */
    public Reservation(int id, int customerID, Boolean reservationExecuted, String startDate, String endDate) {
        this.reservationID = id;
        this.customerID = customerID;
        this.reservationExecuted = reservationExecuted;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * A method which sets reservation id
     * @param id reservation id
     */
    public void setReservationID(int id) {
        this.reservationID = id;
    }

    /**
     * A method which returns reservation id
     * @return reservationID
     */
    public int getReservationID() {
        return this.reservationID;
    }

    /**
     * A method which sets customerID
     * @param id customerID
     */
    public void setCustomerID(int id) {
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
     * A method which sets reservationExecuted
     * @param reservationExecuted reservation executed
     */
    public void setReservationExecuted(boolean reservationExecuted) {
        this.reservationExecuted = reservationExecuted;
    }

    /**
     * A method which returns reservation executed
     * @return reservationExecuted
     */
    public Boolean getReservationExecuted() {
        return this.reservationExecuted;
    }

    /**
     * A method which sets reservation start date
     * @param date reservation start date
     */
   public void setStartDate(String date) {
        this.startDate = date;
   }

    /**
     * A method which returns reservation start date
     * @return startDate
     */
   public String getStartDate() {
        return this.startDate;
   }

    /**
     * A method which sets reservation end date
     * @param date reservation end date
     */
   public void setEndDate(String date) {
        this.endDate = date;
   }

    /**
     * A method which returns reservation end date
     * @return endDate
     */
   public String getEndDate() {
        return this.endDate;
   }

    /**
     * A function which shows information about reservation
     */
    public void showReservation() {

    }

}
