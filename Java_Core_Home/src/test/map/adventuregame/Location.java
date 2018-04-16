package test.map.adventuregame;

import java.util.HashMap;
import java.util.Map;

final public class Location {
	private final int locationID;
	private final String description;
	private final Map<String, Integer> exists;
	
	protected Location(int locationID, String description, Map<String, Integer> exists) {
		this.locationID = locationID;
		this.description = description;
		if (exists != null) {
			this.exists = new HashMap<>(exists);
		} else {
			this.exists = new HashMap<>();
		}
		
		exists.put("Q", 0);
	}
	
//	protected boolean addExit(String direction, int location) {
//		if(exists.containsKey(direction)) {
//			System.out.println("Direction " + direction + " already existed in " + this.description + " --- Direction was not added");
//			return false;
//		} else {
//			exists.put(direction, location);			
//			return true;
//		}
//	}

	protected int getLocationID() {
		return locationID;
	}

	protected String getDescription() {
		return description;
	}

	protected Map<String, Integer> getExists() {
		return new HashMap<String, Integer>(this.exists);
	}
}
