package test.property;

public class PropertyDemo {
	public static void main(String[] args) {
		MyNumber myNumber = new MyNumber(-5);
		myNumber.numberProperty().addListener((observation, oldValue, newValue) -> {
			System.out.println(observation + " : " + oldValue + " : " + newValue);
		});
		
		myNumber.setNumber(10);
		myNumber.setNumber(20);
	}
	
}

