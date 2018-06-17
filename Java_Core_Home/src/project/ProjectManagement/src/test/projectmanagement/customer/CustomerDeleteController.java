package test.projectmanagement.customer;

import java.util.Optional;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import test.projectmanagement.Main;
import test.projectmanagement.datamodel.DataSource;

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
			int deleteResult = DataSource.getInstance().deleteCustomer(customerID);
			if (deleteResult == 1) {
				showAlert(AlertType.INFORMATION, "Customer Deleting Successfully",
						"Bellowing Customer has been delete from DB :\n\tCustomer ID = " + customerID
								+ "\n\tCustomer Name = " + customerName + "\n\tBusiness Type = " + businessType);
			} else if (deleteResult == 2) {
				showAlert(AlertType.WARNING, "Customer Deleting Warning", "Customer ID = " + customerID
						+ " has a reference on another project.\nCannot delete the customer.\nTHANK YOU!!!");
			} else if (deleteResult == 3) {
				showAlert(AlertType.WARNING, "Customer Deleting Warning", "Customer ID = " + customerID
						+ " has a reference on another address.\nCannot delete the customer.\nTHANK YOU!!!");
			} else {
				showAlert(AlertType.ERROR, "Customer Deleting Failure", "Deleting Customer has failed");
			}
		} else {
			showAlert(AlertType.INFORMATION, "Customer Deleting Information",
					"You have cancelled the deleting operation.\nTHANK YOU!!!");
		}
	}

	@FXML
	public void CancelAction() {
		close();
	}
}
