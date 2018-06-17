package STUDENTS.VQChinh_iMic.OOP;

import java.util.Scanner;

public class SanPham {
	private String sTenSanPham;
	private long lGia;
	private double dGiamGia;

	protected void HienThiThongTin() {
		System.out.print("Ten\t" + this.sTenSanPham + "\tGia\t" + this.lGia + "\tGiam Gia\t");
		System.out.printf("%.2f", this.dGiamGia);
		System.out.print("%" + "\tGIA BAN\t");
		System.out.printf("%d", (long)(this.lGia * (double)((100 - this.dGiamGia)*0.01)));
		System.out.println(" VND");
	}

	private long TinhThueNhapKhau() {
		return (long) (this.lGia * 0.1);
	}
	
	protected SanPham() {
	}
	
	
	protected SanPham(String sTenSanPham, long lGia) {
		this.sTenSanPham = sTenSanPham;
		this.lGia = lGia;
	}

	protected SanPham(String sTenSanPham, long lGia, double lGiamGia) {
		this.sTenSanPham = sTenSanPham;
		this.lGia = lGia;
		this.dGiamGia = lGiamGia;
	}

	protected String getTenSanPham() {
		return sTenSanPham;
	}

	protected void setTenSanPham(String tenSanPham) {
		this.sTenSanPham = tenSanPham;
	}

	protected long getlGia() {
		return lGia;
	}

	protected void setlGia(long lGia) {
		this.lGia = lGia;
	}

	protected double getdGiamGia() {
		return dGiamGia;
	}

	//co 2 cach nhap ty le chiet khau nhap theo 2 cach(so int hoac so double)
	//kiem tra ty le chiet khau trong pham vi 0%-100%
	protected void setdGiamGia(double dGiamGia) {
		Scanner sc = new Scanner(System.in);
		boolean bCheck = true;
		do {
			if (dGiamGia < 0 || dGiamGia > 100) {
				System.out.println("\n***Ty le chiet khau phai lon hon 0 HOAC nho hon 100***");
				System.out.print("Vui long nhap lai ty le chiet khau\t");
				dGiamGia = sc.nextDouble();				
			}
			else if (dGiamGia < 1) {
				dGiamGia *= 100;
				this.dGiamGia = dGiamGia;
				bCheck = false;				
			}			
			else {
				this.dGiamGia = dGiamGia;
				bCheck = false;
			}			
		} while (bCheck);
	}
}
