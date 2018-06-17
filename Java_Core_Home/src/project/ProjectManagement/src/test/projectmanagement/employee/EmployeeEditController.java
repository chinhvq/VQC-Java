package test.projectmanagement.employee;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import test.projectmanagement.Main;
import test.projectmanagement.datamodel.DataSource;

public class EmployeeEditController {
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
		genderComboBox.getItems().addAll("m", "f", "o");
		idText.setText(String.valueOf(Main.EmployeeID));
		lastnameText.setText(Main.lastname);
		firstNameText.setText(Main.firstName);
		genderComboBox.setValue(Main.gender);
		phonenumberText.setText(Main.phonenumber);
		emailText.setText(Main.email);
		ageText.setText(String.valueOf(Main.age));
		titleText.setText(Main.title);
		idText.setDisable(true);
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
		String employeID = idText.getText();
		String lastname = lastnameText.getText();
		String firstname = firstNameText.getText();
		String gender = genderComboBox.getValue();
		String phoneNumber = phonenumberText.getText();
		String email = emailText.getText();
		String myAge = ageText.getText();
		String title = titleText.getText();
		String myDepartmentID = departmentIDText.getText();

		if (lastname.isEmpty()) {
			showAlert(AlertType.WARNING, "Employee Adding Warning",
					"Employee Last Name cannot be blank.\nPlease double check again.\n\tTHANK YOU!!!");
		} else if (firstname.isEmpty()) {
			showAlert(AlertType.WARNING, "Employee Adding Warning",
					"Employee First Name cannot be blank.\nPlease double check again.\n\tTHANK YOU!!!");
		} else if (gender == null) {
			showAlert(AlertType.WARNING, "Employee Adding Warning",
					"Employee Gender cannot be blank.\nPlease double check again.\n\tTHANK YOU!!!");
		} else {
			int age = 0;
			int departmentID = 0;
			boolean isAdd = true;
			try {
				if (!myAge.isEmpty()) {
					age = Integer.parseInt(myAge);
				}
			} catch (NumberFormatException e) {
				isAdd = false;
				showAlert(AlertType.WARNING, "Employee Adding Warning",
						"Age can not be a String\nAge should be an integer.\nPlease help to double check again.\n\tTHANK YOU");
			}
			try {
				if (!myDepartmentID.isEmpty()) {
					departmentID = Integer.parseInt(myDepartmentID);
				}
			} catch (NumberFormatException e) {
				isAdd = false;
				showAlert(AlertType.WARNING, "Employee Adding Warning",
						"Department ID can not be a String\nDepartment ID should be an integer.\nPlease help to double check again.\n\tTHANK YOU");
			}
			if (isAdd) {
				if (DataSource.getInstance().updateEmployee(Integer.parseInt(employeID), lastname, firstname, gender,
						phoneNumber, email, age, title, departmentID)) {
					String resultMessage = "Empoyee has been successfully updated into DB with bellowing information.\n\tEmployee ID = "
							+ employeID + "\n\tLast Name = " + lastname + "\n\tFirst Name = " + firstname
							+ "\n\tGender = " + gender + "\n\tPhone Number = " + phoneNumber + "\n\tEmail = " + email
							+ "\n\tAge = " + age + "\n\tTitle = " + title + "\n\tDepartment ID = " + departmentID;
					showAlert(AlertType.INFORMATION, "Employee Editing Successfully", resultMessage);
				} else {
					showAlert(AlertType.ERROR, "Employee Editing Failure",
							"Cannot update the employee information into DB.\n\tTHANK YOU!!!");
				}
			}
		}
	}

	@FXML
	public void CancelAction() {
		lastnameText.setText("");
		firstNameText.setText("");
		phonenumberText.setText("");
		genderComboBox.getSelectionModel().clearSelection();
		emailText.setText("");
		ageText.setText("");
		titleText.setText("");
		departmentIDText.setText("");

		lastnameText.setPromptText("Nhập lại LastName");
		firstNameText.setPromptText("Nhập lại FirstName");
		phonenumberText.setPromptText("Nhập lại Phone Number");
		emailText.setPromptText("Nhập lại Email");
		ageText.setPromptText("Nhập lại Age");
		titleText.setPromptText("Nhập lại Title");
		departmentIDText.setPromptText("Nhập lại Department ID");
	}
}
