package bank;

import java.rmi.*;
import java.util.Scanner;

import common.AccountManagerInterface;

/**
 * Class: RemoteAccount
 * 
 * A class used by the server to run Java RMI
 */
public class RemoteAccount 
{

    // variables
    // server_ is where the rmi object is stored
    private String server_;
    private AccountImpl account_;

    // Constructor with char
    RemoteAccount (char num)
    {
        this.server_ = "//in-csci-rrpc0" + num + ".cs.iupui.edu:2323/BankCMDFactory";
        this.account_ = new AccountImpl ("John Raymond");
    }

    // main
    public static void main(String[] args)
    {
        /**
         * Prompts the user to enter the number of the rrpc machine they are on
         * Once entered create a RemoteAccount Object and run.
         */
        Scanner scan = new Scanner(System.in);
        System.out.println("What rrpc machine are you on? \nEnter one number:");
        char num = scan.next().charAt(0);
        RemoteAccount server = new RemoteAccount(num);
        server.run ();
        scan.close ();
    }

    /**
     * Creates an AccounntManagerInterface object and binds it to the 2323 port
     * Catchs any Exceptions that occur
     */
    public void run ()
    {
        try
        {
            AccountManagerInterface remote_obj = new AccountManager (this.account_);
            
            Naming.rebind (this.server_ , remote_obj);
			System.out.println (this.server_);
			System.out.println ("Server is ready");

        }
        catch (Exception e)
        {
            System.out.println("Server err: " + e.getMessage ());
            e.printStackTrace ();
        }
    }
}