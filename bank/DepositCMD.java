package bank;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.DecimalFormat;

import common.BankCMD;

/**
 * Class: DepositCMD
 * 
 * A object to use with Java RMI. This object is created by the server,
 * then sent to the client when requested, and exected once a double
 * has been entered. Used to deposit an Money amount from a Account.
 */
public class DepositCMD extends UnicastRemoteObject implements BankCMD
{
    // actions will be performed to the AccountImpl ai_
    private AccountImpl ai_;

    //Constructor with a AccountImpl
    public DepositCMD (AccountImpl ai) throws RemoteException
    {
        super ();
        this.ai_ = ai;
    }

    /**
     * Will try to deposit an entered amount. Will return true if it works out
     * 
     * @return  true    if deposit worked
     */
    public boolean execute (double amount) throws RemoteException
    {
        DecimalFormat df = new DecimalFormat("0.00");
        this.ai_.deposit (amount);
        System.out.println ("Deposit of $" + String.valueOf (df.format (amount))+ " completed.");
        return true;
    }    
}
