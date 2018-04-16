package home.generic.qlthuvien;

import java.util.Comparator;

public class SortByDurationInReverseOrderGeneric<T extends VideoGeneric<T>> implements Comparator<T> {
	
	private Comparator<T> originComparator;

	protected SortByDurationInReverseOrderGeneric(Comparator<T> originComparator) {
		this.originComparator = originComparator;
	}

	@Override
	public int compare(T t1, T t2) {
		return (int) (t2.getDuration() - t1.getDuration());
	}
}
