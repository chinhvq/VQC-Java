package test.setinterface.satellite;

public class DwarfPlanet extends HeavenlyBody {

	protected DwarfPlanet(String name, double orbitalPeriod) {
		super(name, orbitalPeriod, BodyTypes.DWARF_PLANNET);
	}

}
