/*
CSC205: 14891 / Monday 11am-1pm
Program: #2
Author: Stephen Arel
Description: Abstract class that provides the necessary methods (some abstract) to give credit/debit operations, account info (number, balance,
             interest rates), and means to fetch this info for the child classes CheckingAccount, CreditcardAccount, and SavingsAccount.
 */

public abstract class BankAccount {

    //data
    protected String accountNumber = "0000-0000-0000-0000";
    protected double interestRate = 0.00;
    protected int balance = 0;


    //------methods
    public boolean credit(int amount) //this method allows us to add funds to a given account balance of any type.
    {
        balance += amount;

        return true;
    }

    public abstract boolean debit(int amount); //returns true if a transaction can be made with the specified amount.

    public int getBalance()
    {
        return balance;
    } //returns the balance of the current account object.

    public String getAccountNumber() //returns the account number
    {
        return this.accountNumber;
    }

    public void setAccountNumber(String accountNumber) //sets the account number for the current account object.
    {
        this.accountNumber = accountNumber;
    }

    public double getInterestRate()
    {
        return interestRate;
    } //returns the interest rate of the current account object.

    public void setInterestRate(double interestRate) //sets the interest rate for the current account object.
    {
        this.interestRate = interestRate;
    }

    public abstract void applyInterest(); //applies interest to the current account object, modified by the differing conditions of each child class.

    public abstract String getAccountInfo(); //returns the account info of the current account object, modified by differing factors of each child class.

}
