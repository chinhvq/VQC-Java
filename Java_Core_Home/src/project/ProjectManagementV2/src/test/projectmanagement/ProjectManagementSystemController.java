package test.projectmanagement;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import test.projectmanagement.datamodel.ProjectCustomer;
import test.projectmanagement.datasource.DataSource;

public class ProjectManagementSystemController {
	@FXML
	private ProgressBar progressBar;
	@FXML
	private TableView<ProjectCustomer> table;
	@FXML
	private Label progressLabel;
	@FXML
	private Label countLabel;
	@FXML
	private Button editButton;
	@FXML
	private Button deleteButton;
	@FXML
	private TabPane tableTab;
	@FXML
	private TableColumn<ProjectCustomer, String> customerName;
	@FXML
	private TableColumn<ProjectCustomer, String> businessType;
	@FXML
	private TableColumn<ProjectCustomer, String> projectName;

	public void initialize() {
	}

	@FXML
	public void quit() {
		Platform.exit();
	}

	@FXML
	public void aboutShow() {
		ProjectManagementUltility.getInstance().loadWindow("AboutDialog.fxml", "application.css", "About", false);
	}

	@FXML
	public void viewCustomerProject() {
		Task<ObservableList<ProjectCustomer>> task = new ViewCustomerProject();
		table.itemsProperty().bind(task.valueProperty());
		progressBar.progressProperty().bind(task.progressProperty());
		progressBar.setVisible(true);
		task.setOnSucceeded(e -> {
			progressBar.setVisible(false);
			progressLabel.setText("LOAD COMPLETED");
			countLabel.setText("Number of return records = "
					+ String.valueOf(DataSource.getInstance().viewCustomerProjectCount()));
		});
		task.setOnFailed(e -> {
			progressBar.setVisible(false);
			progressLabel.setText("LOADING ...");
		});
		new Thread(task).start();
	}

	@FXML
	public void listTable() {
		progressLabel.setText("");
		countLabel.setText("");
		String tableName = tableTab.selectionModelProperty().getValue().getSelectedItem().getText();
		if (tableName.equals("CUSTOMER")) {
			listCustomer();
		} else if (tableName.equals("PROJECT")) {
			listProject();
		} else if (tableName.equals("EMPLOYEE")) {
			listEmployee();
		} else if (tableName.equals("DEPARTMENT")) {
			listDepartment();
		} else if (tableName.equals("ADDRESS")) {
			listAddress();
		}
	}

	@FXML
	public void listCustomer() {
		ProjectManagementUltility.getInstance().loadWindow("customer\\CustomerManagement.fxml", "application.css",
				"Customer Management", true);
	}

	@FXML
	public void listProject() {
		ProjectManagementUltility.getInstance().loadWindow("project\\ProjectManagement.fxml", "application.css",
				"Project Management", true);
	}

	@FXML
	public void listEmployee() {
		ProjectManagementUltility.getInstance().loadWindow("employee\\EmployeeManagement.fxml", "application.css",
				"Employee Management", true);
	}

	@FXML
	public void listDepartment() {
		ProjectManagementUltility.getInstance().loadWindow("department\\DepartmentManagement.fxml", "application.css",
				"Department Management", true);
	}

	@FXML
	public void listAddress() {
		ProjectManagementUltility.getInstance().loadWindow("address\\AddressManagement.fxml", "application.css",
				"Address Management", true);
	}

	@FXML
	public void addTable() {
		String tableName = tableTab.selectionModelProperty().getValue().getSelectedItem().getText();
		if (tableName.equals("CUSTOMER")) {
			addCustomer();
		} else if (tableName.equals("PROJECT")) {
			addProject();
		} else if (tableName.equals("EMPLOYEE")) {
			addEmployee();
		} else if (tableName.equals("DEPARTMENT")) {
			addDepartment();
		} else if (tableName.equals("ADDRESS")) {
			addAddress();
		}
	}

	@FXML
	public void addCustomer() {
		ProjectManagementUltility.getInstance().loadWindow("customer\\CustomerManagementAdd.fxml", "application.css",
				"Customer Adding", false);
	}

	@FXML
	public void addProject() {
		ProjectManagementUltility.getInstance().loadWindow("project\\ProjectManagementAdd.fxml", "application.css",
				"Project Adding", false);
	}

	@FXML
	public void addEmployee() {
		ProjectManagementUltility.getInstance().loadWindow("employee\\EmployeeManagementAdd.fxml", "application.css",
				"Employee Adding", false);
	}

	@FXML
	public void addDepartment() {
		ProjectManagementUltility.getInstance().loadWindow("department\\DepartmentManagementAdd.fxml",
				"application.css", "Department Adding", false);
	}

	@FXML
	public void addAddress() {
		ProjectManagementUltility.getInstance().loadWindow("address\\AddressManagementAdd.fxml", "application.css",
				"Address Adding", false);
	}

	@FXML
	public void searchTable() {
		String tableName = tableTab.selectionModelProperty().getValue().getSelectedItem().getText();
		if (tableName.equals("CUSTOMER")) {
			searchCustomer();
		} else if (tableName.equals("PROJECT")) {
			searchProject();
		} else if (tableName.equals("EMPLOYEE")) {
			searchEmployee();
		} else if (tableName.equals("DEPARTMENT")) {
			searchDepartment();
		} else if (tableName.equals("ADDRESS")) {
			searchAddress();
		}
	}

	@FXML
	public void searchCustomer() {
		ProjectManagementUltility.getInstance().loadWindow("customer\\CustomerManagementSearch.fxml", "application.css",
				"Customer Searching", false);
	}

	@FXML
	public void searchProject() {
		ProjectManagementUltility.getInstance().loadWindow("project\\ProjectManagementSearch.fxml", "application.css",
				"Project Searching", false);
	}

	@FXML
	public void searchEmployee() {
		ProjectManagementUltility.getInstance().loadWindow("employee\\EmployeeManagementSearch.fxml", "application.css",
				"Employee Searching", false);
	}

	@FXML
	public void searchDepartment() {
		ProjectManagementUltility.getInstance().loadWindow("department\\DepartmentManagementSearch.fxml",
				"application.css", "Department Searching", false);
	}

	@FXML
	public void searchAddress() {
		ProjectManagementUltility.getInstance().loadWindow("address\\AddressManagementSearch.fxml", "application.css",
				"Address Searching", false);

	}

	@FXML
	public void logShow() {
		ProjectManagementUltility.getInstance().loadWindow("log\\LogManagement.fxml", "application.css",
				"Log Management", false);
	}
}

class ViewCustomerProject extends Task<ObservableList<ProjectCustomer>> {
	@Override
	public ObservableList<ProjectCustomer> call() throws Exception {
		return FXCollections.observableArrayList((DataSource.getInstance().viewCustomerProject()));
	}
}