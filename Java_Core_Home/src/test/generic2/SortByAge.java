package test.generic2;

import java.util.Comparator;

public class SortByAge implements Comparator<Person> {

	@Override
	public int compare(Person person1, Person person2) {
		return Integer.compare(person1.getAge(), person2.getAge());
	}

	
}
