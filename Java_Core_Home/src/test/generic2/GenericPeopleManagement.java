package test.generic2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class GenericPeopleManagement {

	public static void main(String[] args) {
		List<Person> persons = new ArrayList<Person>();
		Person person = new Person("Chinh", 36);
		persons.add(person);
		person = new Person("Bao", 20);
		persons.add(person);
		person = new Person("Dung", 26);
		persons.add(person);
		person = new Person("Anh", 22);
		persons.add(person);

		System.out.println("\t" + persons);

		persons.sort(new SortByAge());
		System.out.println("Person List after Sort by Age Comparator");
		System.out.println("\t" + persons);
		persons.sort(new SortByAgeInReverseOrder<>(new SortByAge()));
		System.out.println("Person List after Sort by Age In Reversed Order");
		System.out.println("\t" + persons);

		// Using Comparable by default which apply compareTo() in Person class
		persons.sort(null);
		System.out.println("Person List after Sort by Name using Comparable");
		System.out.println("\t" + persons);

		System.out.println("Person who youngest - nonGeneric");
		System.out.println("\t" + min(persons, new SortByAge()));
		System.out.println("Person who eldest - Generic");
		System.out.println("\t" + max(persons, new SortByAge()));

		// test max() with other data type like Integer, Double, String 
		Integer[] intArray = { 3, 54, 23, 656, 45, 767, 23 };
		List<Integer> intList = new ArrayList<>(Arrays.asList(intArray));
		System.out.println("Gia tri lon nhat cua mang Integer la\t" + max(intList, Integer::compare));

		Double[] doubleArr = { 0.23, 3.21, 32.221, 2.21213 };
		List<Double> doubleList = new ArrayList<>(Arrays.asList(doubleArr));
		System.out.println("Gia tri lon nhat cua mang Double la\t" + max(doubleList, Double::compare));
		
		String[] strArray = {"hom", "nay", "la", "Thu", "may"};
		List<String> strList = new ArrayList<>(Arrays.asList(strArray));
		System.out.println("Gia tri lon nhat cua mang String la\t" + max(strList, String::compareTo));
	}

	// min() if not using generic
	private static Person min(List<Person> persons, Comparator<Person> comparator) {
		Person minPerson = persons.get(0);
		if (persons.isEmpty()) {
			System.out.println("There is nobody in the person list");
		} else {
			for (Person person : persons) {
				if (comparator.compare(minPerson, person) > 0) {
					minPerson = person;
				}
			}
		}

		return minPerson;
	}

	// max() using Generic
	private static <T> T max(List<T> list, Comparator<T> comparator) {
		T maxT = list.get(0);
		if (list.isEmpty()) {
			System.out.println("There is nobody elemement in the list");
		} else {
			for (T t : list) {
				if (comparator.compare(maxT, t) < 0) {
					maxT = t;
				}
			}
		}

		return maxT;
	}
}
