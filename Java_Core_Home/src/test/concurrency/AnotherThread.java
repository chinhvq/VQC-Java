package test.concurrency;

public class AnotherThread extends Thread {

	@Override
	public void run() {
		System.out.println("Another Thread - UP " + currentThread().getName() );
		
		try {
			Thread.sleep(100);
		}catch (InterruptedException e) {
			System.out.println(e.getMessage() + " : " + currentThread().getName());
			return;
		}
		
		System.out.println("Another Thread - WAKE UP AGAIN " + currentThread().getName() );
	}

}
