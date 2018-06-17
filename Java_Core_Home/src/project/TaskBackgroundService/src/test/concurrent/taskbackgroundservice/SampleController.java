package test.concurrent.taskbackgroundservice;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.concurrent.Worker.State;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;

public class SampleController {
	// Instead using Task directly, we use Service to manage the Task so JVM will
	// manage the service/thread for us in runtime
	private Service<ObservableList<String>> service;

	@FXML
	private ListView listView;
	@FXML
	private ProgressBar progressBar;
	@FXML
	private Label progressLabel;

	public void initialize() {
		service = new StudentsService();

		progressBar.progressProperty().bind(service.progressProperty());
		progressLabel.textProperty().bind(service.messageProperty());
		// we binding the valueProperty of background Task with itemsProperty of the
		// ListView so that background task will be seperate with UI thread
		listView.itemsProperty().bind(service.valueProperty());

		// this way to hide and show label and progress bar is not very convinient
		// service.setOnRunning(new EventHandler<WorkerStateEvent>() {
		// @Override
		// public void handle(WorkerStateEvent event) {
		// progressBar.setVisible(true);
		// progressLabel.setVisible(true);
		// }
		// });
		//
		// service.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
		// @Override
		// public void handle(WorkerStateEvent event) {
		// progressBar.setVisible(false);
		// progressLabel.setVisible(false);
		// }
		// });
		// //when load - up set invisible
		// progressBar.setVisible(false);
		// progressLabel.setVisible(false);

		// bind label and progressBar with service running state property. ==> Doing
		// this way is more clean
		progressBar.visibleProperty().bind(service.runningProperty());
		progressLabel.visibleProperty().bind(service.runningProperty());
	}

	@FXML
	public void buttonPress() {
		if (service.getState() == State.SUCCEEDED) {
			service.reset();
			service.start();
		} else if (service.getState() == State.READY) {
			service.start();
		}
	}
}
