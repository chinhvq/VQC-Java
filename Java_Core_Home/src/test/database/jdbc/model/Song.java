package test.database.jdbc.model;

public class Song {
	private int id;
	private int track;
	private String title;
	private int albumId;
	
	public int getId() {
		return id;
	}
	
	protected void setId(int id) {
		this.id = id;
	}
	
	public int getTrack() {
		return track;
	}
	
	protected void setTrack(int track) {
		this.track = track;
	}
	
	public String getTitle() {
		return title;
	}
	
	protected void setTitle(String title) {
		this.title = title;
	}
	
	public int getAlbumId() {
		return albumId;
	}
	
	protected void setAlbumId(int albumId) {
		this.albumId = albumId;
	}
	
	

}
