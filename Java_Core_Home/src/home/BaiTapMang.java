package home;

import java.util.Scanner;

public class BaiTapMang {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Nhap so phan tu cho mang\t");
		int n = sc.nextInt();
		int[] arraySo = new int[n];
		System.out.println("Nhap gia tri cho mang");
		for (int i = 0; i < n; i++) {
			System.out.print("array [ " + i + " ] =\t");
			arraySo[i] = sc.nextInt();
		}

		for (int i : arraySo) {
			System.out.print(i + "\t");
		}

		// Sap xep mang
		int nTemp = 0;
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				if (arraySo[i] > arraySo[j]) {
					nTemp = arraySo[i];
					arraySo[i] = arraySo[j];
					arraySo[j] = nTemp;
				}
			}
		}

		System.out.println("\nMang sau khi sap xep la");
		for (int i : arraySo) {
			System.out.print(i + "\t");
		}

		System.out.println("\nPhan tu nho nhat la \t" + arraySo[0]);

		// tim trung binh cong cac phan tu chia het cho 3

		int nCount = 0;
		int nTong = 0;
		for (int i : arraySo) {
			if (i % 3 == 0 & i != 0) {
				nTong += i;
				nCount++;
			}
		}
		System.out.printf("Trung binh cong cua cac so chia het cho 3 la\t%.3f", (double) nTong / nCount);
	}
}
