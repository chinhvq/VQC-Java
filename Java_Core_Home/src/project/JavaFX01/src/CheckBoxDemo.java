
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CheckBoxDemo extends Application {

	@Override
	public void start(Stage primaryStage) {
		Label label = new Label("Bạn thích ngôn ngữ lập trình nào");
		CheckBox checkBox1 = new CheckBox("Java");
		checkBox1.setSelected(true);
		CheckBox checkBox2 = new CheckBox("C#");
		CheckBox checkBox3 = new CheckBox("Python");
		Button button = new Button("Submit");
		button.setOnAction(event -> {
			String message = "Ngôn ngữ bạn chọn là\t";
			if (checkBox1.isSelected()) {
				message += checkBox1.getText() ;
			} 
			if (checkBox2.isSelected()) {
				message += " & " + checkBox2.getText();
			} 
			if (checkBox3.isSelected()) {
				message += " & " + checkBox3.getText() + "\t";
			} 
			System.out.println(message);
			
			Alert alertInformation = new Alert(AlertType.INFORMATION);
			alertInformation.setTitle("Thông tin");
			alertInformation.setHeaderText("Kết Quả");
			alertInformation.setContentText(message);
			alertInformation.show();
		});
		
		HBox hbox = new HBox(10);
		hbox.getChildren().setAll(checkBox1, checkBox2, checkBox3);
		VBox vbox = new VBox(10);
		vbox.getChildren().setAll(label, hbox, button);
		Scene scene = new Scene(vbox, 300, 400);
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
