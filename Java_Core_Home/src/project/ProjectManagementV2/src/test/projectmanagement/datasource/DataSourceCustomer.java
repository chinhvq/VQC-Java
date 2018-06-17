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
import test.projectmanagement.datamodel.Customer;
import test.projectmanagement.datamodel.LogOperation;

public class DataSourceCustomer {
	private static DataSourceCustomer instance = new DataSourceCustomer();

	private DataSourceCustomer() {
	}

	public static DataSourceCustomer getInstance() {
		return instance;
	}

	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/projectmanagement?useSSL=false";
	static final String USER = "root";
	static final String PASS = "root123456";

	private static final String TABLE_CUSTOMER = "customer";
	private static final String COLUMN_CUSTOMER_ID = "id";
	private static final String COLUMN_CUSTOMER_NAME = "customerName";
	private static final String COLUMN_CUSTOMER_BUSINESS_TYPE = "businessType";

	private static final String TABLE_PROJECT_CUSTOMER = "project_customer";
	private static final String COLUMN_PROJECT_CUSTOMER_CUSTOMER_ID = "Customer_id";

	private static final String TABLE_CUSTOMER_ADDRESS = "customer_address";
	private static final String COLUMN_CUSTOMER_ADDRESS_CUSTOMER_ID = "Customer_id";

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

	private static final String CHECK_PROJECT_CUSTOMER_CUSTOMER = "SELECT * FROM " + TABLE_PROJECT_CUSTOMER + " WHERE "
			+ COLUMN_PROJECT_CUSTOMER_CUSTOMER_ID + " = ?";
	private static final String CHECK_CUSTOMER_ADDRESS_CUSTOMER = "SELECT * FROM " + TABLE_CUSTOMER_ADDRESS + " WHERE "
			+ COLUMN_CUSTOMER_ADDRESS_CUSTOMER_ID + " = ?";

	public Connection conn;
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
	private PreparedStatement checkProjectCustomer_Customer;
	private PreparedStatement checkCustomerAddress_Customer;

	public boolean open() {
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
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
			checkProjectCustomer_Customer = conn.prepareStatement(CHECK_PROJECT_CUSTOMER_CUSTOMER);
			checkCustomerAddress_Customer = conn.prepareStatement(CHECK_CUSTOMER_ADDRESS_CUSTOMER);

			return true;
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println("Could not connect to DB - " + e.getMessage());
			return false;
		}
	}

	public void close() {
		try {
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
			if (checkProjectCustomer_Customer != null) {
				checkProjectCustomer_Customer.close();
			}
			if (checkCustomerAddress_Customer != null) {
				checkCustomerAddress_Customer.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("Couldnot close the DB -" + e.getMessage());
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
			DataSource.getInstance()
					.insertLog(new LogOperation(DataSource.OPERATION_INSERT, TABLE_CUSTOMER,
							String.valueOf(new Timestamp(System.currentTimeMillis())),
							DataSource.OPERATION_RESULT_FAILED, "Could not insert customer - Operation Failed"));
			throw new SQLException("Could not insert customer");
		}
		ResultSet generatedKey = addCustomerName.getGeneratedKeys();
		if (generatedKey.next()) {
			DataSource.getInstance()
					.insertLog(new LogOperation(DataSource.OPERATION_INSERT, TABLE_CUSTOMER,
							String.valueOf(new Timestamp(System.currentTimeMillis())),
							DataSource.OPERATION_RESULT_SUCCESS, "DONE"));
			return generatedKey.getInt(1);
		} else {
			DataSource.getInstance()
					.insertLog(new LogOperation(DataSource.OPERATION_INSERT, TABLE_CUSTOMER,
							String.valueOf(new Timestamp(System.currentTimeMillis())),
							DataSource.OPERATION_RESULT_FAILED, "Could not get _id for customer - Operation Failed"));
			throw new SQLException("Could not get _id for customer");
		}
	}

	public boolean addCustomerID(int customerID, String customerName, String businessType) throws SQLException {
		checkCustomer.setInt(1, customerID);
		ResultSet results = checkCustomer.executeQuery();
		if (results.next()) {
			DataSource.getInstance()
					.insertLog(new LogOperation(DataSource.OPERATION_INSERT, TABLE_CUSTOMER,
							String.valueOf(new Timestamp(System.currentTimeMillis())),
							DataSource.OPERATION_RESULT_FAILED, "Customer ID already exist in DB"));
			return false;
		} else {
			addCustomerID.setInt(1, customerID);
			addCustomerID.setString(2, customerName);
			addCustomerID.setString(3, businessType);
			int affectedRows = addCustomerID.executeUpdate();
			if (affectedRows != 1) {
				DataSource.getInstance()
						.insertLog(new LogOperation(DataSource.OPERATION_INSERT, TABLE_CUSTOMER,
								String.valueOf(new Timestamp(System.currentTimeMillis())),
								DataSource.OPERATION_RESULT_FAILED, "Could not insert customer - Operation Failed"));
				throw new SQLException("Could not insert customer");
			} else {
				DataSource.getInstance()
						.insertLog(new LogOperation(DataSource.OPERATION_INSERT, TABLE_CUSTOMER,
								String.valueOf(new Timestamp(System.currentTimeMillis())),
								DataSource.OPERATION_RESULT_SUCCESS, "DONE"));
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
			DataSource.getInstance()
					.insertLog(new LogOperation(DataSource.OPERATION_INSERT, TABLE_CUSTOMER,
							String.valueOf(new Timestamp(System.currentTimeMillis())),
							DataSource.OPERATION_RESULT_SUCCESS, "DONE"));
			return numRowsEffected == 1;
		} catch (SQLException e) {
			DataSource.getInstance()
					.insertLog(new LogOperation(DataSource.OPERATION_INSERT, TABLE_CUSTOMER,
							String.valueOf(new Timestamp(System.currentTimeMillis())),
							DataSource.OPERATION_RESULT_FAILED, "Update Fails"));
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
				DataSource.getInstance().insertLog(new LogOperation(DataSource.OPERATION_DELETE, TABLE_CUSTOMER,
						String.valueOf(new Timestamp(System.currentTimeMillis())), DataSource.OPERATION_RESULT_FAILED,
						"Customer ID = " + customerID + " has a reference on another project."));
				return 2;
			} else if (result2.next()) {
				DataSource.getInstance().insertLog(new LogOperation(DataSource.OPERATION_DELETE, TABLE_CUSTOMER,
						String.valueOf(new Timestamp(System.currentTimeMillis())), DataSource.OPERATION_RESULT_FAILED,
						"Customer ID = " + customerID + " has a reference on another address."));
				return 3;
			} else {
				deleteCustomer.setInt(1, customerID);
				int affectedRows = deleteCustomer.executeUpdate();
				if (affectedRows != 1) {
					DataSource.getInstance()
							.insertLog(new LogOperation(DataSource.OPERATION_DELETE, TABLE_CUSTOMER,
									String.valueOf(new Timestamp(System.currentTimeMillis())),
									DataSource.OPERATION_RESULT_FAILED, "Delete Fails"));
					return 0;
				} else {
					DataSource.getInstance()
							.insertLog(new LogOperation(DataSource.OPERATION_DELETE, TABLE_CUSTOMER,
									String.valueOf(new Timestamp(System.currentTimeMillis())),
									DataSource.OPERATION_RESULT_SUCCESS, "DONE"));
					return 1;
				}
			}
		} catch (SQLException e) {
			DataSource.getInstance()
					.insertLog(new LogOperation(DataSource.OPERATION_DELETE, TABLE_CUSTOMER,
							String.valueOf(new Timestamp(System.currentTimeMillis())),
							DataSource.OPERATION_RESULT_FAILED, "Could not delete customer"));
			System.err.println("Could not delete customer - " + e.getMessage());
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
