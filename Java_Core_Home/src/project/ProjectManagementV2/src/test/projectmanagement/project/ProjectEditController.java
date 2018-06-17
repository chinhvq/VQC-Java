package test.projectmanagement.project;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Window;
import test.projectmanagement.Main;
import test.projectmanagement.ProjectManagementUltility;
import test.projectmanagement.datamodel.ProjectStatusEnum;
import test.projectmanagement.datasource.DataSourceProject;

public class ProjectEditController {
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

	public void initialize() {
		idText.setText(String.valueOf(Main.projectID));
		idText.setDisable(true);
		nameTextArea.setText(Main.projectName);
		statusText.setText(Main.projectStatus);
		categoryText.setText(Main.projectCategory);
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
		int projectId = Integer.parseInt(idText.getText());
		String projectName = nameTextArea.getText();
		String projectStatus = statusText.getText();
		String projectCategory = categoryText.getText();

		boolean isStatus = false;
		for (ProjectStatusEnum status : ProjectStatusEnum.values()) {
			if (projectStatus.equalsIgnoreCase(status.name())) {
				isStatus = true;
				break;
			}
		}

		if (projectName.isEmpty()) {
			ProjectManagementUltility.getInstance().showAlert(AlertType.ERROR, "Project Editing Error ", "Project Name cannot be blank");
		} else if (!isStatus) {
			ProjectManagementUltility.getInstance().showAlert(AlertType.WARNING, "Project Editing Warning",
					"Please double check the input information for Project Status.\nProject Status should be match with defined status as bellowing\n\tSurvey, Planning, Prototype, Testing,Integration, Done");
		} else {
			if (DataSourceProject.getInstance().updateProject(projectId, projectName, projectStatus, projectCategory)) {
				ProjectManagementUltility.getInstance().showAlert(AlertType.INFORMATION, "Project Editing Sucessfully ", "Project ID " + projectId
						+ "\tProject Name " + projectName + " has been successfully updated into DB.\n\tTHANK YOU!!!");
				close();
			} else {
				ProjectManagementUltility.getInstance().showAlert(AlertType.ERROR, "Project Editing Failure", "Update not successfully.");
			}
		}
	}

	@FXML
	public void CancelAction() {
		nameTextArea.setText("");
		statusText.setText("");
		categoryText.setText("");
		nameTextArea.setPromptText("Nhập lại Project Name");
		statusText.setPromptText("Nhập lại Project Status");
		categoryText.setPromptText("Nhập lại Project Category");
	}
}
