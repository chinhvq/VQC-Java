package test.concurrency.livelock;

public class ShareResource {
	private Worker owner;

	protected ShareResource(Worker owner) {
		this.owner = owner;
	}

	protected Worker getOwner() {
		return owner;
	}

	protected synchronized void setOwner(Worker owner) {
		this.owner = owner;
	}
	
	

}
