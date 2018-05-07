package test.concurrency;

public class ThreadDemo {

	public static void main(String[] args) {
		System.out.println("Main Thread - BOOM");
		AnotherThread anotherThread = new AnotherThread();
		anotherThread.setName("ANOTHER THREAD");
		anotherThread.start();
		// Anomyous Thread Class is start
		new Thread() {
			public void run() {
				System.out.println("====");
				System.out.println("Anomyous Class - DOWN");
				System.out.println(
						"Be aware - the order of thread is depend on the scheduler of OS >< not the order in your code :-)");
				System.out.println("====");
			}
		}.start();

		Thread myRunable = new Thread(new MyRunable());
		myRunable.setName("MY RUNABLE THREAD");
		myRunable.start();
		anotherThread.interrupt();

		System.out.println("Main Thread - BOOM AGAIN");
		myRunable = new Thread(new MyRunable() {
			@Override
			public void run() {
				super.run();
				try {
					// join the Runnable Thread to Another Thread so Runnable Thread will wait until
					// Another Thread terminate or time out after 50ms then it will be processed
					anotherThread.join(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
					System.out.println(e.getMessage());
				}
			}
		});
		myRunable.start();

		// bellow code will make Exception because the install of subclass of Thread can
		// be start 01 time only
		try {
			anotherThread.start();
		} catch (Exception e) {
			System.out.println("the install of subclass of Thread can be start 01 time only");
		}

	}

}
