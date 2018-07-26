package application;

import java.sql.Statement;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class Note extends Application {
	
	String testo;
	Statement stm;
	Integer i;
	String id;
	
	public Note(String t, Statement s, int i, String id) {
		testo=t;
		stm=s;
		this.i=i;
		this.id=id;
	}
	
	@Override
	public void start(Stage secondaryStage) {
		try {
			
			Stage s= new Stage();
			
			NoteController.testo = testo;
			NoteController.i = i;
			NoteController.id = id;
			
			FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(Main.class.getResource("note.fxml"));
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