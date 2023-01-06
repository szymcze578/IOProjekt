package com.example.wypoyczalnia;

/**
 * A class which represents bike rent
 */
public class Hire {
    /**
     * Time when hire was made
     */
    private int time;

    /**
     * Total time of the hire
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
    private int payment;

    /**
     * Date when the hire was made
     */
    private String date;

    /**
     * Default constructor method
     */
    public Hire() {

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
    public void setPayment(int payment) {
        this.payment = payment;
    }

    /**
     * A method which returns payment
     * @return payment
     */
    public int getPayment() {
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
     * A function which shows information about the hire
     */
    public void showHire() {

    }

}
