package test.projectmanagement.employee;

import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Window;
import test.projectmanagement.Main;
import test.projectmanagement.ProjectManagementUltility;
import test.projectmanagement.datasource.DataSourceEmployee;

public class EmployeeDeleteController {
	@FXML
	private BorderPane mainPane;
	@FXML
	private TextField idText;
	@FXML
	private TextField lastnameText;
	@FXML
	private TextField firstNameText;
	@FXML
	private TextField phonenumberText;
	@FXML
	private TextField emailText;
	@FXML
	private TextField ageText;
	@FXML
	private TextField titleText;
	@FXML
	private TextField departmentIDText;
	@FXML
	private ComboBox<String> genderComboBox;
	ObservableList<String> genderOptions = FXCollections.observableArrayList("m", "f", "o");

	public void initialize() {
		idText.setText(String.valueOf(Main.EmployeeID));
		lastnameText.setText(Main.lastname);
		firstNameText.setText(Main.firstName);
		genderComboBox.setValue(Main.gender);
		phonenumberText.setText(Main.phonenumber);
		emailText.setText(Main.email);
		ageText.setText(String.valueOf(Main.age));
		titleText.setText(Main.title);

		idText.setDisable(true);
		lastnameText.setDisable(true);
		firstNameText.setDisable(true);
		genderComboBox.setDisable(true);
		phonenumberText.setDisable(true);
		emailText.setDisable(true);
		ageText.setDisable(true);
		titleText.setDisable(true);
		departmentIDText.setDisable(true);
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
		String employeeID = idText.getText();
		String lastname = lastnameText.getText();
		String firstname = firstNameText.getText();
		String gender = genderComboBox.getValue();
		String phoneNumber = phonenumberText.getText();
		String email = emailText.getText();
		String age = ageText.getText();
		String title = titleText.getText();
		deleteAction(Integer.parseInt(employeeID), lastname, firstname, gender, phoneNumber, email,
				Integer.parseInt(age), title);
	}

	public void deleteAction(int employeID, String lastname, String firstname, String gender, String phoneNumber,
			String email, int age, String title) {
		Alert alertConfirmation = new Alert(Alert.AlertType.CONFIRMATION);
		alertConfirmation.setContentText("Are you sure to delete employee with bellowing information:\n\tEmployee ID = "
				+ employeID + "\n\tLast Name = " + lastname + "\n\tFirst Name = " + firstname + "\n\tGender = " + gender
				+ "\n\tPhone Number = " + phoneNumber + "\n\tEmail = " + email + "\n\tAge = " + age + "\n\tTitle = "
				+ title);
		alertConfirmation.setTitle("Employee Deleting Confirmation");
		alertConfirmation.setHeaderText("Alert Information");
		Optional<ButtonType> result = alertConfirmation.showAndWait();

		if (result.get().getButtonData() == ButtonBar.ButtonData.OK_DONE) {
			int deleteResult = DataSourceEmployee.getInstance().deleteEmployee(employeID);
			if (deleteResult == 1) {
				ProjectManagementUltility.getInstance().showAlert(AlertType.INFORMATION,
						"Employee Deleting Successfully",
						"Bellowing Project has been deleted from DB :\n\tEmployee ID = " + employeID
								+ "\n\tLast Name = " + lastname + "\n\tFirst Name = " + firstname + "\n\tGender = "
								+ gender + "\n\tPhone Number = " + phoneNumber + "\n\tEmail = " + email + "\n\tAge = "
								+ age + "\n\tTitle = " + title);
			} else if (deleteResult == 2) {
				ProjectManagementUltility.getInstance().showAlert(AlertType.WARNING, "Employee Deleting Warning",
						"Employee ID = " + employeID
								+ " has a reference on another address.\nCannot delete the employee.\nTHANK YOU!!!");
			} else if (deleteResult == 3) {
				ProjectManagementUltility.getInstance().showAlert(AlertType.WARNING, "Employee Deleting Warning",
						"Employee ID = " + employeID
								+ " has a reference on another project.\nCannot delete the employee.\nTHANK YOU!!!");
			} else {
				ProjectManagementUltility.getInstance().showAlert(AlertType.ERROR, "Employee Deleting Failure",
						"Deleting Employee has failed");
			}
		} else {
			ProjectManagementUltility.getInstance().showAlert(AlertType.INFORMATION, "Employee Deleting Information",
					"You have cancelled the deleting operation.\nTHANK YOU!!!");
		}
	}

	@FXML
	public void CancelAction() {
		close();
	}
}
