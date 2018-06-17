package test.projectmanagement.datamodel;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Project {
	private SimpleIntegerProperty id = new SimpleIntegerProperty(0);
	private SimpleStringProperty projectName = new SimpleStringProperty("");
	private SimpleStringProperty status = new SimpleStringProperty("");
	private SimpleStringProperty projectCategory = new SimpleStringProperty("");

	public Project(int id, String projectName, String status, String projectCategory) {
		this.id.set(id);
		this.projectName.set(projectName);
		this.status.set(status);
		this.projectCategory.set(projectCategory);
	}

	public int getId() {
		return id.get();
	}

	public void setId(int id) {
		this.id.set(id);
	}
	
	public String getProjectName() {
		return projectName.get();
	}

	public void setProjectName(String projectName) {
		this.projectName.set(projectName);
	}

	public String getStatus() {
		return status.get();
	}

	public void setStatus(String status) {
		this.status.set(status);
	}

	public String getProjectCategory() {
		return projectCategory.get();
	}

	public void setProjectCategory(String projectCategory) {
		this.projectCategory.set(projectCategory);
	}
	

}
