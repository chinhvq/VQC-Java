package test.projectmanagement.datamodel;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Department {
	private SimpleIntegerProperty id = new SimpleIntegerProperty(0);
	private SimpleStringProperty deptName = new SimpleStringProperty("");

	public Department(int id, String deptName) {
		this.id.set(id);
		this.deptName.set(deptName);
	}

	public int getId() {
		return id.get();
	}

	public void setId(int id) {
		this.id.set(id);
	}

	public String getDeptName() {
		return deptName.get();
	}

	public void setDeptName(String deptName) {
		this.deptName.set(deptName);
	}
}
