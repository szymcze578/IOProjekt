package com.example.wypoyczalnia;

/**
 *   Abstract class which represents user account
 */
public abstract class Account {

    /**
     * User id
     */
    private int accountID;

    /**
     * User email
     */
    private String email;

    /**
     * User phone number
     */
    private String phoneNumber;

    /**
     * User account type - app user, customer service or serviceman
     */
    private Enum accountType; // todo : getAccountType(), setAccountType();

    /**
     * Default constructor method
     */
    public Account() {
    }

    /**
     * Parameterized constructor method
     * @param id accountID
     * @param email email
     * @param phoneNumber phoneNumber
     */
    public Account(int id, String email, String phoneNumber) {
        this.accountID = id;
        this.email = email;
        this.phoneNumber = phoneNumber;
        //todo: ENUM
    }

    /**
     * A method which sets accountID
     * @param id account id
     */
    public void setID(int id) {
        this.accountID = id;
    }

    /**
     * A method which returns accountID
     * @return  accountID
     */
    public int getID() {
        return this.accountID;
    }

    /**
     * A method which sets email
     * @param e email
     */
    public void setEmail(String e) {
        this.email = e;
    }

    /**
     * A method which returns email
     * @return email
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * A method which sets phone number
     * @param number phone number
     */
    public void setPhoneNumber(String number) {
        this.phoneNumber = number;
    }

    /**
     * A method which returns phone number
     * @return phoneNumber
     */
    public String getPhoneNumber() {
        return this.phoneNumber;
    }


    /**
     * A method which authorizes user account
     */
    public void authorizeUser() {

    }

    /**
     * A method by which user logs in to the application
     */
    public void signIn() {

    }

    /**
     * A method by which user registers to the application
     */
    public void signUp() {

    }


}
