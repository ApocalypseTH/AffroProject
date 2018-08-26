package application;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
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
	static int user = -1;
	int id;
	
	private DateConverter d = new DateConverter();
	
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
	private  Button primo;
	@FXML
	private  Button prec;
	@FXML
	private  Button segue;
	@FXML
	private  Button ultimo;
	@FXML
	private  Button altroUt;
	@FXML
	private  Button ricerche;
	@FXML
	private  Button storico;
	@FXML
	private  Button bruciatori;
	@FXML
	private  Button caldaie;
	@FXML
	private  Button installatori;
	@FXML
	private  Button amministratori;
	@FXML
	private  Button nuovo;
	@FXML
	private  Button canc;
	@FXML
	private  Button richiestaInt;
	@FXML
	private  Button stamp;
	@FXML
	private  Button menu;
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
	@FXML  TextField amministratore;
	@FXML  TextField viaA;
	@FXML  TextField numeroA;
	@FXML  TextField comuneA;
	@FXML  TextField provA;
	@FXML  TextField telefonoA;
	@FXML  TextField cfA;
	@FXML  TextField installatore;
	@FXML  TextField certificatoConformita;
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
	@FXML  TextField c1ditta;
	@FXML
	 TextField c1modello;
	@FXML
	  TextField c1tipo;
	@FXML
	  TextField c1matricola;
	@FXML
	  TextField c1combustibile;
	@FXML
	  TextField c1catasto;
	@FXML
	  TextField c2ditta;
	@FXML
	  TextField c2modello;
	@FXML
	  TextField c2tipo;
	@FXML
	  TextField c2matricola;
	@FXML
	  TextField c2combustibile;
	@FXML
	  TextField c2catasto;
	@FXML
	  TextField c3ditta;
	@FXML
	  TextField c3modello;
	@FXML
	  TextField c3tipo;
	@FXML
	  TextField c3matricola;
	@FXML
	  TextField c3combustibile;
	@FXML
	  TextField c3catasto;
	@FXML
	  TextField c4ditta;
	@FXML
	  TextField c4modello;
	@FXML
	  TextField c4tipo;
	@FXML
	  TextField c4matricola;
	@FXML
	  TextField c4combustibile;
	@FXML
	  TextField c4catasto;
	@FXML
	  TextField c5ditta;
	@FXML
	  TextField c5modello;
	@FXML
	  TextField c5tipo;
	@FXML
	  TextField c5matricola;
	@FXML
	  TextField c5combustibile;
	@FXML
	  TextField c5catasto;
	@FXML
	  TextField c6ditta;
	@FXML
	  TextField c6modello;
	@FXML
	  TextField c6tipo;
	@FXML
	  TextField c6matricola;
	@FXML
	  TextField c6combustibile;
	@FXML
	  TextField c6catasto;
	@FXML
	  TextField b1ditta;
	@FXML
	  TextField b1modello;
	@FXML
	  TextField b1tipo;
	@FXML
	  TextField b1matricola;
	@FXML
	  TextField b1combustibile;
	@FXML
	  TextField b2ditta;
	@FXML
	  TextField b2modello;
	@FXML
	  TextField b2tipo;
	@FXML
	  TextField b2matricola;
	@FXML
	  TextField b2combustibile;
	@FXML
	  TextField b3ditta;
	@FXML
	  TextField b3modello;
	@FXML
	  TextField b3tipo;
	@FXML
	  TextField b3matricola;
	@FXML
	 TextField b3combustibile;
	@FXML
	 TextField b4ditta;
	@FXML
	 TextField b4modello;
	@FXML
	 TextField b4tipo;
	@FXML
	 TextField b4matricola;
	@FXML
	 TextField b4combustibile;
	@FXML
	 TextField b5ditta;
	@FXML
	 TextField b5modello;
	@FXML
	 TextField b5tipo;
	@FXML
	 TextField b5matricola;
	@FXML
	 TextField b5combustibile;
	@FXML
	 TextField b6ditta;
	@FXML
	 TextField b6modello;
	@FXML
	 TextField b6tipo;
	@FXML
	 TextField b6matricola;
	@FXML
	 TextField b6combustibile;
	@FXML
	Label c1;
	@FXML
	Label c2;
	@FXML
	Label c3;
	@FXML
	Label c4;
	@FXML
	Label c5;
	@FXML
	Label c6;
	@FXML
	Label b1;
	@FXML
	Label b2;
	@FXML
	Label b3;
	@FXML
	Label b4;
	@FXML
	Label b5;
	@FXML
	Label b6;
	@FXML
	private Button noteAmministratore;
	@FXML
	private Button noteManutenzione;
	@FXML
	private Button noteInstallatore;
	@FXML
	private Button confModifica;
	@FXML
	private Button annullaModifica;
	@FXML
	private Button messaInFunzione;
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
	@FXML
	private MenuButton analisi;
	@FXML
	private AnchorPane dittaInstallatore;
	
	public void initialize(java.net.URL location, java.util.ResourceBundle resources) {
		
//		String connectionString="jdbc:mysql://127.0.0.1:3306/affro?user=root&password=";
//
//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
//
//		
		try {
//			
//			connection = DriverManager.getConnection(connectionString);
			ConnDB conn = new ConnDB();
			connection = conn.getConnection();
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
		
		for(int i=0; i<griglia.length; i++) {
			for(int j=0; j<griglia[i].length; j++) {
				griglia[i][j].setEditable(false);
			}
		}
		
		orologioap.setMouseTransparent(true);
		tipiimpiantogp.setMouseTransparent(true);
		
		if (user != -1) {
			returnToId(user);
		}
			
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
			Boolean b= false;
			int r=rs.getRow();
			if(rs.isLast())
				b=true;
			stm.execute("delete from utenti where codiceu="+rs.getString("CODICEU"));
			if(b)
				requery(r-1);
			else
				requery(r);
			
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
	public void returnToId(int i) {
		requery();
		try {
			while(Integer.parseInt(rs.getString("CODICEU")) != i) {
				rs.next();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void statusImpianto() {
		try {
			if("SI".equals(rs.getString("IMPACCESO"))) {
				stm.execute("update utenti set impacceso='NO' where codiceu="+codice.getText());
				intervento("SPEGNIMENTO IMPIANTO");
			}
			else {
				stm.execute("update utenti set impacceso='SI' where codiceu="+codice.getText());
				intervento("ACCENSIONE IMPIANTO");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void statusOra() {
		try {
			if("Legale".equals(rs.getString("ORA"))) {
				stm.execute("update utenti set ora='Solare' where codiceu="+codice.getText());
				intervento("VARIAZIONE A ORA SOLARE");
			}
			else {
				stm.execute("update utenti set ora='Legale' where codiceu="+codice.getText());
				intervento("VARIAZIONE A ORA LEGALE");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void puliziacb() {
				intervento("PULIZIE E MANUTENZIONE ORDINARIA");
	}
	
	public void intervento(String motivo){
		try {
			stm.execute("insert into ricint(codiceu, datach, motivoch, dataint, cognomech) values ("+codice.getText()+", CURRENT_TIMESTAMP, '"+motivo+"', CURRENT_TIMESTAMP, '"+cognome.getText()+" - "+nome.getText()+"')");
			
			returnToId(Integer.parseInt(codice.getText()));
			
			if(rs.getString("ANNOPREC1")==null) {
				stm.execute("update utenti set ANNOPREC1=CURRENT_TIMESTAMP where codiceu="+codice.getText());
			}
			else if(rs.getString("ANNOPREC2")==null) {
				stm.execute("update utenti set ANNOPREC2=CURRENT_TIMESTAMP where codiceu="+codice.getText());
			}
			else if(rs.getString("ANNOCOR1")==null) {
				stm.execute("update utenti set ANNOCOR1=CURRENT_TIMESTAMP where codiceu="+codice.getText());
			}
			else if(rs.getString("ANNOCOR2")==null) {
				stm.execute("update utenti set ANNOCOR2=CURRENT_TIMESTAMP where codiceu="+codice.getText());
			}
			else if(rs.getString("ANNOPREC2")!=null) {
				stm.execute("update utenti set ANNOPREC1=CURRENT_TIMESTAMP, ANNOPREC2=NULL, ANNOCOR1=NULL, ANNOCOR2=NULL where codiceu="+codice.getText());
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		returnToId(Integer.parseInt(codice.getText()));
		refresh();
		
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Conferma");
		alert.setHeaderText("Intervento avvenuto con successo");
		try {
			alert.setContentText("è avvenuto un intervento all'utente "+rs.getString("COGNOMEU")+" riguardante: "+motivo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		alert.showAndWait();
	}
	
	public void richInt() {
		RichiestaIntervento c = new RichiestaIntervento(codice.getText(), codManut.getText(), cognome.getText()+" "+nome.getText(), this);
		try {
			c.start(primaryStage);
		} catch (Exception e) {
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
			if(rs.isLast()) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Attenzione");
				alert.setHeaderText("Sei all'ultimo utente");
				alert.showAndWait();
			} else {
				rs.next();
				refresh();
			}
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void previous() {
		try {
			if(rs.isFirst()) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Attenzione");
				alert.setHeaderText("Sei al primo utente");
				alert.showAndWait();
			} else {
				rs.previous();
				refresh();
			}
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	
	public void nuovo() {
		try {
			stm.execute("insert into utenti(cognomeu) values('')");
			rs = stm.executeQuery("select codiceu from utenti where cognomeu='' and nomeu is null and cognomea is null");
			rs.next();
			id=rs.getInt("CODICEU");
			requery();
			try {
//				rs.next();
				while(Integer.parseInt(rs.getString("CODICEU")) != id) {
					rs.next();
				}
				refresh();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cognome.setText("");
		nome.setText("");
		viaU.setText("");
		numeroU.setText("");
		localita.setText("");
		cap.setText("");
		comuneU.setText("");
		provU.setText("");
		telefonoU.setText("");
		cellulareU.setText("");
		cfU.setText("");
		amministratore.setText("");
		viaA.setText("");
		numeroA.setText("");
		comuneA.setText("");
		provA.setText("");
		telefonoA.setText("");
		cfA.setText("");
		intervento1.setText("");
		intervento2.setText("");
		intervento3.setText("");
		intervento4.setText("");
		codManut.setText("");
		puliziacb.setText("");
		analComb.setText("");
		bollino.setText("");
		analisi.setText("");
		installatore.setText("");
		certificatoConformita.setText("");
		c1catasto.setText("");
		c2catasto.setText("");
		c3catasto.setText("");
		c4catasto.setText("");
		c5catasto.setText("");
		c6catasto.setText("");
		condConAmministratore.setSelected(false);
		condTerzoResponsabile.setSelected(false);
		privato.setSelected(false);
		altroDitta.setSelected(false);
		altroTerzoResponsabile.setSelected(false);
		circuiti.setSelected(false);
		termoregolato.setSelected(false);
		contacalorie.setSelected(false);
		superiore35.setSelected(false);
		superiore116.setSelected(false);
		superiore350.setSelected(false);
		
		for(int i=0; i<11; i++) {
			for(int j=0; j<5; j++) {
				griglia[i][j].setText("");
			}
		}
		
		
		modifica();
	}
	
	public void apriCartella() {
		
		String username = System.getProperty("user.name");
		Desktop desktop = Desktop.getDesktop();
        File dirToOpen = null;
        try {
            dirToOpen = new File("C:/Users/"+username+"/Documents/"+codice.getText());
            desktop.open(dirToOpen);
        } catch (IllegalArgumentException iae) {
        	Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Attenzione");
			alert.setHeaderText("Nessuna cartella associata a questo utente");
			alert.showAndWait();
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void Analizzalo() {
		Analisi a;
		try {
			a = new Analisi(Integer.parseInt(rs.getString("CODICEU")));
			a.start(primaryStage);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void modifica() {
		id=Integer.parseInt(codice.getText());
		
		primo.setDisable(true);
		prec.setDisable(true);
		segue.setDisable(true);
		ultimo.setDisable(true);
		altroUt.setDisable(true);
		ricerche.setDisable(true);
		storico.setDisable(true);
		bruciatori.setDisable(true);
		caldaie.setDisable(true);
		installatori.setDisable(true);
		amministratori.setDisable(true);
		nuovo.setDisable(true);
		canc.setDisable(true);
		richiestaInt.setDisable(true);
		stamp.setDisable(true);
		menu.setDisable(true);
		orologioap.setMouseTransparent(false);
		tipiimpiantogp.setMouseTransparent(false);
		cognome.setEditable(true);
		nome.setEditable(true);
		viaU.setEditable(true);
		numeroU.setEditable(true);
		comuneU.setEditable(true);
		provU.setEditable(true);
		telefonoU.setEditable(true);
		cap.setEditable(true);
		localita.setEditable(true);
		cellulareU.setEditable(true);
		cfU.setEditable(true);
		certificatoConformita.setEditable(true);
		c1matricola.setEditable(true);
		c2matricola.setEditable(true);
		c3matricola.setEditable(true);
		c4matricola.setEditable(true);
		c5matricola.setEditable(true);
		c6matricola.setEditable(true);
		b1matricola.setEditable(true);
		b2matricola.setEditable(true);
		b3matricola.setEditable(true);
		b4matricola.setEditable(true);
		b5matricola.setEditable(true);
		b6matricola.setEditable(true);
		bollino.setEditable(true);
		codManut.setEditable(true);
		c1catasto.setEditable(true);
		c2catasto.setEditable(true);
		c3catasto.setEditable(true);
		c4catasto.setEditable(true);
		c5catasto.setEditable(true);
		c6catasto.setEditable(true);
		
		confModifica.setVisible(true);
		annullaModifica.setVisible(true);
		analisi.setDisable(false);
		messaInFunzione.setDisable(false);
		
		 ammingp.setOnMouseClicked(e -> {
			 AmministratoriSchedaUtente su = new AmministratoriSchedaUtente(this, amministratore.getText());
				try {
					su.start(primaryStage);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		 });
		 dittaInstallatore.setOnMouseClicked(e -> {
			 InstallatoriSchedaUtente su = new InstallatoriSchedaUtente(this, installatore.getText());
				try {
					su.start(primaryStage);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		 });
		 c1.setOnMouseClicked(e -> {
			 CaldaieSchedaUtente su = new CaldaieSchedaUtente(this, c1ditta.getText(), c1modello.getText(), 1);
				try {
					su.start(primaryStage);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		 });
		 c2.setOnMouseClicked(e -> {
			 CaldaieSchedaUtente su = new CaldaieSchedaUtente(this,  c2ditta.getText(), c2modello.getText(), 2);
				try {
					su.start(primaryStage);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		 });
		 c3.setOnMouseClicked(e -> {
			 CaldaieSchedaUtente su = new CaldaieSchedaUtente(this,  c3ditta.getText(), c3modello.getText(), 3);
				try {
					su.start(primaryStage);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		 });
		 c4.setOnMouseClicked(e -> {
			 CaldaieSchedaUtente su = new CaldaieSchedaUtente(this, c4ditta.getText(), c4modello.getText(), 4);
				try {
					su.start(primaryStage);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		 });
		 c5.setOnMouseClicked(e -> {
			 CaldaieSchedaUtente su = new CaldaieSchedaUtente(this, c5ditta.getText(), c5modello.getText(), 5);
				try {
					su.start(primaryStage);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		 });
		 c6.setOnMouseClicked(e -> {
			 CaldaieSchedaUtente su = new CaldaieSchedaUtente(this, c6ditta.getText(), c6modello.getText(), 6);
				try {
					su.start(primaryStage);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		 });
		 b1.setOnMouseClicked(e -> {
			 BruciatoreSchedaUtente su = new BruciatoreSchedaUtente(this, b1ditta.getText(), b1modello.getText(), 1);
				try {
					su.start(primaryStage);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		 });
		 b2.setOnMouseClicked(e -> {
			 BruciatoreSchedaUtente su = new BruciatoreSchedaUtente(this, b2ditta.getText(), b2modello.getText(), 2);
				try {
					su.start(primaryStage);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		 });
		 b3.setOnMouseClicked(e -> {
			 BruciatoreSchedaUtente su = new BruciatoreSchedaUtente(this, b3ditta.getText(), b3modello.getText(), 3);
				try {
					su.start(primaryStage);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		 });
		 b4.setOnMouseClicked(e -> {
			 BruciatoreSchedaUtente su = new BruciatoreSchedaUtente(this, b4ditta.getText(), b4modello.getText(), 4);
				try {
					su.start(primaryStage);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		 });
		 b5.setOnMouseClicked(e -> {
			 BruciatoreSchedaUtente su = new BruciatoreSchedaUtente(this, b5ditta.getText(), b5modello.getText(), 5);
				try {
					su.start(primaryStage);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		 });
		 b6.setOnMouseClicked(e -> {
			 BruciatoreSchedaUtente su = new BruciatoreSchedaUtente(this, b6ditta.getText(), b6modello.getText(), 6);
				try {
					su.start(primaryStage);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		 });
	}
	
	public void annullaModifica() {
		ammingp.setOnMouseClicked(null);
		dittaInstallatore.setOnMouseClicked(null);
		c1.setOnMouseClicked(null);
		c2.setOnMouseClicked(null);
		c3.setOnMouseClicked(null);
		c4.setOnMouseClicked(null);
		c5.setOnMouseClicked(null);
		c6.setOnMouseClicked(null);
		b1.setOnMouseClicked(null);
		b2.setOnMouseClicked(null);
		b3.setOnMouseClicked(null);
		b4.setOnMouseClicked(null);
		b5.setOnMouseClicked(null);
		b6.setOnMouseClicked(null);
		
		primo.setDisable(false);
		prec.setDisable(false);
		segue.setDisable(false);
		ultimo.setDisable(false);
		altroUt.setDisable(false);
		ricerche.setDisable(false);
		storico.setDisable(false);
		bruciatori.setDisable(false);
		caldaie.setDisable(false);
		installatori.setDisable(false);
		amministratori.setDisable(false);
		nuovo.setDisable(false);
		canc.setDisable(false);
		richiestaInt.setDisable(false);
		stamp.setDisable(false);
		menu.setDisable(false);
		orologioap.setMouseTransparent(true);
		tipiimpiantogp.setMouseTransparent(true);
		cognome.setEditable(false);
		nome.setEditable(false);
		viaU.setEditable(false);
		numeroU.setEditable(false);
		comuneU.setEditable(false);
		provU.setEditable(false);
		telefonoU.setEditable(false);
		cap.setEditable(false);
		localita.setEditable(false);
		cellulareU.setEditable(false);
		cfU.setEditable(false);
		certificatoConformita.setEditable(false);
		c1matricola.setEditable(false);
		c2matricola.setEditable(false);
		c3matricola.setEditable(false);
		c4matricola.setEditable(false);
		c5matricola.setEditable(false);
		c6matricola.setEditable(false);
		b1matricola.setEditable(false);
		b2matricola.setEditable(false);
		b3matricola.setEditable(false);
		b4matricola.setEditable(false);
		b5matricola.setEditable(false);
		b6matricola.setEditable(false);
		bollino.setEditable(false);
		codManut.setEditable(false);
		c1catasto.setEditable(false);
		c2catasto.setEditable(false);
		c3catasto.setEditable(false);
		c4catasto.setEditable(false);
		c5catasto.setEditable(false);
		c6catasto.setEditable(false);
		
		confModifica.setVisible(false);
		annullaModifica.setVisible(false);
		analisi.setDisable(true);
		messaInFunzione.setDisable(true);
		
		requery();
		try {
//			rs.next();
			while(Integer.parseInt(rs.getString("CODICEU")) != id) {
				rs.next();
			}
			refresh();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void confermaModifica() {
		try {
			String q="update utenti set cognomeu='"+cognome.getText()+"', nomeu='"+nome.getText()+"', indirizzou='"+viaU.getText()+"', numerou='"+numeroU.getText()+"', localitau='"+localita.getText()+"', capu='"+cap.getText()+"', comuneu='"+comuneU.getText()+"', provinciau='"+provU.getText()+"', telefonou='"+telefonoU.getText()+"', cellulareu='"+cellulareU.getText()+"', cfivau='"+cfU.getText()+"', cognomea='"+amministratore.getText()+"', indirizzoa='"+viaA.getText()+"', numeroa='"+numeroA.getText()+"', comunea='"+comuneA.getText()+"', provinciaa='"+provA.getText()+"', telefonoa='"+telefonoA.getText()+"', cfivaa='"+cfA.getText()+"', dittai='"+installatore.getText()+"', codmanu='"+codManut.getText()+"', bollino='"+bollino.getText()+"', certconfv='"+certificatoConformita.getText()+"', n_analisi='"+analisi.getText()+"',  ";
			for(int i=0; i<6; i++) {				
				q=q.concat("dittac"+(i+1)+"='"+griglia[i][0].getText()+"', modelloc"+(i+1)+"='"+griglia[i][1].getText()+"', matric"+(i+1)+"='"+griglia[i][3].getText()+"', combc"+(i+1)+"='"+griglia[i][4].getText()+"', ");
			}
			for(int i=0; i<6; i++) {				
				q=q.concat("dittab"+(i+1)+"='"+griglia[i+6][0].getText()+"', modellob"+(i+1)+"='"+griglia[i+6][1].getText()+"', tipob"+(i+1)+"='"+griglia[i+6][2].getText()+"', matrib"+(i+1)+"='"+griglia[i+6][3].getText()+"', combb"+(i+1)+"='"+griglia[i+6][4].getText()+"', ");
			}
			
			q=q.concat("c1catasto="+c1catasto.getText()+", c2catasto="+c2catasto.getText()+", c3catasto="+c3catasto.getText()+", c4catasto="+c4catasto.getText()+", c5catasto="+c5catasto.getText()+", c6catasto="+c6catasto.getText()+", ");
			
			
			if(circuiti.isSelected()) {
				q=q.concat("impcirc='Circuiti', ");
			}
			if(termoregolato.isSelected()) {
				q=q.concat("impterm='Termoregolato', ");
			}
			if(contacalorie.isSelected()) {
				q=q.concat("impcont='Contacalorie', ");
			}
			if(superiore35.isSelected()) {
				q=q.concat("impsu35='Superiore 35 KW', ");
			}
			if(superiore116.isSelected()) {
				q=q.concat("impsu116='Superiore 116 KW', ");
			}
			if(superiore350.isSelected()) {
				q=q.concat("impsupe='Superiore 350 KW', ");
			}
			
			if(automatico.isSelected()) {
				q=q.concat("orologio='Automatico', ");
			}
			else {
				q=q.concat("orologio='Legale', ");
			}
			
			if(condTerzoResponsabile.isSelected()) {
				q=q.concat("imptipo='Condominio con terzo responsabile' ");
			}
			else if(condConAmministratore.isSelected()) {
				q=q.concat("imptipo='Condominio con amministratore' ");
			}
			else if(privato.isSelected()) {
				q=q.concat("imptipo='Privato' ");
			}
			else if(altroDitta.isSelected()) {
				q=q.concat("imptipo='Altro (Ditta)' ");
			}
			else if(altroTerzoResponsabile.isSelected()) {
				q=q.concat("imptipo='Altro (con terzo responsabile)' ");
			}
			else {
				q=q.concat("imptipo='Altro' ");
			}
			
			stm.execute(q.concat(" where codiceu="+id));
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		annullaModifica();
	}
	
	public void messaInFunzione(){
		MessaInFunzione c = new MessaInFunzione(Integer.parseInt(codice.getText()));
		try {
			c.start(primaryStage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
			c1catasto.setText(rs.getString("C1CATASTO"));
			c2catasto.setText(rs.getString("C2CATASTO"));
			c3catasto.setText(rs.getString("C3CATASTO"));
			c4catasto.setText(rs.getString("C4CATASTO"));
			c5catasto.setText(rs.getString("C5CATASTO"));
			c6catasto.setText(rs.getString("C6CATASTO"));
			
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
				statusImpianto.setText("Impianto Acceso");
				statusImpianto.setStyle("-fx-text-fill: green;");
				impStat=true;
			}
			else {
				statusImpianto.setText("Impianto Spento");
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
		} 
	}
	
	public void gotoMain() {
		Main m = new Main();
		m.start(primaryStage);
	}
	
	public void gotoBruciatori() {
		Bruciatore b = new Bruciatore();
		b.start(primaryStage);
	}
	
	public void gotoInstallatori() {
		Installatori i = new Installatori();
		i.start(primaryStage);
	}
	
	public void gotoAmministratori() {
		Amministratori a = new Amministratori();
		a.start(primaryStage);
	}
	
	public void gotoRicercaUtenti() {
		RicercaUtenti r = new RicercaUtenti();
		try {
			r.start(primaryStage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void gotoCaldaie() {
		Caldaie c = new Caldaie();
		c.start(primaryStage);
	}
	
	public void gotoStoricoInterventi() {
		try {
			StoricoPerUtente s = new StoricoPerUtente("select codiceu, nomeu, cognomeu, indirizzou, comuneu, telefonou, imptipo from utenti where codiceu='"+rs.getInt("CODICEU")+"'");
			s.start(primaryStage);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
