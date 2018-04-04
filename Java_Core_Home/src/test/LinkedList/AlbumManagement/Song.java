package test.LinkedList.AlbumManagement;

public class Song {
	private String sTitle;
	private double dDuration;
	
	protected String getsTitle() {
		return sTitle;
	}
	protected double getdDuration() {
		return dDuration;
	}
	protected Song(String sTitle, double dDuration) {
		this.sTitle = sTitle;
		this.dDuration = dDuration;
	}
	@Override
	public String toString() {
		return "Song [Title=" + sTitle + ", Duration=" + dDuration + "]";
	}
	
	
	
}
