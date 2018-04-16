package home.generic.qlthuvien;

public class VideoGeneric<T> extends PublishMaterial implements Comparable<VideoGeneric<T>>{
	private final double duration;

	protected VideoGeneric(String name, String publisher, double duration) {
		super(name, publisher);
		this.duration = duration;
	}

	protected double getDuration() {
		return duration;
	}

	@Override
	public String toString() {
		return "\n\tName : " + this.getName() + " \n\tPublisher : " + this.getPublisher() + " \n\tDuration : "
				+ String.format("%.2f", this.getDuration()) + " mins";
	}

	@Override
	public int compareTo(VideoGeneric<T> compareVideo) {
		return this.getName().compareTo(compareVideo.getName());
	}

}
