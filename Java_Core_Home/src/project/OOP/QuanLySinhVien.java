package test.oop;

import java.util.ArrayList;

public class QuanLySinhVien {

	public static void main(String[] args) {

		Students student1 = new Students();
		student1.setMaSV("SV01");
		student1.setFullName("Hoàng Văn Thanh");
		student1.setEmail("abc@gmail.com");
		student1.setPhone("123456789");

		System.out.println("Thông tin Sinh Viên 1");
		System.out.println("Mã Sinh Viên:\t" + student1.getMaSV());
		System.out.println("Tên:\t" + student1.getFullName());
		System.out.println("Email:\t" + student1.getEmail());
		System.out.println("Phone:\t" + student1.getPhone());

		System.out.println();
		Students student2 = new Students("SV02", "B", "abc@gmail.com", "123456789");
		System.out.println("Thông tin Sinh Viên 2");
		System.out.println("Mã Sinh Viên:\t" + student2.getMaSV());
		System.out.println("Tên:\t" + student2.getFullName());
		System.out.println("Email:\t" + student2.getEmail());
		System.out.println("Phone:\t" + student2.getPhone());

		System.out.println();
		Students student3 = new Students("SV03", "C", "abc@gmail.com", "123456789");
		System.out.println();
		Students student4 = new Students("SV04", "D", "abc@gmail.com", "123456789");

		student1.setEmail("thanhhv@gmail.com");
		student1.setPhone("0988123456");
		student2.setFullName("Phạm Thị Thùy Linh");
		student3.setFullName("Trần Văn Hiếu");
		student4.setFullName("Nguyễn Văn Hùng");
		student2.setEmail("linhptt@gmail.com");
		student3.setEmail("hieutv@gmail.com");
		student4.setEmail("hungnv@gmail.com");
		student2.setPhone("0988123457");
		student3.setPhone("0988123458");
		student4.setPhone("0988123459");

		System.out.println("Danh Sach Sinh Vien da nhap");
		student1.displayInfo();
		student2.displayInfo();
		student3.displayInfo();
		student4.displayInfo();

		// ArrayList studentList = new ArrayList<Students>();
		long startTime = System.currentTimeMillis();
		Students[] studentList = new Students[1000000];
		for (int i = 0; i < 1000000; i++) {
			studentList[i] = new Students();
			studentList[i].setMaSV("SV" + i);
			studentList[i].setFullName("Sinh Vien " + i);
			studentList[i].setEmail("sv" + i + "@gmail.com");
			studentList[i].setPhone("" + i);
		}
		long endTime = System.currentTimeMillis();
		long startTime1 = System.currentTimeMillis();

		System.out.println("Danh sach SV");
		for (Students std : studentList) {
			std.displayInfo();
		}
		long endTime1 = System.currentTimeMillis();
		System.out.println(
				"Time taken for creation" + " Student objects in array  : " + (endTime - startTime) + " milli seconds");
		System.out.println("Time taken for display" + " Student objects in array  : " + (endTime1 - startTime1)
				+ " milli seconds");

	}
}
