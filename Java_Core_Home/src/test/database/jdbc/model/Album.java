package test.database.jdbc.model;

public class Album {
	private int id;
	private String name;
	private int artistId;
	
	public int getId() {
		return id;
	}
	
	protected void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	protected void setName(String name) {
		this.name = name;
	}
	
	public int getArtistId() {
		return artistId;
	}
	
	protected void setArtistId(int artistId) {
		this.artistId = artistId;
	}
	
	

}
