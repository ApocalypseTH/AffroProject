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

public class CercaAmministratoriController {
	
	static Stage primaryStage;
	
	static AmministratoriController su;
	
	@FXML
	private TextField ammin;
	@FXML
	private Button cerca;
	
	public void cerca(){
		
		su.cerca(ammin.getText());
		
		Stage stage = (Stage) cerca.getScene().getWindow();
	    stage.close();
		
		
	}
	
}
