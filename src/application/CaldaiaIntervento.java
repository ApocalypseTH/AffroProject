package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class CaldaiaIntervento extends Application {

	private int codiceu;
	private RichiestaInterventoController ric;
	private StoricoPerUtenteController spuc;
	private int i;
	
	public CaldaiaIntervento() {
		super();
	}

	public CaldaiaIntervento(int codiceu, RichiestaInterventoController ric) {
		this.codiceu = codiceu;
		this.ric = ric;
		i=0;
	}
	
	public CaldaiaIntervento(int codiceu, StoricoPerUtenteController spuc) {
		this.codiceu = codiceu;
		this.spuc = spuc;
		i=1;
	}
	
	@Override
	public void start(Stage primaryStage) {
		
		try {
			Stage s= new Stage();
			
			CaldaiaInterventoController.codiceu = this.codiceu;
			CaldaiaInterventoController.ric = this.ric;
			CaldaiaInterventoController.spuc = this.spuc;
			CaldaiaInterventoController.i = this.i;
			
			FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(Main.class.getResource("CaldaiaIntervento.fxml"));
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
