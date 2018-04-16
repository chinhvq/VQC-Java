package home.generic.qlthuvien;

public class ThuVienManagementGeneric {

	public static void main(String[] args) {
		System.out.println("Day la bai Generic II");
		
		ThuVienGeneric thuVien = new ThuVienGeneric("TVHN");		
		BookGeneric book = new BookGeneric("Thep da toi the day", "NBX Van Hoa", 250);
		thuVien.add(thuVien.getBooks(), book);
		book = new BookGeneric("7 Thoi Quan", "NXB Ha Noi", 400);
		thuVien.add(thuVien.getBooks(), book);
		book = new BookGeneric("Dat nuoc toi", "NBX Quan Doi", 300);
		thuVien.add(thuVien.getBooks(), book);
		thuVien.add(thuVien.getBooks(), book);
		
		VideoGeneric video = new VideoGeneric("Too fast and too furious", "Dai truyen hinh TP HCM", 400.50);
		thuVien.add(thuVien.getVideos(), video);
		video = new VideoGeneric("Canh sat hinh su", "Dai truyen hinh Viet Nam", 5000);
		thuVien.add(thuVien.getVideos(), video);
		video = new VideoGeneric("Bong dung muon hat", "Dai truyen hinh TP HCM", 500.20);
		thuVien.add(thuVien.getVideos(), video);
		thuVien.add(thuVien.getVideos(), null);		
		
		book = (BookGeneric) thuVien.findLast(thuVien.getBooks());
		System.out.print("\nThe last book of the list: \n\t" + book);
		
		video = (VideoGeneric) thuVien.findLast(thuVien.getVideos());
		System.out.print("\nThe last video of the list: \n\t" + video);
		
		System.out.println("\n\nDanh sach Sach truoc khi sap xep theo ten\n\t" + thuVien.getBooks());
		thuVien.getBooks().sort(null);
		System.out.println("\nDanh sach Sach sau khi sap xep theo ten\n\t" + thuVien.getBooks());
		thuVien.getBooks().sort(new SortByPagesGeneric());
		System.out.println("\nDanh sach Sach sau khi sap xep theo so trang in tang dan\n\t" + thuVien.getBooks());
		thuVien.getBooks().sort(new SortByPagesInReverseOrderGeneric<>(new SortByPagesGeneric()));
		System.out.println("\nDanh sach Sach sau khi sap xep theo so trang in giam dan\n\t" + thuVien.getBooks());
		
		System.out.println("\nDanh sach Video truoc khi sap xep theo ten\n\t" + thuVien.getVideos());
		thuVien.getVideos().sort(null);
		System.out.println("\nDanh sach Video sau khi sap xep theo ten\n\t" + thuVien.getVideos());
		thuVien.getVideos().sort(new SortByDurationGeneric());
		System.out.println("\nDanh sach Video sau khi sap xep theo thoi luong tang dan\n\t" + thuVien.getVideos());		
		thuVien.getVideos().sort(new SortByDurationInReverseOrderGeneric<>(new SortByDurationGeneric()));
		System.out.println("\nDanh sach Video sau khi sap xep theo thoi luong giam dan\n\t" + thuVien.getVideos());

	}

}
