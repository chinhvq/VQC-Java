package test.concurrency;

public class CountDownThread extends Thread{

	private CountDown threadCountDown;

	protected CountDownThread(CountDown threadCountDown) {
		this.threadCountDown = threadCountDown;
	}

	@Override
	public void run() {
		threadCountDown.countDown();
	}
}
