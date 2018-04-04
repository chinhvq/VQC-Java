package test.arrraylist.mobiphone;

import java.util.Scanner;

public class ManageMobileContact {

	private static Scanner scanner = new Scanner(System.in);
	private static MobilePhone mobilePhone = new MobilePhone();
	private static Contact contact;	
	private static String sTenTaiKhoan;
	private static String sSoDienThoai;

	public static void main(String[] args) {
		
		boolean bExit = true;
		do {
			System.out.println("\n---Menu Quan Ly Danh Ba Dien Thoai---");
			System.out.println("1 - Them Danh Ba\n" + "2 - Xem Danh Ba\n" + "3 - Tim Danh Ba Theo Ten\n"
					+ "4 - Tim Danh Ba Theo So Dien Thoai\n" + "5 - Cap Nhap Danh Ba\n" + "6 - Xoa Danh Ba Theo Ten\n"
					+ "7 - Xoa Danh Ba Theo So Dien Thoai\n" + "8 - Thoat Chuong Trinh\n");

			System.out.println("Ban vui long nhap lua chon theo danh muc chuc nang\t");
			int nLuaChon = scanner.nextInt();
			scanner.nextLine();

			switch (nLuaChon) {
			case 1:
				ThemDanhBa();
				break;

			case 2:
				XemDanhBa();
				break;

			case 3:
				TimTheoTen();
				break;

			case 4:
				TimTheoSoDienThoai();
				break;

			case 5:
				CapNhatDanhBa();
				break;

			case 6:
				XoaTheoTen();
				break;

			case 7:
				XoaTheoSoDienThoai();
				break;

			case 8:
				bExit = false;
				break;
			default:
				System.out.println("*** BAN CHON THEO MENU DANH MUC - XIN CAM ON \u263a\u263a\u263a"  + " ***");
				break;
			}

		} while (bExit);
	}

	protected static void ThemDanhBa() {
		System.out.print("Ban nhap ten tai khoan\t");
		String sTenTaiKhoan = scanner.nextLine();
		System.out.print("Ban nhap so dien thoai\t");
		String sSoDienThoai = scanner.nextLine();
		contact = Contact.TaoTaiKhoan(sTenTaiKhoan, sSoDienThoai);
//		Contact contact = new Contact(sTenTaiKhoan, sSoDienThoai);
		mobilePhone.ThemTaiKhoan(contact);
	}

	protected static void XemDanhBa() {
		mobilePhone.XemDanhBa();
	}

	protected static void TimTheoTen() {
		System.out.print("Nhap ten tai khoan ban muon tim\t");
		sTenTaiKhoan = scanner.nextLine();
		mobilePhone.TimTenTaiKhoan(sTenTaiKhoan);
	}

	protected static void TimTheoSoDienThoai() {
		System.out.print("Nhap so dien thoai can tim\t");
		sSoDienThoai = scanner.nextLine();
		mobilePhone.TimSoDienThoai(sSoDienThoai);
	}

	protected static void CapNhatDanhBa() {
		System.out.print("Nhap ten tai khoan ban muon cap nhat\t");
		sTenTaiKhoan = scanner.nextLine();
		System.out.print("Nhap ten tai khoan moi\t");
		String sNewTenTaiKhoan = scanner.nextLine();	
		mobilePhone.CapNhat(sTenTaiKhoan, sNewTenTaiKhoan);
	}

	protected static void XoaTheoTen() {
		System.out.print("Nhap ten tai khoan ban muon xoa\t");
		sTenTaiKhoan = scanner.nextLine();
		mobilePhone.XoaTenTaiKhoan(sTenTaiKhoan);
	}

	protected static void XoaTheoSoDienThoai() {
		System.out.print("Nhap so dien thoai can xoa\t");
		sSoDienThoai = scanner.nextLine();
		mobilePhone.XoaSoDienThoai(sSoDienThoai);
	}

}
