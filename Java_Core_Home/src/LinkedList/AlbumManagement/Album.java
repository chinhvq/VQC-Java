package LinkedList.AlbumManagement;

import java.util.ArrayList;
import java.util.LinkedList;

public class Album {
	private String sSongName;
	private String sArtist;
	private ArrayList<Song> listSong;
	
	protected Album(String sSongName, String sArtist) {
		this.sSongName = sSongName;
		this.sArtist = sArtist;
		listSong = new ArrayList<Song>();
	}
	
	protected boolean addSong(String sTitle, double dDuration) {
		if(findSong(sTitle) == null) {
			listSong.add(new Song(sTitle, dDuration));
			return true;
		}
		else {
			return false;
		}
	}
	
	protected boolean addSongToPlayList(int nTrackNumber, LinkedList<Song> listPlayList) {
		int nIndex = nTrackNumber - 1;
		if (nIndex >= 0 && nIndex <= listSong.size()) {
			listPlayList.add(listSong.get(nIndex));
			return true;
		} else {
			System.out.println("\nThis album does not have the track " + nTrackNumber);
			return false;
		}		
	}
	
	protected boolean addSongToPlayList(String sTitle, LinkedList<Song> listPlayList) {
		Song checkedSong = findSong(sTitle);
		if(checkedSong != null) {
			listPlayList.add(checkedSong);
			return true;
		} else {
			System.out.println("\nThe song " + sTitle + " is not in the album");
			return false;			
		}		
	}
	
	private Song findSong(String sTitle) {
		for (Song checkedSong : listSong) {
			if (checkedSong.getsTitle().equalsIgnoreCase(sTitle)) {
				return checkedSong;
			}
		}
		return null;		
	}
}
