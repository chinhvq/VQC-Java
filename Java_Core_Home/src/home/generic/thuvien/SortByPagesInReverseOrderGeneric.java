package home.generic.thuvien;

import java.util.Comparator;

public class SortByPagesInReverseOrderGeneric<T extends BookGeneric> implements Comparator<T> {
	private Comparator<T> originComparator;

	protected SortByPagesInReverseOrderGeneric(Comparator<T> originComparator) {
		this.originComparator = originComparator;
	}

	@Override
	public int compare(T t1, T t2) {
		return t2.getNumOfPage() - t1.getNumOfPage();
	}

}
