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
import test.projectmanagement.datamodel.LogOperation;
import test.projectmanagement.datamodel.Project;

public class DataSourceProject {
	private static DataSourceProject instance = new DataSourceProject();

	private DataSourceProject() {
	}

	public static DataSourceProject getInstance() {
		return instance;
	}

	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/projectmanagement?useSSL=false";
	static final String USER = "root";
	static final String PASS = "root123456";

	private static final String TABLE_PROJECT = "project";
	private static final String COLUMN_PROJECT_ID = "id";
	private static final String COLUMN_PROJECT_NAME = "projectName";
	private static final String COLUMN_PROJECT_STATUS = "status";
	private static final String COLUMN_PROJECT_CATEGORY = "projectCategory";

	private static final String TABLE_PROJECT_CUSTOMER = "project_customer";
	private static final String COLUMN_PROJECT_CUSTOMER_PROJECT_ID = "Project_id";

	private static final String TABLE_EMPLOYEE_PROJECT = "employee_project";
	private static final String COLUMN_EMPLOYEE_PROJECT_PROJECT_ID = "Project_id";

	private static final String QUERY_PROJECT_INFO = "SELECT * FROM " + TABLE_PROJECT + " ORDER BY " + COLUMN_PROJECT_ID
			+ " ASC";
	private static final String QUERY_PROJECT_COUNT = "SELECT COUNT(*) FROM " + TABLE_PROJECT;
	private static final String INSERT_PROJECT_NAME = "INSERT INTO " + TABLE_PROJECT + " (" + COLUMN_PROJECT_NAME + ", "
			+ COLUMN_PROJECT_STATUS + ", " + COLUMN_PROJECT_CATEGORY + ") VALUES (?, ?, ?)";
	private static final String CHECK_PROJECT = "SELECT * FROM " + TABLE_PROJECT + " WHERE " + COLUMN_PROJECT_ID
			+ " = ?";
	private static final String INSERT_PROJECT_ID_NAME = "INSERT INTO " + TABLE_PROJECT + " (" + COLUMN_PROJECT_ID
			+ ", " + COLUMN_PROJECT_NAME + ", " + COLUMN_PROJECT_STATUS + ", " + COLUMN_PROJECT_CATEGORY
			+ ") VALUES (?, ?, ?, ?)";
	private static final String UPDATE_PROJECT = "UPDATE " + TABLE_PROJECT + " SET " + COLUMN_PROJECT_NAME + " = ?, "
			+ COLUMN_PROJECT_STATUS + " = ?, " + COLUMN_PROJECT_CATEGORY + " = ? WHERE " + COLUMN_PROJECT_ID + " = ?";
	private static final String DELETE_PROJECT = "DELETE FROM " + TABLE_PROJECT + " WHERE id = ?";
	private static final String SEARCH_PROJECT_ID = "SELECT * FROM " + TABLE_PROJECT + " WHERE " + COLUMN_PROJECT_ID
			+ " = ?";
	private static final String SEARCH_PROJECT_NAME = "SELECT * FROM " + TABLE_PROJECT + " WHERE " + COLUMN_PROJECT_NAME
			+ " LIKE ?";
	private static final String SEARCH_PROJECT_STATUS = "SELECT * FROM " + TABLE_PROJECT + " WHERE "
			+ COLUMN_PROJECT_STATUS + " LIKE ?";
	private static final String SEARCH_PROJECT_CATEGORY = "SELECT * FROM " + TABLE_PROJECT + " WHERE "
			+ COLUMN_PROJECT_CATEGORY + " LIKE ?";
	private static final String SEARCH_PROJECT_NAME_STATUS = "SELECT * FROM " + TABLE_PROJECT + " WHERE "
			+ COLUMN_PROJECT_NAME + " LIKE ? AND " + COLUMN_PROJECT_STATUS + " LIKE ?";
	private static final String SEARCH_PROJECT_NAME_CATEGORY = "SELECT * FROM " + TABLE_PROJECT + " WHERE "
			+ COLUMN_PROJECT_NAME + " LIKE ? AND " + COLUMN_PROJECT_CATEGORY + " LIKE ?";
	private static final String SEARCH_PROJECT_STATUS_CATEGORY = "SELECT * FROM " + TABLE_PROJECT + " WHERE "
			+ COLUMN_PROJECT_STATUS + " LIKE ? AND " + COLUMN_PROJECT_CATEGORY + " LIKE ?";
	private static final String SEARCH_PROJECT_NAME_COUNT = "SELECT COUNT(*) FROM " + TABLE_PROJECT + " WHERE "
			+ COLUMN_PROJECT_NAME + " LIKE ?";
	private static final String SEARCH_PROJECT_STATUS_COUNT = "SELECT COUNT(*) FROM " + TABLE_PROJECT + " WHERE "
			+ COLUMN_PROJECT_STATUS + " LIKE ?";
	private static final String SEARCH_PROJECT_CATEGORY_COUNT = "SELECT COUNT(*) FROM " + TABLE_PROJECT + " WHERE "
			+ COLUMN_PROJECT_CATEGORY + " LIKE ?";
	private static final String SEARCH_PROJECT_NAME_CATEGORY_COUNT = "SELECT COUNT(*) FROM " + TABLE_PROJECT + " WHERE "
			+ COLUMN_PROJECT_NAME + " LIKE ? AND " + COLUMN_PROJECT_CATEGORY + " LIKE ?";
	private static final String SEARCH_PROJECT_NAME_STATUS_COUNT = "SELECT COUNT(*) FROM " + TABLE_PROJECT + " WHERE "
			+ COLUMN_PROJECT_NAME + " LIKE ? AND " + COLUMN_PROJECT_STATUS + " LIKE ?";
	private static final String SEARCH_PROJECT_STATUS_CATEGORY_COUNT = "SELECT COUNT(*) FROM " + TABLE_PROJECT
			+ " WHERE " + COLUMN_PROJECT_STATUS + " LIKE ? AND " + COLUMN_PROJECT_CATEGORY + " LIKE ?";;

	private static final String CHECK_PROJECT_CUSTOMER_PROJECT = "SELECT * FROM " + TABLE_PROJECT_CUSTOMER + " WHERE "
			+ COLUMN_PROJECT_CUSTOMER_PROJECT_ID + " = ?";
	private static final String CHECK_EMPLOYEE_PROJECT_PROJECT = "SELECT * FROM " + TABLE_EMPLOYEE_PROJECT + " WHERE "
			+ COLUMN_EMPLOYEE_PROJECT_PROJECT_ID + " = ?";

	public Connection conn;
	private PreparedStatement queryProject;
	private PreparedStatement queryProjectCount;
	private PreparedStatement insertProjectName;
	private PreparedStatement checkProject;
	private PreparedStatement insertprojectIDName;
	private PreparedStatement updateProject;
	private PreparedStatement deleteProject;
	private PreparedStatement searchProjectID;
	private PreparedStatement searchProjectName;
	private PreparedStatement searchProjectStatus;
	private PreparedStatement searchProjectCategory;
	private PreparedStatement searchProjectNameStatus;
	private PreparedStatement searchProjectNameCategory;
	private PreparedStatement searchProjectStatusCategory;
	private PreparedStatement searchProjectNameCount;
	private PreparedStatement searchProjectStatusCount;
	private PreparedStatement searchProjectCategoryCount;
	private PreparedStatement searchProjectNameStatusCount;
	private PreparedStatement searchProjectNameCategoryCount;
	private PreparedStatement searchProjectStatusCategoryCount;
	private PreparedStatement checkProjectCustomer_Project;
	private PreparedStatement checkEmployeeProject_Project;

	public boolean open() {
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			queryProject = conn.prepareStatement(QUERY_PROJECT_INFO);
			queryProjectCount = conn.prepareStatement(QUERY_PROJECT_COUNT);
			insertProjectName = conn.prepareStatement(INSERT_PROJECT_NAME, Statement.RETURN_GENERATED_KEYS);
			checkProject = conn.prepareStatement(CHECK_PROJECT);
			insertprojectIDName = conn.prepareStatement(INSERT_PROJECT_ID_NAME);
			updateProject = conn.prepareStatement(UPDATE_PROJECT);
			deleteProject = conn.prepareStatement(DELETE_PROJECT, Statement.RETURN_GENERATED_KEYS);
			searchProjectID = conn.prepareStatement(SEARCH_PROJECT_ID);
			searchProjectName = conn.prepareStatement(SEARCH_PROJECT_NAME);
			searchProjectStatus = conn.prepareStatement(SEARCH_PROJECT_STATUS);
			searchProjectCategory = conn.prepareStatement(SEARCH_PROJECT_CATEGORY);
			searchProjectNameStatus = conn.prepareStatement(SEARCH_PROJECT_NAME_STATUS);
			searchProjectNameCategory = conn.prepareStatement(SEARCH_PROJECT_NAME_CATEGORY);
			searchProjectStatusCategory = conn.prepareStatement(SEARCH_PROJECT_STATUS_CATEGORY);
			searchProjectNameCount = conn.prepareStatement(SEARCH_PROJECT_NAME_COUNT);
			searchProjectStatusCount = conn.prepareStatement(SEARCH_PROJECT_STATUS_COUNT);
			searchProjectCategoryCount = conn.prepareStatement(SEARCH_PROJECT_CATEGORY_COUNT);
			searchProjectNameStatusCount = conn.prepareStatement(SEARCH_PROJECT_NAME_STATUS_COUNT);
			searchProjectNameCategoryCount = conn.prepareStatement(SEARCH_PROJECT_NAME_CATEGORY_COUNT);
			searchProjectStatusCategoryCount = conn.prepareStatement(SEARCH_PROJECT_STATUS_CATEGORY_COUNT);
			checkProjectCustomer_Project = conn.prepareStatement(CHECK_PROJECT_CUSTOMER_PROJECT);
			checkEmployeeProject_Project = conn.prepareStatement(CHECK_EMPLOYEE_PROJECT_PROJECT);

			return true;
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println("Could not connect to DB - " + e.getMessage());
			return false;
		}
	}

	public void close() {
		try {
			if (queryProject != null) {
				queryProject.close();
			}
			if (queryProjectCount != null) {
				queryProjectCount.close();
			}
			if (insertProjectName != null) {
				insertProjectName.close();
			}
			if (checkProject != null) {
				checkProject.close();
			}
			if (insertprojectIDName != null) {
				insertprojectIDName.close();
			}
			if (updateProject != null) {
				updateProject.close();
			}
			if (deleteProject != null) {
				deleteProject.close();
			}
			if (searchProjectID != null) {
				searchProjectID.close();
			}
			if (searchProjectName != null) {
				searchProjectName.close();
			}
			if (searchProjectStatus != null) {
				searchProjectStatus.close();
			}
			if (searchProjectCategory != null) {
				searchProjectCategory.close();
			}
			if (searchProjectNameStatus != null) {
				searchProjectNameStatus.close();
			}
			if (searchProjectNameCategory != null) {
				searchProjectNameCategory.close();
			}
			if (searchProjectStatusCategory != null) {
				searchProjectStatusCategory.close();
			}
			if (searchProjectNameCount != null) {
				searchProjectNameCount.close();
			}
			if (searchProjectStatusCount != null) {
				searchProjectStatusCount.close();
			}
			if (searchProjectCategoryCount != null) {
				searchProjectCategoryCount.close();
			}
			if (searchProjectNameStatusCount != null) {
				searchProjectNameStatusCount.close();
			}
			if (searchProjectNameCategoryCount != null) {
				searchProjectNameCategoryCount.close();
			}
			if (searchProjectStatusCategoryCount != null) {
				searchProjectStatusCategoryCount.close();
			}
			if (checkProjectCustomer_Project != null) {
				checkProjectCustomer_Project.close();
			}
			if (checkEmployeeProject_Project != null) {
				checkEmployeeProject_Project.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("Couldnot close the DB -" + e.getMessage());
		}
	}

	public List<Project> listAllProject() {
		try {
			ResultSet results = queryProject.executeQuery();
			List<Project> projectList = new ArrayList<>();
			while (results.next()) {
				projectList.add(new Project(results.getInt(1), results.getString(2), results.getString(3),
						results.getString(4)));
			}
			return projectList;
		} catch (SQLException e) {
			System.out.println("Query failed - Can not load project information " + e.getMessage());
			return null;
		}
	}

	public int insertProjectName(String projectName, String projectStatus, String projectCategory) throws SQLException {
		insertProjectName.setString(1, projectName);
		insertProjectName.setString(2, projectStatus);
		insertProjectName.setString(3, projectCategory);
		int affectedRows = insertProjectName.executeUpdate();
		if (affectedRows != 1) {
			DataSource.getInstance()
					.insertLog(new LogOperation(DataSource.OPERATION_INSERT, TABLE_PROJECT,
							String.valueOf(new Timestamp(System.currentTimeMillis())),
							DataSource.OPERATION_RESULT_FAILED, "Could not insert project - Operation Failed"));
			throw new SQLException("Could not insert project");
		}
		ResultSet generatedKey = insertProjectName.getGeneratedKeys();
		if (generatedKey.next()) {
			DataSource.getInstance()
					.insertLog(new LogOperation(DataSource.OPERATION_INSERT, TABLE_PROJECT,
							String.valueOf(new Timestamp(System.currentTimeMillis())),
							DataSource.OPERATION_RESULT_SUCCESS, "DONE"));
			return generatedKey.getInt(1);
		} else {
			DataSource.getInstance()
					.insertLog(new LogOperation(DataSource.OPERATION_INSERT, TABLE_PROJECT,
							String.valueOf(new Timestamp(System.currentTimeMillis())),
							DataSource.OPERATION_RESULT_FAILED, "Could not get _id for project - Operation Failed"));
			throw new SQLException("Could not get _id for project");
		}
	}

	public boolean insertprojectIDName(int projectID, String projectName, String projectStatus, String projectCategory)
			throws SQLException {
		checkProject.setInt(1, projectID);
		ResultSet results = checkProject.executeQuery();
		if (results.next()) {
			DataSource.getInstance()
					.insertLog(new LogOperation(DataSource.OPERATION_INSERT, TABLE_PROJECT,
							String.valueOf(new Timestamp(System.currentTimeMillis())),
							DataSource.OPERATION_RESULT_FAILED, "Project ID already exist in DB"));
			return false;
		} else {
			insertprojectIDName.setInt(1, projectID);
			insertprojectIDName.setString(2, projectName);
			insertprojectIDName.setString(3, projectStatus);
			insertprojectIDName.setString(4, projectCategory);
			System.out.println(insertprojectIDName);
			int affectedRows = insertprojectIDName.executeUpdate();
			if (affectedRows != 1) {
				DataSource.getInstance()
						.insertLog(new LogOperation(DataSource.OPERATION_INSERT, TABLE_PROJECT,
								String.valueOf(new Timestamp(System.currentTimeMillis())),
								DataSource.OPERATION_RESULT_FAILED, "Could not insert project - Operation Failed"));
				throw new SQLException("Could not insert project");
			} else {
				DataSource.getInstance()
						.insertLog(new LogOperation(DataSource.OPERATION_INSERT, TABLE_PROJECT,
								String.valueOf(new Timestamp(System.currentTimeMillis())),
								DataSource.OPERATION_RESULT_SUCCESS, "DONE"));
				return true;
			}
		}
	}

	public boolean updateProject(int projectID, String projectName, String projectStatus, String projectCategory) {
		try {
			updateProject.setString(1, projectName);
			updateProject.setString(2, projectStatus);
			updateProject.setString(3, projectCategory);
			updateProject.setInt(4, projectID);
			int numRowsEffected = updateProject.executeUpdate();
			DataSource.getInstance()
					.insertLog(new LogOperation(DataSource.OPERATION_UPDATE, TABLE_PROJECT,
							String.valueOf(new Timestamp(System.currentTimeMillis())),
							DataSource.OPERATION_RESULT_SUCCESS, "DONE"));
			return numRowsEffected == 1;
		} catch (SQLException e) {
			DataSource.getInstance()
					.insertLog(new LogOperation(DataSource.OPERATION_UPDATE, TABLE_PROJECT,
							String.valueOf(new Timestamp(System.currentTimeMillis())),
							DataSource.OPERATION_RESULT_FAILED, "Update Fails"));
			System.out.println("Update Fails - " + e.getMessage());
			return false;
		}
	}

	public int deleteProject(int projectID) {
		try {
			checkProjectCustomer_Project.setInt(1, projectID);
			ResultSet result1 = checkProjectCustomer_Project.executeQuery();
			checkEmployeeProject_Project.setInt(1, projectID);
			ResultSet result2 = checkEmployeeProject_Project.executeQuery();
			if (result1.next()) {
				DataSource.getInstance().insertLog(new LogOperation(DataSource.OPERATION_DELETE, TABLE_PROJECT,
						String.valueOf(new Timestamp(System.currentTimeMillis())), DataSource.OPERATION_RESULT_FAILED,
						"Project ID = " + projectID + " has a reference on another customer."));
				return 2;
			} else if (result2.next()) {
				DataSource.getInstance().insertLog(new LogOperation(DataSource.OPERATION_DELETE, TABLE_PROJECT,
						String.valueOf(new Timestamp(System.currentTimeMillis())), DataSource.OPERATION_RESULT_FAILED,
						"Project ID = " + projectID + " has a reference on another employee."));
				return 3;
			} else {
				deleteProject.setInt(1, projectID);
				int affectedRows = deleteProject.executeUpdate();
				if (affectedRows != 1) {
					DataSource.getInstance()
							.insertLog(new LogOperation(DataSource.OPERATION_DELETE, TABLE_PROJECT,
									String.valueOf(new Timestamp(System.currentTimeMillis())),
									DataSource.OPERATION_RESULT_FAILED, "Delete Fails"));
					return 0;
				} else {
					DataSource.getInstance()
							.insertLog(new LogOperation(DataSource.OPERATION_DELETE, TABLE_PROJECT,
									String.valueOf(new Timestamp(System.currentTimeMillis())),
									DataSource.OPERATION_RESULT_SUCCESS, "DONE"));
					return 1;
				}
			}
		} catch (SQLException e) {
			DataSource.getInstance()
					.insertLog(new LogOperation(DataSource.OPERATION_DELETE, TABLE_PROJECT,
							String.valueOf(new Timestamp(System.currentTimeMillis())),
							DataSource.OPERATION_RESULT_FAILED, "Could not delete project"));
			System.err.println("Could not delete project - " + e.getMessage());
			return 0;
		}
	}

	public String[] searchProjectID(int projectID) {
		try {
			searchProjectID.setInt(1, projectID);
			ResultSet results = searchProjectID.executeQuery();
			String[] project = new String[3];
			while (results.next()) {
				project[0] = results.getString(2);
				project[1] = results.getString(3);
				project[2] = results.getString(4);
			}
			return project;
		} catch (SQLException e) {
			System.out.println("Query failed - Can not search project information " + e.getMessage());
			return null;
		}
	}

	public List<Project> searchProjectName(String projectName) {
		try {
			searchProjectName.setString(1, "%" + projectName + "%");
			ResultSet results = searchProjectName.executeQuery();
			List<Project> projectList = new ArrayList<>();
			while (results.next()) {
				projectList.add(new Project(results.getInt(1), results.getString(2), results.getString(3),
						results.getString(4)));
			}
			return projectList;
		} catch (SQLException e) {
			System.out.println("Query failed - Can not search project information " + e.getMessage());
			return null;
		}
	}

	public List<Project> searchProjectStatus(String projectStatus) {
		try {
			searchProjectStatus.setString(1, "%" + projectStatus + "%");
			ResultSet results = searchProjectStatus.executeQuery();
			List<Project> projectList = new ArrayList<>();
			while (results.next()) {
				projectList.add(new Project(results.getInt(1), results.getString(2), results.getString(3),
						results.getString(4)));
			}
			return projectList;
		} catch (SQLException e) {
			System.out.println("Query failed - Can not search project information " + e.getMessage());
			return null;
		}
	}

	public List<Project> searchProjectCategory(String projectCategory) {
		try {
			searchProjectCategory.setString(1, "%" + projectCategory + "%");
			ResultSet results = searchProjectCategory.executeQuery();
			List<Project> projectList = new ArrayList<>();
			while (results.next()) {
				projectList.add(new Project(results.getInt(1), results.getString(2), results.getString(3),
						results.getString(4)));
			}
			return projectList;
		} catch (SQLException e) {
			System.out.println("Query failed - Can not search project information " + e.getMessage());
			return null;
		}
	}

	public List<Project> searchProjectNameStatus(String projectName, String projectStatus) {
		try {
			searchProjectNameStatus.setString(1, "%" + projectName + "%");
			searchProjectNameStatus.setString(2, "%" + projectStatus + "%");
			ResultSet results = searchProjectNameStatus.executeQuery();
			List<Project> projectList = new ArrayList<>();
			while (results.next()) {
				projectList.add(new Project(results.getInt(1), results.getString(2), results.getString(3),
						results.getString(4)));
			}
			return projectList;
		} catch (SQLException e) {
			System.out.println("Query failed - Can not search project information " + e.getMessage());
			return null;
		}
	}

	public List<Project> searchProjectNameCategory(String projectName, String projectCategory) {
		try {
			searchProjectNameCategory.setString(1, "%" + projectName + "%");
			searchProjectNameCategory.setString(2, "%" + projectCategory + "%");
			ResultSet results = searchProjectNameCategory.executeQuery();
			List<Project> projectList = new ArrayList<>();
			while (results.next()) {
				projectList.add(new Project(results.getInt(1), results.getString(2), results.getString(3),
						results.getString(4)));
			}
			return projectList;
		} catch (SQLException e) {
			System.out.println("Query failed - Can not search project information " + e.getMessage());
			return null;
		}
	}

	public List<Project> searchProjectStatusCategory(String projectStatus, String projectCategory) {
		try {
			searchProjectStatusCategory.setString(1, "%" + projectStatus + "%");
			searchProjectStatusCategory.setString(2, "%" + projectCategory + "%");
			ResultSet results = searchProjectStatusCategory.executeQuery();
			List<Project> projectList = new ArrayList<>();
			while (results.next()) {
				projectList.add(new Project(results.getInt(1), results.getString(2), results.getString(3),
						results.getString(4)));
			}
			return projectList;
		} catch (SQLException e) {
			System.out.println("Query failed - Can not search project information " + e.getMessage());
			return null;
		}
	}

	public int searchProjectNameCount(String projectName) {
		try {
			searchProjectNameCount.setString(1, "%" + projectName + "%");
			ResultSet results = searchProjectNameCount.executeQuery();
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

	public int searchProjectStatusCount(String projectStatus) {
		try {
			searchProjectStatusCount.setString(1, "%" + projectStatus + "%");
			ResultSet results = searchProjectStatusCount.executeQuery();
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

	public int searchProjectCategoryCount(String projectCategory) {
		try {
			searchProjectCategoryCount.setString(1, "%" + projectCategory + "%");
			ResultSet results = searchProjectCategoryCount.executeQuery();
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

	public int searchProjectNameStatusCount(String projectName, String projectStatus) {
		try {
			searchProjectNameStatusCount.setString(1, "%" + projectName + "%");
			searchProjectNameStatusCount.setString(2, "%" + projectStatus + "%");
			ResultSet results = searchProjectNameStatusCount.executeQuery();
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

	public int searchProjectNameCategoryCount(String projectName, String projectCategory) {
		try {
			searchProjectNameCategoryCount.setString(1, "%" + projectName + "%");
			searchProjectNameCategoryCount.setString(2, "%" + projectCategory + "%");
			ResultSet results = searchProjectNameCategoryCount.executeQuery();
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

	public int searchProjectStatusCategoryCount(String projectStatus, String projectCategory) {
		try {
			searchProjectStatusCategoryCount.setString(1, "%" + projectStatus + "%");
			searchProjectStatusCategoryCount.setString(2, "%" + projectCategory + "%");
			ResultSet results = searchProjectStatusCategoryCount.executeQuery();
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

	public int queryProjectCount() {
		int count = 0;
		try {
			ResultSet results = queryProjectCount.executeQuery();
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
