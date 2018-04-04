package test.arrraylist.mobiphone;

public class Contact {
	private String sTenTaiKhoan;
	private String sSoDienThoai;
	
	//Ham nay de tao mot Contact trong ham Main de dam bao dong goi va thuan tien
	protected static Contact TaoTaiKhoan(String sTenTaiKhoan, String sSoDienThoai) {
		return new Contact(sTenTaiKhoan, sSoDienThoai);
	}
	protected Contact(String sTenTaiKhoan, String sSoDienThoai) {
		this.sTenTaiKhoan = sTenTaiKhoan;
		this.sSoDienThoai = sSoDienThoai;
	}
	protected String getsPhoneName() {
		return sTenTaiKhoan;
	}
	protected String getsPhoneNumber() {
		return sSoDienThoai;
	}
	
	

}
