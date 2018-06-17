package test.projectmanagement.project;

import java.util.Optional;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import test.projectmanagement.Main;
import test.projectmanagement.datamodel.DataSource;

public class ProjectDeleteController {
	@FXML
	BorderPane mainPane;
	@FXML
	private TextField idText;
	@FXML
	private TextField statusText;
	@FXML
	private TextField categoryText;
	@FXML
	private TextArea nameTextArea;

	public void initialize() {
		idText.setText(String.valueOf(Main.projectID));
		idText.setDisable(true);
		nameTextArea.setText(Main.projectName);
		nameTextArea.setDisable(true);
		statusText.setText(Main.projectStatus);
		statusText.setDisable(true);
		categoryText.setText(Main.projectCategory);
		categoryText.setDisable(true);

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
		String projectID = idText.getText();
		String projectName = nameTextArea.getText();
		String projectStatus = statusText.getText();
		String projectCategory = categoryText.getText();
		deleteAction(Integer.parseInt(projectID), projectName, projectStatus, projectCategory);
	}

	public void deleteAction(int projectID, String projectName, String projectStatus, String projectCategory) {
		Alert alertConfirmation = new Alert(Alert.AlertType.CONFIRMATION);
		alertConfirmation.setContentText("Are you sure to delete project with bellowing information:\n\tProject ID = "
				+ projectID + "\n\tProject Name = " + projectName + "\n\tProject Staus = " + projectStatus
				+ "\n\tProject Category = " + projectCategory);
		alertConfirmation.setTitle("Project Deleting Confirmation");
		alertConfirmation.setHeaderText("Alert Information");
		Optional<ButtonType> result = alertConfirmation.showAndWait();

		if (result.get().getButtonData() == ButtonBar.ButtonData.OK_DONE) {
			int deleteResult = DataSource.getInstance().deleteProject(projectID);
			if (deleteResult == 1) {
				showAlert(AlertType.INFORMATION, "Project Deleting Successfully",
						"Bellowing Project has been delete from DB :\n\tProject ID = " + projectID
								+ "\n\tProject Name = " + projectName + "\n\tProject Staus = " + projectStatus
								+ "\n\tProject Category = " + projectCategory);
			} else if (deleteResult == 2) {
				showAlert(AlertType.WARNING, "Project Deleting Warning", "Project ID = " + projectID
						+ " has a reference on another customer.\nCannot delete the project.\nTHANK YOU!!!");
			} else if (deleteResult == 3) {
				showAlert(AlertType.WARNING, "Project Deleting Warning", "Project ID = " + projectID
						+ " has a reference on another employee.\nCannot delete the project.\nTHANK YOU!!!");
			} else {
				showAlert(AlertType.ERROR, "Project Deleting Failure", "Deleting Project has failed");
			}
		} else {
			showAlert(AlertType.INFORMATION, "Project Deleting Information",
					"You have cancelled the deleting operation.\nTHANK YOU!!!");
		}
	}

	@FXML
	public void CancelAction() {
		close();
	}
}
