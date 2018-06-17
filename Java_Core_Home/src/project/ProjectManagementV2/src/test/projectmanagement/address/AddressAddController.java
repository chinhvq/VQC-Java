package test.projectmanagement.address;

import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Window;
import test.projectmanagement.ProjectManagementUltility;
import test.projectmanagement.datasource.DataSourceAddress;

public class AddressAddController {
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

		try {
			if (ward.isEmpty()) {
				ProjectManagementUltility.getInstance().showAlert(AlertType.WARNING, "Address Adding Warning",
						"Ward cannot be blank.\nPlease double check again.\n\tTHANK YOU!!!");
			} else if (district.isEmpty()) {
				ProjectManagementUltility.getInstance().showAlert(AlertType.WARNING, "Address Adding Warning",
						"District cannot be blank.\nPlease double check again.\n\tTHANK YOU!!!");
			} else if (country.length() > 3) {
				ProjectManagementUltility.getInstance().showAlert(AlertType.WARNING, "Address Adding Warning",
						"Country code is presented by 2 character.\n\tFor example \"VN\", \"US\", \"SG\", etc...\nPlease double check again.\n\tTHANK YOU!!!");
			} else if (addressID.isEmpty() && !ward.isEmpty() && !district.isEmpty()) {
				int result = DataSourceAddress.getInstance().insertAddress(ward, district, city, country);
				if (result != 0) {
					ProjectManagementUltility.getInstance().showAlert(AlertType.INFORMATION,
							"Address Adding Successfully",
							"New Address has been added into DB with bellowing information.\n\tAddress ID = " + result
									+ "\n\tWard = " + ward + "\n\tDistrict = " + district + "\n\tCity = " + city
									+ "\n\tCountry = " + country);
				}
			} else if (!addressID.isEmpty() && !ward.isEmpty() && !district.isEmpty()) {
				if (Integer.parseInt(addressID) == 0) {
					ProjectManagementUltility.getInstance().showAlert(AlertType.WARNING, "Address Adding Warning",
							"Address ID cannot be equal to 0.\nPlease double check again.\n\tTHANK YOU!!!");
				} else {
					if (DataSourceAddress.getInstance().insertAddressID(Integer.parseInt(addressID), ward, district,
							city, country)) {
						ProjectManagementUltility.getInstance().showAlert(AlertType.INFORMATION,
								"Address Adding Successfully",
								"New Address has been added into DB with bellowing information.\n\tAddress ID = "
										+ addressID + "\n\tWard = " + ward + "\n\tDistrict = " + district
										+ "\n\tCity = " + city + "\n\tCountry = " + country);
					} else {
						ProjectManagementUltility.getInstance().showAlert(AlertType.WARNING, "Address Adding Warning",
								"Address ID " + addressID
										+ " has been exist in DB.\nCannot add address information into DB.\n\tTHANK YOU!!!");
					}
				}
			}
		} catch (NumberFormatException e) {
			ProjectManagementUltility.getInstance().showAlert(AlertType.WARNING, "Address Adding Warning",
					"Address ID cannot be a string.\nAddress ID should be an interger.\n\tTHANK YOU!!!");
		} catch (SQLException e) {
			ProjectManagementUltility.getInstance().showAlert(AlertType.ERROR, "Address Adding Failure",
					"Cannot add the address information into DB.\n\tTHANK YOU!!!");
		}
	}

	@FXML
	public void CancelAction() {
		idText.setText("");
		wardText.setText("");
		districtText.setText("");
		cityText.setText("");
		countryText.setText("");

		idText.setPromptText("Nhập lại Address ID");
		wardText.setPromptText("Nhập lại Phường / Xã");
		districtText.setPromptText("Nhập lại Quận / Huyện");
		cityText.setPromptText("Nhập lại Thành Phố / Tỉnh");
		countryText.setPromptText("Nhập lại tên Quốc Gia");
	}

}
