package test.concurrency.livelock;

public class LiveLockDemo {

	public static void main(String[] args) {

		final Worker worker1 = new Worker("Worker 1", true);
		final Worker worker2 = new Worker("Worker 2", true);
		final ShareResource shareResource = new ShareResource(worker1);

		new Thread(new Runnable() {

			@Override
			public void run() {
				worker1.work(shareResource, worker2);
			}
		}).start();

		new Thread(new Runnable() {

			@Override
			public void run() {
				worker2.work(shareResource, worker1);
			}
		}).start();
	}

}

// a live lock is similar to a deadlock but instead of the threads been blocked
// they're actually constantly active and usually waiting for all the other
// threads to complete their tasks now since all the threads are waiting for
// others to complete one of them can actually progress so let's say that the
// thread a will loop until thread b complete its task and thread b will loop
// until thread a complete its task thread a and thread b can get into a state
// in which they're both looping and waiting for the other to complete that's
// actually what's called a live lock the threads will never progress but
// they're not actually blocked

// we're going to create 2 worker threads that share a resource when they see
// that the other threads active they're going to give the resource to the other
// thread and wait for it to finish using that resource