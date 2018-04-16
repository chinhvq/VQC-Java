package home.generic.qlthuvien_full;

public class VideoGeneric<T1, T2, T3> extends PublishMaterialGeneric<T1, T2> implements Comparable<VideoGeneric<T1, T2, T3>>{
	private final T3 duration;

	protected VideoGeneric(T1 name, T2 publisher, T3 duration) {
		super(name, publisher);
		this.duration = duration;
	}

	protected T3 getDuration() {
		return duration;
	}

	@Override
	public String toString() {
		return "\n\tName : " + this.getName() + " \n\tPublisher : " + this.getPublisher() + " \n\tDuration : "
				+ String.format("%s", this.getDuration()) + " mins";
	}

	@Override
	public int compareTo(VideoGeneric<T1, T2, T3> o) {
		return this.getName().toString().compareTo(o.getName().toString());
	}


}
