package test.concurrency.myproducer_blocking_queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MyProducerConsumerExecutiveInterface {

	public static void main(String[] args) {
		ArrayBlockingQueue<String> buffer = new ArrayBlockingQueue<>(6);
		ExecutorService excutorService = Executors.newFixedThreadPool(3);

		MyProducer procuder = new MyProducer(buffer);
		MyConsumer consumer1 = new MyConsumer(buffer);
		MyConsumer consumer2 = new MyConsumer(buffer);

		excutorService.execute(procuder);
		excutorService.execute(consumer1);
		excutorService.execute(consumer2);

		// when we want to receive a value back from a thread that we are executing in
		// the background we can use the submit method now the submit method accepts a
		// Callable object which is very similar to a runnable object except that it can
		// return a value the value can be returned as an object of type future
		Future<String> future = excutorService.submit(new Callable<String>() {

			@Override
			public String call() throws Exception {
				System.out.println("===========================");
				System.out.println("Caller Class");
				return "This is callable result\n===========================";
			}
		});

		try {
			System.out.println(future.get());
		} catch (ExecutionException | InterruptedException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		// after we call the shutdown method is that it won't accept any new tasks now
		// this is an orderly shutdown if we wanted the service to shut down immediately
		// we call the shutdown now method as opposed to shut down in that case the
		// service will try to halt any remaining tasks and will also throw away any
		// tasks in this queue but there aren't any guarantees that it will be able to
		// do
		// that now it's possible that some threads will never terminate so it's always
		// best to try and shut down in an orderly fashion
		excutorService.shutdown();
	}
}

// the package contains thread-safe queues which a perfect for a producer
// consumer type applications for. All the queue classes implement the blocking
// queue interface. an array blocking queue now it processes elements in a FIFO
// order that stands for first in first out and that's exactly what we want here
// because our consumers is always remove the first element of the array list .
// array blocking queues are bounded and that means that we have to pass in the
// number of elements the arrays should be able to hold its capacity in other
// words to the constructor that don't grow like arrays lists