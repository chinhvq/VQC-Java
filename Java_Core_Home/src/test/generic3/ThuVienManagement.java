package test.generic3;

public class ThuVienManagement {

	public static void main(String[] args) {
		ThuVien thuVien = new ThuVien("TVQG");
		Book book = new Book("Thep da toi the day", "NBX Van Hoa", 400);
		thuVien.addBook(book);
		book = new Book("7 Thoi Quan", "NXB Ha Noi", 250);
		thuVien.addBook(book);
		book = new Book("Dat nuoc toi", "NBX Quan Doi", 300);
		thuVien.addBook(book);
		thuVien.addBook(book);

		Video video = new Video("Bong dung muon hat", "Dai truyen hinh TP HCM", 500.20);
		thuVien.addVideo(video);
		video = new Video("Canh sat hinh su", "Dai truyen hinh Viet Nam", 5000);
		thuVien.addVideo(video);
		video = new Video("Too fast and too furious", "Dai truyen hinh TP HCM", 400.50);
		thuVien.addVideo(video);

		System.out.println(thuVien);

		book = thuVien.findLastBook();
		System.out.print("\nThe last book of the list: \n\t" + book);

		video = thuVien.findLastVideo();
		System.out.print("\nThe last video of the list: \n\t" + video);
	}

}
