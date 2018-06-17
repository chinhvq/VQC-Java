package test.projectmanagement.log;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Window;
import test.projectmanagement.ProjectManagementUltility;
import test.projectmanagement.datamodel.LogOperation;
import test.projectmanagement.datasource.DataSource;

public class LogManagementController {
	@FXML
	BorderPane mainPane;
	@FXML
	private ProgressBar progressBar;
	@FXML
	private TableView<LogOperation> table;
	@FXML
	private Label progressLabel;
	@FXML
	private Label countLabel;

	@FXML

	public void initialize() {
		// String windowsUser = System.getProperty("user.name");
		// String windowsDomain = System.getenv("USERDOMAIN");
		// System.out.println("User =\t" + windowsDomain + "\\" + windowsUser);
		listShow();
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
		Task<ObservableList<LogOperation>> task = new LogShow();
		table.itemsProperty().bind(task.valueProperty());
		progressBar.progressProperty().bind(task.progressProperty());
		progressBar.setVisible(true);
		task.setOnSucceeded(e -> {
			progressBar.setVisible(false);
			progressLabel.setText("LOAD COMPLETED");
			countLabel.setText("Number of return records = " + String.valueOf(DataSource.getInstance().listLogCount()));
		});
		task.setOnFailed(e -> {
			progressBar.setVisible(false);
			progressLabel.setText("LOADING FAILED ...");
		});
		new Thread(task).start();
	}
}

class LogShow extends Task<ObservableList<LogOperation>> {
	@Override
	public ObservableList<LogOperation> call() throws Exception {
		return FXCollections.observableArrayList((DataSource.getInstance().listAllLog()));
	}
}
