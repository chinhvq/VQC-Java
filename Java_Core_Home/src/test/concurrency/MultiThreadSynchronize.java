package test.concurrency;

public class MultiThreadSynchronize {

	public static void main(String[] args) {

		CountDown countDown = new CountDown();
		CountDownThread t1 = new CountDownThread(countDown);
		t1.setName("Thread 1");
		CountDownThread t2 = new CountDownThread(countDown);
		t1.setName("Thread 2");

		t1.start();
		t2.start();
	}
}


