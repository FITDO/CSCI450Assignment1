package bank;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import common.BankCMD;
import common.AccountManagerInterface;

/**
 * Class: AccountManager
 * 
 * A class implenting a AccountManagerInterface. Is responsible for giveing BankCMDs to the user.
 * Acts as an in-between for the client and the bankAccount.
 */
public class AccountManager extends UnicastRemoteObject implements AccountManagerInterface
{
    // variables
    private AccountImpl account_;
    private DepositCMD depositCMD_;
    private WithdrawCMD withdrawCMD_;

    // constuctor with an AccountImpl. Will create BankCMDS for the choosen account
    public AccountManager (AccountImpl account) throws RemoteException
    {
        super ();

        this.account_ = account;

        // creates cmds for the entered account
        this.depositCMD_ = new DepositCMD (account);
        this.withdrawCMD_ = new WithdrawCMD (account);
    }

    // AccountName getter
    @Override
    public String getAccountName () throws RemoteException
    {
        System.out.println ("Returning Account Name");
        return this.account_.getName ();
    }

    // AccountBalance getter
    @Override
    public double getAccountBalance () throws RemoteException
    {
        System.out.println ("Returning Account Balance");
        return this.account_.getBalance().getAmount ();
    }

    /**
     * Returns the WithdrawCmd for the client to use.
     */
    @Override
    public BankCMD createWithdrawCMD () throws RemoteException
    {
        System.out.println ("Returning WithdrawCMD");
        return this.withdrawCMD_;
    }

    /**
     * Returns the DepositCmd for the client to use.
     */
    @Override
    public BankCMD createDepositCMD () throws RemoteException
    {
        System.out.println ("Returning DepositCMD");
        return this.depositCMD_;
    }
}
