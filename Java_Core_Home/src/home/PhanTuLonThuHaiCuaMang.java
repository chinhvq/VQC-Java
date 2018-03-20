package home;

import java.util.Scanner;

public class PhanTuLonThuHaiCuaMang {

	public static void main(String[] args) {
		Scanner sc = new Scanner (System.in);
		int nSoPhanTu;
		do {
			System.out.println("Nhap so luong phan tu cua mang");
			nSoPhanTu = sc.nextInt();			
			if (nSoPhanTu < 2) {
				System.out.println("So phan tu cua mang can phai lon hon 2 phan tu");
			}
			else
				break;
		}while(true);
				
		int[] mang = new int[nSoPhanTu];
		
		System.out.println("Nhap gia tri cho mang");
		for (int i = 0; i < mang.length; i ++) {
			System.out.print("mang[ " + i + " ] =\t");
			mang[i] = sc.nextInt();
		}
	
		int nMax = 0 , nSecond;
		
		if (mang[0] > mang[1]) {
			nMax = mang[0];
			nSecond = mang[1];
		} 
		else
		{
			nMax = mang[1];
			nSecond = mang[0];
		}
		
		for (int i = 2; i < mang.length; i++) {
			if (mang[i] > nMax) {
				nSecond = nMax;
				nMax = mang[i];						
			}
			else if (mang[i] > nSecond)
			{
				nSecond = mang[i];
			}
		}
		
		System.out.println("Gia tri lon thu 2 cua mang =\t" + nSecond);
		sc.close();
	}

}
