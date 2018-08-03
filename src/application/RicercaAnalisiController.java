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
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class RicercaAnalisiController implements Initializable{
	public static Stage primaryStage;
	private Connection connection;
	private Statement stm;
	private ToggleGroup group;
	
	@FXML
	private TextField t;
	@FXML
	private RadioButton eseguita;
	@FXML
	private RadioButton nonEseguita;
	
	
	
	
	public void conferma() {
		String q="select distinct u.codiceu, u.cognomeu, u.nomeu from analisi as a join utenti as u on a.codiceu=u.codiceu";
		
		if(!"".equals(t.getText())) {
			if(eseguita.isSelected())
				q=q.concat(" where a.data between '"+t.getText()+"-01-01' and '"+t.getText()+"-12-31'");
			else if(nonEseguita.isSelected())
				q=q.concat(" where a.data<'"+t.getText()+"-01-01' or a.data>'"+t.getText()+"-12-31'");
		}
		
		System.out.println(q);
		
		RisultatiRicercaAnalisi su = new RisultatiRicercaAnalisi(q);
		try {
			su.start(primaryStage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void annulla() {
		Main su = new Main();
		try {
			su.start(primaryStage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void azzera() {
		t.setText("");
		eseguita.setSelected(false);
		nonEseguita.setSelected(false);
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
		
		eseguita.setToggleGroup(group);
		nonEseguita.setToggleGroup(group);
		
	}
}
