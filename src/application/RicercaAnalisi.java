package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class RicercaAnalisi extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			
			FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(RicercaAnalisi.class.getResource("ricercaAnalisi.fxml"));
	        AnchorPane ap = loader.load();
	        BorderPane root = new BorderPane();
	        root.setCenter(ap);

			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
			
			RicercaAnalisiController.primaryStage = primaryStage;
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}