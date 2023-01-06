package com.example.wypozyczalnia;

/**
 * A class which represents complaint
 */
public class Complaint {

    /**
     * Complaint ID
     */
    private int complaintID;

    /**
     * Customer ID
     */
    private int customerID;

    /**
     * Date when complaint was made
     */
    private String date;

    /**
     * Complaint description
     */
    private String description;

    /**
     * Complaint status
     */
    private Enum complaintStatus; //todo: set, get, constructor

    /**
     * Default constructor method
     */
    public Complaint() {

    }

    /**
     * Parameterized constructor method
     * @param id complaintID
     * @param customerID customerID
     * @param date date
     * @param desc decsription
     */
    public Complaint(int id, int customerID, String date, String desc) {
        this.complaintID = id;
        this.customerID = customerID;
        this.date = date;
        this.description = desc;
    }

    /**
     * A method which sets complaintID
     * @param id complaintID
     */
    public void setComplaintID(int id) {
        this.complaintID = id;
    }

    /**
     * A method which returns complaintID
     * @return complaintID
     */
    public int getComplaintID() {
        return this.complaintID;
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
     * A method which sets complaint date
     * @param date date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * A method which returns complaint date
     * @return complaint date
     */
    public String getDate() {
        return this.date;
    }

    /**
     * A method which sets complaint description
     * @param desc complaint description
     */
    public void setDescription(String desc) {
        this.description = desc;
    }

    /**
     * A method which returns complaint description
     * @return complaint description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * A function which shows information about complaint
     */
    public void showComplaint() {

    }
}
