package test.concurrency.bankaccount_reentrancelock;

public class BankAccountDemo {

	public static void main(String[] args) {
		BankAccount account = new BankAccount(1000.00, "12345-678");

		Thread trThread1 = new Thread() {
			public void run() {
				account.deposit(300.00);
				account.withdraw(50.00);
			}
		};

		Thread trThread2 = new Thread() {
			public void run() {
				account.deposit(203.75);
				account.withdraw(100.00);
			}
		};

		trThread1.start();
		trThread2.start();

	}

}
