package application;

import java.sql.Statement;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class CercaAmministratori extends Application {
	
	AmministratoriController su;
	AmministratoriSchedaUtenteController s;
	int id;
	
	public CercaAmministratori(AmministratoriController su) {
		this.su=su;
		id=1;
	}
	public CercaAmministratori(AmministratoriSchedaUtenteController su) {
		this.s=su;
		id=0;
	}
	
	@Override
	public void start(Stage primaryStage) {
		try {
			
			Stage s = new Stage();
			
			CercaAmministratoriController.su = su;
			CercaAmministratoriController.s = this.s;
			CercaAmministratoriController.id=id;
			
			FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(Main.class.getResource("cercaAmministratori.fxml"));
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