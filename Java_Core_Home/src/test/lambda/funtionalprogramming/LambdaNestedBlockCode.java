package test.lambda.funtionalprogramming;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.IntUnaryOperator;
import java.util.function.Predicate;

import home.Main;

public class LambdaNestedBlockCode {

	public static void main(String[] args) {

		Employee chinh = new Employee("VUONG CHINH", 35);
		Employee tu = new Employee("NGUYEN TU", 21);
		Employee maianh = new Employee("TRAN MAI ANH", 24);
		Employee bao = new Employee("DO BAO", 20);

		List<Employee> employeeList = new ArrayList<>();
		employeeList.add(chinh);
		employeeList.add(tu);
		employeeList.add(maianh);
		employeeList.add(bao);

		// enhance for loop with lambda
		System.out.println("for each loop with lambda");
		employeeList.forEach(employee -> System.out.println(employee.getName() + "\t : " + employee.getAge()));

		printEmployee(employeeList, "Employee over 30", employee -> employee.getAge() > 30);
		printEmployee(employeeList, "Employee under 30", employee -> employee.getAge() < 30);

		// we can use Predicate anonymous class however we rarely used nowadays
		printEmployee(employeeList, "Employee under 22", new Predicate<Employee>() {

			@Override
			public boolean test(Employee employee) {
				return employee.getAge() < 22;
			}
		});

		System.out.println("====================");
		System.out.println("Last name only");
		employeeList.forEach(employee -> {
			String lastName = employee.getName().substring(employee.getName().indexOf(" ") + 1);
			System.out.println(lastName + "\t : " + employee.getAge());
		});

		// using Function interface to get last name
		Function<Employee, String> getLastName = employee -> {
			return employee.getName().substring(employee.getName().indexOf(" ") + 1);
		};
		
		Function<Employee, String> getFirstName = employee -> {
			return employee.getName().substring(0, employee.getName().indexOf(" "));
		};

		System.out.println("====================");
		System.out.println("Last name only using Function interface");
		employeeList.forEach(employee -> System.out.println(getLastName.apply(employee) + "\t : " + employee.getAge()));

		System.out.println("====================");
		System.out.println("Randomdy get Last name & Firstname using Function interface");
		Random booleanRandom = new Random();
		for (Employee employee : employeeList) {
			if (booleanRandom.nextBoolean()) {
				System.out.println(getAName(getFirstName, employee));
			} else {
				System.out.println(getAName(getLastName, employee));
			}
		}

		Function<Employee, String> lowercase = employee -> employee.getName().toLowerCase();
		Function<String, String> lastname = name -> name.substring(name.indexOf(" ") + 1);
		Function<Employee, String> lastNameLowerCase = lowercase.andThen(lastname);
		System.out.println("====================");
		System.out.println("Lowercase Last name only using chained Function");
		employeeList.forEach(
				employee -> System.out.println(lastNameLowerCase.apply(employee) + "\t : " + employee.getAge()));
		
		System.out.println("====================");
		System.out.println("Lowercase Last name with Age using BiFunction");
		BiFunction<String, Employee, String> lastNameWithAge = (name , employee ) -> name.concat("\t : " + employee.getAge());
		employeeList.forEach(employee -> System.out.println(lastNameWithAge.apply(lastname.apply(employee.getName()), employee)));
		
		System.out.println("====================");
		System.out.println("Unary Operator demo");
		IntUnaryOperator intIncreaseBy5 = i -> i + 5;
		System.out.println(intIncreaseBy5.applyAsInt(95));
		
	}

	// print employee using Predicate interface which return a boolean based on the
	// inputed condition (age)
	private static void printEmployee(List<Employee> employeeList, String ageText, Predicate<Employee> ageCondition) {
		System.out.println("====================");
		System.out.println(ageText);
		for (Employee employee : employeeList) {
			if (ageCondition.test(employee)) {
				System.out.println(employee.getName() + "\t : " + employee.getAge());
			}
		}
	}

	private static String getAName(Function<Employee, String> getname, Employee employee) {
		return getname.apply(employee) + "\t : " + employee.getAge();
	}
}
