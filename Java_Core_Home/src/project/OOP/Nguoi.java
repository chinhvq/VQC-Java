package test.oop.kethua;

public class Nguoi {
	private int nTay, nChan;
	String sMauDa, sThucAn;
	
	protected void diChuyen() {
		System.out.println("Dang Di Chuyen");
	}
	
	protected void HienThiNguoi () {
		System.out.println("Tay:\t" + this.getnTay() + "\tChan:\t" + this.getnChan() + "\tMau Da:\t" + this.getsMauDa() + "\tThuc An:\t" + this.getsThucAn());
	}
	protected Nguoi() {
		this.nTay = 2;
		this.nChan = 2;
		this.sMauDa = "Da Den";
		this.sThucAn = "Thit Ga";
	}
	
	protected Nguoi(int nTay, int nChan, String sMauDa, String sThucAn) {
		this.nTay = nTay;
		this.nChan = nChan;
		this.sMauDa = sMauDa;
		this.sThucAn = sThucAn;
	}

	protected int getnTay() {
		return nTay;
	}
	protected void setnTay(int nTay) {
		this.nTay = nTay;
	}
	protected int getnChan() {
		return nChan;
	}
	protected void setnChan(int nChan) {
		this.nChan = nChan;
	}
	protected String getsMauDa() {
		return sMauDa;
	}
	protected void setsMauDa(String sMauDa) {
		this.sMauDa = sMauDa;
	}
	protected String getsThucAn() {
		return sThucAn;
	}
	protected void setsThucAn(String sThucAn) {
		this.sThucAn = sThucAn;
	}	
	
	

}
