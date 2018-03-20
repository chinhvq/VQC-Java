package home;

import java.text.DecimalFormat;
import java.util.Scanner;

public class DuyetSoChanLe {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		double dSo = 0;
		int nSo = 0;
		short shKieuSo = 0; // shKieuSo = 1 la so chan OR shKieuSo = 2  la so le
		boolean bExitDoWhile = false;
		
		do {
			System.out.println("\n---CHUONG TRINH DUYET SO CHAN LE---");
			System.out.println("1 - Ban nhap so can kiem tra");
			System.out.println("2 - Ban chon kieu so chan hoac kieu so le");
			System.out.println("3 - Hien thi ket qua 1 ");
			System.out.println("4 - Hien thi ket qua 2 - theo kieu mang");
			System.out.println("5 - Ket Thuc Chuong Trinh");
			System.out.print("Vui long nhap lua chon\t");			
			String sLuaChon = sc.nextLine();
			
			switch (sLuaChon) {
			case "1":
				System.out.print("Nhap gia tri cho so can kiem tra\t");
				dSo = sc.nextDouble();
 				break;
 				
			case "2":							
				shKieuSo = ChonSoChanLe(shKieuSo);				
				break;
				
			case "3":
				if(KiemTraDuLieu(dSo, shKieuSo)) {
					nSo = (int)Math.abs(dSo);
					HienThiKetQua(nSo,  shKieuSo);
				}				
				break;
			
			case "4":				
				if(KiemTraDuLieu(dSo, shKieuSo)) {	
					HienThiKetQua2(dSo,  shKieuSo);
				}
				break;
				
			case "5":
				System.out.println("\nBAN DA THOAT CHUONG TRINH");
				bExitDoWhile = true;
				break;
				
			default:
				System.out.println("\n***Ban vui long nhap lua chon tu 1 den 4***");
				break;					
			}
			
		}while (!bExitDoWhile);			
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
		}while (!bExit);	
		return shKieuSo;
	}
	
	public static boolean KiemTraDuLieu(double dSo, short shKieuSo) {
		boolean bCheck = false;
		if(dSo == 0) {
			System.out.println("\n***So nhap vao khong duoc bang 0***");
			System.out.println("Vui long nhap gia tri cho so kiem tra tai tuy chon so 1");
		}
		else if(shKieuSo == 0) {
			System.out.println("\n***Ban can nhap kieu hien thi <so chan hoac so le>***");
			System.out.println("Vui long nhap gia tri cho so kiem tra tai tuy chon so 2");
		}
		else {
			bCheck = true;
		}
		return bCheck;			
	}
	public static void HienThiKetQua(int nSo, short shKieuSo) {		
		//bien nCheck dung de dinh vi viec xuong dong 
		//(***bai nay de la xuong dong sau khi hien thi 16 ket qua tim thay***)
		int nCheck = 0; 
		if (shKieuSo == 1) {
			System.out.println("\nDanh Sach so chan la");
			for (int i = 0; i <= nSo; i++) {
				if (i % 2 == 0) {
					System.out.print(i + "\t");
					nCheck++;
					if (nCheck % 16 == 0) {
						System.out.println("\n");
					}
				}
			}
		}
		else {
			System.out.println("\nDanh Sach so le la");
			for (int i = 0; i <= nSo; i++) {
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
	
	public static void HienThiKetQua2(double dSo, short shKieuSo) {
		//so Double the hien chinh xac 14 so sau phan thap phan
		DecimalFormat df = new DecimalFormat("0.00000000000000");		
		Double DSo = Math.abs(dSo);
		String sSo = DSo.toString();
		//theo doi co bao nhieu so chan le trong chuoi ky tu
		int nTrack = 0;
		char[] mangChar = sSo.toCharArray();
		if(shKieuSo == 1) {
			System.out.println("\nSo nhap vao la\t" + df.format(DSo));
			System.out.println("Danh sach so chan tu so nhap vao bao gom");
			for (char c : mangChar) {
				//neu la so chan va khong phai la dau '.' thi in ra ky tu kieu char cua so do
				if (((int)c % 2 == 0) && (int)c != 46 ) {
					System.out.print(c + "\t");
					nTrack++;
				}
			}
			System.out.println("\nCo tat ca\t" + nTrack + "\tso chan tu so nhap vao");
		}
		else
		{
			System.out.println("\nSo nhap vao la" + df.format(DSo));
			System.out.println("Danh sach so le tu so nhap vao bao gom");
			for (char c : mangChar) {
				if (((int)c % 2 != 0) && (int)c != 46 ) {
					System.out.print(c + "\t");
					nTrack++;
				}
			}
			System.out.println("\nCo tat ca\t" + nTrack + "\tso chan tu so nhap vao");
		}
		
	}

}
