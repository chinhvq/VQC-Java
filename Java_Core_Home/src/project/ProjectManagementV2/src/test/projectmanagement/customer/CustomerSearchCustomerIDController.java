package test.projectmanagement.customer;

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
import test.projectmanagement.datamodel.Customer;
import test.projectmanagement.datasource.DataSourceCustomer;

public class CustomerSearchCustomerIDController {
	@FXML
	BorderPane mainPane;
	@FXML
	private ProgressBar progressBar;
	@FXML
	private TableView<Customer> table;
	@FXML
	private Label progressLabel;
	@FXML
	private Label countLabel;
	@FXML
	private TableColumn<Customer, String> customerName;
	@FXML
	private TableColumn<Customer, String> businessType;

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
		Task<ObservableList<Customer>> task = new SearchCustomerID();
		table.itemsProperty().bind(task.valueProperty());
		progressBar.progressProperty().bind(task.progressProperty());
		progressBar.setVisible(true);
		task.setOnSucceeded(e -> {
			progressBar.setVisible(false);
			progressLabel.setText("LOAD COMPLETED");
			countLabel.setText("Number of return records = "
					+ String.valueOf(DataSourceCustomer.getInstance().searchCustomerIDCount(Main.CustomerID)));
		});
		task.setOnFailed(e -> {
			progressBar.setVisible(false);
			progressLabel.setText("LOADING FAILED ...");
		});
		new Thread(task).start();
	}
}

class SearchCustomerID extends Task<ObservableList<Customer>> {
	@Override
	public ObservableList<Customer> call() throws Exception {
		return FXCollections.observableArrayList((DataSourceCustomer.getInstance().searchCustomerID(Main.CustomerID)));
	}
}
