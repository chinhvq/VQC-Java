package test.generic3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class ThuVienGeneric {
	private final String name;
	List<Book> books;
	List<Video> videos;

	protected ThuVienGeneric(String name) {
		this.name = name;
		this.books = new ArrayList<>();
		this.videos = new ArrayList<>();		
	}	

	protected String getName() {
		return name;
	}

	protected <E> boolean add(Collection<E> E, E e) {
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

	private <E> boolean find(Collection<E> E, E e) {
		if(e == null) {
			return false;
		} else {
			if(E.contains(e)) {
				return true;
			}
		}
		return false;
	}
	
	protected <E> E findLast(Collection<E> X) {
		E e = null; 
		Iterator<E> itr = X.iterator();
		while(itr.hasNext()) {
			e = itr.next(); 
		}		
		return e;	
	}
	
	@Override
	public String toString() {
		return "ThuVien [name=" + name + "]" + "\n\tBooks= \t" + books + "\n\tVideos=\t" + videos;
	}
}
