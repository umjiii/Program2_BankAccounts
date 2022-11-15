/*
CSC205: 14891 / Monday 11am-1pm
Program: #2
Author: Stephen Arel
Description: Extends BankAccount to give debit operations, means to apply interest to a SavingsAccount balance, and means to fetch account info for a
             BankAccount/SavingsAccount object.
 */

import java.text.DecimalFormat;

public class SavingsAccount extends BankAccount {

    //------methods
    public boolean debit(int amount) //Returns false if an attempt to overdraw is made. otherwise performs the transaction and returns true.
    {
        if (balance - amount < 0) return false;
        else {
            balance -= amount;
            return true;
        }
    }

    @Override
    public void applyInterest() { //Applies and updates account balance with interest rate.
        double realInterestRate = (this.interestRate*0.01);
        this.balance += balance*realInterestRate;
        }


    public String getAccountInfo() //Returns account info as string
    {
        DecimalFormat df = new DecimalFormat("0.00");
        return    "Account type  : Savings" +
                "\nAccount #     : " + accountNumber +
                "\nBalance       : $" + df.format(((double)this.balance)*0.01) +
                "\nInterest rate : " + df.format(this.interestRate) +"%";
    }


}
