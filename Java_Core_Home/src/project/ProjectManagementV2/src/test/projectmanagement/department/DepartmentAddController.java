package test.projectmanagement.department;

import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Window;
import test.projectmanagement.ProjectManagementUltility;
import test.projectmanagement.datasource.DataSourceDepartment;

public class DepartmentAddController {
	@FXML
	BorderPane mainPane;
	@FXML
	private TextField idText;
	@FXML
	private TextField nameText;

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
		String departmentName = nameText.getText();
		String departmentID = idText.getText();

		try {
			if (departmentName.isEmpty()) {
				ProjectManagementUltility.getInstance().showAlert(AlertType.WARNING, "Department Adding Warning",
						"Department Name cannot be blank");
			} else if (departmentID.isEmpty() && !departmentName.isEmpty()) {
				System.out.println("Here - department Name " + departmentName);
				int result = DataSourceDepartment.getInstance().insertDepartmentName(departmentName);
				if (result != 0) {
					String alertContext = "New Department has been successfully added into DB\nDepartment ID = "
							+ result + "\nDepartment Name = " + departmentName;
					ProjectManagementUltility.getInstance().showAlert(AlertType.INFORMATION,
							"Department Adding Successfully", alertContext);
				} else {
					System.out.println("smth");
				}
			} else if (Integer.parseInt(departmentID) == 0) {
				ProjectManagementUltility.getInstance().showAlert(AlertType.WARNING, "Department Adding Warning",
						"Department ID cannot be equal to 0");
			} else {
				if (DataSourceDepartment.getInstance().insertDepartmentIdName(Integer.parseInt(departmentID),
						departmentName)) {
					String alertContext = "New Department has been successfully added into DB\nDepartment ID = "
							+ departmentID + "\nDepartment Name = " + departmentName;
					ProjectManagementUltility.getInstance().showAlert(AlertType.INFORMATION,
							"Department Adding Successfully", alertContext);
				} else {
					ProjectManagementUltility.getInstance().showAlert(AlertType.WARNING, "Department Adding Warning",
							"New Department ID " + departmentID + " already exist in DB\n"
									+ "Cannot insert new Department");
				}
			}
		} catch (NumberFormatException e) {
			ProjectManagementUltility.getInstance().showAlert(AlertType.WARNING, "Department Addition Warning",
					"Please check again Department ID.\n\tDepartment ID can not be a string.\n\t Department ID should be an integer.\n\tTHANK YOU!!!");
		} catch (SQLException e) {
			ProjectManagementUltility.getInstance().showAlert(AlertType.ERROR, "Department Adding Failure",
					"Please check again!");
		}
	}

	@FXML
	public void CancelAction() {
		idText.setText("");
		nameText.setText("");

		idText.setPromptText("Nhập lại Department ID");
		nameText.setPromptText("Nhập lại Department Name");
	}
}
