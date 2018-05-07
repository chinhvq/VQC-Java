package test.concurrency.myproducer_reentrantlock_trylock;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class MyConsumer implements Runnable {
	private List<String> buffer;
	private ReentrantLock bufferLock;

	protected MyConsumer(List<String> buffer, ReentrantLock bufferLock) {
		this.buffer = buffer;
		this.bufferLock = bufferLock;
	}

	@Override
	public void run() {
		int counter = 0;
		while (true) {
			if (bufferLock.tryLock()) {
				try {
					if (buffer.isEmpty()) {
						continue;
					}
					System.out.println("Count = " + String.format("%,d", counter));
					counter = 0;
					if (buffer.get(0).equals("EOF")) {
						System.out.println("Exiting");
						break;
					} else {
						System.out.println("Remove " + buffer.remove(0) + "\t by \t" + Thread.currentThread().getName());
					}
				} finally {
					bufferLock.unlock();
				}
			} else {
				counter++;
			}
		}
	}
}

// 1. a thread can actually test to see whether a lock is available using the
// try lock method so if lock is available the thread will acquire the lock and
// continue executing if it's not available the thread won't block and
// alternatively can execute some different code now for our particular
// application .
// 2. what we can also do when we use try lock is we can set a time out period
// so if the thread still waiting for the lock when the timeout expires it
// interrupted and continues executing so to do that we just have to pass the
// time out period and the time unit that were using to the method
// 3. we might want to see how many threads are waiting for a lock before we try
// to acquire it if they're our you know already a lot of threads waiting we can
// run other code instead of blocking and wait for the lock
// 4. the reentrant lock constructor accepts a fairness parameter when it's set
// to true the reentrant lock class will try to be fair by giving them the lock
// 2 whichever thread has been waiting for it the longest again if the thread
// comes along and finds out that a hundred threads are already waiting and it
// knows the lock is a fair lock it might choose to terminate instead of
// blocking on the lock when using a re-entrant lock
// 5. we can also check for the number of threads waiting for the lock by
// calling the get queued length method 