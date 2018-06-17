package test.projectmanagement.datamodel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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

	private static final String TABLE_LOG = "log";
	// private static final String COLUMN_LOG_ID = "id";
	private static final String COLUMN_LOG_OPERATION = "operation";
	private static final String COLUMN_LOG_TABLE_NAME = "tablename";
	private static final String COLUMN_LOG_AT_TIME = "at_time";
	private static final String COLUMN_LOG_RESULT = "result";
	private static final String COLUMN_LOG_REASON = "reason";
	private static final String OPERATION_INSERT = "INSERT";
	private static final String OPERATION_UPDATE = "UPDATE";
	private static final String OPERATION_DELETE = "DELETE";
	private static final String OPERATION_RESULT_FAILED = "FAILED";
	private static final String OPERATION_RESULT_SUCCESS = "SUCCESS";

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

	private static final String TABLE_ADDRESS = "address";
	private static final String COLUMN_ADDRESS_ID = "id";
	private static final String COLUMN_ADDRESS_WARD = "ward";
	private static final String COLUMN_ADDRESS_DISTRICT = "district";
	private static final String COLUMN_ADDRESS_CITY = "city";
	private static final String COLUMN_ADDRESS_COUNTRY = "country";

	private static final String TABLE_DEPARTMENT = "department";
	private static final String COLUMN_DEPARTMENT_ID = "id";
	private static final String COLUMN_DEPARTMENT_NAME = "deptName";

	private static final String TABLE_PROJECT = "project";
	private static final String COLUMN_PROJECT_ID = "id";
	private static final String COLUMN_PROJECT_NAME = "projectName";
	private static final String COLUMN_PROJECT_STATUS = "status";
	private static final String COLUMN_PROJECT_CATEGORY = "projectCategory";

	private static final String TABLE_CUSTOMER = "customer";
	private static final String COLUMN_CUSTOMER_ID = "id";
	private static final String COLUMN_CUSTOMER_NAME = "customerName";
	private static final String COLUMN_CUSTOMER_BUSINESS_TYPE = "businessType";

	private static final String TABLE_PROJECT_CUSTOMER = "project_customer";
	private static final String COLUMN_PROJECT_CUSTOMER_CUSTOMER_ID = "Customer_id";
	private static final String COLUMN_PROJECT_CUSTOMER_PROJECT_ID = "Project_id";

	private static final String TABLE_CUSTOMER_ADDRESS = "customer_address";
	private static final String COLUMN_CUSTOMER_ADDRESS_CUSTOMER_ID = "Customer_id";
	private static final String COLUMN_CUSTOMER_ADDRESS_ADDRESS_ID = "Address_id";

	private static final String TABLE_EMPLOYEE_ADDRESS = "employee_address";
	private static final String COLUMN_EMPLOYEE_ADDRESS_ADDRESS_ID = "Address_id";
	private static final String COLUMN_EMPLOYEE_ADDRESS_EMPLOYEE_ID = "Employee_id";

	private static final String TABLE_EMPLOYEE_PROJECT = "employee_project";
	private static final String COLUMN_EMPLOYEE_PROJECT_EMPLOYEE_ID = "Employee_id";
	private static final String COLUMN_EMPLOYEE_PROJECT_PROJECT_ID = "Project_id";

	private static final String VIEW_CUSTOMER_PROJECT = "customer_project_view";
	private static final String QUERY_VIEW_CUSTOMER_PROJECT = "SELECT * FROM " + VIEW_CUSTOMER_PROJECT;
	private static final String QUERY_VIEW_CUSTOMER_PROJECT_COUNT = "SELECT COUNT(*) FROM " + VIEW_CUSTOMER_PROJECT;

	private static final String INSERT_LOG = "INSERT INTO " + TABLE_LOG + " (" + COLUMN_LOG_OPERATION + ", "
			+ COLUMN_LOG_TABLE_NAME + ", " + COLUMN_LOG_AT_TIME + ", " + COLUMN_LOG_RESULT + ", " + COLUMN_LOG_REASON
			+ ") VALUES (?, ?, ?, ?, ?)";
	private static final String QUERY_LOG = "SELECT * FROM " + TABLE_LOG;
	private static final String QUERY_LOG_COUNT = "SELECT COUNT(*) FROM " + TABLE_LOG;

	private static final String QUERY_CUSTOMER_INFO = "SELECT * FROM " + TABLE_CUSTOMER + " ORDER BY "
			+ COLUMN_CUSTOMER_ID + " ASC";
	private static final String QUERY_CUSTOMER_COUNT = "SELECT COUNT(*) FROM " + TABLE_CUSTOMER;
	private static final String INSERT_CUSTOMER_NAME = "INSERT INTO " + TABLE_CUSTOMER + " (" + COLUMN_CUSTOMER_NAME
			+ " , " + COLUMN_CUSTOMER_BUSINESS_TYPE + ") VALUES (?, ?)";
	private static final String INSERT_CUSTOMER_ID = "INSERT INTO " + TABLE_CUSTOMER + " (" + COLUMN_CUSTOMER_ID + " , "
			+ COLUMN_CUSTOMER_NAME + " , " + COLUMN_CUSTOMER_BUSINESS_TYPE + ") VALUES (?, ?, ?)";
	private static final String CHECK_CUSTOMER = "SELECT * FROM " + TABLE_CUSTOMER + " WHERE " + COLUMN_CUSTOMER_ID
			+ " = ?";
	private static final String UPDATE_CUSTOMER = "UPDATE " + TABLE_CUSTOMER + " SET " + COLUMN_CUSTOMER_NAME
			+ " = ? , " + COLUMN_CUSTOMER_BUSINESS_TYPE + " = ? WHERE " + COLUMN_CUSTOMER_ID + " = ?";
	private static final String QUERY_PROJECT_INFO = "SELECT * FROM " + TABLE_PROJECT + " ORDER BY " + COLUMN_PROJECT_ID
			+ " ASC";
	private static final String DELETE_CUSTOMER = "DELETE FROM " + TABLE_CUSTOMER + " WHERE " + COLUMN_CUSTOMER_ID
			+ " = ?";
	private static final String SEARCH_CUSTOMER_ID = "SELECT * FROM CUSTOMER WHERE " + COLUMN_CUSTOMER_ID + " = ?";
	private static final String SEARCH_CUSTOMER_NAME = "SELECT * FROM CUSTOMER WHERE " + COLUMN_CUSTOMER_NAME
			+ " LIKE ?";
	private static final String SEARCH_CUSTOMER_BUSINESS_TYPE = "SELECT * FROM CUSTOMER WHERE "
			+ COLUMN_CUSTOMER_BUSINESS_TYPE + " LIKE ?";
	private static final String SEARCH_CUSTOMER_NAME_BUSINESS_TYPE = "SELECT * FROM CUSTOMER WHERE "
			+ COLUMN_CUSTOMER_NAME + " LIKE ? AND " + COLUMN_CUSTOMER_BUSINESS_TYPE + " LIKE ?";
	private static final String SEARCH_CUSTOMER_ID_COUNT = "SELECT COUNT(*) FROM CUSTOMER WHERE " + COLUMN_CUSTOMER_ID
			+ " = ?";
	private static final String SEARCH_CUSTOMER_NAME_COUNT = "SELECT COUNT(*) FROM CUSTOMER WHERE "
			+ COLUMN_CUSTOMER_NAME + " LIKE ?";
	private static final String SEARCH_CUSTOMER_BUSINESS_TYPE_COUNT = "SELECT COUNT(*) FROM CUSTOMER WHERE "
			+ COLUMN_CUSTOMER_BUSINESS_TYPE + " LIKE ?";
	private static final String SEARCH_CUSTOMER_NAME_BUSINESS_TYPE_COUNT = "SELECT COUNT(*) FROM CUSTOMER WHERE "
			+ COLUMN_CUSTOMER_NAME + " LIKE ? AND " + COLUMN_CUSTOMER_BUSINESS_TYPE + " LIKE ?";

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
	private static final String QUERY_DEPARTMENT_INFO = "SELECT * FROM " + TABLE_DEPARTMENT + " ORDER BY "
			+ COLUMN_DEPARTMENT_ID + " ASC";
	private static final String QUERY_DEPARTMENT_COUNT = "SELECT COUNT(*) FROM " + TABLE_DEPARTMENT;
	private static final String CHECK_DEPARTMENT = "SELECT * FROM " + TABLE_DEPARTMENT + " WHERE id = ?";
	private static final String INSERT_DEPARTMENT_ID_NAME = "INSERT INTO " + TABLE_DEPARTMENT
			+ " (id, deptName) VALUES (?, ?)";
	private static final String INSERT_DEPARTMENT_NAME = "INSERT INTO " + TABLE_DEPARTMENT + " (deptName) VALUES (?)";
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

	private static final String QUERY_ADDRESS_INFO = "SELECT * FROM " + TABLE_ADDRESS + " ORDER BY " + COLUMN_ADDRESS_ID
			+ " ASC";
	private static final String QUERY_ADDRESS_COUNT = "SELECT COUNT(*) FROM " + TABLE_ADDRESS;
	private static final String INSERT_ADDRESS = "INSERT INTO " + TABLE_ADDRESS + " SET " + COLUMN_ADDRESS_WARD
			+ " = ? , " + COLUMN_ADDRESS_DISTRICT + " = ? , " + COLUMN_ADDRESS_CITY + " = ? , " + COLUMN_ADDRESS_COUNTRY
			+ " = ?";
	private static final String INSERT_ADDRESS_ID = "INSERT INTO " + TABLE_ADDRESS + " SET " + COLUMN_ADDRESS_ID
			+ " = ? , " + COLUMN_ADDRESS_WARD + " = ? , " + COLUMN_ADDRESS_DISTRICT + " = ? , " + COLUMN_ADDRESS_CITY
			+ " = ? , " + COLUMN_ADDRESS_COUNTRY + " = ?";
	private static final String CHECK_ADDRESS = "SELECT * FROM " + TABLE_ADDRESS + " WHERE " + COLUMN_ADDRESS_ID
			+ " = ?";
	private static final String UPDATE_ADDRESS = "UPDATE " + TABLE_ADDRESS + " SET " + COLUMN_ADDRESS_WARD + " = ? , "
			+ COLUMN_ADDRESS_DISTRICT + " = ? , " + COLUMN_ADDRESS_CITY + " = ? , " + COLUMN_ADDRESS_COUNTRY
			+ " = ? WHERE " + COLUMN_ADDRESS_ID + " = ?";

	private static final String DELETE_ADDRESS = "DELETE FROM " + TABLE_ADDRESS + " WHERE " + COLUMN_ADDRESS_ID
			+ " = ?";
	private static final String CHECK_PROJECT_CUSTOMER_PROJECT = "SELECT * FROM " + TABLE_PROJECT_CUSTOMER + " WHERE "
			+ COLUMN_PROJECT_CUSTOMER_PROJECT_ID + " = ?";
	private static final String CHECK_EMPLOYEE_PROJECT_PROJECT = "SELECT * FROM " + TABLE_EMPLOYEE_PROJECT + " WHERE "
			+ COLUMN_EMPLOYEE_PROJECT_PROJECT_ID + " = ?";
	private static final String CHECK_CUSTOMER_ADDRESS_ADDRESS = "SELECT * FROM " + TABLE_CUSTOMER_ADDRESS + " WHERE "
			+ COLUMN_CUSTOMER_ADDRESS_ADDRESS_ID + " = ?";
	private static final String CHECK_EMPLOYEE_ADDRESS_ADDRESS = "SELECT * FROM " + TABLE_EMPLOYEE_ADDRESS + " WHERE "
			+ COLUMN_EMPLOYEE_ADDRESS_ADDRESS_ID + " = ?";
	private static final String SEARCH_ADDRESS_ID = "SELECT * FROM " + TABLE_ADDRESS + " WHERE " + COLUMN_ADDRESS_ID
			+ " = ?";
	private static final String SEARCH_ADDRESS_WARD = "SELECT * FROM " + TABLE_ADDRESS + " WHERE " + COLUMN_ADDRESS_WARD
			+ " LIKE ?";
	private static final String SEARCH_ADDRESS_DISTRICT = "SELECT * FROM " + TABLE_ADDRESS + " WHERE "
			+ COLUMN_ADDRESS_DISTRICT + " LIKE ?";
	private static final String SEARCH_ADDRESS_CITY = "SELECT * FROM " + TABLE_ADDRESS + " WHERE " + COLUMN_ADDRESS_CITY
			+ " LIKE ?";
	private static final String SEARCH_ADDRESS_COUNTRY = "SELECT * FROM " + TABLE_ADDRESS + " WHERE "
			+ COLUMN_ADDRESS_COUNTRY + " LIKE ?";
	private static final String SEARCH_ADDRESS_WARD_DISTRICT = "SELECT * FROM " + TABLE_ADDRESS + " WHERE "
			+ COLUMN_ADDRESS_WARD + " LIKE ? AND " + COLUMN_ADDRESS_DISTRICT + " LIKE ?";
	private static final String SEARCH_ADDRESS_WARD_CITY = "SELECT * FROM " + TABLE_ADDRESS + " WHERE "
			+ COLUMN_ADDRESS_WARD + " LIKE ? AND " + COLUMN_ADDRESS_CITY + " LIKE ?";
	private static final String SEARCH_ADDRESS_WARD_COUNTRY = "SELECT * FROM " + TABLE_ADDRESS + " WHERE "
			+ COLUMN_ADDRESS_WARD + " LIKE ? AND " + COLUMN_ADDRESS_COUNTRY + " LIKE ?";
	private static final String SEARCH_ADDRESS_CITY_COUNTRY = "SELECT * FROM " + TABLE_ADDRESS + " WHERE "
			+ COLUMN_ADDRESS_CITY + " LIKE ? AND " + COLUMN_ADDRESS_COUNTRY + " LIKE ?";
	private static final String SEARCH_ADDRESS_ID_COUNT = "SELECT COUNT(*) FROM " + TABLE_ADDRESS + " WHERE "
			+ COLUMN_ADDRESS_ID + " = ?";
	private static final String SEARCH_ADDRESS_WARD_COUNT = "SELECT COUNT(*) FROM " + TABLE_ADDRESS + " WHERE "
			+ COLUMN_ADDRESS_WARD + " LIKE ?";
	private static final String SEARCH_ADDRESS_DISTRICT_COUNT = "SELECT COUNT(*) FROM " + TABLE_ADDRESS + " WHERE "
			+ COLUMN_ADDRESS_DISTRICT + " LIKE ?";
	private static final String SEARCH_ADDRESS_CITY_COUNT = "SELECT COUNT(*) FROM " + TABLE_ADDRESS + " WHERE "
			+ COLUMN_ADDRESS_CITY + " LIKE ?";
	private static final String SEARCH_ADDRESS_COUNTRY_COUNT = "SELECT COUNT(*) FROM " + TABLE_ADDRESS + " WHERE "
			+ COLUMN_ADDRESS_COUNTRY + " LIKE ?";
	private static final String SEARCH_ADDRESS_WARD_DISTRICT_COUNT = "SELECT COUNT(*) FROM " + TABLE_ADDRESS + " WHERE "
			+ COLUMN_ADDRESS_WARD + " LIKE ? AND " + COLUMN_ADDRESS_DISTRICT + " LIKE ?";
	private static final String SEARCH_ADDRESS_WARD_CITY_COUNT = "SELECT COUNT(*) FROM " + TABLE_ADDRESS + " WHERE "
			+ COLUMN_ADDRESS_WARD + " LIKE ? AND " + COLUMN_ADDRESS_CITY + " LIKE ?";
	private static final String SEARCH_ADDRESS_WARD_COUNTRY_COUNT = "SELECT COUNT(*) FROM " + TABLE_ADDRESS + " WHERE "
			+ COLUMN_ADDRESS_WARD + " LIKE ? AND " + COLUMN_ADDRESS_COUNTRY + " LIKE ?";
	private static final String SEARCH_ADDRESS_CITY_COUNTRY_COUNT = "SELECT COUNT(*) FROM " + TABLE_ADDRESS + " WHERE "
			+ COLUMN_ADDRESS_CITY + " LIKE ? AND " + COLUMN_ADDRESS_COUNTRY + " LIKE ?";
	private static final String CHECK_PROJECT_CUSTOMER_CUSTOMER = "SELECT * FROM " + TABLE_PROJECT_CUSTOMER + " WHERE "
			+ COLUMN_PROJECT_CUSTOMER_CUSTOMER_ID + " = ?";
	private static final String CHECK_CUSTOMER_ADDRESS_CUSTOMER = "SELECT * FROM " + TABLE_CUSTOMER_ADDRESS + " WHERE "
			+ COLUMN_CUSTOMER_ADDRESS_CUSTOMER_ID + " = ?";
	private static final String CHECK_EMPLOYEE_ADDRESS_EMPLOYEE = "SELECT * FROM " + TABLE_EMPLOYEE_ADDRESS + " WHERE "
			+ COLUMN_EMPLOYEE_ADDRESS_EMPLOYEE_ID + " = ?";
	private static final String CHECK_EMPLOYEE_PROJECT_EMPLOYEE = "SELECT * FROM " + TABLE_EMPLOYEE_PROJECT + " WHERE "
			+ COLUMN_EMPLOYEE_PROJECT_EMPLOYEE_ID + " = ?";

	private Connection conn;
	private PreparedStatement insertLog;
	private PreparedStatement queryLog;
	private PreparedStatement queryLogCount;
	private PreparedStatement viewCustomerProject;
	private PreparedStatement viewCustomerProjectCount;
	private PreparedStatement addCustomerName;
	private PreparedStatement checkCustomer;
	private PreparedStatement queryCustomerCount;
	private PreparedStatement addCustomerID;
	private PreparedStatement queryCustomer;
	private PreparedStatement updateCustomer;
	private PreparedStatement deleteCustomer;
	private PreparedStatement searchCustomerID;
	private PreparedStatement searchCustomerName;
	private PreparedStatement searchCustomerBusinesType;
	private PreparedStatement searchCustomerNameBusinessType;
	private PreparedStatement searchCustomerIDCount;
	private PreparedStatement searchCustomerNameCount;
	private PreparedStatement searchCustomerBusinesTypeCount;
	private PreparedStatement searchCustomerNameBusinessTypeCount;
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
	private PreparedStatement queryAddress;
	private PreparedStatement queryAddressCount;
	private PreparedStatement insertAddress;
	private PreparedStatement insertAddressID;
	private PreparedStatement checkAddress;
	private PreparedStatement updateAddress;
	private PreparedStatement deleteAddress;
	private PreparedStatement searchAddressID;
	private PreparedStatement searchAddressWard;
	private PreparedStatement searchAddressDistrict;
	private PreparedStatement searchAddressCity;
	private PreparedStatement searchAddressCountry;
	private PreparedStatement searchAddressWardDistrict;
	private PreparedStatement searchAddressWardCity;
	private PreparedStatement searchAddressWardCountry;
	private PreparedStatement searchAddressCityCountry;
	private PreparedStatement searchAddressIDCount;
	private PreparedStatement searchAddressWardCount;
	private PreparedStatement searchAddressDistrictCount;
	private PreparedStatement searchAddressCityCount;
	private PreparedStatement searchAddressCountryCount;
	private PreparedStatement searchAddressWardDistrictCount;
	private PreparedStatement searchAddressWardCityCount;
	private PreparedStatement searchAddressWardCountryCount;
	private PreparedStatement searchAddressCityCountryCount;
	private PreparedStatement checkProjectCustomer_Project;
	private PreparedStatement checkEmployeeProject_Project;
	private PreparedStatement checkCustomerAddress_Address;
	private PreparedStatement checkEmployeeAddress_Address;
	private PreparedStatement checkProjectCustomer_Customer;
	private PreparedStatement checkCustomerAddress_Customer;
	private PreparedStatement checkEmployeeAddress_Employee;
	private PreparedStatement checkEmployeeProject_Employee;

	public boolean open() {
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			insertLog = conn.prepareStatement(INSERT_LOG);
			queryLog = conn.prepareStatement(QUERY_LOG);
			queryLogCount = conn.prepareStatement(QUERY_LOG_COUNT);
			viewCustomerProject = conn.prepareStatement(QUERY_VIEW_CUSTOMER_PROJECT);
			viewCustomerProjectCount = conn.prepareStatement(QUERY_VIEW_CUSTOMER_PROJECT_COUNT);
			addCustomerName = conn.prepareStatement(INSERT_CUSTOMER_NAME, Statement.RETURN_GENERATED_KEYS);
			checkCustomer = conn.prepareStatement(CHECK_CUSTOMER);
			addCustomerID = conn.prepareStatement(INSERT_CUSTOMER_ID);
			queryCustomer = conn.prepareStatement(QUERY_CUSTOMER_INFO);
			queryCustomerCount = conn.prepareStatement(QUERY_CUSTOMER_COUNT);
			updateCustomer = conn.prepareStatement(UPDATE_CUSTOMER);
			deleteCustomer = conn.prepareStatement(DELETE_CUSTOMER);
			searchCustomerID = conn.prepareStatement(SEARCH_CUSTOMER_ID);
			searchCustomerName = conn.prepareStatement(SEARCH_CUSTOMER_NAME);
			searchCustomerBusinesType = conn.prepareStatement(SEARCH_CUSTOMER_BUSINESS_TYPE);
			searchCustomerNameBusinessType = conn.prepareStatement(SEARCH_CUSTOMER_NAME_BUSINESS_TYPE);
			searchCustomerIDCount = conn.prepareStatement(SEARCH_CUSTOMER_ID_COUNT);
			searchCustomerNameCount = conn.prepareStatement(SEARCH_CUSTOMER_NAME_COUNT);
			searchCustomerBusinesTypeCount = conn.prepareStatement(SEARCH_CUSTOMER_BUSINESS_TYPE_COUNT);
			searchCustomerNameBusinessTypeCount = conn.prepareStatement(SEARCH_CUSTOMER_NAME_BUSINESS_TYPE_COUNT);
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
			queryDepartment = conn.prepareStatement(QUERY_DEPARTMENT_INFO);
			queryDepartmentCount = conn.prepareStatement(QUERY_DEPARTMENT_COUNT);
			checkDepartment = conn.prepareStatement(CHECK_DEPARTMENT);
			insertDepartmentIdName = conn.prepareStatement(INSERT_DEPARTMENT_ID_NAME, Statement.RETURN_GENERATED_KEYS);
			insertDepartmentName = conn.prepareStatement(INSERT_DEPARTMENT_NAME, Statement.RETURN_GENERATED_KEYS);
			updateDepartment = conn.prepareStatement(UPDATE_DEPARTMENT);
			deleteDepartment = conn.prepareStatement(DELETE_DEPARTMENT, Statement.RETURN_GENERATED_KEYS);
			searchDepartmentID = conn.prepareStatement(SEARCH_DEPARTMENT_ID);
			searchDepartmentName = conn.prepareStatement(SEARCH_DEPARTMENT_NAME);
			searchDepartmentIDName = conn.prepareStatement(SEARCH_DEPARTMENT_ID_NAME);
			searchDepartmentCount = conn.prepareStatement(SEARCH_DEPARTMENT_COUNT);
			queryAddress = conn.prepareStatement(QUERY_ADDRESS_INFO);
			queryAddressCount = conn.prepareStatement(QUERY_ADDRESS_COUNT);
			insertAddress = conn.prepareStatement(INSERT_ADDRESS, Statement.RETURN_GENERATED_KEYS);
			insertAddressID = conn.prepareStatement(INSERT_ADDRESS_ID);
			checkAddress = conn.prepareStatement(CHECK_ADDRESS);
			updateAddress = conn.prepareStatement(UPDATE_ADDRESS);
			deleteAddress = conn.prepareStatement(DELETE_ADDRESS);
			searchAddressID = conn.prepareStatement(SEARCH_ADDRESS_ID);
			searchAddressWard = conn.prepareStatement(SEARCH_ADDRESS_WARD);
			searchAddressDistrict = conn.prepareStatement(SEARCH_ADDRESS_DISTRICT);
			searchAddressCity = conn.prepareStatement(SEARCH_ADDRESS_CITY);
			searchAddressCountry = conn.prepareStatement(SEARCH_ADDRESS_COUNTRY);
			searchAddressWardDistrict = conn.prepareStatement(SEARCH_ADDRESS_WARD_DISTRICT);
			searchAddressWardCity = conn.prepareStatement(SEARCH_ADDRESS_WARD_CITY);
			searchAddressWardCountry = conn.prepareStatement(SEARCH_ADDRESS_WARD_COUNTRY);
			searchAddressCityCountry = conn.prepareStatement(SEARCH_ADDRESS_CITY_COUNTRY);
			searchAddressIDCount = conn.prepareStatement(SEARCH_ADDRESS_ID_COUNT);
			searchAddressWardCount = conn.prepareStatement(SEARCH_ADDRESS_WARD_COUNT);
			searchAddressDistrictCount = conn.prepareStatement(SEARCH_ADDRESS_DISTRICT_COUNT);
			searchAddressCityCount = conn.prepareStatement(SEARCH_ADDRESS_CITY_COUNT);
			searchAddressCountryCount = conn.prepareStatement(SEARCH_ADDRESS_COUNTRY_COUNT);
			searchAddressWardDistrictCount = conn.prepareStatement(SEARCH_ADDRESS_WARD_DISTRICT_COUNT);
			searchAddressWardCityCount = conn.prepareStatement(SEARCH_ADDRESS_WARD_CITY_COUNT);
			searchAddressWardCountryCount = conn.prepareStatement(SEARCH_ADDRESS_WARD_COUNTRY_COUNT);
			searchAddressCityCountryCount = conn.prepareStatement(SEARCH_ADDRESS_CITY_COUNTRY_COUNT);
			checkProjectCustomer_Project = conn.prepareStatement(CHECK_PROJECT_CUSTOMER_PROJECT);
			checkEmployeeProject_Project = conn.prepareStatement(CHECK_EMPLOYEE_PROJECT_PROJECT);
			checkCustomerAddress_Address = conn.prepareStatement(CHECK_CUSTOMER_ADDRESS_ADDRESS);
			checkEmployeeAddress_Address = conn.prepareStatement(CHECK_EMPLOYEE_ADDRESS_ADDRESS);
			checkProjectCustomer_Customer = conn.prepareStatement(CHECK_PROJECT_CUSTOMER_CUSTOMER);
			checkCustomerAddress_Customer = conn.prepareStatement(CHECK_CUSTOMER_ADDRESS_CUSTOMER);
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
			if (queryCustomer != null) {
				queryCustomer.close();
			}
			if (queryCustomerCount != null) {
				queryCustomerCount.close();
			}
			if (addCustomerID != null) {
				addCustomerID.close();
			}
			if (addCustomerName != null) {
				addCustomerName.close();
			}
			if (checkCustomer != null) {
				checkCustomer.close();
			}
			if (updateCustomer != null) {
				updateCustomer.close();
			}
			if (deleteCustomer != null) {
				deleteCustomer.close();
			}
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
			if (queryAddress != null) {
				queryAddress.close();
			}
			if (queryAddressCount != null) {
				queryAddressCount.close();
			}
			if (insertAddress != null) {
				insertAddress.close();
			}
			if (insertAddressID != null) {
				insertAddressID.close();
			}
			if (checkAddress != null) {
				checkAddress.close();
			}
			if (updateAddress != null) {
				updateAddress.close();
			}
			if (deleteAddress != null) {
				deleteAddress.close();
			}
			if (searchAddressID != null) {
				searchAddressID.close();
				;
			}
			if (searchAddressWard != null) {
				searchAddressWard.close();
			}
			if (searchAddressDistrict != null) {
				searchAddressDistrict.close();
			}
			if (searchAddressCity != null) {
				searchAddressCity.close();
			}
			if (searchAddressCountry != null) {
				searchAddressCountry.close();
				;
			}
			if (searchAddressWardDistrict != null) {
				searchAddressWardDistrict.close();
			}
			if (searchAddressWardCity != null) {
				searchAddressWardCity.close();
			}
			if (searchAddressWardCountry != null) {
				searchAddressWardCountry.close();
			}
			if (searchAddressCityCountry != null) {
				searchAddressCityCountry.close();
			}
			if (searchAddressIDCount != null) {
				searchAddressIDCount.close();
			}
			if (searchAddressWardCount != null) {
				searchAddressWardCount.close();
			}
			if (searchAddressDistrictCount != null) {
				searchAddressDistrictCount.close();
			}
			if (searchAddressCityCount != null) {
				searchAddressCityCount.close();
			}
			if (searchAddressCountryCount != null) {
				searchAddressCountryCount.close();
			}
			if (searchAddressWardDistrictCount != null) {
				searchAddressWardDistrictCount.close();
			}
			if (searchAddressWardCityCount != null) {
				searchAddressWardCityCount.close();
			}
			if (searchAddressWardCountryCount != null) {
				searchAddressWardCountryCount.close();
			}
			if (searchAddressCityCountryCount != null) {
				searchAddressCityCountryCount.close();
			}
			if (checkProjectCustomer_Project != null) {
				checkProjectCustomer_Project.close();
			}
			if (checkEmployeeProject_Project != null) {
				checkEmployeeProject_Project.close();
			}
			if (checkCustomerAddress_Address != null) {
				checkCustomerAddress_Address.close();
			}
			if (checkEmployeeAddress_Address != null) {
				checkEmployeeAddress_Address.close();
			}
			if (checkProjectCustomer_Customer != null) {
				checkProjectCustomer_Customer.close();
			}
			if (checkCustomerAddress_Customer != null) {
				checkCustomerAddress_Customer.close();
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

	public List<Customer> listAllCustomer() {
		try {
			ResultSet results = queryCustomer.executeQuery();
			List<Customer> customerList = new ArrayList<>();
			while (results.next()) {
				customerList.add(new Customer(results.getInt(1), results.getString(2), results.getString(3)));
			}
			return customerList;
		} catch (SQLException e) {
			System.out.println("Query failed - Can not load Customer information " + e.getMessage());
			return null;
		}
	}

	public int queryCustomerCount() {
		try {
			ResultSet results = queryCustomerCount.executeQuery();
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

	public int addCustomerName(String customerName, String businessType) throws SQLException {
		addCustomerName.setString(1, customerName);
		addCustomerName.setString(2, businessType);
		int affectedRows = addCustomerName.executeUpdate();
		if (affectedRows != 1) {
			insertLog(new LogOperation(OPERATION_INSERT, TABLE_CUSTOMER,
					String.valueOf(new Timestamp(System.currentTimeMillis())), OPERATION_RESULT_FAILED,
					"Could not insert customer - Operation Failed"));
			throw new SQLException("Could not insert customer");
		}
		ResultSet generatedKey = addCustomerName.getGeneratedKeys();
		if (generatedKey.next()) {
			insertLog(new LogOperation(OPERATION_INSERT, TABLE_CUSTOMER,
					String.valueOf(new Timestamp(System.currentTimeMillis())), OPERATION_RESULT_SUCCESS, "DONE"));
			return generatedKey.getInt(1);
		} else {
			insertLog(new LogOperation(OPERATION_INSERT, TABLE_CUSTOMER,
					String.valueOf(new Timestamp(System.currentTimeMillis())), OPERATION_RESULT_FAILED,
					"Could not get _id for customer - Operation Failed"));
			throw new SQLException("Could not get _id for customer");
		}
	}

	public boolean addCustomerID(int customerID, String customerName, String businessType) throws SQLException {
		checkCustomer.setInt(1, customerID);
		ResultSet results = checkCustomer.executeQuery();
		if (results.next()) {
			insertLog(new LogOperation(OPERATION_INSERT, TABLE_CUSTOMER,
					String.valueOf(new Timestamp(System.currentTimeMillis())), OPERATION_RESULT_FAILED,
					"Customer ID already exist in DB"));
			return false;
		} else {
			addCustomerID.setInt(1, customerID);
			addCustomerID.setString(2, customerName);
			addCustomerID.setString(3, businessType);
			int affectedRows = addCustomerID.executeUpdate();
			if (affectedRows != 1) {
				insertLog(new LogOperation(OPERATION_INSERT, TABLE_CUSTOMER,
						String.valueOf(new Timestamp(System.currentTimeMillis())), OPERATION_RESULT_FAILED,
						"Could not insert customer - Operation Failed"));
				throw new SQLException("Could not insert customer");
			} else {
				insertLog(new LogOperation(OPERATION_INSERT, TABLE_CUSTOMER,
						String.valueOf(new Timestamp(System.currentTimeMillis())), OPERATION_RESULT_SUCCESS, "DONE"));
				return true;
			}
		}
	}

	public boolean updateCustomer(int customerID, String customerName, String businessType) {
		try {
			updateCustomer.setString(1, customerName);
			updateCustomer.setString(2, businessType);
			updateCustomer.setInt(3, customerID);
			int numRowsEffected = updateCustomer.executeUpdate();
			insertLog(new LogOperation(OPERATION_INSERT, TABLE_CUSTOMER,
					String.valueOf(new Timestamp(System.currentTimeMillis())), OPERATION_RESULT_SUCCESS, "DONE"));
			return numRowsEffected == 1;
		} catch (SQLException e) {
			insertLog(new LogOperation(OPERATION_INSERT, TABLE_CUSTOMER,
					String.valueOf(new Timestamp(System.currentTimeMillis())), OPERATION_RESULT_FAILED,
					"Update Fails"));
			System.out.println("Update Fails - " + e.getMessage());
			return false;
		}
	}

	public int deleteCustomer(int customerID) {
		try {
			checkProjectCustomer_Customer.setInt(1, customerID);
			ResultSet result1 = checkProjectCustomer_Customer.executeQuery();
			checkCustomerAddress_Customer.setInt(1, customerID);
			ResultSet result2 = checkCustomerAddress_Customer.executeQuery();
			if (result1.next()) {
				insertLog(new LogOperation(OPERATION_DELETE, TABLE_CUSTOMER,
						String.valueOf(new Timestamp(System.currentTimeMillis())), OPERATION_RESULT_FAILED,
						"Customer ID = " + customerID + " has a reference on another project."));
				return 2;
			} else if (result2.next()) {
				insertLog(new LogOperation(OPERATION_DELETE, TABLE_CUSTOMER,
						String.valueOf(new Timestamp(System.currentTimeMillis())), OPERATION_RESULT_FAILED,
						"Customer ID = " + customerID + " has a reference on another address."));
				return 3;
			} else {
				deleteCustomer.setInt(1, customerID);
				int affectedRows = deleteCustomer.executeUpdate();
				if (affectedRows != 1) {
					insertLog(new LogOperation(OPERATION_DELETE, TABLE_CUSTOMER,
							String.valueOf(new Timestamp(System.currentTimeMillis())), OPERATION_RESULT_FAILED,
							"Delete Fails"));
					return 0;
				} else {
					insertLog(new LogOperation(OPERATION_DELETE, TABLE_CUSTOMER,
							String.valueOf(new Timestamp(System.currentTimeMillis())), OPERATION_RESULT_SUCCESS,
							"DONE"));
					return 1;
				}
			}
		} catch (SQLException e) {
			insertLog(new LogOperation(OPERATION_DELETE, TABLE_CUSTOMER,
					String.valueOf(new Timestamp(System.currentTimeMillis())), OPERATION_RESULT_FAILED,
					"Could not delete customer"));
			System.err.println("Could not delete customer - " + e.getMessage());
			return 0;
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
			insertLog(new LogOperation(OPERATION_INSERT, TABLE_PROJECT,
					String.valueOf(new Timestamp(System.currentTimeMillis())), OPERATION_RESULT_FAILED,
					"Could not insert project - Operation Failed"));
			throw new SQLException("Could not insert project");
		}
		ResultSet generatedKey = insertProjectName.getGeneratedKeys();
		if (generatedKey.next()) {
			insertLog(new LogOperation(OPERATION_INSERT, TABLE_PROJECT,
					String.valueOf(new Timestamp(System.currentTimeMillis())), OPERATION_RESULT_SUCCESS, "DONE"));
			return generatedKey.getInt(1);
		} else {
			insertLog(new LogOperation(OPERATION_INSERT, TABLE_PROJECT,
					String.valueOf(new Timestamp(System.currentTimeMillis())), OPERATION_RESULT_FAILED,
					"Could not get _id for project - Operation Failed"));
			throw new SQLException("Could not get _id for project");
		}
	}

	public boolean insertprojectIDName(int projectID, String projectName, String projectStatus, String projectCategory)
			throws SQLException {
		checkProject.setInt(1, projectID);
		ResultSet results = checkProject.executeQuery();
		if (results.next()) {
			insertLog(new LogOperation(OPERATION_INSERT, TABLE_PROJECT,
					String.valueOf(new Timestamp(System.currentTimeMillis())), OPERATION_RESULT_FAILED,
					"Project ID already exist in DB"));
			return false;
		} else {
			insertprojectIDName.setInt(1, projectID);
			insertprojectIDName.setString(2, projectName);
			insertprojectIDName.setString(3, projectStatus);
			insertprojectIDName.setString(4, projectCategory);
			System.out.println(insertprojectIDName);
			int affectedRows = insertprojectIDName.executeUpdate();
			if (affectedRows != 1) {
				insertLog(new LogOperation(OPERATION_INSERT, TABLE_PROJECT,
						String.valueOf(new Timestamp(System.currentTimeMillis())), OPERATION_RESULT_FAILED,
						"Could not insert project - Operation Failed"));
				throw new SQLException("Could not insert project");
			} else {
				insertLog(new LogOperation(OPERATION_INSERT, TABLE_PROJECT,
						String.valueOf(new Timestamp(System.currentTimeMillis())), OPERATION_RESULT_SUCCESS, "DONE"));
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
			insertLog(new LogOperation(OPERATION_UPDATE, TABLE_PROJECT,
					String.valueOf(new Timestamp(System.currentTimeMillis())), OPERATION_RESULT_SUCCESS, "DONE"));
			return numRowsEffected == 1;
		} catch (SQLException e) {
			insertLog(new LogOperation(OPERATION_UPDATE, TABLE_PROJECT,
					String.valueOf(new Timestamp(System.currentTimeMillis())), OPERATION_RESULT_FAILED,
					"Update Fails"));
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
				insertLog(new LogOperation(OPERATION_DELETE, TABLE_PROJECT,
						String.valueOf(new Timestamp(System.currentTimeMillis())), OPERATION_RESULT_FAILED,
						"Project ID = " + projectID + " has a reference on another customer."));
				return 2;
			} else if (result2.next()) {
				insertLog(new LogOperation(OPERATION_DELETE, TABLE_PROJECT,
						String.valueOf(new Timestamp(System.currentTimeMillis())), OPERATION_RESULT_FAILED,
						"Project ID = " + projectID + " has a reference on another employee."));
				return 3;
			} else {
				deleteProject.setInt(1, projectID);
				int affectedRows = deleteProject.executeUpdate();
				if (affectedRows != 1) {
					insertLog(new LogOperation(OPERATION_DELETE, TABLE_PROJECT,
							String.valueOf(new Timestamp(System.currentTimeMillis())), OPERATION_RESULT_FAILED,
							"Delete Fails"));
					return 0;
				} else {
					insertLog(new LogOperation(OPERATION_DELETE, TABLE_PROJECT,
							String.valueOf(new Timestamp(System.currentTimeMillis())), OPERATION_RESULT_SUCCESS,
							"DONE"));
					return 1;
				}
			}
		} catch (SQLException e) {
			insertLog(new LogOperation(OPERATION_DELETE, TABLE_PROJECT,
					String.valueOf(new Timestamp(System.currentTimeMillis())), OPERATION_RESULT_FAILED,
					"Could not delete project"));
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
			searchProjectStatusCategoryCount.setString(1, "%" + projectName + "%");
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
			insertLog(new LogOperation(OPERATION_INSERT, TABLE_EMPLOYEE,
					String.valueOf(new Timestamp(System.currentTimeMillis())), OPERATION_RESULT_FAILED,
					"Could not insert address - Operation failed"));
			throw new SQLException("Could not insert address");
		}
		ResultSet generatedKey = insertEmployee.getGeneratedKeys();
		if (generatedKey.next()) {
			insertLog(new LogOperation(OPERATION_INSERT, TABLE_EMPLOYEE,
					String.valueOf(new Timestamp(System.currentTimeMillis())), OPERATION_RESULT_SUCCESS, "DONE"));
			return generatedKey.getInt(1);
		} else {
			insertLog(new LogOperation(OPERATION_INSERT, TABLE_EMPLOYEE,
					String.valueOf(new Timestamp(System.currentTimeMillis())), OPERATION_RESULT_FAILED,
					"Could not get _id for address - Operation failed"));
			throw new SQLException("Could not get _id for address");
		}
	}

	public boolean insertEmployeeID(int employeID, String lastname, String firstname, String gender, String phoneNumber,
			String email, int age, String title, int departmentID) throws SQLException {
		checkEmployee.setInt(1, employeID);
		ResultSet results = checkEmployee.executeQuery();
		if (results.next()) {
			insertLog(new LogOperation(OPERATION_INSERT, TABLE_EMPLOYEE,
					String.valueOf(new Timestamp(System.currentTimeMillis())), OPERATION_RESULT_FAILED,
					"Employee ID " + employeID + " already exist in DB"));
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
				insertLog(new LogOperation(OPERATION_INSERT, TABLE_EMPLOYEE,
						String.valueOf(new Timestamp(System.currentTimeMillis())), OPERATION_RESULT_FAILED,
						"Could not insert address - Operation failed"));
				throw new SQLException("Could not insert department");
			} else {
				insertLog(new LogOperation(OPERATION_INSERT, TABLE_EMPLOYEE,
						String.valueOf(new Timestamp(System.currentTimeMillis())), OPERATION_RESULT_SUCCESS, "DONE"));
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
			System.out.println(updateEmployee);
			int numRowsEffected = updateEmployee.executeUpdate();
			insertLog(new LogOperation(OPERATION_UPDATE, TABLE_EMPLOYEE,
					String.valueOf(new Timestamp(System.currentTimeMillis())), OPERATION_RESULT_SUCCESS, "DONE"));
			return numRowsEffected == 1;
		} catch (SQLException e) {
			insertLog(new LogOperation(OPERATION_UPDATE, TABLE_EMPLOYEE,
					String.valueOf(new Timestamp(System.currentTimeMillis())), OPERATION_RESULT_FAILED,
					"Update Fails"));
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
				insertLog(new LogOperation(OPERATION_DELETE, TABLE_EMPLOYEE,
						String.valueOf(new Timestamp(System.currentTimeMillis())), OPERATION_RESULT_FAILED,
						"Employee ID = " + employeID + " has a reference on another address."));
				return 2;
			} else if (result2.next()) {
				insertLog(new LogOperation(OPERATION_DELETE, TABLE_EMPLOYEE,
						String.valueOf(new Timestamp(System.currentTimeMillis())), OPERATION_RESULT_FAILED,
						"Employee ID = " + employeID + " has a reference on another project."));
				return 3;
			} else {
				deleteEmployee.setInt(1, employeID);
				int affectedRows = deleteEmployee.executeUpdate();
				if (affectedRows != 1) {
					insertLog(new LogOperation(OPERATION_DELETE, TABLE_EMPLOYEE,
							String.valueOf(new Timestamp(System.currentTimeMillis())), OPERATION_RESULT_FAILED,
							"Delete Failed"));
					return 0;
				} else {
					insertLog(new LogOperation(OPERATION_DELETE, TABLE_EMPLOYEE,
							String.valueOf(new Timestamp(System.currentTimeMillis())), OPERATION_RESULT_SUCCESS,
							"DONE"));
					return 1;
				}
			}
		} catch (SQLException e) {
			insertLog(new LogOperation(OPERATION_DELETE, TABLE_EMPLOYEE,
					String.valueOf(new Timestamp(System.currentTimeMillis())), OPERATION_RESULT_FAILED,
					"Could not delete Employee"));
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
			insertLog(new LogOperation(OPERATION_INSERT, TABLE_DEPARTMENT,
					String.valueOf(new Timestamp(System.currentTimeMillis())), OPERATION_RESULT_FAILED,
					"Deparment ID already exist"));
			return false;
		} else {
			insertDepartmentIdName.setInt(1, departmentId);
			insertDepartmentIdName.setString(2, departmentName);
			int affectedRows = insertDepartmentIdName.executeUpdate();
			System.out.println(affectedRows);
			if (affectedRows != 1) {
				insertLog(new LogOperation(OPERATION_INSERT, TABLE_DEPARTMENT,
						String.valueOf(new Timestamp(System.currentTimeMillis())), OPERATION_RESULT_FAILED,
						"Could not insert department - Operation Failed"));
				throw new SQLException("Could not insert department");
			} else {
				insertLog(new LogOperation(OPERATION_INSERT, TABLE_DEPARTMENT,
						String.valueOf(new Timestamp(System.currentTimeMillis())), OPERATION_RESULT_SUCCESS, "DONE"));
				return true;
			}
		}
	}

	public int insertDepartmentName(String departmentName) throws SQLException {
		insertDepartmentName.setString(1, departmentName);
		int affectedRows = insertDepartmentName.executeUpdate();
		if (affectedRows != 1) {
			insertLog(new LogOperation(OPERATION_INSERT, TABLE_DEPARTMENT,
					String.valueOf(new Timestamp(System.currentTimeMillis())), OPERATION_RESULT_FAILED,
					"Could not insert department - Operation Failed"));
			throw new SQLException("Could not insert department");
		}
		ResultSet generatedKey = insertDepartmentName.getGeneratedKeys();
		if (generatedKey.next()) {
			insertLog(new LogOperation(OPERATION_INSERT, TABLE_DEPARTMENT,
					String.valueOf(new Timestamp(System.currentTimeMillis())), OPERATION_RESULT_SUCCESS, "DONE"));
			return generatedKey.getInt(1);
		} else {
			insertLog(new LogOperation(OPERATION_INSERT, TABLE_DEPARTMENT,
					String.valueOf(new Timestamp(System.currentTimeMillis())), OPERATION_RESULT_FAILED,
					"Could not get _id for department - Operation Failed"));
			throw new SQLException("Could not get _id for department");
		}
	}

	public boolean updateDepartment(int departmentId, String departmentName) {
		try {
			updateDepartment.setString(1, departmentName);
			updateDepartment.setInt(2, departmentId);
			int numRowsEffected = updateDepartment.executeUpdate();
			insertLog(new LogOperation(OPERATION_INSERT, TABLE_DEPARTMENT,
					String.valueOf(new Timestamp(System.currentTimeMillis())), OPERATION_RESULT_SUCCESS, "DONE"));
			return numRowsEffected == 1;
		} catch (SQLException e) {
			insertLog(new LogOperation(OPERATION_INSERT, TABLE_DEPARTMENT,
					String.valueOf(new Timestamp(System.currentTimeMillis())), OPERATION_RESULT_FAILED,
					"Update Fails"));
			System.out.println("Update Fails - " + e.getMessage());
			return false;
		}
	}

	public boolean deleteDepartment(int departmentId) {
		try {
			deleteDepartment.setInt(1, departmentId);
			int numRowsEffected = deleteDepartment.executeUpdate();
			insertLog(new LogOperation(OPERATION_INSERT, TABLE_DEPARTMENT,
					String.valueOf(new Timestamp(System.currentTimeMillis())), OPERATION_RESULT_SUCCESS, "DONE"));
			return numRowsEffected == 1;
		} catch (SQLException e) {
			insertLog(new LogOperation(OPERATION_INSERT, TABLE_DEPARTMENT,
					String.valueOf(new Timestamp(System.currentTimeMillis())), OPERATION_RESULT_FAILED,
					"Delete Fails"));
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

	public List<Address> listAllAddress() {
		try {
			ResultSet results = queryAddress.executeQuery();
			List<Address> addressList = new ArrayList<>();
			while (results.next()) {
				addressList.add(new Address(results.getInt(1), results.getString(2), results.getString(3),
						results.getString(4), results.getString(5)));
			}
			return addressList;
		} catch (SQLException e) {
			System.out.println("Query failed - Can not load address information " + e.getMessage());
			return null;
		}
	}

	public int queryAddressCount() {
		int count = 0;
		try {
			ResultSet results = queryAddressCount.executeQuery();
			while (results.next()) {
				count = results.getInt(1);
			}
			return count;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}

	public int insertAddress(String ward, String district, String city, String country) throws SQLException {
		insertAddress.setString(1, ward);
		insertAddress.setString(2, district);
		insertAddress.setString(3, city);
		insertAddress.setString(4, country);
		int affectedRows = insertAddress.executeUpdate();
		if (affectedRows != 1) {
			insertLog(new LogOperation(OPERATION_INSERT, TABLE_ADDRESS,
					String.valueOf(new Timestamp(System.currentTimeMillis())), OPERATION_RESULT_FAILED,
					"Could not insert address - Operation failed"));
			throw new SQLException("Could not insert address");
		}
		ResultSet generatedKey = insertAddress.getGeneratedKeys();
		if (generatedKey.next()) {
			insertLog(new LogOperation(OPERATION_INSERT, TABLE_ADDRESS,
					String.valueOf(new Timestamp(System.currentTimeMillis())), OPERATION_RESULT_SUCCESS, "DONE"));
			return generatedKey.getInt(1);
		} else {
			insertLog(new LogOperation(OPERATION_INSERT, TABLE_ADDRESS,
					String.valueOf(new Timestamp(System.currentTimeMillis())), OPERATION_RESULT_FAILED,
					"Could not get _id for address - Operation failed"));
			throw new SQLException("Could not get _id for address");
		}
	}

	public boolean insertAddressID(int addressID, String ward, String district, String city, String country)
			throws SQLException {
		checkAddress.setInt(1, addressID);
		ResultSet results = checkAddress.executeQuery();
		if (results.next()) {
			insertLog(new LogOperation(OPERATION_INSERT, TABLE_ADDRESS,
					String.valueOf(new Timestamp(System.currentTimeMillis())), OPERATION_RESULT_FAILED,
					"Address ID " + addressID + " already exist in DB"));
			return false;
		} else {
			insertAddressID.setInt(1, addressID);
			insertAddressID.setString(2, ward);
			insertAddressID.setString(3, district);
			insertAddressID.setString(4, city);
			insertAddressID.setString(5, country);
			int affectedRows = insertAddressID.executeUpdate();
			if (affectedRows != 1) {
				insertLog(new LogOperation(OPERATION_INSERT, TABLE_ADDRESS,
						String.valueOf(new Timestamp(System.currentTimeMillis())), OPERATION_RESULT_FAILED,
						"Could not insert address - Operation failed"));
				throw new SQLException("Could not insert department");
			} else {
				insertLog(new LogOperation(OPERATION_INSERT, TABLE_ADDRESS,
						String.valueOf(new Timestamp(System.currentTimeMillis())), OPERATION_RESULT_SUCCESS, "DONE"));
				return true;
			}
		}
	}

	public boolean updateAddress(int addressID, String ward, String district, String city, String country) {
		try {
			updateAddress.setString(1, ward);
			updateAddress.setString(2, district);
			updateAddress.setString(3, city);
			updateAddress.setString(4, country);
			updateAddress.setInt(5, addressID);
			int numRowsEffected = updateAddress.executeUpdate();
			insertLog(new LogOperation(OPERATION_UPDATE, TABLE_ADDRESS,
					String.valueOf(new Timestamp(System.currentTimeMillis())), OPERATION_RESULT_SUCCESS, "DONE"));
			return numRowsEffected == 1;
		} catch (SQLException e) {
			insertLog(new LogOperation(OPERATION_UPDATE, TABLE_ADDRESS,
					String.valueOf(new Timestamp(System.currentTimeMillis())), OPERATION_RESULT_FAILED,
					"Update Fails"));
			System.out.println("Update Fails - " + e.getMessage());
			return false;
		}
	}

	public int deleteAddress(int addressID) {
		try {
			checkCustomerAddress_Address.setInt(1, addressID);
			ResultSet customerResult = checkCustomerAddress_Address.executeQuery();
			checkEmployeeAddress_Address.setInt(1, addressID);
			ResultSet employeeResult = checkEmployeeAddress_Address.executeQuery();
			if (customerResult.next()) {
				insertLog(new LogOperation(OPERATION_DELETE, TABLE_ADDRESS,
						String.valueOf(new Timestamp(System.currentTimeMillis())), OPERATION_RESULT_FAILED,
						"Address ID = " + addressID + " has a reference on another customer."));
				return 2;
			} else if (employeeResult.next()) {
				insertLog(new LogOperation(OPERATION_DELETE, TABLE_ADDRESS,
						String.valueOf(new Timestamp(System.currentTimeMillis())), OPERATION_RESULT_FAILED,
						"Address ID = " + addressID + " has a reference on another employee."));
				return 3;
			} else {
				deleteAddress.setInt(1, addressID);
				int affectedRows = deleteAddress.executeUpdate();
				if (affectedRows != 1) {
					insertLog(new LogOperation(OPERATION_DELETE, TABLE_ADDRESS,
							String.valueOf(new Timestamp(System.currentTimeMillis())), OPERATION_RESULT_FAILED,
							"Delete Failed"));
					return 0;
				} else {
					insertLog(new LogOperation(OPERATION_DELETE, TABLE_ADDRESS,
							String.valueOf(new Timestamp(System.currentTimeMillis())), OPERATION_RESULT_SUCCESS,
							"DONE"));
					return 1;
				}
			}
		} catch (SQLException e) {
			insertLog(new LogOperation(OPERATION_DELETE, TABLE_ADDRESS,
					String.valueOf(new Timestamp(System.currentTimeMillis())), OPERATION_RESULT_FAILED,
					"Could not delete Address"));
			System.err.println("Could not delete Address - " + e.getMessage());
			e.printStackTrace();
			return 0;
		}
	}

	public List<Address> searchAddressID(int addressID) {
		try {
			searchAddressID.setInt(1, addressID);
			ResultSet results = searchAddressID.executeQuery();
			List<Address> addressList = new ArrayList<>();
			while (results.next()) {
				addressList.add(new Address(results.getInt(1), results.getString(2), results.getString(3),
						results.getString(4), results.getString(5)));
			}
			return addressList;
		} catch (SQLException e) {
			System.out.println("Query failed - Can not search department information " + e.getMessage());
			return null;
		}
	}

	public List<Address> searchAddressWard(String ward) {
		try {
			searchAddressWard.setString(1, "%" + ward + "%");
			ResultSet results = searchAddressWard.executeQuery();
			List<Address> addressList = new ArrayList<>();
			while (results.next()) {
				addressList.add(new Address(results.getInt(1), results.getString(2), results.getString(3),
						results.getString(4), results.getString(5)));
			}
			return addressList;
		} catch (SQLException e) {
			System.out.println("Query failed - Can not search department information " + e.getMessage());
			return null;
		}
	}

	public List<Address> searchAddressDistrict(String district) {
		try {
			searchAddressDistrict.setString(1, "%" + district + "%");
			ResultSet results = searchAddressDistrict.executeQuery();
			List<Address> addressList = new ArrayList<>();
			while (results.next()) {
				addressList.add(new Address(results.getInt(1), results.getString(2), results.getString(3),
						results.getString(4), results.getString(5)));
			}
			return addressList;
		} catch (SQLException e) {
			System.out.println("Query failed - Can not search department information " + e.getMessage());
			return null;
		}
	}

	public List<Address> searchAddressCity(String city) {
		try {
			searchAddressCity.setString(1, "%" + city + "%");
			ResultSet results = searchAddressCity.executeQuery();
			List<Address> addressList = new ArrayList<>();
			while (results.next()) {
				addressList.add(new Address(results.getInt(1), results.getString(2), results.getString(3),
						results.getString(4), results.getString(5)));
			}
			return addressList;
		} catch (SQLException e) {
			System.out.println("Query failed - Can not search department information " + e.getMessage());
			return null;
		}
	}

	public List<Address> searchAddressCountry(String country) {
		try {
			searchAddressCountry.setString(1, "%" + country + "%");
			ResultSet results = searchAddressCountry.executeQuery();
			List<Address> addressList = new ArrayList<>();
			while (results.next()) {
				addressList.add(new Address(results.getInt(1), results.getString(2), results.getString(3),
						results.getString(4), results.getString(5)));
			}
			return addressList;
		} catch (SQLException e) {
			System.out.println("Query failed - Can not search department information " + e.getMessage());
			return null;
		}
	}

	public List<Address> searchAddressWardDistrict(String ward, String district) {
		try {
			searchAddressWardDistrict.setString(1, "%" + ward + "%");
			searchAddressWardDistrict.setString(2, "%" + district + "%");
			ResultSet results = searchAddressWardDistrict.executeQuery();
			List<Address> addressList = new ArrayList<>();
			while (results.next()) {
				addressList.add(new Address(results.getInt(1), results.getString(2), results.getString(3),
						results.getString(4), results.getString(5)));
			}
			return addressList;
		} catch (SQLException e) {
			System.out.println("Query failed - Can not search department information " + e.getMessage());
			return null;
		}
	}

	public List<Address> searchAddressWardCity(String ward, String city) {
		try {
			searchAddressWardCity.setString(1, "%" + ward + "%");
			searchAddressWardCity.setString(2, "%" + city + "%");
			ResultSet results = searchAddressWardCity.executeQuery();
			List<Address> addressList = new ArrayList<>();
			while (results.next()) {
				addressList.add(new Address(results.getInt(1), results.getString(2), results.getString(3),
						results.getString(4), results.getString(5)));
			}
			return addressList;
		} catch (SQLException e) {
			System.out.println("Query failed - Can not search department information " + e.getMessage());
			return null;
		}
	}

	public List<Address> searchAddressWardCountry(String ward, String country) {
		try {
			searchAddressWardCountry.setString(1, "%" + ward + "%");
			searchAddressWardCountry.setString(2, "%" + country + "%");
			ResultSet results = searchAddressWardCountry.executeQuery();
			List<Address> addressList = new ArrayList<>();
			while (results.next()) {
				addressList.add(new Address(results.getInt(1), results.getString(2), results.getString(3),
						results.getString(4), results.getString(5)));
			}
			return addressList;
		} catch (SQLException e) {
			System.out.println("Query failed - Can not search department information " + e.getMessage());
			return null;
		}
	}

	public List<Address> searchAddressCityCountry(String city, String country) {
		try {
			searchAddressCityCountry.setString(1, "%" + city + "%");
			searchAddressCityCountry.setString(2, "%" + country + "%");
			ResultSet results = searchAddressCityCountry.executeQuery();
			List<Address> addressList = new ArrayList<>();
			while (results.next()) {
				addressList.add(new Address(results.getInt(1), results.getString(2), results.getString(3),
						results.getString(4), results.getString(5)));
			}
			return addressList;
		} catch (SQLException e) {
			System.out.println("Query failed - Can not search department information " + e.getMessage());
			return null;
		}
	}

	public int searchAddressIDCount(int addressID) {
		try {
			searchAddressIDCount.setInt(1, addressID);
			ResultSet results = searchAddressIDCount.executeQuery();
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

	public int searchAddressWardCount(String ward) {
		try {
			searchAddressWardCount.setString(1, "%" + ward + "%");
			ResultSet results = searchAddressWardCount.executeQuery();
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

	public int searchAddressDistrictCount(String district) {
		try {
			searchAddressDistrictCount.setString(1, "%" + district + "%");
			ResultSet results = searchAddressDistrictCount.executeQuery();
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

	public int searchAddressCityCount(String city) {
		try {
			searchAddressCityCount.setString(1, "%" + city + "%");
			ResultSet results = searchAddressCityCount.executeQuery();
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

	public int searchAddressCountryCount(String country) {
		try {
			searchAddressCountryCount.setString(1, "%" + country + "%");
			ResultSet results = searchAddressCountryCount.executeQuery();
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

	public int searchAddressWardDistrictCount(String ward, String district) {
		try {
			searchAddressWardDistrictCount.setString(1, "%" + ward + "%");
			searchAddressWardDistrictCount.setString(2, "%" + district + "%");
			ResultSet results = searchAddressWardDistrictCount.executeQuery();
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

	public int searchAddressWardCityCount(String ward, String city) {
		try {
			searchAddressWardCityCount.setString(1, "%" + ward + "%");
			searchAddressWardCityCount.setString(2, "%" + city + "%");
			ResultSet results = searchAddressWardCityCount.executeQuery();
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

	public int searchAddressWardCountryCount(String ward, String country) {
		try {
			searchAddressWardCountryCount.setString(1, "%" + ward + "%");
			searchAddressWardCountryCount.setString(2, "%" + country + "%");
			ResultSet results = searchAddressWardCountryCount.executeQuery();
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

	public int searchAddressCityCountryCount(String city, String country) {
		try {
			searchAddressCityCountryCount.setString(1, "%" + city + "%");
			searchAddressCityCountryCount.setString(2, "%" + country + "%");
			ResultSet results = searchAddressCityCountryCount.executeQuery();
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

	public List<Customer> searchCustomerID(int customerID) {
		try {
			searchCustomerID.setInt(1, customerID);
			ResultSet results = searchCustomerID.executeQuery();
			List<Customer> customerList = new ArrayList<>();
			while (results.next()) {
				customerList.add(new Customer(results.getInt(1), results.getString(2), results.getString(3)));
			}
			return customerList;
		} catch (SQLException e) {
			System.out.println("Query failed - Can not search customer information " + e.getMessage());
			return null;
		}
	}

	public int searchCustomerIDCount(int customerID) {
		try {
			searchCustomerIDCount.setInt(1, customerID);
			ResultSet results = searchCustomerIDCount.executeQuery();
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

	public List<Customer> searchCustomerName(String customerName) {
		try {
			searchCustomerName.setString(1, "%" + customerName + "%");
			ResultSet results = searchCustomerName.executeQuery();
			List<Customer> customerList = new ArrayList<>();
			while (results.next()) {
				customerList.add(new Customer(results.getInt(1), results.getString(2), results.getString(3)));
			}
			return customerList;
		} catch (SQLException e) {
			System.out.println("Query failed - Can not search customer information " + e.getMessage());
			return null;
		}
	}

	public int searchCustomerNameCount(String customerName) {
		try {
			searchCustomerNameCount.setString(1, "%" + customerName + "%");
			ResultSet results = searchCustomerNameCount.executeQuery();
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

	public List<Customer> searchCustomerBusinesType(String businessType) {
		try {
			searchCustomerBusinesType.setString(1, "%" + businessType + "%");
			ResultSet results = searchCustomerBusinesType.executeQuery();
			List<Customer> customerList = new ArrayList<>();
			while (results.next()) {
				customerList.add(new Customer(results.getInt(1), results.getString(2), results.getString(3)));
			}
			return customerList;
		} catch (SQLException e) {
			System.out.println("Query failed - Can not search customer information " + e.getMessage());
			return null;
		}
	}

	public int searchCustomerBusinesTypeCount(String businessType) {
		try {
			searchCustomerBusinesTypeCount.setString(1, "%" + businessType + "%");
			ResultSet results = searchCustomerBusinesTypeCount.executeQuery();
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

	public List<Customer> searchCustomerNameBusinessType(String customerName, String businessType) {
		try {
			searchCustomerNameBusinessType.setString(1, "%" + customerName + "%");
			searchCustomerNameBusinessType.setString(2, "%" + businessType + "%");
			ResultSet results = searchCustomerNameBusinessType.executeQuery();
			List<Customer> customerList = new ArrayList<>();
			while (results.next()) {
				customerList.add(new Customer(results.getInt(1), results.getString(2), results.getString(3)));
			}
			return customerList;
		} catch (SQLException e) {
			System.out.println("Query failed - Can not search customer information " + e.getMessage());
			return null;
		}
	}

	public int searchCustomerNameBusinessTypeCount(String customerName, String businessType) {
		try {
			searchCustomerNameBusinessTypeCount.setString(1, "%" + customerName + "%");
			searchCustomerNameBusinessTypeCount.setString(2, "%" + businessType + "%");
			ResultSet results = searchCustomerNameBusinessTypeCount.executeQuery();
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
