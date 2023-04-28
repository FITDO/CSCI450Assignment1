package common;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Class: AccountManagerInterface
 * 
 * Provides a interface to be used over java RMI. Allows the client
 * to get information and perform actions on a bank account.
 */
public interface AccountManagerInterface extends Remote
{

    public String getAccountName () throws RemoteException;

    public double getAccountBalance () throws RemoteException;

    public BankCMD createWithdrawCMD () throws RemoteException;

    public BankCMD createDepositCMD () throws RemoteException;
}
