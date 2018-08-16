package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Bruciatore extends Application{

	@Override
	public void start(Stage primaryStage) {
		try {
			
			FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(Bruciatore.class.getResource("bruciatore.fxml"));
	        AnchorPane ap = loader.load();
	        BorderPane root = new BorderPane();
	        root.setCenter(ap);

			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
			
			BruciatoreController.primaryStage = primaryStage;
			
		} catch(Exception e) {
			e.printStackTrace();
		}		
	}

	public static void main(String[] args) {
		launch(args);
	}
	
}
