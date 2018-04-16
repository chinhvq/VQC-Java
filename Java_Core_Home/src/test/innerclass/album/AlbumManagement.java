package test.innerclass.album;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

public class AlbumManagement {

	private static List<Album> listAlbums = new ArrayList<Album>();

	public static void main(String[] args) {
		Album album = new Album("The Eminem Show", "Eminem");
		album.addSong("Curtains Up", 0.5);
		album.addSong("White America", (5 + 24 / 60));
		album.addSong("Business", (4 + 11 / 60));
		album.addSong("Cleanin' Out My Closet", (4 + 57 / 60));
		album.addSong("Square Dance", (5 + 24 / 60));
		album.addSong("The Kiss", (1 + 16 / 60));
		album.addSong("Soldier", (3 + 46 / 60));
		album.addSong("Say Goodbye Hollywood", (4 + 33 / 60));
		album.addSong("Drips", (4 + 45 / 60));
		album.addSong("Without Me", (4 + 50 / 60));
		album.addSong("Paul Rosenberg", (23 / 60));
		album.addSong("Sing for the Moment", (5 + 40 / 60));
		album.addSong("Superman", (5 + 50 / 60));
		album.addSong("Hailie's Song", (5 + 21 / 60));
		listAlbums.add(album);

		album = new Album("Master of Puppets", "Metallica");
		album.addSong("Battery", (5 + 13 / 60));
		album.addSong("Master of Puppets", (8 + 35 / 60));
		album.addSong("The Thing That Should Not Be", (6 + 36 / 60));
		album.addSong("Welcome Home (Sanitarium)", (6 + 27 / 60));
		album.addSong("Disposable Heroes", (8 + 17 / 60));
		album.addSong("Leper Messiah", (5 + 40 / 60));
		album.addSong("Orion", (8 + 27 / 60));
		album.addSong("Damage, Inc.", (5 + 32 / 60));
		listAlbums.add(album);

		List<Song> listPlayList = new LinkedList<Song>();
		listAlbums.get(0).addSongToPlayList("White America", listPlayList);
		listAlbums.get(0).addSongToPlayList("Superman", listPlayList);
		listAlbums.get(0).addSongToPlayList("Sing for the Moment", listPlayList);
		listAlbums.get(0).addSongToPlayList("Soldier", listPlayList);
		listAlbums.get(1).addSongToPlayList(2, listPlayList);
		listAlbums.get(1).addSongToPlayList(4, listPlayList);
		// listAlbums.get(1).addSongToPlayList(30, listPlayList);

		play(listPlayList);
	}

	private static void play(List<Song> listPlayList) {
		Scanner scanner = new Scanner(System.in);
		ListIterator<Song> listIterator = listPlayList.listIterator();
		boolean bExit = false;
		boolean bForward = true;
		if (listPlayList.isEmpty()) {
			System.out.println("\nThere is no song in this Play List");
		} else {
			if (listIterator.hasNext()) {
				System.out.println("Playing " + listIterator.next().toString());
				printMenu();
			}
		}

		while (!bExit) {
			int nChoice = scanner.nextInt();
			scanner.nextLine();

			switch (nChoice) {
			case 0:
				System.out.println("Play list is completed");
				bExit = true;
				break;

			case 1:
				if (!bForward) {
					if (listIterator.hasNext()) {
						listIterator.next();
					}
					bForward = true;
				}
				if (listIterator.hasNext()) {
					System.out.println("\nNow playing " + listIterator.next().toString());
				} else {
					System.out.println("\nWe have reach to the End of the Play List");
					bForward = false;
				}
				break;

			case 2:
				if (bForward) {
					if (listIterator.hasPrevious()) {
						listIterator.previous();
					}
					bForward = false;
				}
				if (listIterator.hasPrevious()) {
					System.out.println("\nNow playing " + listIterator.previous().toString());
				} else {
					System.out.println("\nWe have reach to the Start of the Play List");
					bForward = true;
				}
				break;

			case 3:
				replay(bForward, listPlayList,  listIterator);
				break;

			case 4:
				printPlayList(listPlayList);
				break;

			case 5:
				deleteCurrentSong(listPlayList,  listIterator);
				break;
			case 6:
				printMenu();
				break;
			}
		}

		scanner.close();

	}

	private static void replay(boolean bForward, List<Song> listPlayList, ListIterator<Song> listIterator) {		
		if (bForward) {
			if (listIterator.hasPrevious()) {
				System.out.println("\nReplaying " + listIterator.previous().toString());
				bForward = false;
			} else {
				System.out.println("\nWe have reach to the Start of the Play List");
			}
		} else {
			if (listIterator.hasNext()) {
				System.out.println("\nReplaying " + listIterator.next().toString());
				bForward = true;
			} else {
				System.out.println("\nWe have reach to the End of the Play List");
			}
		}
	}
	
	private static void deleteCurrentSong(List<Song> listPlayList, ListIterator<Song> listIterator) {
		if(!listPlayList.isEmpty()) {
			listIterator.remove();
			if(listIterator.hasNext()) {
				System.out.println("\nNow playing " + listIterator.next());
			} else if (listIterator.hasPrevious()) {
				System.out.println("\nNow playing " + listIterator.previous());
			} else {
				System.out.println("End of PlayList - NO MORE SONG AVAILABLE\n PLEASE AT SOME MORE SONG INTO PLAYLIST");
			}
		} else {
			System.out.println("\nThere is no song in this Play List");
		}
	}

	private static void printMenu() {
		System.out.print("\nPlease make your selection\n" + "1 - Play next song\n" + "2 - Play previous song\n"
				+ "3 - Replay the current song\n" + "4 - Print list of song\n" + "5 - Delete the current song\n" + "6 - Print selection menu\n"
				+ "Please choose:\t");
	}

	public static void printPlayList(List<Song> listPlayList) {
		Iterator<Song> iterator = listPlayList.iterator();
		System.out.println("\nAvailable song in the Play List");
		System.out.println("==========================");
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
		System.out.println("==========================");
	}

}
