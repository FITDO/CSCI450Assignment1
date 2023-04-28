common: ./common/BankCMD.class ./common/AccountManagerInterface.class

client: common ./client/AccountClient.class

server: common ./bank/Money.class ./bank/AccountImpl.class ./bank/DepositCMD.class ./bank/WithdrawCMD.class  ./bank/AccountManager.class ./bank/RemoteAccount.class

./common/BankCMD.class: ./common/BankCMD.java
	javac ./common/BankCMD.java

./common/AccountManagerInterface.class: ./common/AccountManagerInterface.java
	javac ./common/AccountManagerInterface.java

./client/AccountClient.class: ./client/AccountClient.java
	javac ./client/AccountClient.java

./bank/Money.class: ./bank/Money.java
	javac ./bank/Money.java

./bank/AccountImpl.class: ./bank/AccountImpl.java
	javac ./bank/AccountImpl.java

./bank/DepositCMD.class: ./bank/DepositCMD.java
	javac ./bank/DepositCMD.java

./bank/WithdrawCMD.class: ./bank/WithdrawCMD.java
	javac ./bank/WithdrawCMD.java

./bank/AccountManager.class: ./bank/AccountManager.java
	javac ./bank/AccountManager.java

./bank/RemoteAccount.class: ./bank/RemoteAccount.java
	javac ./bank/RemoteAccount.java

serverRun: server ./bank/RemoteAccount.class
	java bank.RemoteAccount

clientRun: client
	java client.AccountClient