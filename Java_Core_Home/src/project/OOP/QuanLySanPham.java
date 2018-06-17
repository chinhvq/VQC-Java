package STUDENTS.VQChinh_iMic.OOP;

import java.util.Scanner;

public class QuanLySanPham {

	public static void main(String[] args) {
		SanPham[] arraySanPham = new SanPham[100];
		
		for (int i = 0; i < 2; i++) {
			arraySanPham[i] = new SanPham();
			arraySanPham[i] = NhapThongTinSanPham(arraySanPham[i]);
		}

		System.out.println("\n---Thong tin san pham da nhap la---");
		for (int i = 0; i < 2; i++) {
			System.out.print("SAN PHAM " + i + "\t");
			arraySanPham[i].HienThiThongTin();
		}
	}

	public static SanPham NhapThongTinSanPham(SanPham sanPham) {
		Scanner sc = new Scanner(System.in);
		sanPham = new SanPham();
		System.out.println("---Ban nhap thong tin cho san pham---");
		System.out.print("Ten san pham\t");
		sanPham.setTenSanPham(sc.nextLine());
		System.out.print("Gia san pham\t");
		sanPham.setlGia(sc.nextLong());
		System.out.print("Ty le giam gia\t");
		sanPham.setdGiamGia(sc.nextDouble());
		return sanPham;
	}
}
