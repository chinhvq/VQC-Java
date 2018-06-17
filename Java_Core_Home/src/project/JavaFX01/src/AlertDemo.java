
import java.util.Optional;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class AlertDemo extends Application {

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Aler Example");
		Button button = new Button("Close");
		button.setOnAction(event -> {
			Alert alertConfirmation = new Alert(Alert.AlertType.CONFIRMATION);
			alertConfirmation.setContentText("choose your option");
			alertConfirmation.setTitle("Confirmation");
			alertConfirmation.setHeaderText("Alert Information");
			
			ButtonType buttonTypeYes = new ButtonType("Vâng", ButtonBar.ButtonData.YES);
			ButtonType buttonTypeNo = new ButtonType("Không", ButtonBar.ButtonData.NO);
			ButtonType buttonTypeCancel = new ButtonType("Bỏ Qua", ButtonBar.ButtonData.CANCEL_CLOSE);
			alertConfirmation.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo, buttonTypeCancel);
			//or we can use the default system Button Type
//			alertConfirmation.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
			
			Optional<ButtonType> result = alertConfirmation.showAndWait();
			if (result.get().getButtonData() == ButtonBar.ButtonData.YES) {
				System.out.println("Vâng");
			} else if (result.get().getButtonData() == ButtonBar.ButtonData.NO) {
				System.err.println("Không");
			} else {
				System.out.println("Bỏ Qua");
			}
			
			String message = result.get().getText();
			Alert alertInformation = new Alert(AlertType.INFORMATION);
			alertInformation.setContentText(message);
			alertInformation.setTitle("Information");
			alertInformation.setHeaderText("Notification");
			alertInformation.show();		
			
		});
		
		StackPane stackPanel = new StackPane();
		stackPanel.getChildren().add(button);
		Scene scene = new Scene(stackPanel, 400, 500);
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
