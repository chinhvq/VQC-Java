package test.oop;

public class Students {
	private String maSV;
	private String fullName;
	private String email;
	private String phone;

	
	public Students() {
	}

	public Students(String maSV) {
		this.maSV = maSV;
	}
	
	public Students(String maSV, String fullName) {
		this.maSV = maSV;
		this.fullName = fullName;
	}

	public Students(String maSV, String fullName, String email, String phone) {
		this.maSV = maSV;
		this.fullName = fullName;
		this.email = email;
		this.phone = phone;
	}
	protected String getMaSV() {
		return maSV;
	}
	protected void setMaSV(String maSV) {
		this.maSV = maSV;
	}
	protected String getFullName() {
		return fullName;
	}
	protected void setFullName(String fullName) {
		this.fullName = fullName;
	}
	protected String getEmail() {
		return email;
	}
	protected void setEmail(String email) {
		this.email = email;
	}
	protected String getPhone() {
		return phone;
	}
	protected void setPhone(String phone) {
		this.phone = phone;
	}
	
	protected void displayInfo() {
		System.out.println();
		System.out.println("Thong tin Sinh Vien\t");
		System.out.println("Mã SV:\t" + this.maSV + "\tTên:\t" + this.fullName + "\tEmail:\t" + this.email + "\tPhone:\t" + this.phone);		
	}

}
