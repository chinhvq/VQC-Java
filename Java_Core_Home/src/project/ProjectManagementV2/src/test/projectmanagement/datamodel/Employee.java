package test.projectmanagement.datamodel;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Employee extends Person {
	private SimpleStringProperty title = new SimpleStringProperty("");
	private SimpleIntegerProperty department_Id = new SimpleIntegerProperty(0);
	private SimpleStringProperty deptName = new SimpleStringProperty("");

	public Employee(int id, String lastName, String firstname, String gender, String phoneNumber, String email, int age,
			String title, int department_Id) {
		super(id, lastName, firstname, gender, phoneNumber, email, age);
		this.title.set(title);
		this.department_Id.set(department_Id);
	}

	public Employee(int id, String lastName, String firstname, String gender, String phoneNumber, String email, int age,
			String title, String deptName) {
		super(id, lastName, firstname, gender, phoneNumber, email, age);
		this.title.set(title);
		this.deptName.set(deptName);
	}

	public String getTitle() {
		return title.get();
	}

	public void setTitle(String title) {
		this.title.set(title);
	}

	public int getDepartment_Id() {
		return department_Id.get();
	}

	public void setDepartment_Id(int department_Id) {
		this.department_Id.set(department_Id);
	}

	public String getDeptName() {
		return deptName.get();
	}

	public void setDeptName(String deptName) {
		this.deptName.set(deptName);
	}
}
