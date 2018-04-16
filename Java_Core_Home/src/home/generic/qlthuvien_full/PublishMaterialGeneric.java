package home.generic.qlthuvien_full;

public class PublishMaterialGeneric <T1, T2> {
	private final T1 name;
	private final T2 publisher;
	
	protected PublishMaterialGeneric(T1 name, T2 publisher) {
		this.name = name;
		this.publisher = publisher;
	}

	protected T1 getName() {
		return name;
	}

	protected T2 getPublisher() {
		return publisher;
	}

	@Override
	public String toString() {
		return "PublishMaterial [name=" + name + ", publisher=" + publisher + "]";
	}
	
	

}
