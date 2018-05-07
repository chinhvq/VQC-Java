package test.concurrency.myproducer_reentrantlock_finally;

import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class MyProducer implements Runnable {
	private List<String> buffer;
	private ReentrantLock bufferLock;

	protected MyProducer(List<String> buffer, ReentrantLock bufferLock) {
		this.buffer = buffer;
		this.bufferLock = bufferLock;
	}

	@Override
	public void run() {
		Random random = new Random();
		String[] nums = { "1", "2", "3", "4", "5", "6", "7", "8" };
		try {
			for (String num : nums) {
				System.out.println("Adding " + num);
				bufferLock.lock();
				try {
					buffer.add(num);
				} finally {
					bufferLock.unlock();
				}				
				Thread.sleep(random.nextInt(1000));
			}
			System.out.println("Adding EOF");
			bufferLock.lock();
			try {
				buffer.add("EOF");
			} finally {
				bufferLock.unlock();
			}
		} catch (InterruptedException e) {
			System.out.println(e.getMessage() + "Producer has interrupted");
			e.printStackTrace();
		}
	}
}

// if its thread is already holding a reentrant lock when it reaches the code
// that requires the same lock it can continue executing it doesn't have to
// obtain the lock again. ==> we're responsible for releasing the lock it won't
// happen automatically it doesn't happen automatically as it would when we use
// the synchronized block so the intrinsic locks we use with synchronized blocks
// are always released when the thread holding the lock exits the synchronized
// block so this is one drawback to using lock objects in that we have to manage
// this lock process themselves by making sure we call the .lock and the unlock
// ==> now when a thread calls the lock method it will either obtain the lock
// and continue executing OR it won't be able to get the lock because of thread
// already has it so in that case the thread that called lock will be suspended
// until it can get that lock if we forget to release the lock threads waiting
// for the lock will then start blocking forever

// if we get a maximum lock count exceeded message with an exception. we enter
// the loop and then we get the lock the lock is the first thing we do after
// entering the loop we then check to see if there's any data in the buffer if
// there is data we continue executing the loop code if there isn't we loop back
// and get the lock again because we're still holding the lock so you can you
// see what the problem is here we've never released the lock as such so the
// buffer lock.unlock code is never executed because that's only the moment
// written on line 84 after the while loop exits when we were using a
// synchronized block the lock was released because we exited the synchronized
// block when we return to the beginning of the while loop by just exiting the
// synchronized block that automatically released the lock but when we are using
// the lock object ourselves we skip over the unlock code and keep getting the
// lock again and again we are continuing which essentially is in calling this
// unlock code so re-entrant lock objects can keep track of how many times they
// locked if the thread gets the same lock twice it has to release it twice
// before another thread can get the lock