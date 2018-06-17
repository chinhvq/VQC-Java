package test.projectmanagement.address;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Window;
import test.projectmanagement.Main;
import test.projectmanagement.ProjectManagementUltility;
import test.projectmanagement.datasource.DataSourceAddress;

public class AddressEditController {
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

		if (ward.isEmpty()) {
			ProjectManagementUltility.getInstance().showAlert(AlertType.WARNING, "Address Adding Warning",
					"Ward cannot be blank.\nPlease double check again.\n\tTHANK YOU!!!");
		} else if (district.isEmpty()) {
			ProjectManagementUltility.getInstance().showAlert(AlertType.WARNING, "Address Adding Warning",
					"District cannot be blank.\nPlease double check again.\n\tTHANK YOU!!!");
		} else if (country.length() > 3) {
			ProjectManagementUltility.getInstance().showAlert(AlertType.WARNING, "Address Adding Warning",
					"Country code is presented by 2 character.\n\tFor example \"VN\", \"US\", \"SG\", etc...\nPlease double check again.\n\tTHANK YOU!!!");
		} else if (!ward.isEmpty() && !district.isEmpty()) {
			if (DataSourceAddress.getInstance().updateAddress(Main.addressID, ward, district, city, country)) {
				ProjectManagementUltility.getInstance().showAlert(AlertType.INFORMATION, "Address Editing Successfully",
						"Address has been updated successfully into DB with bellowing information.\n\tAddress ID = "
								+ addressID + "\n\tWard = " + ward + "\n\tDistrict = " + district + "\n\tCity = " + city
								+ "\n\tCountry = " + country);
				close();
			} else {
				ProjectManagementUltility.getInstance().showAlert(AlertType.ERROR, "Address Editing Failure",
						"Address Editing has not been successfully.\nPlease check again.\n\tTHANK YOU!!!");
			}
		}
	}

	@FXML
	public void CancelAction() {
		wardText.setText("");
		districtText.setText("");
		cityText.setText("");
		countryText.setText("");

		wardText.setPromptText("Nhập lại Phường / Xã");
		districtText.setPromptText("Nhập lại Quận / Huyện");
		cityText.setPromptText("Nhập lại Thành Phố / Tỉnh");
		countryText.setPromptText("Nhập lại tên Quốc Gia");
	}

}
