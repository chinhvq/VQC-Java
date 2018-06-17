
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SwapScene extends Application {
	Stage window;
	Scene scene1, scene2;
	
	@Override
	public void start(Stage primaryStage) {
		window = primaryStage;
		Label label1 = new Label("Demo Chuyển Scene - Trang 1");
		Button button1 = new Button("Chuyển sang trang 2");
		button1.setOnAction(event -> {
			primaryStage.setScene(scene2);
		});
		VBox vbox = new VBox(10);
		vbox.getChildren().addAll(label1, button1);
		scene1 = new Scene(vbox, 300, 400);
		
		Label label2 = new Label("Demo Chuyển Scene - Trang 2");
		Button button2 = new Button("Chuyển sang trang 1");
		button2.setOnAction(event -> {
			primaryStage.setScene(scene1);
		});
		HBox hBox = new HBox(10);		
		hBox.getChildren().addAll(label2, button2);
		scene2 = new Scene(hBox, 500, 500);
		
		window.setScene(scene1);
		window.show();
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
