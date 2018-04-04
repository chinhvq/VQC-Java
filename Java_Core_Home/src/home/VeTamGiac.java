package home;

import java.util.Scanner;

public class VeTamGiac {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Nhap do dai cua tam giac\t");
		int n = sc.nextInt();

		System.out.println("Ve tam giac sao vuong can:");
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= i; j++)
				System.out.print("* ");

			System.out.println();
		}

		System.out.println("Ve tam giac sao vuong can nguoc:");
		for (int i = n; i >= 1; i--) {
			for (int j = 1; j <= i; j++)
				System.out.print("* ");

			System.out.println();
		}

		System.out.println("Ve tam deu:");
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n - i; j++) {
				System.out.print(" ");
			}
			for (int j = 1; j <= i; j++) {
				System.out.print("* ");
			}
			System.out.println();
		}

		System.out.println("Ve tam deu nguoc:");
		for (int i = n; i >= 1; i--) {
			for (int j = 1; j <= n - i; j++) {
				System.out.print(" ");
			}
			for (int j = 1; j <= i; j++) {
				System.out.print("* ");
			}
			System.out.println("\n");
		}

		int k = 1;
		System.out.println("Ve tam giac Floyd:");
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= i; j++)
				System.out.printf("%3d", k++);

			System.out.println();
		}

	}

}
