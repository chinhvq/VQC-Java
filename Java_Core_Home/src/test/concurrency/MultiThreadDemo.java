package test.concurrency;

public class MultiThreadDemo {

	public static void main(String[] args) {

		// demo of thread inference ==> race condition whenever two or more thread
		// writing the share resource ==> this happens because t1 & t2 share the same
		// countDown object in head. As the result, event t1 and t2 has its own thread
		// stack however the thread interfere still happen
		CountDown countDown = new CountDown();
		CountDownThread t1 = new CountDownThread(countDown);
		t1.setName("Thread 1");
		CountDownThread t2 = new CountDownThread(countDown);
		t1.setName("Thread 2");

		t1.start();
		t2.start();
	}

}

