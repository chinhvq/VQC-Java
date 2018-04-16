package home.generic.qlthuvien_full;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import test.generic3.Book;

public class ThuVienGeneric<T, E1, E2> {
	private final T name;
	private final List<E1> books;
	private final List<E2> videos;

	protected ThuVienGeneric(T name) {
		this.name = name;
		this.books = new ArrayList<>();
		this.videos = new ArrayList<>();		
	}	
	
	protected List<E1> getBooks() {
		return books;
	}

	protected List<E2> getVideos() {
		return videos;
	}

	protected T getName() {
		return name;
	}

	protected <E> boolean add(Collection<E> E, E e) {
		if (e == null) {
			System.out.println("Please input item information for \" " + String.valueOf(e).toUpperCase() + " \"");
			return false;
		} else if (find(E, e)) {
			System.out.println("Item \""+ e.toString() + "\"\nalready exist in library");			
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
	
	protected <E> E findLast(Collection<E> collection) {
		E e = null; 
		Iterator<E> itr = collection.iterator();
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
