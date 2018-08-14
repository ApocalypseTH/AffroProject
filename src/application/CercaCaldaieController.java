package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class CercaCaldaieController {
	
	static Stage primaryStage;
	
	static CaldaieController su;
	
	@FXML
	private TextField ditta;
	@FXML
	private TextField modello;
	@FXML
	private Button cerca;
	
	public void cerca(){
		
//		FXMLLoader loader = new FXMLLoader(getClass().getResource("schedaUtente.fxml"));
//		try {
//			AnchorPane pane = (AnchorPane) loader.load();
//		
//		SchedaUtenteController controller = loader.getController();
//		controller.cerca(nome.getText(), cognome.getText());
//		Scene finale = new Scene(pane);
//		
//		primaryStage.setScene(finale);
//		
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		su.cerca(ditta.getText(), modello.getText());
		
		Stage stage = (Stage) cerca.getScene().getWindow();
	    stage.close();
		
		
	}
	
}
