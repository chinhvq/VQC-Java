package test.todolist;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;
import test.todolist.datamodel.ToDoData;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("mainWindow.fxml"));
			Scene scene = new Scene(root,900,500);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("To Do List");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void stop() throws Exception {
		try {
			ToDoData.getInstance().storeToDoItems();
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}		
	}
	
	@Override
	public void init() throws Exception {
		try {
			ToDoData.getInstance().loadToDoItems();
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}
