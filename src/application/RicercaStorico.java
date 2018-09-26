package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class RicercaStorico extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Stage s = new Stage();
			FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(RicercaStorico.class.getResource("ricercaStorico.fxml"));
	        AnchorPane ap = loader.load();
	        BorderPane root = new BorderPane();
	        root.setCenter(ap);

			Scene scene = new Scene(root);
			s.setScene(scene);
			Screen screen = Screen.getPrimary();
			Rectangle2D bounds = screen.getVisualBounds();

			s.setX(bounds.getMinX());
			s.setY(bounds.getMinY());
			s.setWidth(bounds.getWidth());
			s.setHeight(bounds.getHeight());
			s.show();
			
			RicercaStoricoController.primaryStage = primaryStage;
			RicercaStoricoController.s = s;
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}