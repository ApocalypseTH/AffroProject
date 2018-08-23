package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class CaldaiaAnalisi extends Application {
	
	private int codiceu;
	private AnalisiController ac;
	
	public CaldaiaAnalisi() {
		super();
	}
	
	public CaldaiaAnalisi(int codiceUtente, AnalisiController ac) {
		codiceu = codiceUtente;
		this.ac = ac;
	}

	@Override
	public void start(Stage primaryStage) {
		try {
			
			Stage s = new Stage();
			
			CaldaiaAnalisiController.codiceu = codiceu;
			CaldaiaAnalisiController.ac = this.ac;
			CaldaiaAnalisiController.stage = s;
			
			FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(RicercaAnalisi.class.getResource("caldaiaAnalisi.fxml"));
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
