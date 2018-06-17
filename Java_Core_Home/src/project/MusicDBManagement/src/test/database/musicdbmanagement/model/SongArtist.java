package test.database.musicdbmanagement.model;

public class SongArtist {
	private String artistName;
	private String albumName;
	private int track;
	
	protected SongArtist(String artistName, String albumName, int track) {
		this.artistName = artistName;
		this.albumName = albumName;
		this.track = track;
	}

	public String getArtistName() {
		return artistName;
	}
	
	protected void setArtistName(String artistName) {
		this.artistName = artistName;
	}
	
	public String getAlbumName() {
		return albumName;
	}
	
	protected void setAlbumName(String albumName) {
		this.albumName = albumName;
	}
	
	public int getTrack() {
		return track;
	}
	
	protected void setTrack(int track) {
		this.track = track;
	}
	
	
	
	

}
