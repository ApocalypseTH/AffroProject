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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class CaldaiaAllegato2Controller implements Initializable{

	private Connection connection;
	private Statement stm;
	private ResultSet rs;
	private int lastCaldIndex;
	
	public static int codice;
	public static StoricoPerUtenteController spuc;

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
	private Button stampa;
	@FXML
	private Button annulla;
	
	@FXML
	private Label b1;
	@FXML
	private Label b2;
	@FXML
	private Label b3;
	@FXML
	private Label b4;
	@FXML
	private Label b5;
	@FXML
	private Label b6;
	
	@FXML
	private ComboBox<String> tecnici;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		try {
			ConnDB conn = new ConnDB();
			connection = conn.getConnection();
			stm = connection.createStatement();
			
			String q = "select ";
			for(int i=1; i<=6; i++) {
				q=q+"dittab"+i+", modellob"+i+", dittac"+i+", modelloc"+i+(i==6?" ":", ");
			}
			q=q+"from utenti where codiceu='"+codice+"'";
			
			System.out.println(q);
			
			rs = stm.executeQuery(q);
			rs.next();
			
			b1.setText(rs.getString("dittab1")+" - "+rs.getString("modellob1"));
			b2.setText(rs.getString("dittab2")+" - "+rs.getString("modellob2"));
			b3.setText(rs.getString("dittab3")+" - "+rs.getString("modellob3"));
			b4.setText(rs.getString("dittab4")+" - "+rs.getString("modellob4"));
			b5.setText(rs.getString("dittab5")+" - "+rs.getString("modellob5"));
			b6.setText(rs.getString("dittab6")+" - "+rs.getString("modellob6"));
			
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
			
			try {
				rs = stm.executeQuery("select * from tecnici");
				while(rs.next())
					tecnici.getItems().add(rs.getString("NOMET"));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void stampa() {
		
		String tecnico = tecnici.getValue() == null?"":tecnici.getValue();
		
		if(c1.isSelected() && 1<=lastCaldIndex) {
			spuc.creaDocumentoAl2("1", tecnico);
			annulla();
		} else if(c2.isSelected() && 2<=lastCaldIndex) {
			spuc.creaDocumentoAl2("2", tecnico);
			annulla();
		} else if(c3.isSelected() && 3<=lastCaldIndex) {
			spuc.creaDocumentoAl2("3", tecnico);
			annulla();
		} else if(c4.isSelected() && 4<=lastCaldIndex) {
			spuc.creaDocumentoAl2("4", tecnico);
			annulla();
		} else if(c5.isSelected() && 5<=lastCaldIndex) {
			spuc.creaDocumentoAl2("5", tecnico);
			annulla();
		} else if(c6.isSelected() && 6<=lastCaldIndex) {
			spuc.creaDocumentoAl2("6", tecnico);
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
