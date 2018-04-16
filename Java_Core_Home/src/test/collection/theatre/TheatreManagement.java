package test.collection.theatre;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TheatreManagement {

	public static void main(String[] args) {
		Theatre theatre = new Theatre("CVG", 5, 10);
		System.out.println("List of seat at " + theatre.getTheatreName());
		theatre.getSeat();
		
		if(theatre.reserveSeat("A07")) {
			System.out.println("Please pay for reservation of  the seat ");
		}
		
		if(theatre.reserveSeat("A07")) {
			System.out.println("Please pay for reservation of  the seat ");
		} 	
		
		System.out.println("\nList of Seat in theatre " + theatre.getTheatreName());
		List<Theatre.Seat> listSeats = new ArrayList<>(theatre.getSeat());
		printList(listSeats);
		
		System.out.println("\nReserved Seat List");
		List<Theatre.Seat> reverseSeats = new ArrayList<>(theatre.getSeat());
		Collections.reverse(reverseSeats);
		printList(reverseSeats);
		System.out.println("\nPrice Based Seat List");
		List<Theatre.Seat> priceBasedSeats = new ArrayList<>(theatre.getSeat());
		Collections.sort(priceBasedSeats, theatre.PRICE_ORDER);
		printList(priceBasedSeats);
	}
	
	protected static void printList(List<Theatre.Seat> listSeat) {
		int lineDown = 0;
		for (Theatre.Seat seat : listSeat) {
			System.out.print(seat.getSeatNumber() + " " + String.format("%4.1f", seat.getPrice()) + "\t");			
			lineDown++;
			if (lineDown % 6 == 0 && lineDown !=0) {
				System.out.println();
			
			}
		}
	}

}
