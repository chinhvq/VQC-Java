package test.setinterface.satellite;

public class Moon extends HeavenlyBody {

	protected Moon(String name, double orbitalPeriod) {
		super(name, orbitalPeriod, BodyTypes.MOON);
	}

}
