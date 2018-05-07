package home.thread_array;

public class ThreadArray implements Runnable {
	private int endPos;
	private int initPos;
	private int sum;
	private int max;
	int[] array;
	
	
	protected ThreadArray(int initPos, int endPos, int[] array) {
		this.endPos = endPos;
		this.initPos = initPos;
		this.array = array;
		this.sum = 0;
		this.max = array[initPos];
	}		
	
	protected int getSum() {
		return this.sum;
	}
	
	protected int getMax() {
		return max;
	}

	@Override
	public void run() {
		
		for (int i = initPos; i <= endPos; i++) {
			this.sum += array[i];
			
			if(this.max < array[i]) {
				this.max = array[i];				
			}
		}
		
		for (int i = initPos; i <= endPos; i++) {
			System.out.println("i_" + (i + 1) + " = " + array[i] + "\t" + Thread.currentThread().getName());		
		}
		
		System.out.println("---SUM = " + this.sum + "\t" + Thread.currentThread().getName() + "---");
		System.out.println("---MAX = " + this.max + "\t" + Thread.currentThread().getName() + "---");
	}
	
	
	
	
}
