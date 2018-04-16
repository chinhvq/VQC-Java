package test.generic3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class ThuVienXGeneric {
	static List<Book> books = new ArrayList<>();
	static List<Video> videos = new ArrayList<>();

	public static void main(String[] args) {
		
		
		Book book = new Book("7 Thoi Quan", "NXB Ha Noi", 250);
		add(books, book);
		book = new Book("Thep da toi the day", "NBX Van Hoa", 400);
		add(books, book);
		book = new Book("Dat nuoc toi", "NBX Quan Doi", 300);
		add(books, book);
		add(books, book);
		add(books,null);
		
		Video video = new Video("Bong dung muon hat", "Dai truyen hinh TP HCM", 500);
		add(videos, video);
		video = new Video("Canh sat hinh su", "Dai truyen hinh Viet Nam", 5000);
		add(videos, video);
		video = new Video("Too fast and too furious", "Dai truyen hinh TP HCM", 500);
		add(videos, video);
		add(videos, video);
		
		book = findLast(books);
		System.out.print("\nThe last book of the list: \n\t" + book);
		
		video = findLast(videos);
		System.out.print("\nThe last book of the list: \n\t" + video);
	}
		
	private static <E> boolean add(Collection<E> E, E e) {
		if (e == null) {
			System.out.println("Please input item information for \" " + String.valueOf(e).toUpperCase() + " \"");
			return false;
		} else if (find(E, e)) {
			System.out.println("Item \""+ e.toString() + "\" already exist in library");			
			return false;			
		} else {
			E.add(e);
			return true;			
		}			
	}		

	private static <E> boolean find(Collection<E> E, E e) {
		if(e == null) {
			return false;
		} else {
			if(E.contains(e)) {
				return true;
			}
		}
		return false;
	}
	
	private static <E> E findLast(Collection<E> X) {
		E e = null; 
		Iterator<E> itr = X.iterator();
		while(itr.hasNext()) {
			e = itr.next(); 
		}		
		return e;	
	}
}
