package test.concurrency.myproducer_synchronized;

import java.util.ArrayList;
import java.util.List;

public class MyProducerConsumerSynchronize {

	public static void main(String[] args) {
		List<String> buffer = new ArrayList<>();
		MyProducer procuder = new MyProducer(buffer);
		MyConsumer consumer1 = new MyConsumer(buffer);
		MyConsumer consumer2 = new MyConsumer(buffer);
		
		new Thread(procuder).start();;
		new Thread(consumer1).start();
		new Thread(consumer2).start();

	}

}
