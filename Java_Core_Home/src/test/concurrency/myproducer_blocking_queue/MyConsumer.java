package test.concurrency.myproducer_blocking_queue;

import java.util.concurrent.ArrayBlockingQueue;

public class MyConsumer implements Runnable {
	private ArrayBlockingQueue<String> buffer;

	protected MyConsumer(ArrayBlockingQueue<String> buffer) {
		this.buffer = buffer;
	}

	@Override
	public void run() {
		while (true) {
			synchronized (buffer) {
				try {
					if (buffer.isEmpty()) {
						continue;
					}

					if (buffer.peek().equals("EOF")) {
						System.out.println("Exiting");
						break;
					} else {
						System.out.println("Remove " + buffer.take() + "\t by \t" + Thread.currentThread().getName());
					}
				} catch (InterruptedException e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
				}
			}
		}
	}
}

// remember that we need to leave the end of file in the queue for the other
// consumer the peak method doesn't block when the queue is empty it returns
// null and that's why we still have to check to see whether the queue is empty

// We probably get a null pointer exception depending on timing in might or
// might not see this null pointer exception in the consumer code. Remember that
// thread safe means that we can be confident that our call to one of the class
// methods will complete before another thread can run a method in the class so
// the producer code only calls the put method and the code following the put
// doesn't depend on the result of the put. However in the consumer code what
// we're doing is we check to see if the queue is empty if it isn't we then peak
// at the next element but the consumer thread can be suspended between calling
// is empty and calling peak while it suspended the other consumer thread can
// run and take the next element from the queue when the suspended consumer
// thread runs again peak returns null and consequently we get a null pointer
// exception when we call the equals method and that's what happened in this
// scenario ==> That is why we need to put the code in the synchronized block
