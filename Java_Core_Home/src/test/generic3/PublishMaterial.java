package test.generic3;

public class PublishMaterial {
	private final String name;
	private final String publisher;
	
	protected PublishMaterial(String name, String publisher) {
		this.name = name;
		this.publisher = publisher;
	}

	protected String getName() {
		return name;
	}

	protected String getPublisher() {
		return publisher;
	}

	@Override
	public String toString() {
		return "PublishMaterial [name=" + name + ", publisher=" + publisher + "]";
	}
	
	

}
