package home;

import java.util.Scanner;

public class DangKyKetBan {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String sTen = null, sGioiTinh = null;
		String sHonNhan = null;
		int nTuoi = 0;
		boolean sExitDoWhile = false;
		boolean bExitGioiTinh = false;
		boolean bExitTinhTrangHonNhan = false;
		String sLuaChon;		
		do {
			System.out.println("\n---Chuong Trinh Dang Ky Ket Ban---");
			System.out.println("---Vui long nhap day du thong tin ben duoi");
			System.out.println("1 - Nhap Ten");
			System.out.println("2 - Nhap Tuoi");
			System.out.println("3 - Nhap GioiTinh");
			System.out.println("4 - Nhap Tinh Trang Hon Nhan");
			System.out.println("5 - Dang Ky");
			System.out.println("6 - Thoat chuong trinh");
			System.out.print("Vui long nhap lua chon theo menu\t\t");			
			sLuaChon = sc.nextLine();
			
			switch (sLuaChon) {
			case "1":
				System.out.print("Nhap ten\t\t");
				sTen = sc.nextLine();
				break;	
				
			case "2":
				System.out.println("Nhap tuoi\t\t");
				nTuoi = sc.nextInt();
				sc.nextLine();
				break;
				
			case "3":
				do {
					System.out.println("\n---Nhap gioi tinh---");
					System.out.println("1 - Nam");
					System.out.println("2 - Nu");
					System.out.print("Vui long nhap lua chon theo menu\t\t");
					String sLuaChonGioiTinh = sc.nextLine();
					
					switch(sLuaChonGioiTinh) {
					case "1":
						sGioiTinh = "Nam";
						bExitGioiTinh = true;
						break;
					case "2":
						sGioiTinh = "Nu";
						bExitGioiTinh = true;
						break;
					default:
						System.out.println("\n*****Vui long chon gia tri (1 hoac 2) theo gioi tinh phu hop*****");
						break;
					}					
				}while (!bExitGioiTinh);
				break;
				
			case "4":
				do {
					System.out.println("\n---Nhap tinh trang hon nhan---");
					System.out.println("1 - Doc Than");
					System.out.println("2 - Da Co Gia Dinh");
					System.out.println("3 - Ly Hon");		
					System.out.print("Vui long nhap lua chon theo menu\t\t");
					String sLCHonNhan = sc.nextLine();
					
					switch (sLCHonNhan) {
					case "1":
						sHonNhan = "Doc Than";
						bExitTinhTrangHonNhan = true;
						break;
					case "2":
						sHonNhan = "Gia Dinh";
						bExitTinhTrangHonNhan = true;
						break;
					case "3":
						sHonNhan = "Ly Hon";
						bExitTinhTrangHonNhan = true;
						break;
					default:
						System.out.println("\n*****Vui long chon gia tri tinh trang hon nhan tuong ung***** ");
						break;
					}					
				}while (!bExitTinhTrangHonNhan);				
				break;
				
			case "5":
				if(!CheckInputData(sTen, nTuoi, sGioiTinh, sHonNhan)) {
					break;
				}
				else {
					Registration(sTen, nTuoi, sGioiTinh, sHonNhan);
				}				
				break;
				
			case "6":
				System.out.println("\nBAN DA THOAT CHUONG TRINH THANH CONG");
				sExitDoWhile = true;
				break;
			default:
				System.out.println("\n*****Vui long lua chon tu 1 - 6*****");
				break;					
			}			
		}while(!sExitDoWhile);			
	}
	
	//check xem nguoi dung da nhap du lieu dau vao chua
	public static boolean CheckInputData(String sTen, int nTuoi, String sGioiTinh, String sHonNhan){
		boolean bCheckInputData = false;
		if (sTen == null) {
			System.out.println("\n*****Vui long nhap ten*****");
		} 
		else if (nTuoi == 0) {
			System.out.println("\n*****Tuoi phai khac 0*****");
		} 
		else if (sGioiTinh == null) {
			System.out.println("\n*****Vui long nhap thong tin gioi tinh*****");
		}
		else if (sHonNhan == null) {
			System.out.println("\n*****Vui long nhap thong tin tinh trang hon nhan*****");
		}
		else {
			bCheckInputData = true;
		}
		return bCheckInputData;			
			
	}
	
	//kiem tra du lieu dau vao va dang ky cho nguoi dung voi dieu kien phu hop
	public static void Registration(String sTen, int nTuoi, String sGioiTinh, String sHonNhan) {
		
		if (nTuoi < 19)
		{
			System.out.println("\n*****Ban chua du 19 tuoi*****");
			System.out.println("*****Vui long quay lai sau*****");			
		}
		else if (sHonNhan == "Gia Dinh") {
			System.out.println("\n*****Ban khong the dang ky khi dang co gia dinh*****");
			System.out.println("*****Vui long quay lai sau*****");
		}
		else if (sGioiTinh == "Nam" && nTuoi < 22) {
			System.out.println("\n*****Ban Nam can tren 22 tuoi*****");
			System.out.println("*****Vui long quay lai sau*****");
		} 
		else {
			System.out.println("\n---Chao mung " + sTen + "---");
			System.out.println("Tuoi - " + nTuoi);
			System.out.println("Gioi Tinh - " + sGioiTinh); 
			System.out.println("Tinh Trang Hon Nhan - " + sHonNhan);
			System.out.println("---CHUC MUNG BAN DANG KY THANH CONG---");			
		}					
	}
}
