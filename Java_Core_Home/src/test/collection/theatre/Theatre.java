package test.collection.theatre;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Theatre {
	private String theatreName;
	private List<Seat> seats = new ArrayList<>();
	static final Comparator<Theatre.Seat> PRICE_ORDER = new Comparator<Theatre.Seat>() {

		@Override
		public int compare(Seat seat1, Seat seat2) {
			if (seat1.getPrice() > seat2.getPrice()) {
				return 1;
			} else if (seat1.getPrice() < seat2.getPrice()) {
				return -1;
			} else {
				return 0;
			}
		}
	};

	protected Theatre(String theatreName, int numRows, int seatPerRows) {
		this.theatreName = theatreName;
		int lastRow = 'A' + numRows - 1;
		// char ch = (char) lastRow;
		// System.out.println(ch);
		for (char row = 'A'; row <= lastRow; row++) {
			for (int seatNum = 1; seatNum <= seatPerRows; seatNum++) {
				double price = 12.00;
				if (row < 'D' && (seatNum > 4 && seatNum < 9)) {
					price = 14.00;
				} else if (row > 'F' || (seatNum < 4 || seatNum > 9)) {
					price = 7.00;
				}
				Seat seat = new Seat(row + String.format("%02d", seatNum), price);
				seats.add(seat);
			}
		}
	}

	protected String getTheatreName() {
		return theatreName;
	}

	public boolean reserveSeat(String seatNumber) {
		Seat seat = new Seat(seatNumber);
		int foundSeat = Collections.binarySearch(seats, seat, null);
		if(foundSeat >= 0) {
			return seats.get(foundSeat).reserve();
		} else {
			System.out.println("There is no seat " + seatNumber);
			return false;
		}

		// old way using equal()
		// Seat requestedSeat = null;
		// for (Seat seat : seats) {
		// if (seat.getSeatNumber().equals(seatNumber)) {
		// requestedSeat = seat;
		// break;
		// }
		// }
		// if(requestedSeat == null) {
		// System.out.println("Seat number " + seatNumber + " is not available");
		// return false;
		// }
		//
		// return requestedSeat.reserve();
	}

	public Collection<Seat> getSeat() {
		return seats;
//		int lineDown = 0;
//		for (Seat seat : seats) {
//			System.out.print(seat.getSeatNumber() + "\t");
//			lineDown++;
//			if (lineDown % 10 == 0 && lineDown != 0) {
//				System.out.println();
//			}
//		}
	}

	// private Seat Class using Comparable to use BinarySearch function
	protected class Seat implements Comparable<Seat> {
		private final String seatNumber;
		private double price;
		private boolean reserved = false;

		
		protected Seat(String seatNumber) {
			this.seatNumber = seatNumber;
		}

		protected Seat(String seatNumber, double price) {
			this.seatNumber = seatNumber;
			this.price = price;
		}

		protected String getSeatNumber() {
			return seatNumber;
		}
		
		protected double getPrice() {
			return price;
		}

		protected boolean reserve() {
			if (!this.reserved) {
				this.reserved = true;
				System.out.println("The seat number " + seatNumber + " was successfully reserved.");
				return true;
			} else {
				System.out
						.println("Sorry! this seat has been already reserved. Please help to reserve another seat!!!");
				return false;
			}
		}

		protected boolean cancel() {
			if (this.reserved) {
				this.reserved = false;
				System.out.println("The reservation of seat number " + seatNumber + " has been cancelled");
				return true;
			} else {
				return false;
			}
		}

		@Override
		public int compareTo(Seat seat) {
			return this.getSeatNumber().compareToIgnoreCase(seat.getSeatNumber());
		}
	}

}
