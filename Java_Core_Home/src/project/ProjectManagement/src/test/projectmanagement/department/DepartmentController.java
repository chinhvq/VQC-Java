package test.projectmanagement.department;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import test.projectmanagement.Main;
import test.projectmanagement.datamodel.DataSource;
import test.projectmanagement.datamodel.Department;

public class DepartmentController {
	@FXML
	BorderPane mainPane;
	@FXML
	private ProgressBar progressBar;
	@FXML
	private TableView<Department> table;
	@FXML
	private Label progressLabel;
	@FXML
	private Label countLabel;
	@FXML
	private ContextMenu listContextMenu;

	public void initialize() {
		listShow();
		contextMenuAction();
	}

	private void contextMenuAction() {
		listContextMenu = new ContextMenu();
		MenuItem editMenuItem = new MenuItem("Edit");
		MenuItem deleteMenuItem = new MenuItem("Delete");
		
		editMenuItem.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Main.deptID = table.getSelectionModel().getSelectedItem().getId();
				Main.deptName = table.getSelectionModel().getSelectedItem().getDeptName();
				loadWindow("DepartmentManagementEdit.fxml", "..\\application.css", "Department Editing", false);
			}
		});
		
		deleteMenuItem.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Department item = table.getSelectionModel().getSelectedItem();
				int departmentID = item.getId();
				String departmentName = item.getDeptName();
				DepartmentDeleteController controller = new DepartmentDeleteController();
				controller.deleteAction(departmentID, departmentName);
				listShow();
			}
		});

		listContextMenu.getItems().addAll(editMenuItem, deleteMenuItem);

		table.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent t) {
				if (t.getButton() == MouseButton.SECONDARY) {
					listContextMenu.show(table, t.getScreenX(), t.getScreenY());
				}
			}
		});
	}

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
	public void addShow() {
		loadWindow("DepartmentManagementAdd.fxml", "..\\application.css", "Department Adding", false);
	}

	@FXML
	public void editShow() {
		// check if any item in Table View is selected
		if (table.getSelectionModel().isEmpty()) {
			showAlert(AlertType.WARNING, "Department Editing", "Please select a department to edit.\nTHANK YOU!");
		} else {
			Main.deptID = table.getSelectionModel().getSelectedItem().getId();
			Main.deptName = table.getSelectionModel().getSelectedItem().getDeptName();
			loadWindow("DepartmentManagementEdit.fxml", "..\\application.css", "Department Editing", false);
		}
	}

	@FXML
	public void deleteShow() {
		if (table.getSelectionModel().isEmpty()) {
			showAlert(AlertType.WARNING, "Department Deleting", "Please select a department to delete.\nTHANK YOU!");
		} else {
			Main.deptID = table.getSelectionModel().getSelectedItem().getId();
			Main.deptName = table.getSelectionModel().getSelectedItem().getDeptName();
			loadWindow("DepartmentManagementDelete.fxml", "..\\application.css", "Department Deleting", false);
		}
	}

	@FXML
	public void searchShow() {
		loadWindow("DepartmentManagementSearch.fxml", "..\\application.css", "Department Searching", false);
	}

	@FXML
	public void listShow() {
		progressLabel.setText("LOADING ...");
		countLabel.setText("");
		Task<ObservableList<Department>> task = new ListDepartment();
		table.itemsProperty().bind(task.valueProperty());
		progressBar.progressProperty().bind(task.progressProperty());
		progressBar.setVisible(true);
		task.setOnSucceeded(e -> {
			progressBar.setVisible(false);
			progressLabel.setText("LOAD COMPLETED");
			countLabel.setText(
					"Number of return records = " + String.valueOf(DataSource.getInstance().queryDepartmentCount()));
		});
		task.setOnFailed(e -> {
			progressBar.setVisible(false);
			progressLabel.setText("LOADING FAILED ...");
		});
		new Thread(task).start();
	}
}

class ListDepartment extends Task<ObservableList<Department>> {
	@Override
	public ObservableList<Department> call() throws Exception {
		return FXCollections.observableArrayList((DataSource.getInstance().listAllDepartment()));
	}

}