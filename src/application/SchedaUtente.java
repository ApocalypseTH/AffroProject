package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class SchedaUtente extends Application {
	
	public SchedaUtente() {
		super();
	}

	public SchedaUtente(int utente) {
		SchedaUtenteController.user = utente;
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader();

		SchedaUtenteController.primaryStage = primaryStage;
		
		loader.setLocation(this.getClass().getResource("schedaUtente.fxml"));
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
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

}
