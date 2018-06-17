package test.projectmanagement.datamodel;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Person {
	private SimpleIntegerProperty id = new SimpleIntegerProperty(0);
	private SimpleStringProperty firstname = new SimpleStringProperty("");
	private SimpleStringProperty lastName = new SimpleStringProperty("");
	private SimpleStringProperty gender = new SimpleStringProperty("");
	private SimpleStringProperty phoneNumber = new SimpleStringProperty("");
	private SimpleStringProperty email = new SimpleStringProperty("");
	private SimpleIntegerProperty age = new SimpleIntegerProperty(0);

	public Person(int id, String lastName,String firstname, String gender, String phoneNumber, String email, int age) {
		this.id.set(id);
		this.lastName.set(lastName);
		this.firstname.set(firstname);
		this.gender.set(gender);
		this.phoneNumber.set(phoneNumber);
		this.email.set(email);
		this.age.set(age);
	}

	public int getId() {
		return id.get();
	}

	public void setId(int id) {
		this.id.set(id);
	}

	public String getFirstname() {
		return firstname.get();
	}

	public void setFirstname(String firstname) {
		this.firstname.set(firstname);
	}

	public String getLastName() {
		return lastName.get();
	}

	public void setLastName(String lastName) {
		this.lastName.set(lastName);
	}

	public String getPhoneNumber() {
		return phoneNumber.get();
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber.set(phoneNumber);
	}

	public String getEmail() {
		return email.get();
	}

	public void setEmail(String email) {
		this.email.set(email);
	}

	public int getAge() {
		return age.get();
	}

	public void setAge(int age) {
		this.age.set(age);
	}

	public String getGender() {
		return gender.get();
	}

	public void setGender(String gender) {
		this.gender.set(gender);
	}

}
