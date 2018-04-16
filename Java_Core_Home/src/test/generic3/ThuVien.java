package test.generic3;

import java.util.ArrayList;
import java.util.List;

public class ThuVien {
	private final String name;
	List<Book> books;
	List<Video> videos;

	protected ThuVien(String name) {
		this.name = name;
		this.books = new ArrayList<>();
		this.videos = new ArrayList<>();		
	}	

	protected String getName() {
		return name;
	}


	protected boolean addBook(Book newBook) {
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
	
	protected Book findLastBook() {
		return books.get(books.size() -1);		
	}

	private boolean findBook(Book searchBook) {
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
	
	protected boolean addVideo(Video newVideo) {
		if (newVideo == null) {
			System.out.println("Vui long them thong tin video can them");
			return false;
		}else if (findVideo(newVideo)) { 
			System.out.println("Ten video da co trong danh sach cua thu vien");
			return false;
		} else {
			videos.add(newVideo);
			return true;
		}
	}
	
	protected Video findLastVideo() {
		return videos.get(videos.size() -1);		
	}

	private boolean findVideo(Video newVideo) {
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

	@Override
	public String toString() {
		return "ThuVien [name=" + name + "]" + "\n\tBooks= \t" + books + "\n\tVideos=\t" + videos;
	}
	
	
}
