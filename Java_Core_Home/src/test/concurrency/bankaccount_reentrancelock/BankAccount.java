package test.concurrency.bankaccount_reentrancelock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {
	private double balance;
	private String accountNumber;
	private ReentrantLock lock;

	protected BankAccount(double balance, String accountNumber) {
		this.balance = balance;
		this.accountNumber = accountNumber;
		lock = new ReentrantLock();
	}

	protected double getBalance() {
		return balance;
	}

	protected String getAccountNumber() {
		return accountNumber;
	}

	protected void printAccountNumber() {
		System.out.println("Account Number = " + accountNumber);
	}

	// use try finally with reentranceLock
	// protected void deposit(double amount) {
	// lock.lock();
	// try {
	// balance += amount;
	// } finally {
	// lock.unlock();
	// }
	// }
	//
	// protected void withdraw(double amount) {
	// lock.lock();
	// try {
	// balance -= amount;
	// } finally {
	// lock.unlock();
	// }
	// }

	// use tryLock() with reEntrancelock
	// protected void deposit(double amount) {
	// try {
	// if (lock.tryLock(1000, TimeUnit.MILLISECONDS)) {
	// try {
	// balance += amount;
	// } finally {
	// lock.unlock();
	// }
	// } else {
	// System.out.println("Cannot get the lock");
	// }
	// } catch (InterruptedException e) {
	// e.printStackTrace();
	// }
	//
	// }
	//
	// protected void withdraw(double amount) {
	// try {
	// if (lock.tryLock(1000, TimeUnit.MILLISECONDS)) {
	// try {
	// balance -= amount;
	// } finally {
	// lock.unlock();
	// }
	// } else {
	// System.out.println("Cannot acquire the lock");
	// }
	// } catch (InterruptedException e) {
	// e.printStackTrace();
	// }
	// }

	// use tryLock() with reEntrancelock with status tracking
	protected void deposit(double amount) {
		boolean status = false;
		try {
			if (lock.tryLock(1000, TimeUnit.MILLISECONDS)) {
				try {
					balance += amount;
					status = true;
				} finally {
					lock.unlock();
				}
			} else {
				System.out.println("Cannot acquire the lock (deposit) - " + Thread.currentThread().getName());
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Deposit Transaction status is " + status + " - " + Thread.currentThread().getName());
	}

	protected void withdraw(double amount) {
		boolean status = false;
		try {
			if (lock.tryLock(1000, TimeUnit.MILLISECONDS)) {
				try {
					balance -= amount;
					status = true;
				} finally {
					lock.unlock();
				}
			} else {
				System.out.println("Cannot acquire the lock withdraw - " + Thread.currentThread().getName());
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Withdraw Transaction status is " + status + " - " + Thread.currentThread().getName());
	}
}
