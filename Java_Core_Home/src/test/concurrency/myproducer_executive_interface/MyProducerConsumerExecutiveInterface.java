package test.concurrency.myproducer_executive_interface;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.locks.ReentrantLock;

public class MyProducerConsumerExecutiveInterface {

	public static void main(String[] args) {
		List<String> buffer = new ArrayList<>();
		ReentrantLock bufferLock = new ReentrantLock();
		// we can create several different types of executor services based on the type
		// of thread pool we want the service to use the different types of thread polls
		ExecutorService excutorService = Executors.newFixedThreadPool(3);

		MyProducer procuder = new MyProducer(buffer, bufferLock);
		MyConsumer consumer1 = new MyConsumer(buffer, bufferLock);
		MyConsumer consumer2 = new MyConsumer(buffer, bufferLock);

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
