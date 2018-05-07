package test.concurrency;

public class MyRunable implements Runnable{

	@Override
	public void run() {
		System.out.println("RUNABLE - WOOP " + Thread.currentThread().getName());
	}

}
