package test.projectmanagement.datasource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import test.projectmanagement.datamodel.LogOperation;
import test.projectmanagement.datamodel.ProjectCustomer;

public class DataSource {
	private static DataSource instance = new DataSource();

	private DataSource() {
	}

	public static DataSource getInstance() {
		return instance;
	}

	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/projectmanagement?useSSL=false";
	static final String USER = "root";
	static final String PASS = "root123456";

	public static final String TABLE_LOG = "log";
	public static final String COLUMN_LOG_OPERATION = "operation";
	public static final String COLUMN_LOG_TABLE_NAME = "tablename";
	public static final String COLUMN_LOG_AT_TIME = "at_time";
	public static final String COLUMN_LOG_RESULT = "result";
	public static final String COLUMN_LOG_REASON = "reason";
	public static final String OPERATION_INSERT = "INSERT";
	public static final String OPERATION_UPDATE = "UPDATE";
	public static final String OPERATION_DELETE = "DELETE";
	public static final String OPERATION_RESULT_FAILED = "FAILED";
	public static final String OPERATION_RESULT_SUCCESS = "SUCCESS";

	private static final String VIEW_CUSTOMER_PROJECT = "customer_project_view";
	private static final String QUERY_VIEW_CUSTOMER_PROJECT = "SELECT * FROM " + VIEW_CUSTOMER_PROJECT;
	private static final String QUERY_VIEW_CUSTOMER_PROJECT_COUNT = "SELECT COUNT(*) FROM " + VIEW_CUSTOMER_PROJECT;

	private static final String INSERT_LOG = "INSERT INTO " + TABLE_LOG + " (" + COLUMN_LOG_OPERATION + ", "
			+ COLUMN_LOG_TABLE_NAME + ", " + COLUMN_LOG_AT_TIME + ", " + COLUMN_LOG_RESULT + ", " + COLUMN_LOG_REASON
			+ ") VALUES (?, ?, ?, ?, ?)";
	private static final String QUERY_LOG = "SELECT * FROM " + TABLE_LOG;
	private static final String QUERY_LOG_COUNT = "SELECT COUNT(*) FROM " + TABLE_LOG;

	public Connection conn;
	private PreparedStatement insertLog;
	private PreparedStatement queryLog;
	private PreparedStatement queryLogCount;
	private PreparedStatement viewCustomerProject;
	private PreparedStatement viewCustomerProjectCount;

	public boolean open() {
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			insertLog = conn.prepareStatement(INSERT_LOG);
			queryLog = conn.prepareStatement(QUERY_LOG);
			queryLogCount = conn.prepareStatement(QUERY_LOG_COUNT);
			viewCustomerProject = conn.prepareStatement(QUERY_VIEW_CUSTOMER_PROJECT);
			viewCustomerProjectCount = conn.prepareStatement(QUERY_VIEW_CUSTOMER_PROJECT_COUNT);

			return true;
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println("Could not connect to DB - " + e.getMessage());
			return false;
		}
	}

	public void close() {
		try {
			if (insertLog != null) {
				insertLog.close();
			}
			if (queryLog != null) {
				queryLog.close();
			}
			if (queryLogCount != null) {
				queryLogCount.close();
			}
			if (viewCustomerProject != null) {
				viewCustomerProject.close();
			}
			if (viewCustomerProjectCount != null) {
				viewCustomerProjectCount.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("Couldnot close the DB -" + e.getMessage());
		}
	}

	public void insertLog(LogOperation logOperation) {
		try {
			insertLog.setString(1, logOperation.getOperation());
			insertLog.setString(2, logOperation.getTableName());
			insertLog.setString(3, logOperation.getAt_time());
			insertLog.setString(4, logOperation.getResult());
			insertLog.setString(5, logOperation.getReason());
			insertLog.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<LogOperation> listAllLog() {
		try {
			ResultSet results = queryLog.executeQuery();
			List<LogOperation> logList = new ArrayList<>();
			while (results.next()) {
				logList.add(new LogOperation(results.getInt(1), results.getString(2), results.getString(3),
						results.getString(4), results.getString(5), results.getString(6)));
			}
			return logList;
		} catch (SQLException e) {
			System.out.println("Query failed - Can not load log information " + e.getMessage());
			return null;
		}
	}

	public int listLogCount() {
		try {
			ResultSet results = queryLogCount.executeQuery();
			int count = 0;
			while (results.next()) {
				count = results.getInt(1);
			}
			return count;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}

	public List<ProjectCustomer> viewCustomerProject() {
		try {
			ResultSet results = viewCustomerProject.executeQuery();
			List<ProjectCustomer> projectCustomers = new ArrayList<>();
			while (results.next()) {
				projectCustomers.add(new ProjectCustomer(results.getString(1), results.getString(2),
						results.getString(3), results.getString(4), results.getString(5)));
			}
			return projectCustomers;
		} catch (SQLException e) {
			System.out.println("Query failed - Can not load project customer information " + e.getMessage());
			return null;
		}
	}

	public int viewCustomerProjectCount() {
		try {
			ResultSet results = viewCustomerProjectCount.executeQuery();
			int count = 0;
			while (results.next()) {
				count = results.getInt(1);
			}
			return count;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}
}
