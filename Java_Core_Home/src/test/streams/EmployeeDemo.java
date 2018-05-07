package test.streams;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EmployeeDemo {

	public static void main(String[] args) {
		Employee john = new Employee("John Doe", 30);
		Employee jane = new Employee("Jane Deer", 25);
		Employee jack = new Employee("Jack Hill", 40);
		Employee snow = new Employee("Snow White", 22);
		Employee david = new Employee("David Beckam", 40);
		Employee maria = new Employee("Maria Hope", 22);

		Department hr = new Department("Human Resource");
		hr.addEmployee(jane);
		hr.addEmployee(jack);
		hr.addEmployee(snow);
		hr.addEmployee(maria);
		Department accounting = new Department("Accounting");
		accounting.addEmployee(john);
		accounting.addEmployee(david);

		List<Department> departments = new ArrayList<>();
		departments.add(hr);
		departments.add(accounting);
		System.out.println("===============================");
		System.out.println("Employee List by For Enhance Loop");
		departments.forEach(dep -> System.out.println(dep.getName() + "\n\t" + dep.getEmployee()));

		System.out.println("===============================");
		System.out.println("Employee List by FlapMap and Streams");
		departments.stream().flatMap(dep -> dep.getEmployee().stream()).forEach(System.out::println);

		System.out.println("===============================");
		System.out.println("Employee List with Age");
		departments.stream().flatMap(dep -> dep.getEmployee().stream())
				.forEach(emp -> System.out.println("\t" + emp.getName() + "\t: " + emp.getAge()));

		System.out.println("===============================");
		System.out.println("Employee name start with J");
		List<Employee> employeeListStartWithJ = departments.stream().flatMap(dep -> dep.getEmployee().stream())
				.filter(emp -> emp.getName().toUpperCase().startsWith("J")).collect(Collectors.toList());
		System.out.println(employeeListStartWithJ);

		System.out.println("===============================");
		System.out.println("Employee name start with J and sorted");
		List<Employee> employeeListStartWithJSorted = departments.stream().flatMap(dep -> dep.getEmployee().stream())
				.filter(emp -> emp.getName().toUpperCase().startsWith("J"))
				.sorted((emp1, emp2) -> emp1.getName().compareTo(emp2.getName())).collect(Collectors.toList());
		System.out.println(employeeListStartWithJSorted);

		System.out.println("===============================");
		System.out.println("Employee name group in same age");
		Map<Integer, List<Employee>> employeeGroupByAge = departments.stream()
				.flatMap(dep -> dep.getEmployee().stream()).collect(Collectors.groupingBy(emp -> emp.getAge()));
		for (Integer id : employeeGroupByAge.keySet()) {
			System.out.println(id + " - " + employeeGroupByAge.get(id));
		}

		System.out.println("===============================");
		System.out.println("Employee name group in same age");
		Map<Integer, List<Employee>> employeeGroupByAgeSorted = departments.stream()
				.flatMap(dep -> dep.getEmployee().stream())
				.sorted((emp1, emp2) -> emp1.getName().compareTo(emp2.getName()))
				.collect(Collectors.groupingBy(emp -> emp.getAge()));

		for (Integer id : employeeGroupByAgeSorted.keySet()) {
			System.out.println(id + " - " + employeeGroupByAgeSorted.get(id));
		}

		System.out.println("===============================");
		System.out.println("Youngest Employee");
		departments.stream().flatMap(dep -> dep.getEmployee().stream())
				.reduce((emp1, emp2) -> emp1.getAge() < emp2.getAge() ? emp1 : emp2)
				.ifPresent(emp -> System.out.println(emp.getName() + "\t: " + emp.getAge()));
	}
}
