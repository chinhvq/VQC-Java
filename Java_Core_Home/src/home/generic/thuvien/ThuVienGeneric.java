package home.generic.thuvien;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class ThuVienGeneric {
	static List<BookGeneric> bookGenerics = new ArrayList<>();
	static List<VideoGeneric> videoGenerics = new ArrayList<>();

	public static void main(String[] args) {

		System.out.println("Day la bai Generic I");
		BookGeneric bookGeneric = new BookGeneric("Thep da toi the day", "NBX Van Hoa", 250);
		add(bookGenerics, bookGeneric);
		bookGeneric = new BookGeneric("7 Thoi Quan", "NXB Ha Noi", 400);
		add(bookGenerics, bookGeneric);
		bookGeneric = new BookGeneric("Dat nuoc toi", "NBX Quan Doi", 300);
		add(bookGenerics, bookGeneric);
		add(bookGenerics, null);

		VideoGeneric videoGeneric = new VideoGeneric("Too fast and too furious", "Dai truyen hinh TP HCM", 400.30);
		add(videoGenerics, videoGeneric);
		videoGeneric = new VideoGeneric("Canh sat hinh su", "Dai truyen hinh Viet Nam", 5000);
		add(videoGenerics, videoGeneric);
		videoGeneric = new VideoGeneric("Bong dung muon hat", "Dai truyen hinh TP HCM", 500.20);
		add(videoGenerics, videoGeneric);
		add(videoGenerics, videoGeneric);

		bookGeneric = findLast(bookGenerics);
		System.out.print("\nThe last book of the list: \n\t" + bookGeneric);

		videoGeneric = findLast(videoGenerics);
		System.out.print("\nThe last video of the list: \n\t" + videoGeneric);

		System.out.println("\nDanh sach Book truoc khi sap xep theo ten");
		System.out.println(bookGenerics);
		bookGenerics.sort(null);
		System.out.println("\nDanh sach Book sau khi sap xep theo ten");
		System.out.println(bookGenerics);
		bookGenerics.sort(new SortByPagesGeneric());
		System.out.println("\nDanh sach Book sau khi sap xep theo pages tang dan");
		System.out.println(bookGenerics);
		bookGenerics.sort(new SortByPagesInReverseOrderGeneric<>(new SortByPagesGeneric()));
		System.out.println("\nDanh sach Book sau khi sap xep theo pages giam dan");
		System.out.println(bookGenerics);
		
		System.out.println("\nDanh sach Video truoc khi sap xep theo ten");
		System.out.println(videoGenerics);
		videoGenerics.sort(null);
		System.out.println("\nDanh sach Video sau khi sap xep theo ten");
		System.out.println(videoGenerics);
		videoGenerics.sort(new SortByDurationGeneric());
		System.out.println("\nDanh sach Video sau khi sap xep theo duration tang dan");
		System.out.println(videoGenerics);
		videoGenerics.sort(new SortByDurationInReverseOrderGeneric<>(new SortByDurationGeneric()));
		System.out.println("\nDanh sach Video sau khi sap xep theo duration giam dan");
		System.out.println(videoGenerics);

	}

	private static <E> boolean add(Collection<E> E, E e) {
		if (e == null) {
			System.out.print("Please input item information for \" " + String.valueOf(e).toUpperCase() + " \"");
			return false;
		} else if (find(E, e)) {
			System.out.println("\nItem \"" + e.toString() + "\"\nalready exist in library");
			return false;
		} else {
			E.add(e);
			return true;
		}
	}

	private static <E> boolean find(Collection<E> E, E e) {
		if (e == null) {
			return false;
		} else {
			if (E.contains(e)) {
				return true;
			}
		}
		return false;
	}

	private static <E> E findLast(Collection<E> X) {
		E e = null;
		Iterator<E> itr = X.iterator();
		while (itr.hasNext()) {
			e = itr.next();
		}
		return e;
	}
}
