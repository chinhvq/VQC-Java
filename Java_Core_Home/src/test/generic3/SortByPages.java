package test.generic3;

import java.util.Comparator;

public class SortByPages implements Comparator<Book>{

	@Override
	public int compare(Book book1, Book book2) {
		return book1.getNumOfPage() - book2.getNumOfPage();
	}

}
