package test.generic3;

import java.util.Scanner;

public class MangDoiGiaTriGeneric {
	static Scanner scanner = new Scanner(System.in);
	static int nIndex1, nIndex2;

	public static <T> void main(String[] args) {

		System.out.print("Nhap so luong phan tu cua mang Interger \t");
		int nPhanTu = scanner.nextInt();
		Object[] array = (T[]) new Object[nPhanTu];
		// Integer[] intArray = new Integer[nPhanTu];
		// mang kieu in
		// for (int i = 0; i < array.length; i++) {
		// array[i] = (int) (i + Math.random() * 100);
		// }
		// mang kieu String
		for (int i = 0; i < array.length; i++) {
			array[i] = String.valueOf((int) (i + Math.random() * 100));
		}

		// for (int i = 0; i < array.length; i++) {
		// System.out.print("array [" + i + " ] = ");
		// array[i] = scanner.next();
		// }

		display(array);
		change(array, nIndex1, nIndex2);
		display(array);
	}

	@SuppressWarnings("unchecked")
	private static <T> void change(T[] t, int nIndex1, int nIndex2) {
		boolean bExit = true;

		do {
			System.out.print("\nBan nhap vi tri phan tu muon thay doi thu nhat \t");
			nIndex1 = scanner.nextInt();
			System.out.print("Ban nhap vi tri phan tu muon thay doi thu hai \t");
			nIndex2 = scanner.nextInt();

			if (nIndex1 == nIndex2) {
				System.out.println("\n***Ban vui long nhap 2 vi tri khac nhau***");
				System.out.println("Vui long nhap lai vi tri\n");
			} else if (nIndex1 == 0 || nIndex2 == 0) {
				System.out.println("\n***Vi tri nhap vao phai lon hon 0***");
				System.out.println("Vui long nhap lai vi tri\n");
			} else if (nIndex1 > t.length || nIndex2 > t.length) {
				System.out.println("\n***Vi tri cua phan tu cua ban phai nho hon do dai cua mang***");
				System.out.println("Vui long nhap lai vi tri\n");
			} else {
				bExit = false;
			}
		} while (bExit);

		for (int i = 0; i < t.length; i++) {
			if (i == (nIndex1 - 1) || i == (nIndex2 - 1)) {
				System.out.print("Ban nhap lai gia tri cho phan tu " + (i + 1) + "\t");
				t[i] = (T) scanner.next();
			}
		}

	}

	private static <T> void display(T[] t) {
		System.out.println("Mang duoc nhap vao la");
		int nIndex = 0;
		for (T i : t) {
			System.out.print(i + "\t");
			nIndex++;
			if (nIndex != 0 && nIndex % 10 == 0) {
				System.out.println();
			}
		}
	}

}
