package test.projectmanagement.department;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Window;
import test.projectmanagement.Main;
import test.projectmanagement.ProjectManagementUltility;
import test.projectmanagement.datasource.DataSourceDepartment;

public class DepartmentSearchController {
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
		String departmentId = idText.getText();
		String departmentName = nameText.getText();
		try {
			if (departmentId.isEmpty() && departmentName.isEmpty()) {

			} else if (!departmentId.isEmpty() && !departmentName.isEmpty()) {
				Main.deptID = Integer.parseInt(departmentId);
				Main.deptName = departmentName;
				ProjectManagementUltility.getInstance().loadWindow("department\\DepartmentManagementSearchResult.fxml",
						"application.css", "Department Search Result", false);
			} else if (departmentName.isEmpty()) {
				String result = DataSourceDepartment.getInstance().searchDepartmentID(Integer.parseInt(departmentId));
				nameText.setText(result);
				idText.setDisable(true);
				nameText.setDisable(true);
			} else {
				int result = DataSourceDepartment.getInstance().searchDepartmentName(departmentName);
				idText.setText(String.valueOf(result));
				idText.setDisable(true);
				nameText.setDisable(true);
			}
		} catch (NumberFormatException e) {
			ProjectManagementUltility.getInstance().showAlert(AlertType.WARNING, "Department Search Waring",
					"Department ID cannot be a string.\n\tDepartment ID should be a integer.\nTHANK YOU!!!");
		}

	}

	@FXML
	public void CancelAction() {
		idText.setText("");
		nameText.setText("");
		idText.setPromptText("Nhập lại Department ID");
		nameText.setPromptText("Nhập lại Department Name");
		idText.setDisable(false);
		nameText.setDisable(false);
	}
}
