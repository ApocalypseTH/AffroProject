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
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class SchedaUtenteController implements Initializable {
	
	static Stage primaryStage;
	private boolean impStat;
	final ToggleGroup orologio = new ToggleGroup();
	final ToggleGroup edificio = new ToggleGroup();
	private Connection connection;
	private Statement stm;
	private ResultSet rs;
	
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
	@FXML
	private CheckBox circuiti;
	@FXML
	private CheckBox termoregolato;
	@FXML
	private CheckBox contacalorie;
	@FXML
	private CheckBox superiore35;
	@FXML
	private CheckBox superiore116;
	@FXML
	private CheckBox superiore350;
	@FXML
	private RadioButton automatico;
	@FXML
	private RadioButton manuale;
	@FXML
	private Button ora;
	@FXML
	private Button statusImpianto;
	@FXML
	private TextField c1ditta;
	@FXML
	private TextField c1modello;
	@FXML
	private TextField c1tipo;
	@FXML
	private TextField c1matricola;
	@FXML
	private TextField c1combustibile;
	@FXML
	private TextField c2ditta;
	@FXML
	private TextField c2modello;
	@FXML
	private TextField c2tipo;
	@FXML
	private TextField c2matricola;
	@FXML
	private TextField c2combustibile;
	@FXML
	private TextField c3ditta;
	@FXML
	private TextField c3modello;
	@FXML
	private TextField c3tipo;
	@FXML
	private TextField c3matricola;
	@FXML
	private TextField c3combustibile;
	@FXML
	private TextField c4ditta;
	@FXML
	private TextField c4modello;
	@FXML
	private TextField c4tipo;
	@FXML
	private TextField c4matricola;
	@FXML
	private TextField c4combustibile;
	@FXML
	private TextField c5ditta;
	@FXML
	private TextField c5modello;
	@FXML
	private TextField c5tipo;
	@FXML
	private TextField c5matricola;
	@FXML
	private TextField c5combustibile;
	@FXML
	private TextField c6ditta;
	@FXML
	private TextField c6modello;
	@FXML
	private TextField c6tipo;
	@FXML
	private TextField c6matricola;
	@FXML
	private TextField c6combustibile;
	@FXML
	private TextField b1ditta;
	@FXML
	private TextField b1modello;
	@FXML
	private TextField b1tipo;
	@FXML
	private TextField b1matricola;
	@FXML
	private TextField b1combustibile;
	@FXML
	private TextField b2ditta;
	@FXML
	private TextField b2modello;
	@FXML
	private TextField b2tipo;
	@FXML
	private TextField b2matricola;
	@FXML
	private TextField b2combustibile;
	@FXML
	private TextField b3ditta;
	@FXML
	private TextField b3modello;
	@FXML
	private TextField b3tipo;
	@FXML
	private TextField b3matricola;
	@FXML
	private TextField b3combustibile;
	@FXML
	private TextField b4ditta;
	@FXML
	private TextField b4modello;
	@FXML
	private TextField b4tipo;
	@FXML
	private TextField b4matricola;
	@FXML
	private TextField b4combustibile;
	@FXML
	private TextField b5ditta;
	@FXML
	private TextField b5modello;
	@FXML
	private TextField b5tipo;
	@FXML
	private TextField b5matricola;
	@FXML
	private TextField b5combustibile;
	@FXML
	private TextField b6ditta;
	@FXML
	private TextField b6modello;
	@FXML
	private TextField b6tipo;
	@FXML
	private TextField b6matricola;
	@FXML
	private TextField b6combustibile;
	
	private TextField[] t = new TextField[5];
	
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
		
		automatico.setToggleGroup(orologio);
		manuale.setToggleGroup(orologio);
		
		t[0]=c1ditta;
		t[1]=c1modello;
		t[2]=c1tipo;
		t[3]=c1matricola;
		t[4]=c1combustibile;
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
			
			if("Circuiti".equals(rs.getString("IMPCIRC"))) {
				circuiti.setSelected(true);
			}
			else {
				circuiti.setSelected(false);
			}
			if("Termoregolato".equals(rs.getString("IMPTERM"))) {
				termoregolato.setSelected(true);
			}
			else {
				termoregolato.setSelected(false);
			}
			if("??".equals(rs.getString("IMPCONT"))) {
				contacalorie.setSelected(true);
			}
			else {
				contacalorie.setSelected(false);
			}
			if("Superiore 35 KW".equals(rs.getString("IMPSU35"))) {
				superiore35.setSelected(true);
			}
			else {
				superiore35.setSelected(false);
			}
			if("Superiore 116 KW".equals(rs.getString("IMPSU116"))) {
				superiore116.setSelected(true);
			}
			else {
				superiore116.setSelected(false);
			}
			if("Superiore 350 KW".equals(rs.getString("IMPSUPE"))) {
				superiore350.setSelected(true);
			}
			else {
				superiore350.setSelected(false);
			}
			if("SI".equals(rs.getString("IMPACCESO"))) {
				statusImpianto.setStyle("-fx-text-fill: green;");
				impStat=true;
			}
			else {
				statusImpianto.setStyle("-fx-text-fill: red;");
				impStat=false;
			}
			if("Automatico".equals(rs.getString("OROLOGIO"))) {
				automatico.setSelected(true);
			}
			else {
				manuale.setSelected(true);
			}
			if("Legale".equals(rs.getString("ORA"))) {
				ora.setText("Ora Legale");
			}
			else {
				ora.setText("Ora Solare");
			}
			
			t[0].setText(rs.getString("DITTAC1"));
			t[1].setText(rs.getString("MODELLOC1"));
			t[3].setText(rs.getString("MATRIC1"));
			t[4].setText(rs.getString("COMBC1"));
			
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
