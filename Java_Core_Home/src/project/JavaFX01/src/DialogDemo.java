
import java.util.Optional;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Pair;

public class DialogDemo extends Application {

	@Override
	public void start(Stage primaryStage) {
		Dialog<Pair<String, String>> dialog = new Dialog<>();
		dialog.setTitle("Login Dialog");
		dialog.setHeaderText("Sign up");

		ButtonType buttonTypeLogin = new ButtonType("Login", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().setAll(buttonTypeLogin, ButtonType.CANCEL);

		GridPane gridPanel = new GridPane();
		gridPanel.setHgap(10);
		gridPanel.setVgap(10);
		gridPanel.setPadding(new Insets(20, 150, 10, 10));
		
		TextField username = new TextField();
		username.setPromptText("Bạn nhập tài khoản");
		PasswordField password = new PasswordField();
		password.setPromptText("Bạn nhập Password");
		
		gridPanel.add(new Label("UserName : "), 0, 0);
		gridPanel.add(username, 1, 0);
		gridPanel.add(new Label("Password : "), 0, 1);
		gridPanel.add(password, 1, 1);
		
		//default set buttonLogin / buttonTypeLogin disable
		Node buttonLogin = dialog.getDialogPane().lookupButton(buttonTypeLogin);
		buttonLogin.setDisable(true);
		
		//if username is not blank -> buttonLogin will be enable
		username.textProperty().addListener((observation, oldValue, newValue) -> {
			buttonLogin.setDisable(newValue.trim().isEmpty());
		});
		
		//add gridPanell into dialog
		dialog.getDialogPane().setContent(gridPanel);
		
		// if buttonLogin is press, we return value of TextField and PasswordField
		dialog.setResultConverter(button -> {
			if(button == buttonTypeLogin) {
				return new Pair<String, String>(username.getText(), password.getText());
			} else {
				return null;
			}			
		});
		
		//Store returned Pair into result Optional and print to console
		Optional<Pair<String, String>> result = dialog.showAndWait();
		result.ifPresent(pair -> {
			System.out.println("Username : " + pair.getKey() + "\tPassword : " + pair.getValue());
			
			Alert alertInformation = new Alert(AlertType.INFORMATION);
			alertInformation.setContentText("Username : " + pair.getKey() + "\tPassword : " + pair.getValue());
			alertInformation.setTitle("Information");
			alertInformation.setHeaderText("Notification");
			alertInformation.show();	
		});			
	}

	public static void main(String[] args) {
		launch(args);
	}
}
