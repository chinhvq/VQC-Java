package home.generic.qlthuvien_full;

import java.util.Comparator;
public class SortByPagesGeneric<T2, T1, T3> implements Comparator<BookGeneric<T1, T2, T3>>{

	@Override
	public int compare(BookGeneric<T1, T2, T3> o1, BookGeneric<T1, T2, T3> o2) {
		return o1.getNumOfPage().toString().compareTo(o2.getNumOfPage().toString());
	}

}
