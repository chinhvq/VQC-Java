package test.projectmanagement.customer;

import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Window;
import test.projectmanagement.ProjectManagementUltility;
import test.projectmanagement.datasource.DataSourceCustomer;

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

	@FXML
	public void aboutShow() {
		ProjectManagementUltility.getInstance().loadWindow("AboutDialog.fxml", "application.css", "About", false);
	}

	@FXML
	public void OkAction() {
		String customerID = idText.getText();
		String customerName = nameTextArea.getText();
		String businessType = businessTypeText.getText();

		try {
			if (customerName.isEmpty()) {
				ProjectManagementUltility.getInstance().showAlert(AlertType.WARNING, "Customer Adding Warning", "Customer Name cannot be blank");
			} else if (customerID.isEmpty()) {
				int result = DataSourceCustomer.getInstance().addCustomerName(customerName, businessType);
				if (result != 0) {
					ProjectManagementUltility.getInstance().showAlert(AlertType.INFORMATION, "Customer Adding Successfully",
							"New customer has been added into DB with bellowing information:\n\tCustomer ID = " + result
									+ "\n\tCustomer Name = " + customerName + "\n\tBusiness Type = " + businessType);
					close();
				}
			} else {
				if (Integer.parseInt(customerID) == 0) {
					ProjectManagementUltility.getInstance().showAlert(AlertType.WARNING, "Customer Adding Warning", "Customer ID cannot be equal to 0");
				} else {
					if (DataSourceCustomer.getInstance().addCustomerID(Integer.parseInt(customerID), customerName,
							businessType)) {
						ProjectManagementUltility.getInstance().showAlert(AlertType.INFORMATION, "Customer Adding Successfully",
								"New customer has been added into DB with bellowing information:\n\tCustomer ID = "
										+ customerID + "\n\tCustomer Name = " + customerName + "\n\tBusiness Type = "
										+ businessType);
						close();
					} else {
						ProjectManagementUltility.getInstance().showAlert(AlertType.WARNING, "Customer Adding Warning", "New Customer ID " + customerID
								+ " already exist in DB\n" + "Cannot insert new Customer");
					}
				}
			}
		} catch (NumberFormatException e) {
			ProjectManagementUltility.getInstance().showAlert(AlertType.WARNING, "Customer Addition Warning",
					"Please check again Customer ID.\n\tCustomer ID can not be a string.\n\tCustomer ID should be an integer.\n\tTHANK YOU!!!");
		} catch (SQLException e) {
			ProjectManagementUltility.getInstance().showAlert(AlertType.ERROR, "Customer Adding Failure", "Please check again!");
		}

	}

	@FXML
	public void CancelAction() {
		idText.setText("");
		nameTextArea.setText("");
		businessTypeText.setText("");

		idText.setPromptText("Nhập lại Customer ID");
		nameTextArea.setPromptText("Nhập lại  Customer Name");
		businessTypeText.setPromptText("Nhập lại  Customer Type");
	}
}
