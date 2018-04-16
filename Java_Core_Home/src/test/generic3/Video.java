package test.generic3;

public class Video extends PublishMaterial implements Comparable<Video>{
	private final double duration;

	protected Video(String name, String publisher, double duration) {
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
	public int compareTo(Video video) {
		return this.getName().compareTo(video.getName());
	}

}
