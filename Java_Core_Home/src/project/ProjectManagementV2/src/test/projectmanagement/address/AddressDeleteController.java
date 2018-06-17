package test.projectmanagement.address;

import java.util.Optional;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Window;
import test.projectmanagement.Main;
import test.projectmanagement.ProjectManagementUltility;
import test.projectmanagement.datasource.DataSourceAddress;

public class AddressDeleteController {
	@FXML
	BorderPane mainPane;
	@FXML
	private TextField idText;
	@FXML
	private TextField wardText;
	@FXML
	private TextField districtText;
	@FXML
	private TextField cityText;
	@FXML
	private TextField countryText;

	public void initialize() {
		idText.setText(String.valueOf(Main.addressID));
		wardText.setText(Main.ward);
		districtText.setText(Main.district);
		cityText.setText(Main.city);
		countryText.setText(Main.country);
		idText.setDisable(true);
		wardText.setDisable(true);
		districtText.setDisable(true);
		cityText.setDisable(true);
		countryText.setDisable(true);
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
	public void OkAction() {
		String addressID = idText.getText();
		String ward = wardText.getText();
		String district = districtText.getText();
		String city = cityText.getText();
		String country = countryText.getText();
		deleteAction(Integer.parseInt(addressID), ward, district, city, country);
	}

	public void deleteAction(int addressID, String ward, String district, String city, String country) {
		Alert alertConfirmation = new Alert(Alert.AlertType.CONFIRMATION);
		alertConfirmation.setContentText("Are you sure to delete address with bellowing information:\n\tAddress ID = "
				+ addressID + "\n\tWard = " + ward + "\n\tDistrict = " + district + "\n\tCity = " + city
				+ "\n\tCountry = " + country);
		alertConfirmation.setTitle("Address Deleting Confirmation");
		alertConfirmation.setHeaderText("Alert Information");
		Optional<ButtonType> result = alertConfirmation.showAndWait();

		if (result.get().getButtonData() == ButtonBar.ButtonData.OK_DONE) {
			int deleteResult = DataSourceAddress.getInstance().deleteAddress(addressID);
			if (deleteResult == 1) {
				ProjectManagementUltility.getInstance().showAlert(AlertType.INFORMATION, "Address Deleting Succesfully",
						"Address ID " + addressID + " has been delete successfully from DB");
			} else if (deleteResult == 2) {
				ProjectManagementUltility.getInstance().showAlert(AlertType.ERROR, "Address Deleting Failure",
						"Address ID " + addressID
								+ " has a reference with another customer.\nCannot delete the address.\nPlease double check again.\n\tTHANK YOU!!!");
			} else if (deleteResult == 3) {
				ProjectManagementUltility.getInstance().showAlert(AlertType.ERROR, "Address Deleting Failure",
						"Address ID " + addressID
								+ " has a reference with another employee.\nCannot delete the address.\nPlease double check again.\n\tTHANK YOU!!!");
			} else {
				ProjectManagementUltility.getInstance().showAlert(AlertType.ERROR, "Address Deleting Failure",
						"Deleting Address ID " + addressID
								+ " has not been successfully.\nPlease double check again.\n\tTHANK YOU!!!");
			}
		} else {
			ProjectManagementUltility.getInstance().showAlert(AlertType.INFORMATION, "Address Deleting Information",
					"You have cancelled the deleting operation.\nTHANK YOU!!!");
		}
	}

	@FXML
	public void CancelAction() {
		close();
	}

}
