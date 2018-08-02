package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RicercaAnalisiController implements Initializable{
	public static Stage primaryStage;
	private Connection connection;
	private Statement stm;
	
	@FXML
	private TextField t;
	@FXML
	private RadioButton eseguita;
	@FXML
	private RadioButton nonEseguita;
	
	
	
	
	public void conferma() {
		
	}
	
	public void annulla() {
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		String connectionString="jdbc:mysql://127.0.0.1:3306/affro?user=root&password=";

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		
		try {
			
			connection = DriverManager.getConnection(connectionString);
			stm = connection.createStatement();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
}
