package test.property;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class MyNumber {
	private DoubleProperty number = new SimpleDoubleProperty();

	public double getNumber() {
		return number.get();
	}

	public void setNumber(double number) {
		this.number.set(number);
	}

	public DoubleProperty numberProperty() {
		return number;
	}

	public MyNumber(double number) {
		this.number.set(number);
	}

	public MyNumber() {
	}
	
	
	
	

}
