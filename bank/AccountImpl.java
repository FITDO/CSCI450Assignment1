package bank;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Class: AccountImpl
 * 
 * A account that manages a name and Money balance.
 * Also responsible for loading a money object from a
 * .ser file.
 */
public class AccountImpl 
{

    // variables
    private String name_;
    private Money balance_;

    // default constructor
    public AccountImpl ()
    {
        this.name_ = "NULL";
        this.balance_ = new Money();
    }

    // constructor with a string
    public AccountImpl (String name)
    {
        this.name_ = name;
        this.loadMoney ();
    }

    // Name setter
    public void setName (String name) 
    {
        this.name_ = name;
    }

    // Name getter
    public String getName () 
    {
        return this.name_;
    }

    // Balance setter
    public void setBalance (Money balance) 
    {
        this.balance_ = balance;
    }

    // Balance getter
    public Money getBalance () 
    {
        return this.balance_;
    }

    /**
     * Tries to add an amount entered to the balance
     * Has a catch for exception handling. If everthing 
     * works out save the money object to a .ser file
     * 
     * @param amount
     */
    public void deposit (Double amount)
    {
        try
        {
            this.balance_.addAmount (amount);
            this.saveMoney ();
        }
        catch (Exception e)
        {
            e.getMessage ();
            e.printStackTrace ();
        }
    }

    /**
     * Tries to subtract an amount entered to the balance
     * Has a catch for exception handling. If everthing 
     * works out save the money object to a .ser file
     * 
     * @param amount
     */
    public void withdraw (Double amount)
    {
        try
        {
            this.balance_.subtractAmount (amount);
            this.saveMoney ();
        }
        catch (Exception e)
        {
            e.getMessage ();
            e.printStackTrace ();
        }
    }

    /**
     * Writes the Money object to a .ser file and prints messages
     * to inform the server
     */
    private void saveMoney ()
    {
        try
        {
            if (this.name_ != "NULL")
            {
                String filePath = "bank/AccountBalances/" + this.name_ + ".ser";
                FileOutputStream fileOut = new FileOutputStream (filePath);
                ObjectOutputStream out = new ObjectOutputStream (fileOut);
                out.writeObject (this.balance_);
                out.close ();
                fileOut.close ();
                System.out.println ("Balance was successfully saved to " + filePath);
            }
        }
        catch (Exception e)
        {
            System.out.print ("Serialized Data Error: " + e.getMessage ());
            e.printStackTrace ();
        }
    }

    /**
     * Loads the Money object from a .ser object. If .ser file cannot be
     * found just loads a new Money object with a balance of $0.00
     */
    private void loadMoney ()
    {
        try
        {
            String filePath = "bank/AccountBalances/" + this.name_ + ".ser";
            FileInputStream fileIn = new FileInputStream (filePath);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            this.balance_ = (Money) in.readObject();
            fileIn.close ();
            in.close ();
            System.out.println ("Balance was successfuly retrieved from " + filePath);
        }
        catch (Exception e)
        {
            System.out.println (e.getMessage ());
            this.balance_ = new Money ();
        }
    }
}
