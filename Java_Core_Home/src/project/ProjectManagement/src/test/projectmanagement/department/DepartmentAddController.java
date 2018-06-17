package test.projectmanagement.department;

import java.sql.SQLException;

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
import test.projectmanagement.datamodel.DataSource;

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
		String departmentName = nameText.getText();
		String departmentID = idText.getText();

		try {
			if (departmentName.isEmpty()) {
				showAlert(AlertType.WARNING, "Department Adding Warning", "Department Name cannot be blank");
			} else if (departmentID.isEmpty() && !departmentName.isEmpty()) {
				int result = DataSource.getInstance().insertDepartmentName(departmentName);
				if (result != 0) {
					String alertContext = "New Department has been successfully added into DB\nDepartment ID = "
							+ result + "\nDepartment Name = " + departmentName;
					showAlert(AlertType.INFORMATION, "Department Adding Successfully", alertContext);
				} else {
					System.out.println("smth");
				}
			} else if (Integer.parseInt(departmentID) == 0) {
				showAlert(AlertType.WARNING, "Department Adding Warning", "Department ID cannot be equal to 0");
			} else {
				if (DataSource.getInstance().insertDepartmentIdName(Integer.parseInt(departmentID), departmentName)) {
					String alertContext = "New Department has been successfully added into DB\nDepartment ID = "
							+ departmentID + "\nDepartment Name = " + departmentName;
					showAlert(AlertType.INFORMATION, "Department Adding Successfully", alertContext);
				} else {
					showAlert(AlertType.WARNING, "Department Adding Warning", "New Department ID " + departmentID
							+ " already exist in DB\n" + "Cannot insert new Department");
				}
			}
		} catch (NumberFormatException e) {
			showAlert(AlertType.WARNING, "Department Addition Warning",
					"Please check again Department ID.\n\tDepartment ID can not be a string.\n\t Department ID should be an integer.\n\tTHANK YOU!!!");
		} catch (SQLException e) {
			showAlert(AlertType.ERROR, "Department Adding Failure", "Please check again!");
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
