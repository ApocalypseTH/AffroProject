package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;


public class StoricoPerUtente extends Application {
	
	public StoricoPerUtente() {
		super();
	}
	
	public StoricoPerUtente(String q) {
		StoricoPerUtenteController.query = q;
	}
	
	public StoricoPerUtente(String q, int codice) {
		StoricoPerUtenteController.query = q;
		StoricoPerUtenteController.codice = codice;
	}
	
	@Override
	public void start(Stage primaryStage) {
		try {
			StoricoPerUtenteController.primaryStage = primaryStage;
			FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(Main.class.getResource("storicoPerUtente.fxml"));
	        AnchorPane pane = (AnchorPane) loader.load();
			Scene scene = new Scene(pane);
			primaryStage.setScene(scene);
			Screen screen = Screen.getPrimary();
			Rectangle2D bounds = screen.getVisualBounds();

			primaryStage.setX(bounds.getMinX());
			primaryStage.setY(bounds.getMinY());
			primaryStage.setWidth(bounds.getWidth());
			primaryStage.setHeight(bounds.getHeight());
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}