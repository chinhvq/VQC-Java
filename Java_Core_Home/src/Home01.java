import java.util.Scanner;

public class Home01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				// Tinh Dien tich va Chu vi hinh chu nha
				// S = dai * rong;
				// P = (dai + rong) * 2

				double dDai, dRong, dDienTich, dChuVi;
				Scanner scanner = new Scanner(System.in);
				
				System.out.print("Ban nhap chieu dai =\t");
				dDai = scanner.nextDouble();
				System.out.print("Ban nhap chieu rong =\t");
				dRong = scanner.nextDouble();
				dDienTich = dDai * dRong;
				dChuVi = (dDai + dRong) * 2;
				System.out.println("Dien tich hinh chu nhat bang\t" + dDienTich);
				System.out.println("Chu vi hinh chu nhat bang\t" + dChuVi);
	}

}
