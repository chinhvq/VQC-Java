package test.concurrency.fairlock;

import java.util.concurrent.locks.ReentrantLock;

public class FairLockDemo {
	private static ReentrantLock reentrantLock = new ReentrantLock(true);

	public static void main(String[] args) {

		Thread t1 = new Thread(new Worker(), "Priority 10");
		Thread t2 = new Thread(new Worker(), "Priority 8");
		Thread t3 = new Thread(new Worker(), "Priority 6");
		Thread t4 = new Thread(new Worker(), "Priority 4");
		Thread t5 = new Thread(new Worker(), "Priority 2");

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
				reentrantLock.lock();
				try {
					System.out.format("%s runCount = %d\n", Thread.currentThread().getName(), runCount++);
				} finally {
					reentrantLock.unlock();
				}
			}
		}
	}
}

// reentrant lock implementation allows us to create fair locks not all lock
// implementations do that the interface doesn't dictate that locks have to be
// fair. ReentrantLock(true) -> whether it's a fair lock so first come first
// served or not

// a few important notes about using a fair reentrant lock only fairness in
// acquiring the lock is guaranteed not fairness in threads scheduling so it's
// possible that the thread that gets the lock will execute a task that takes a
// long time so when using fair locks it's possible for threads to still have to
// wait a long time to run the only thing a fair lock guarantees is the first
// come first served ordering for getting the lock
// SECONDLY the try lock method doesn't honor the fairness settings so it will
// not be first come first served
// LASTLY when using fair locks with a lot of threads keep in mind that
// performance will be impacted to ensure fairness there has to be an extra
// layer of processing that manages which thread gets the lock so that can
// ultimately slow things down when there's a lot of threads competing for that
// lock

// ===> no thread has to twidle his thumbs waiting for another thread to count
// all the way to a hundred so you might be wondering at this point why you'd
// want to use synchronized blocks in that case ??? you know from the
// applications architecture that it will be rare for more than one thread to be
// blocked on a lock at a time then starvation may not actually be an issue for
// your code in that particular application also since using fair locks can
// impact performance you might actually decide that starvation will be the
// lesser of two you might actually decide that starvation will be the lesser of
// two evils so depending on the tasks that the threads are performing it might
// actually so depending on the tasks that the threads are performing it might
// actually not matter if a thread is waiting for a long time <> for example you
// might have multiple threads pulling data of the same queue and you don't
// actually care which threads pull up the data as long as it's done quickly so
// in that situation using synchronize blocks would be preferable