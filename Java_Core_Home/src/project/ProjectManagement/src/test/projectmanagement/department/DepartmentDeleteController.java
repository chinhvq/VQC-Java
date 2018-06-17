package test.projectmanagement.department;

import java.util.Optional;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import test.projectmanagement.Main;
import test.projectmanagement.datamodel.DataSource;

public class DepartmentDeleteController {
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
		nameText.setDisable(true);
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
		String departmentID = idText.getText();
		String departmentName = nameText.getText();
		deleteAction(Integer.parseInt(departmentID), departmentName);
	}

	public void deleteAction(int departmentID, String departmentName) {
		Alert alertConfirmation = new Alert(Alert.AlertType.CONFIRMATION);
		alertConfirmation
				.setContentText("Are you sure to delete department with bellowing information:\n\tDepartment ID = "
						+ departmentID + "\n\tDepartment Name = " + departmentName);
		alertConfirmation.setTitle("Department Deleting Confirmation");
		alertConfirmation.setHeaderText("Alert Information");
		Optional<ButtonType> result = alertConfirmation.showAndWait();

		if (result.get().getButtonData() == ButtonBar.ButtonData.OK_DONE) {
			if (DataSource.getInstance().deleteDepartment(departmentID)) {
				showAlert(AlertType.INFORMATION, "Department Deleting Successfully",
						"Bellowing department has been delete from DB :\n\tDepartment ID = " + departmentID
								+ "\n\tDepartment Name = " + departmentName);
			} else {
				showAlert(AlertType.ERROR, "Department Deleting Failure", "Deleting department has failed");
			}
		} else {
			showAlert(AlertType.INFORMATION, "Department Deleting Information",
					"You have cancelled the deleting operation.\nTHANK YOU!!!");
		}
	}

	@FXML
	public void CancelAction() {
		close();
	}
}
