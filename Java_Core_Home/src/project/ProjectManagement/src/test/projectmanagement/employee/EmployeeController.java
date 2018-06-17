package test.projectmanagement.employee;

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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import test.projectmanagement.Main;
import test.projectmanagement.datamodel.DataSource;
import test.projectmanagement.datamodel.Employee;

public class EmployeeController {
	@FXML
	BorderPane mainPane;
	@FXML
	private ProgressBar progressBar;
	@FXML
	private TableView<Employee> table;
	@FXML
	private Label progressLabel;
	@FXML
	private Label countLabel;
	@FXML
	private TableColumn<Employee, String> firstName;
	@FXML
	private TableColumn<Employee, String> lastName;
	@FXML
	private TableColumn<Employee, String> email;
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
				Employee item = table.getSelectionModel().getSelectedItem();
				Main.EmployeeID = item.getId();
				Main.lastname = item.getLastName();
				Main.firstName = item.getFirstname();
				Main.gender = item.getGender();
				Main.phonenumber = item.getPhoneNumber();
				Main.email = item.getEmail();
				Main.age = item.getAge();
				Main.title = item.getTitle();
				loadWindow("EmployeeManagementEdit.fxml", "..\\application.css", "Employee Editing", false);
			}
		});

		deleteMenuItem.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Employee item = table.getSelectionModel().getSelectedItem();
				int employeeID = item.getId();
				String lastname = item.getLastName();
				String firstname = item.getFirstname();
				String gender = item.getGender();
				String phonenumber = item.getPhoneNumber();
				String email = item.getEmail();
				int age = item.getAge();
				String title = item.getTitle();

				EmployeeDeleteController controller = new EmployeeDeleteController();
				controller.deleteAction(employeeID, lastname, firstname, gender, phonenumber, email, age, title);
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
	private void close() {
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
	public void listShow() {
		progressLabel.setText("LOADING ...");
		countLabel.setText("");
		Task<ObservableList<Employee>> task = new ListEmployee();
		table.itemsProperty().bind(task.valueProperty());
		progressBar.progressProperty().bind(task.progressProperty());
		progressBar.setVisible(true);
		task.setOnSucceeded(e -> {
			progressBar.setVisible(false);
			progressLabel.setText("LOAD COMPLETED");
			countLabel.setText(
					"Number of return records = " + String.valueOf(DataSource.getInstance().queryEmployeeCount()));
		});
		task.setOnFailed(e -> {
			progressBar.setVisible(false);
			progressLabel.setText("LOADING FAILED");
		});
		new Thread(task).start();
	}

	@FXML
	public void addShow() {
		loadWindow("EmployeeManagementAdd.fxml", "..\\application.css", "Employee Adding", false);
	}

	@FXML
	public void editShow() {
		if (table.getSelectionModel().isEmpty()) {
			showAlert(AlertType.WARNING, "Employee Editing", "Please select a Employee to edit.\nTHANK YOU!");
		} else {
			Main.EmployeeID = table.getSelectionModel().getSelectedItem().getId();
			Main.lastname = table.getSelectionModel().getSelectedItem().getLastName();
			Main.firstName = table.getSelectionModel().getSelectedItem().getFirstname();
			Main.gender = table.getSelectionModel().getSelectedItem().getGender();
			Main.phonenumber = table.getSelectionModel().getSelectedItem().getPhoneNumber();
			Main.email = table.getSelectionModel().getSelectedItem().getEmail();
			Main.age = table.getSelectionModel().getSelectedItem().getAge();
			Main.title = table.getSelectionModel().getSelectedItem().getTitle();
			loadWindow("EmployeeManagementEdit.fxml", "..\\application.css", "Employee Editing", false);
		}
	}

	@FXML
	public void deleteShow() {
		if (table.getSelectionModel().isEmpty()) {
			showAlert(AlertType.WARNING, "Employee Deleting", "Please select an employee to delete.\nTHANK YOU!!!");
		} else {
			Main.EmployeeID = table.getSelectionModel().getSelectedItem().getId();
			Main.lastname = table.getSelectionModel().getSelectedItem().getLastName();
			Main.firstName = table.getSelectionModel().getSelectedItem().getFirstname();
			Main.gender = table.getSelectionModel().getSelectedItem().getGender();
			Main.phonenumber = table.getSelectionModel().getSelectedItem().getPhoneNumber();
			Main.email = table.getSelectionModel().getSelectedItem().getEmail();
			Main.age = table.getSelectionModel().getSelectedItem().getAge();
			Main.title = table.getSelectionModel().getSelectedItem().getTitle();

			loadWindow("EmployeeManagementDelete.fxml", "..\\application.css", "Employee Deleting", false);
		}
	}

	@FXML
	public void searchShow() {
	}

}

class ListEmployee extends Task<ObservableList<Employee>> {
	@Override
	public ObservableList<Employee> call() throws Exception {
		return FXCollections.observableArrayList((DataSource.getInstance().listAllEmployee()));
	}

}
