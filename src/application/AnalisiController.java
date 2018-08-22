package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Vector;

import com.sun.prism.TextureMap;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class AnalisiController implements Initializable{
	public static Stage primaryStage;
	private Connection connection;
	private Statement stm;
	private ResultSet rs;
	private DateConverter d;
	
	private String dataM;
	private String caldaiaM;
	
	static int codiceu = 3;
	
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
		
		d = new DateConverter();
		
		try {
			ConnDB conn = new ConnDB();
			connection = conn.getConnection();
			stm = connection.createStatement();
			
			codaIdCald = new Vector<String>();
			codaData = new Vector<String>();
			
			rs = stm.executeQuery("select nomeu, cognomeu from utenti where codiceu='"+codiceu+"'");
			rs.next();
			utente.setText(codiceu+" - "+rs.getString("cognomeu")+" "+rs.getString("nomeu"));
			
			refreshTabella("select * from analisi where codiceu='"+codiceu+"'");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void refreshTabella(String query) {
		
		try {
			
			rs = stm.executeQuery(query);
			
			codaData.clear();
			codaIdCald.clear();
			
			gp.getChildren().clear();
			gp.setGridLinesVisible(false);
			gp.setGridLinesVisible(true);			
			
			int i=0;
			while(rs.next()) {
				
				codaIdCald.addElement(rs.getString("id"));
				codaData.addElement(rs.getString("data"));
				
				Label t1= new Label(" "+i);
				TextField t2= new TextField(d.mysqlToLocal(rs.getString("data"))); //data analisi
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
		} catch (ParseException e) {
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
						if(rs.next()) {
							this.data.setText(d.mysqlToLocal(rs.getString("data")));
							caldBru.setText(rs.getString("elemento"));
							modello.setText(rs.getString("modello"));
							matricola.setText(rs.getString("matri"));
							combustibile.setText(rs.getString("comb"));
							tempFumi.setText(rs.getString("tempfumi"));
							tempAmb.setText(rs.getString("tempamb"));
							o2.setText(rs.getString("o2"));
							barach.setText(rs.getString("bach"));
							coMis.setText(rs.getString("co"));
							portComb.setText(rs.getString("portcomb"));
							indAria.setText(rs.getString("aria"));
							co2.setText(rs.getString("co2"));
							coCalc.setText(rs.getString("cocal"));
							perdCal.setText(rs.getString("perdita"));
							rendComb.setText(rs.getString("rendim"));
							potTerm.setText(rs.getString("potenza"));
							tiraggio.setText(rs.getString("ver0"));
							rispBarach.setText(rs.getString("ver1"));
							co1000ppm.setText(rs.getString("ver2"));
							rendDPR.setText(rs.getString("ver3"));
							statoCoib.setText(rs.getString("coiben"));
							statoCanna.setText(rs.getString("canna"));
							dispRegCtrl.setText(rs.getString("regctrl"));
							sistAer.setText(rs.getString("aera"));
	
						}
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
        });
        
    }
	
	public void modifica() {
		
		try {
			dataM = rs.getString("data");
			caldaiaM = rs.getString("id");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		gp.setMouseTransparent(true);
		
		elimina.setDisable(false);
		annulla.setDisable(false);
		conferma.setDisable(false);
		
		stampa.setDisable(true);
		sceltaStp.setDisable(true);
		nuova.setDisable(true);
		modifica.setDisable(true);
		
		data.setEditable(true);
		tempFumi.setEditable(true);
		tempAmb.setEditable(true);
		o2.setEditable(true);
		barach.setEditable(true);
		coMis.setEditable(true);
		portComb.setEditable(true);
		indAria.setEditable(true);
		co2.setEditable(true);
		coCalc.setEditable(true);
		perdCal.setEditable(true);
		rendComb.setEditable(true);
		potTerm.setEditable(true);
		tiraggio.setEditable(true);
		rispBarach.setEditable(true);
		co1000ppm.setEditable(true);
		rendDPR.setEditable(true);
		statoCoib.setEditable(true);
		statoCanna.setEditable(true);
		dispRegCtrl.setEditable(true);
		sistAer.setEditable(true);
	}
	
	public void confermaModifica() {
		
		String q;
		try {
			q = "update analisi set "
					+ "data='"+d.localToMysql(data.getText())
					+ "', tempfumi='"+tempFumi.getText()
					+ "', tempamb='"+tempAmb.getText()
					+ "', co2 ='"+co2.getText()
					+ "', bach='"+barach.getText()
					+ "', co='"+coMis.getText()
					+ "', o2='"+o2.getText()
					+ "', perdita='"+perdCal.getText()
					+ "', rendim='"+rendComb.getText()
					+ "', portcomb='"+portComb.getText()
					+ "', aria='"+indAria.getText()
					+ "', cocal='"+coCalc.getText()
					+ "', potenza='"+potTerm.getText()
					+ "', ver0='"+tiraggio.getText()
					+ "', ver1='"+rispBarach.getText()
					+ "', ver2='"+co1000ppm.getText()
					+ "', ver3='"+rendDPR.getText()
					+ "', coiben='"+statoCoib.getText()
					+ "', canna='"+statoCanna.getText()
					+ "', regctrl='"+dispRegCtrl.getText()
					+ "', aera='"+sistAer.getText()
					+ "' where codiceu='"+codiceu+"' and data='"+dataM+"' and id='"+caldaiaM+"'";

			stm.execute(q);
			annullaModifica();
			refreshTabella("select * from analisi where codiceu='"+codiceu+"'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Attenzione");
			alert.setHeaderText("Formato data errato \nFormato corretto: gg/MM/aaaa");
			alert.showAndWait();
		}
		
	}
	
	public void annullaModifica() {
		
		elimina.setDisable(true);
		annulla.setDisable(true);
		conferma.setDisable(true);
		
		gp.setMouseTransparent(false);
		
		stampa.setDisable(false);
		sceltaStp.setDisable(false);
		nuova.setDisable(false);
		modifica.setDisable(false);
		
		data.setEditable(false);
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
		
	}
	
	public void elimina() {
		
		try {
			stm.execute("delete from analisi where codiceu='"+codiceu+"' and data='"+rs.getString("data")+"' and id='"+rs.getString("id")+"'");
			annullaModifica();
			refreshTabella("select * from analisi where codiceu='"+codiceu+"'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
