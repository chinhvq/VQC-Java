package test.generic3;

public class ThuVienManagementGeneric {

	public static void main(String[] args) {
		ThuVienGeneric thuVien = new ThuVienGeneric("TVHN");
		
		Book book = new Book("Thep da toi the day", "NBX Van Hoa", 400);
		thuVien.add(thuVien.books, book);
		book = new Book("7 Thoi Quan", "NXB Ha Noi", 250);
		thuVien.add(thuVien.books, book);
		book = new Book("Dat nuoc toi", "NBX Quan Doi", 300);
		thuVien.add(thuVien.books, book);
		thuVien.add(thuVien.books, book);
		
		Video video = new Video("Bong dung muon hat", "Dai truyen hinh TP HCM", 500.20);
		thuVien.add(thuVien.videos, video);
		video = new Video("Canh sat hinh su", "Dai truyen hinh Viet Nam", 5000);
		thuVien.add(thuVien.videos, video);
		video = new Video("Too fast and too furious", "Dai truyen hinh TP HCM", 400.50);
		thuVien.add(thuVien.videos, video);
		thuVien.add(thuVien.videos, null);		
		
		book = thuVien.findLast(thuVien.books);
		System.out.print("\nThe last book of the list: \n\t" + book);
		
		video = thuVien.findLast(thuVien.videos);
		System.out.print("\nThe last video of the list: \n\t" + video);
		
		System.out.println("\n\nDanh sach Sach truoc khi sap xep theo ten\n\t" + thuVien.books);
		thuVien.books.sort(null);
		System.out.println("\nDanh sach Sach sau khi sap xep theo ten\n\t" + thuVien.books);
		thuVien.books.sort(new SortByPages());
		System.out.println("\nDanh sach Sach sau khi sap xep theo so trang in\n\t" + thuVien.books);
		
		System.out.println("\nDanh sach Video truoc khi sap xep theo ten\n\t" + thuVien.videos);
		thuVien.videos.sort(null);
		System.out.println("\nDanh sach Video sau khi sap xep theo ten\n\t" + thuVien.videos);
		thuVien.videos.sort(new SortByDuration());
		System.out.println("\nDanh sach Video sau khi sap xep theo thoi luong\n\t" + thuVien.videos);		

	}

}
