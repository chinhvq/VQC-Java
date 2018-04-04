package home;

public class InBangCuuChuong {

	public static void main(String[] args) {
		System.out.println("\t---In bang cuu chuong rut gon---");
		for (int i = 2; i <= 10; i++) {
			for (int j = 1; j <= 10; j++)
				System.out.printf("%3d\t", i * j);

			System.out.println();
		}
		
		System.out.println("\n\t---In bang cuu chuong day du---");
		for (int i = 2; i <= 10; i++) {
			for (int j = 1; j <= 10; j++)
				System.out.printf("%2d x %2d = %3d\t", i, j, i * j);

			System.out.println();
		}
		

		/*int i, j, n;
		n = 3;
		j = 1;
		System.out.println("In bang nhan:");

		for (i = n; i <= (n * 10); i += n) {
			System.out.printf("%3d  x %2d  =  %3d\n", n, j, i);
			j++;
		}*/
	}

}
