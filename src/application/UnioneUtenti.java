package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class UnioneUtenti extends Application{

	public UnioneUtenti() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			
			FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(RicercaAnalisi.class.getResource("unioneUtenti.fxml"));
	        AnchorPane ap = loader.load();
	        BorderPane root = new BorderPane();
	        root.setCenter(ap);

			Scene scene = new Scene(root);
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
