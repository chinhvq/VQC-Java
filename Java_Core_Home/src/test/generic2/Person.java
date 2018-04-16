package test.generic2;

public class Person implements Comparable<Person>{
	private final String name;
	private final int age;
	
	protected Person(String name, int age) {
		this.name = name;
		this.age = age;
	}	
	
	protected String getName() {
		return name;
	}

	protected int getAge() {
		return age;
	}

	@Override
	public String toString() {
		return "Person [" + name + " : " + age + "]";
	}

	@Override
	public int compareTo(Person person) {
		return this.getName().compareTo(person.getName());
	}

	
}
