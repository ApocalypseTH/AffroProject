package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.Vector;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class AnalisiController implements Initializable{
	public static Stage primaryStage;
	private Connection connection;
	private Statement stm;
	private ResultSet rs;
	
	static int codiceu;
	
	@FXML
	private TextField data;
	@FXML
	private TextField caldBru;
	@FXML
	private TextField modello;
	@FXML
	private TextField matricola;
	@FXML
	private TextField combustibile;
	@FXML
	private TextField tempFumi;
	@FXML
	private TextField tempAmb;
	@FXML
	private TextField o2;
	@FXML
	private TextField barach;
	@FXML
	private TextField coMis;
	@FXML
	private TextField portComb;
	@FXML
	private TextField indAria;
	@FXML
	private TextField co2;
	@FXML
	private TextField coCalc;
	@FXML
	private TextField perdCal;
	@FXML
	private TextField rendComb;
	@FXML
	private TextField potTerm;
	@FXML
	private TextField tiraggio;
	@FXML
	private TextField rispBarach;
	@FXML
	private TextField co1000ppm;
	@FXML
	private TextField rendDPR;
	@FXML
	private TextField statoCoib;
	@FXML
	private TextField statoCanna;
	@FXML
	private TextField dispRegCtrl;
	@FXML
	private TextField sistAer;
	@FXML
	private TextField utente;
	
	@FXML
	private GridPane gp;
	@FXML
	private ScrollPane sp;
	
	@FXML
	private Button elimina;
	@FXML
	private Button conferma;
	@FXML
	private Button annulla;
	@FXML
	private Button modifica;
	@FXML
	private Button nuova;
	@FXML
	private Button stampa;
	@FXML
	private Button sceltaStp;
	
	private Vector<String> codaIdCald;
	private Vector<String> codaData;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		data.setEditable(false);
		caldBru.setEditable(false);
		modello.setEditable(false);
		matricola.setEditable(false);
		combustibile.setEditable(false);
		tempFumi.setEditable(false);
		tempAmb.setEditable(false);
		o2.setEditable(false);
		barach.setEditable(false);
		coMis.setEditable(false);
		portComb.setEditable(false);
		indAria.setEditable(false);
		co2.setEditable(false);
		coCalc.setEditable(false);
		perdCal.setEditable(false);
		rendComb.setEditable(false);
		potTerm.setEditable(false);
		tiraggio.setEditable(false);
		rispBarach.setEditable(false);
		co1000ppm.setEditable(false);
		rendDPR.setEditable(false);
		statoCoib.setEditable(false);
		statoCanna.setEditable(false);
		dispRegCtrl.setEditable(false);
		sistAer.setEditable(false);
		utente.setEditable(false);
		
		elimina.setDisable(true);
		annulla.setDisable(true);
		conferma.setDisable(true);
		
		try {
			ConnDB conn = new ConnDB();
			connection = conn.getConnection();
			stm = connection.createStatement();
			
			rs = stm.executeQuery("select nomeu, cognomeu from utenti where codiceu='"+codiceu+"'");
			rs.next();
			utente.setText(codiceu+" - "+rs.getString("cognomeu")+" "+rs.getString("nomeu"));
			
			rs = stm.executeQuery("select * from analisi where codiceu='"+codiceu+"'");
			
			codaIdCald = new Vector<String>();
			codaData = new Vector<String>();
			
			gp.getChildren().clear();
			gp.setGridLinesVisible(false);
			gp.setGridLinesVisible(true);			
			
			int i=0;
			while(rs.next()) {
				
				codaIdCald.addElement(rs.getString("id"));
				codaData.addElement(rs.getString("data"));
				
				Label t1= new Label(" "+i);
				TextField t2= new TextField(rs.getString("data")); //data analisi
				TextField t3= new TextField(rs.getString("id")); //id caldaia
				
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
	
	private void refresh(TextField t) {
        t.setOnMouseClicked(e -> {
					TextField source = (TextField) e.getSource();
					int r = gp.getRowIndex(source);
					
					String idCald = codaIdCald.get(r);
					String data = codaData.get(r);
					
					String sql = "select * from analisi where codiceu='"+codiceu+"' and id='"+idCald+"' and data='"+data+"'";
					try {
						
						rs = stm.executeQuery(sql);
						while(rs.next()) {
						//	System.out.println(rs.getString("data"));
						
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
        });
        
    }

}
