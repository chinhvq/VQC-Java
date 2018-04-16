package test.generic2;

import java.util.Comparator;

public class SortByAgeInReverseOrder<T extends Person> implements Comparator<T>{
	private Comparator<T> orginComparator ;

	protected SortByAgeInReverseOrder(Comparator<T> orginComparator) {
		this.orginComparator = orginComparator;
	}

	public int compare(T o1, T o2) {
		return orginComparator.compare(o1, o2) * -1;
	}

}
