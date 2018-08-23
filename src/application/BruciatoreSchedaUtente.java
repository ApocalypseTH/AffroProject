package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class BruciatoreSchedaUtente extends Application{
	
SchedaUtenteController su;
 int id;
 String c;
 String m;
	
	public BruciatoreSchedaUtente(SchedaUtenteController su, String c, String m, int i) {
		this.su=su;
		this.c=c;
		this.m=m;
		id=i;
	}

	@Override
	public void start(Stage primaryStage) {
		try {

			Stage s = new Stage();
			
			BruciatoreSchedaUtenteController.su = su;
			BruciatoreSchedaUtenteController.c = c;
			BruciatoreSchedaUtenteController.m=m;
			BruciatoreSchedaUtenteController.id = id;
			FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(BruciatoreSchedaUtente.class.getResource("bruciatoreSchedaUtente.fxml"));
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
