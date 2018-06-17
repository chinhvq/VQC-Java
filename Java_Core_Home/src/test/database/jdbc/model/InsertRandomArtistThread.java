package test.database.jdbc.model;

public class InsertRandomArtistThread extends Thread {
	DataSource dataSource = new DataSource();
	private long startTime;
	private long finishTime;
	private long duration;
	private boolean result;
	private String name;
	private int startIndex, endIndex;

	public InsertRandomArtistThread(String name, int startIndex, int endIndex) {
		this.name = name;
		this.startIndex = startIndex;
		this.endIndex = endIndex;
	}
	
	public InsertRandomArtistThread(String name) {
		this.name = name;
	}
	
	@Override
	public void run() {
		startTime = System.currentTimeMillis();
		System.out.println("Start Time" + startTime + "\t" + this.name + "\t" + Thread.currentThread().getName());
		result = dataSource.insertRandomArtistRange("Artist-", this.startIndex, this.endIndex);
//		result = dataSource.insertRandomArtist("Artist-");
		finishTime = System.currentTimeMillis();
		duration = finishTime - startTime;
		System.out.println("Finish Time" + finishTime + "\t" + this.name + "\t" + Thread.currentThread().getName());
		System.out.println("Time need - " + duration + " ms -> = " + duration / 60000 + " mins -\t" + this.name + "\t"
				+ Thread.currentThread().getName());
		if(result) {
			System.out.println("Insert artist - SUCCESSFUL " + this.name + "\t" + Thread.currentThread().getName());
		} else {
			System.out.println("Insert artist - FAIL " + this.name + "\t" + Thread.currentThread().getName());
		}
	}

}
