package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class Note extends Application {
	
	String testo;
	Integer i;
	String id;
	
	private SchedaUtenteController suc;
	
	public Note(String t, int i, String id, SchedaUtenteController suc) {
		testo=t;
		this.i=i;
		this.id=id;
		this.suc = suc;
	}
	
	@Override
	public void start(Stage secondaryStage) {
		try {
			
			Stage s= new Stage();
			
			NoteController.testo = testo;
			NoteController.i = i;
			NoteController.id = id;
			NoteController.suc = suc;
			
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