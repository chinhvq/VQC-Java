package home;

import java.util.Scanner;

import home.Person;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String sName, sAddress;
		int nAge;
		Person[] pPerson = new Person[2];		

		for (int i = 0; i < pPerson.length; i++) {			
			System.out.print("Nhap ten nhan vien:\t");			
			sName = sc.nextLine();				
			System.out.print("Nhap tuoi nhan vien:\t");
			nAge = sc.nextInt();					
			System.out.print("Nhap dia chi nhan vien:\t");
			sc.nextLine();
			sAddress = sc.nextLine();
			Person ps = new Person(sName, nAge, sAddress);			
		}
		
		for (int i = 0; i < pPerson.length; i++) {
			System.out.println("Nhan vien " + pPerson[i].getsName() + "\tTuoi " + pPerson[i].getnAge() + "\tDia Chi " + pPerson[i].getsAddress());
		}
		
		System.out.println("Ket Thuc");
	}
}
