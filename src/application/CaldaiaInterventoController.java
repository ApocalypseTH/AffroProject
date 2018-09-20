package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class CaldaiaInterventoController implements Initializable {
	
	private Connection connection;
	private Statement stm;
	private ResultSet rs;
	
	private int lastCaldIndex;
	
	static StoricoPerUtenteController spuc;
	static RichiestaInterventoController ric;
	static int codiceu;
	static int i; //i=0 richiesta intervento, i=1 storico per utente
	
	ToggleGroup caldaie = new ToggleGroup();
	
	@FXML
	private RadioButton c1;
	@FXML
	private RadioButton c2;
	@FXML
	private RadioButton c3;
	@FXML
	private RadioButton c4;
	@FXML
	private RadioButton c5;
	@FXML
	private RadioButton c6;
	
	@FXML
	private Button conferma;
	@FXML
	private Button annulla;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		try {
			ConnDB conn = new ConnDB();
			connection = conn.getConnection();
			stm = connection.createStatement();
			
			String q = "select ";
			for(int i=1; i<=6; i++) {
				q=q+"dittac"+i+", modelloc"+i+(i==6?" ":", ");
			}
			q=q+"from utenti where codiceu='"+codiceu+"'";
			
			rs = stm.executeQuery(q);
			rs.next();
			
			c1.setText(rs.getString("dittac1")+" - "+rs.getString("modelloc1"));
			c2.setText(rs.getString("dittac2")+" - "+rs.getString("modelloc2"));
			c3.setText(rs.getString("dittac3")+" - "+rs.getString("modelloc3"));
			c4.setText(rs.getString("dittac4")+" - "+rs.getString("modelloc4"));
			c5.setText(rs.getString("dittac5")+" - "+rs.getString("modelloc5"));
			c6.setText(rs.getString("dittac6")+" - "+rs.getString("modelloc6"));
			
			lastCaldIndex=0;
			for(int i=1;  i<=6; i++) {
				if(!rs.getString("dittac"+i).isEmpty() && !rs.getString("modelloc"+i).isEmpty())
					lastCaldIndex++;
			}
			
			c1.setToggleGroup(caldaie);
			c2.setToggleGroup(caldaie);
			c3.setToggleGroup(caldaie);
			c4.setToggleGroup(caldaie);
			c5.setToggleGroup(caldaie);
			c6.setToggleGroup(caldaie);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
	}
	
	public void conferma() {
		
		if(c1.isSelected() && 1<=lastCaldIndex) {
			if (i==0) {
				ric.creaDocumento("c1");
			} else if (i==1) {
				spuc.creaDocumentoL("c1");
			}
			annulla();
		} else if(c2.isSelected() && 2<=lastCaldIndex) {
			if (i==0) {
				ric.creaDocumento("c2");
			} else if (i==1) {
				spuc.creaDocumentoL("c2");
			}
			annulla();
		} else if(c3.isSelected() && 3<=lastCaldIndex) {
			if (i==0) {
				ric.creaDocumento("c3");
			} else if (i==1) {
				spuc.creaDocumentoL("c3");
			}
			annulla();
		} else if(c4.isSelected() && 4<=lastCaldIndex) {
			if (i==0) {
				ric.creaDocumento("c4");
			} else if (i==1) {
				spuc.creaDocumentoL("c4");
			}
			annulla();
		} else if(c5.isSelected() && 5<=lastCaldIndex) {
			if (i==0) {
				ric.creaDocumento("c5");
			} else if (i==1) {
				spuc.creaDocumentoL("c5");
			}
			annulla();
		} else if(c6.isSelected() && 6<=lastCaldIndex) {
			if (i==0) {
				ric.creaDocumento("c6");
			} else if (i==1) {
				spuc.creaDocumentoL("c6");
			}
			annulla();
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Attenzione");
			alert.setHeaderText("Selezionare una caldaia valida");
			alert.showAndWait();
		}
				
	}
	
	public void annulla() {
		
		Stage s = (Stage) annulla.getScene().getWindow();
		s.close();
		
	}

}
