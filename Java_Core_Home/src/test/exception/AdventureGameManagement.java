package test.exception;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AdventureGameManagement {
	private static Locations locations = new Locations();
			
	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);	
        
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
