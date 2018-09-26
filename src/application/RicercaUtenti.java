package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class RicercaUtenti extends Application {
	
	public RicercaUtenti() {
		super();
	}
	
	public RicercaUtenti(boolean storico) {
		RicercaUtentiController.storico = storico;
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		
		Stage s = new Stage();
		
		RicercaUtentiController.primaryStage = primaryStage;
		RicercaUtentiController.s = s;
		
		loader.setLocation(this.getClass().getResource("ricercaUtenti.fxml"));
		AnchorPane pane = (AnchorPane) loader.load();
		Scene scene = new Scene(pane);
		s.setScene(scene);
		Screen screen = Screen.getPrimary();
		Rectangle2D bounds = screen.getVisualBounds();

		s.setX(bounds.getMinX());
		s.setY(bounds.getMinY());
		s.setWidth(bounds.getWidth());
		s.setHeight(bounds.getHeight());
		s.show();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

}
