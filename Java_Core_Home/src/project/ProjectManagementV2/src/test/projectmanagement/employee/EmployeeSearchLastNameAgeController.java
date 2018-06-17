package test.projectmanagement.employee;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Window;
import test.projectmanagement.Main;
import test.projectmanagement.ProjectManagementUltility;
import test.projectmanagement.datamodel.Employee;
import test.projectmanagement.datasource.DataSourceEmployee;

public class EmployeeSearchLastNameAgeController {
	@FXML
	BorderPane mainPane;
	@FXML
	private ProgressBar progressBar;
	@FXML
	private TableView<Employee> table;
	@FXML
	private Label progressLabel;
	@FXML
	private Label countLabel;
	@FXML
	private TableColumn<Employee, String> firstName;
	@FXML
	private TableColumn<Employee, String> lastName;
	@FXML
	private TableColumn<Employee, String> email;

	public void initialize() {
		listShow();
	}

	@FXML
	public void close() {
		Window window = mainPane.getScene().getWindow();
		window.hide();
	}

	@FXML
	public void aboutShow() {
		ProjectManagementUltility.getInstance().loadWindow("AboutDialog.fxml", "application.css", "About", false);
	}

	@FXML
	public void listShow() {
		progressLabel.setText("LOADING ...");
		countLabel.setText("");
		Task<ObservableList<Employee>> task = new SearchEmployeeLastNameAge();
		table.itemsProperty().bind(task.valueProperty());
		progressBar.progressProperty().bind(task.progressProperty());
		progressBar.setVisible(true);
		task.setOnSucceeded(e -> {
			progressBar.setVisible(false);
			progressLabel.setText("LOAD COMPLETED");
			countLabel.setText("Number of return records = "
					+ String.valueOf(DataSourceEmployee.getInstance().searchEmployeeLastNameAgeCount(Main.lastname, Main.age)));
		});
		task.setOnFailed(e -> {
			progressBar.setVisible(false);
			progressLabel.setText("LOADING FAILED ...");
		});
		new Thread(task).start();
	}
}

class SearchEmployeeLastNameAge extends Task<ObservableList<Employee>> {
	@Override
	public ObservableList<Employee> call() throws Exception {
		return FXCollections
				.observableArrayList((DataSourceEmployee.getInstance().searchEmployeeLastNameAge(Main.lastname, Main.age)));
	}
}
