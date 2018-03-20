package home;

import java.util.Scanner;

public class Buoi4KiemTraSo {

	public static void main(String[] args) {
		Scanner sc = new Scanner (System.in);
		
		System.out.print("Nhap so de kiem tra\t");
		int nSo = sc.nextInt();		
		
		if (nSo == 1 || nSo == 3 || nSo == 7 || nSo == 9 || nSo == 15 || nSo == 20)
		{
			System.out.println(nSo);
		}
		else
		{
			System.out.println("I don't like this number");
		}
		sc.close();
	}
}
