package common;

import java.rmi.*;

/**
 * Class: BankCMD
 * 
 * Provides an interface for interacting with a bank account over Java RMI.
 * Commands when executed will perform an action on the accounts balance.
 * Takes a double amount to perofram an action and returns a boolean if 
 * action succeeded.
 */
public interface BankCMD extends Remote
{
    public boolean execute (double amount) throws RemoteException;
}
