package home.generic.qlthuvien_full;

import java.util.Comparator;

public class SortByDurationInReverseOrderGeneric<T1, T2, T3> implements Comparator<VideoGeneric<T1, T2, T3>> {
	
	private Comparator<VideoGeneric<T1, T2, T3>> originComparator;

	protected SortByDurationInReverseOrderGeneric(Comparator<VideoGeneric<T1, T2, T3>> originComparator) {
		this.originComparator = originComparator;
	}

	@Override
	public int compare(VideoGeneric<T1, T2, T3> o1, VideoGeneric<T1, T2, T3> o2) {
		return o2.getDuration().toString().compareTo(o1.getDuration().toString());
	}

}
