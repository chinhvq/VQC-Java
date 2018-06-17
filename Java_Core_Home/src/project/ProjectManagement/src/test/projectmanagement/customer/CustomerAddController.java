package test.projectmanagement.customer;

import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import test.projectmanagement.datamodel.DataSource;

public class CustomerAddController {
	@FXML
	BorderPane mainPane;
	@FXML
	private TextField idText;
	@FXML
	private TextArea nameTextArea;
	@FXML
	private TextField businessTypeText;

	@FXML
	public void close() {
		Window window = mainPane.getScene().getWindow();
		window.hide();
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

	private void showAlert(AlertType alertType, String alertTitle, String alertContext) {
		Alert alertInfo = new Alert(alertType);
		alertInfo.setContentText(alertContext);
		alertInfo.setTitle(alertTitle);
		alertInfo.show();
	}

	@FXML
	public void OkAction() {
		String customerID = idText.getText();
		String customerName = nameTextArea.getText();
		String businessType = businessTypeText.getText();

		try {
			if (customerName.isEmpty()) {
				showAlert(AlertType.WARNING, "Customer Adding Warning", "Customer Name cannot be blank");
			} else if (customerID.isEmpty()) {
				int result = DataSource.getInstance().addCustomerName(customerName, businessType);
				if (result != 0) {
					showAlert(AlertType.INFORMATION, "Customer Adding Successfully",
							"New customer has been added into DB with bellowing information:\n\tCustomer ID = " + result
									+ "\n\tCustomer Name = " + customerName + "\n\tBusiness Type = " + businessType);
					close();
				}
			} else {
				if (Integer.parseInt(customerID) == 0) {
					showAlert(AlertType.WARNING, "Customer Adding Warning", "Customer ID cannot be equal to 0");
				} else {
					if (DataSource.getInstance().addCustomerID(Integer.parseInt(customerID), customerName,
							businessType)) {
						showAlert(AlertType.INFORMATION, "Customer Adding Successfully",
								"New customer has been added into DB with bellowing information:\n\tCustomer ID = "
										+ customerID + "\n\tCustomer Name = " + customerName + "\n\tBusiness Type = "
										+ businessType);
						close();
					} else {
						showAlert(AlertType.WARNING, "Customer Adding Warning", "New Customer ID " + customerID
								+ " already exist in DB\n" + "Cannot insert new Customer");
					}
				}
			}
		} catch (NumberFormatException e) {
			showAlert(AlertType.WARNING, "Customer Addition Warning",
					"Please check again Customer ID.\n\tCustomer ID can not be a string.\n\tCustomer ID should be an integer.\n\tTHANK YOU!!!");
		} catch (SQLException e) {
			showAlert(AlertType.ERROR, "Customer Adding Failure", "Please check again!");
		}

	}

	@FXML
	public void CancelAction() {
		idText.setText("");
		nameTextArea.setText("");
		businessTypeText.setText("");

		idText.setPromptText("Nhập lại Customer ID");
		nameTextArea.setPromptText("Nhập lại Customer Name");
		businessTypeText.setPromptText("Nhập lại Customer Type");
	}
}
