package home.generic.qlthuvien_full;

import java.util.Comparator;

public class SortByDurationGeneric<T1, T2,T3> implements Comparator<VideoGeneric<T1, T2, T3>> {

	@Override
	public int compare(VideoGeneric<T1, T2, T3> o1, VideoGeneric<T1, T2, T3> o2) {
		return o1.getDuration().toString().compareTo(o2.getDuration().toString());
	}

}
