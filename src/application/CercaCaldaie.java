package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class CercaCaldaie extends Application {
	
	CaldaieController su;
	CaldaieSchedaUtenteController s;
	int id;
	
	public CercaCaldaie(CaldaieController su) {
		this.su=su;
		id=1;
	}
	public CercaCaldaie(CaldaieSchedaUtenteController s) {
		this.s=s;
		id=0;
	}
	
	@Override
	public void start(Stage primaryStage) {
		try {
			
			Stage s = new Stage();
			
			CercaCaldaieController.su = su;
			CercaCaldaieController.s=this.s;
			CercaCaldaieController.id=id;
			
			FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(Main.class.getResource("cercaCaldaie.fxml"));
	        AnchorPane ap = loader.load();
	        BorderPane root = new BorderPane();
	        root.setCenter(ap);

			Scene scene = new Scene(root);
			s.setScene(scene);
			s.show();
			
			HomeController.primaryStage = primaryStage;
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

}