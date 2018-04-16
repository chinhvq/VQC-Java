package test.exception;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

final public class Location implements Serializable {
	private static final long serialVersionUID = 1L;
	private final int locationID;
	private final String description;
	private final Map<String, Integer> exists;
	
	public Location(int locationID, String description, Map<String, Integer> exists) {
		this.locationID = locationID;
		this.description = description;
		if (exists != null) {
			this.exists = new LinkedHashMap<>(exists);
		} else {
			this.exists = new LinkedHashMap<>();
		}		
		this.exists.put("Q", 0);
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

	public int getLocationID() {
		return locationID;
	}

	public String getDescription() {
		return description;
	}

	public Map<String, Integer> getExists() {
		return new LinkedHashMap<String, Integer>(this.exists);
	}

	@Override
	public String toString() {
		return "Location [locationID=" + locationID + ", description=" + description + ", exists=" + exists + "]";
	}

	public void addExit(String direction, int destination) {
		exists.put(direction, destination);
		
	}
	
	
}