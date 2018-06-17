package test.demo;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class GridPanelDemo extends Application {

	@Override
	public void start(Stage primaryStage) {
		GridPane root = new GridPane();
		root.setAlignment(Pos.CENTER);
		root.setVgap(10);
		root.setHgap(10);
		Scene scene = new Scene(root, 400, 500);
		Label label = new Label("WELCOME TO JAVA-FX");
		label.setTextFill(Color.GREEN);
		label.setFont(Font.font("Time New Roman", FontWeight.EXTRA_BOLD, 20));
		Label label1 = new Label("Let's move on :-)");
		label.setTextFill(Color.GREEN);
		label.setFont(Font.font("Time New Roman", FontWeight.EXTRA_BOLD, 20));
		root.getChildren().addAll(label, label1);

		primaryStage.setTitle("Demo");
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}
}
