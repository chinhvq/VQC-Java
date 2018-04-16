package home.non_generic;

public class Book extends PublishMaterial implements Comparable<Book> {

	private final int numOfPage;

	protected Book(String name, String publisher, int numOfPage) {
		super(name, publisher);
		this.numOfPage = numOfPage;
	}

	protected int getNumOfPage() {
		return numOfPage;
	}

	@Override
	public String toString() {

		return "\n\tName : " + this.getName() + " \n\tPublisher : " + this.getPublisher() + " \n\tNumber of pages : "
				+ this.getNumOfPage() + " pages";
	}

	@Override
	public int compareTo(Book book) {
		return this.getName().compareTo(book.getName());
	}



}
