package test.setinterface.satellite;

public class Planet extends HeavenlyBody {

	protected Planet(String name, double orbitalPeriod) {
		super(name, orbitalPeriod, BodyTypes.PLANNET);
	}
	
	@Override
	public boolean addSattelite(HeavenlyBody moon) {
		if (moon.getKey().getBodyTypes() == BodyTypes.MOON) {
			return super.addSattelite(moon);
		} else {
			return false;
		}		
	}		

}
