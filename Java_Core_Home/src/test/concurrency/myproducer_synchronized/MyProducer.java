package test.concurrency.myproducer_synchronized;

import java.util.List;
import java.util.Random;

public class MyProducer implements Runnable {
	private List<String> buffer;

	protected MyProducer(List<String> buffer) {
		this.buffer = buffer;
	}

	@Override
	public void run() {
		Random random = new Random();
		String[] nums = { "1", "2", "3", "4", "5", "6", "7", "8" };
		try {
			for (String num : nums) {
				System.out.println("Adding " + num);
				synchronized (buffer) {
					buffer.add(num);
				}
				Thread.sleep(random.nextInt(1000));
			}
			System.out.println("Adding EOF");
			synchronized (buffer) {
				buffer.add("EOF");
			}

		} catch (InterruptedException e) {
			System.out.println(e.getMessage() + "Producer has interrupted");
			e.printStackTrace();
		}
	}
}

// this demo is before java 1.5 java.util.concurrent and it still have its
// drawbacks
// the first drawback is that threads that are blocked waiting to execute
// synchronize code can't be interrupted once they're blocked their stuck there
// until they get the lock for the object the code is synchronizing on and if
// you think about it that can lead to problems
// the second drawback is that the synchronized block must be within the same
// method in other words we can't start a synchronized block in one method and
// end the synchronization block in another for obvious reasons
// the third drawback is that we can't test to see if an object's intrinsic lock
// is available or find out any other information about that lock also if the
// lock isn't available we can't timeout after we waited for the lock for a
// while when we reach the beginning of a synchronized block we can either get
// the lock and continue executing or block at that line of code until we get
// the lock
// the fourth drawback is that if multiple threads are waiting to get a lock
// it's not first come first served there isn't a set order in which the jvm
// will choose the next thread that gets the lock so the first thread that
// blocked could be the last thread to get the lock and ice versa
// ==> so instead of using synchronization we can prevent thread interference
// using classes that implement the java.util.concurrent locks.lock interface