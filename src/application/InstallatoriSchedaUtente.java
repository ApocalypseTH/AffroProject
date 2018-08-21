package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class InstallatoriSchedaUtente extends Application {
	
	SchedaUtenteController su;
	
	public InstallatoriSchedaUtente(SchedaUtenteController su) {
		this.su=su;
	}
	
	@Override
	public void start(Stage primaryStage) {
		try {
			
			Stage s = new Stage();
			
			InstallatoriSchedaUtenteController.su = su;
			FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(InstallatoriSchedaUtente.class.getResource("installatoriSchedaUtente.fxml"));
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