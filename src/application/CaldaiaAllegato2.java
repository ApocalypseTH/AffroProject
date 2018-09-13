package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class CaldaiaAllegato2 extends Application{

	private int codice;
	private StoricoPerUtenteController spuc;
	
	public CaldaiaAllegato2(int codice, StoricoPerUtenteController spuc) {
		this.codice = codice;
		this.spuc = spuc;
	}
	
	public CaldaiaAllegato2() {
		super();
	}

	@Override
	public void start(Stage primaryStage) {
		
		try {
			
			CaldaiaAllegato2Controller.codice = this.codice;
			CaldaiaAllegato2Controller.spuc = this.spuc;
			
			FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(Main.class.getResource("caldaiaAllegato2.fxml"));
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
