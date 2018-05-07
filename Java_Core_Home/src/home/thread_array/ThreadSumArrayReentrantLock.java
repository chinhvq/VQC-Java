package home.thread_array;

import java.util.concurrent.locks.ReentrantLock;

public class ThreadSumArrayReentrantLock implements Runnable {
	private int i;
	private int sum;
	int[] array;
	private ReentrantLock arrayLock;

	protected ThreadSumArrayReentrantLock(int[] array, ReentrantLock arrayLock) {
		this.array = array;
		this.arrayLock = arrayLock;
		this.sum = 0;
	}

	@Override
	public void run() {

		for (i = 0; i < this.array.length; i++) {
			if (arrayLock.tryLock()) {
				try {
					this.array[i] = (int) (Math.random() * 100);
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				} finally {
					arrayLock.unlock();
				}
			}
		}

		for (i = 0; i < this.array.length; i++) {
			if (arrayLock.tryLock()) {
				try {
					System.out
							.println("i_" + (i + 1) + " : " + this.array[i] + "\t" + Thread.currentThread().getName());
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				} finally {
					arrayLock.unlock();
				}
			}
		}

		for (i = 0; i < this.array.length; i++) {
			if (arrayLock.tryLock()) {
				if (arrayLock.tryLock()) {
					try {
						sum += this.array[i];
						System.out.println("Sum = " + sum + "\t" + Thread.currentThread().getName());
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					} finally {
						arrayLock.unlock();
					}
				}
			}
		}
	}
}
