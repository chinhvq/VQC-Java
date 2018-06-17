package test.projectmanagement.address;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Window;
import test.projectmanagement.Main;
import test.projectmanagement.ProjectManagementUltility;

public class AddressSearchController {
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

		if (!addressID.isEmpty() && ward.isEmpty() && district.isEmpty() && city.isEmpty() && country.isEmpty()) {
			try {
				Main.addressID = Integer.parseInt(addressID);
				if (Main.addressID == 0) {
					ProjectManagementUltility.getInstance().showAlert(AlertType.WARNING, "Address Searching Warning",
							"Address ID cannot be equal to 0");
				} else {
					ProjectManagementUltility.getInstance().loadWindow("address\\AddressManagementSearchAddressID.fxml",
							"application.css", "Address Searching Address ID Result", false);
				}
			} catch (NumberFormatException e) {
				ProjectManagementUltility.getInstance().showAlert(AlertType.WARNING, "Address Searching Warning",
						"Please check again Address ID.\nAddress ID can not be a string.\nAddress ID should be an integer.\n\tTHANK YOU!!!");
			}
		} else if (addressID.isEmpty() && !ward.isEmpty() && district.isEmpty() && city.isEmpty()
				&& country.isEmpty()) {
			Main.ward = ward;
			ProjectManagementUltility.getInstance().loadWindow("address\\AddressManagementSearchWard.fxml",
					"application.css", "Address Searching Address Ward Result", false);
		} else if (addressID.isEmpty() && ward.isEmpty() && !district.isEmpty() && city.isEmpty()
				&& country.isEmpty()) {
			Main.district = district;
			ProjectManagementUltility.getInstance().loadWindow("address\\AddressManagementSearchDistrict.fxml",
					"application.css", "Address Searching Address District Result", false);
		} else if (addressID.isEmpty() && ward.isEmpty() && district.isEmpty() && !city.isEmpty()
				&& country.isEmpty()) {
			Main.city = city;
			ProjectManagementUltility.getInstance().loadWindow("address\\AddressManagementSearchCity.fxml",
					"application.css", "Address Searching Address City Result", false);
		} else if (addressID.isEmpty() && ward.isEmpty() && district.isEmpty() && city.isEmpty()
				&& !country.isEmpty()) {
			Main.country = country;
			ProjectManagementUltility.getInstance().loadWindow("address\\AddressManagementSearchCountry.fxml",
					"application.css", "Address Searching Address Country Result", false);
		} else if (addressID.isEmpty() && !ward.isEmpty() && !district.isEmpty() && city.isEmpty()
				&& country.isEmpty()) {
			Main.ward = ward;
			Main.district = district;
			ProjectManagementUltility.getInstance().loadWindow("address\\AddressManagementSearchWardDistrict.fxml",
					"application.css", "Address Searching Address Ward && District Result", false);
		} else if (addressID.isEmpty() && !ward.isEmpty() && district.isEmpty() && !city.isEmpty()
				&& country.isEmpty()) {
			Main.ward = ward;
			Main.city = city;
			ProjectManagementUltility.getInstance().loadWindow("address\\AddressManagementSearchWardCity.fxml",
					"application.css", "Address Searching Address Ward & City Result", false);
		} else if (addressID.isEmpty() && !ward.isEmpty() && district.isEmpty() && city.isEmpty()
				&& !country.isEmpty()) {
			Main.ward = ward;
			Main.country = country;
			ProjectManagementUltility.getInstance().loadWindow("address\\AddressManagementSearchWardCountry.fxml",
					"application.css", "Address Searching Address Ward & Country Result", false);
		} else if (addressID.isEmpty() && ward.isEmpty() && district.isEmpty() && !city.isEmpty()
				&& !country.isEmpty()) {
			Main.city = city;
			Main.country = country;
			ProjectManagementUltility.getInstance().loadWindow("address\\AddressManagementSearchCityCountry.fxml",
					"application.css", "Address Searching Address City & Country Result", false);
		} else if (addressID.isEmpty() && ward.isEmpty() && district.isEmpty() && city.isEmpty() && country.isEmpty()) {
			ProjectManagementUltility.getInstance().showAlert(AlertType.INFORMATION, "Address Searching Information",
					"Please input address infomration to input.\n\tTHANK YOU!!!");
		} else {
			ProjectManagementUltility.getInstance().showAlert(AlertType.INFORMATION, "Address Searching Information",
					"The combination of searching criteria will be support soon.\n\tTHANK YOU!!!");
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
