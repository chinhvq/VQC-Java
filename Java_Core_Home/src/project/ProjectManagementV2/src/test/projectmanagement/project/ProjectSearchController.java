package test.projectmanagement.project;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Window;
import test.projectmanagement.Main;
import test.projectmanagement.ProjectManagementUltility;
import test.projectmanagement.datasource.DataSourceProject;

public class ProjectSearchController {
	@FXML
	BorderPane mainPane;
	@FXML
	private TextField idText;
	@FXML
	private TextArea nameTextArea;
	@FXML
	private TextField statusText;
	@FXML
	private TextField categoryText;

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
		String projectId = idText.getText();
		String projectName = nameTextArea.getText();
		String projectStatus = statusText.getText();
		String projectCategory = categoryText.getText();
		try {
			if (projectId.isEmpty() && projectName.isEmpty() && projectStatus.isEmpty() && projectCategory.isEmpty()) {

			} else if (!projectId.isEmpty() && projectName.isEmpty() && projectStatus.isEmpty()
					&& projectCategory.isEmpty()) {
				// search project by ID only
				String[] project = DataSourceProject.getInstance().searchProjectID(Integer.parseInt(projectId));
				if (project[0] == null && project[1] == null && project[2] == null) {
					ProjectManagementUltility.getInstance().showAlert(AlertType.ERROR, "Project Searching Failure",
							"Project ID does not exist in DB.\nPlease double check again");
				} else {
					nameTextArea.setText(project[0]);
					statusText.setText(project[1]);
					categoryText.setText(project[2]);
					idText.setDisable(true);
					nameTextArea.setDisable(true);
					statusText.setDisable(true);
					categoryText.setDisable(true);
				}
			} else if (!projectName.isEmpty() && projectId.isEmpty() && projectStatus.isEmpty()
					&& projectCategory.isEmpty()) {
				// search project by name only
				Main.projectName = projectName;
				ProjectManagementUltility.getInstance().loadWindow("project\\ProjectManagementSearchName.fxml",
						"application.css", "Project Searching Name Result", false);
			} else if (!projectStatus.isEmpty() && projectId.isEmpty() && projectName.isEmpty()
					&& projectCategory.isEmpty()) {
				// search project by status only
				Main.projectStatus = projectStatus;
				ProjectManagementUltility.getInstance().loadWindow("project\\ProjectManagementSearchStatus.fxml",
						"application.css", "Project Searching Status Result", false);
			} else if (!projectCategory.isEmpty() && projectId.isEmpty() && projectName.isEmpty()
					&& projectStatus.isEmpty()) {
				// search project by category only
				Main.projectCategory = projectCategory;
				ProjectManagementUltility.getInstance().loadWindow("project\\ProjectManagementSearchCategory.fxml",
						"application.css", "Project Searching Category Result", false);
			} else if (!projectName.isEmpty() && !projectStatus.isEmpty() && projectCategory.isEmpty()
					&& projectId.isEmpty()) {
				// search project by name and status
				Main.projectName = projectName;
				Main.projectStatus = projectStatus;
				ProjectManagementUltility.getInstance().loadWindow("project\\ProjectManagementSearchNameStatus.fxml",
						"application.css", "Project Searching Name and Status Result", false);
			} else if (!projectName.isEmpty() && !projectCategory.isEmpty() && projectId.isEmpty()
					&& projectStatus.isEmpty()) {
				// search project by name and category
				Main.projectName = projectName;
				Main.projectCategory = projectCategory;
				ProjectManagementUltility.getInstance().loadWindow("project\\ProjectManagementSearchNameCategory.fxml",
						"application.css", "Project Searching Name and Category Result", false);
			} else if (!projectStatus.isEmpty() && !projectCategory.isEmpty() && projectId.isEmpty()
					&& projectName.isEmpty()) {
				// search project by status and category
				Main.projectStatus = projectStatus;
				Main.projectCategory = projectCategory;
				ProjectManagementUltility.getInstance().loadWindow(
						"project\\ProjectManagementSearchStatusCategory.fxml", "application.css",
						"Project Searching Status and Category Result", false);
			} else if (Integer.parseInt(projectId) == 0) {
				ProjectManagementUltility.getInstance().showAlert(AlertType.WARNING, "Project Searching Warning",
						"Project ID cannot be equal to 0");
			} else {
				ProjectManagementUltility.getInstance().showAlert(AlertType.INFORMATION,
						"Project Searching Information",
						"The combination of searching criteria will be support soon.\n\tTHANK YOU!!!");
			}
		} catch (NumberFormatException e) {
			ProjectManagementUltility.getInstance().showAlert(AlertType.WARNING, "Project Searching Warning",
					"Please check again Project ID.\n\tProject ID can not be a string.\n\tProject ID should be an integer.\n\tTHANK YOU!!!");
		}

	}

	@FXML
	public void CancelAction() {
		idText.setText("");
		nameTextArea.setText("");
		statusText.setText("");
		categoryText.setText("");
		idText.setPromptText("Nhập lại Project ID");
		nameTextArea.setPromptText("Nhập lại Project Name");
		statusText.setPromptText("Nhập lại Project Status");
		categoryText.setPromptText("Nhập lại Project Category");
		idText.setDisable(false);
		nameTextArea.setDisable(false);
		statusText.setDisable(false);
		categoryText.setDisable(false);
	}
}
