package home;

public class Person {
	protected String sName;
	protected int nAge;
	protected String sAddress;
	
	public Person() {}

	public Person(String sName, int nAge, String sAddress) {
		this.sName = sName;
		this.nAge = nAge;
		this.sAddress = sAddress;
	}
	
	public String getsName() {
		return sName;
	}
	public void setsName(String sName) {
		this.sName = sName;
	}
	public int getnAge() {
		return nAge;
	}
	public void setnAge(int nAge) {
		this.nAge = nAge;
	}
	public String getsAddress() {
		return sAddress;
	}
	public void setsAddress(String sAddress) {
		this.sAddress = sAddress;
	}	
	
	public void Add(String sName, int nAge, String sAddress) {
		this.sName = sName;
		this.nAge = nAge;
		this.sAddress = sAddress;		
	}	
	public void Show() {
		System.out.println("Nhan vien " + this.sName + " " + this.nAge + " " + this.sAddress);		
	}
}
