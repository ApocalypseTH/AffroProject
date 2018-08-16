package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class CercaBruciatore extends Application{

	BruciatoreController bc;
	
	public CercaBruciatore(BruciatoreController bc) {
		this.bc = bc;
	}
	
	@Override
	public void start(Stage primaryStage) {
		try {
			
			Stage s = new Stage();
			
			CercaBruciatoreController.bc = bc;
			
			FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(Main.class.getResource("cercaBruciatore.fxml"));
	        AnchorPane ap = loader.load();
	        BorderPane root = new BorderPane();
	        root.setCenter(ap);

			Scene scene = new Scene(root);
			s.setScene(scene);
			s.show();
			
			HomeController.primaryStage = primaryStage;
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}