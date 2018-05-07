package test.concurrency.starvation;

public class StarvationDemo {
	private static Object lock = new Object();

	public static void main(String[] args) {

		Thread t1 = new Thread(new Worker(), "Priority 10");
		Thread t2 = new Thread(new Worker(), "Priority 8");
		Thread t3 = new Thread(new Worker(), "Priority 6");
		Thread t4 = new Thread(new Worker(), "Priority 4");
		Thread t5 = new Thread(new Worker(), "Priority 2");
		t1.setPriority(10);
		t2.setPriority(8);
		t3.setPriority(6);
		t4.setPriority(4);
		t5.setPriority(2);

		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
	}

	private static class Worker implements Runnable {
		private int runCount = 0;

		@Override
		public void run() {
			for (int i = 0; i <= 100; i++) {
				synchronized (lock) {
					System.out.format("%s runCount = %d\n", Thread.currentThread().getName(), runCount++);
				}
			}
		}
	}
}

// when starvation occurs it's not that threads will never progress because
// they'll never get a lock but that they rarely have the opportunity to run and
// progress so starvation often occurs due to thread priority when we assign a
// high priority priority as to a thread we are suggesting to the operating
// system that it should try and run the thread before other waiting threads
// remember that when we use synchronized blocks it's not first come first
// served when there's more than one thread waiting for a lock and that lock
// becomes available the operating system won't necessarily choose the thread
// that has been waiting the longest to run a thread can block on the lock first
// but that doesn't mean it will be the first thread to run when the lock
// becomes available so it's possible that the thread won't be able to run for a
// long time because other threads keep blocking on the lock and when the lock
// becomes available ==> the operating system chooses one of those other threads
// to run especially when one of the other threads has a higher priority than
// the first thread that blocked