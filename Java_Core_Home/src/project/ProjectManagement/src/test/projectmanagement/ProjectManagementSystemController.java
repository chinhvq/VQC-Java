package test.projectmanagement;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import test.projectmanagement.datamodel.DataSource;
import test.projectmanagement.datamodel.ProjectCustomer;

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
		editButton.setDisable(true);
		deleteButton.setDisable(true);
	}

	@FXML
	public void quit() {
		Platform.exit();
	}

	private void loadWindow(String fxmlFile, String cssFile, String windowTitle, boolean isResizable) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFile));
			Parent root = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setTitle(windowTitle);
			Scene scence = new Scene(root);
			scence.getStylesheets().add(getClass().getResource(cssFile).toExternalForm());
			stage.setScene(scence);
			stage.setResizable(isResizable);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void aboutShow() {
		loadWindow("AboutDialog.fxml", "application.css", "About", false);
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
		loadWindow("customer\\CustomerManagement.fxml", "application.css", "Customer Management", true);
	}

	@FXML
	public void listProject() {
		loadWindow("project\\ProjectManagement.fxml", "application.css", "Project Management", true);
	}

	@FXML
	public void listEmployee() {
		loadWindow("employee\\EmployeeManagement.fxml", "application.css", "Employee Management", true);
	}

	@FXML
	public void listDepartment() {
		loadWindow("department\\DepartmentManagement.fxml", "application.css", "Department Management", true);
	}

	@FXML
	public void listAddress() {
		loadWindow("address\\AddressManagement.fxml", "application.css", "Address Management", true);
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
		loadWindow("customer\\CustomerManagementAdd.fxml", "application.css", "Customer Adding", false);
	}

	@FXML
	public void addProject() {
		loadWindow("project\\ProjectManagementAdd.fxml", "application.css", "Project Adding", false);
	}

	@FXML
	public void addEmployee() {
		loadWindow("employee\\EmployeeManagementAdd.fxml", "application.css", "Employee Adding", false);
	}

	@FXML
	public void addDepartment() {
		loadWindow("department\\DepartmentManagementAdd.fxml", "application.css", "Department Adding", false);
	}

	@FXML
	public void addAddress() {
		loadWindow("address\\AddressManagementAdd.fxml", "application.css", "Address Adding", false);
	}

	@FXML
	public void editTable() {
		String tableName = tableTab.selectionModelProperty().getValue().getSelectedItem().getText();
		if (tableName.equals("CUSTOMER")) {
			editCustomer();
		} else if (tableName.equals("PROJECT")) {
			editProject();
		} else if (tableName.equals("EMPLOYEE")) {
			editEmployee();
		} else if (tableName.equals("DEPARTMENT")) {
			editDepartment();
		} else if (tableName.equals("ADDRESS")) {
			editAddress();
		}
	}

	@FXML
	public void editCustomer() {
	}

	@FXML
	public void editProject() {
	}

	@FXML
	public void editEmployee() {
	}

	@FXML
	public void editDepartment() {
	}

	@FXML
	public void editAddress() {
	}

	@FXML
	public void deleteTable() {
		String tableName = tableTab.selectionModelProperty().getValue().getSelectedItem().getText();
		if (tableName.equals("CUSTOMER")) {
			deleteCustomer();
		} else if (tableName.equals("PROJECT")) {
			deleteProject();
		} else if (tableName.equals("EMPLOYEE")) {
			deleteEmployee();
		} else if (tableName.equals("DEPARTMENT")) {
			deleteDepartment();
		} else if (tableName.equals("ADDRESS")) {
			deleteAddress();
		}
	}

	@FXML
	public void deleteCustomer() {
	}

	@FXML
	public void deleteProject() {
	}

	@FXML
	public void deleteEmployee() {
	}

	@FXML
	public void deleteDepartment() {
	}

	@FXML
	public void deleteAddress() {
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
		loadWindow("customer\\CustomerManagementSearch.fxml", "application.css", "Customer Searching", false);
	}

	@FXML
	public void searchProject() {
		loadWindow("project\\ProjectManagementSearch.fxml", "application.css", "Project Searching", false);
	}

	@FXML
	public void searchEmployee() {
		loadWindow("employee\\EmployeeManagementSearch.fxml", "application.css", "Employee Searching", false);
	}

	@FXML
	public void searchDepartment() {
		loadWindow("department\\DepartmentManagementSearch.fxml", "application.css", "Department Searching", false);
	}

	@FXML
	public void searchAddress() {
		loadWindow("address\\AddressManagementSearch.fxml", "application.css", "Address Searching", false);

	}

	@FXML
	public void logShow() {
		loadWindow("log\\LogManagement.fxml", "application.css", "Log Management", false);
	}
}

class ViewCustomerProject extends Task<ObservableList<ProjectCustomer>> {
	@Override
	public ObservableList<ProjectCustomer> call() throws Exception {
		return FXCollections.observableArrayList((DataSource.getInstance().viewCustomerProject()));
	}
}