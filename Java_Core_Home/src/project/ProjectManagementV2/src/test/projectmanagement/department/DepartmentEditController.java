package test.projectmanagement.department;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Window;
import test.projectmanagement.Main;
import test.projectmanagement.ProjectManagementUltility;
import test.projectmanagement.datasource.DataSourceDepartment;

public class DepartmentEditController {
	@FXML
	BorderPane mainPane;
	@FXML
	private TextField idText;
	@FXML
	private TextField nameText;

	public void initialize() {
		idText.setText(String.valueOf(Main.deptID));
		idText.setDisable(true);
		nameText.setText(Main.deptName);
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
		int departmentID = Integer.parseInt(idText.getText());
		String departmentName = nameText.getText();
		if (departmentName.isEmpty()) {
			ProjectManagementUltility.getInstance().showAlert(AlertType.ERROR, "Department Editing Error ", "Deparment Name cannot be blank");
		} else {
			if (DataSourceDepartment.getInstance().updateDepartment(departmentID, departmentName)) {
				ProjectManagementUltility.getInstance().showAlert(AlertType.INFORMATION, "Department Editing Sucessfully ",
						"Department " + String.valueOf(Main.deptName) + " has been updated successfully\n"
								+ "New department name is " + departmentName);				
				close();
			} else {
				ProjectManagementUltility.getInstance().showAlert(AlertType.ERROR, "Department Editing Failure", "Update not successfully.");
			}
		}
	}

	@FXML
	public void CancelAction() {
		nameText.setText("");
		nameText.setPromptText("Nhập lại Department Name");
	}
}
