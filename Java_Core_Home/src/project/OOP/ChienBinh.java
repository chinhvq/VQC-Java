package test.oop.kethua;

public class ChienBinh extends Nguoi {
	private int nNoiLuc, nKhangDoc;
	String sBinhKhi, sAoGiap;
	
	protected void tanCong() {
		System.out.println("Chien Binh Tan Cong");
	}
	
	protected void HienThiChienBinh() {		
		super.HienThiNguoi();
		System.out.println("Noi Luc:\t" + this.getnNoiLuc() + "\tKhang Doc:\t" + this.getnKhangDoc() + "\tBinh Khi\t" + this.getsBinhKhi() + "\tAo Giap:\t" + this.getsAoGiap());
	}
	
	@Override
	protected void diChuyen() {
		System.out.println("Chien Binh Dang Di Chuyen");
	}
	protected ChienBinh() {
		super();
		this.nNoiLuc = 50;
		this.nKhangDoc = 50;
		this.sBinhKhi = "Kiem";
		this.sAoGiap = "Ao Giap Sat";
	}	
	
	protected ChienBinh(int nNoiLuc, int nKhangDoc, String sBinhKhi, String sAoGiap) {
		this.nNoiLuc = nNoiLuc;
		this.nKhangDoc = nKhangDoc;
		this.sBinhKhi = sBinhKhi;
		this.sAoGiap = sAoGiap;
	}


	protected int getnNoiLuc() {
		return nNoiLuc;
	}
	protected void setnNoiLuc(int nNoiLuc) {
		this.nNoiLuc = nNoiLuc;
	}
	protected int getnKhangDoc() {
		return nKhangDoc;
	}
	protected void setnKhangDoc(int nKhangDoc) {
		this.nKhangDoc = nKhangDoc;
	}
	protected String getsBinhKhi() {
		return sBinhKhi;
	}
	protected void setsBinhKhi(String sBinhKhi) {
		this.sBinhKhi = sBinhKhi;
	}
	protected String getsAoGiap() {
		return sAoGiap;
	}
	protected void setsAoGiap(String sAoGiap) {
		this.sAoGiap = sAoGiap;
	}
	
}
