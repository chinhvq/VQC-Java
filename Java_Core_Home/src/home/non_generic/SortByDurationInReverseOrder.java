package home.non_generic;

import java.util.Comparator;

public class SortByDurationInReverseOrder implements Comparator<Video> {
	
	private Comparator<Video> originComparator;

	protected SortByDurationInReverseOrder(Comparator<Video> originComparator) {
		this.originComparator = originComparator;
	}

	@Override
	public int compare(Video video1, Video video2) {
		return (int) (video2.getDuration() - video1.getDuration());
	}
	

}
