package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Analisi extends Application{
	
	public Analisi() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Analisi(int codiceu) {
		AnalisiController.codiceu = codiceu;
	}

	@Override
	public void start(Stage primaryStage) {
		try {
			
			FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(RicercaAnalisi.class.getResource("analisi.fxml"));
	        AnchorPane ap = loader.load();
	        BorderPane root = new BorderPane();
	        root.setCenter(ap);

			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			Screen screen = Screen.getPrimary();
			Rectangle2D bounds = screen.getVisualBounds();

			primaryStage.setX(bounds.getMinX());
			primaryStage.setY(bounds.getMinY());
			primaryStage.setWidth(bounds.getWidth());
			primaryStage.setHeight(bounds.getHeight());
			primaryStage.show();
			
			AnalisiController.primaryStage = primaryStage;
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
