package application;

import java.sql.Statement;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class CercaInstallatori extends Application {
	
	InstallatoriController su;
	InstallatoriSchedaUtenteController s;
	int id;
	
	public CercaInstallatori(InstallatoriController su) {
		this.su=su;
		id=1;
	}
	public CercaInstallatori(InstallatoriSchedaUtenteController su) {
		this.s=su;
		id=0;
	}
	
	@Override
	public void start(Stage primaryStage) {
		try {
			
			Stage s = new Stage();
			
			CercaInstallatoriController.su = su;
			CercaInstallatoriController.s = this.s;
			CercaInstallatoriController.id=id;
			
			FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(Main.class.getResource("cercaInstallatori.fxml"));
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