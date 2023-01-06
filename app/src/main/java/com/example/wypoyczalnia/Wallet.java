package com.example.wypoyczalnia;

/**
 * A class which represents user wallet
 */
public class Wallet {

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
    public Wallet(int funds) {
        this.funds = funds;
    }

    /**
     * A method which sets funds
     * @param funds funds
     */
    public void setFunds(int funds) {
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
