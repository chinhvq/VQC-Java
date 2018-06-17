package test.projectmanagement.project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Window;
import test.projectmanagement.Main;
import test.projectmanagement.ProjectManagementUltility;
import test.projectmanagement.datamodel.Project;
import test.projectmanagement.datasource.DataSourceProject;

public class ProjectController {
	@FXML
	BorderPane mainPane;
	@FXML
	private ProgressBar progressBar;
	@FXML
	private TableView<Project> table;
	@FXML
	private Label progressLabel;
	@FXML
	private Label countLabel;
	@FXML
	private TableColumn<Project, String> projectName;
	@FXML
	private ContextMenu listContextMenu;

	@FXML
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
				Project item = table.getSelectionModel().getSelectedItem();
				Main.projectID = item.getId();
				Main.projectName = item.getProjectName();
				Main.projectStatus = item.getStatus();
				Main.projectCategory = item.getProjectCategory();
				ProjectManagementUltility.getInstance().loadWindow("project\\ProjectManagementEdit.fxml",
						"application.css", "Project Editing", false);
			}
		});

		deleteMenuItem.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Project item = table.getSelectionModel().getSelectedItem();
				int projectID = item.getId();
				String projectName = item.getProjectName();
				String projectStatus = item.getStatus();
				String projectCategory = item.getProjectCategory();
				ProjectDeleteController controller = new ProjectDeleteController();
				controller.deleteAction(projectID, projectName, projectStatus, projectCategory);
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

	@FXML
	public void aboutShow() {
		ProjectManagementUltility.getInstance().loadWindow("AboutDialog.fxml", "application.css", "About", false);
	}

	@FXML
	public void listShow() {
		progressLabel.setText("LOADING ...");
		countLabel.setText("");
		Task<ObservableList<Project>> task = new ListProject();
		table.itemsProperty().bind(task.valueProperty());
		progressBar.progressProperty().bind(task.progressProperty());
		progressBar.setVisible(true);
		task.setOnSucceeded(e -> {
			progressBar.setVisible(false);
			progressLabel.setText("LOAD COMPLETED");
			countLabel.setText(
					"Number of return records = " + String.valueOf(DataSourceProject.getInstance().queryProjectCount()));
		});
		task.setOnFailed(e -> {
			progressBar.setVisible(false);
			progressLabel.setText("LOADING FAILED");
		});
		new Thread(task).start();
	}

	@FXML
	public void addShow() {
		ProjectManagementUltility.getInstance().loadWindow("project\\ProjectManagementAdd.fxml", "application.css",
				"Project Adding", false);
	}

	@FXML
	public void editShow() {
		if (table.getSelectionModel().isEmpty()) {
			ProjectManagementUltility.getInstance().showAlert(AlertType.WARNING, "Project Editing",
					"Please select a Project to edit.\nTHANK YOU!");
		} else {
			Main.projectID = table.getSelectionModel().getSelectedItem().getId();
			Main.projectName = table.getSelectionModel().getSelectedItem().getProjectName();
			Main.projectStatus = table.getSelectionModel().getSelectedItem().getStatus();
			Main.projectCategory = table.getSelectionModel().getSelectedItem().getProjectCategory();
			ProjectManagementUltility.getInstance().loadWindow("project\\ProjectManagementEdit.fxml", "application.css",
					"Project Editing", false);
		}
	}

	@FXML
	public void deleteShow() {
		if (table.getSelectionModel().isEmpty()) {
			ProjectManagementUltility.getInstance().showAlert(AlertType.WARNING, "Project Deleting",
					"Please select a project to delete.\nTHANK YOU!!!");
		} else {
			Main.projectID = table.getSelectionModel().getSelectedItem().getId();
			Main.projectName = table.getSelectionModel().getSelectedItem().getProjectName();
			Main.projectStatus = table.getSelectionModel().getSelectedItem().getStatus();
			Main.projectCategory = table.getSelectionModel().getSelectedItem().getProjectCategory();
			ProjectManagementUltility.getInstance().loadWindow("project\\ProjectManagementDelete.fxml",
					"application.css", "Project Deleting", false);
		}
	}

	@FXML
	public void searchShow() {
		ProjectManagementUltility.getInstance().loadWindow("project\\ProjectManagementSearch.fxml", "application.css",
				"Project Searching", false);
	}

}

class ListProject extends Task<ObservableList<Project>> {
	@Override
	public ObservableList<Project> call() throws Exception {
		return FXCollections.observableArrayList((DataSourceProject.getInstance().listAllProject()));
	}

}
