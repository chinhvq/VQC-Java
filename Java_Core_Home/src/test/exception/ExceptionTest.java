package test.exception;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ExceptionTest {

	public static void main(String[] args) {
		System.out.println("input = " + getInput());
		System.out.println("input = " + getInputLBYL());

	}

	private static int getInputLBYL() {
		Scanner scanner = new Scanner(System.in);
		boolean isValid = true;
		System.out.print("Ban nhap 1 so Integer: \t");
		String input = scanner.next();
		for (int i = 0; i < input.length(); i++) {
			if(!Character.isDigit(input.charAt(i))) {
				isValid = false;
				break;
			}
		}
		if(isValid) {
			return Integer.parseInt(input);
		} else {
			return 0;
		}		
	}

	private static int getInput() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Ban nhap 1 so Integer: \t");
		while(true) {
			try {
				return scanner.nextInt();
			} catch (InputMismatchException e) {				
				scanner.nextLine();
				System.out.print("Ban vui long chi nhap so tu 0 den 9\t");				
			}	
		}			
	}
}
