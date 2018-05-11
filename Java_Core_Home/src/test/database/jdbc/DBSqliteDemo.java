package test.database.jdbc;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBSqliteDemo {
	public static final String DB_NAME = "test.db";
	public static final String CONNECTION_STRING = "jdbc:sqlite:Java_Core_Home\\src\\test\\database\\jdbc\\" + DB_NAME;
	public static final String TABLE_CONTACTS = "contacts";
	public static final String COLUMN_NAME = "name";
	public static final String COLUMN_PHONE = "phone";
	public static final String COLUMN_EMAIL = "email";

	public static void main(String[] args) {
		try (Connection conn = DriverManager.getConnection(CONNECTION_STRING);
				Statement statement = conn.createStatement()) {
			// statement.execute("CREATE TABLE IF NOT EXISTS " + TABLE_CONTACTS + "(COLUMN_NAME			
			// TEXT, COLUMN_PHONE INTEGER, COLUMN_EMAIL TEXT)");
			// statement.execute(
			// "INSERT INTO " + TABLE_CONTACTS + "VALUES ('tu','0915668227', 'Ttu@gmail.com')");
			// statement.execute("UPDATE" + TABLE_CONTACTS + "SET phone = 123456 WHERE " + COLUMN_NAME +"
			// = 'tu'");
			// statement.execute("DELETE FROM TABLE_CONTACTS WHERE COLUMN_NAME = 'tu'");

			ResultSet results = statement.executeQuery("SELECT * FROM " + TABLE_CONTACTS);
			while (results.next()) {
				System.out.println(results.getString(COLUMN_NAME) + "\t" + results.getInt(COLUMN_PHONE) + "\t"
						+ results.getString(COLUMN_EMAIL));
			}
			results.close();

		} catch (java.sql.SQLException e) {
			System.out.println("Error " + e.getMessage());
			e.printStackTrace();
		}
	}
}

// File currentWorkingDirectory = new File("").getAbsoluteFile();
// System.out.print("Current working directory \n\t" + currentWorkingDirectory);
