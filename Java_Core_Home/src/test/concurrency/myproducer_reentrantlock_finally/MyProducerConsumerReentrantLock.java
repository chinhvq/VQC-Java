package test.concurrency.myproducer_reentrantlock_finally;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class MyProducerConsumerReentrantLock {

	public static void main(String[] args) {
		List<String> buffer = new ArrayList<>();
		ReentrantLock bufferLock = new ReentrantLock();
		MyProducer procuder = new MyProducer(buffer, bufferLock);
		MyConsumer consumer1 = new MyConsumer(buffer, bufferLock);
		MyConsumer consumer2 = new MyConsumer(buffer, bufferLock);
		
		new Thread(procuder).start();;
		new Thread(consumer1).start();
		new Thread(consumer2).start();

	}

}
