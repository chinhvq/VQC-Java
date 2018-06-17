package test.projectmanagement.customer;

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
import test.projectmanagement.Main;

public class CustomerSearchController {
	@FXML
	BorderPane mainPane;
	@FXML
	private TextField idText;
	@FXML
	private TextArea nameTextArea;
	@FXML
	private TextField businessTypeText;

	public void initialize() {
	}

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

		if (customerID.isEmpty() && customerName.isEmpty() && businessType.isEmpty()) {

		} else if (!customerID.isEmpty() && customerName.isEmpty() && businessType.isEmpty()) {
			try {
				if (Integer.parseInt(customerID) == 0) {
					showAlert(AlertType.WARNING, "Customer Searching Warning", "Customer ID cannot be equal to 0");
				} else {
					Main.CustomerID = Integer.parseInt(customerID);
					loadWindow("CustomerManagementSearchCustomerID.fxml", "..\\application.css",
							"Customer Searching Customer ID Result", false);
				}

			} catch (NumberFormatException e) {
				showAlert(AlertType.WARNING, "Customer Searching Warning",
						"Please check again Customer ID.\n\tCustomer ID can not be a string.\n\tCustomer ID should be an integer.\n\tTHANK YOU!!!");
			}

		} else if (customerID.isEmpty() && !customerName.isEmpty() && businessType.isEmpty()) {
			Main.customerName = customerName;
			loadWindow("CustomerManagementSearchCustomerName.fxml", "..\\application.css",
					"Customer Searching Customer Name Result", false);
		} else if (customerID.isEmpty() && customerName.isEmpty() && !businessType.isEmpty()) {
			Main.businessType = businessType;
			loadWindow("CustomerManagementSearchBusinessType.fxml", "..\\application.css",
					"Customer Searching Business Type Result", false);
		} else if (customerID.isEmpty() && !customerName.isEmpty() && !businessType.isEmpty()) {
			Main.customerName = customerName;
			Main.businessType = businessType;
			loadWindow("CustomerManagementSearchCustomerNameBusinessType.fxml", "..\\application.css",
					"Customer Searching Customer Name & Business Type Result", false);
		} else {
			showAlert(AlertType.INFORMATION, "Customer Searching Information",
					"The combination of searching criteria will be support soon.\n\tTHANK YOU!!!");
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
