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
import test.projectmanagement.datamodel.DataSource;

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
		if (customerName.isEmpty()) {
			showAlert(AlertType.WARNING, "Customer Adding Warning", "Customer Name cannot be blank");
		} else if (!customerName.isEmpty()) {
			if (DataSource.getInstance().updateCustomer(Integer.parseInt(customerID), customerName, businessType)) {
				showAlert(AlertType.INFORMATION, "Customer Adding Successfully",
						"Customer has been updated into DB with bellowing information:\n\tCustomer ID = " + customerID
								+ "\n\tCustomer Name = " + customerName + "\n\tBusiness Type = " + businessType);
			} else {
				showAlert(AlertType.ERROR, "Customer Editing Failure", "Update not successfully.");
			}
		}
	}

	@FXML
	public void CancelAction() {
		nameTextArea.setText("");
		businessTypeText.setText("");

		nameTextArea.setPromptText("Nhập lại Customer Name");
		businessTypeText.setPromptText("Nhập lại Customer Type");
	}
}