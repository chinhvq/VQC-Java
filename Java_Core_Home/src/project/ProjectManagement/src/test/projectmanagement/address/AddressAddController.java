package test.projectmanagement.address;

import java.sql.SQLException;

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
import test.projectmanagement.datamodel.DataSource;

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

		try {
			if (ward.isEmpty()) {
				showAlert(AlertType.WARNING, "Address Adding Warning",
						"Ward cannot be blank.\nPlease double check again.\n\tTHANK YOU!!!");
			} else if (district.isEmpty()) {
				showAlert(AlertType.WARNING, "Address Adding Warning",
						"District cannot be blank.\nPlease double check again.\n\tTHANK YOU!!!");
			} else if (country.length() > 3) {
				showAlert(AlertType.WARNING, "Address Adding Warning",
						"Country code is presented by 2 character.\n\tFor example \"VN\", \"US\", \"SG\", etc...\nPlease double check again.\n\tTHANK YOU!!!");
			} else if (addressID.isEmpty() && !ward.isEmpty() && !district.isEmpty()) {
				int result = DataSource.getInstance().insertAddress(ward, district, city, country);
				if (result != 0) {
					showAlert(AlertType.INFORMATION, "Address Adding Successfully",
							"New Address has been added into DB with bellowing information.\n\tAddress ID = " + result
									+ "\n\tWard = " + ward + "\n\tDistrict = " + district + "\n\tCity = " + city
									+ "\n\tCountry = " + country);
				}
			} else if (!addressID.isEmpty() && !ward.isEmpty() && !district.isEmpty()) {
				if (Integer.parseInt(addressID) == 0) {
					showAlert(AlertType.WARNING, "Address Adding Warning",
							"Address ID cannot be equal to 0.\nPlease double check again.\n\tTHANK YOU!!!");
				} else {
					if (DataSource.getInstance().insertAddressID(Integer.parseInt(addressID), ward, district, city,
							country)) {
						showAlert(AlertType.INFORMATION, "Address Adding Successfully",
								"New Address has been added into DB with bellowing information.\n\tAddress ID = "
										+ addressID + "\n\tWard = " + ward + "\n\tDistrict = " + district
										+ "\n\tCity = " + city + "\n\tCountry = " + country);
					} else {
						showAlert(AlertType.WARNING, "Address Adding Warning", "Address ID " + addressID
								+ " has been exist in DB.\nCannot add address information into DB.\n\tTHANK YOU!!!");
					}
				}
			}
		} catch (NumberFormatException e) {
			showAlert(AlertType.WARNING, "Address Adding Warning",
					"Address ID cannot be a string.\nAddress ID should be an interger.\n\tTHANK YOU!!!");
		} catch (SQLException e) {
			showAlert(AlertType.ERROR, "Address Adding Failure",
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
		countryText.setPromptText("Nhập lại Quốc Gia");
	}

}
