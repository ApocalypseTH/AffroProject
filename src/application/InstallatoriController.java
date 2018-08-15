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

public class InstallatoriController implements Initializable{
	public static Stage primaryStage;
	private Connection connection;
	private Statement stm;
	private ResultSet rs;
	private Vector<String> codac;
	private String dittaM;
	

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
	private Button conferma;
	@FXML
	private Button annulla;
	@FXML
	private Button modifica;
	@FXML
	private Button cerca;
	@FXML
	private Button elimina;
	@FXML
	private Button aggiungi;
	@FXML
	private Button confermaAggiunta;
	@FXML
	private Button annullaAggiunta;
	@FXML
	private Button annullaRicerca;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		String connectionString="jdbc:mysql://127.0.0.1:3306/affro?user=root&password=";

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		
		try {
			
			connection = DriverManager.getConnection(connectionString);
			stm = connection.createStatement();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		codac= new Vector<String>();
		
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
				
				Label t1= new Label(" "+i);
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
	
	public void modifica() {
		
		dittaM = ditta.getText();
		
		conferma.setVisible(true);
		annulla.setVisible(true);
		
		modifica.setDisable(true);
		cerca.setDisable(true);
		aggiungi.setDisable(true);
		elimina.setDisable(true);
		
		sp.setMouseTransparent(true);
		
		ditta.setEditable(true);
		comune.setEditable(true);
		telefono.setEditable(true);
	}
	
	public void confermaModifica() {
		try {
			String q = "update insta set dittai='"+ditta.getText()+"', indirizzoi='"+comune.getText()+"', telefonoi='"+telefono.getText()+"'";
			
			
			stm.execute(q.concat(" where dittai='"+dittaM+"'"));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		refreshTabella("select * from insta order by dittai");
		
		annullaModifica();
	}
	public void annullaModifica() {
		
		conferma.setVisible(false);
		annulla.setVisible(false);
		
		modifica.setDisable(false);
		cerca.setDisable(false);
		aggiungi.setDisable(false);
		elimina.setDisable(false);
		
		sp.setMouseTransparent(false);
		
		ditta.setEditable(false);
		comune.setEditable(false);
		telefono.setEditable(false);

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
		modifica.setDisable(true);
		aggiungi.setDisable(true);
		cerca.setDisable(true);
		elimina.setDisable(true);
	}
	public void annullaCerca() {
		annullaRicerca.setVisible(false);
		modifica.setDisable(false);
		aggiungi.setDisable(false);
		cerca.setDisable(false);
		elimina.setDisable(false);
		
		refreshTabella("select * from insta order by dittai");
	}
	
	public void elimina() {
		try {
			stm.execute("delete from insta where dittai='"+ditta.getText()+"'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		refreshTabella("select * from insta order by dittai");
	}
	
public void aggiungi() {
		
		dittaM = ditta.getText();
		
		confermaAggiunta.setVisible(true);
		annullaAggiunta.setVisible(true);
		
		modifica.setDisable(true);
		cerca.setDisable(true);
		aggiungi.setDisable(true);
		elimina.setDisable(true);
		
		sp.setMouseTransparent(true);
		
		ditta.setEditable(true);
		comune.setEditable(true);
		telefono.setEditable(true);


		
		ditta.setText("");
		comune.setText("");
		telefono.setText("");
	}
	
	public void confermaAggiungi(){
		try {
			String q = "insert into insta (dittai, indirizzoi, telefonoi) values ('"+ditta.getText()+"', '"+comune.getText()+"', '"+telefono.getText()+"')";			
			stm.execute(q);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		refreshTabella("select * from insta order by dittai");

		annullaAggiungi();
	}
	public void annullaAggiungi(){
		confermaAggiunta.setVisible(false);
		annullaAggiunta.setVisible(false);
		
		modifica.setDisable(false);
		cerca.setDisable(false);
		aggiungi.setDisable(false);
		elimina.setDisable(false);
		
		sp.setMouseTransparent(false);
		
		ditta.setEditable(false);
		comune.setEditable(false);
		telefono.setEditable(false);
	}
}


