package test.database.jdbc;

import test.database.jdbc.model.DataSource;

public class InsertRandomArtistThreadDemo extends Thread {
	static DataSource dataSource = new DataSource(); 
	private long startTime;
	private long finishTime;
	private long duration;
	private boolean result;
	
	private String name;
	private int startIndex, endIndex;

	public InsertRandomArtistThreadDemo(String name, int startIndex, int endIndex) {
		this.name = name;
		this.startIndex = startIndex;
		this.endIndex = endIndex;
	}
	
	public InsertRandomArtistThreadDemo(String name) {
		this.name = name;
	}

	public static void main(String[] args) {

		if (!dataSource.open()) {
			System.out.println("Cannot open DB");
			return;
		} else {
			System.out.println("Connected to DB");
		}

//		new Thread(new InsertRandomArtistThread("Task-1")).start();;
		Thread task1 = new InsertRandomArtistThreadDemo("Task-1", 10001, 20000);
		Thread task2 = new InsertRandomArtistThreadDemo("Task-2", 20001, 30000);
		Thread task3 = new InsertRandomArtistThreadDemo("Task-3", 30001, 40000);
		Thread task4 = new InsertRandomArtistThreadDemo("Task-4", 40001, 50000);
		Thread task5 = new InsertRandomArtistThreadDemo("Task-5", 50001, 60000);
		Thread task6 = new InsertRandomArtistThreadDemo("Task-6", 60001, 70000);
		Thread task7 = new InsertRandomArtistThreadDemo("Task-7", 70001, 80000);
		Thread task8 = new InsertRandomArtistThreadDemo("Task-8", 80001, 90000);
		Thread task9 = new InsertRandomArtistThreadDemo("Task-9", 90001, 100000);
		
		new Thread(task1).start();
		new Thread(task2).start();
		new Thread(task3).start();
		new Thread(task4).start();
		new Thread(task5).start();
		new Thread(task6).start();
		new Thread(task7).start();
		new Thread(task8).start();
		new Thread(task9).start();

		dataSource.close();
	}
	
	@Override
	public void run() {
		startTime = System.currentTimeMillis();
		System.out.println("Start Time" + startTime + "\t" + this.name + "\t" + Thread.currentThread().getName());
		result = dataSource.insertRandomArtistRange("Artist-", this.startIndex, this.endIndex);
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
