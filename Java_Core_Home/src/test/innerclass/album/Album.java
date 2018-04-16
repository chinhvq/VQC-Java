package test.innerclass.album;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Album {
	private String sSongName;
	private String sArtist;
	private SongList listSong;

	protected Album(String sSongName, String sArtist) {
		this.sSongName = sSongName;
		this.sArtist = sArtist;
		this.listSong = new SongList();
	}

	protected boolean addSong(String sTitle, double dDuration) {
		return this.listSong.addSong(new Song(sTitle, dDuration));
	}

	protected boolean addSongToPlayList(int nTrackNumber, List<Song> listPlayList) {
		Song checkedSong = this.listSong.findSong(nTrackNumber);
		if (checkedSong != null) {
			listPlayList.add(checkedSong);
			return true;
		} else {
			System.out.println("\nThis album does not have the track " + nTrackNumber);
			return false;
		}
	}

	protected boolean addSongToPlayList(String sTitle, List<Song> listPlayList) {
		Song checkedSong = this.listSong.findSong(sTitle);
		if (checkedSong != null) {
			listPlayList.add(checkedSong);
			return true;
		} else {
			System.out.println("\nThe song " + sTitle + " is not in the album");
			return false;
		}
	}

	// inner class SongList
	private class SongList {
		private ArrayList<Song> songs;

		protected SongList() {
			this.songs = new ArrayList<Song>();
		}

		protected boolean addSong(Song song) {
			if (this.songs.contains(song)) {
				return false;
			} else {
				this.songs.add(song);
				return true;
			}
		}

		private Song findSong(String sTitle) {
			for (Song checkedSong : this.songs) {
				if (checkedSong.getsTitle().equalsIgnoreCase(sTitle)) {
					return checkedSong;
				}
			}
			return null;
		}

		private Song findSong(int nTrackNumber) {
			int nIndex = nTrackNumber - 1;
			if (nIndex >= 0 && nIndex <= this.songs.size()) {
				return this.songs.get(nIndex);
			}
			return null;
		}

	}
}
