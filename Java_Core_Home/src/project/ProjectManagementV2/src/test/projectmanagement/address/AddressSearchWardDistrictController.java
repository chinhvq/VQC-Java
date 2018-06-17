package test.projectmanagement.address;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Window;
import test.projectmanagement.Main;
import test.projectmanagement.ProjectManagementUltility;
import test.projectmanagement.datamodel.Address;
import test.projectmanagement.datasource.DataSourceAddress;

public class AddressSearchWardDistrictController {
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
		Task<ObservableList<Address>> task = new SearchAddressWardDistrict();
		table.itemsProperty().bind(task.valueProperty());
		progressBar.progressProperty().bind(task.progressProperty());
		progressBar.setVisible(true);
		task.setOnSucceeded(e -> {
			progressBar.setVisible(false);
			progressLabel.setText("LOAD COMPLETED");
			countLabel.setText("Number of return records = " + String
					.valueOf(DataSourceAddress.getInstance().searchAddressWardDistrictCount(Main.ward, Main.district)));
		});
		task.setOnFailed(e -> {
			progressBar.setVisible(false);
			progressLabel.setText("LOADING FAILED ...");
		});
		new Thread(task).start();
	}
}

class SearchAddressWardDistrict extends Task<ObservableList<Address>> {
	@Override
	public ObservableList<Address> call() throws Exception {
		return FXCollections
				.observableArrayList((DataSourceAddress.getInstance().searchAddressWardDistrict(Main.ward, Main.district)));
	}
}
