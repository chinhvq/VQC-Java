package test.lambda;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import home.Main;

public class LambdaDemo {

	public static void main(String[] args) {
		// Anonymous class implement Runnable interface
		new Thread(new CodeToRun()).start();

		// Anonymous Runnable class
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("This Thread from Runnable");
				System.out.println("Hello Runnable");
			}
		}).start();

		// use lambda
		new Thread(() -> {
			System.out.println("This Thread from Runnable");
			System.out.println("Hello Lambda");
		}).start();

		Employee chinh = new Employee("CHINH", 35);
		Employee tu = new Employee("TU", 21);
		Employee maianh = new Employee("MAI ANH", 24);
		Employee bao = new Employee("BAO", 20);

		List<Employee> employeeList = new ArrayList<>();
		employeeList.add(chinh);
		employeeList.add(tu);
		employeeList.add(maianh);
		employeeList.add(bao);

		Collections.sort(employeeList, new Comparator<Employee>() {
			@Override
			public int compare(Employee employee1, Employee employee2) {
				return employee1.getName().compareTo(employee2.getName());
			}
		});
		System.out.println("List of employee after sorted by name");
		for (Employee employee : employeeList) {
			System.out.println("\t" + employee.getName() + " : " + employee.getAge());
		}

		Collections.sort(employeeList,
				(Employee employee1, Employee employee2) -> employee2.getName().compareTo(employee1.getName()));
		System.out.println("List of employee after sorted in reversed name order");
		for (Employee employee : employeeList) {
			System.out.println("\t" + employee.getName() + " : " + employee.getAge());
		}

		Collections.sort(employeeList, (employee1, employee2) -> employee1.getAge() - employee2.getAge());
		System.out.println("List of employee after sorted in age");
		for (Employee employee : employeeList) {
			System.out.println("\t" + employee.getName() + " : " + employee.getAge());
		}

		// using anonymous class
		String concatEmployeeName = doStringStuff(new UpperConcat() {
			@Override
			public String upperAndConcate(String s1, String s2) {
				return s1.toUpperCase().concat(" : ").concat(s2).toUpperCase();
			}
		}, employeeList.get(0).getName(), employeeList.get(1).getName());
		System.out.println("Result of Concat and Uppercase of two string \t" + concatEmployeeName);
		System.out.println();

		// using lambda
		String concatEmployeeName2 = doStringStuff((s1, s2) -> s1.toUpperCase().concat(" : ").concat(s2).toUpperCase(),
				employeeList.get(2).getName(), employeeList.get(3).getName());
		System.out.println("Result of Concat and Uppercase of two string \t" + concatEmployeeName2);

		AnotherClass anotherClass = new AnotherClass();
		String s = anotherClass.doSomething();
		System.out.println(s);
		s = anotherClass.doSomethingLambda();
		System.out.println(s);
	}

	public static final String doStringStuff(UpperConcat uc, String s1, String s2) {
		return uc.upperAndConcate(s1, s2);
	}
}

class CodeToRun implements Runnable {
	@Override
	public void run() {
		System.out.println("This Thread from Runnable");
		System.out.println("Hello Code To Run Class");
	}
}

class Employee {
	private String name;
	private int age;

	protected Employee(String name, int age) {
		this.name = name;
		this.age = age;
	}

	protected String getName() {
		return name;
	}

	protected void setName(String name) {
		this.name = name;
	}

	protected int getAge() {
		return age;
	}

	protected void setAge(int age) {
		this.age = age;
	}
}

interface UpperConcat {
	public String upperAndConcate(String s1, String s2);
}

class AnotherClass {
	public String doSomething() {
		System.out.println("The another class - class name is " + getClass().getSimpleName());
		return LambdaDemo.doStringStuff(new UpperConcat() {

			@Override
			public String upperAndConcate(String s1, String s2) {
				System.out.println("The anomyous class - class name is " + getClass().getSimpleName());
				return s1.toUpperCase().concat(" : ").concat(s2).toUpperCase();
			}
		}, "String 1", "String 2");
	}

	// Lambda expression is not a class ==> it is treated as a nested block of code
	// and has the same scope as the nested block
	public String doSomethingLambda() {
		UpperConcat uc = (s1, s2) -> {
			System.out.println("Lambda expression 's class is " + getClass().getSimpleName());
			String result = s1.toUpperCase().concat(" : ").concat(s2).toUpperCase();
			return result;
		};

		System.out.println("The Another Class - class name is " + getClass().getSimpleName());
		return LambdaDemo.doStringStuff(uc, "String 1", "String 2");
	}
}