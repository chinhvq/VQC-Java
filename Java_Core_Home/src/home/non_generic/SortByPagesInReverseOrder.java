package home.non_generic;

import java.util.Comparator;

public class SortByPagesInReverseOrder implements Comparator<Book> {
	private Comparator<Book> originComparator;	

	protected SortByPagesInReverseOrder(Comparator<Book> originComparator) {
		this.originComparator = originComparator;
	}

	@Override
	public int compare(Book book1, Book book2) {
		return -(book2.getNumOfPage() - book1.getNumOfPage());
	}

}
