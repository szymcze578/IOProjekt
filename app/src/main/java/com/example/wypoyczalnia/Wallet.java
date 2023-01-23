package com.example.wypoyczalnia;

import java.io.Serializable;

/**
 * A class which represents user wallet
 */
@SuppressWarnings("serial")
public class Wallet implements Serializable {

    /**
     * Current amount of money
     */
    private double funds;

    /**
     * Default constructor method
     */
    public Wallet() {

    }

    /**
     * Constructor method
     * @param funds total amount of money
     */
    public Wallet(double funds) {
        this.funds = funds;
    }

    /**
     * A method which sets funds
     * @param funds funds
     */
    public void setFunds(double funds) {
        this.funds = funds;
    }

    /**
     * A method which returns funds
     * @return funds
     */
    public double getFunds() {
        return this.funds;
    }

    /**
     * A method which adds the money to the wallet
     * @param f amount of money which should be added
     */
    public void addFunds(double f){
        this.funds += f;
    }

    /**
     * A method which takes money from the user wallet
     * @param f amount of money which should be taken
     */
    public void takeFunds(double f) {
        this.funds -= f;

    }

    /**
     * A method which shows current amount of money in user wallet
     */
    public void showFunds() {

    }

}
