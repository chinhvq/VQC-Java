package test.database.jdbc.model;


public class InsertRandomArtistRunnable implements Runnable {
	DataSource dataSource = new DataSource();
	long startTime, finishTime, duration;
	boolean result;
	private String name;
	private int startIndex, endIndex;

	public InsertRandomArtistRunnable(String name, int startIndex, int endIndex) {
		this.name = name;
		this.startIndex = startIndex;
		this.endIndex = endIndex;
	}
	
	@Override
	public void run() {
		startTime = System.currentTimeMillis();
		System.out.println("Start Time" + startTime + "\t" + this.name + "\t" + Thread.currentThread().getName());
		result = dataSource.insertRandomArtistRange("RandomArtist-", this.startIndex, this.endIndex);
		finishTime = System.currentTimeMillis();
		duration = finishTime - startTime;
		System.out.println("Finish Time" + finishTime + "\t" + this.name + "\t" + Thread.currentThread().getName());
		System.out.println("Time need - " + duration + " ms -> = " + duration / 60000 + " mins -\t" + this.name + "\t"
				+ Thread.currentThread().getName());
		if(result) {
			System.out.println("Inser artist - SUCCESSFUL " + this.name + "\t" + Thread.currentThread().getName());
		} else {
			System.out.println("Inser artist - FAIL " + this.name + "\t" + Thread.currentThread().getName());
		}
	}

}
