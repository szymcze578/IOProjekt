package com.example.wypoyczalnia;

import com.example.wypoyczalnia.Account;

/**
 * A class which represents customer account
 */
public class Customer extends Account {

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
    public Customer(int id, String email, String phoneNumber) {
        super(id, email, phoneNumber);
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
    void rentBike() {

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
