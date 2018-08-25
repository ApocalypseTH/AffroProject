package application;

import java.sql.Statement;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class RisultatiRicercaAnalisi extends Application {
	
	String query;
	
	public RisultatiRicercaAnalisi(String t) {
		query=t;
	}
	
	@Override
	public void start(Stage primaryStage) {
		try {
			
			
			RisultatiRicercaAnalisiController.query = query;
			RisultatiRicercaAnalisiController.primaryStage = primaryStage;
			
			FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(Main.class.getResource("risultatiRicercaAnalisi.fxml"));
	        AnchorPane pane = (AnchorPane) loader.load();
			Scene scene = new Scene(pane);
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}