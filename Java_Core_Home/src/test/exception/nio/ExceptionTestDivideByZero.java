package test.exception.nio;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ExceptionTestDivideByZero {
	public static void main(String[] args) {
		try {
			int result = divide();
			System.out.println("result = " + result);
		} catch (NoSuchElementException | ArithmeticException e) {
			System.out.println(e.toString());
			System.out.println("Unable to perform division, auto shutdown");
		}		
	}

	private static int divide() {
		int x, y;
		
//		try {
			x = getInt();
			y = getInt();
			System.out.println("x is " + x + " y is " + y);
			return x / y;
//		} catch (NoSuchElementException e) {
//			throw new ArithmeticException("NO SUITABLE INPUT");
//		} catch (ArithmeticException e) {
//			throw new ArithmeticException("YOU ARE ATTEMP TO DIVDE BY ZERO");
//		}		
	}

	private static int getInt() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Please input an integer\t");
		while(true) {
			try {
				return scanner.nextInt();
			} catch (InputMismatchException e) {
				scanner.nextLine();
				System.out.println("Please input only from 0 to 9");
				System.out.print("Please input an integer\t");
			}
		}		
	}	
}
