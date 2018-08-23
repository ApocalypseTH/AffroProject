package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class AmministratoriSchedaUtente extends Application {
	
	SchedaUtenteController su;
	String a;
	public AmministratoriSchedaUtente(SchedaUtenteController su, String a) {
		this.su=su;
		this.a=a;
	}
	
	@Override
	public void start(Stage primaryStage) {
		try {
			
			Stage s = new Stage();
			
			AmministratoriSchedaUtenteController.su = su;
			AmministratoriSchedaUtenteController.a=a;
			FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(AmministratoriSchedaUtente.class.getResource("amministratoriSchedaUtente.fxml"));
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