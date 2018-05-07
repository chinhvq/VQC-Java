package test.concurrency.livelock;

public class Worker {
	private String name;
	private boolean active;

	protected Worker(String name, boolean active) {
		this.name = name;
		this.active = active;
	}

	protected String getName() {
		return name;
	}

	protected boolean isActive() {
		return active;
	}

	public synchronized void work(ShareResource shareResource, Worker otherWorker) {
		while (active) {
			if (shareResource.getOwner() != this) {
				try {
					wait(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				continue;
			}
			if (otherWorker.isActive()) {
				System.out.println(this.getName() + " : give the resource to the worker " + otherWorker.getName());
				shareResource.setOwner(otherWorker);
				continue;
			}
			System.out.println(this.getName() + " : working on common resource ");
			active = false;
			shareResource.setOwner(otherWorker);
		}
	}
}

// the work method so the threads going to check to see whether it owns the
// shared resource if it doesn't it's gonna wait for 10 milliseconds loop back
// and try again => so this is waiting to see whether it's acquired the resource
// and whether it basically whether it owns the shared resource now when the
// condition evaluates to true meaning that it owns the shared resource it then
// checks to see whether the other threads active if it is its going to politely
// give the shared resource to the other thread then return back to the
// beginning of the loop now if the other thread is an active it will actually
// use the shared resource and then loop back to the beginning again. ==> so
// really the only time this thread will complete a loop iteration is when it
// owns a shared resource and the other thread isn't active