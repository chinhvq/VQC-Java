package test.streams;

import java.util.ArrayList;
import java.util.List;

public class Department {
	private String name;
	private List<Employee> employees;
	
	protected Department(String name) {
		this.name = name;
		employees = new ArrayList<>();
	}	
	
	protected String getName() {
		return name;
	}

	protected void addEmployee(Employee employee) {
		employees.add(employee);
	}
	
	protected List<Employee> getEmployee() {
		return employees;
	}	
}
