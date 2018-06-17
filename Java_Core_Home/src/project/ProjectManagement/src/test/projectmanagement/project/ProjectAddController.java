package test.projectmanagement.project;

import java.sql.SQLException;

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
import test.projectmanagement.datamodel.DataSource;
import test.projectmanagement.datamodel.ProjectStatusEnum;

public class ProjectAddController {
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
		String projectID = idText.getText();
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

		try {
			if (projectName.isEmpty()) {
				showAlert(AlertType.WARNING, "Project Adding Warning", "Project Name cannot be blank");
			} else if (!isStatus) {
				showAlert(AlertType.WARNING, "Project Editing Warning",
						"Please double check the input information for Project Status.\nProject Status should be match with defined status as bellowing\n\tSurvey, Planning, Prototype, Testing,Integration, Done");
			} else if (projectID.isEmpty() && !projectName.isEmpty()) {
				System.out.println(projectStatus);
				int result = DataSource.getInstance().insertProjectName(projectName, projectStatus, projectCategory);
				if (result != 0) {
					showAlert(AlertType.INFORMATION, "Project Adding Successfully",
							"New project has been added into DB with bellowing information:\n\tProject ID = " + result
									+ "\n\tProject Name = " + projectName + "\n\tProject Status = " + projectStatus
									+ "\n\tProject Category = " + projectCategory);
					close();
				} else {
					System.out.println("smth");
				}
			} else if (Integer.parseInt(projectID) == 0) {
				showAlert(AlertType.WARNING, "Project Adding Warning", "Project ID cannot be equal to 0");
			} else {
				if (DataSource.getInstance().insertprojectIDName(Integer.parseInt(projectID), projectName,
						projectStatus, projectCategory)) {
					showAlert(AlertType.INFORMATION, "Project Adding Successfully",
							"New project has been added into DB with bellowing information:\n\tProject ID = "
									+ projectID + "\n\tProject Name = " + projectName + "\n\tProject Status = "
									+ projectStatus + "\n\tProject Category = " + projectCategory);
					close();
				} else {
					showAlert(AlertType.WARNING, "Project Adding Warning",
							"New Project ID " + projectID + " already exist in DB\n" + "Cannot insert new Project");
				}
			}
		} catch (NumberFormatException e) {
			showAlert(AlertType.WARNING, "Project Addition Warning",
					"Please check again Project ID.\n\tProject ID can not be a string.\n\tProject ID should be an integer.\n\tTHANK YOU!!!");
		} catch (SQLException e) {
			showAlert(AlertType.ERROR, "Project Adding Failure", "Please check again!");
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
	}
}
