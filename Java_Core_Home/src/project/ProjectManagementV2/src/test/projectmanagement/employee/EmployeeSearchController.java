package test.projectmanagement.employee;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Window;
import test.projectmanagement.Main;
import test.projectmanagement.ProjectManagementUltility;

public class EmployeeSearchController {
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
		String employeID = idText.getText();
		String lastname = lastnameText.getText();
		String firstname = firstNameText.getText();
		String gender = genderComboBox.getValue();
		String phoneNumber = phonenumberText.getText();
		String email = emailText.getText();
		String myAge = ageText.getText();
		String title = titleText.getText();
		String myDepartmentID = departmentIDText.getText();

		if (employeID.isEmpty() && lastname.isEmpty() && firstname.isEmpty() && (gender == null)
				&& phoneNumber.isEmpty() && email.isEmpty() && myAge.isEmpty() && title.isEmpty()
				&& myDepartmentID.isEmpty()) {

		} else if (!employeID.isEmpty() && lastname.isEmpty() && firstname.isEmpty() && (gender == null)
				&& phoneNumber.isEmpty() && email.isEmpty() && myAge.isEmpty() && title.isEmpty()
				&& myDepartmentID.isEmpty()) {
			// search Employee Id only
			try {
				Main.EmployeeID = Integer.parseInt(employeID);
				ProjectManagementUltility.getInstance().loadWindow("employee\\EmployeeManagementSearchEmployeeID.fxml",
						"application.css", "Employee Employee ID Searching", false);
			} catch (NumberFormatException e) {
				ProjectManagementUltility.getInstance().showAlert(AlertType.WARNING, "Employee Searching Warning",
						"Employee ID can not be a String\nEmployee ID should be an integer.\nPlease help to double check again.\n\tTHANK YOU");
			}
		} else if (employeID.isEmpty() && !lastname.isEmpty() && firstname.isEmpty() && (gender == null)
				&& phoneNumber.isEmpty() && email.isEmpty() && myAge.isEmpty() && title.isEmpty()
				&& myDepartmentID.isEmpty()) {
			Main.lastname = lastname;
			ProjectManagementUltility.getInstance().loadWindow("employee\\EmployeeManagementSearchLastName.fxml",
					"application.css", "Employee Employee Last Name Searching", false);
			// search Last Name only
		} else if (employeID.isEmpty() && lastname.isEmpty() && !firstname.isEmpty() && (gender == null)
				&& phoneNumber.isEmpty() && email.isEmpty() && myAge.isEmpty() && title.isEmpty()
				&& myDepartmentID.isEmpty()) {
			// search First Name only
			Main.firstName = firstname;
			ProjectManagementUltility.getInstance().loadWindow("employee\\EmployeeManagementSearchFirstName.fxml",
					"application.css", "Employee Employee First Name Searching", false);
		} else if (employeID.isEmpty() && lastname.isEmpty() && firstname.isEmpty() && (gender != null)
				&& phoneNumber.isEmpty() && email.isEmpty() && myAge.isEmpty() && title.isEmpty()
				&& myDepartmentID.isEmpty()) {
			// search Gender only
			Main.gender = gender;
			ProjectManagementUltility.getInstance().loadWindow("employee\\EmployeeManagementSearchGender.fxml",
					"application.css", "Employee Employee Gender Searching", false);
		} else if (employeID.isEmpty() && lastname.isEmpty() && firstname.isEmpty() && (gender == null)
				&& !phoneNumber.isEmpty() && email.isEmpty() && myAge.isEmpty() && title.isEmpty()
				&& myDepartmentID.isEmpty()) {
			// search Phone Number only
			Main.phonenumber = phoneNumber;
			ProjectManagementUltility.getInstance().loadWindow("employee\\EmployeeManagementSearchPhoneNumber.fxml",
					"application.css", "Employee Employee Phone Number Searching", false);
		} else if (employeID.isEmpty() && lastname.isEmpty() && firstname.isEmpty() && (gender == null)
				&& phoneNumber.isEmpty() && !email.isEmpty() && myAge.isEmpty() && title.isEmpty()
				&& myDepartmentID.isEmpty()) {
			// search email only
			Main.email = email;
			ProjectManagementUltility.getInstance().loadWindow("employee\\EmployeeManagementSearchEmail.fxml",
					"application.css", "Employee Employee Email Searching", false);
		} else if (employeID.isEmpty() && lastname.isEmpty() && firstname.isEmpty() && (gender == null)
				&& phoneNumber.isEmpty() && email.isEmpty() && !myAge.isEmpty() && title.isEmpty()
				&& myDepartmentID.isEmpty()) {
			// search age only
			try {
				Main.age = Integer.parseInt(myAge);
				ProjectManagementUltility.getInstance().loadWindow("employee\\EmployeeManagementSearchAge.fxml",
						"application.css", "Employee Employee Age Searching", false);
			} catch (NumberFormatException e) {
				ProjectManagementUltility.getInstance().showAlert(AlertType.WARNING, "Employee Searching Warning",
						"Age can not be a String\nAge should be an integer.\nPlease help to double check again.\n\tTHANK YOU");
			}
		} else if (employeID.isEmpty() && lastname.isEmpty() && firstname.isEmpty() && (gender == null)
				&& phoneNumber.isEmpty() && email.isEmpty() && myAge.isEmpty() && !title.isEmpty()
				&& myDepartmentID.isEmpty()) {
			// search title only
			Main.title = title;
			ProjectManagementUltility.getInstance().loadWindow("employee\\EmployeeManagementSearchTitle.fxml",
					"application.css", "Employee Employee Title Searching", false);
		} else if (employeID.isEmpty() && lastname.isEmpty() && firstname.isEmpty() && (gender == null)
				&& phoneNumber.isEmpty() && email.isEmpty() && myAge.isEmpty() && title.isEmpty()
				&& !myDepartmentID.isEmpty()) {
			// search Department Id only
			try {
				Main.departmentID = Integer.parseInt(myDepartmentID);
				ProjectManagementUltility.getInstance().loadWindow(
						"employee\\EmployeeManagementSearchDepartmentID.fxml", "application.css",
						"Employee Employee Department ID Searching", false);
			} catch (NumberFormatException e) {
				ProjectManagementUltility.getInstance().showAlert(AlertType.WARNING, "Employee Searching Warning",
						"Department ID can not be a String\nDepartment ID should be an integer.\nPlease help to double check again.\n\tTHANK YOU");
			}
		} else if (employeID.isEmpty() && !lastname.isEmpty() && firstname.isEmpty() && (gender != null)
				&& phoneNumber.isEmpty() && email.isEmpty() && myAge.isEmpty() && title.isEmpty()
				&& myDepartmentID.isEmpty()) {
			// search last name and gender only
			Main.lastname = lastname;
			Main.gender = gender;
			ProjectManagementUltility.getInstance().loadWindow("employee\\EmployeeManagementSearchLastNameGender.fxml",
					"application.css", "Employee Employee Last Name && Gender Searching", false);
		} else if (employeID.isEmpty() && !lastname.isEmpty() && firstname.isEmpty() && (gender == null)
				&& phoneNumber.isEmpty() && email.isEmpty() && !myAge.isEmpty() && title.isEmpty()
				&& myDepartmentID.isEmpty()) {
			// search last name and age only
			try {
				Main.lastname = lastname;
				Main.age = Integer.parseInt(myAge);
				ProjectManagementUltility.getInstance().loadWindow("employee\\EmployeeManagementSearchLastNameAge.fxml",
						"application.css", "Employee Employee Last Name && Age Searching", false);
			} catch (NumberFormatException e) {
				ProjectManagementUltility.getInstance().showAlert(AlertType.WARNING, "Employee Searching Warning",
						"Age can not be a String\nAge should be an integer.\nPlease help to double check again.\n\tTHANK YOU");
			}
		} else if (employeID.isEmpty() && !lastname.isEmpty() && firstname.isEmpty() && (gender == null)
				&& phoneNumber.isEmpty() && email.isEmpty() && myAge.isEmpty() && !title.isEmpty()
				&& myDepartmentID.isEmpty()) {
			// search last name and title only
			Main.lastname = lastname;
			Main.title = title;
			ProjectManagementUltility.getInstance().loadWindow("employee\\EmployeeManagementSearchLastNameTitle.fxml",
					"application.css", "Employee Employee Last Name && Title Searching", false);
		} else if (employeID.isEmpty() && !lastname.isEmpty() && firstname.isEmpty() && (gender == null)
				&& phoneNumber.isEmpty() && email.isEmpty() && myAge.isEmpty() && title.isEmpty()
				&& !myDepartmentID.isEmpty()) {
			// search last name and department ID only
			try {
				Main.lastname = lastname;
				Main.departmentID = Integer.parseInt(myDepartmentID);
				ProjectManagementUltility.getInstance().loadWindow(
						"employee\\EmployeeManagementSearchLastNameDepartmentID.fxml", "application.css",
						"Employee Employee Last Name && Department ID Searching", false);
			} catch (NumberFormatException e) {
				ProjectManagementUltility.getInstance().showAlert(AlertType.WARNING, "Employee Searching Warning",
						"Department ID can not be a String\nDepartment ID should be an integer.\nPlease help to double check again.\n\tTHANK YOU");
			}

		} else if (employeID.isEmpty() && lastname.isEmpty() && firstname.isEmpty() && (gender != null)
				&& phoneNumber.isEmpty() && email.isEmpty() && !myAge.isEmpty() && title.isEmpty()
				&& myDepartmentID.isEmpty()) {
			// search gender and age only
			try {
				Main.gender = gender;
				Main.age = Integer.parseInt(myAge);
				ProjectManagementUltility.getInstance().loadWindow("employee\\EmployeeManagementSearchGenderAge.fxml",
						"application.css", "Employee Employee Gender && Age Searching", false);
			} catch (NumberFormatException e) {
				ProjectManagementUltility.getInstance().showAlert(AlertType.WARNING, "Employee Searching Warning",
						"Age can not be a String\nAge should be an integer.\nPlease help to double check again.\n\tTHANK YOU");
			}
		} else if (employeID.isEmpty() && lastname.isEmpty() && firstname.isEmpty() && (gender != null)
				&& phoneNumber.isEmpty() && email.isEmpty() && myAge.isEmpty() && !title.isEmpty()
				&& myDepartmentID.isEmpty()) {
			// search gender and title only
			Main.gender = gender;
			Main.title = title;
			ProjectManagementUltility.getInstance().loadWindow("employee\\EmployeeManagementSearchGenderTitle.fxml",
					"application.css", "Employee Employee Gender && Title Searching", false);
		} else if (employeID.isEmpty() && lastname.isEmpty() && firstname.isEmpty() && (gender != null)
				&& phoneNumber.isEmpty() && email.isEmpty() && myAge.isEmpty() && title.isEmpty()
				&& !myDepartmentID.isEmpty()) {
			// search gender and department ID only
			try {
				Main.gender = gender;
				Main.departmentID = Integer.parseInt(myDepartmentID);
				ProjectManagementUltility.getInstance().loadWindow(
						"employee\\EmployeeManagementSearchGenderDepartmentID.fxml", "application.css",
						"Employee Employee Gender && Department ID Searching", false);
			} catch (NumberFormatException e) {
				ProjectManagementUltility.getInstance().showAlert(AlertType.WARNING, "Employee Searching Warning",
						"Department ID can not be a String\nDepartment ID should be an integer.\nPlease help to double check again.\n\tTHANK YOU");
			}
		} else if (employeID.isEmpty() && lastname.isEmpty() && firstname.isEmpty() && (gender == null)
				&& phoneNumber.isEmpty() && email.isEmpty() && !myAge.isEmpty() && !title.isEmpty()
				&& myDepartmentID.isEmpty()) {
			// search age and title only
			try {
				Main.age = Integer.parseInt(myAge);
				Main.title = title;
				ProjectManagementUltility.getInstance().loadWindow("employee\\EmployeeManagementSearchAgeTitle.fxml",
						"application.css", "Employee Employee Age && Title Searching", false);
			} catch (NumberFormatException e) {
				ProjectManagementUltility.getInstance().showAlert(AlertType.WARNING, "Employee Searching Warning",
						"Age can not be a String\nAge should be an integer.\nPlease help to double check again.\n\tTHANK YOU");
			}
		} else if (employeID.isEmpty() && lastname.isEmpty() && firstname.isEmpty() && (gender == null)
				&& phoneNumber.isEmpty() && email.isEmpty() && !myAge.isEmpty() && title.isEmpty()
				&& !myDepartmentID.isEmpty()) {
			// search age and department ID only
			boolean isSearch = true;
			try {
				Main.age = Integer.parseInt(myAge);
			} catch (NumberFormatException e) {
				isSearch = false;
				ProjectManagementUltility.getInstance().showAlert(AlertType.WARNING, "Employee Searching Warning",
						"Age can not be a String\nAge should be an integer.\nPlease help to double check again.\n\tTHANK YOU");
			}
			try {
				Main.departmentID = Integer.parseInt(myDepartmentID);
			} catch (NumberFormatException e) {
				isSearch = false;
				ProjectManagementUltility.getInstance().showAlert(AlertType.WARNING, "Employee Searching Warning",
						"Department ID can not be a String\nDepartment ID should be an integer.\nPlease help to double check again.\n\tTHANK YOU");
			}
			if (isSearch) {
				ProjectManagementUltility.getInstance().loadWindow(
						"employee\\EmployeeManagementSearchAgeDepartmentID.fxml", "application.css",
						"Employee Employee Age && Department ID Searching", false);
			}
		} else if (employeID.isEmpty() && lastname.isEmpty() && firstname.isEmpty() && (gender == null)
				&& phoneNumber.isEmpty() && email.isEmpty() && myAge.isEmpty() && !title.isEmpty()
				&& !myDepartmentID.isEmpty()) {
			// search title and department ID only
			try {
				Main.title = title;
				Main.departmentID = Integer.parseInt(myDepartmentID);
				ProjectManagementUltility.getInstance().loadWindow(
						"employee\\EmployeeManagementSearchTitleDepartmentID.fxml", "application.css",
						"Employee Employee Title && Department ID Searching", false);
			} catch (NumberFormatException e) {
				ProjectManagementUltility.getInstance().showAlert(AlertType.WARNING, "Employee Searching Warning",
						"Department ID can not be a String\nDepartment ID should be an integer.\nPlease help to double check again.\n\tTHANK YOU");
			}
		} else {
			ProjectManagementUltility.getInstance().showAlert(AlertType.INFORMATION, "Employee Searching Information",
					"The combination of searching criteria will be support soon.\n\tTHANK YOU!!!");
		}
	}

	@FXML
	public void CancelAction() {
		idText.setText("");
		lastnameText.setText("");
		firstNameText.setText("");
		phonenumberText.setText("");
		genderComboBox.getSelectionModel().clearSelection();
		emailText.setText("");
		ageText.setText("");
		titleText.setText("");
		departmentIDText.setText("");

		idText.setPromptText("Nhập lại Employee ID");
		lastnameText.setPromptText("Nhập lại LastName");
		firstNameText.setPromptText("Nhập lại FirstName");
		phonenumberText.setPromptText("Nhập lại Phone Number");
		emailText.setPromptText("Nhập lại Email");
		ageText.setPromptText("Nhập lại Age");
		titleText.setPromptText("Nhập lại Title");
		departmentIDText.setPromptText("Nhập lại Department ID");
	}
}
