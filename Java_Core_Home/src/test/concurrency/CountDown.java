package test.concurrency;

public class CountDown {

	private int i;

	// way 1 - synchronized the whole method
	// public synchronized void countDown() {
	public void countDown() {
		// if we only want to synchronized the specific part of code block instead
		// synchronized the whole method we can do like that way. However you should not
		// LOCK / MUTEX the internal variable be cause each thread store its internal
		// variable in its own thread stack. We could lock that static method or
		// static object. In this case I do lock on this CountDown Object
		synchronized (this) {
			for (i = 0; i < 20; i++) {
				System.out.println(Thread.currentThread().getName() + " i = " + i);
			}
		}
	}
}
