package test.setinterface.satellite;

import java.util.HashSet;
import java.util.Set;

public abstract class HeavenlyBody {
	private final Key key;
	private final double orbitalPeriod;
	private final Set<HeavenlyBody> satellites;

	protected enum BodyTypes {
		STAR, PLANNET, DWARF_PLANNET, MOON, COMET, ASTEROID
	}

	protected HeavenlyBody(String name, double orbitalPeriod, BodyTypes bodyType) {
		key = new Key(name, bodyType);
		this.orbitalPeriod = orbitalPeriod;
		satellites = new HashSet<>();
	}

	protected double getOrbitalPeriod() {
		return orbitalPeriod;
	}	

	protected Key getKey() {
		return key;
	}

	protected Set<HeavenlyBody> getSatellites() {
		return new HashSet<>(this.satellites);
	}

	public boolean addSattelite(HeavenlyBody moon) {
		return this.satellites.add(moon);
	}

	protected static Key makeKey(String name, BodyTypes bodyTypes) {
		return new Key(name, bodyTypes);
	}
	
	// because our Set planet is not immutable, we still add moon in to the planet
	// => to ensure no duplication in the Set planets, we need to override the
	// equals // and hashCode method to ensure the Object name is matched / equal =>
	// no duplication items is allowed
	@Override
	public final boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj instanceof HeavenlyBody) {
			HeavenlyBody theObject = (HeavenlyBody) obj;
			return this.key.equals(theObject.getKey());
		}
		return false;
	}

	@Override
	public final int hashCode() {
		return this.key.hashCode();
	}

	@Override
	public String toString() {
		return "[name=" + this.key.name + ", orbitalPeriod=" + orbitalPeriod + ", satellites=" + satellites
				+ ", bodyType=" + this.key.bodyTypes + "]";
	}
	
	
	protected static final class Key {
		private String name;
		private BodyTypes bodyTypes;
		
		private Key(String name, BodyTypes bodyTypes) {
			this.name = name;
			this.bodyTypes = bodyTypes;
		}

		protected String getName() {
			return name;
		}

		protected BodyTypes getBodyTypes() {
			return bodyTypes;
		}
		
		@Override
		public String toString() {
			return "Key [name=" + name + ", bodyTypes=" + bodyTypes + "]";
		}

		@Override
		public int hashCode() {
			return this.getName().hashCode() + 57 + this.getBodyTypes().hashCode();
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			Key key = (Key) obj;
			if (this.getName().equals(key.getName())) {
				return this.getBodyTypes() == key.getBodyTypes();
			}
			return false;
		}	
		
	}

}
