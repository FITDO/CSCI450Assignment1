package bank;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.DecimalFormat;

import common.BankCMD;

/**
 * Class: WithdrawCMD
 * 
 * A object to use with Java RMI. This object is created by the server,
 * then sent to the client when requested, and exected once a double
 * has been entered. Used to withdraw an Money amount from a Account.
 */
public class WithdrawCMD extends UnicastRemoteObject implements BankCMD
{

    // actions will be performed to the AccountImpl ai_
    private AccountImpl ai_;

    //Constructor with a AccountImpl
    public WithdrawCMD (AccountImpl ai) throws RemoteException
    {
        super ();
        this.ai_ = ai;
    }

    /**
     * Will try to withdraw an entered amount. Checks if withdraw if possible
     * 
     * @return  true    if withdraw worked
     * @return  false   if withdraw failed
     */
    public boolean execute (double amount) throws RemoteException
    {
        if (this.ai_.getBalance().getAmount () >= amount)
        {
            DecimalFormat df = new DecimalFormat("0.00");
            this.ai_.withdraw (amount);
            System.out.println ("Withdraw of $" + String.valueOf (df.format (amount)) + " completed.");
            return true;
        }
        else // balance < amount
        {
            System.out.println ("Withdraw of $" + String.valueOf (amount) + " failed.");
            return false;
        }
    }
}
