package test.projectmanagement.customer;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Window;
import test.projectmanagement.Main;
import test.projectmanagement.ProjectManagementUltility;

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

	@FXML
	public void aboutShow() {
		ProjectManagementUltility.getInstance().loadWindow("AboutDialog.fxml", "application.css", "About", false);
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
					ProjectManagementUltility.getInstance().showAlert(AlertType.WARNING, "Customer Searching Warning",
							"Customer ID cannot be equal to 0");
				} else {
					Main.CustomerID = Integer.parseInt(customerID);
					ProjectManagementUltility.getInstance().loadWindow(
							"customer\\CustomerManagementSearchCustomerID.fxml", "application.css",
							"Customer Searching Customer ID Result", false);
				}

			} catch (NumberFormatException e) {
				ProjectManagementUltility.getInstance().showAlert(AlertType.WARNING, "Customer Searching Warning",
						"Please check again Customer ID.\n\tCustomer ID can not be a string.\n\tCustomer ID should be an integer.\n\tTHANK YOU!!!");
			}

		} else if (customerID.isEmpty() && !customerName.isEmpty() && businessType.isEmpty()) {
			Main.customerName = customerName;
			ProjectManagementUltility.getInstance().loadWindow("customer\\CustomerManagementSearchCustomerName.fxml",
					"application.css", "Customer Searching Customer Name Result", false);
		} else if (customerID.isEmpty() && customerName.isEmpty() && !businessType.isEmpty()) {
			Main.businessType = businessType;
			ProjectManagementUltility.getInstance().loadWindow("customer\\CustomerManagementSearchBusinessType.fxml",
					"application.css", "Customer Searching Business Type Result", false);
		} else if (customerID.isEmpty() && !customerName.isEmpty() && !businessType.isEmpty()) {
			Main.customerName = customerName;
			Main.businessType = businessType;
			ProjectManagementUltility.getInstance().loadWindow(
					"customer\\CustomerManagementSearchCustomerNameBusinessType.fxml", "application.css",
					"Customer Searching Customer Name & Business Type Result", false);
		} else {
			ProjectManagementUltility.getInstance().showAlert(AlertType.INFORMATION, "Customer Searching Information",
					"The combination of searching criteria will be support soon.\n\tTHANK YOU!!!");
		}
	}

	@FXML
	public void CancelAction() {
		idText.setText("");
		nameTextArea.setText("");
		businessTypeText.setText("");

		idText.setPromptText("Nhập lại  Customer ID");
		nameTextArea.setPromptText("Nhập lại  Customer Name");
		businessTypeText.setPromptText("Nhập lại Customer Type");
	}
}
