package com.example.wypoyczalnia;

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
