package home.generic.qlthuvien_full;

import java.util.Comparator;

public class SortByPagesInReverseOrderGeneric<T1, T2, T3> implements Comparator<BookGeneric<T1, T2, T3>> {
	private Comparator<BookGeneric<T1, T2, T3>> originComparator;

	protected SortByPagesInReverseOrderGeneric(Comparator<BookGeneric<T1, T2, T3>> originComparator) {
		this.originComparator = originComparator;
	}

	@Override
	public int compare(BookGeneric<T1, T2, T3> o1, BookGeneric<T1, T2, T3> o2) {
		return o2.getNumOfPage().toString().compareTo(o1.getNumOfPage().toString());
	}




}
