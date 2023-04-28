package bank;

import java.io.Serializable;
import java.text.DecimalFormat;

/**
 * Class: Money
 * 
 * A wrapper around a double. Checks if user is interacting with 
 * double in a reasonable way. Throws exceptions for bad inputs.
 */
public class Money implements Serializable
{

    // Variables
    private DecimalFormat df_;
    private double amount_;

    // default Constructor
    public Money ()
    {
        this.df_ = new DecimalFormat ("0.00");
        this.amount_ = 0.00;
    }

    // constructor that takes defualt amount
    public Money (Double amount) throws Exception
    {
        this.df_ = new DecimalFormat ("0.00");
        this.setAmount (amount);
    }

    // returns amount
    public double getAmount ()
    {
        return this.amount_;
    }

    /**
     * Takes a double as amount. Shortens double to 2 decimal places
     * and checks double is less than 0. Once confirmed sets amount 
     * entered as amount_.
     * 
     * @param amount
     * @throws Exception
     */
    public void setAmount (Double amount) throws Exception
    {
        if (amount > 0.0)
        {
            this.amount_ =  Double.valueOf (df_.format (amount));
        }
        else // amount <= 0
        {
            throw new Exception ("Negative amount attempted");
        }
    }

    /**
     * Takes a double as amount. Shortens double to 2 decimal places
     * and checks double is less than 0. Once confirmed adds amount
     * to current amount_.
     * @param amount
     * @throws Exception
     */
    public void addAmount (Double amount) throws Exception
    {
        if (amount > 0.0)
        {
            this.amount_ += Double.valueOf (df_.format (amount));
        }
        else // amount <= 0
        {
            throw new Exception ("Negative amount attempted");
        }
    }

    /**
     * Takes a double as amount. Shortens double to 2 decimal places
     * and checks double is less than 0. Once confirmed subtracts 
     * amount to current amount_.
     * @param amount
     * @throws Exception
     */
    public void subtractAmount (Double amount) throws Exception
    {
        if (amount > 0.0)
        {
            // Makes sure new amount wont ever be negative
            if (this.amount_ >= amount)
            {
                this.amount_ -= Double.valueOf (df_.format (amount));
            }
            else // amount_ < amount
            {
                this.amount_ = 0.00;
            }
        }
        else // amount <= 0
        {
            throw new Exception ("Negative amount attempted");
        }
    }
}
