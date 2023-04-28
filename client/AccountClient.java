package client;


import java.rmi.Naming;
import java.text.DecimalFormat;
import java.util.Scanner;

import common.BankCMD;
import common.AccountManagerInterface;

/**
 * Class: AccountClient
 * 
 * A client to connect to a server using Java RMI. Gains access to a 
 * AccountMangerInterface to interact with a Bank account
 */
public class AccountClient 
{

    // variables
    private String server_;
    private AccountManagerInterface stub_;

    /**
     * Constructor with a char. Uses server_ to find a AccountMangerInterface.
     * @param num
     */
    public AccountClient (char num)
    {
        this.server_ = "//in-csci-rrpc0" + num + ".cs.iupui.edu:2323/BankCMDFactory";

        try
        {
            this.stub_ = (AccountManagerInterface)Naming.lookup (this.server_);
            System.out.println ("Found server object. Beggining client");
        }
        catch (Exception e)
        {
            System.out.println("Server Error: " + e.getMessage ());
            e.printStackTrace ();
        }
    }

    /**
     * Runs a while loop and a menu that gets input from the client.
     * @param scan
     */
    public void run (Scanner scan)
    {
        try
        {
            
            // variables that will be used to perform commands
            int choice = 4;
            double amount;
            BankCMD cmd;
            DecimalFormat df = new DecimalFormat("0.00");

            // keeps going until user asks to stop
            boolean keepgoing = true;
            while (keepgoing)
            {
                // Prompt user and get input
                System.out.println ("Hello "+ this.stub_.getAccountName () +". What would you like to do? \n0) Check Balance \n1) Withdraw Funds \n2) Deposit Funds \n3) Quit");
                choice = scan.nextInt();

                // evaluate input
                if (choice == 0)
                {
                    System.out.println("Current Balance: $" + df.format(this.stub_.getAccountBalance ()));
                }
                else if (choice == 1)
                {
                    /**
                     * User will enter a double then the stub will request a cmd
                     * amount will be entered into the cmd and executed. System will check
                     * if the deposit succeeded.
                     */
                    System.out.println ("How much would you like to withdraw?");
                    amount = scan.nextDouble ();
                    cmd = this.stub_.createWithdrawCMD ();

                    if (cmd.execute (amount))
                    {
                        System.out.println("Withdraw complete.");
                        System.out.println("New Balance: $" + df.format(this.stub_.getAccountBalance ()));
                    }
                    else
                    {
                        System.out.println("Withdraw was incomplete due to insufficent funds.");
                        System.out.println("Current Balance: $" + df.format(this.stub_.getAccountBalance ()));
                    }
                }
                else if (choice == 2)
                {
                    /**
                     * User will enter a double then the stub will request a cmd
                     * amount will be entered into the cmd and executed
                     */
                    System.out.println ("How much would you like to deposit?");
                    amount = scan.nextDouble ();
                    cmd = this.stub_.createDepositCMD ();
                    cmd.execute (amount);
                    System.out.println("New Balance: $" + df.format(this.stub_.getAccountBalance ()));
                }
                else if (choice == 3)
                {
                    // set keepgoing to false and exit system
                    System.out.println ("Exiting System.");
                    keepgoing = false;
                }
                else // choice == ???
                {
                    System.out.println ("Invalid Input.");
                }
            }

            System.out.println ("Goodbye.");
        }
        catch (Exception e)
        {
            System.out.println( "Client err :" + e.getMessage ());
            e.printStackTrace ();
            System.out.println ("Stopping Client.");
        }
    }

    // main
    public static void main (String[] args)
    {
        /**
         * Get what rrpc the server is running on and create a
         * Accountclient object and run it.
         */
        Scanner scan = new Scanner(System.in);
        System.out.println("What rrpc machine is your server on? \nEnter one number:");
        char num = scan.next().charAt(0);
        AccountClient client = new AccountClient(num);

        client.run (scan);

        scan.close ();
    }
}
