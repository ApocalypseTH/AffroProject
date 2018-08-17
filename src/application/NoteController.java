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
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class NoteController implements Initializable{
	public static String testo;
	public static Integer i;
	public static String id;
	private Connection connection;
	private Statement stm;
	
	static SchedaUtenteController suc;
	
	@FXML
	private TextArea t;
	@FXML
	private Label title;
	@FXML
	private Button annulla;
	
	public void conferma() {
		String txt=t.getText();
		String q = "";
		if(i==1) {
			q="a";
		}
		if(i==2) {
			q="m";
		}
		if(i==3) {
			q="i";
		}
		try {
			String s="update utenti set note"+q+"='"+txt+"' where codiceu="+id;
			stm.execute(s);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		annulla();
	}
	
	public void annulla() {
		Stage stage = (Stage) annulla.getScene().getWindow();
	    // do what you have to do
		//Ok I'll do what i want to do XOXO by Eddie
		suc.requery(suc.getRow());
		suc.refresh();
	    stage.close();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		t.setText(testo);
		
		if(i==1) {
			title.setText("Note sull'Amministratore");
		}
		if(i==2) {
			title.setText("Note sulla Manutenzione");
		}
		if(i==3) {
			title.setText("Note sull'Installatore");
		}
		
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
