package test.generic3;

public class ThuVienManagementGeneric2 {

	public static void main(String[] args) {
		ThuVienGeneric2 thuVien = new ThuVienGeneric2("TVHN");
		
		Book book = new Book("Thep da toi the day", "NBX Van Hoa", 400);
		thuVien.add(thuVien.getBooks(), book);
		book = new Book("7 Thoi Quan", "NXB Ha Noi", 250);
		thuVien.add(thuVien.getBooks(), book);
		book = new Book("Dat nuoc toi", "NBX Quan Doi", 300);
		thuVien.add(thuVien.getBooks(), book);
		thuVien.add(thuVien.getBooks(), book);
		
		Video video = new Video("Bong dung muon hat", "Dai truyen hinh TP HCM", 500.20);
		thuVien.add(thuVien.getVideos(), video);
		video = new Video("Canh sat hinh su", "Dai truyen hinh Viet Nam", 5000);
		thuVien.add(thuVien.getVideos(), video);
		video = new Video("Too fast and too furious", "Dai truyen hinh TP HCM", 400.50);
		thuVien.add(thuVien.getVideos(), video);
		thuVien.add(thuVien.getVideos(), null);		
		
		book = (Book) thuVien.findLast(thuVien.getBooks());
		System.out.print("\nThe last book of the list: \n\t" + book);
		
		video = (Video) thuVien.findLast(thuVien.getVideos());
		System.out.print("\nThe last video of the list: \n\t" + video);
		
		System.out.println("\n\nDanh sach Sach truoc khi sap xep theo ten\n\t" + thuVien.getBooks());
		thuVien.getBooks().sort(null);
		System.out.println("\nDanh sach Sach sau khi sap xep theo ten\n\t" + thuVien.getBooks());
		thuVien.getBooks().sort(new SortByPages());
		System.out.println("\nDanh sach Sach sau khi sap xep theo so trang in\n\t" + thuVien.getBooks());
		
		System.out.println("\nDanh sach Video truoc khi sap xep theo ten\n\t" + thuVien.getVideos());
		thuVien.getVideos().sort(null);
		System.out.println("\nDanh sach Video sau khi sap xep theo ten\n\t" + thuVien.getVideos());
		thuVien.getVideos().sort(new SortByDuration());
		System.out.println("\nDanh sach Video sau khi sap xep theo thoi luong\n\t" + thuVien.getVideos());		

	}

}
