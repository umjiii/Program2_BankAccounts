/*
CSC205: 14891 / Monday 11am-1pm
Program: #2
Author: Stephen Arel
Description: Extends BankAccount to include various methods to support debit operations, an overdraft-fee function (method), a method to apply interest rates
             to CheckingAccount balances, and a String method to print the account info for a BankAccount/CheckingAccount object.
 */


import java.text.DecimalFormat;

public class CheckingAccount extends BankAccount {
    //------data
    private int overdraftFee = 0;

    //------methods
    public boolean debit(int amount) //returns true even if balance is overdrafted. overdraft fee applied to balance if it runs negative.
    {
        this.balance -= amount;
        if (balance < 0) balance -= (overdraftFee);
        return true;
    }

    public int getOverdraftFee() //returns current overdraft fee of CheckingAccount object.
    {
        return this.overdraftFee;
    }

    public void setOverdraftFee(int overdraftFee){ //sets current overdraft fee for current CheckingAccount object.
        this.overdraftFee = overdraftFee;
    }

    public void applyInterest() //if the balance is not negative, applies current interest rate to current CheckingAccount balance.
    {
        if (this.balance > 0)
        {
            double realInterestRate = (this.interestRate*0.01);
            this.balance += this.balance*realInterestRate;
        }
    }

    public String getAccountInfo() //returns info for CheckingAccount object
    {
        DecimalFormat df = new DecimalFormat("0.00");
        return    "Account type  : Checking" +
                "\nAccount #     : " + this.accountNumber +
                "\nBalance       : $" + df.format(((double)this.balance)*0.01) +
                "\nInterest rate : " + df.format(this.interestRate) +"%" +
                "\nOverdraft fee : $" + df.format(((double)this.overdraftFee)*0.01);
    }



}
