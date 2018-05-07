package home.thread_array;

import java.util.Scanner;

public class ThreadArrayDemo {
	static int total = 0;

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		System.out.print("Nhap so luong phan tu mang \t");
		int n = scanner.nextInt();
		int[] array = new int[n];

		for (int i = 0; i < array.length; i++) {
			array[i] = (int) (Math.random() * 100);
		}

		int nIndex = 0;
		for (int i : array) {
			System.out.print(i + "\t");
			nIndex++;
			if (nIndex != 0 && nIndex % 12 == 0) {
				System.out.println();
			}
		}

		System.out.println();
		System.out.println(0 + " : " + ((n / 4) - 1) + " : " + (n / 4) + " : " + (((n / 4) * 2) - 1) + " : "
				+ ((n / 4) * 2) + " : " + (((n / 4) * 3) - 1) + " : " + ((n / 4) * 3) + " : " + (n - 1));
		ThreadArray thread1 = new ThreadArray(0, ((n / 4) - 1), array);
		ThreadArray thread2 = new ThreadArray((n / 4), (((n / 4) * 2) - 1), array);
		ThreadArray thread3 = new ThreadArray(((n / 4) * 2), (((n / 4) * 3) - 1), array);
		ThreadArray thread4 = new ThreadArray(((n / 4) * 3), (n - 1), array);

		new Thread(thread1).start();
		new Thread(thread2).start();
		new Thread(thread3).start();
		new Thread(thread4).start();

		Thread threadSum = new Thread() {
			public void run() {
				while (true) {
					if (thread1.getSum() == 0 || thread2.getSum() == 0 || thread3.getSum() == 0
							|| thread4.getSum() == 0) {
						try {
							Thread.sleep(1000);
							continue;
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					} else {
						total = thread1.getSum() + thread2.getSum() + thread3.getSum() + thread4.getSum();
						System.out.println(
								"***Tong cua mang = " + total + "\t" + Thread.currentThread().getName() + "***");
						break;
					}
				}
			}
		};
		threadSum.setName("Thread SUM ---- FINAL");
		threadSum.setPriority(Thread.MIN_PRIORITY);
		threadSum.start();

		Thread threadMax = new Thread() {
			public void run() {
				while (true) {
					if (total == 0) {
						try {
							Thread.sleep(1000);
							continue;
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					} else {
						int finalMax = thread1.getMax();
						if (finalMax < thread2.getMax()) {
							finalMax = thread2.getMax();
						} else if (finalMax < thread3.getMax()) {
							finalMax = thread3.getMax();
						} else if (finalMax < thread4.getMax()) {
							finalMax = thread4.getMax();
						}
						System.out.println("***MAX cua mang la " + finalMax + "***");
						break;
					}
				}
			}
		};
		threadMax.setName("Thread MAX ---- FINAL");
		threadMax.setPriority(Thread.MIN_PRIORITY);
		threadMax.start();

		if (scanner != null) {
			scanner.close();
		}
	}
}
