package test.generic3;

import java.util.Comparator;

public class SortByDuration implements Comparator<Video>{

	@Override
	public int compare(Video video1, Video video2) {
		return (int) (video1.getDuration() - video2.getDuration());
	}

}
