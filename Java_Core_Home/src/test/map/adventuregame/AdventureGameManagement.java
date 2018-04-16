package test.map.adventuregame;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AdventureGameManagement {
	private static Map<Integer, Location> locations = new HashMap<Integer, Location>();
	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		Map<String , Integer> tempExists = new HashMap<>();	
		locations.put(0, new Location(0, "Java computer", tempExists));
	
		tempExists.put("W", 2);
        tempExists.put("E", 3);
        tempExists.put("S", 4);
        tempExists.put("N", 5);
        locations.put(1, new Location(1, "brick building", tempExists));
        
        tempExists = new HashMap<>();
        tempExists.put("N", 5);
        locations.put(2, new Location(2, "top of the hill", tempExists));

        tempExists = new HashMap<>();
        tempExists.put("W", 1);
        tempExists.put("W", 1);
        locations.put(3, new Location(3, "small spring", tempExists));

        tempExists = new HashMap<>();
        tempExists.put("N", 1);
        tempExists.put("W", 2);
        locations.put(4, new Location(4, "beside the stream", tempExists));

        tempExists = new HashMap<>();
        tempExists.put("S", 1);
        tempExists.put("W", 2);
        locations.put(5, new Location(5, "in the forest", tempExists));
        
        Map<String, String> vocabulary = new HashMap<>();
        vocabulary.put("QUIT", "Q");
        vocabulary.put("WEST", "W");
        vocabulary.put("NORTH", "N");
        vocabulary.put("SOUTH", "S");
        vocabulary.put("EAST", "E"); 
        		
		int loc = 1;
		while(true) {
			System.out.println(locations.get(loc).getDescription());
			if (loc == 0 ) {
				break;	
			}			
			Map<String, Integer> exists = locations.get(loc).getExists();
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
				loc = exists.get(direction);
			} else {
				System.out.println("You cannot go that direction");
			}
			
		}		
	}

}
