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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class SchedaUtenteController implements Initializable {
	
	static Stage primaryStage;
	private boolean impStat;
	final ToggleGroup orologio = new ToggleGroup();
	final ToggleGroup edificio = new ToggleGroup();
	private Connection connection;
	private  Statement stm;
	private  ResultSet rs;
	private TextField[][] griglia = new TextField[12][5];
	
	@FXML
	private MenuItem schedaUtente;

	@FXML
	private MenuItem ricercheUtenti;

	@FXML
	private MenuItem ricercheAnalisi;

	@FXML
	private MenuItem ricercheStorico;
	
	//riferimenti ai pane del codice fxml per velocizzare il processo con il quale si rendono editabili 
	//e non tutti i textfield presenti nella scheda utente
	
	@FXML
	private GridPane caldbrugp;
	@FXML
	private GridPane utentegp;
	@FXML
	private GridPane ammingp;
	@FXML
	private GridPane manutsxgp;	//grid pane dati sezione manut quello a sx
	@FXML
	private GridPane manutdxgp; //grid pane dati sezione manut quello a dx
	@FXML
	private GridPane tipiimpiantogp;
	@FXML
	private GridPane installatoregp;
	@FXML
	private AnchorPane orologioap;
	
	
	@FXML
	private  Label codice;
	@FXML
	private  TextField nome;
	@FXML
	private  TextField cognome;
	@FXML
	private  TextField viaU;
	@FXML
	private  TextField numeroU;
	@FXML
	private  TextField localita;
	@FXML
	private  TextField cap;
	@FXML
	private  TextField comuneU;
	@FXML
	private  TextField provU;
	@FXML
	private  TextField telefonoU;
	@FXML
	private  TextField cellulareU;
	@FXML
	private  TextField cfU;
	@FXML
	private  TextField amministratore;
	@FXML
	private  TextField viaA;
	@FXML
	private  TextField numeroA;
	@FXML
	private  TextField comuneA;
	@FXML
	private  TextField provA;
	@FXML
	private  TextField telefonoA;
	@FXML
	private  TextField cfA;
	@FXML
	private  TextField installatore;
	@FXML
	private  TextField certificatoConformita;
	@FXML
	private  TextField intervento1;
	@FXML
	private  TextField intervento2;
	@FXML
	private  TextField intervento3;
	@FXML
	private  TextField intervento4;
	@FXML
	private  TextField codManut;
	@FXML
	private  TextField puliziacb;
	@FXML
	private  TextField analComb;
	@FXML
	private  TextField bollino;
	@FXML
	private  CheckBox circuiti;
	@FXML
	private  CheckBox termoregolato;
	@FXML
	private  CheckBox contacalorie;
	@FXML
	private  CheckBox superiore35;
	@FXML
	private  CheckBox superiore116;
	@FXML
	private  CheckBox superiore350;
	@FXML
	private  RadioButton automatico;
	@FXML
	private  RadioButton manuale;
	@FXML
	private  Button ora;
	@FXML
	private  Button statusImpianto;
	@FXML
	private  TextField c1ditta;
	@FXML
	private  TextField c1modello;
	@FXML
	private  TextField c1tipo;
	@FXML
	private  TextField c1matricola;
	@FXML
	private  TextField c1combustibile;
	@FXML
	private  TextField c2ditta;
	@FXML
	private  TextField c2modello;
	@FXML
	private  TextField c2tipo;
	@FXML
	private  TextField c2matricola;
	@FXML
	private  TextField c2combustibile;
	@FXML
	private  TextField c3ditta;
	@FXML
	private  TextField c3modello;
	@FXML
	private  TextField c3tipo;
	@FXML
	private  TextField c3matricola;
	@FXML
	private  TextField c3combustibile;
	@FXML
	private  TextField c4ditta;
	@FXML
	private  TextField c4modello;
	@FXML
	private  TextField c4tipo;
	@FXML
	private  TextField c4matricola;
	@FXML
	private  TextField c4combustibile;
	@FXML
	private  TextField c5ditta;
	@FXML
	private  TextField c5modello;
	@FXML
	private  TextField c5tipo;
	@FXML
	private  TextField c5matricola;
	@FXML
	private  TextField c5combustibile;
	@FXML
	private  TextField c6ditta;
	@FXML
	private  TextField c6modello;
	@FXML
	private  TextField c6tipo;
	@FXML
	private  TextField c6matricola;
	@FXML
	private  TextField c6combustibile;
	@FXML
	private  TextField b1ditta;
	@FXML
	private  TextField b1modello;
	@FXML
	private  TextField b1tipo;
	@FXML
	private  TextField b1matricola;
	@FXML
	private  TextField b1combustibile;
	@FXML
	private  TextField b2ditta;
	@FXML
	private  TextField b2modello;
	@FXML
	private  TextField b2tipo;
	@FXML
	private  TextField b2matricola;
	@FXML
	private  TextField b2combustibile;
	@FXML
	private  TextField b3ditta;
	@FXML
	private  TextField b3modello;
	@FXML
	private  TextField b3tipo;
	@FXML
	private  TextField b3matricola;
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
	@FXML
	private Button noteAmministratore;
	@FXML
	private Button noteManutenzione;
	@FXML
	private Button noteInstallatore;
	@FXML
	private RadioButton condTerzoResponsabile;
	@FXML
	private RadioButton condConAmministratore;
	@FXML
	private RadioButton privato;
	@FXML
	private RadioButton altroDitta;
	@FXML
	private RadioButton altroTerzoResponsabile;
	
	
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

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		automatico.setToggleGroup(orologio);
		manuale.setToggleGroup(orologio);
		
		condTerzoResponsabile.setToggleGroup(edificio);
		condConAmministratore.setToggleGroup(edificio);
		privato.setToggleGroup(edificio);
		altroDitta.setToggleGroup(edificio);
		altroTerzoResponsabile.setToggleGroup(edificio);
		
		utentegp.setMouseTransparent(true);
		ammingp.setMouseTransparent(true);
		caldbrugp.setMouseTransparent(true);
		orologioap.setMouseTransparent(true);
		installatoregp.setMouseTransparent(true);
		manutdxgp.setMouseTransparent(true);
		manutsxgp.setMouseTransparent(true);
		tipiimpiantogp.setMouseTransparent(true);
		
		griglia[0][0]=c1ditta;
		griglia[0][1]=c1modello;
		griglia[0][2]=c1tipo;
		griglia[0][3]=c1matricola;
		griglia[0][4]=c1combustibile;
		
		griglia[1][0]=c2ditta;
		griglia[1][1]=c2modello;
		griglia[1][2]=c2tipo;
		griglia[1][3]=c2matricola;
		griglia[1][4]=c2combustibile;
		
		griglia[2][0]=c3ditta;
		griglia[2][1]=c3modello;
		griglia[2][2]=c3tipo;
		griglia[2][3]=c3matricola;
		griglia[2][4]=c3combustibile;
		
		griglia[3][0]=c4ditta;
		griglia[3][1]=c4modello;
		griglia[3][2]=c4tipo;
		griglia[3][3]=c4matricola;
		griglia[3][4]=c4combustibile;
		
		griglia[4][0]=c5ditta;
		griglia[4][1]=c5modello;
		griglia[4][2]=c5tipo;
		griglia[4][3]=c5matricola;
		griglia[4][4]=c5combustibile;
		
		griglia[5][0]=c6ditta;
		griglia[5][1]=c6modello;
		griglia[5][2]=c6tipo;
		griglia[5][3]=c6matricola;
		griglia[5][4]=c6combustibile;
		
		griglia[6][0]=b1ditta;
		griglia[6][1]=b1modello;
		griglia[6][2]=b1tipo;
		griglia[6][3]=b1matricola;
		griglia[6][4]=b1combustibile;
		
		griglia[7][0]=b2ditta;
		griglia[7][1]=b2modello;
		griglia[7][2]=b2tipo;
		griglia[7][3]=b2matricola;
		griglia[7][4]=b2combustibile;
		
		griglia[8][0]=b3ditta;
		griglia[8][1]=b3modello;
		griglia[8][2]=b3tipo;
		griglia[8][3]=b3matricola;
		griglia[8][4]=b3combustibile;
		
		griglia[9][0]=b4ditta;
		griglia[9][1]=b4modello;
		griglia[9][2]=b4tipo;
		griglia[9][3]=b4matricola;
		griglia[9][4]=b4combustibile;
		
		griglia[10][0]=b5ditta;
		griglia[10][1]=b5modello;
		griglia[10][2]=b5tipo;
		griglia[10][3]=b5matricola;
		griglia[10][4]=b5combustibile;

		griglia[11][0]=b6ditta;
		griglia[11][1]=b6modello;
		griglia[11][2]=b6tipo;
		griglia[11][3]=b6matricola;
		griglia[11][4]=b6combustibile;
		
		refresh();
    }
	
	public void ricercheU(){
	
	}
	
	public void ricercheA(){
	
	}
	
	public void ricercheS(){
	
	}
	
	public void noteA() {
		String testo="";
		String id="";
		try {
			testo = rs.getString("NOTEA");
			id=rs.getString("CODICEU");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Note n = new Note(testo, 1, id, this);
		try {
			n.start(primaryStage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void noteM() {
		String testo="";
		String id="";
		try {
			testo = rs.getString("NOTEM");
			id=rs.getString("CODICEU");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Note n = new Note(testo, 2, id, this);
		try {
			n.start(primaryStage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void noteI() {
		String testo="";
		String id="";
		try {
			testo = rs.getString("NOTEI");
			id=rs.getString("CODICEU");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Note n = new Note(testo, 3, id, this);
		try {
			n.start(primaryStage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void cancellaRecord() {
		try {
			stm.execute("delete from utenti where codiceu="+rs.getString("CODICEU"));
			requery();
			refresh();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int getRow() {
		try {
			return rs.getRow();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 1;
		}
	}
	
	public void requery() {
		try {
			rs = stm.executeQuery("select * from utenti order by codiceu");
			rs.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void requery(int row) {
		try {
			rs = stm.executeQuery("select * from utenti order by codiceu");
			rs.absolute(row);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void cercaW(){
		Cerca c = new Cerca(this);
		try {
			c.start(primaryStage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public  void cerca(String nome, String cognome) {
		try {
			ResultSet cerca = stm.executeQuery("select codiceu from utenti where nomeu like '%"+nome+"%' and cognomeu like '%"+cognome+"%'");
			
			if(cerca.next()) {
				String idc = cerca.getString("CODICEU");
				
				System.out.println(nome+" "+cognome+" "+idc);
				
				requery();
				rs.first();
				while (!idc.equals(rs.getString("CODICEU"))) {
					rs.next();
				}
				refresh();
			}
			else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Attenzione");
				alert.setHeaderText("La ricerca non ha prodotto risultati");
				alert.showAndWait();
				requery();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Attenzione");
			alert.setHeaderText("La ricerca non ha prodotto risultati");
			alert.showAndWait();
		}
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
	
	public void modifica() {
		
	}
	
	public void annullaModifica() {
	}
	
	public void confermaModifica() {
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
			if("Contacalorie".equals(rs.getString("IMPCONT"))) {
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
			
			for(int i=0; i<12; i++) {
				if(i<6) {
					griglia[i][0].setText(rs.getString("DITTAC"+(i+1)));
					griglia[i][1].setText(rs.getString("MODELLOC"+(i+1)));
					griglia[i][3].setText(rs.getString("MATRIC"+(i+1)));
					griglia[i][4].setText(rs.getString("COMBC"+(i+1)));
				}
				else {
					griglia[i][0].setText(rs.getString("DITTAB"+(i-5)));
					griglia[i][1].setText(rs.getString("MODELLOB"+(i-5)));
					griglia[i][2].setText(rs.getString("TIPOB"+(i-5)));
					griglia[i][3].setText(rs.getString("MATRIB"+(i-5)));
					griglia[i][4].setText(rs.getString("COMBB"+(i-5)));
				}
			}
			
			if(!"".equals(rs.getString("NOTEA"))) {
				noteAmministratore.setStyle("-fx-text-fill: red;");
			}
			else {
				noteAmministratore.setStyle("-fx-text-fill: black;");
			}
			if(!"".equals(rs.getString("NOTEM"))) {
				noteManutenzione.setStyle("-fx-text-fill: red;");
			}
			else {
				noteManutenzione.setStyle("-fx-text-fill: black;");
			}
			if(!"".equals(rs.getString("NOTEI"))) {
				noteInstallatore.setStyle("-fx-text-fill: red;");
			}
			else {
				noteInstallatore.setStyle("-fx-text-fill: black;");
			}
			
			if("Condominio con terzo responsabile".equals(rs.getString("IMPTIPO"))) {
				condTerzoResponsabile.setSelected(true);
			}
			else if("Condominio con amministratore".equals(rs.getString("IMPTIPO"))) {
				condConAmministratore.setSelected(true);
			}
			else if("Privato".equals(rs.getString("IMPTIPO"))) {
				privato.setSelected(true);
			}
			else if("Altro (Ditta)".equals(rs.getString("IMPTIPO"))) {
				altroDitta.setSelected(true);
			}
			else if("Altro (con terzo responsabile)".equals(rs.getString("IMPTIPO"))) {
				altroTerzoResponsabile.setSelected(true);
			}
			else if("".equals(rs.getString("IMPTIPO")) || "Condominio".equals(rs.getString("IMPTIPO")) || "Altro".equals(rs.getString("IMPTIPO"))) {
				condTerzoResponsabile.setSelected(false);
				condConAmministratore.setSelected(false);
				privato.setSelected(false);
				altroDitta.setSelected(false);
				altroTerzoResponsabile.setSelected(false);
			}
			
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
