package application;

import java.sql.ResultSet;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class RichiestaIntervento extends Application {
	
	String cod;
	String ut;
	String id;
	ResultSet rs;
	int i;
	
	private SchedaUtenteController suc;
	StoricoPerUtenteController spuc;
	public RichiestaIntervento(String id, String cod, String ut, SchedaUtenteController suc) {
		this.cod=cod;
		this.id=id;
		this.ut=ut;
		this.suc = suc;
		i=0;
	}
	public RichiestaIntervento(ResultSet rs, StoricoPerUtenteController spuc) {
		this.rs=rs;
		this.spuc=spuc;
		i=1;
	}
	
	@Override
	public void start(Stage secondaryStage) {
		try {
			
			Stage s= new Stage();
			
			RichiestaInterventoController.cod=cod;
			RichiestaInterventoController.id=id;
			RichiestaInterventoController.ut=ut;
			RichiestaInterventoController.suc = suc;
			RichiestaInterventoController.res=rs;
			RichiestaInterventoController.i=i;
			RichiestaInterventoController.spuc=spuc;
				
			FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(Main.class.getResource("richiestaIntervento.fxml"));
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