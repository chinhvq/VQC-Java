package test.eventdemo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class SampleController {
	@FXML
	private TextField Height;
	
	public void submit(ActionEvent event) {
		String height = Height.getText();
		
		Alert alertInfo = new Alert(AlertType.INFORMATION);
		alertInfo.setContentText("CHIỀU CAO CỦA BẠN LÀ " + height + " cm");
		alertInfo.show();
	}
}
