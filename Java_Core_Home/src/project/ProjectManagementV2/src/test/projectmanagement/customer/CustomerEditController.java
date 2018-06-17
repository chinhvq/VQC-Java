package test.projectmanagement.customer;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Window;
import test.projectmanagement.Main;
import test.projectmanagement.ProjectManagementUltility;
import test.projectmanagement.datasource.DataSourceCustomer;

public class CustomerEditController {
	@FXML
	BorderPane mainPane;
	@FXML
	private TextField idText;
	@FXML
	private TextArea nameTextArea;
	@FXML
	private TextField businessTypeText;

	public void initialize() {
		idText.setText(String.valueOf(Main.CustomerID));
		nameTextArea.setText(Main.customerName);
		businessTypeText.setText(Main.businessType);
		idText.setDisable(true);
	}

	@FXML
	public void close() {
		Window window = mainPane.getScene().getWindow();
		window.hide();
	}

	@FXML
	public void aboutShow() {
		ProjectManagementUltility.getInstance().loadWindow("AboutDialog.fxml", "application.css", "About",
				false);
	}

	@FXML
	public void OkAction() {
		String customerID = idText.getText();
		String customerName = nameTextArea.getText();
		String businessType = businessTypeText.getText();
		if (customerName.isEmpty()) {
			ProjectManagementUltility.getInstance().showAlert(AlertType.WARNING, "Customer Adding Warning",
					"Customer Name cannot be blank");
		} else if (!customerName.isEmpty()) {
			if (DataSourceCustomer.getInstance().updateCustomer(Integer.parseInt(customerID), customerName, businessType)) {
				ProjectManagementUltility.getInstance().showAlert(AlertType.INFORMATION, "Customer Adding Successfully",
						"Customer has been updated into DB with bellowing information:\n\tCustomer ID = " + customerID
								+ "\n\tCustomer Name = " + customerName + "\n\tBusiness Type = " + businessType);
			} else {
				ProjectManagementUltility.getInstance().showAlert(AlertType.ERROR, "Customer Editing Failure",
						"Update not successfully.");
			}
		}
	}

	@FXML
	public void CancelAction() {
		nameTextArea.setText("");
		businessTypeText.setText("");

		nameTextArea.setPromptText("Nhập lại  Customer Name");
		businessTypeText.setPromptText("Nhập lại  Customer Type");
	}
}
