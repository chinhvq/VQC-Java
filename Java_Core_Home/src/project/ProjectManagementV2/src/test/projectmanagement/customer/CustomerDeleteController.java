package test.projectmanagement.customer;

import java.util.Optional;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Window;
import test.projectmanagement.Main;
import test.projectmanagement.ProjectManagementUltility;
import test.projectmanagement.datasource.DataSourceCustomer;

public class CustomerDeleteController {
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
		nameTextArea.setDisable(true);
		businessTypeText.setDisable(true);
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
		int customerID = Main.CustomerID;
		String customerName = Main.customerName;
		String businessType = Main.businessType;
		deleteAction(customerID, customerName, businessType);
	}

	public void deleteAction(int customerID, String customerName, String businessType) {
		Alert alertConfirmation = new Alert(Alert.AlertType.CONFIRMATION);
		alertConfirmation.setContentText("Are you sure to delete customer with bellowing information:\n\tCustomer ID = "
				+ customerID + "\n\tCustomer Name = " + customerName + "\n\tBusiness Type = " + businessType);
		alertConfirmation.setTitle("Customer Deleting Confirmation");
		alertConfirmation.setHeaderText("Alert Information");
		Optional<ButtonType> result = alertConfirmation.showAndWait();

		if (result.get().getButtonData() == ButtonBar.ButtonData.OK_DONE) {
			int deleteResult = DataSourceCustomer.getInstance().deleteCustomer(customerID);
			if (deleteResult == 1) {
				ProjectManagementUltility.getInstance().showAlert(AlertType.INFORMATION, "Customer Deleting Successfully",
						"Bellowing Customer has been delete from DB :\n\tCustomer ID = " + customerID
								+ "\n\tCustomer Name = " + customerName + "\n\tBusiness Type = " + businessType);
			} else if (deleteResult == 2) {
				ProjectManagementUltility.getInstance().showAlert(AlertType.WARNING, "Customer Deleting Warning", "Customer ID = " + customerID
						+ " has a reference on another project.\nCannot delete the customer.\nTHANK YOU!!!");
			} else if (deleteResult == 3) {
				ProjectManagementUltility.getInstance().showAlert(AlertType.WARNING, "Customer Deleting Warning", "Customer ID = " + customerID
						+ " has a reference on another address.\nCannot delete the customer.\nTHANK YOU!!!");
			} else {
				ProjectManagementUltility.getInstance().showAlert(AlertType.ERROR, "Customer Deleting Failure", "Deleting Customer has failed");
			}
		} else {
			ProjectManagementUltility.getInstance().showAlert(AlertType.INFORMATION, "Customer Deleting Information",
					"You have cancelled the deleting operation.\nTHANK YOU!!!");
		}
	}

	@FXML
	public void CancelAction() {
		close();
	}
}
