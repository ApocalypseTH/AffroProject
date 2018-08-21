package application;

import java.sql.Statement;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class MessaInFunzione extends Application {
	
	int id;
	
	public MessaInFunzione(int id) {
		// TODO Auto-generated constructor stub
		this.id=id;
	}
	
	@Override
	public void start(Stage primaryStage) {
		try {
			
			Stage s = new Stage();
			
			MessaInFunzioneController.id=id;
			
			FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(Main.class.getResource("messaInFunzione.fxml"));
	        AnchorPane ap = loader.load();
	        BorderPane root = new BorderPane();
	        root.setCenter(ap);

			Scene scene = new Scene(root);
			s.setScene(scene);
			s.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}