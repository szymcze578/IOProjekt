package com.example.wypoyczalnia;

import com.example.wypoyczalnia.Account;

/**
 * A class which represents serviceman account
 */
public class Serviceman extends Account {

    /**
     * Default constructor method
     */
    public Serviceman(){

    }

    /**
     * Parameterized constructor method
     * @param id serviceman id
     * @param email serviceman email
     * @param phoneNumber serviceman phone number
     */
    public Serviceman(int id, String email, String phoneNumber) {
        super(id,email,phoneNumber);
    }

    /**
     * A function which allows serviceman to contact customer service
     */
    public void contactCustomerService() {

    }

    /**
     * A function which allows serviceman to add a bike
     */
    public void addBike() {

    }

    /**
     * A function which allows serviceman to take a bike
     */
    public void takeBike() {

    }

    /**
     * A function which allows serviceman to change bike technical status
     */
    public void changeBikeCondition() {

    }

}
