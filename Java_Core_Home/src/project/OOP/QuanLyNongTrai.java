package test.oop.kethua;

import java.util.Scanner;

public class QuanLyNongTrai {

	public static void main(String[] args) {
		
		ChienBinh chienBinh1 = new ChienBinh();
		NongDan nongDan1 = new NongDan();
		System.out.println("\nThong tin Chien Binh 1");
		chienBinh1.HienThiChienBinh();
		chienBinh1.diChuyen();
		chienBinh1.tanCong();
		
		
		System.out.println("\nThong tin Nong Dan 1");
		nongDan1.HienThiNongDan();
		nongDan1.diChuyen();
		nongDan1.laoDong();
		
		nongDan1.setnKyNang(80);
		nongDan1.setsMauDa("Vang");
		chienBinh1.setnNoiLuc(75);
		chienBinh1.setsThucAn(chienBinh1.getsThucAn() + " + Thit Bo");
		
		System.out.println("\nThong tin Nong Dan 1 sau khi cap nhat");
		nongDan1.HienThiNongDan();
		System.out.println("\nThong tin Chien Binh 1 sau khi cap nhat");
		chienBinh1.HienThiChienBinh();
		
	}

	public static ChienBinh ThemChienBinh(ChienBinh chienBinh) {
		Scanner sc = new Scanner(System.in);
		chienBinh = new ChienBinh();
		System.out.println("Nhap thong tin cua chien binh");
		System.out.print("Nhap Noi Luc\t");
		chienBinh.setnNoiLuc(sc.nextInt());
		System.out.print("Nhap Khang Doc\t");
		chienBinh.setnKhangDoc(sc.nextInt());
		System.out.print("Nhap Binh Khi\t");
		chienBinh.setsBinhKhi(sc.nextLine());
		System.out.print("Nhap Ao Giap\t");
		chienBinh.setsAoGiap(sc.nextLine());
		sc.close();
		return chienBinh;
	}


	public static NongDan ThemNongDan(NongDan nongDan) {
		Scanner sc = new Scanner(System.in);
		nongDan = new NongDan();
		System.out.println("Nhap thong tin nong dan");	
		System.out.println("Nhap Gio Lao Dong\t");
		nongDan.setnGioLaoDong(sc.nextInt());
		System.out.println("Nhap Ky Nang");
		nongDan.setnKyNang(sc.nextInt());
		System.out.println("Nhap Nong Cu\t");
		nongDan.setsNongCu(sc.nextLine());
		System.out.println("Nhap Trang Phuc");
		nongDan.setsNongCu(sc.nextLine());
		sc.close();
		return nongDan;
	}	
	
}
