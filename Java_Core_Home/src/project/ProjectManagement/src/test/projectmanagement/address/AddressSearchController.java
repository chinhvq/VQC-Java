package test.projectmanagement.address;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import test.projectmanagement.Main;

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

	private void showAlert(AlertType alertType, String alertTitle, String alertContext) {
		Alert alertInfo = new Alert(alertType);
		alertInfo.setContentText(alertContext);
		alertInfo.setTitle(alertTitle);
		alertInfo.show();
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
		loadWindow("..\\AboutDialog.fxml", "..\\application.css", "About", false);
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
					showAlert(AlertType.WARNING, "Address Searching Warning", "Address ID cannot be equal to 0");
				} else {
					loadWindow("AddressManagementSearchAddressID.fxml", "..\\application.css",
							"Address Searching Address ID Result", false);
				}
			} catch (NumberFormatException e) {
				showAlert(AlertType.WARNING, "Address Searching Warning",
						"Please check again Address ID.\nAddress ID can not be a string.\nAddress ID should be an integer.\n\tTHANK YOU!!!");
			}
		} else if (addressID.isEmpty() && !ward.isEmpty() && district.isEmpty() && city.isEmpty()
				&& country.isEmpty()) {
			Main.ward = ward;
			loadWindow("AddressManagementSearchWard.fxml", "..\\application.css",
					"Address Searching Address Ward Result", false);
		} else if (addressID.isEmpty() && ward.isEmpty() && !district.isEmpty() && city.isEmpty()
				&& country.isEmpty()) {
			Main.district = district;
			loadWindow("AddressManagementSearchDistrict.fxml", "..\\application.css",
					"Address Searching Address District Result", false);
		} else if (addressID.isEmpty() && ward.isEmpty() && district.isEmpty() && !city.isEmpty()
				&& country.isEmpty()) {
			Main.city = city;
			loadWindow("AddressManagementSearchCity.fxml", "..\\application.css",
					"Address Searching Address City Result", false);
		} else if (addressID.isEmpty() && ward.isEmpty() && district.isEmpty() && city.isEmpty()
				&& !country.isEmpty()) {
			Main.country = country;
			loadWindow("AddressManagementSearchCountry.fxml", "..\\application.css",
					"Address Searching Address Country Result", false);
		} else if (addressID.isEmpty() && !ward.isEmpty() && !district.isEmpty() && city.isEmpty()
				&& country.isEmpty()) {
			Main.ward = ward;
			Main.district = district;
			loadWindow("AddressManagementSearchWardDistrict.fxml", "..\\application.css",
					"Address Searching Address Ward && District Result", false);
		} else if (addressID.isEmpty() && !ward.isEmpty() && district.isEmpty() && !city.isEmpty()
				&& country.isEmpty()) {
			Main.ward = ward;
			Main.city = city;
			loadWindow("AddressManagementSearchWardCity.fxml", "..\\application.css",
					"Address Searching Address Ward & City Result", false);
		} else if (addressID.isEmpty() && !ward.isEmpty() && district.isEmpty() && city.isEmpty()
				&& !country.isEmpty()) {
			Main.ward = ward;
			Main.country = country;
			loadWindow("AddressManagementSearchWardCountry.fxml", "..\\application.css",
					"Address Searching Address Ward & Country Result", false);
		} else if (addressID.isEmpty() && ward.isEmpty() && district.isEmpty() && !city.isEmpty()
				&& !country.isEmpty()) {
			Main.city = city;
			Main.country = country;
			loadWindow("AddressManagementSearchCityCountry.fxml", "..\\application.css",
					"Address Searching Address City & Country Result", false);
		} else if (addressID.isEmpty() && ward.isEmpty() && district.isEmpty() && city.isEmpty() && country.isEmpty()) {
			showAlert(AlertType.INFORMATION, "Address Searching Information",
					"Please input address infomration to input.\n\tTHANK YOU!!!");
		} else {
			showAlert(AlertType.INFORMATION, "Address Searching Information",
					"The combination of searching criteria will be support soon.\n\tTHANK YOU!!!");
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
		countryText.setPromptText("Nhập lại Quốc Gia");
	}

}
