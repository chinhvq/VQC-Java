package test.menubar;

import javafx.application.Platform;
import javafx.event.ActionEvent;

public class SampleController {
	public void Action(ActionEvent event) {
		Platform.exit();
		System.exit(0);
	}
}
