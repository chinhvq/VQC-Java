package home.generic.thuvien;

import java.util.Comparator;

public class SortByDurationGeneric<T> implements Comparator<VideoGeneric<T>> {

	@Override
	public int compare(VideoGeneric<T> video1, VideoGeneric<T> video2) {
		return (int) (video1.getDuration() - video2.getDuration());
	}
}
