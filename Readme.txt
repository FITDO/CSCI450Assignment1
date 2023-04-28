This was made as Assignment 1 in Principles of Software Engineering. It ustilizes Java RMI to communicate between machines



How to run:

    There are a few things to set up first even before you call a make command

    How to set up the server:

        1) Connect to any rrpc machine with ssh

        2) Make sure no other rmiregistry is running if it is then kill it

        3) Type "rmiregistry 2323 &"
        
        4) Type "make serverRun" this will create all classes needed by the server and run RemoteAccount

        5) Next it will ask what machine you are on.
            If you are on rrpc1 type '1'
            If you are on rrpc2 type '2'
            etc..
        
        6) If everything works you should see "Server is Ready." You are ready to go

        7) Once you are done with the server you may kill it

    How to set up the client:

        1) Make sure you have a server set up

        2) Connect to any rrpc machine with ssh

        3) Type "make clientRun" this will create all classes needed by the client and run Accountclient

        4) Next it will ask which rrpc machine the server is set up on (Not what rrpc machine you are currently using).
            If you set up the server on rrpc1 type '1'
            If you set up the server on rrpc2 type '2'
            etc..
        
        5) The client will run and you may interact with the bank account 