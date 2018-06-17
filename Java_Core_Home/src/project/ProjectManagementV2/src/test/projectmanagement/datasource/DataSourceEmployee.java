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
import test.projectmanagement.datamodel.Employee;
import test.projectmanagement.datamodel.LogOperation;

public class DataSourceEmployee {
	private static DataSourceEmployee instance = new DataSourceEmployee();

	private DataSourceEmployee() {
	}

	public static DataSourceEmployee getInstance() {
		return instance;
	}

	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/projectmanagement?useSSL=false";
	static final String USER = "root";
	static final String PASS = "root123456";

	private static final String TABLE_EMPLOYEE = "employee";
	private static final String COLUMN_EMPLOYEE_ID = "id";
	private static final String COLUMN_EMPLOYEE_FIRSTNAME = "firstname";
	private static final String COLUMN_EMPLOYEE_LASTNAME = "lastName";
	private static final String COLUMN_EMPLOYEE_PHONENUMBER = "phoneNumber";
	private static final String COLUMN_EMPLOYEE_EMAIL = "email";
	private static final String COLUMN_EMPLOYEE_AGE = "age";
	private static final String COLUMN_EMPLOYEE_GENDER = "gender";
	private static final String COLUMN_EMPLOYEE_TITLE = "title";
	private static final String COLUMN_EMPLOYEE_DEPPARTMENT_ID = "department_Id";

	private static final String TABLE_DEPARTMENT = "department";
	private static final String COLUMN_DEPARTMENT_ID = "id";
	
	private static final String TABLE_EMPLOYEE_ADDRESS = "employee_address";
	private static final String COLUMN_EMPLOYEE_ADDRESS_EMPLOYEE_ID = "Employee_id";

	private static final String TABLE_EMPLOYEE_PROJECT = "employee_project";
	private static final String COLUMN_EMPLOYEE_PROJECT_EMPLOYEE_ID = "Employee_id";

	private static final String QUERY_EMPLOYEE_INFO = "SELECT e.id id, firstname, lastName, gender, phoneNumber, email, age, title, deptName FROM "
			+ TABLE_EMPLOYEE + " e JOIN " + TABLE_DEPARTMENT + " ON e." + COLUMN_EMPLOYEE_DEPPARTMENT_ID + " = "
			+ TABLE_DEPARTMENT + "." + COLUMN_DEPARTMENT_ID + " ORDER BY " + COLUMN_EMPLOYEE_ID + " ASC";
	private static final String QUERY_EMPLOYEE_COUNT = "SELECT COUNT(*) FROM " + TABLE_EMPLOYEE + " JOIN "
			+ TABLE_DEPARTMENT + " ON " + TABLE_EMPLOYEE + "." + COLUMN_EMPLOYEE_DEPPARTMENT_ID + " = "
			+ TABLE_DEPARTMENT + "." + COLUMN_DEPARTMENT_ID;
	private static final String INSERT_EMPLOYEE = "INSERT INTO " + TABLE_EMPLOYEE + " (" + COLUMN_EMPLOYEE_LASTNAME
			+ ", " + COLUMN_EMPLOYEE_FIRSTNAME + ", " + COLUMN_EMPLOYEE_GENDER + ", " + COLUMN_EMPLOYEE_PHONENUMBER
			+ ", " + COLUMN_EMPLOYEE_EMAIL + ", " + COLUMN_EMPLOYEE_AGE + ", " + COLUMN_EMPLOYEE_TITLE + ", "
			+ COLUMN_EMPLOYEE_DEPPARTMENT_ID + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String INSERT_EMPLOYEE_ID = "INSERT INTO " + TABLE_EMPLOYEE + " (" + COLUMN_EMPLOYEE_ID + ", "
			+ COLUMN_EMPLOYEE_LASTNAME + ", " + COLUMN_EMPLOYEE_FIRSTNAME + ", " + COLUMN_EMPLOYEE_GENDER + ", "
			+ COLUMN_EMPLOYEE_PHONENUMBER + ", " + COLUMN_EMPLOYEE_EMAIL + ", " + COLUMN_EMPLOYEE_AGE + ", "
			+ COLUMN_EMPLOYEE_TITLE + ", " + COLUMN_EMPLOYEE_DEPPARTMENT_ID + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String CHECK_EMPLOYEE = "SELECT * FROM " + TABLE_EMPLOYEE + " WHERE " + COLUMN_EMPLOYEE_ID
			+ " = ?";
	private static final String UPDATE_EMPLOYEE = "UPDATE " + TABLE_EMPLOYEE + " SET " + COLUMN_EMPLOYEE_LASTNAME
			+ " = ? , " + COLUMN_EMPLOYEE_FIRSTNAME + " = ? , " + COLUMN_EMPLOYEE_GENDER + " = ? , "
			+ COLUMN_EMPLOYEE_PHONENUMBER + " = ? , " + COLUMN_EMPLOYEE_EMAIL + " = ? , " + COLUMN_EMPLOYEE_AGE
			+ " = ? , " + COLUMN_EMPLOYEE_TITLE + " = ? , " + COLUMN_EMPLOYEE_DEPPARTMENT_ID + " = ? WHERE "
			+ COLUMN_EMPLOYEE_ID + " = ?";
	private static final String DELETE_EMPLOYEE = "DELETE FROM " + TABLE_EMPLOYEE + " WHERE " + COLUMN_EMPLOYEE_ID
			+ " = ?";
	private static final String SEARCH_EMPLOYEE_ID = "SELECT * FROM " + TABLE_EMPLOYEE + " WHERE " + COLUMN_EMPLOYEE_ID
			+ " = ?";
	private static final String SEARCH_EMPLOYEE_LASTNAME = "SELECT * FROM " + TABLE_EMPLOYEE + " WHERE "
			+ COLUMN_EMPLOYEE_LASTNAME + " LIKE ?";
	private static final String SEARCH_EMPLOYEE_FIRSTNAME = "SELECT * FROM " + TABLE_EMPLOYEE + " WHERE "
			+ COLUMN_EMPLOYEE_FIRSTNAME + " LIKE ?";
	private static final String SEARCH_EMPLOYEE_GENDER = "SELECT * FROM " + TABLE_EMPLOYEE + " WHERE "
			+ COLUMN_EMPLOYEE_GENDER + " = ?";
	private static final String SEARCH_EMPLOYEE_PHONENUMBER = "SELECT * FROM " + TABLE_EMPLOYEE + " WHERE "
			+ COLUMN_EMPLOYEE_PHONENUMBER + " LIKE ?";
	private static final String SEARCH_EMPLOYEE_EMAIL = "SELECT * FROM " + TABLE_EMPLOYEE + " WHERE "
			+ COLUMN_EMPLOYEE_EMAIL + " LIKE ?";
	private static final String SEARCH_EMPLOYEE_AGE = "SELECT * FROM " + TABLE_EMPLOYEE + " WHERE "
			+ COLUMN_EMPLOYEE_AGE + " = ?";
	private static final String SEARCH_EMPLOYEE_TITLE = "SELECT * FROM " + TABLE_EMPLOYEE + " WHERE "
			+ COLUMN_EMPLOYEE_TITLE + " LIKE ?";
	private static final String SEARCH_EMPLOYEE_DEPARTMENTID = "SELECT * FROM " + TABLE_EMPLOYEE + " WHERE "
			+ COLUMN_EMPLOYEE_DEPPARTMENT_ID + " = ?";
	private static final String SEARCH_EMPLOYEE_LASTNAME_GENDER = "SELECT * FROM " + TABLE_EMPLOYEE + " WHERE "
			+ COLUMN_EMPLOYEE_LASTNAME + " LIKE ? AND " + COLUMN_EMPLOYEE_GENDER + " = ?";
	private static final String SEARCH_EMPLOYEE_LASTNAME_AGE = "SELECT * FROM " + TABLE_EMPLOYEE + " WHERE "
			+ COLUMN_EMPLOYEE_LASTNAME + " LIKE ? AND " + COLUMN_EMPLOYEE_AGE + " = ?";
	private static final String SEARCH_EMPLOYEE_LASTNAME_TITLE = "SELECT * FROM " + TABLE_EMPLOYEE + " WHERE "
			+ COLUMN_EMPLOYEE_LASTNAME + " LIKE ? AND " + COLUMN_EMPLOYEE_TITLE + " LIKE ?";
	private static final String SEARCH_EMPLOYEE_LASTNAME_DEPARTMENTID = "SELECT * FROM " + TABLE_EMPLOYEE + " WHERE "
			+ COLUMN_EMPLOYEE_LASTNAME + " LIKE ? AND " + COLUMN_EMPLOYEE_DEPPARTMENT_ID + " = ?";
	private static final String SEARCH_EMPLOYEE_GENDER_AGE = "SELECT * FROM " + TABLE_EMPLOYEE + " WHERE "
			+ COLUMN_EMPLOYEE_GENDER + " = ? AND " + COLUMN_EMPLOYEE_AGE + " = ?";
	private static final String SEARCH_EMPLOYEE_GENDER_TITLE = "SELECT * FROM " + TABLE_EMPLOYEE + " WHERE "
			+ COLUMN_EMPLOYEE_GENDER + " = ? AND " + COLUMN_EMPLOYEE_TITLE + " LIKE ?";
	private static final String SEARCH_EMPLOYEE_GENDER_DEPARTMENTID = "SELECT * FROM " + TABLE_EMPLOYEE + " WHERE "
			+ COLUMN_EMPLOYEE_GENDER + " = ? AND " + COLUMN_EMPLOYEE_DEPPARTMENT_ID + " = ?";
	private static final String SEARCH_EMPLOYEE_AGE_TITLE = "SELECT * FROM " + TABLE_EMPLOYEE + " WHERE "
			+ COLUMN_EMPLOYEE_AGE + " = ? AND " + COLUMN_EMPLOYEE_TITLE + " LIKE ?";
	private static final String SEARCH_EMPLOYEE_AGE_DEPARTMENTID = "SELECT * FROM " + TABLE_EMPLOYEE + " WHERE "
			+ COLUMN_EMPLOYEE_AGE + " = ? AND " + COLUMN_EMPLOYEE_DEPPARTMENT_ID + " = ?";
	private static final String SEARCH_EMPLOYEE_ID_COUNT = "SELECT COUNT(*) FROM " + TABLE_EMPLOYEE + " WHERE "
			+ COLUMN_EMPLOYEE_ID + " = ?";
	private static final String SEARCH_EMPLOYEE_LASTNAME_COUNT = "SELECT COUNT(*) FROM " + TABLE_EMPLOYEE + " WHERE "
			+ COLUMN_EMPLOYEE_LASTNAME + " LIKE ?";
	private static final String SEARCH_EMPLOYEE_FIRSTNAME_COUNT = "SELECT COUNT(*) FROM " + TABLE_EMPLOYEE + " WHERE "
			+ COLUMN_EMPLOYEE_FIRSTNAME + " LIKE ?";
	private static final String SEARCH_EMPLOYEE_GENDER_COUNT = "SELECT COUNT(*) FROM " + TABLE_EMPLOYEE + " WHERE "
			+ COLUMN_EMPLOYEE_GENDER + " = ?";
	private static final String SEARCH_EMPLOYEE_PHONENUMBER_COUNT = "SELECT COUNT(*) FROM " + TABLE_EMPLOYEE + " WHERE "
			+ COLUMN_EMPLOYEE_PHONENUMBER + " LIKE ?";
	private static final String SEARCH_EMPLOYEE_EMAIL_COUNT = "SELECT COUNT(*) FROM " + TABLE_EMPLOYEE + " WHERE "
			+ COLUMN_EMPLOYEE_EMAIL + " LIKE ?";
	private static final String SEARCH_EMPLOYEE_AGE_COUNT = "SELECT COUNT(*) FROM " + TABLE_EMPLOYEE + " WHERE "
			+ COLUMN_EMPLOYEE_AGE + " = ?";
	private static final String SEARCH_EMPLOYEE_TITLE_COUNT = "SELECT COUNT(*) FROM " + TABLE_EMPLOYEE + " WHERE "
			+ COLUMN_EMPLOYEE_TITLE + " LIKE ?";
	private static final String SEARCH_EMPLOYEE_DEPARTMENTID_COUNT = "SELECT COUNT(*) FROM " + TABLE_EMPLOYEE
			+ " WHERE " + COLUMN_EMPLOYEE_DEPPARTMENT_ID + " = ?";
	private static final String SEARCH_EMPLOYEE_LASTNAME_GENDER_COUNT = "SELECT COUNT(*) FROM " + TABLE_EMPLOYEE
			+ " WHERE " + COLUMN_EMPLOYEE_LASTNAME + " LIKE ? AND " + COLUMN_EMPLOYEE_GENDER + " = ?";
	private static final String SEARCH_EMPLOYEE_LASTNAME_AGE_COUNT = "SELECT COUNT(*) FROM " + TABLE_EMPLOYEE
			+ " WHERE " + COLUMN_EMPLOYEE_LASTNAME + " LIKE ? AND " + COLUMN_EMPLOYEE_AGE + " = ?";
	private static final String SEARCH_EMPLOYEE_LASTNAME_TITLE_COUNT = "SELECT COUNT(*) FROM " + TABLE_EMPLOYEE
			+ " WHERE " + COLUMN_EMPLOYEE_LASTNAME + " LIKE ? AND " + COLUMN_EMPLOYEE_TITLE + " LIKE ?";
	private static final String SEARCH_EMPLOYEE_LASTNAME_DEPARTMENTID_COUNT = "SELECT COUNT(*) FROM " + TABLE_EMPLOYEE
			+ " WHERE " + COLUMN_EMPLOYEE_LASTNAME + " LIKE ? AND " + COLUMN_EMPLOYEE_DEPPARTMENT_ID + " = ?";
	private static final String SEARCH_EMPLOYEE_GENDER_AGE_COUNT = "SELECT COUNT(*) FROM " + TABLE_EMPLOYEE + " WHERE "
			+ COLUMN_EMPLOYEE_GENDER + " = ? AND " + COLUMN_EMPLOYEE_AGE + " = ?";
	private static final String SEARCH_EMPLOYEE_GENDER_TITLE_COUNT = "SELECT COUNT(*) FROM " + TABLE_EMPLOYEE
			+ " WHERE " + COLUMN_EMPLOYEE_GENDER + " = ? AND " + COLUMN_EMPLOYEE_TITLE + " LIKE ?";
	private static final String SEARCH_EMPLOYEE_GENDER_DEPARTMENTID_COUNT = "SELECT COUNT(*) FROM " + TABLE_EMPLOYEE
			+ " WHERE " + COLUMN_EMPLOYEE_GENDER + " = ? AND " + COLUMN_EMPLOYEE_DEPPARTMENT_ID + " = ?";
	private static final String SEARCH_EMPLOYEE_AGE_TITLE_COUNT = "SELECT COUNT(*) FROM " + TABLE_EMPLOYEE + " WHERE "
			+ COLUMN_EMPLOYEE_AGE + " = ? AND " + COLUMN_EMPLOYEE_TITLE + " LIKE ?";
	private static final String SEARCH_EMPLOYEE_AGE_DEPARTMENTID_COUNT = "SELECT COUNT(*) FROM " + TABLE_EMPLOYEE
			+ " WHERE " + COLUMN_EMPLOYEE_AGE + " = ? AND " + COLUMN_EMPLOYEE_DEPPARTMENT_ID + " = ?";

	private static final String CHECK_EMPLOYEE_ADDRESS_EMPLOYEE = "SELECT * FROM " + TABLE_EMPLOYEE_ADDRESS + " WHERE "
			+ COLUMN_EMPLOYEE_ADDRESS_EMPLOYEE_ID + " = ?";
	private static final String CHECK_EMPLOYEE_PROJECT_EMPLOYEE = "SELECT * FROM " + TABLE_EMPLOYEE_PROJECT + " WHERE "
			+ COLUMN_EMPLOYEE_PROJECT_EMPLOYEE_ID + " = ?";

	public Connection conn;
	private PreparedStatement queryEmployee;
	private PreparedStatement queryEmployeeCount;
	private PreparedStatement insertEmployee;
	private PreparedStatement insertEmployeeID;
	private PreparedStatement checkEmployee;
	private PreparedStatement updateEmployee;
	private PreparedStatement deleteEmployee;
	private PreparedStatement searchEmployeeID;
	private PreparedStatement searchEmployeeLastName;
	private PreparedStatement searchEmployeeFirstName;
	private PreparedStatement searchEmployeeGender;
	private PreparedStatement searchEmployeePhoneNumber;
	private PreparedStatement searchEmployeeEmail;
	private PreparedStatement searchEmployeeAge;
	private PreparedStatement searchEmployeeTitle;
	private PreparedStatement searchEmployeeDepartmentID;
	private PreparedStatement searchEmployeeLastNameGender;
	private PreparedStatement searchEmployeeLastNameAge;
	private PreparedStatement searchEmployeeLastNameTitle;
	private PreparedStatement searchEmployeeLastNameDepartmentID;
	private PreparedStatement searchEmployeeGenderAge;
	private PreparedStatement searchEmployeeGenderTitle;
	private PreparedStatement searchEmployeeGenderDepartmentID;
	private PreparedStatement searchEmployeeAgeTitle;
	private PreparedStatement searchEmployeeAgeDepartmentID;
	private PreparedStatement searchEmployeeIDCount;
	private PreparedStatement searchEmployeeLastNameCount;
	private PreparedStatement searchEmployeeFirstNameCount;
	private PreparedStatement searchEmployeeGenderCount;
	private PreparedStatement searchEmployeePhoneNumberCount;
	private PreparedStatement searchEmployeeEmailCount;
	private PreparedStatement searchEmployeeAgeCount;
	private PreparedStatement searchEmployeeTitleCount;
	private PreparedStatement searchEmployeeDepartmentIDCount;
	private PreparedStatement searchEmployeeLastNameGenderCount;
	private PreparedStatement searchEmployeeLastNameAgeCount;
	private PreparedStatement searchEmployeeLastNameTitleCount;
	private PreparedStatement searchEmployeeLastNameDepartmentIDCount;
	private PreparedStatement searchEmployeeGenderAgeCount;
	private PreparedStatement searchEmployeeGenderTitleCount;
	private PreparedStatement searchEmployeeGenderDepartmentIDCount;
	private PreparedStatement searchEmployeeAgeTitleCount;
	private PreparedStatement searchEmployeeAgeDepartmentIDCount;
	private PreparedStatement checkEmployeeAddress_Employee;
	private PreparedStatement checkEmployeeProject_Employee;

	public boolean open() {
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			queryEmployee = conn.prepareStatement(QUERY_EMPLOYEE_INFO);
			queryEmployeeCount = conn.prepareStatement(QUERY_EMPLOYEE_COUNT);
			insertEmployee = conn.prepareStatement(INSERT_EMPLOYEE, Statement.RETURN_GENERATED_KEYS);
			insertEmployeeID = conn.prepareStatement(INSERT_EMPLOYEE_ID);
			checkEmployee = conn.prepareStatement(CHECK_EMPLOYEE);
			updateEmployee = conn.prepareStatement(UPDATE_EMPLOYEE);
			deleteEmployee = conn.prepareStatement(DELETE_EMPLOYEE);
			searchEmployeeID = conn.prepareStatement(SEARCH_EMPLOYEE_ID);
			searchEmployeeLastName = conn.prepareStatement(SEARCH_EMPLOYEE_LASTNAME);
			searchEmployeeFirstName = conn.prepareStatement(SEARCH_EMPLOYEE_FIRSTNAME);
			searchEmployeeGender = conn.prepareStatement(SEARCH_EMPLOYEE_GENDER);
			searchEmployeePhoneNumber = conn.prepareStatement(SEARCH_EMPLOYEE_PHONENUMBER);
			searchEmployeeEmail = conn.prepareStatement(SEARCH_EMPLOYEE_EMAIL);
			searchEmployeeAge = conn.prepareStatement(SEARCH_EMPLOYEE_AGE);
			searchEmployeeTitle = conn.prepareStatement(SEARCH_EMPLOYEE_TITLE);
			searchEmployeeDepartmentID = conn.prepareStatement(SEARCH_EMPLOYEE_DEPARTMENTID);
			searchEmployeeLastNameGender = conn.prepareStatement(SEARCH_EMPLOYEE_LASTNAME_GENDER);
			searchEmployeeLastNameAge = conn.prepareStatement(SEARCH_EMPLOYEE_LASTNAME_AGE);
			searchEmployeeLastNameTitle = conn.prepareStatement(SEARCH_EMPLOYEE_LASTNAME_TITLE);
			searchEmployeeLastNameDepartmentID = conn.prepareStatement(SEARCH_EMPLOYEE_LASTNAME_DEPARTMENTID);
			searchEmployeeGenderAge = conn.prepareStatement(SEARCH_EMPLOYEE_GENDER_AGE);
			searchEmployeeGenderTitle = conn.prepareStatement(SEARCH_EMPLOYEE_GENDER_TITLE);
			searchEmployeeGenderDepartmentID = conn.prepareStatement(SEARCH_EMPLOYEE_GENDER_DEPARTMENTID);
			searchEmployeeAgeTitle = conn.prepareStatement(SEARCH_EMPLOYEE_AGE_TITLE);
			searchEmployeeAgeDepartmentID = conn.prepareStatement(SEARCH_EMPLOYEE_AGE_DEPARTMENTID);
			searchEmployeeIDCount = conn.prepareStatement(SEARCH_EMPLOYEE_ID_COUNT);
			searchEmployeeLastNameCount = conn.prepareStatement(SEARCH_EMPLOYEE_LASTNAME_COUNT);
			searchEmployeeFirstNameCount = conn.prepareStatement(SEARCH_EMPLOYEE_FIRSTNAME_COUNT);
			searchEmployeeGenderCount = conn.prepareStatement(SEARCH_EMPLOYEE_GENDER_COUNT);
			searchEmployeePhoneNumberCount = conn.prepareStatement(SEARCH_EMPLOYEE_PHONENUMBER_COUNT);
			searchEmployeeEmailCount = conn.prepareStatement(SEARCH_EMPLOYEE_EMAIL_COUNT);
			searchEmployeeAgeCount = conn.prepareStatement(SEARCH_EMPLOYEE_AGE_COUNT);
			searchEmployeeTitleCount = conn.prepareStatement(SEARCH_EMPLOYEE_TITLE_COUNT);
			searchEmployeeDepartmentIDCount = conn.prepareStatement(SEARCH_EMPLOYEE_DEPARTMENTID_COUNT);
			searchEmployeeLastNameGenderCount = conn.prepareStatement(SEARCH_EMPLOYEE_LASTNAME_GENDER_COUNT);
			searchEmployeeLastNameAgeCount = conn.prepareStatement(SEARCH_EMPLOYEE_LASTNAME_AGE_COUNT);
			searchEmployeeLastNameTitleCount = conn.prepareStatement(SEARCH_EMPLOYEE_LASTNAME_TITLE_COUNT);
			searchEmployeeLastNameDepartmentIDCount = conn
					.prepareStatement(SEARCH_EMPLOYEE_LASTNAME_DEPARTMENTID_COUNT);
			searchEmployeeGenderAgeCount = conn.prepareStatement(SEARCH_EMPLOYEE_GENDER_AGE_COUNT);
			searchEmployeeGenderTitleCount = conn.prepareStatement(SEARCH_EMPLOYEE_GENDER_TITLE_COUNT);
			searchEmployeeGenderDepartmentIDCount = conn.prepareStatement(SEARCH_EMPLOYEE_GENDER_DEPARTMENTID_COUNT);
			searchEmployeeAgeTitleCount = conn.prepareStatement(SEARCH_EMPLOYEE_AGE_TITLE_COUNT);
			searchEmployeeAgeDepartmentIDCount = conn.prepareStatement(SEARCH_EMPLOYEE_AGE_DEPARTMENTID_COUNT);
			checkEmployeeAddress_Employee = conn.prepareStatement(CHECK_EMPLOYEE_ADDRESS_EMPLOYEE);
			checkEmployeeProject_Employee = conn.prepareStatement(CHECK_EMPLOYEE_PROJECT_EMPLOYEE);

			return true;
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println("Could not connect to DB - " + e.getMessage());
			return false;
		}
	}

	public void close() {
		try {
			if (queryEmployee != null) {
				queryEmployee.close();
			}
			if (queryEmployeeCount != null) {
				queryEmployeeCount.close();
			}
			if (insertEmployee != null) {
				insertEmployee.close();
			}
			if (insertEmployeeID != null) {
				insertEmployeeID.close();
			}
			if (checkEmployee != null) {
				checkEmployee.close();
			}
			if (updateEmployee != null) {
				updateEmployee.close();
			}
			if (deleteEmployee != null) {
				deleteEmployee.close();
			}
			if (searchEmployeeID != null) {
				searchEmployeeID.close();
			}
			if (searchEmployeeLastName != null) {
				searchEmployeeLastName.close();
			}
			if (searchEmployeeFirstName != null) {
				searchEmployeeFirstName.close();
			}
			if (searchEmployeeGender != null) {
				searchEmployeeGender.close();
			}
			if (searchEmployeePhoneNumber != null) {
				searchEmployeePhoneNumber.close();
			}
			if (searchEmployeeEmail != null) {
				searchEmployeeEmail.close();
			}
			if (searchEmployeeAge != null) {
				searchEmployeeAge.close();
			}
			if (searchEmployeeTitle != null) {
				searchEmployeeTitle.close();
			}
			if (searchEmployeeDepartmentID != null) {
				searchEmployeeDepartmentID.close();
			}
			if (searchEmployeeLastNameGender != null) {
				searchEmployeeLastNameGender.close();
			}
			if (searchEmployeeLastNameAge != null) {
				searchEmployeeLastNameAge.close();
			}
			if (searchEmployeeLastNameTitle != null) {
				searchEmployeeLastNameTitle.close();
			}
			if (searchEmployeeLastNameDepartmentID != null) {
				searchEmployeeLastNameDepartmentID.close();
			}
			if (searchEmployeeGenderAge != null) {
				searchEmployeeGenderAge.close();
			}
			if (searchEmployeeGenderTitle != null) {
				searchEmployeeGenderTitle.close();
			}
			if (searchEmployeeGenderDepartmentID != null) {
				searchEmployeeGenderDepartmentID.close();
			}
			if (searchEmployeeAgeTitle != null) {
				searchEmployeeAgeTitle.close();
			}
			if (searchEmployeeAgeDepartmentID != null) {
				searchEmployeeAgeDepartmentID.close();
			}
			if (searchEmployeeIDCount != null) {
				searchEmployeeIDCount.close();
			}
			if (searchEmployeeLastNameCount != null) {
				searchEmployeeLastNameCount.close();
			}
			if (searchEmployeeFirstNameCount != null) {
				searchEmployeeFirstNameCount.close();
			}
			if (searchEmployeeGenderCount != null) {
				searchEmployeeGenderCount.close();
			}
			if (searchEmployeePhoneNumberCount != null) {
				searchEmployeePhoneNumberCount.close();
			}
			if (searchEmployeeEmailCount != null) {
				searchEmployeeEmailCount.close();
			}
			if (searchEmployeeAgeCount != null) {
				searchEmployeeAgeCount.close();
			}
			if (searchEmployeeTitleCount != null) {
				searchEmployeeTitleCount.close();
			}
			if (searchEmployeeDepartmentIDCount != null) {
				searchEmployeeDepartmentIDCount.close();
			}
			if (searchEmployeeLastNameGenderCount != null) {
				searchEmployeeLastNameGenderCount.close();
			}
			if (searchEmployeeLastNameAgeCount != null) {
				searchEmployeeLastNameAgeCount.close();
			}
			if (searchEmployeeLastNameAgeCount != null) {
				searchEmployeeLastNameAgeCount.close();
			}
			if (searchEmployeeLastNameTitleCount != null) {
				searchEmployeeLastNameTitleCount.close();
			}
			if (searchEmployeeLastNameDepartmentIDCount != null) {
				searchEmployeeLastNameDepartmentIDCount.close();
			}
			if (searchEmployeeGenderAgeCount != null) {
				searchEmployeeGenderAgeCount.close();
			}
			if (searchEmployeeGenderTitleCount != null) {
				searchEmployeeGenderTitleCount.close();
			}
			if (searchEmployeeGenderDepartmentIDCount != null) {
				searchEmployeeGenderDepartmentIDCount.close();
			}
			if (searchEmployeeAgeTitleCount != null) {
				searchEmployeeAgeTitleCount.close();
			}
			if (searchEmployeeAgeDepartmentIDCount != null) {
				searchEmployeeAgeDepartmentIDCount.close();
			}
			if (checkEmployeeAddress_Employee != null) {
				checkEmployeeAddress_Employee.close();
			}
			if (checkEmployeeProject_Employee != null) {
				checkEmployeeProject_Employee.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("Couldnot close the DB -" + e.getMessage());
		}
	}

	public List<Employee> listAllEmployee() {
		try {
			ResultSet results = queryEmployee.executeQuery();
			List<Employee> employeeList = new ArrayList<>();
			while (results.next()) {
				employeeList.add(new Employee(results.getInt(1), results.getString(2), results.getString(3),
						results.getString(4), results.getString(5), results.getString(6), results.getInt(7),
						results.getString(8), results.getString(9)));
			}
			return employeeList;
		} catch (SQLException e) {
			System.out.println("Query failed - Can not load Employee information " + e.getMessage());
			return null;
		}
	}

	public int queryEmployeeCount() {
		int count = 0;
		try {
			ResultSet results = queryEmployeeCount.executeQuery();
			while (results.next()) {
				count = results.getInt(1);
			}
			return count;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}

	public int insertEmployee(String lastname, String firstname, String gender, String phoneNumber, String email,
			int age, String title, int departmentID) throws SQLException {
		insertEmployee.setString(1, lastname);
		insertEmployee.setString(2, firstname);
		insertEmployee.setString(3, gender);
		insertEmployee.setString(4, phoneNumber);
		insertEmployee.setString(5, email);
		insertEmployee.setInt(6, age);
		insertEmployee.setString(7, title);
		insertEmployee.setInt(8, departmentID);
		System.out.println(insertEmployee);
		int affectedRows = insertEmployee.executeUpdate();
		if (affectedRows != 1) {
			DataSource.getInstance()
					.insertLog(new LogOperation(DataSource.OPERATION_INSERT, TABLE_EMPLOYEE,
							String.valueOf(new Timestamp(System.currentTimeMillis())),
							DataSource.OPERATION_RESULT_FAILED, "Could not insert address - Operation failed"));
			throw new SQLException("Could not insert address");
		}
		ResultSet generatedKey = insertEmployee.getGeneratedKeys();
		if (generatedKey.next()) {
			DataSource.getInstance()
					.insertLog(new LogOperation(DataSource.OPERATION_INSERT, TABLE_EMPLOYEE,
							String.valueOf(new Timestamp(System.currentTimeMillis())),
							DataSource.OPERATION_RESULT_SUCCESS, "DONE"));
			return generatedKey.getInt(1);
		} else {
			DataSource.getInstance()
					.insertLog(new LogOperation(DataSource.OPERATION_INSERT, TABLE_EMPLOYEE,
							String.valueOf(new Timestamp(System.currentTimeMillis())),
							DataSource.OPERATION_RESULT_FAILED, "Could not get _id for address - Operation failed"));
			throw new SQLException("Could not get _id for address");
		}
	}

	public boolean insertEmployeeID(int employeID, String lastname, String firstname, String gender, String phoneNumber,
			String email, int age, String title, int departmentID) throws SQLException {
		checkEmployee.setInt(1, employeID);
		ResultSet results = checkEmployee.executeQuery();
		if (results.next()) {
			DataSource.getInstance()
					.insertLog(new LogOperation(DataSource.OPERATION_INSERT, TABLE_EMPLOYEE,
							String.valueOf(new Timestamp(System.currentTimeMillis())),
							DataSource.OPERATION_RESULT_FAILED, "Employee ID " + employeID + " already exist in DB"));
			return false;
		} else {
			insertEmployeeID.setInt(1, employeID);
			insertEmployeeID.setString(2, lastname);
			insertEmployeeID.setString(3, firstname);
			insertEmployeeID.setString(4, gender);
			insertEmployeeID.setString(5, phoneNumber);
			insertEmployeeID.setString(6, email);
			insertEmployeeID.setInt(7, age);
			insertEmployeeID.setString(8, title);
			insertEmployeeID.setInt(9, departmentID);
			int affectedRows = insertEmployeeID.executeUpdate();
			if (affectedRows != 1) {
				DataSource.getInstance()
						.insertLog(new LogOperation(DataSource.OPERATION_INSERT, TABLE_EMPLOYEE,
								String.valueOf(new Timestamp(System.currentTimeMillis())),
								DataSource.OPERATION_RESULT_FAILED, "Could not insert address - Operation failed"));
				throw new SQLException("Could not insert department");
			} else {
				DataSource.getInstance()
						.insertLog(new LogOperation(DataSource.OPERATION_INSERT, TABLE_EMPLOYEE,
								String.valueOf(new Timestamp(System.currentTimeMillis())),
								DataSource.OPERATION_RESULT_SUCCESS, "DONE"));
				return true;
			}
		}
	}

	public boolean updateEmployee(int employeID, String lastname, String firstname, String gender, String phoneNumber,
			String email, int age, String title, int departmentID) {
		try {
			updateEmployee.setString(1, lastname);
			updateEmployee.setString(2, firstname);
			updateEmployee.setString(3, gender);
			updateEmployee.setString(4, phoneNumber);
			updateEmployee.setString(5, email);
			updateEmployee.setInt(6, age);
			updateEmployee.setString(7, title);
			updateEmployee.setInt(8, departmentID);
			updateEmployee.setInt(9, employeID);
			int numRowsEffected = updateEmployee.executeUpdate();
			DataSource.getInstance()
					.insertLog(new LogOperation(DataSource.OPERATION_UPDATE, TABLE_EMPLOYEE,
							String.valueOf(new Timestamp(System.currentTimeMillis())),
							DataSource.OPERATION_RESULT_SUCCESS, "DONE"));
			return numRowsEffected == 1;
		} catch (SQLException e) {
			DataSource.getInstance()
					.insertLog(new LogOperation(DataSource.OPERATION_UPDATE, TABLE_EMPLOYEE,
							String.valueOf(new Timestamp(System.currentTimeMillis())),
							DataSource.OPERATION_RESULT_FAILED, "Update Fails"));
			System.out.println("Update Fails - " + e.getMessage());
			return false;
		}
	}

	public int deleteEmployee(int employeID) {
		try {
			checkEmployeeAddress_Employee.setInt(1, employeID);
			ResultSet result1 = checkEmployeeAddress_Employee.executeQuery();
			checkEmployeeProject_Employee.setInt(1, employeID);
			ResultSet result2 = checkEmployeeProject_Employee.executeQuery();
			if (result1.next()) {
				DataSource.getInstance().insertLog(new LogOperation(DataSource.OPERATION_DELETE, TABLE_EMPLOYEE,
						String.valueOf(new Timestamp(System.currentTimeMillis())), DataSource.OPERATION_RESULT_FAILED,
						"Employee ID = " + employeID + " has a reference on another address."));
				return 2;
			} else if (result2.next()) {
				DataSource.getInstance().insertLog(new LogOperation(DataSource.OPERATION_DELETE, TABLE_EMPLOYEE,
						String.valueOf(new Timestamp(System.currentTimeMillis())), DataSource.OPERATION_RESULT_FAILED,
						"Employee ID = " + employeID + " has a reference on another project."));
				return 3;
			} else {
				deleteEmployee.setInt(1, employeID);
				int affectedRows = deleteEmployee.executeUpdate();
				if (affectedRows != 1) {
					DataSource.getInstance()
							.insertLog(new LogOperation(DataSource.OPERATION_DELETE, TABLE_EMPLOYEE,
									String.valueOf(new Timestamp(System.currentTimeMillis())),
									DataSource.OPERATION_RESULT_FAILED, "Delete Failed"));
					return 0;
				} else {
					DataSource.getInstance()
							.insertLog(new LogOperation(DataSource.OPERATION_DELETE, TABLE_EMPLOYEE,
									String.valueOf(new Timestamp(System.currentTimeMillis())),
									DataSource.OPERATION_RESULT_SUCCESS, "DONE"));
					return 1;
				}
			}
		} catch (SQLException e) {
			DataSource.getInstance()
					.insertLog(new LogOperation(DataSource.OPERATION_DELETE, TABLE_EMPLOYEE,
							String.valueOf(new Timestamp(System.currentTimeMillis())),
							DataSource.OPERATION_RESULT_FAILED, "Could not delete Employee"));
			System.err.println("Could not delete Employee - " + e.getMessage());
			e.printStackTrace();
			return 0;
		}
	}

	public List<Employee> searchEmployeeID(int employeeID) {
		try {
			searchEmployeeID.setInt(1, employeeID);
			ResultSet results = searchEmployeeID.executeQuery();
			List<Employee> employeeList = new ArrayList<>();
			while (results.next()) {
				employeeList.add(new Employee(results.getInt(1), results.getString(2), results.getString(3),
						results.getString(4), results.getString(5), results.getString(6), results.getInt(7),
						results.getString(8), results.getInt(9)));
			}
			return employeeList;
		} catch (SQLException e) {
			System.out.println("Query failed - Can not search employee information " + e.getMessage());
			return null;
		}
	}

	public int searchEmployeeIDCount(int employeeID) {
		try {
			searchEmployeeIDCount.setInt(1, employeeID);
			ResultSet results = searchEmployeeIDCount.executeQuery();
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

	public List<Employee> searchEmployeeLastName(String lastname) {
		try {
			searchEmployeeLastName.setString(1, "%" + lastname + "%");
			ResultSet results = searchEmployeeLastName.executeQuery();
			List<Employee> employeeList = new ArrayList<>();
			while (results.next()) {
				employeeList.add(new Employee(results.getInt(1), results.getString(2), results.getString(3),
						results.getString(4), results.getString(5), results.getString(6), results.getInt(7),
						results.getString(8), results.getInt(9)));
			}
			return employeeList;
		} catch (SQLException e) {
			System.out.println("Query failed - Can not search employee information " + e.getMessage());
			return null;
		}
	}

	public int searchEmployeeLastNameCount(String lastname) {
		try {
			searchEmployeeLastNameCount.setString(1, "%" + lastname + "%");
			ResultSet results = searchEmployeeLastNameCount.executeQuery();
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

	public List<Employee> searchEmployeeFirstName(String firstName) {
		try {
			searchEmployeeFirstName.setString(1, "%" + firstName + "%");
			ResultSet results = searchEmployeeFirstName.executeQuery();
			List<Employee> employeeList = new ArrayList<>();
			while (results.next()) {
				employeeList.add(new Employee(results.getInt(1), results.getString(2), results.getString(3),
						results.getString(4), results.getString(5), results.getString(6), results.getInt(7),
						results.getString(8), results.getInt(9)));
			}
			return employeeList;
		} catch (SQLException e) {
			System.out.println("Query failed - Can not search employee information " + e.getMessage());
			return null;
		}
	}

	public int searchEmployeeFirstNameCount(String firstName) {
		try {
			searchEmployeeFirstNameCount.setString(1, "%" + firstName + "%");
			ResultSet results = searchEmployeeFirstNameCount.executeQuery();
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

	public List<Employee> searchEmployeeGender(String gender) {
		try {
			searchEmployeeGender.setString(1, gender);
			ResultSet results = searchEmployeeGender.executeQuery();
			List<Employee> employeeList = new ArrayList<>();
			while (results.next()) {
				employeeList.add(new Employee(results.getInt(1), results.getString(2), results.getString(3),
						results.getString(4), results.getString(5), results.getString(6), results.getInt(7),
						results.getString(8), results.getInt(9)));
			}
			return employeeList;
		} catch (SQLException e) {
			System.out.println("Query failed - Can not search employee information " + e.getMessage());
			return null;
		}
	}

	public int searchEmployeeGenderCount(String gender) {
		try {
			searchEmployeeGenderCount.setString(1, gender);
			ResultSet results = searchEmployeeGenderCount.executeQuery();
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

	public List<Employee> searchEmployeePhoneNumber(String phonenumber) {
		try {
			searchEmployeePhoneNumber.setString(1, "%" + phonenumber + "%");
			ResultSet results = searchEmployeePhoneNumber.executeQuery();
			List<Employee> employeeList = new ArrayList<>();
			while (results.next()) {
				employeeList.add(new Employee(results.getInt(1), results.getString(2), results.getString(3),
						results.getString(4), results.getString(5), results.getString(6), results.getInt(7),
						results.getString(8), results.getInt(9)));
			}
			return employeeList;
		} catch (SQLException e) {
			System.out.println("Query failed - Can not search employee information " + e.getMessage());
			return null;
		}
	}

	public int searchEmployeePhoneNumberCount(String phonenumber) {
		try {
			searchEmployeePhoneNumberCount.setString(1, "%" + phonenumber + "%");
			ResultSet results = searchEmployeePhoneNumberCount.executeQuery();
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

	public List<Employee> searchEmployeeEmail(String email) {
		try {
			searchEmployeeEmail.setString(1, "%" + email + "%");
			ResultSet results = searchEmployeeEmail.executeQuery();
			List<Employee> employeeList = new ArrayList<>();
			while (results.next()) {
				employeeList.add(new Employee(results.getInt(1), results.getString(2), results.getString(3),
						results.getString(4), results.getString(5), results.getString(6), results.getInt(7),
						results.getString(8), results.getInt(9)));
			}
			return employeeList;
		} catch (SQLException e) {
			System.out.println("Query failed - Can not search employee information " + e.getMessage());
			return null;
		}
	}

	public int searchEmployeeEmailCount(String email) {
		try {
			searchEmployeeEmailCount.setString(1, "%" + email + "%");
			ResultSet results = searchEmployeeEmailCount.executeQuery();
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

	public List<Employee> searchEmployeeAge(int age) {
		try {
			searchEmployeeAge.setInt(1, age);
			ResultSet results = searchEmployeeAge.executeQuery();
			List<Employee> employeeList = new ArrayList<>();
			while (results.next()) {
				employeeList.add(new Employee(results.getInt(1), results.getString(2), results.getString(3),
						results.getString(4), results.getString(5), results.getString(6), results.getInt(7),
						results.getString(8), results.getInt(9)));
			}
			return employeeList;
		} catch (SQLException e) {
			System.out.println("Query failed - Can not search employee information " + e.getMessage());
			return null;
		}
	}

	public int searchEmployeeAgeCount(int age) {
		try {
			searchEmployeeAgeCount.setInt(1, age);
			ResultSet results = searchEmployeeAgeCount.executeQuery();
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

	public List<Employee> searchEmployeeTitle(String title) {
		try {
			searchEmployeeTitle.setString(1, "%" + title + "%");
			ResultSet results = searchEmployeeTitle.executeQuery();
			List<Employee> employeeList = new ArrayList<>();
			while (results.next()) {
				employeeList.add(new Employee(results.getInt(1), results.getString(2), results.getString(3),
						results.getString(4), results.getString(5), results.getString(6), results.getInt(7),
						results.getString(8), results.getInt(9)));
			}
			return employeeList;
		} catch (SQLException e) {
			System.out.println("Query failed - Can not search employee information " + e.getMessage());
			return null;
		}
	}

	public int searchEmployeeTitleCount(String title) {
		try {
			searchEmployeeTitleCount.setString(1, "%" + title + "%");
			ResultSet results = searchEmployeeTitleCount.executeQuery();
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

	public List<Employee> searchEmployeeDepartmentID(int departmentID) {
		try {
			searchEmployeeDepartmentID.setInt(1, departmentID);
			ResultSet results = searchEmployeeDepartmentID.executeQuery();
			List<Employee> employeeList = new ArrayList<>();
			while (results.next()) {
				employeeList.add(new Employee(results.getInt(1), results.getString(2), results.getString(3),
						results.getString(4), results.getString(5), results.getString(6), results.getInt(7),
						results.getString(8), results.getInt(9)));
			}
			return employeeList;
		} catch (SQLException e) {
			System.out.println("Query failed - Can not search employee information " + e.getMessage());
			return null;
		}
	}

	public int searchEmployeeDepartmentIDCount(int departmentID) {
		try {
			searchEmployeeDepartmentIDCount.setInt(1, departmentID);
			ResultSet results = searchEmployeeDepartmentIDCount.executeQuery();
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

	public List<Employee> searchEmployeeLastNameGender(String lastname, String gender) {
		try {
			searchEmployeeLastNameGender.setString(1, "%" + lastname + "%");
			searchEmployeeLastNameGender.setString(2, gender);
			ResultSet results = searchEmployeeLastNameGender.executeQuery();
			List<Employee> employeeList = new ArrayList<>();
			while (results.next()) {
				employeeList.add(new Employee(results.getInt(1), results.getString(2), results.getString(3),
						results.getString(4), results.getString(5), results.getString(6), results.getInt(7),
						results.getString(8), results.getInt(9)));
			}
			return employeeList;
		} catch (SQLException e) {
			System.out.println("Query failed - Can not search employee information " + e.getMessage());
			return null;
		}
	}

	public int searchEmployeeLastNameGenderCount(String lastname, String gender) {
		try {
			searchEmployeeLastNameGenderCount.setString(1, "%" + lastname + "%");
			searchEmployeeLastNameGenderCount.setString(2, gender);
			ResultSet results = searchEmployeeLastNameGenderCount.executeQuery();
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

	public List<Employee> searchEmployeeLastNameAge(String lastname, int age) {
		try {
			searchEmployeeLastNameAge.setString(1, "%" + lastname + "%");
			searchEmployeeLastNameAge.setInt(2, age);
			ResultSet results = searchEmployeeLastNameAge.executeQuery();
			List<Employee> employeeList = new ArrayList<>();
			while (results.next()) {
				employeeList.add(new Employee(results.getInt(1), results.getString(2), results.getString(3),
						results.getString(4), results.getString(5), results.getString(6), results.getInt(7),
						results.getString(8), results.getInt(9)));
			}
			return employeeList;
		} catch (SQLException e) {
			System.out.println("Query failed - Can not search employee information " + e.getMessage());
			return null;
		}
	}

	public int searchEmployeeLastNameAgeCount(String lastname, int age) {
		try {
			searchEmployeeLastNameAgeCount.setString(1, "%" + lastname + "%");
			searchEmployeeLastNameAgeCount.setInt(2, age);
			ResultSet results = searchEmployeeLastNameAgeCount.executeQuery();
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

	public List<Employee> searchEmployeeLastNameTitle(String lastname, String title) {
		try {
			searchEmployeeLastNameTitle.setString(1, "%" + lastname + "%");
			searchEmployeeLastNameTitle.setString(2, "%" + title + "%");
			ResultSet results = searchEmployeeLastNameTitle.executeQuery();
			List<Employee> employeeList = new ArrayList<>();
			while (results.next()) {
				employeeList.add(new Employee(results.getInt(1), results.getString(2), results.getString(3),
						results.getString(4), results.getString(5), results.getString(6), results.getInt(7),
						results.getString(8), results.getInt(9)));
			}
			return employeeList;
		} catch (SQLException e) {
			System.out.println("Query failed - Can not search employee information " + e.getMessage());
			return null;
		}
	}

	public int searchEmployeeLastNameTitleCount(String lastname, String title) {
		try {
			searchEmployeeLastNameTitleCount.setString(1, "%" + lastname + "%");
			searchEmployeeLastNameTitleCount.setString(2, "%" + title + "%");
			ResultSet results = searchEmployeeLastNameTitleCount.executeQuery();
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

	public List<Employee> searchEmployeeLastNameDepartmentID(String lastname, int departmentID) {
		try {
			searchEmployeeLastNameDepartmentID.setString(1, "%" + lastname + "%");
			searchEmployeeLastNameDepartmentID.setInt(2, departmentID);
			ResultSet results = searchEmployeeLastNameDepartmentID.executeQuery();
			List<Employee> employeeList = new ArrayList<>();
			while (results.next()) {
				employeeList.add(new Employee(results.getInt(1), results.getString(2), results.getString(3),
						results.getString(4), results.getString(5), results.getString(6), results.getInt(7),
						results.getString(8), results.getInt(9)));
			}
			return employeeList;
		} catch (SQLException e) {
			System.out.println("Query failed - Can not search employee information " + e.getMessage());
			return null;
		}
	}

	public int searchEmployeeLastNameDepartmentIDCount(String lastname, int departmentID) {
		try {
			searchEmployeeLastNameDepartmentIDCount.setString(1, "%" + lastname + "%");
			searchEmployeeLastNameDepartmentIDCount.setInt(2, departmentID);
			ResultSet results = searchEmployeeLastNameDepartmentIDCount.executeQuery();
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

	public List<Employee> searchEmployeeGenderAge(String gender, int age) {
		try {
			searchEmployeeGenderAge.setString(1, gender);
			searchEmployeeGenderAge.setInt(2, age);
			ResultSet results = searchEmployeeGenderAge.executeQuery();
			List<Employee> employeeList = new ArrayList<>();
			while (results.next()) {
				employeeList.add(new Employee(results.getInt(1), results.getString(2), results.getString(3),
						results.getString(4), results.getString(5), results.getString(6), results.getInt(7),
						results.getString(8), results.getInt(9)));
			}
			return employeeList;
		} catch (SQLException e) {
			System.out.println("Query failed - Can not search employee information " + e.getMessage());
			return null;
		}
	}

	public int searchEmployeeGenderAgeCount(String gender, int age) {
		try {
			searchEmployeeGenderAgeCount.setString(1, gender);
			searchEmployeeGenderAgeCount.setInt(2, age);
			ResultSet results = searchEmployeeGenderAgeCount.executeQuery();
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

	public List<Employee> searchEmployeeGenderTitle(String gender, String title) {
		try {
			searchEmployeeGenderTitle.setString(1, gender);
			searchEmployeeGenderTitle.setString(2, "%" + title + "%");
			ResultSet results = searchEmployeeGenderTitle.executeQuery();
			List<Employee> employeeList = new ArrayList<>();
			while (results.next()) {
				employeeList.add(new Employee(results.getInt(1), results.getString(2), results.getString(3),
						results.getString(4), results.getString(5), results.getString(6), results.getInt(7),
						results.getString(8), results.getInt(9)));
			}
			return employeeList;
		} catch (SQLException e) {
			System.out.println("Query failed - Can not search employee information " + e.getMessage());
			return null;
		}
	}

	public int searchEmployeeGenderTitleCount(String gender, String title) {
		try {
			searchEmployeeGenderTitleCount.setString(1, gender);
			searchEmployeeGenderTitleCount.setString(2, "%" + title + "%");
			ResultSet results = searchEmployeeGenderTitleCount.executeQuery();
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

	public List<Employee> searchEmployeeGenderDepartmentID(String gender, int departmentID) {
		try {
			searchEmployeeGenderDepartmentID.setString(1, gender);
			searchEmployeeGenderDepartmentID.setInt(2, departmentID);
			ResultSet results = searchEmployeeGenderDepartmentID.executeQuery();
			List<Employee> employeeList = new ArrayList<>();
			while (results.next()) {
				employeeList.add(new Employee(results.getInt(1), results.getString(2), results.getString(3),
						results.getString(4), results.getString(5), results.getString(6), results.getInt(7),
						results.getString(8), results.getInt(9)));
			}
			return employeeList;
		} catch (SQLException e) {
			System.out.println("Query failed - Can not search employee information " + e.getMessage());
			return null;
		}
	}

	public int searchEmployeeGenderDepartmentIDCount(String gender, int departmentID) {
		try {
			searchEmployeeGenderDepartmentIDCount.setString(1, gender);
			searchEmployeeGenderDepartmentIDCount.setInt(2, departmentID);
			ResultSet results = searchEmployeeGenderDepartmentIDCount.executeQuery();
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

	public List<Employee> searchEmployeeAgeTitle(int age, String title) {
		try {
			searchEmployeeAgeTitle.setInt(1, age);
			searchEmployeeAgeTitle.setString(2, "%" + title + "%");
			ResultSet results = searchEmployeeAgeTitle.executeQuery();
			List<Employee> employeeList = new ArrayList<>();
			while (results.next()) {
				employeeList.add(new Employee(results.getInt(1), results.getString(2), results.getString(3),
						results.getString(4), results.getString(5), results.getString(6), results.getInt(7),
						results.getString(8), results.getInt(9)));
			}
			return employeeList;
		} catch (SQLException e) {
			System.out.println("Query failed - Can not search employee information " + e.getMessage());
			return null;
		}
	}

	public int searchEmployeeAgeTitleCount(int age, String title) {
		try {
			searchEmployeeAgeTitleCount.setInt(1, age);
			searchEmployeeAgeTitleCount.setString(2, "%" + title + "%");
			ResultSet results = searchEmployeeAgeTitleCount.executeQuery();
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

	public List<Employee> searchEmployeeAgeDepartmentID(int age, int departmentID) {
		try {
			searchEmployeeAgeDepartmentID.setInt(1, age);
			searchEmployeeAgeDepartmentID.setInt(2, departmentID);
			ResultSet results = searchEmployeeAgeDepartmentID.executeQuery();
			List<Employee> employeeList = new ArrayList<>();
			while (results.next()) {
				employeeList.add(new Employee(results.getInt(1), results.getString(2), results.getString(3),
						results.getString(4), results.getString(5), results.getString(6), results.getInt(7),
						results.getString(8), results.getInt(9)));
			}
			return employeeList;
		} catch (SQLException e) {
			System.out.println("Query failed - Can not search employee information " + e.getMessage());
			return null;
		}
	}

	public int searchEmployeeAgeDepartmentIDCount(int age, int departmentID) {
		try {
			searchEmployeeAgeDepartmentIDCount.setInt(1, age);
			searchEmployeeAgeDepartmentIDCount.setInt(2, departmentID);
			ResultSet results = searchEmployeeAgeDepartmentIDCount.executeQuery();
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
