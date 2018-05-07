package test.concurrency.myproducer_synchronized;

import java.util.List;

public class MyConsumer implements Runnable{
	private List<String> buffer;

	protected MyConsumer(List<String> buffer) {
		this.buffer = buffer;
	}
	
	@Override
	public void run() {
		while(true) {
			synchronized (buffer) {
				if (buffer.isEmpty()) {
					continue;
				}
				if(buffer.get(0).equals("EOF")) {
					System.out.println("Exiting");
					break;
				} else {
					System.out.println("Remove " + buffer.remove(0));				
				}		
			}				
		}
	}
}
