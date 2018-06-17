package test.projectmanagement.datamodel;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Customer {
	private SimpleIntegerProperty id = new SimpleIntegerProperty(0);
	private SimpleStringProperty customerName = new SimpleStringProperty("");
	private SimpleStringProperty businessType = new SimpleStringProperty("");
	
	public Customer(int id, String customerName, String businessType) {
		this.id.set(id);
		this.customerName.set(customerName);
		this.businessType.set(businessType);
	}

	public int getId() {
		return id.get();
	}

	public void setId(int id) {
		this.id.set(id);
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
	
}
