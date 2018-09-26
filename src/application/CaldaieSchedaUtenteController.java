package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.Vector;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class CaldaieSchedaUtenteController implements Initializable{
	public static Stage primaryStage;
	private Connection connection;
	private Statement stm;
	private ResultSet rs;
	private ToggleGroup g1 = new ToggleGroup();
	private ToggleGroup g2 = new ToggleGroup();
	private ToggleGroup g3 = new ToggleGroup();
	private Vector<String> codac;
	private Vector<String> codam;
	private String dittaM;
	private String modelloM;
	static SchedaUtenteController su;
	static String c;
	static String m;
	static int id;

	@FXML
	private ScrollPane sp;
	@FXML
	private GridPane gp;
	@FXML
	private RadioButton murale;
	@FXML
	private RadioButton basamento;
	@FXML
	private RadioButton cameraStagna;
	@FXML
	private RadioButton cameraAperta;
	@FXML
	private RadioButton istantanea;
	@FXML
	private RadioButton conBollitore;
	@FXML
	private RadioButton soloRiscaldamento;
	@FXML
	private TextField ditta;
	@FXML
	private TextField modello;
	@FXML
	private TextField potenzaFocolare;
	@FXML
	private TextField potenzaUtile;
	@FXML
	private TextField rendimentoUtile;
	@FXML
	private TextField combustibile;
	@FXML
	private TextField cert;
	@FXML
	private CheckBox tutte;
	@FXML
	private ComboBox<String> ditte;
	@FXML
	private Label nCaldaie;
	@FXML
	private Button cerca;
	@FXML
	private TextField matricola;


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
		// TODO Auto-generated method stub
		
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

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		murale.setToggleGroup(g1);
		basamento.setToggleGroup(g1);
		
		cameraAperta.setToggleGroup(g2);
		cameraStagna.setToggleGroup(g2);
		
		istantanea.setToggleGroup(g3);
		conBollitore.setToggleGroup(g3);
		soloRiscaldamento.setToggleGroup(g3);
		
		murale.setMouseTransparent(true);
		basamento.setMouseTransparent(true);
		
		cameraAperta.setMouseTransparent(true);
		cameraStagna.setMouseTransparent(true);
		
		istantanea.setMouseTransparent(true);
		conBollitore.setMouseTransparent(true);
		soloRiscaldamento.setMouseTransparent(true);
		
		if(!m.equals("") && !c.equals(""))
			refresh(c, m);
		
		codac= new Vector<String>();
		codam= new Vector<String>();
		
		try {
			rs=stm.executeQuery("select distinct dittac from caldaie order by dittac");
			while (rs.next()) {
				ditte.getItems().add(rs.getString("DITTAC"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		refreshTabella("select * from caldaie order by dittac");
		
		check();
	}
	
	
	public void dittaRefresh() {
		try {
			rs = stm.executeQuery("select count(*) as count from caldaie where dittac='"+ditte.getValue()+"' ");
			rs.next();
			nCaldaie.setText(rs.getString("count"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		refreshTabella("select * from caldaie where dittac='"+ditte.getValue()+"'");
	}
	
	public void check() {
		if(tutte.isSelected()) {
			ditte.setValue("");
			ditte.setDisable(true);
			refreshTabella("select * from caldaie order by dittac");
			
			try {
				rs = stm.executeQuery("select count(*) as count from caldaie ");
				rs.next();
				nCaldaie.setText(rs.getString("count"));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		else {
			ditte.setDisable(false);
		}
	}
	
	public void refresh(String c, String m) {
		String q="select * from caldaie where dittac=? and modelloc=?;";
		
		PreparedStatement prepStat;
		try {
			
			prepStat = connection.prepareStatement(q);
			prepStat.setString(1, c);
			prepStat.setString(2, m);
			
			rs= prepStat.executeQuery();
			
			rs.next();
			
			if("Basamento".equals(rs.getString("MBC"))) {
				basamento.setSelected(true);
			}
			else {
				murale.setSelected(true);
			}
			
			if("Camera Aperta".equals(rs.getString("CACSC"))) {
				cameraAperta.setSelected(true);
			}
			else {
				cameraStagna.setSelected(true);
			}
			
			if("Solo Riscaldamento".equals(rs.getString("BISRC"))) {
				soloRiscaldamento.setSelected(true);
			}
			else if("Con Bollitore".equals(rs.getString("BISRC"))) {
				conBollitore.setSelected(true);
			}
			else {
				istantanea.setSelected(true);
			}
			
			ditta.setText(rs.getString("DITTAC"));
			modello.setText(rs.getString("MODELLOC"));
			
			potenzaFocolare.setText(rs.getString("PNFC"));
			potenzaUtile.setText(rs.getString("PNUC"));
			rendimentoUtile.setText(rs.getString("RUC"));
			combustibile.setText(rs.getString("COMBC"));
			cert.setText(rs.getString("ECC"));
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void refresh(TextField t) {
		 t.setOnMouseClicked(e -> {
				TextField source = (TextField) e.getSource();
				int r=gp.getRowIndex(source);
				
				String c=codac.get(r);
				String m=codam.get(r);
				
				
				String q="select * from caldaie where dittac=? and modelloc=?;";
				
				PreparedStatement prepStat;
				try {
					
					prepStat = connection.prepareStatement(q);
					prepStat.setString(1, c);
					prepStat.setString(2, m);
					
					rs= prepStat.executeQuery();
					
					rs.next();
					
					if("Basamento".equals(rs.getString("MBC"))) {
						basamento.setSelected(true);
					}
					else {
						murale.setSelected(true);
					}
					
					if("Camera Aperta".equals(rs.getString("CACSC"))) {
						cameraAperta.setSelected(true);
					}
					else {
						cameraStagna.setSelected(true);
					}
					
					if("Solo Riscaldamento".equals(rs.getString("BISRC"))) {
						soloRiscaldamento.setSelected(true);
					}
					else if("Con Bollitore".equals(rs.getString("BISRC"))) {
						conBollitore.setSelected(true);
					}
					else {
						istantanea.setSelected(true);
					}
					
					ditta.setText(rs.getString("DITTAC"));
					modello.setText(rs.getString("MODELLOC"));
					
					potenzaFocolare.setText(rs.getString("PNFC"));
					potenzaUtile.setText(rs.getString("PNUC"));
					rendimentoUtile.setText(rs.getString("RUC"));
					combustibile.setText(rs.getString("COMBC"));
					cert.setText(rs.getString("ECC"));
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
		 });
	}
	
	public void refreshTabella(String q) {
		try {
			
			rs= stm.executeQuery(q);
			
			gp.getChildren().clear();
			gp.setGridLinesVisible(false);
			gp.setGridLinesVisible(true);
			
			codac.clear();
			codam.clear();
			
			int i=0;
			while (rs.next()) {
//				coda.add(Integer.parseInt(rs.getString("CODICEU")));
				
				codac.add(rs.getString("DITTAC"));
				codam.add(rs.getString("MODELLOC"));
				
				Label t1= new Label(" "+(i+1));
				TextField t2= new TextField(rs.getString("DITTAC"));
				TextField t3= new TextField(rs.getString("MODELLOC"));
				
				t2.setEditable(false);
				t3.setEditable(false);
				
				refresh(t2);
				refresh(t3);
							
				gp.addRow(i, t1, t2, t3);
				i++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void cercaW(){
		CercaCaldaie c = new CercaCaldaie(this);
		try {
			c.start(primaryStage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void cerca( String ditta, String modello) {
		refreshTabella("select * from caldaie where dittac like '%"+ditta+"%' and modelloc like '%"+modello+"%'");
		tutte.setSelected(false);
		check();
	}
	
	public void conferma() {
		try {
			switch(id) {
			case 1:
				su.c1ditta.setText(rs.getString("DITTAC"));
				su.c1modello.setText(rs.getString("MODELLOC"));

				su.c1combustibile.setText(rs.getString("COMBC"));
				break;
			
			case 2:
				su.c2ditta.setText(rs.getString("DITTAC"));
				su.c2modello.setText(rs.getString("MODELLOC"));
	
				su.c2combustibile.setText(rs.getString("COMBC"));
				break;
				
			case 3:
				su.c3ditta.setText(rs.getString("DITTAC"));
				su.c3modello.setText(rs.getString("MODELLOC"));

				su.c3combustibile.setText(rs.getString("COMBC"));
				break;
				
			case 4:
				su.c4ditta.setText(rs.getString("DITTAC"));
				su.c4modello.setText(rs.getString("MODELLOC"));

				su.c4combustibile.setText(rs.getString("COMBC"));
				break;
				
			case 5:
				su.c5ditta.setText(rs.getString("DITTAC"));
				su.c5modello.setText(rs.getString("MODELLOC"));
				su.c5combustibile.setText(rs.getString("COMBC"));
				break;
				
			case 6:
				su.c6ditta.setText(rs.getString("DITTAC"));
				su.c6modello.setText(rs.getString("MODELLOC"));

				su.c6combustibile.setText(rs.getString("COMBC"));
				break;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Stage stage = (Stage) cerca.getScene().getWindow();
	    stage.close();
	}
}