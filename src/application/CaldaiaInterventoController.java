package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class CaldaiaInterventoController implements Initializable {
	
	private Connection connection;
	private Statement stm;
	private ResultSet rs;
	
	static RichiestaInterventoController ric;
	static int codiceu;
	
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
		
		if(c1.isSelected() && c1.getText() != "") {
			ric.creaDocumento("c1");
		} else if(c2.isSelected() && c2.getText() != "") {
			ric.creaDocumento("c2");
		} else if(c3.isSelected() && c3.getText() != "") {
			ric.creaDocumento("c3");
		} else if(c4.isSelected() && c4.getText() != "") {
			ric.creaDocumento("c4");
		} else if(c5.isSelected() && c5.getText() != "") {
			ric.creaDocumento("c5");
		} else if(c6.isSelected() && c6.getText() != "") {
			ric.creaDocumento("c6");
		}
		
		annulla();
		
	}
	
	public void annulla() {
		
		Stage s = (Stage) annulla.getScene().getWindow();
		s.close();
		
	}

}
