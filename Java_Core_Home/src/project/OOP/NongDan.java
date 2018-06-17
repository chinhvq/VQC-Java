package test.oop.kethua;

public class NongDan extends Nguoi{
	int nGioLaoDong, nKyNang;
	String sNongCu, sTrangPhuc;
	
	protected void laoDong() {
		System.out.println("Nong Dan Lao Dong");		
	}
	
	@Override
	protected void diChuyen() {
		System.out.println("Nong Dan Dang Di Chuyen");
	}
	
	protected void HienThiNongDan() {		
		super.HienThiNguoi();
		System.out.println("Gio Lao Dong:\t" + this.getnGioLaoDong() + "\tKy Nang:\t" + this.getnKyNang() + "\tNongCu:\t" + this.getsNongCu() + "\tTrang Phuc:\t" + this.getsTrangPhuc());		
	}
	protected NongDan() {
		super();
		this.nGioLaoDong = 192;
		this.nKyNang = 50;
		this.sNongCu = "May Cay";
		this.sTrangPhuc = "Ao Nong Dan";		
	}
	
	protected NongDan(int nGioLaoDong, int nKyNang, String sNongCu, String sTrangPhuc) {
		this.nGioLaoDong = nGioLaoDong;
		this.nKyNang = nKyNang;
		this.sNongCu = sNongCu;
		this.sTrangPhuc = sTrangPhuc;
	}

	protected int getnGioLaoDong() {
		return nGioLaoDong;
	}
	protected void setnGioLaoDong(int nGioLaoDong) {
		this.nGioLaoDong = nGioLaoDong;
	}
	protected int getnKyNang() {
		return nKyNang;
	}
	protected void setnKyNang(int nKyNang) {
		this.nKyNang = nKyNang;
	}
	protected String getsNongCu() {
		return sNongCu;
	}
	protected void setsNongCu(String sNongCu) {
		this.sNongCu = sNongCu;
	}
	protected String getsTrangPhuc() {
		return sTrangPhuc;
	}
	protected void setsTrangPhuc(String sTrangPhuc) {
		this.sTrangPhuc = sTrangPhuc;
	}
	
	

}
