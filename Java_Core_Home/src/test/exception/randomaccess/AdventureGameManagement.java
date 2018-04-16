package test.exception.randomaccess;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AdventureGameManagement {
	private static Locations locations = new Locations();
			
	
	public static void main(String[] args) throws IOException {
		
		Scanner scanner = new Scanner(System.in);	
        
        Map<String, String> vocabulary = new HashMap<>();
        vocabulary.put("QUIT", "Q");
        vocabulary.put("WEST", "W");
        vocabulary.put("NORTH", "N");
        vocabulary.put("SOUTH", "S");
        vocabulary.put("EAST", "E"); 
        		
		Location currentLocation = locations.getLocation(1);
		while(true) {
			System.out.println(currentLocation.getDescription());
			if (currentLocation.getLocationID() == 0 ) {
				break;	
			}			
			Map<String, Integer> exists = currentLocation.getExists();
			System.out.println("Available Exist ");
			for (String exist : exists.keySet()) {
				System.out.print(exist + " , ");
			}
			System.out.println();
			
			String direction = scanner.nextLine().toUpperCase();
			if(direction.length() > 1) {
				String[] words = direction.split(" ");
				for (String word : words) {
					if (vocabulary.containsKey(word)) {
						direction = vocabulary.get(word);
						break;
					}
				}
			}
			
			if (exists.containsKey(direction)) {
				currentLocation = locations.getLocation(currentLocation.getExists().get(direction));
			} else {
				System.out.println("You cannot go that direction");
			}			
		}	
		locations.close();
	}
}
