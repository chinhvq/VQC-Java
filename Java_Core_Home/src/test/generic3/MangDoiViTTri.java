package test.generic3;

import java.util.Scanner;

public class MangDoiViTTri {
	static Scanner scanner = new Scanner(System.in);
	static int nIndex1, nIndex2;

	public static void main(String[] args) {

		System.out.print("Nhap so luong phan tu cua mang Interger \t");
		int nPhanTu = scanner.nextInt();
		int[] intArray = new int[nPhanTu];
		for (int i = 0; i < intArray.length; i++) {
			intArray[i] = (int) (i + Math.random() * 100);
		}

		display(intArray);
		change(intArray, nIndex1, nIndex2);
		display(intArray);
	}

	private static void change(int[] intArray, int nIndex1, int nIndex2) {
		boolean bExit = true;

		do {
			System.out.print("Ban nhap vi tri phan tu muon thay doi thu nhat \t");
			nIndex1 = scanner.nextInt();
			System.out.print("Ban nhap vi tri phan tu muon thay doi thu nhat \t");
			nIndex2 = scanner.nextInt();

			if (nIndex1 == nIndex2) {
				System.out.println("\n***Ban vui long nhap 2 vi tri khac nhau***");
				System.out.println("Vui long nhap lai vi tri\n");
			} else if (nIndex1 > intArray.length || nIndex2 > intArray.length) {
				System.out.println("\n***Vi tri cua phan tu cua ban phai nho hon do dai cua mang***");
				System.out.println("Vui long nhap lai vi tri\n");				
			} else {
				bExit = false;
			}
		} while (bExit);

		for (int i = 0; i < intArray.length; i++) {
			if (i == (nIndex1 -1) || i == (nIndex2 - 1)) {
				System.out.print("Ban nhap lai gia tri cho phan tu " + (i + 1) + "\t");
				intArray[i] = scanner.nextInt();
			}
		}

	}

	private static void display(int[] intArray) {
		System.out.println("Mang duoc nhap vao la");
		int nIndex = 0;
		for (int i : intArray) {
			System.out.print(String.format("%3d", i) + "\t");
			nIndex++;
			if (nIndex != 0 && nIndex % 10 == 0) {
				System.out.println();
			}
		}
	}

}
