package test.projectmanagement.project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import test.projectmanagement.Main;
import test.projectmanagement.datamodel.DataSource;
import test.projectmanagement.datamodel.ProjectStatusEnum;

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
			showAlert(AlertType.ERROR, "Project Editing Error ", "Project Name cannot be blank");
		} else if (!isStatus) {
			showAlert(AlertType.WARNING, "Project Editing Warning",
					"Please double check the input information for Project Status.\nProject Status should be match with defined status as bellowing\n\tSurvey, Planning, Prototype, Testing,Integration, Done");
		} else {
			if (DataSource.getInstance().updateProject(projectId, projectName, projectStatus, projectCategory)) {
				showAlert(AlertType.INFORMATION, "Project Editing Sucessfully ", "Project ID " + projectId
						+ "\tProject Name " + projectName + " has been successfully updated into DB.\n\tTHANK YOU!!!");
				close();
			} else {
				showAlert(AlertType.ERROR, "Project Editing Failure", "Update not successfully.");
			}
		}
	}

	@FXML
	public void CancelAction() {
		nameTextArea.setText("");
		nameTextArea.setPromptText("Nhập lại Project Name");
		statusText.setText("");
		statusText.setPromptText("Nhập lại Project Status");
		categoryText.setText("");
		categoryText.setPromptText("Nhập lại Project Category");
	}
}
