package test.database.jdbc;

import java.util.List;
import java.util.Scanner;

import test.database.jdbc.model.Artist;
import test.database.jdbc.model.DataSource;
import test.database.jdbc.model.InsertRandomArtistRunnable;
import test.database.jdbc.model.SongArtist;

public class DBMusicDemo {
	public static void main(String[] args) {
		DataSource dataSource = new DataSource();
		if (!dataSource.open()) {
			System.out.println("Cannot open DB");
			return;
		}
		
		if(dataSource.createViewForSongArtist()) {
			System.out.println("View Created");
		}

		System.out.println("========================================");
		System.out.println("Artist List : ");
		List<Artist> artists = dataSource.queryArtist(DataSource.ORDER_BY_ASC);
		if (artists.isEmpty()) {
			System.out.println("No artists found");
		}
		artists.forEach(artist -> System.out.println("\tID =\t" + artist.getId() + "\t Name = " + artist.getName()));

		System.out.println("========================================");
		String string = "Pink Floyd";
		System.out.println("List of album - " + string);
		List<String> albumsForArtist = dataSource.queryAlbumsForArtist(string, DataSource.ORDER_BY_ASC);
		if (albumsForArtist.isEmpty()) {
			System.out.println("No Album found");
		}
		albumsForArtist.forEach(album -> System.out.println("\t" + album));

		System.out.println("========================================");
		string = "Go Your Own Way";
		System.out.println("List of album of " + string);		
		List<SongArtist> songArtists = dataSource.queryAristsForSong(string, DataSource.ORDER_BY_ASC);
		if (songArtists.isEmpty()) {
			System.out.println("No Album found");
		}
		songArtists.forEach(list -> System.out
				.println("\t" + list.getArtistName() + "\t" + list.getAlbumName() + "\t" + list.getTrack()));
		
		System.out.println("========================================");
		Scanner scanner = new Scanner(System.in);
		System.out.print("Please input song name =\t");
		string = scanner.nextLine();
		System.out.println("List of album of " + string);
		songArtists.clear();
		songArtists = dataSource.querySongInfoView(string);
		if (songArtists.isEmpty()) {
			System.out.println("No Album found");
		}
		songArtists.forEach(list -> System.out
				.println("\t" + list.getArtistName() + "\t" + list.getAlbumName() + "\t" + list.getTrack()));

		dataSource.querySongMetadata();
		string = DataSource.TABLE_SONGS;
		System.out.println("\nNumber of song in table " + string + " = " + dataSource.getCount(string));
		
//		System.out.println("Start");
//		for (int i = 0; i < 10; i++) {
//			new Thread(new InsertRandomArtistMultiThread("Task-" + (i + 1), i * 10000 + 1 , (i + 1) * 10000)).start();
//		}
//		System.out.println("Finished");
//		
		dataSource.insertSong("Touch of Grey", "Graceful Dead", "In The Dark", 1);
		dataSource.insertSong("Like a Rolling Stone", "Bob Dylan", "Bob Dylan 's The Greatest Hits", 1);
		dataSource.insertSong("Bird Dog", "Everly Brothers", "All-Time Greatest Hits", 1);
		dataSource.insertSong("Bird Cat", "Everly Brothers", "All-Time Greatest Hits", 2);
		dataSource.insertSong("Bird Pet", "Everly Brothers", "All-Time Greatest Hits", 3);
		dataSource.close();
	}
	
}