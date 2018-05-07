package home.thread_array;

public class ThreadSumArraySynchronized implements Runnable {
	private Integer i;
	private Integer sum = 0;
	int[] array;

	protected ThreadSumArraySynchronized(int[] array) {
		this.array = array;
	}

	@Override
	public void run() {
		for (i = 0; i < array.length; i++) {
			synchronized (array) {
				synchronized (i) {
					array[i] = (int) (Math.random() * 100);
				}
			}
			// try {
			// Thread.sleep(100);
			// } catch (InterruptedException e) {
			// e.printStackTrace();
			// }
		}

		for (i = 0; i < array.length; i++) {
			synchronized (array) {
				synchronized (i) {
					System.out.println("i_" + (i + 1) + " : " + array[i] + "\t" + Thread.currentThread().getName());
				}
			}
			// try {
			// Thread.sleep(100);
			// } catch (InterruptedException e) {
			// e.printStackTrace();
			// }
		}

		for (i = 0; i < array.length; i++) {
			synchronized (sum) {
				synchronized (array) {
					sum += array[i];
					System.out.println("Sum = " + sum + "\t" + Thread.currentThread().getName());
				}
			}
			// try {
			// Thread.sleep(100);
			// } catch (InterruptedException e) {
			// e.printStackTrace();
			// }
		}
	}

}
