package test.database.musicdbmanagement;
	
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import test.database.musicdbmanagement.model.DataSource;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("MusicDB.fxml"));
			BorderPane root = loader.load();
			MusicDBController controller = loader.getController();
			controller.listArtilist();
			
			Scene scene = new Scene(root,800,600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Music DataBase Management");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void init() throws Exception {
		super.init();
		if(!DataSource.getInstance().open()) {
			System.out.println("Fatal Error - Cannot connect to DB");
			Platform.exit();
		} else {
			System.out.println("Connected to DB");
		}
	}

	@Override
	public void stop() throws Exception {
		super.stop();
		DataSource.getInstance().close();
	}
	
}
