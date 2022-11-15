/*
CSC205: 14891 / Monday 11am-1pm
Program: #2
Author: Stephen Arel
Description: Extends BankAccount and gives debit operations, a credit limit, means to apply interest to a CreditcardAccount balance,
             and a way to fetch/set a credit limit to a specific BankAccount/CreditcardAccount object.
 */

import java.text.DecimalFormat;

public class CreditcardAccount extends BankAccount {

    //------data
    private int creditLimit = 0;

    //------methods
    public boolean debit(int amount) //if the balance does not exceed the creditLimit, returns true and performs the transaction. otherwise returns false
    {
        if (this.balance - amount < -1*creditLimit)
        {
            return false;
        }
        else {
            this.balance -= amount;
            return true;
        }
    }

    public int getCreditLimit() //returns credit limit of CreditcardAccount object
    {
        return this.creditLimit;
    }

    public void setCreditLimit(int creditLimit) //sets creditLimit to specified amount for current object
    {
        this.creditLimit = creditLimit;
    }

    public void applyInterest() //applies interest to current object's balance if the balance is not positive
    {
        if (balance < 0)
        {
            double realInterestRate = (this.interestRate*0.01);
            balance += balance*realInterestRate;
        }
    }

    public String getAccountInfo() //returns info for current CreditcardAccount object
    {
        DecimalFormat df = new DecimalFormat("0.00");
        return    "Account type  : Creditcard" +
                "\nAccount #     : " + this.accountNumber +
                "\nBalance       : $" + df.format(((double)this.balance)*0.01) +
                "\nInterest rate : " + df.format(this.interestRate) +"%" +
                "\nCredit limit  : $" + df.format(((double)this.creditLimit)*0.01) + "";
    }







}
