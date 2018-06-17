package test.database.jdbc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import test.database.jdbc.model.DataSource;
import test.database.jdbc.model.InsertRandomArtistRunnable;

public class InsertRandomArtistDemo {

	public static void main(String[] args) {
		DataSource dataSource = new DataSource();
		if (!dataSource.open()) {
			System.out.println("Cannot open DB");
			return;
		}
		
		System.out.println("==================START INSERTION==================");
		if(dataSource.insertRandomArtist("Artist-")) {
			System.out.println("Insert artist - SUCCESSFUL ");
		} else {
			System.out.println("Inser artist - FAIL " );
		}
		System.out.println("==================COMPLETION===================");
		
		
		
//		InsertRandomArtistMultiThread task1 = new InsertRandomArtistMultiThread("Task-1", 1, 10000);
//		InsertRandomArtistMultiThread task2 = new InsertRandomArtistMultiThread("Task-2", 10001, 20000);
//		InsertRandomArtistMultiThread task3 = new InsertRandomArtistMultiThread("Task-3", 20001, 30000);
//		InsertRandomArtistMultiThread task4 = new InsertRandomArtistMultiThread("Task-4", 30001, 40000);
//		InsertRandomArtistMultiThread task5 = new InsertRandomArtistMultiThread("Task-5", 40001, 50000);
//		InsertRandomArtistMultiThread task6 = new InsertRandomArtistMultiThread("Task-6", 50001, 60000);
//		InsertRandomArtistMultiThread task7 = new InsertRandomArtistMultiThread("Task-7", 60001, 70000);
//		InsertRandomArtistMultiThread task8 = new InsertRandomArtistMultiThread("Task-8", 70001, 80000);
//		InsertRandomArtistMultiThread task9 = new InsertRandomArtistMultiThread("Task-9", 80001, 90000);
//		InsertRandomArtistMultiThread task10 = new InsertRandomArtistMultiThread("Task-10", 90001, 100000);
//		
//		ExecutorService excutorService = Executors.newFixedThreadPool(10);
//		excutorService.execute(task1);
//		excutorService.execute(task2);
//		excutorService.execute(task3);
//		excutorService.execute(task4);
//		excutorService.execute(task5);
//		excutorService.execute(task6);
//		excutorService.execute(task7);
//		excutorService.execute(task8);
//		excutorService.execute(task9);
//		excutorService.execute(task10);
		
		dataSource.close();
		
		// for (int i = 1; i <= 10; i++) {
		// new Thread(new InsertRandomArtistMultiThread("Task-" + i, (i-1) * 10000 + 1 ,
		// i * 10000)).start();
		// }
	}

}
