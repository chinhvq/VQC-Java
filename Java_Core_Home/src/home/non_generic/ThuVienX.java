package home.non_generic;

import java.util.ArrayList;
import java.util.List;

public class ThuVienX {
	static List<Book> books = new ArrayList<>();
	static List<Video> videos = new ArrayList<>();

	public static void main(String[] args) {
		
		
		Book book = new Book("Thep da toi the day", "NBX Van Hoa", 400);
		addBook(book);
		book = new Book("7 Thoi Quan", "NXB Ha Noi", 250);
		addBook(book);
		book = new Book("Dat nuoc toi", "NBX Quan Doi", 300);
		addBook(book);
		addBook(book);
		
		Video video = new Video("Too fast and too furious", "Dai truyen hinh TP HCM", 400.50);
		addVideo(video);
		video = new Video("Canh sat hinh su", "Dai truyen hinh Viet Nam", 5000);
		addVideo(video);
		video = new Video("Bong dung muon hat", "Dai truyen hinh TP HCM", 500.20);
		addVideo(video);		
		addVideo(null);
		
		book = findLastBook();
		System.out.print("\nThe last book of the list: \n\t" + book);
		
		video = findLastVideo();
		System.out.print("\n\nThe last video of the list: \n\t" + video);
		
		System.out.println("\n\nDanh sach Sach truoc khi sap xep theo ten\n\t" + books);
		books.sort(null);
		System.out.println("\nDanh sach Sach sau khi sap xep theo ten\n\t" + books);
		books.sort(new SortByPages());
		System.out.println("\nDanh sach Sach sau khi sap xep theo so trang in tang dan\n\t" + books);
		books.sort(new SortByPagesInReverseOrder(new SortByPages()));
		System.out.println("\nDanh sach Sach sau khi sap xep theo so trang in giam dan\n\t" + books);
		
		System.out.println("\nDanh sach Video truoc khi sap xep theo ten\n\t" + videos);
		videos.sort(null);
		System.out.println("\nDanh sach Video sau khi sap xep theo ten\n\t" + videos);
		videos.sort(new SortByDuration());
		System.out.println("\nDanh sach Video sau khi sap xep theo thoi luong tang dan\n\t" + videos);
		videos.sort(new SortByDurationInReverseOrder(new SortByDuration()));
		System.out.println("\nDanh sach Video sau khi sap xep theo thoi luong tang dan\n\t" + videos);
		
	}
		
	private static boolean addBook(Book newBook) {
		if (newBook == null) {
			System.out.println("Vui long them thong tin sach can them");
			return false;
		}else if (findBook(newBook)) { 
			System.out.println("Ten sach \""+ newBook.getName() + "\" da co trong danh sach cua thu vien");
			return false;
		} else {
			books.add(newBook);
			return true;
		}
	}
	
	private static Book findLastBook() {
		return books.get(books.size() -1);		
	}

	private static boolean findBook(Book searchBook) {
		if(searchBook == null) {
			return false;
		} else {
			for (Book book : books) {
				if (book.getName().equals(searchBook.getName())) {
					return true;
				}
			}
			return false;
		}		
	}
	
	private static boolean addVideo(Video newVideo) {
		if (newVideo == null) {
			System.out.println("Vui long them thong tin video can them ");
			return false;
		}else if (findVideo(newVideo)) { 
			System.out.println("Ten video da co trong danh sach cua thu vien");
			return false;
		} else {
			videos.add(newVideo);
			return true;
		}
	}
	
	private static Video findLastVideo() {
		return videos.get(videos.size() -1);		
	}

	private static boolean findVideo(Video newVideo) {
		if(newVideo == null) {
			return false;
		} else {
			for (Video video : videos) {
				if (video.getName().equals(newVideo.getName())) {
					return true;
				}
			}
			return false;
		}		
	}

}
