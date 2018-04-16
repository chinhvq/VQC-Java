package home.generic.qlthuvien_full;

public class BookGeneric<T1, T2, T3> extends PublishMaterialGeneric<T1, T2> implements Comparable<BookGeneric<T1, T2, T3>> {
	private final T3 numOfPage;
	
	protected BookGeneric(T1 name, T2 publisher, T3 numOfPage) {
		super(name, publisher);
		this.numOfPage = numOfPage;
	}	

	protected T3 getNumOfPage() {
		return numOfPage;
	}

	@Override
	public String toString() {

		return "\n\tName : " + this.getName() + " \n\tPublisher : " + this.getPublisher() + " \n\tNumber of pages : "
				+ this.getNumOfPage() + " pages";
	}

	@Override
	public int compareTo(BookGeneric<T1, T2, T3> o) {
		return this.getName().toString().compareTo(o.getName().toString());
	}



}
