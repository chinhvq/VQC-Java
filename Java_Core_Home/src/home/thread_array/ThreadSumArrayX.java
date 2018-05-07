package home.thread_array;

public class ThreadSumArrayX implements Runnable {
	private int numOfElement;
	private Integer i;
	private Integer sum = 0;
	int[] array;

	protected ThreadSumArrayX(int numOfElement) {
		this.numOfElement = numOfElement;
		array = new int[numOfElement];
	}

	@Override
	public void run() {
		for (i = 0; i < array.length; i++) {
			synchronized (i) {
				synchronized (array) {
					array[i] = (int) (Math.random() * 100);
				}				
			}
		}

		for (i = 0; i < array.length; i++) {
			synchronized (array) {
				synchronized (i) {
					System.out.println(array[i] + "\t" + Thread.currentThread().getName());
				}				
			}			
		}

		for (i = 0; i < array.length; i++) {
			synchronized (sum) {
				synchronized (array) {
					sum += array[i];
					System.out.println("Sum = " + sum + "\t" + Thread.currentThread().getName());
				}				
			}			
		}		
	}

}
