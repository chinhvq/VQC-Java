package test.projectmanagement;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import test.projectmanagement.datasource.DataSource;
import test.projectmanagement.datasource.DataSourceAddress;
import test.projectmanagement.datasource.DataSourceCustomer;
import test.projectmanagement.datasource.DataSourceDepartment;
import test.projectmanagement.datasource.DataSourceEmployee;
import test.projectmanagement.datasource.DataSourceProject;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;

public class Main extends Application {
	public static int deptID;
	public static String deptName;
	public static int projectID;
	public static String projectName;
	public static String projectStatus;
	public static String projectCategory;
	public static int addressID;
	public static String ward;
	public static String district;
	public static String city;
	public static String country;
	public static int EmployeeID;
	public static String lastname;
	public static String firstName;
	public static String gender;
	public static String phonenumber;
	public static String email;
	public static int age;
	public static String title;
	public static int departmentID;
	public static int CustomerID;
	public static String customerName;
	public static String businessType;

	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("ProjectManagementSystem.fxml"));
			BorderPane root = loader.load();
			ProjectManagementSystemController controller = loader.getController();
			controller.viewCustomerProject();

			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Project Management System");
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void init() throws Exception {
		super.init();
		if (!DataSource.getInstance().open()) {
			System.out.println("Fatal Error - Cannot connect to DB");
			Platform.exit();
		}
		if (!DataSourceDepartment.getInstance().open()) {
			System.out.println("Fatal Error - Cannot connect to DB");
			Platform.exit();
		}
		if (!DataSourceAddress.getInstance().open()) {
			System.out.println("Fatal Error - Cannot connect to DB");
			Platform.exit();
		}
		if (!DataSourceCustomer.getInstance().open()) {
			System.out.println("Fatal Error - Cannot connect to DB");
			Platform.exit();
		}
		if (!DataSourceEmployee.getInstance().open()) {
			System.out.println("Fatal Error - Cannot connect to DB");
			Platform.exit();
		}
		if (!DataSourceProject.getInstance().open()) {
			System.out.println("Fatal Error - Cannot connect to DB");
			Platform.exit();
		}
	}

	@Override
	public void stop() throws Exception {
		super.stop();
		DataSource.getInstance().close();
		DataSourceDepartment.getInstance().close();
		DataSourceAddress.getInstance().close();
		DataSourceCustomer.getInstance().close();
		DataSourceEmployee.getInstance().close();
		DataSourceProject.getInstance().close();
	}
}
