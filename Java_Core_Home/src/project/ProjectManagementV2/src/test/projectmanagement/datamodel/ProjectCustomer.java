package test.projectmanagement.datamodel;

import javafx.beans.property.SimpleStringProperty;

public class ProjectCustomer {
	private SimpleStringProperty customerName = new SimpleStringProperty();
	private SimpleStringProperty businessType = new SimpleStringProperty();
	private SimpleStringProperty projectName = new SimpleStringProperty();
	private SimpleStringProperty status = new SimpleStringProperty();
	private SimpleStringProperty projectCategory = new SimpleStringProperty();

	public ProjectCustomer(String customerName, String businessType, String projectName, String status,
			String projectCategory) {
		this.customerName.set(customerName);
		this.businessType.set(businessType);
		this.projectName.set(projectName);
		this.status.set(status);
		this.projectCategory.set(projectCategory);
	}

	public String getCustomerName() {
		return customerName.get();
	}

	public void setCustomerName(String customerName) {
		this.customerName.set(customerName);
	}

	public String getBusinessType() {
		return businessType.get();
	}

	public void setBusinessType(String businessType) {
		this.businessType.set(businessType);
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
