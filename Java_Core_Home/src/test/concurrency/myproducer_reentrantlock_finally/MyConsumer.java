package test.concurrency.myproducer_reentrantlock_finally;

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
		while (true) {
			bufferLock.lock();
			try {
				if (buffer.isEmpty()) {
					continue;
				}
				if (buffer.get(0).equals("EOF")) {
					System.out.println("Exiting");
					break;
				} else {
					System.out.println("Remove " + buffer.remove(0));
				}
			} finally {
				bufferLock.unlock();
			}
		}
	}
}
