package test.concurrent.taskbackground;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;

public class SampleController {
	private Task<ObservableList<String>> task;

	@FXML
	private ListView listView;
	@FXML
	private ProgressBar progressBar;
	@FXML
	private Label progressLabel;

	public void initialize() {
		task = new Task<ObservableList<String>>() {
			@Override
			protected ObservableList<String> call() throws Exception {
				String[] names = { "Chinh", "Dung", "Mai Anh", "Tu", "Hoang", "Bao" };
				final ObservableList<String> students = FXCollections.observableArrayList();
				// this way of Platform.runLater is not recommended as we want to seperate the
				// code of UI and the background task
				// Platform.runLater(new Runnable() {
				// @Override
				// public void run() {
				// listView.setItems(students);
				// }
				// });
				for (int i = 0; i < names.length; i++) {
					students.add(names[i]);
					updateProgress(i + 1, names.length);
					updateMessage("Add " + names[i] + " to the list");
					Thread.sleep(200);
				}
				return students;
			}
		};

		progressBar.progressProperty().bind(task.progressProperty());
		progressLabel.textProperty().bind(task.messageProperty());
		// we binding the valueProperty of background Task with itemsProperty of the
		// ListView so that background task will be seperate with UI thread
		listView.itemsProperty().bind(task.valueProperty());

	}

	@FXML
	public void buttonPress() {
		new Thread(task).start();
	}
}
