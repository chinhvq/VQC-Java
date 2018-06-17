package test.projectmanagement.address;

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
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Window;
import test.projectmanagement.Main;
import test.projectmanagement.ProjectManagementUltility;
import test.projectmanagement.datamodel.Address;
import test.projectmanagement.datasource.DataSourceAddress;

public class AddressController {
	@FXML
	BorderPane mainPane;
	@FXML
	private ProgressBar progressBar;
	@FXML
	private TableView<Address> table;
	@FXML
	private Label progressLabel;
	@FXML
	private Label countLabel;
	@FXML
	private ContextMenu listContextMenu;

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
				Address item = table.getSelectionModel().getSelectedItem();
				Main.addressID = item.getId();
				Main.ward = item.getWard();
				Main.district = item.getDistrict();
				Main.city = item.getCity();
				Main.country = item.getCountry();
				ProjectManagementUltility.getInstance().loadWindow("address\\AddressManagementEdit.fxml",
						"application.css", "Address Editing", false);
			}
		});

		deleteMenuItem.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Address item = table.getSelectionModel().getSelectedItem();
				int addressID = item.getId();
				String ward = item.getWard();
				String district = item.getDistrict();
				String city = item.getCity();
				String country = item.getCountry();
				AddressDeleteController controller = new AddressDeleteController();
				controller.deleteAction(addressID, ward, district, city, country);
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
		Task<ObservableList<Address>> task = new ListAddress();
		table.itemsProperty().bind(task.valueProperty());
		progressBar.progressProperty().bind(task.progressProperty());
		progressBar.setVisible(true);
		task.setOnSucceeded(e -> {
			progressBar.setVisible(false);
			progressLabel.setText("LOAD COMPLETED");
			countLabel.setText("Number of return records = "
					+ String.valueOf(DataSourceAddress.getInstance().queryAddressCount()));
		});
		task.setOnFailed(e -> {
			progressBar.setVisible(false);
			progressLabel.setText("LOADING FAILED");
		});
		new Thread(task).start();
	}

	@FXML
	public void addShow() {
		ProjectManagementUltility.getInstance().loadWindow("address\\AddressManagementAdd.fxml", "application.css",
				"Address Adding", false);
	}

	@FXML
	public void editShow() {
		if (table.getSelectionModel().isEmpty()) {
			ProjectManagementUltility.getInstance().showAlert(AlertType.WARNING, "Address Editing",
					"Please select an Address to edit.\nTHANK YOU!");
		} else {
			Main.addressID = table.getSelectionModel().getSelectedItem().getId();
			Main.ward = table.getSelectionModel().getSelectedItem().getWard();
			Main.district = table.getSelectionModel().getSelectedItem().getDistrict();
			Main.city = table.getSelectionModel().getSelectedItem().getCity();
			Main.country = table.getSelectionModel().getSelectedItem().getCountry();
			ProjectManagementUltility.getInstance().loadWindow("address\\AddressManagementEdit.fxml", "application.css",
					"Address Editing", false);
		}
	}

	@FXML
	public void deleteShow() {
		if (table.getSelectionModel().isEmpty()) {
			ProjectManagementUltility.getInstance().showAlert(AlertType.WARNING, "Address Deleting",
					"Please select an Address to delete.\nTHANK YOU!");
		} else {
			Main.addressID = table.getSelectionModel().getSelectedItem().getId();
			Main.ward = table.getSelectionModel().getSelectedItem().getWard();
			Main.district = table.getSelectionModel().getSelectedItem().getDistrict();
			Main.city = table.getSelectionModel().getSelectedItem().getCity();
			Main.country = table.getSelectionModel().getSelectedItem().getCountry();
			ProjectManagementUltility.getInstance().loadWindow("address\\AddressManagementDelete.fxml",
					"application.css", "Address Deleting", false);
		}
	}

	@FXML
	public void searchShow() {
		ProjectManagementUltility.getInstance().loadWindow("address\\AddressManagementSearch.fxml", "application.css",
				"Address Searching", false);
	}

}

class ListAddress extends Task<ObservableList<Address>> {
	@Override
	public ObservableList<Address> call() throws Exception {
		return FXCollections.observableArrayList((DataSourceAddress.getInstance().listAllAddress()));
	}

}
