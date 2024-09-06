package HW1;

/**
 * A simple bank account class with basic methods.
 * 
 * @author Trevor Swan
 * @version CSDS233 - Fall 2024
 */
public class BankAccount {
    // The account's number and balance, as specified
    private final String accountNumber;
    private double balance;

    /**
     * Creates a bank account with a given account number and starting balance.
     * 
     * @param accountNumber
     * @param balance
     */
    public BankAccount(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.setBalance(balance);
    }

    // Boilerplate
    public String getAccountNumber() {
        return this.accountNumber;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * A method to get the current balance
     * 
     * @return The account's current balance.
     */
    public double getBalance() {
        return this.balance;
    }
    
    /**
     * Adds a specified amount of money to the account's balance.
     * 
     * @param amount The amount to be added
     */
    public void deposit(double amount) {

    }

    /**
     * Removes a specified amount of money from the account's balance.
     * 
     * @param amount The amount to be subtracted
     * @return a boolean value indicated whether the withdrawal was successful or not
     */
    public boolean withdraw(double amount) {
        return true;
    }
}
