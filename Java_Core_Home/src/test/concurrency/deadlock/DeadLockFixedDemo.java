package test.concurrency.deadlock;

public class DeadLockFixedDemo {
	private static Object lock1 = new Object();
	private static Object lock2 = new Object();
	
	public static void main(String[] args) {
		new Thread1().start();
		new Thread2().start();
	}
	
	private static class Thread1 extends Thread {
		@Override
		public void run() {
			synchronized (lock1) {
				System.out.println("Thread 1 has lock1 ");
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
				}
				System.out.println("Thread 1 waiting for Lock 2");
				synchronized (lock2) {
					System.out.println("Thread 1 has lock2 ");
				}
				System.out.println("Thread 1 release lock 2");
			}
			System.out.println("Thread 1 release lock 1 - Exiting...");
		}
	}
	
	private static class Thread2 extends Thread {
		@Override
		public void run() {
			synchronized (lock1) {
				System.out.println("Thread 2 has lock1 ");
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
				}
				System.out.println("Thread 2 waiting for Lock 2");
				synchronized (lock2) {
					System.out.println("Thread 2 has lock2 ");
				}
				System.out.println("Thread 2 release lock 2");
			}
			System.out.println("Thread 2 release lock 1 - Exiting...");
		}
	}

}
