package home;

import java.text.DecimalFormat;
import java.util.Scanner;

import javax.swing.plaf.SliderUI;

public class DuyetSoChanLe2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String sSo = null;
		String sLuaChon;
		double dSo = 0;		
		short shKieuSo = 0; // shKieuSo = 1 la so chan OR shKieuSo = 2 la so le
		boolean bExitDoWhile = true;
		do {
			System.out.println("\n---CHUONG TRINH DUYET SO CHAN LE---");
			System.out.println("1 - Ban nhap so can kiem tra");
			System.out.println("2 - Ban chon kieu so chan hoac kieu so le");
			System.out.println("3 - Hien thi ket qua theo kieu mang ");
			System.out.println("4 - Hien thi ket qua theo kieu so ");
			System.out.println("5 - Ket Thuc Chuong Trinh");
			System.out.print("Vui long nhap lua chon\t");
			sLuaChon = sc.nextLine();

			switch (sLuaChon) {
			case "1":
				System.out.print("Nhap gia tri cho so can kiem tra\t");
				sSo = sc.nextLine();
				break;

			case "2":
				shKieuSo = ChonSoChanLe(shKieuSo);
				break;

			case "3":
				if (KiemTraDuLieu(sSo, shKieuSo)) {
					char[] array = XulyStringKieuMang(sSo);
					HienThiKetQuaKieuMang(array, shKieuSo);
				}
				break;
			case "4":
				if (KiemTraDuLieu(sSo, shKieuSo)) {
					dSo = XulyStringKieuSo(sSo);
					HienThiKetQuaKieuSo(dSo, shKieuSo);
				}
				break;

			case "5":
				System.out.println("\nBAN DA THOAT CHUONG TRINH");
				bExitDoWhile = false;
				break;

			default:
				System.out.println("\n***Ban vui long nhap lua chon tu 1 den 4***");
				break;
			}

		} while (bExitDoWhile);
		sc.close();
	}

	public static short ChonSoChanLe(short shKieuSo) {
		Scanner sc = new Scanner(System.in);
		boolean bExit = false;
		do {
			System.out.println("\n---Ban chon kieu so chan hoac so le---");
			System.out.println("1 - So chan");
			System.out.println("2 - So le");
			System.out.print("Vui long nhap lua chon\t");

			String sLuaChon = sc.nextLine();

			switch (sLuaChon) {
			case "1":
				shKieuSo = 1;
				bExit = true;
				break;

			case "2":
				shKieuSo = 2;
				bExit = true;
				break;

			default:
				System.out.println("\n***Ban vui long nhap lua chon 1 hoac 2");
				break;
			}
		} while (!bExit);
		return shKieuSo;
	}

	public static boolean KiemTraDuLieu(String sSo, short shKieuSo) {
		boolean bCheck = false;
		if (sSo == null) {
			System.out.println("\n***So nhap vao khong duoc bang 0***");
			System.out.println("Vui long nhap gia tri cho so kiem tra tai tuy chon so 1");
		} else if (shKieuSo == 0) {
			System.out.println("\n***Ban can nhap kieu hien thi <so chan hoac so le>***");
			System.out.println("Vui long nhap gia tri cho so kiem tra tai tuy chon so 2");
		} else {
			bCheck = true;
		}
		return bCheck;
	}

	public static char[] XulyStringKieuMang(String sSo) {
		char[] array = new char[sSo.length()];
		char[] arraySo = new char[sSo.length()];
		array = sSo.toCharArray();

		// lay ky tu so tu array copy sang array1 -> loai bo ky tu chu cai va cac ky tu
		// dac biet
		for (int l = 0; l < array.length; l++) {
			if ((array[l]) == '0' || array[l] == '1' || array[l] == '2' || array[l] == '3' || array[l] == '4'
					|| array[l] == '5' || array[l] == '6' || array[l] == '7' || array[l] == '8' || array[l] == '9') {
				arraySo[l] = array[l];
			}
		}
		return arraySo;
	}

	public static double XulyStringKieuSo(String sSo) {

		// chuyen String sang mang ky tu, loc mang ky tu lay cac so 0-9 copy vao sTem
		// chuyen sTemp sang kieu double
		char[] array = new char[sSo.length()];
		array = sSo.toCharArray();
		String sTemp = "";
		for (char c : array) {
			if (c == '0' || c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6' || c == '7' || c == '8'
					|| c == '9') {
				System.out.print(c + "\t");
				sTemp += c;
			}
		}

		double dSo = Double.parseDouble(sTemp);
		return dSo;
	}

	public static void HienThiKetQuaKieuMang(char[] array, short shKieuSo) {
		System.out.print("So nhap vao la\t");
		System.out.println(array);
		if (shKieuSo == 1) {
			System.out.println("\nDanh Sach so chan la");
			for (char c : array) {
				if ((int) c % 2 == 0 && (int) c != 0) {
					System.out.print(c + "\t");
				}
			}
		} else {
			System.out.println("\nDanh Sach so le la");
			for (char c : array) {
				if ((int) c % 2 != 0) {
					System.out.print(c + "\t");
				}
			}
		}
	}

	public static void HienThiKetQuaKieuSo(double dSo, short shKieuSo) {
		// bien nCheck dung de dinh vi viec xuong dong
		// (***bai nay de la xuong dong sau khi hien thi 16 ket qua tim thay***)
		int nCheck = 0;
		System.out.print("So nhap vao la\t" + dSo);
		if (shKieuSo == 1) {
			System.out.println("\nDanh Sach so chan la");
			for (int i = 0; i <= dSo; i++) {
				if (i % 2 == 0) {
					System.out.print(i + "\t");
					nCheck++;
					if (nCheck % 16 == 0) {
						System.out.println("\n");
					}
				}
			}
		} else {
			System.out.println("\nDanh Sach so le la");
			for (int i = 0; i <= dSo; i++) {
				if (i % 2 != 0) {
					System.out.print(i + "\t");
					nCheck++;
					if (nCheck % 16 == 0) {
						System.out.println("\n");
					}
				}
			}
		}
	}
}
