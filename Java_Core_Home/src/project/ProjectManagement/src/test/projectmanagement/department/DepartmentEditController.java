package test.projectmanagement.department;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import test.projectmanagement.Main;
import test.projectmanagement.datamodel.DataSource;

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
		int departmentID = Integer.parseInt(idText.getText());
		String departmentName = nameText.getText();
		if (departmentName.isEmpty()) {
			showAlert(AlertType.ERROR, "Department Editing Error ", "Deparment Name cannot be blank");
		} else {
			if (DataSource.getInstance().updateDepartment(departmentID, departmentName)) {
				showAlert(AlertType.INFORMATION, "Department Editing Sucessfully ",
						"Department " + String.valueOf(Main.deptName) + " has been updated successfully\n"
								+ "New department name is " + departmentName);				
				close();
			} else {
				showAlert(AlertType.ERROR, "Department Editing Failure", "Update not successfully.");
			}
		}
	}

	@FXML
	public void CancelAction() {
		nameText.setText("");
		nameText.setPromptText("Nhập lại Department Name");
	}
}
