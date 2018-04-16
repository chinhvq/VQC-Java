package home.generic.qlthuvien;

import java.util.Comparator;

public class SortByPagesGeneric<T> implements Comparator<BookGeneric<T>>{

	@Override
	public int compare(BookGeneric<T> book1, BookGeneric<T> book2) {
		return book1.getNumOfPage() - book2.getNumOfPage();
	}

}
