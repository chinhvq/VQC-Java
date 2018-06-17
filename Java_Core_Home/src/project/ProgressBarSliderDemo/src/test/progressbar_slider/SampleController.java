package test.progressbar_slider;

import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.text.NumberFormatter;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.NumberStringConverter;

public class SampleController implements Initializable {
	@FXML
	ProgressBar progressBar;
	@FXML
	ProgressIndicator progressIndicator;
	@FXML
	Label label;
	@FXML
	Slider slider;
	@FXML
	TextField textField;
	static final double INIT_VALUE = 100;
	DoWork task;
	
	public void start() {
		task = new DoWork();
		label.textProperty().bind(task.messageProperty());
		progressBar.progressProperty().bind(task.progressProperty());
		progressIndicator.progressProperty().bind(task.progressProperty());
		new Thread(task).start();		
	}
	
	public void cancel() {
		label.textProperty().unbind();
		label.setText("Ready");
		progressBar.progressProperty().unbind();
		progressBar.setProgress(0);
		progressIndicator.progressProperty().unbind();
		progressIndicator.setProgress(0);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//slider and textField
		slider.setValue(INIT_VALUE);
		slider.setMax(200d);
		textField.setText(String.valueOf(INIT_VALUE));
		textField.textProperty().bindBidirectional(slider.valueProperty(), new NumberStringConverter());
	}
}

class DoWork extends Task<Void>{

	@Override
	protected Void call() throws Exception {
		for(int i = 0; i < 100; i++) {
			if(isCancelled()) {
				updateMessage("Cancel");
			}
			updateProgress(i+1, 100);
			updateMessage("In progress");
			Thread.sleep(100);
		}
		updateMessage("Completed");
		return null;
	}
	
}
