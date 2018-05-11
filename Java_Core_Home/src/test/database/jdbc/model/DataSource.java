package test.database.jdbc.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DataSource {
	public static final String DB_NAME = "music.db";
	public static final String CONNECTION_STRING = "jdbc:sqlite:Java_Core_Home\\src\\test\\database\\jdbc\\" + DB_NAME;

	public static final String TABLE_SONGS = "songs";
	public static final String COLUMN_SONG_ID = "_id";
	public static final String COLUMN_SONG_TRACK = "track";
	public static final String COLUMN_SONG_TITLE = "title";
	public static final String COLUMN_SONG_ALBUM = "album";
	public static final int INDEX_SONGS_ID = 1;
	public static final int INDEX_SONGS_TRACK = 2;
	public static final int INDEX_SONGS_TITLE = 3;
	public static final int INDEX_SONGS_ALBUM = 4;

	public static final String TABLE_ALBUMS = "albums";
	public static final String COLUMN_ALBUM_ID = "_id";
	public static final String COLUMN_ALBUM_NAME = "name";
	public static final String COLUMN_ALBUM_ARTIST = "artist";
	public static final int INDEX_ALBUMS_ID = 1;
	public static final int INDEX_ALBUMS_NAME = 2;
	public static final int INDEX_ALBUMS_ARTIST = 3;

	public static final String TABLE_ARTISTS = "artists";
	public static final String COLUMN_ARTIST_ID = "_id";
	public static final String COLUMN_ARTIST_NAME = "name";
	public static final int INDEX_ARTISTS_ID = 1;
	public static final int INDEX_ARTISTS_NAME = 2;

	public static final int ORDER_BY_NONE = 1;
	public static final int ORDER_BY_ASC = 2;
	public static final int ORDER_BY_DESC = 3;

	public static final String QUERY_ARTISTS_LIST_START = "SELECT * FROM " + TABLE_ARTISTS;
	public static final String QUERY_ARTISTS_LIST_ORDER = " ORDER BY " + TABLE_ARTISTS + "." + COLUMN_ARTIST_NAME
			+ " COLLATE NOCASE ";

	public static final String QUERY_ALBUMS_BY_ARTIST_START = "SELECT " + TABLE_ALBUMS + "." + COLUMN_ALBUM_NAME
			+ " FROM " + TABLE_ALBUMS + " INNER JOIN " + TABLE_ARTISTS + " ON " + TABLE_ALBUMS + "."
			+ COLUMN_ALBUM_ARTIST + " = " + TABLE_ARTISTS + "." + COLUMN_ARTIST_ID + " WHERE " + TABLE_ARTISTS + "."
			+ COLUMN_ARTIST_NAME + " = '";
	public static final String QUERY_ALBUMS_BY_ARTIST_SORT = " ORDER BY " + TABLE_ALBUMS + "." + COLUMN_ALBUM_NAME
			+ " COLLATE NOCASE ";

	public static final String QUERY_ARTIST_FOR_SONG_START = "SELECT " + TABLE_ARTISTS + "." + COLUMN_ARTIST_NAME + ", "
			+ TABLE_ALBUMS + "." + COLUMN_ALBUM_NAME + ", " + TABLE_SONGS + "." + COLUMN_SONG_TRACK + " FROM "
			+ TABLE_ARTISTS + " INNER JOIN " + TABLE_ALBUMS + " ON " + TABLE_ARTISTS + "." + COLUMN_ARTIST_ID + " = "
			+ TABLE_ALBUMS + "." + COLUMN_ALBUM_ARTIST + " INNER JOIN " + TABLE_SONGS + " ON " + TABLE_ALBUMS + "."
			+ COLUMN_ALBUM_ID + " = " + TABLE_SONGS + "." + COLUMN_SONG_ALBUM + " WHERE " + TABLE_SONGS + "."
			+ COLUMN_SONG_TITLE + " = '";
	public static final String QUERY_ARTIST_FOR_SONG_SORT = " ORDER BY ARTIST, ALBUM COLLATE NOCASE ";

	public static final String TABLE_ARTIST_SONG_VIEW = "artist_list";
	public static final String ALIAS_ARTIST_NAME = "artist";
	public static final String ALIAS_ALBUM_NAME = "album";
	public static final String CREATE_ARTIST_FOR_SONG_VIEW = "CREATE VIEW IF NOT EXISTS " + TABLE_ARTIST_SONG_VIEW
			+ " AS SELECT " + TABLE_ARTISTS + "." + COLUMN_ARTIST_NAME + " AS " + ALIAS_ARTIST_NAME + " , "
			+ TABLE_ALBUMS + "." + COLUMN_ALBUM_NAME + " AS " + ALIAS_ALBUM_NAME + " , " + TABLE_SONGS + "."
			+ COLUMN_SONG_TRACK + ", " + TABLE_SONGS + "." + COLUMN_SONG_TITLE + " FROM " + TABLE_SONGS + " INNER JOIN "
			+ TABLE_ALBUMS + " ON " + TABLE_SONGS + "." + COLUMN_SONG_ALBUM + " = " + TABLE_ALBUMS + "."
			+ COLUMN_ALBUM_ID + " INNER JOIN " + TABLE_ARTISTS + " ON " + TABLE_ALBUMS + "." + COLUMN_ALBUM_ARTIST
			+ " = " + TABLE_ARTISTS + "." + COLUMN_ARTIST_ID + " ORDER BY " + ALIAS_ARTIST_NAME + " , "
			+ ALIAS_ALBUM_NAME + " , " + TABLE_SONGS + "." + COLUMN_SONG_TRACK + " COLLATE NOCASE ASC";
	public static final String QUERY_VIEW_SONG_INFO_PRE = "SELECT " + ALIAS_ARTIST_NAME + " , " + ALIAS_ALBUM_NAME
			+ " , " + COLUMN_SONG_TRACK + " , " + COLUMN_SONG_TITLE + " FROM " + TABLE_ARTIST_SONG_VIEW + " WHERE "
			+ COLUMN_SONG_TITLE + " = ?";

	public static final String INSERT_ARTIST = "INSERT INTO " + TABLE_ARTISTS + " (" + COLUMN_ARTIST_NAME
			+ ") VALUES (?)";
	public static final String INSERT_ALBUM = "INSERT INTO " + TABLE_ALBUMS + " (" + COLUMN_ALBUM_NAME + ", "
			+ COLUMN_ALBUM_ARTIST + ") VALUES (?, ?)";
	public static final String INSERT_SONG = "INSERT INTO " + TABLE_SONGS + " (" + COLUMN_SONG_TRACK + ", "
			+ COLUMN_SONG_TITLE + ", " + COLUMN_SONG_ALBUM + ") VALUES (?, ?, ?)";
	public static final String QUERY_ARTIST = "SELECT " + COLUMN_ARTIST_ID + " FROM " + TABLE_ARTISTS + " WHERE "
			+ COLUMN_ARTIST_NAME + " = ?";
	public static final String QUERY_ALBUM = "SELECT " + COLUMN_ALBUM_ID + " FROM " + TABLE_ALBUMS + " WHERE "
			+ COLUMN_ALBUM_NAME + " = ?";
	public static final String QUERY_SONG = "SELECT " + COLUMN_SONG_ID + " FROM " + TABLE_SONGS + " WHERE "
			+ COLUMN_SONG_TITLE + " = ?";

	private Connection conn;
	private PreparedStatement querySongInfoView;
	private PreparedStatement insertIntoArtist;
	private PreparedStatement insertIntoAlbum;
	private PreparedStatement insertIntoSong;
	private PreparedStatement queryArtist;
	private PreparedStatement queryAlbum;
	private PreparedStatement querySong;

	public boolean open() {
		try {
			conn = DriverManager.getConnection(CONNECTION_STRING);
			querySongInfoView = conn.prepareStatement(QUERY_VIEW_SONG_INFO_PRE);
			insertIntoArtist = conn.prepareStatement(INSERT_ARTIST, Statement.RETURN_GENERATED_KEYS);
			insertIntoAlbum = conn.prepareStatement(INSERT_ALBUM, Statement.RETURN_GENERATED_KEYS);
			insertIntoSong = conn.prepareStatement(INSERT_SONG);
			queryArtist = conn.prepareStatement(QUERY_ARTIST);
			queryAlbum = conn.prepareStatement(QUERY_ALBUM);
			querySong = conn.prepareStatement(QUERY_SONG);
			return true;
		} catch (SQLException e) {
			System.out.println("Could not connect to DB - " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	public void close() {
		try {
			if (querySongInfoView != null) {
				querySongInfoView.close();
			}
			if (insertIntoArtist != null) {
				insertIntoArtist.close();
			}
			if (insertIntoAlbum != null) {
				insertIntoAlbum.close();
			}
			if (insertIntoSong != null) {
				insertIntoSong.close();
			}
			if (queryArtist != null) {
				queryArtist.close();
			}
			if (queryAlbum != null) {
				queryAlbum.close();
			}
			if (querySong != null) {
				querySong.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("Couldnot close the DB -" + e.getMessage());
			e.printStackTrace();
		}
	}

	public List<Artist> queryArtist() {
		List<Artist> artists = new ArrayList<>();
		try (Statement statement = conn.createStatement();
				ResultSet results = statement.executeQuery(QUERY_ARTISTS_LIST_START)) {
			while (results.next()) {
				artists.add(new Artist(results.getInt(1), results.getString(2)));
			}
			return artists;
		} catch (SQLException e) {
			System.out.println("Query failed - " + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	public List<Artist> queryArtist(int sortOrder) {
		StringBuilder sb = new StringBuilder(QUERY_ARTISTS_LIST_START);
		if (sortOrder != ORDER_BY_NONE) {
			sb.append(QUERY_ARTISTS_LIST_ORDER);
			if (sortOrder == ORDER_BY_DESC) {
				sb.append("DESC");
			} else {
				sb.append("ASC");
			}
		}

		System.out.println("SQL Statement = " + sb.toString());
		try (Statement statement = conn.createStatement(); ResultSet results = statement.executeQuery(sb.toString())) {
			List<Artist> artists = new ArrayList<>();
			while (results.next()) {
				artists.add(new Artist(results.getInt(1), results.getString(2)));
			}
			return artists;
		} catch (SQLException e) {
			System.out.println("Query failed - " + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	public List<String> queryAlbumsForArtist(String artistName, int sortOrder) {
		StringBuilder sb = new StringBuilder(QUERY_ALBUMS_BY_ARTIST_START);
		sb.append(artistName);
		sb.append("'");
		if (sortOrder != ORDER_BY_NONE) {
			sb.append(QUERY_ALBUMS_BY_ARTIST_SORT);
			if (sortOrder == ORDER_BY_DESC) {
				sb.append("DESC");
			} else {
				sb.append("ASC");
			}
		}

		System.out.println("SQL Statement = " + sb.toString());

		try (Statement statement = conn.createStatement(); ResultSet results = statement.executeQuery(sb.toString())) {
			List<String> albums = new ArrayList<>();
			while (results.next()) {
				albums.add(results.getString(1));
			}
			return albums;
		} catch (SQLException e) {
			System.out.println("Query failed - " + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	public List<SongArtist> queryAristsForSong(String songName, int sortOrder) {
		StringBuilder sb = new StringBuilder(QUERY_ARTIST_FOR_SONG_START);
		sb.append(songName);
		sb.append("'");
		if (sortOrder != ORDER_BY_NONE) {
			sb.append(QUERY_ARTIST_FOR_SONG_SORT);
			if (sortOrder == ORDER_BY_DESC) {
				sb.append("DESC");
			} else {
				sb.append("ASC");
			}
		}

		System.out.println("SQL Statement = " + sb.toString());

		try (Statement statement = conn.createStatement(); ResultSet results = statement.executeQuery(sb.toString())) {
			List<SongArtist> songArtists = new ArrayList<>();
			while (results.next()) {
				songArtists.add(new SongArtist(results.getString(1), results.getString(2), results.getInt(3)));
			}
			return songArtists;
		} catch (SQLException e) {
			System.out.println("Query failed - " + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	public void querySongMetadata() {
		String query = "SELECT * FROM " + TABLE_SONGS;
		try (Statement statement = conn.createStatement(); ResultSet results = statement.executeQuery(query)) {
			ResultSetMetaData meta = results.getMetaData();
			int numColums = meta.getColumnCount();
			for (int i = 1; i <= numColums; i++) {
				System.out.format("Column %d in the songs table has name is %s\n", i, meta.getColumnName(i));
			}
		} catch (SQLException e) {
			System.out.println("Query failed - " + e.getMessage());
			e.printStackTrace();
		}
	}

	public int getCount(String table) {
		String query = "SELECT COUNT(DISTINCT TITLE) AS COUNT, MAX(_id) AS MAX FROM " + table;
		try (Statement statement = conn.createStatement(); ResultSet results = statement.executeQuery(query)) {
			int count = results.getInt("COUNT");
			int max = results.getInt("MAX");
			System.out.format("Count = %d , max = %d", count, max);
			return count;
		} catch (SQLException e) {
			System.out.println("Query failed - " + e.getMessage());
			e.printStackTrace();
			return -1;
		}
	}

	public boolean createViewForSongArtist() {
		try (Statement statement = conn.createStatement()) {
			statement.execute(CREATE_ARTIST_FOR_SONG_VIEW);
			return true;
		} catch (SQLException e) {
			System.out.println("Create View failed - " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	public List<SongArtist> querySongInfoView(String title) {
		try {
			querySongInfoView.setString(1, title);
			ResultSet results = querySongInfoView.executeQuery();
			List<SongArtist> songArtists = new ArrayList<>();
			while (results.next()) {
				songArtists.add(new SongArtist(results.getString(1), results.getString(2), results.getInt(3)));
			}
			return songArtists;
		} catch (SQLException e) {
			System.out.println("Query failed - " + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	private int insertArtist(String name) throws SQLException {
		queryArtist.setString(1, name);
		ResultSet results = queryArtist.executeQuery();
		if (results.next()) {
			return results.getInt(1);
		} else {
			insertIntoArtist.setString(1, name);
			int affectedRows = insertIntoArtist.executeUpdate();
			if (affectedRows != 1) {
				throw new SQLException("Could not insert artist");
			}
			ResultSet generatedKey = insertIntoArtist.getGeneratedKeys();
			if (generatedKey.next()) {
				return generatedKey.getInt(1);
			} else {
				throw new SQLException("Could not get _id for artist");
			}
		}
	}

	private int insertAlbum(String name, int artistID) throws SQLException {
		queryAlbum.setString(1, name);
		ResultSet results = queryAlbum.executeQuery();
		if (results.next()) {
			return results.getInt(1);
		} else {
			insertIntoAlbum.setString(1, name);
			insertIntoAlbum.setInt(2, artistID);
			int affectedRows = insertIntoAlbum.executeUpdate();
			if (affectedRows != 1) {
				throw new SQLException("Could not insert album");
			}
			ResultSet generatedKey = insertIntoAlbum.getGeneratedKeys();
			if (generatedKey.next()) {
				return generatedKey.getInt(1);
			} else {
				throw new SQLException("Could not get _id for album");
			}
		}
	}

	private boolean querySong(String title){
		try {
			querySong.setString(1, title);
			ResultSet results = querySong.executeQuery();
			if (results.next()) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}		
	}

	public void insertSong(String title, String artist, String album, int track) {
		if (querySong(title)) {
			System.out.println("Song " + title + " already exist in DB");
		} else {
			try {
				conn.setAutoCommit(false);
				int artistID = insertArtist(artist);
				int albumID = insertAlbum(album, artistID);
				insertIntoSong.setInt(1, track);
				insertIntoSong.setString(2, title);
				insertIntoSong.setInt(3, albumID);
				int affectedRows = insertIntoSong.executeUpdate();
				if (affectedRows == 1) {
					conn.commit();
					System.out.println("Song is added - " + title);
				} else {
					throw new SQLException("Song insert failed");
				}
			} catch (Exception e) {
				System.out.println("Insert Song exception " + e.getMessage());
				try {
					System.out.println("Performaing roll back");
					conn.rollback();
				} catch (SQLException e1) {
					System.out.println("Cannot Roll back -> BAD " + e1.getMessage());
				}
			} finally {
				try {
					System.out.println("Reset Autocommit to true");
					conn.setAutoCommit(true);
				} catch (SQLException e) {
					System.out.println("Cannot reset Auto commit to true");
				}
			}
		}
	}
}
