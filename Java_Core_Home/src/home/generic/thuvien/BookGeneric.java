package home.generic.thuvien;

public class BookGeneric<T> extends PublishMaterial implements Comparable<BookGeneric<T>> {
	private final int numOfPage;
	
	protected BookGeneric(String name, String publisher, int numOfPage) {
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
	public int compareTo(BookGeneric<T> compareBook) {
		return this.getName().compareTo(compareBook.getName());
	}

}
