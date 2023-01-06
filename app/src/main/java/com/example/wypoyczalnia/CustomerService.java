package com.example.wypoyczalnia;

/**
 * A class which represents customer service account
 */
public class CustomerService extends Account {

    /**
     * Default constructor method
     */
    public CustomerService() {

    }

    /**
     * Parameterized constructor method
     * @param id customer service id
     * @param email customer service email
     * @param phoneNumber customer service phone number
     */
    public CustomerService(int id, String email, String phoneNumber) {
        super(id, email, phoneNumber);
    }

    /**
     * A function which allows customer service to contact with a client
     */
    public void contactClient() {

    }

    /**
     * A function which allows customer service to unlock a bike
     */
    public void unlockBike() {

    }

    /**
     * A function which allows customer service to lock a bike
     */
    public void lockBike() {

    }

    /**
     * A function by which customer service can manage rental
     */
    public void manageRental() {

    }

    /**
     * A class which allows customer service to change complaint status
     */
    public void changeComplaintStatus() {

    }

}

