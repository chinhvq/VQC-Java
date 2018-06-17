package test.projectmanagement.datamodel;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Address {
	private SimpleIntegerProperty id = new SimpleIntegerProperty(0);
	private SimpleStringProperty ward = new SimpleStringProperty("");
	private SimpleStringProperty district = new SimpleStringProperty("");
	private SimpleStringProperty city = new SimpleStringProperty("");
	private SimpleStringProperty country = new SimpleStringProperty("");

	public Address(int id, String ward, String district, String city, String country) {
		this.id.set(id);
		this.ward.set(ward);
		this.district.set(district);
		this.city.set(city);
		this.country.set(country);
	}

	public int getId() {
		return id.get();
	}

	public void setId(int id) {
		this.id.set(id);
	}

	public String getWard() {
		return ward.get();
	}

	public void setWard(String ward) {
		this.ward.set(ward);
	}

	public String getDistrict() {
		return district.get();
	}

	public void setDistrict(String district) {
		this.district.set(district);
	}

	public String getCity() {
		return city.get();
	}

	public void setCity(String city) {
		this.city.set(city);
	}

	public String getCountry() {
		return country.get();
	}

	public void setCountry(String country) {
		this.country.set(country);
	}
}
