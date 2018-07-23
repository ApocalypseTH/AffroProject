package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SchedaUtenteController implements Initializable {
	
	static Stage primaryStage;
	
	@FXML
	private MenuItem schedaUtente;

	@FXML
	private MenuItem ricercheUtenti;

	@FXML
	private MenuItem ricercheAnalisi;

	@FXML
	private MenuItem ricercheStorico;
	
	@FXML
	private Label codice;
	@FXML
	private TextField nome;
	@FXML
	private TextField cognome;
	@FXML
	private TextField viaU;
	@FXML
	private TextField numeroU;
	@FXML
	private TextField localita;
	@FXML
	private TextField cap;
	@FXML
	private TextField comuneU;
	@FXML
	private TextField provU;
	@FXML
	private TextField telefonoU;
	@FXML
	private TextField cellulareU;
	@FXML
	private TextField cfU;
	@FXML
	private TextField amministratore;
	@FXML
	private TextField viaA;
	@FXML
	private TextField numeroA;
	@FXML
	private TextField comuneA;
	@FXML
	private TextField provA;
	@FXML
	private TextField telefonoA;
	@FXML
	private TextField cfA;
	@FXML
	private TextField installatore;
	@FXML
	private TextField certificatoConformita;
	@FXML
	private TextField intervento1;
	@FXML
	private TextField intervento2;
	@FXML
	private TextField intervento3;
	@FXML
	private TextField intervento4;
	@FXML
	private TextField codManut;
	@FXML
	private TextField puliziacb;
	@FXML
	private TextField analComb;
	@FXML
	private TextField bollino;
	
	
	private Connection connection;
	private Statement stm;
	private ResultSet rs;
	
	public void initialize(java.net.URL location, java.util.ResourceBundle resources) {
		
		String connectionString="jdbc:mysql://127.0.0.1:3306/affro?user=root&password=";

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		
		try {
			
			connection = DriverManager.getConnection(connectionString);
			stm = connection.createStatement();
			rs = stm.executeQuery("select * from utenti order by codiceu");
			rs.next();
			refresh();
			

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
    }


	public void schedaU(){
		SchedaUtente su = new SchedaUtente();
		try {
			su.start(primaryStage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void ricercheU(){
		
	}
	
	public void ricercheA(){
		
	}

	public void ricercheS(){
	
	}
	
	public void next(){
		try {
			rs.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		refresh();
	}
	
	public void previous() {
		try {
			rs.previous();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		refresh();
	}
	
	public void last() {
		try {
			rs.last();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		refresh();
	}
	
	public void first() {
		try {
			rs.first();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		refresh();
	}
	
	public void refresh() {
		try {
			codice.setText(rs.getString("CODICEU"));
			nome.setText(rs.getString("NOMEU"));
			cognome.setText(rs.getString("COGNOMEU"));
			viaU.setText(rs.getString("INDIRIZZOU"));
			numeroU.setText(rs.getString("NUMEROU"));
			localita.setText(rs.getString("LOCALITAU"));
			cap.setText(rs.getString("CAPU"));
			comuneU.setText(rs.getString("COMUNEU"));
			provU.setText(rs.getString("PROVINCIAU"));
			telefonoU.setText(rs.getString("TELEFONOU"));
			cellulareU.setText(rs.getString("CELLULAREU"));
			cfU.setText(rs.getString("CFIVAU"));
			amministratore.setText(rs.getString("COGNOMEA"));
			viaA.setText(rs.getString("INDIRIZZOA"));
			numeroA.setText(rs.getString("NUMEROA"));
			comuneA.setText(rs.getString("COMUNEA"));
			provA.setText(rs.getString("PROVINCIAA"));
			telefonoA.setText(rs.getString("TELEFONOA"));
			cfA.setText(rs.getString("CFIVAA"));
			installatore.setText(rs.getString("DITTAI"));
			certificatoConformita.setText(rs.getString("CERTCONFV"));
			intervento1.setText(rs.getString("ANNOPREC1"));
			intervento2.setText(rs.getString("ANNOPREC2"));
			intervento3.setText(rs.getString("ANNOCOR1"));
			intervento4.setText(rs.getString("ANNOCOR2"));
			codManut.setText(rs.getString("CODMANU"));
			puliziacb.setText(rs.getString("MANPROGM"));
			analComb.setText(rs.getString("ANALCOMB"));
			bollino.setText(rs.getString("BOLLINO"));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Attenzione");
			alert.setHeaderText("Sei al primo o all'ultimo utente");

			alert.showAndWait();
		}
	}

}
