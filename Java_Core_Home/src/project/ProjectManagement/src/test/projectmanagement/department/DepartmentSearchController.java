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

	private void showAlert(AlertType alertType, String alertTitle, String alertContext) {
		Alert alertInfo = new Alert(alertType);
		alertInfo.setContentText(alertContext);
		alertInfo.setTitle(alertTitle);
		alertInfo.show();
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

	@FXML
	public void OkAction() {
		String departmentId = idText.getText();
		String departmentName = nameText.getText();
		try {
			if (departmentId.isEmpty() && departmentName.isEmpty()) {

			} else if (!departmentId.isEmpty() && !departmentName.isEmpty()) {
				Main.deptID = Integer.parseInt(departmentId);
				Main.deptName = departmentName;
				loadWindow("DepartmentManagementSearchResult.fxml", "..\\application.css", "Department Search Result",
						false);
			} else if (departmentName.isEmpty()) {
				String result = DataSource.getInstance().searchDepartmentID(Integer.parseInt(departmentId));
				nameText.setText(result);
				idText.setDisable(true);
				nameText.setDisable(true);
			} else {
				int result = DataSource.getInstance().searchDepartmentName(departmentName);
				idText.setText(String.valueOf(result));
				idText.setDisable(true);
				nameText.setDisable(true);
			}
		} catch (NumberFormatException e) {
			showAlert(AlertType.WARNING, "Department Search Waring",
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
