package test.concurrency.bankaccount;

public class BankAccount {
	private double balance;
	private String accountNumber;
	
	protected BankAccount(double balance, String accountNumber) {
		this.balance = balance;
		this.accountNumber = accountNumber;
	}
	
	
	
//	protected synchronized void deposit(double amount) {
//		balance += amount;
//	}
//	
//	protected synchronized void withdraw(double amount) {
//		balance -= amount;
//	}
	
	protected double getBalance() {
		return balance;
	}

	protected String getAccountNumber() {
		return accountNumber;
	}
	
	protected void printAccountNumber() {
		System.out.println("Account Number = " + accountNumber);
	}


	protected void deposit(double amount) {
		synchronized (this) {
			balance += amount;
		}		
	}
	
	protected void withdraw(double amount) {
		synchronized (this) {
			balance -= amount;
		}		
	}
}
