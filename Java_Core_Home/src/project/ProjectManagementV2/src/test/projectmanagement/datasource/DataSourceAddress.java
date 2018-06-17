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
import test.projectmanagement.datamodel.Address;
import test.projectmanagement.datamodel.LogOperation;

public class DataSourceAddress {
	private static DataSourceAddress instance = new DataSourceAddress();

	private DataSourceAddress() {
	}

	public static DataSourceAddress getInstance() {
		return instance;
	}

	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/projectmanagement?useSSL=false";
	static final String USER = "root";
	static final String PASS = "root123456";

	private static final String TABLE_ADDRESS = "address";
	private static final String COLUMN_ADDRESS_ID = "id";
	private static final String COLUMN_ADDRESS_WARD = "ward";
	private static final String COLUMN_ADDRESS_DISTRICT = "district";
	private static final String COLUMN_ADDRESS_CITY = "city";
	private static final String COLUMN_ADDRESS_COUNTRY = "country";

	private static final String TABLE_CUSTOMER_ADDRESS = "customer_address";
	private static final String COLUMN_CUSTOMER_ADDRESS_ADDRESS_ID = "Address_id";

	private static final String TABLE_EMPLOYEE_ADDRESS = "employee_address";
	private static final String COLUMN_EMPLOYEE_ADDRESS_ADDRESS_ID = "Address_id";

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

	public Connection conn;
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
	private PreparedStatement checkCustomerAddress_Address;
	private PreparedStatement checkEmployeeAddress_Address;

	public boolean open() {
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
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
			checkCustomerAddress_Address = conn.prepareStatement(CHECK_CUSTOMER_ADDRESS_ADDRESS);
			checkEmployeeAddress_Address = conn.prepareStatement(CHECK_EMPLOYEE_ADDRESS_ADDRESS);

			return true;
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println("Could not connect to DB - " + e.getMessage());
			return false;
		}
	}

	public void close() {
		try {
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
			if (checkCustomerAddress_Address != null) {
				checkCustomerAddress_Address.close();
			}
			if (checkEmployeeAddress_Address != null) {
				checkEmployeeAddress_Address.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("Couldnot close the DB -" + e.getMessage());
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
			DataSource.getInstance()
					.insertLog(new LogOperation(DataSource.OPERATION_INSERT, TABLE_ADDRESS,
							String.valueOf(new Timestamp(System.currentTimeMillis())),
							DataSource.OPERATION_RESULT_FAILED, "Could not insert address - Operation failed"));
			throw new SQLException("Could not insert address");
		}
		ResultSet generatedKey = insertAddress.getGeneratedKeys();
		if (generatedKey.next()) {
			DataSource.getInstance()
					.insertLog(new LogOperation(DataSource.OPERATION_INSERT, TABLE_ADDRESS,
							String.valueOf(new Timestamp(System.currentTimeMillis())),
							DataSource.OPERATION_RESULT_SUCCESS, "DONE"));
			return generatedKey.getInt(1);
		} else {
			DataSource.getInstance()
					.insertLog(new LogOperation(DataSource.OPERATION_INSERT, TABLE_ADDRESS,
							String.valueOf(new Timestamp(System.currentTimeMillis())),
							DataSource.OPERATION_RESULT_FAILED, "Could not get _id for address - Operation failed"));
			throw new SQLException("Could not get _id for address");
		}
	}

	public boolean insertAddressID(int addressID, String ward, String district, String city, String country)
			throws SQLException {
		checkAddress.setInt(1, addressID);
		ResultSet results = checkAddress.executeQuery();
		if (results.next()) {
			DataSource.getInstance()
					.insertLog(new LogOperation(DataSource.OPERATION_INSERT, TABLE_ADDRESS,
							String.valueOf(new Timestamp(System.currentTimeMillis())),
							DataSource.OPERATION_RESULT_FAILED, "Address ID " + addressID + " already exist in DB"));
			return false;
		} else {
			insertAddressID.setInt(1, addressID);
			insertAddressID.setString(2, ward);
			insertAddressID.setString(3, district);
			insertAddressID.setString(4, city);
			insertAddressID.setString(5, country);
			int affectedRows = insertAddressID.executeUpdate();
			if (affectedRows != 1) {
				DataSource.getInstance()
						.insertLog(new LogOperation(DataSource.OPERATION_INSERT, TABLE_ADDRESS,
								String.valueOf(new Timestamp(System.currentTimeMillis())),
								DataSource.OPERATION_RESULT_FAILED, "Could not insert address - Operation failed"));
				throw new SQLException("Could not insert department");
			} else {
				DataSource.getInstance()
						.insertLog(new LogOperation(DataSource.OPERATION_INSERT, TABLE_ADDRESS,
								String.valueOf(new Timestamp(System.currentTimeMillis())),
								DataSource.OPERATION_RESULT_SUCCESS, "DONE"));
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
			DataSource.getInstance()
					.insertLog(new LogOperation(DataSource.OPERATION_UPDATE, TABLE_ADDRESS,
							String.valueOf(new Timestamp(System.currentTimeMillis())),
							DataSource.OPERATION_RESULT_SUCCESS, "DONE"));
			return numRowsEffected == 1;
		} catch (SQLException e) {
			DataSource.getInstance()
					.insertLog(new LogOperation(DataSource.OPERATION_UPDATE, TABLE_ADDRESS,
							String.valueOf(new Timestamp(System.currentTimeMillis())),
							DataSource.OPERATION_RESULT_FAILED, "Update Fails"));
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
				DataSource.getInstance().insertLog(new LogOperation(DataSource.OPERATION_DELETE, TABLE_ADDRESS,
						String.valueOf(new Timestamp(System.currentTimeMillis())), DataSource.OPERATION_RESULT_FAILED,
						"Address ID = " + addressID + " has a reference on another customer."));
				return 2;
			} else if (employeeResult.next()) {
				DataSource.getInstance().insertLog(new LogOperation(DataSource.OPERATION_DELETE, TABLE_ADDRESS,
						String.valueOf(new Timestamp(System.currentTimeMillis())), DataSource.OPERATION_RESULT_FAILED,
						"Address ID = " + addressID + " has a reference on another employee."));
				return 3;
			} else {
				deleteAddress.setInt(1, addressID);
				int affectedRows = deleteAddress.executeUpdate();
				if (affectedRows != 1) {
					DataSource.getInstance()
							.insertLog(new LogOperation(DataSource.OPERATION_DELETE, TABLE_ADDRESS,
									String.valueOf(new Timestamp(System.currentTimeMillis())),
									DataSource.OPERATION_RESULT_FAILED, "Delete Failed"));
					return 0;
				} else {
					DataSource.getInstance()
							.insertLog(new LogOperation(DataSource.OPERATION_DELETE, TABLE_ADDRESS,
									String.valueOf(new Timestamp(System.currentTimeMillis())),
									DataSource.OPERATION_RESULT_SUCCESS, "DONE"));
					return 1;
				}
			}
		} catch (SQLException e) {
			DataSource.getInstance()
					.insertLog(new LogOperation(DataSource.OPERATION_DELETE, TABLE_ADDRESS,
							String.valueOf(new Timestamp(System.currentTimeMillis())),
							DataSource.OPERATION_RESULT_FAILED, "Could not delete Address"));
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
}
