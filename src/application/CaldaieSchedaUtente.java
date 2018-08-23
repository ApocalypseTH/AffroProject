package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class CaldaieSchedaUtente extends Application {
	
	SchedaUtenteController su;
	String c;
	String m;
	int id;
	
	public CaldaieSchedaUtente(SchedaUtenteController su, String c, String m, int id) {
		this.su=su;
		this.c=c;
		this.m=m;
		this.id=id;
	}
	
	
	@Override
	public void start(Stage primaryStage) {
		try {
			
			Stage s = new Stage();
			
			CaldaieSchedaUtenteController.su = su;
			CaldaieSchedaUtenteController.c=c;
			CaldaieSchedaUtenteController.m=m;
			CaldaieSchedaUtenteController.id=id;
			FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(CaldaieSchedaUtente.class.getResource("caldaieSchedaUtente.fxml"));
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