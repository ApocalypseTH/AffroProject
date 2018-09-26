package application;

import java.awt.print.PageFormat;
import java.awt.print.PrinterJob;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ResourceBundle;
import java.util.Vector;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class RisultatiRicercaStoricoController implements Initializable{
	public static String query;
	public static Integer i;
	public static String id;
	public static Stage primaryStage;
	private Connection connection;
	private Statement stm;
	private ResultSet rs;
	private Vector<Integer> coda;
	private Vector<String> data;
	private Vector<String> motivo;
	
	private String dataL, motivoL;
	private int codiceu=-1;
	
	@FXML
	private GridPane gp;
	@FXML
	private TextField cognome;
	@FXML
	private TextField nome;
	@FXML
	private TextField indirizzo;
	@FXML
	private TextField localita;
	@FXML
	private TextField cap;
	@FXML
	private TextField comune;
	@FXML
	private TextField telefono;
	@FXML
	private TextField cellulare;
	@FXML
	private TextField cfiva;
	@FXML
	private TextField c1;
	@FXML
	private TextField c2;
	@FXML
	private TextField c3;
	@FXML
	private TextField c4;
	@FXML
	private TextField c5;
	@FXML
	private TextField c6;
	@FXML
	private TextField b1;
	@FXML
	private TextField b2;
	@FXML
	private TextField b3;
	@FXML
	private TextField b4;
	@FXML
	private TextField b5;
	@FXML
	private TextField b6;
	@FXML
	private TextField amministratore;
	@FXML
	private TextField installatore;
	@FXML
	private TextField codManu;
	@FXML
	private TextField manuProgrammata;
	@FXML
	private TextField analisi;
	@FXML
	private TextField bollino;
	@FXML
	private TextField messaInFunzione;
	
	@FXML
	private Button foglioL;
	
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
		RicercaUtenti su = new RicercaUtenti();
		try {
			su.start(primaryStage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void ricercheA(){
		RicercaAnalisi su = new RicercaAnalisi();
		try {
			su.start(primaryStage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void ricercheS(){
		RicercaStorico su = new RicercaStorico();
		try {
			su.start(primaryStage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void storicoPerUtente(){
		RicercaUtenti ru = new RicercaUtenti(true);
		try {
			ru.start(primaryStage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void storicoPerAmministratore(){
		StoricoPerAmministratore su = new StoricoPerAmministratore();
		try {
			su.start(primaryStage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void archivioCaldaie(){
		Caldaie su = new Caldaie();
		try {
			su.start(primaryStage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void archivioInstallatori(){
		Installatori su = new Installatori();
		try {
			su.start(primaryStage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void archivioBruciatori() {
		Bruciatore b = new Bruciatore();
		try {
			b.start(primaryStage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void archivioAmministratori(){
		Amministratori su = new Amministratori();
		try {
			su.start(primaryStage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void elencoTecnici(){
		Tecnici su = new Tecnici();
		try {
			su.start(primaryStage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void anagraficaDitta(){
		AnagraficaDitta su = new AnagraficaDitta();
		try {
			su.start(primaryStage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void unioneUtenti(){
		UnioneUtenti su = new UnioneUtenti();
		try {
			su.start(primaryStage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void parametriConnessione(){
		Connessione su = new Connessione();
		try {
			su.start(primaryStage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void percorsi(){
		Percorsi su = new Percorsi();
		try {
			su.start(primaryStage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void backup(){
		Backup su = new Backup();
		try {
			su.start(primaryStage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
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
			rs= stm.executeQuery(query);
			System.out.println(query);
			
			foglioL.setDisable(true);
			
			coda= new Vector<Integer>();
			data = new Vector<String>();
			motivo = new Vector<String>();
			
			int i=1;
			while (rs.next()) {				
				Label t1= new Label(" "+rs.getString("u.CODICEU"));
				TextField t2= new TextField(rs.getString("u.COGNOMEU")+" "+rs.getString("u.NOMEU"));
				TextField t3= new TextField(rs.getString("r.dataint"));
				TextField t4= new TextField(rs.getString("r.motivoch"));
				
				t2.setEditable(false);
				t3.setEditable(false);
				t4.setEditable(false);

				coda.add(Integer.parseInt(rs.getString("CODICEU")));
				motivo.add(rs.getString("r.motivoch"));
				data.add(rs.getString("r.dataint"));
				
				refresh(t2);
				refresh(t3);
				refresh(t4);
							
				gp.addRow(i, t1, t2, t3, t4);
				i++;
			}
					
		} catch (SQLException e) {
			e.printStackTrace();
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Attenzione");
			alert.setHeaderText("Non inserire caratteri sensibili come ' o /");
			alert.showAndWait();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			
		}
	}
	
	private void refresh(TextField t) {
        t.setOnMouseClicked(e -> {
					TextField source = (TextField) e.getSource();
					int r=gp.getRowIndex(source);
					
					int cod = coda.get(r-1);
					this.codiceu = cod;
					
					this.dataL = data.elementAt(gp.getRowIndex(source)-1);
					this.motivoL = motivo.elementAt(gp.getRowIndex(source)-1);
					
					foglioL.setDisable(false);
					
					String s="select * from utenti where codiceu=?";
					
					try {
//						rs= stm.executeQuery(s);
						
						PreparedStatement prepStat = connection.prepareStatement(s);
						prepStat.setInt(1, cod);
						
						rs = prepStat.executeQuery();
						
						rs.next();
						
						cognome.setText(rs.getString("COGNOMEU"));
						nome.setText(rs.getString("NOMEU"));
						indirizzo.setText(rs.getString("INDIRIZZOU")+", "+rs.getString("NUMEROU"));
						localita.setText(rs.getString("lOCALITAU"));
						cap.setText(rs.getString("CAPU"));
						comune.setText(rs.getString("COMUNEU"));
						telefono.setText(rs.getString("TELEFONOU"));
						cellulare.setText(rs.getString("CELLULAREU"));
						cfiva.setText(rs.getString("CFIVAU"));
						
						c1.setText(rs.getString("DITTAC1")+" "+rs.getString("MODELLOC1"));
						c2.setText(rs.getString("DITTAC2")+" "+rs.getString("MODELLOC2"));
						c3.setText(rs.getString("DITTAC3")+" "+rs.getString("MODELLOC3"));
						c4.setText(rs.getString("DITTAC4")+" "+rs.getString("MODELLOC4"));
						c5.setText(rs.getString("DITTAC5")+" "+rs.getString("MODELLOC5"));
						c6.setText(rs.getString("DITTAC6")+" "+rs.getString("MODELLOC6"));
						
						b1.setText(rs.getString("DITTAB1")+" "+rs.getString("MODELLOB1"));
						b2.setText(rs.getString("DITTAB2")+" "+rs.getString("MODELLOB2"));
						b3.setText(rs.getString("DITTAB3")+" "+rs.getString("MODELLOB3"));
						b4.setText(rs.getString("DITTAB4")+" "+rs.getString("MODELLOB4"));
						b5.setText(rs.getString("DITTAB5")+" "+rs.getString("MODELLOB5"));
						b6.setText(rs.getString("DITTAB6")+" "+rs.getString("MODELLOB6"));
						
						amministratore.setText(rs.getString("COGNOMEA"));
						
						installatore.setText(rs.getString("DITTAI"));
						
						codManu.setText(rs.getString("CODMANU"));
						manuProgrammata.setText(rs.getString("MANPROGM"));
						analisi.setText(rs.getString("ANALCOMB"));
						bollino.setText(rs.getString("BOLLINO"));
						messaInFunzione.setText(rs.getString("CONTRATM"));
						
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
        });
        
    }
	
	public void gotoSchedaUtente() {
		SchedaUtente su;
		try {
			su = new SchedaUtente(codiceu!=-1?codiceu:1);
			su.start(primaryStage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void gotoMain() {
		Main m = new Main();
		m.start(primaryStage);
	}
	
	public void scegliStampante() {
		 PrinterJob printer = PrinterJob.getPrinterJob(); // this method calls to setup a job for printing pages
		 PageFormat pFormat = printer.defaultPage(); // the page is set to default size format and orientation
		 
		 printer.printDialog();
		 try {
		     printer.print(); //if clicking ok in the print dialog, this will print the pages with the default format
		 }
		 catch (Exception pe) {
			 System.out.println(pe);
		 }
	}
	
	public void printFoglioL() {
		CaldaiaIntervento ci = new CaldaiaIntervento(codiceu, this);
		ci.start(null);
	}
	
	public void creaDocumentoL(String idcaldaia) {
		
		DateConverter dateConv = new DateConverter();
		
		try {
			
			ResultSet rsInt = stm.executeQuery("select * from ricint where codiceu='"+codiceu+"' and datach='"+dataL+"' and motivoch='"+motivoL+"'");
			rsInt.next();	
			
			ResultSet rsUtente = connection.createStatement().executeQuery("select u.cognomeu, u.nomeu, u.indirizzou, u.numerou, u.comuneu, u.modello"+idcaldaia+", u.mf"+idcaldaia+", "
					+ "u.matri"+idcaldaia+", u.cognomea, u.indirizzoa, u.numeroa, u.comunea, u.cfivaa from utenti as u where u.codiceu='"+codiceu+"'");
			rsUtente.next();
			
			System.out.println(rsUtente.getString("mf"+idcaldaia));
			
			FoglioLavoro r = new FoglioLavoro(new Stage());
			r.replace(codiceu,
						rsInt.getString("datach") == null?"":dateConv.mysqlToLocal(rsInt.getString("datach")), 
						(rsUtente.getString("cognomea").equals("")?(rsUtente.getString("cognomeu")+" "+rsUtente.getString("nomeu")):rsUtente.getString("cognomea")), 
						rsUtente.getString("cfivaa"),
						(rsUtente.getString("indirizzoa").equals("")?(rsUtente.getString("indirizzou")+(rsUtente.getString("numerou").equals("")?"":", "+rsUtente.getString("numerou"))+" - "+rsUtente.getString("comuneu")):(rsUtente.getString("indirizzoa")+(rsUtente.getString("numeroa").equals("")?"":", "+rsUtente.getString("numeroa"))+" - "+rsUtente.getString("comunea"))), 
						rsUtente.getString("modello"+idcaldaia), 
						rsUtente.getString("matri"+idcaldaia), 
						rsUtente.getString("cognomeu")+" "+rsUtente.getString("nomeu"), 
						(rsUtente.getString("indirizzou")+(rsUtente.getString("numerou").equals("")?"":", "+rsUtente.getString("numerou"))+" - "+rsUtente.getString("comuneu"))+" - "+"M.F.: "+dateConv.mysqlToLocal(rsUtente.getString("mf"+idcaldaia)),
						rsInt.getString("motivoch"), 
						rsInt.getString("noteint"));
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
