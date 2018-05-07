package test.concurrency;

import java.util.Random;

public class ComsumerProducer {

	public static void main(String[] args) {

		Message message = new Message();
		(new Thread(new Writer(message))).start();
		(new Thread(new Reader(message))).start();
	}

}

class Message {
	private String message;
	private boolean isEmpty = true;

	public synchronized String read() {
		while (isEmpty) {
			try {
				// wait and notifyAll help to resolve the deadlock situation
				// when a thread calls the wait method it will suspend execution and release
				// whatever locks it's holding until another thread issues a notification
				wait();
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}
		}
		isEmpty = true;
		notifyAll();
		return message;
	}

	public synchronized void write(String message) {
		// we always want to call wait() within a for loop that's testing for whatever
		// condition we're waiting on because when a thread is notified to wake up
		// there's no guarantee that it's being woken up because the condition is
		// waiting on has changed so it's possible the operating system has woken it up
		// for another reason or it could have woken up because wait through and
		// interrupted exception so we always want to call wait within a loop so that
		// when it returns because there's been a notification of some sort we'll go
		// back to the beginning of the loop we check whatever condition we're interested
		// in and then we call wait again if the condition hasn't changed so in other
		// words never assume that a thread has been woken up because the condition that
		// it's waiting on has changed
		while (!isEmpty) {
			try {
				wait();
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}
		}
		isEmpty = false;
		this.message = message;
		// thread called notify all after its change the value of the empty
		notifyAll();
	}
}

class Writer implements Runnable {
	private Message message;

	protected Writer(Message message) {
		this.message = message;
	}

	@Override
	public void run() {
		String[] messages = { "Hi Kids ", "Do you like Primist ", "Do you see anything in my eyelips",
				"That the way I am" };
		Random random = new Random();
		for (int i = 0; i < messages.length; i++) {
			message.write(messages[i]);
			try {
				Thread.sleep(random.nextInt(3000));
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}
		}
		message.write("Finished");
	}
}

class Reader implements Runnable {
	private Message message;

	protected Reader(Message message) {
		this.message = message;
	}

	@Override
	public void run() {
		Random random = new Random();
		for (String lastestMessage = message.read(); !lastestMessage.equals("Finished"); lastestMessage = message
				.read()) {
			System.out.println(lastestMessage);
			try {
				Thread.sleep(random.nextInt(3000));
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}
		}
	}

}