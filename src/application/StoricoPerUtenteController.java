package application;

import java.awt.print.PageFormat;
import java.awt.print.PrinterJob;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Vector;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class StoricoPerUtenteController implements Initializable{
	static String query; 
	public static Integer i;
	public static String id;
	private Connection connection;
	private Statement stm;
	private ResultSet rs;
	private Vector<Integer> coda;
	static Stage primaryStage;
	static int codice = -1;
	private Vector<String> data;
	private Vector<String> motivo;
	
	public String dataL;
	public String motivoL;
	
	@FXML
	private GridPane gp;
	@FXML
	private GridPane analisi;
	@FXML
	private ScrollPane sp;

	@FXML
	private Button allegato2;
	@FXML
	private Button foglioL;
	@FXML
	private Button cancella;
	
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

		
		try {
			
//			connection = DriverManager.getConnection(connectionString);
			ConnDB conn = new ConnDB();
			connection = conn.getConnection();
			stm = connection.createStatement();
			rs= stm.executeQuery(query);
			
			coda = new Vector<Integer>();
			data = new Vector<String>();
			motivo = new Vector<String>();
			
			int i=1;
			while (rs.next()) {
				coda.add(Integer.parseInt(rs.getString("CODICEU")));
				
				Label t1= new Label(" "+rs.getString("CODICEU"));
				TextField t2= new TextField(rs.getString("COGNOMEU"));
				TextField t3= new TextField(rs.getString("NOMEU"));
				TextField t4= new TextField(rs.getString("INDIRIZZOU"));
				TextField t5= new TextField(rs.getString("COMUNEU"));
				TextField t6= new TextField(rs.getString("TELEFONOU"));
				TextField t7= new TextField(rs.getString("IMPTIPO"));
				
				t2.setEditable(false);
				t3.setEditable(false);
				t4.setEditable(false);
				t5.setEditable(false);
				t6.setEditable(false);
				t7.setEditable(false);
				
				refresh(t2);
				refresh(t3);
				refresh(t4);
				refresh(t5);
				refresh(t6);
				refresh(t7);
							
				gp.addRow(i, t1, t2, t3, t4, t5, t6, t7);
				i++;
			}
			
			foglioL.setDisable(true);
			allegato2.setDisable(true);
			cancella.setDisable(true);
			
			if (codice != -1) {
				refresh();
				allegato2.setDisable(false);
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
	
	public void refresh() {
		data.clear();
		motivo.clear();
		
		String s="select * from utenti as u join ricint as r on u.codiceu=r.codiceu where u.codiceu=? order by r.datach desc";
		
		
		try {
//			rs= stm.executeQuery(s);
			
			PreparedStatement prepStat = connection.prepareStatement(s);
			prepStat.setInt(1, codice);
			
			rs = prepStat.executeQuery();		
			
			
			analisi.getChildren().clear();
			analisi.setGridLinesVisible(false);
			analisi.setGridLinesVisible(true);
			
			int i=0;
			while (rs.next()) {
				
				data.add(rs.getString("r.DATACH"));
				motivo.add(rs.getString("r.MOTIVOCH"));
				
				Label t1= new Label(" "+rs.getString("r.CODICEU"));
				TextField t2= new TextField(" "+rs.getString("r.DATACH"));
				TextField t3= new TextField(" "+rs.getString("r.CODMANU"));
				TextField t4= new TextField(" "+rs.getString("r.MOTIVOCH"));
				TextField t5= new TextField(" "+rs.getString("r.COGNOMECH"));
				TextField t6= new TextField(" "+rs.getString("r.TELECH"));
				TextField t7= new TextField(" "+rs.getString("r.DATAINT"));
				TextField t8= new TextField(" "+rs.getString("r.TECNICO"));
				
				t2.setEditable(false);
				t3.setEditable(false);
				t4.setEditable(false);
				t5.setEditable(false);
				t6.setEditable(false);
				t7.setEditable(false);
				t8.setEditable(false);
				
				intervento(t2);
				intervento(t3);
				intervento(t4);
				intervento(t5);
				intervento(t6);
				intervento(t7);
				intervento(t8);
							
				analisi.addRow(i, t1, t2, t3, t4, t5, t6, t7, t8);
				i++;
			}						
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	private void refresh(TextField t) {
        t.setOnMouseClicked(e -> {
        	
					TextField source = (TextField) e.getSource();
					int r=gp.getRowIndex(source);
					
					int cod = coda.get(r-1);
					
					codice=cod;
					data.clear();
					motivo.clear();
					
					String s="select * from utenti as u join ricint as r on u.codiceu=r.codiceu where u.codiceu=? order by r.datach desc";
					
					
					try {
//						rs= stm.executeQuery(s);
						
						PreparedStatement prepStat = connection.prepareStatement(s);
						prepStat.setInt(1, cod);
						
						rs = prepStat.executeQuery();		
						
						
						analisi.getChildren().clear();
						analisi.setGridLinesVisible(false);
						analisi.setGridLinesVisible(true);
						
						int i=0;
						while (rs.next()) {
							
							data.add(rs.getString("r.DATACH"));
							motivo.add(rs.getString("r.MOTIVOCH"));
							
							Label t1= new Label(" "+rs.getString("r.CODICEU"));
							TextField t2= new TextField(" "+rs.getString("r.DATACH"));
							TextField t3= new TextField(" "+rs.getString("r.CODMANU"));
							TextField t4= new TextField(" "+rs.getString("r.MOTIVOCH"));
							TextField t5= new TextField(" "+rs.getString("r.COGNOMECH"));
							TextField t6= new TextField(" "+rs.getString("r.TELECH"));
							TextField t7= new TextField(" "+rs.getString("r.DATAINT"));
							TextField t8= new TextField(" "+rs.getString("r.TECNICO"));
							
							t2.setEditable(false);
							t3.setEditable(false);
							t4.setEditable(false);
							t5.setEditable(false);
							t6.setEditable(false);
							t7.setEditable(false);
							t8.setEditable(false);
							
							intervento(t2);
							intervento(t3);
							intervento(t4);
							intervento(t5);
							intervento(t6);
							intervento(t7);
							intervento(t8);
										
							analisi.addRow(i, t1, t2, t3, t4, t5, t6, t7, t8);
							i++;
						}						
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					if (allegato2.isDisabled()) {
						allegato2.setDisable(false);
					}
					
        });
        
    }
	private void intervento(TextField t) {
		t.setOnMouseClicked(e -> {
			if(e.getButton().equals(MouseButton.PRIMARY)){
				if(e.getClickCount() == 2){
					
					foglioL.setDisable(false);
					
					TextField source = (TextField) e.getSource();
					int r=gp.getRowIndex(source);

					String mot = motivo.get(r);
					String dat= data.get(r);

					String s="select * from utenti as u join ricint as r on u.codiceu=r.codiceu where u.codiceu=? and r.datach=? and r.motivoch=?";

					try {				
						PreparedStatement prepStat = connection.prepareStatement(s);
						prepStat.setInt(1, codice);
						prepStat.setString(2, dat);
						prepStat.setString(3, mot);

						rs = prepStat.executeQuery();
						rs.next();
						RichiestaIntervento c = new RichiestaIntervento(rs, this);

						c.start(primaryStage);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if(e.getClickCount() == 1) {
					cancella.setDisable(false);
					foglioL.setDisable(false);
					TextField source = (TextField) e.getSource();
					int r = gp.getRowIndex(source);
					motivoL = motivo.get(r);
					dataL= data.get(r);
					//procedo con la stampa senza aprire la finestra della richiesta intervento
				}
			}
		});
	}
	
	public void printFoglioL() {
		CaldaiaIntervento ci = new CaldaiaIntervento(codice, this);
		ci.start(null);
		
	}
	
	public void creaDocumentoL(String idcaldaia) {
		
		DateConverter dateConv = new DateConverter();
		
		try {
			
			ResultSet rsInt = stm.executeQuery("select * from ricint where codiceu='"+codice+"' and datach='"+dataL+"' and motivoch='"+motivoL+"'");
			rsInt.next();	
			
			ResultSet rsUtente = connection.createStatement().executeQuery("select u.cognomeu, u.nomeu, u.indirizzou, u.numerou, u.comuneu, u.modello"+idcaldaia+", u.mf"+idcaldaia+", "
					+ "u.matri"+idcaldaia+", u.cognomea, u.indirizzoa, u.numeroa, u.comunea, u.cfivaa from utenti as u where u.codiceu='"+codice+"'");
			rsUtente.next();
			
			System.out.println(rsUtente.getString("mf"+idcaldaia));
			
			FoglioLavoro r = new FoglioLavoro(new Stage());
			r.replace(dateConv.mysqlToLocal(rsInt.getString("datach")), 
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
	
	public void printAllegato2() {
		CaldaiaAllegato2 ca2 = new CaldaiaAllegato2(codice, this);
		ca2.start(new Stage());
	}
	
	public void creaDocumentoAl2(String idElemento, String tecnico) {
		
		try {
		
		ResultSet rsUtente; // il numero della caldaia lo ho già ... è idElemento
		ResultSet rsCald;
		Date date = new Date();
		
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			String data = sdf.format(date);
		
		
			String sqlU = "select c"+idElemento+"catasto, comuneu, provinciau, indirizzou, numerou, cognomea, cfivaa, indirizzoa, numeroa, comunea, provinciaa,"
					+ " dittac"+idElemento+", modelloc"+idElemento+", matric"+idElemento+" from utenti where codiceu='"+codice+"'";
			System.out.println("dati utente: "+sqlU);
			rsUtente = stm.executeQuery(sqlU);
			rsUtente.next();
			
			String ditta = rsUtente.getString("DITTAC"+idElemento);
			String modello = rsUtente.getString("MODELLOC"+idElemento);
			
			String sqlC = "select pnfc from caldaie where dittac like '"+ditta+"' and modelloc like '"+modello+"'";
			System.out.println("dati pot foc: "+sqlC);
			rsCald = connection.createStatement().executeQuery(sqlC);
			rsCald.next();
			
			Allegato2 allegato2 = new Allegato2(new Stage());
			allegato2.replace(rsUtente.getString("c"+idElemento+"catasto"), 
							  rsUtente.getString("comuneu"), 
							  rsUtente.getString("provinciau"), 
							  rsUtente.getString("indirizzou"), 
							  rsUtente.getString("numerou"), 
							  rsUtente.getString("cognomea"), 
							  rsUtente.getString("cfivaa"), 
							  rsUtente.getString("indirizzoa"), 
							  rsUtente.getString("numeroa"), 
							  rsUtente.getString("comunea"), 
							  rsUtente.getString("provinciaa"), 
							  idElemento+"",
							  rsUtente.getString("dittac"+idElemento), 
							  rsUtente.getString("modelloc"+idElemento), 
							  rsUtente.getString("matric"+idElemento), 
							  rsCald.getString("pnfc"), 
							  data,
							  tecnico);
			System.out.println("stampa allegato");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void cancella() {
		
		String delSql = "delete from ricint where codiceu='"+codice+"' and motivoch='"+motivoL+"' and datach='"+dataL+"'";
		try {
			stm.execute(delSql);
			refresh();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void seleziona() {
		
		RicercaUtenti r = new RicercaUtenti(true);
		try {
			analisi.getChildren().clear();
			codice = -1;
			r.start(primaryStage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void gotoSchedaUtente() {
		SchedaUtente su;
		try {
			su = new SchedaUtente(codice!=-1?codice:1);
			su.start(primaryStage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	
}
