package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
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

public class AnagraficaDittaController implements Initializable{
	
	static Stage primaryStage;
	@FXML
	private TextField ditta;
	@FXML
	private TextField indirizzo;
	@FXML
	private TextField cap;
	@FXML
	private TextField localita;
	@FXML
	private TextField telefono;
	@FXML
	private TextField fax;
	@FXML
	private TextField partitaiva;
	@FXML
	private TextField codicefiscale;
	@FXML
	private TextField descrizione1;
	@FXML
	private TextField descrizione2;
	
	private Connection connection;
	private Statement stm;
	private ResultSet q;
	
	public void conferma(){
		try {
			stm.execute("update ditta set ditta='"+ditta.getText()+"', indirizzo='"+indirizzo.getText()+"', cap='"+cap.getText()+"', localita='"+localita.getText()+"', telefono='"+telefono.getText()+"', fax='"+fax.getText()+"', piva='"+partitaiva.getText()+"', codfis='"+codicefiscale.getText()+"', descri1='"+descrizione1.getText()+"', descri2='"+descrizione2.getText()+"' where ditta='"+q.getString("DITTA")+"'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Main su = new Main();
		try {
			su.start(primaryStage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Stage stage = (Stage) ditta.getScene().getWindow();
	    stage.close();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

//		String connectionString="jdbc:mysql://127.0.0.1:3306/affro?user=root&password=";
//
//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}

		
		try {
			
//			Connection connection = DriverManager.getConnection(connectionString);
			ConnDB conn = new ConnDB();
			connection = conn.getConnection();
			stm = connection.createStatement();
			
			q = stm.executeQuery("select * from ditta");
			
			q.next();
			
			ditta.setText(q.getString("DITTA"));
			indirizzo.setText(q.getString("INDIRIZZO"));
			cap.setText(q.getString("CAP"));
			localita.setText(q.getString("LOCALITA"));
			telefono.setText(q.getString("TELEFONO"));
			fax.setText(q.getString("FAX"));
			partitaiva.setText(q.getString("PIVA"));
			codicefiscale.setText(q.getString("CODFIS"));
			descrizione1.setText(q.getString("DESCRI1"));
			descrizione2.setText(q.getString("DESCRI2"));
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
}
