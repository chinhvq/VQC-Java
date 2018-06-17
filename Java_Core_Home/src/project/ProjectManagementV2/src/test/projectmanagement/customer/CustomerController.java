package test.projectmanagement.customer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Window;
import test.projectmanagement.Main;
import test.projectmanagement.ProjectManagementUltility;
import test.projectmanagement.datamodel.Customer;
import test.projectmanagement.datasource.DataSourceCustomer;

public class CustomerController {
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
	@FXML
	private ContextMenu listContextMenu;

	@FXML
	public void initialize() {
		listShow();
		contextMenuAction();
	}

	private void contextMenuAction() {
		listContextMenu = new ContextMenu();
		MenuItem editMenuItem = new MenuItem("Edit");
		MenuItem deleteMenuItem = new MenuItem("Delete");

		editMenuItem.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Customer item = table.getSelectionModel().getSelectedItem();
				Main.CustomerID = item.getId();
				Main.customerName = item.getCustomerName();
				Main.businessType = item.getBusinessType();
				ProjectManagementUltility.getInstance().loadWindow("CustomerManagementEdit.fxml", "..\\application.css", "Customer Editing", false);
			}
		});

		deleteMenuItem.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Customer item = table.getSelectionModel().getSelectedItem();
				int customerID = item.getId();
				String customerName = item.getCustomerName();
				String businessType = item.getBusinessType();
				CustomerDeleteController controller = new CustomerDeleteController();
				controller.deleteAction(customerID, customerName, businessType);
				listShow();
			}
		});

		listContextMenu.getItems().addAll(editMenuItem, deleteMenuItem);

		table.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent t) {
				if (t.getButton() == MouseButton.SECONDARY) {
					listContextMenu.show(table, t.getScreenX(), t.getScreenY());
				}
			}
		});
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
		Task<ObservableList<Customer>> task = new ListCustomer();
		table.itemsProperty().bind(task.valueProperty());
		progressBar.progressProperty().bind(task.progressProperty());
		progressBar.setVisible(true);
		task.setOnSucceeded(e -> {
			progressBar.setVisible(false);
			progressLabel.setText("LOAD COMPLETED");
			countLabel.setText(
					"Number of return records = " + String.valueOf(DataSourceCustomer.getInstance().queryCustomerCount()));
		});
		task.setOnFailed(e -> {
			progressBar.setVisible(false);
			progressLabel.setText("LOADING FAILED");
		});
		new Thread(task).start();
	}

	@FXML
	public void addShow() {
		ProjectManagementUltility.getInstance().loadWindow("customer\\CustomerManagementAdd.fxml", "application.css", "Customer Adding", false);
	}

	@FXML
	public void editShow() {
		if (table.getSelectionModel().isEmpty()) {
			ProjectManagementUltility.getInstance().showAlert(AlertType.WARNING, "Project Editing", "Please select a Project to edit.\nTHANK YOU!");
		} else {
			Main.CustomerID = table.getSelectionModel().getSelectedItem().getId();
			Main.customerName = table.getSelectionModel().getSelectedItem().getCustomerName();
			Main.businessType = table.getSelectionModel().getSelectedItem().getBusinessType();
			ProjectManagementUltility.getInstance().loadWindow("customer\\CustomerManagementEdit.fxml", "application.css", "Customer Editing", false);
		}
	}

	@FXML
	public void deleteShow() {
		if (table.getSelectionModel().isEmpty()) {
			ProjectManagementUltility.getInstance().showAlert(AlertType.WARNING, "Customer Deleting", "Please select a Customer to delete.\nTHANK YOU!");
		} else {
			Main.CustomerID = table.getSelectionModel().getSelectedItem().getId();
			Main.customerName = table.getSelectionModel().getSelectedItem().getCustomerName();
			Main.businessType = table.getSelectionModel().getSelectedItem().getBusinessType();
			ProjectManagementUltility.getInstance().loadWindow("customer\\CustomerManagementDelete.fxml", "application.css", "Customer Deleting", false);
		}
	}

	@FXML
	public void searchShow() {
		ProjectManagementUltility.getInstance().loadWindow("customer\\CustomerManagementSearch.fxml", "application.css", "Customer Searching", false);
	}

}

class ListCustomer extends Task<ObservableList<Customer>> {
	@Override
	public ObservableList<Customer> call() throws Exception {
		return FXCollections.observableArrayList((DataSourceCustomer.getInstance().listAllCustomer()));
	}

}
