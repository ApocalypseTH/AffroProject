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

public class InstallatoriSchedaUtenteController implements Initializable{
	public static Stage primaryStage;
	private Connection connection;
	private Statement stm;
	private ResultSet rs;
	private Vector<String> codac;
	private String dittaM;
	static SchedaUtenteController su;
	static String i;
	
	@FXML
	private ScrollPane sp;
	@FXML
	private GridPane gp;
	@FXML
	private TextField ditta;
	@FXML
	private TextField comune;
	@FXML
	private TextField telefono;
	@FXML
	private Label nInstallatori;
	@FXML
	private Button cerca;
	@FXML
	private Button annullaRicerca;
	@FXML
	private Button conferma;
	

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
		
		codac= new Vector<String>();
		
		refresh(i);
		
		refreshTabella("select * from insta order by dittai");
		
	}
	
	public void refreshTabella(String q) {
		try {
			
			rs= stm.executeQuery(q);
			
			gp.getChildren().clear();
			gp.setGridLinesVisible(false);
			gp.setGridLinesVisible(true);
			
			codac.clear();
			
			int i=0;
			while (rs.next()) {
//				coda.add(Integer.parseInt(rs.getString("CODICEU")));
				
				codac.add(rs.getString("DITTAI"));
				
				Label t1= new Label(" "+(i+1));
				TextField t2= new TextField(rs.getString("DITTAI"));
				
				t2.setEditable(false);
				
				refresh(t2);
							
				gp.addRow(i, t1, t2);
				i++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			rs = stm.executeQuery("select count(*) as count from insta ");
			rs.next();
			nInstallatori.setText(rs.getString("count"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void refresh(String i) {
		String q="select * from insta where dittai=?;";
		
		PreparedStatement prepStat;
		try {
			
			prepStat = connection.prepareStatement(q);
			prepStat.setString(1, i);
			
			rs= prepStat.executeQuery();
			
			rs.next();
			
			ditta.setText(rs.getString("DITTAI"));
			comune.setText(rs.getString("INDIRIZZOI"));
			telefono.setText(rs.getString("TELEFONOI"));
			
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
				
				
				String q="select * from insta where dittai=?;";
				
				PreparedStatement prepStat;
				try {
					
					prepStat = connection.prepareStatement(q);
					prepStat.setString(1, c);
					
					rs= prepStat.executeQuery();
					
					rs.next();
					
					ditta.setText(rs.getString("DITTAI"));
					comune.setText(rs.getString("INDIRIZZOI"));
					telefono.setText(rs.getString("TELEFONOI"));
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
		 });
	}
	
	public void conferma() {
		try {
			su.installatore.setText(rs.getString("DITTAI"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Stage stage = (Stage) cerca.getScene().getWindow();
	    stage.close();
	}
		
	public void cercaW(){
		CercaInstallatori c = new CercaInstallatori(this);
		try {
			c.start(primaryStage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void cerca( String ditta) {
		refreshTabella("select * from insta where dittai like '%"+ditta+"%'");
		annullaRicerca.setVisible(true);
		cerca.setDisable(true);
	}
	public void annullaCerca() {
		annullaRicerca.setVisible(false);
		cerca.setDisable(false);
		
		refreshTabella("select * from insta order by dittai");
	}
}


