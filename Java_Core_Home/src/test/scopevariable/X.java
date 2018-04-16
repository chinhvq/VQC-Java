package test.scopevariable;

import java.util.Scanner;

public class X {
	private int x;

	protected X(Scanner x) {
		System.out.print("Ban nhap vao 1 so\t");
		this.x = x.nextInt();
	};
	
	protected void x() {
		for (int x = 0; x <= this.x; x++) {
			System.out.println(String.format("%3d * %3d = %4d", x,this.x, x * this.x));			
		}
		
	}

}
