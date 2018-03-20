package home;
import java.text.DecimalFormat;
import java.util.Scanner;

public class MenuPhepToan {

	public static void main(String[] args) {
		String sLuaChon, sToanTu = null;
		double a = 0, b = 0;
		double dKetQua = 0;
//		double dTong = 0, dHieu = 0, dTich = 0, dThuong = 0;
		// dung de kiem tra viec nhap du lieu chua
		int nIndex1 = 0, nIndex2 = 0, nIndex3 = 0;
		// Dung de thoat khoi menu
		boolean bExitDoWhile = false;
		Scanner sc = new Scanner(System.in);

		do {
			System.out.println("\n--------Nhap gia tri cho a & b va phep toan tuong ung--------");
			System.out.println("--------Ban nhap lua chon tu 1 - 4--------");
			System.out.println("1-Nhap a\t");
			System.out.println("2-Nhap b\t");
			System.out.println("3-Nhap mot trong cac phep toan ( + - * / )");
			System.out.println("4-Hien thi ket qua");
			System.out.println("5-Thoat chuong trinh");
			System.out.print("\nVui long nhap lua chon\t");
			sLuaChon = sc.nextLine();

			switch (sLuaChon) {
			case "1":
				System.out.print("Nhap a =\t");
				a = sc.nextDouble();
				sc.nextLine();
				nIndex1++;
				break;

			case "2":
				System.out.print("Nhap b =\t");
				b = sc.nextDouble();
				sc.nextLine();
				nIndex2++;
				break;

			case "3":
				boolean bCheck = false;
				do {
					System.out.print("--------Nhap toan tu can tinh--------\t");
					sToanTu = sc.nextLine();
					if (!CheckOperator(sToanTu)) {
						System.out.println("\n*****Vui long nhap toan tu \"+\" hoac \"-\" hoac \"*\" hoac \"/\"*****");
					} else
						bCheck = true;
				} while (!bCheck);
				nIndex3++;
				break;

			case "4":
				// check xem nguoi dung da nhap du lieu cho a, b, va toan tu chua
				if (!CheckIndex(nIndex1, nIndex2, nIndex3)) {
					break;
				}	
				else {
				CalResult(a, b, sToanTu, dKetQua);
				break;				
				}
				
			case "5":
				System.out.println("\n--------Ban da lua chon thoat chuong trinh--------");
				System.out.println("--------Vui long xac nhan \"Y/N\"--------");
				String sCheckExit = sc.nextLine();
				if (CheckExit(sCheckExit)) {
					System.out.println("\n--------BAN DA THOAT CHUONG TRINH--------");
					bExitDoWhile = true;
					break;
				}

			default:
				System.out.println("\n*****Ban vui long nhap lua chon tu 1 - 5*****");
				break;
			}
		} while (!bExitDoWhile);
	}
	
	public static boolean CheckOperator(String sToanTu) {
		if (sToanTu.equals("+") || sToanTu.equals("-") || sToanTu.equals("*") || sToanTu.equals("/")
				|| sToanTu.toUpperCase().equals("CONG") || sToanTu.toUpperCase().equals("TRU")
				|| sToanTu.toUpperCase().equals("NHAN") || sToanTu.toUpperCase().equals("CHIA")) {
			return true;
		} else
			return false;
	}
	
	
	public static boolean CheckIndex(int nIndex1, int nIndex2, int nIndex3) {
		boolean bCheck = false;
		if (nIndex1 == 0) {
			System.out.println("\n*****Ban can nhap gia tri cho a*****");
			System.out.println("*****Vui long chon lai lua chon thu 1*****");
		} else if (nIndex2 == 0) {
			System.out.println("\n*****Ban can nhap gia tri cho b*****");
			System.out.println("*****Vui long chon lai lua chon thu 2*****");
		} else if (nIndex3 == 0) {
			System.out.println("\n*****Ban chua chon phep toan*****");
			System.out.println("*****Vui long chon lai lua chon thu 3*****");
		} else {
			bCheck = true;
		}
		return bCheck;
	}
	
	public static boolean CheckExit(String sCheckExit) {
		boolean bCheck = false;
		if (sCheckExit.toUpperCase().equals("Y") || sCheckExit.toUpperCase().equals("YES")) {
			bCheck = true;
		}
		return bCheck;
	}

	//tinh toan ket qua dua vao toan hang va trinh bay ket qua theo dinh dang thong nhat
	public static void CalResult(double a, double b, String sToanTu, double dKetQua){
		//dinh dang ket qua tra ve theo dinh dang thong nhat
		DecimalFormat df = new DecimalFormat("0.0000");
		if (sToanTu.equals("+") || sToanTu.toUpperCase().equals("CONG")) {
			dKetQua = a + b;
			System.out.println("\nTong cua (" + a + ") + (" + b + ") =\t" + df.format(dKetQua));
		} else if (sToanTu.equals("-") || sToanTu.toUpperCase().equals("TRU")) {
			dKetQua = a - b;
			System.out.println("\nHieu cua (" + a + ") - (" + b + ") =\t" + df.format(dKetQua));
		} else if (sToanTu.equals("*") || sToanTu.toUpperCase().equals("NHAN")) {
			dKetQua = a * b;
			System.out.println("\nTich cua (" + a + ") * (" + b + ") =\t" + df.format(dKetQua));
		} else {
			CalDivision(a, b, dKetQua);
		}		
	}
	public static void CalDivision(double a, double b, double dKetQua) {
		DecimalFormat df = new DecimalFormat("0.0000");
		if (b == 0 && a != 0) {
			System.out.println("\n*****Cannot divide by zero*****");
		} else if (b == 0 && a == 0) {
			System.out.println("\n*****Result of function is underfined*****");
		} else {
			dKetQua = a / b;
			System.out.println("\nThuong cua (" + a + ") / (" + b + ") =\t" + df.format(dKetQua));
		}
	}

}
