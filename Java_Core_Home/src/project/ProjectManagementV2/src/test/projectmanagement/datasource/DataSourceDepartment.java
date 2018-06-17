package test.projectmanagement.datasource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import test.projectmanagement.datamodel.Department;
import test.projectmanagement.datamodel.LogOperation;

public class DataSourceDepartment {
	private static DataSourceDepartment instance = new DataSourceDepartment();

	private DataSourceDepartment() {
	}

	public static DataSourceDepartment getInstance() {
		return instance;
	}

	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/projectmanagement?useSSL=false";
	static final String USER = "root";
	static final String PASS = "root123456";

	private static final String TABLE_DEPARTMENT = "department";
	private static final String COLUMN_DEPARTMENT_ID = "id";
	private static final String COLUMN_DEPARTMENT_NAME = "deptName";

	private static final String QUERY_DEPARTMENT_INFO = "SELECT * FROM " + TABLE_DEPARTMENT + " ORDER BY "
			+ COLUMN_DEPARTMENT_ID + " ASC";
	private static final String QUERY_DEPARTMENT_COUNT = "SELECT COUNT(*) FROM " + TABLE_DEPARTMENT;
	private static final String CHECK_DEPARTMENT = "SELECT * FROM " + TABLE_DEPARTMENT + " WHERE "
			+ COLUMN_DEPARTMENT_ID + " = ?";
	private static final String INSERT_DEPARTMENT_ID_NAME = "INSERT INTO " + TABLE_DEPARTMENT + " ("
			+ COLUMN_DEPARTMENT_ID + ", " + COLUMN_DEPARTMENT_NAME + ") VALUES (?, ?)";
	private static final String INSERT_DEPARTMENT_NAME = "INSERT INTO " + TABLE_DEPARTMENT + " ("
			+ COLUMN_DEPARTMENT_NAME + ") VALUES (?)";
	private static final String UPDATE_DEPARTMENT = "UPDATE " + TABLE_DEPARTMENT + " SET " + COLUMN_DEPARTMENT_NAME
			+ " = ?" + " WHERE " + COLUMN_DEPARTMENT_ID + " = ?";
	private static final String DELETE_DEPARTMENT = "DELETE FROM " + TABLE_DEPARTMENT + " WHERE id = ?";
	private static final String SEARCH_DEPARTMENT_ID = "SELECT " + COLUMN_DEPARTMENT_NAME + " FROM " + TABLE_DEPARTMENT
			+ " WHERE " + COLUMN_DEPARTMENT_ID + " = ?";
	private static final String SEARCH_DEPARTMENT_NAME = "SELECT " + COLUMN_DEPARTMENT_ID + " FROM " + TABLE_DEPARTMENT
			+ " WHERE " + COLUMN_DEPARTMENT_NAME + " = ?";
	private static final String SEARCH_DEPARTMENT_ID_NAME = "SELECT * FROM " + TABLE_DEPARTMENT + " WHERE "
			+ COLUMN_DEPARTMENT_ID + " = ? AND " + COLUMN_DEPARTMENT_NAME + " LIKE ?";
	private static final String SEARCH_DEPARTMENT_COUNT = "SELECT COUNT(*) FROM " + TABLE_DEPARTMENT + " WHERE "
			+ COLUMN_DEPARTMENT_ID + " = ? AND " + COLUMN_DEPARTMENT_NAME + " LIKE ?";

	public Connection conn;
	private PreparedStatement queryDepartment;
	private PreparedStatement checkDepartment;
	private PreparedStatement insertDepartmentIdName;
	private PreparedStatement insertDepartmentName;
	private PreparedStatement queryDepartmentCount;
	private PreparedStatement updateDepartment;
	private PreparedStatement deleteDepartment;
	private PreparedStatement searchDepartmentID;
	private PreparedStatement searchDepartmentName;
	private PreparedStatement searchDepartmentIDName;
	private PreparedStatement searchDepartmentCount;

	public boolean open() {
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			queryDepartment = DataSource.getInstance().conn.prepareStatement(QUERY_DEPARTMENT_INFO);
			queryDepartmentCount = DataSource.getInstance().conn.prepareStatement(QUERY_DEPARTMENT_COUNT);
			checkDepartment = DataSource.getInstance().conn.prepareStatement(CHECK_DEPARTMENT);
			insertDepartmentIdName = DataSource.getInstance().conn.prepareStatement(INSERT_DEPARTMENT_ID_NAME);
			insertDepartmentName = DataSource.getInstance().conn.prepareStatement(INSERT_DEPARTMENT_NAME,
					Statement.RETURN_GENERATED_KEYS);
			updateDepartment = DataSource.getInstance().conn.prepareStatement(UPDATE_DEPARTMENT);
			deleteDepartment = DataSource.getInstance().conn.prepareStatement(DELETE_DEPARTMENT,
					Statement.RETURN_GENERATED_KEYS);
			searchDepartmentID = DataSource.getInstance().conn.prepareStatement(SEARCH_DEPARTMENT_ID);
			searchDepartmentName = DataSource.getInstance().conn.prepareStatement(SEARCH_DEPARTMENT_NAME);
			searchDepartmentIDName = DataSource.getInstance().conn.prepareStatement(SEARCH_DEPARTMENT_ID_NAME);
			searchDepartmentCount = DataSource.getInstance().conn.prepareStatement(SEARCH_DEPARTMENT_COUNT);
			return true;
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println("Could not connect to DB - " + e.getMessage());
			return false;
		}
	}

	public void close() {
		try {
			if (queryDepartment != null) {
				queryDepartment.close();
			}
			if (queryDepartmentCount != null) {
				queryDepartmentCount.close();
			}
			if (checkDepartment != null) {
				checkDepartment.close();
			}
			if (insertDepartmentIdName != null) {
				insertDepartmentIdName.close();
			}
			if (insertDepartmentName != null) {
				insertDepartmentName.close();
			}
			if (updateDepartment != null) {
				updateDepartment.close();
			}
			if (deleteDepartment != null) {
				deleteDepartment.close();
			}
			if (searchDepartmentID != null) {
				searchDepartmentID.close();
			}
			if (searchDepartmentName != null) {
				searchDepartmentName.close();
			}
			if (searchDepartmentIDName != null) {
				searchDepartmentIDName.close();
			}
			if (searchDepartmentCount != null) {
				searchDepartmentCount.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("Couldnot close the DB -" + e.getMessage());
		}
	}

	public List<Department> listAllDepartment() {
		try {
			ResultSet results = queryDepartment.executeQuery();
			List<Department> deptList = new ArrayList<>();
			while (results.next()) {
				deptList.add(new Department(results.getInt(1), results.getString(2)));
			}
			return deptList;
		} catch (SQLException e) {
			System.out.println("Query failed - Can not load department information " + e.getMessage());
			return null;
		}
	}

	public int queryDepartmentCount() {
		int count = 0;
		try {
			ResultSet results = queryDepartmentCount.executeQuery();
			while (results.next()) {
				count = results.getInt(1);
			}
			return count;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}

	public boolean insertDepartmentIdName(int departmentId, String departmentName) throws SQLException {
		checkDepartment.setInt(1, departmentId);
		ResultSet results = checkDepartment.executeQuery();
		if (results.next()) {
			DataSource.getInstance()
					.insertLog(new LogOperation(DataSource.OPERATION_INSERT, TABLE_DEPARTMENT,
							String.valueOf(new Timestamp(System.currentTimeMillis())),
							DataSource.OPERATION_RESULT_FAILED, "Deparment ID already exist"));
			return false;
		} else {
			insertDepartmentIdName.setInt(1, departmentId);
			insertDepartmentIdName.setString(2, departmentName);
			int affectedRows = insertDepartmentIdName.executeUpdate();
			System.out.println(affectedRows);
			if (affectedRows != 1) {
				DataSource.getInstance()
						.insertLog(new LogOperation(DataSource.OPERATION_INSERT, TABLE_DEPARTMENT,
								String.valueOf(new Timestamp(System.currentTimeMillis())),
								DataSource.OPERATION_RESULT_FAILED, "Could not insert department - Operation Failed"));
				throw new SQLException("Could not insert department");
			} else {
				DataSource.getInstance()
						.insertLog(new LogOperation(DataSource.OPERATION_INSERT, TABLE_DEPARTMENT,
								String.valueOf(new Timestamp(System.currentTimeMillis())),
								DataSource.OPERATION_RESULT_SUCCESS, "DONE"));
				return true;
			}
		}
	}

	public int insertDepartmentName(String departmentName) throws SQLException {
		System.out.println("We are in In insertDepartmentName - " + departmentName);
		insertDepartmentName.setString(1, departmentName);
		int affectedRows = insertDepartmentName.executeUpdate();
		if (affectedRows != 1) {
			DataSource.getInstance()
					.insertLog(new LogOperation(DataSource.OPERATION_INSERT, TABLE_DEPARTMENT,
							String.valueOf(new Timestamp(System.currentTimeMillis())),
							DataSource.OPERATION_RESULT_FAILED, "Could not insert department - Operation Failed"));
			throw new SQLException("Could not insert department");
		}
		ResultSet generatedKey = insertDepartmentName.getGeneratedKeys();
		if (generatedKey.next()) {
			DataSource.getInstance()
					.insertLog(new LogOperation(DataSource.OPERATION_INSERT, TABLE_DEPARTMENT,
							String.valueOf(new Timestamp(System.currentTimeMillis())),
							DataSource.OPERATION_RESULT_SUCCESS, "DONE"));
			return generatedKey.getInt(1);
		} else {
			DataSource.getInstance()
					.insertLog(new LogOperation(DataSource.OPERATION_INSERT, TABLE_DEPARTMENT,
							String.valueOf(new Timestamp(System.currentTimeMillis())),
							DataSource.OPERATION_RESULT_FAILED, "Could not get _id for department - Operation Failed"));
			throw new SQLException("Could not get _id for department");
		}
	}

	public boolean updateDepartment(int departmentId, String departmentName) {
		try {
			updateDepartment.setString(1, departmentName);
			updateDepartment.setInt(2, departmentId);
			int numRowsEffected = updateDepartment.executeUpdate();
			DataSource.getInstance()
					.insertLog(new LogOperation(DataSource.OPERATION_UPDATE, TABLE_DEPARTMENT,
							String.valueOf(new Timestamp(System.currentTimeMillis())),
							DataSource.OPERATION_RESULT_SUCCESS, "DONE"));
			return numRowsEffected == 1;
		} catch (SQLException e) {
			DataSource.getInstance()
					.insertLog(new LogOperation(DataSource.OPERATION_UPDATE, TABLE_DEPARTMENT,
							String.valueOf(new Timestamp(System.currentTimeMillis())),
							DataSource.OPERATION_RESULT_FAILED, "Update Fails"));
			System.out.println("Update Fails - " + e.getMessage());
			return false;
		}
	}

	public boolean deleteDepartment(int departmentId) {
		try {
			deleteDepartment.setInt(1, departmentId);
			int numRowsEffected = deleteDepartment.executeUpdate();
			DataSource.getInstance()
					.insertLog(new LogOperation(DataSource.OPERATION_DELETE, TABLE_DEPARTMENT,
							String.valueOf(new Timestamp(System.currentTimeMillis())),
							DataSource.OPERATION_RESULT_SUCCESS, "DONE"));
			return numRowsEffected == 1;
		} catch (SQLException e) {
			DataSource.getInstance()
					.insertLog(new LogOperation(DataSource.OPERATION_DELETE, TABLE_DEPARTMENT,
							String.valueOf(new Timestamp(System.currentTimeMillis())),
							DataSource.OPERATION_RESULT_FAILED, "Delete Fails"));
			System.out.println("Delete Fails - " + e.getMessage());
			return false;
		}
	}

	public String searchDepartmentID(int departmentId) {
		try {
			searchDepartmentID.setInt(1, departmentId);
			ResultSet results = searchDepartmentID.executeQuery();
			String departmentName = null;
			while (results.next()) {
				departmentName = results.getString(1);
			}
			return departmentName;
		} catch (SQLException e) {
			System.out.println("Search not found the department ID - " + e.getMessage());
			return null;
		}
	}

	public int searchDepartmentName(String departmentName) {
		try {
			searchDepartmentName.setString(1, departmentName);
			ResultSet results = searchDepartmentName.executeQuery();
			int departmentId = 0;
			while (results.next()) {
				departmentId = results.getInt(1);
			}
			return departmentId;
		} catch (SQLException e) {
			System.out.println("Search not found the department name - " + e.getMessage());
			return 0;
		}
	}

	public List<Department> searchDepartmentIDName(int departmentId, String departmentName) {
		try {
			searchDepartmentIDName.setInt(1, departmentId);
			searchDepartmentIDName.setString(2, "%" + departmentName + "%");
			ResultSet results = searchDepartmentIDName.executeQuery();
			List<Department> deptList = new ArrayList<>();
			while (results.next()) {
				deptList.add(new Department(results.getInt(1), results.getString(2)));
			}
			return deptList;
		} catch (SQLException e) {
			System.out.println("Query failed - Can not search department information " + e.getMessage());
			return null;
		}
	}

	public int queryDepartmentSearchCount(int departmentId, String departmentName) {
		try {
			searchDepartmentCount.setInt(1, departmentId);
			searchDepartmentCount.setString(2, "%" + departmentName + "%");
			System.out.println(searchDepartmentCount);
			ResultSet results = searchDepartmentCount.executeQuery();
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
