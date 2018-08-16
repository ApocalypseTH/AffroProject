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

public class AmministratoriController implements Initializable{
	public static Stage primaryStage;
	private Connection connection;
	private Statement stm;
	private ResultSet rs;
	private Vector<String> codac;
	private String ammin;
	

	@FXML
	private ScrollPane sp;
	@FXML
	private GridPane gp;
	@FXML
	private TextField amministratore;
	@FXML
	private TextField comune;
	@FXML
	private TextField provincia;
	@FXML
	private TextField via;
	@FXML
	private TextField numero;
	@FXML
	private TextField cf;
	@FXML
	private TextField telefono;
	@FXML
	private Label nAmministratori;
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
		
		refreshTabella("select * from ammin order by cognomea");
		
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
				
				codac.add(rs.getString("COGNOMEA"));
				
				Label t1= new Label(" "+(i+1));
				TextField t2= new TextField(rs.getString("COGNOMEA"));
				
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
			rs = stm.executeQuery("select count(*) as count from ammin ");
			rs.next();
			nAmministratori.setText(rs.getString("count"));
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
				
				
				String q="select * from ammin where cognomea=?;";
				
				PreparedStatement prepStat;
				try {
					
					prepStat = connection.prepareStatement(q);
					prepStat.setString(1, c);
					
					rs= prepStat.executeQuery();
					
					rs.next();
					
					amministratore.setText(rs.getString("COGNOMEA"));
					comune.setText(rs.getString("COMUNEA"));
					telefono.setText(rs.getString("TELEFONOA"));
					via.setText(rs.getString("INDIRIZZOA"));
					numero.setText(rs.getString("NUMEROA"));
					provincia.setText(rs.getString("PROVINCIAA"));
					cf.setText(rs.getString("CFIVAA"));
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
		 });
	}
	
	public void modifica() {
		
		ammin = amministratore.getText();
		
		conferma.setVisible(true);
		annulla.setVisible(true);
		
		modifica.setDisable(true);
		cerca.setDisable(true);
		aggiungi.setDisable(true);
		elimina.setDisable(true);
		
		sp.setMouseTransparent(true);
		
		amministratore.setEditable(true);
		via.setEditable(true);
		numero.setEditable(true);
		comune.setEditable(true);
		provincia.setEditable(true);
		telefono.setEditable(true);
		cf.setEditable(true);
	}
	
	public void confermaModifica() {
		try {
			String q = "update ammin set cognomea='"+amministratore.getText()+"', indirizzoa='"+via.getText()+"', numeroa='"+numero.getText()+"', comunea='"+comune.getText()+"', provinciaa='"+provincia.getText()+"', telefonoa='"+telefono.getText()+"', cfivaa='"+cf.getText()+"'";
			
			
			stm.execute(q.concat(" where cognomea='"+ammin+"'"));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		refreshTabella("select * from ammin order by cognomea");
		
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
		
		amministratore.setEditable(false);
		via.setEditable(false);
		numero.setEditable(false);
		comune.setEditable(false);
		provincia.setEditable(false);
		telefono.setEditable(false);
		cf.setEditable(false);

	}
	
	public void cercaW(){
		CercaAmministratori c = new CercaAmministratori(this);
		try {
			c.start(primaryStage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void cerca( String amm) {
		refreshTabella("select * from ammin where cognomea like '%"+amm+"%'");
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
		
		refreshTabella("select * from ammin order by cognomea");
	}
	
	public void elimina() {
		try {
			stm.execute("delete from ammin where cognomea='"+amministratore.getText()+"'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		refreshTabella("select * from ammin order by cognomea");
	}
	
public void aggiungi() {
		
		ammin = amministratore.getText();
		
		confermaAggiunta.setVisible(true);
		annullaAggiunta.setVisible(true);
		
		modifica.setDisable(true);
		cerca.setDisable(true);
		aggiungi.setDisable(true);
		elimina.setDisable(true);
		
		sp.setMouseTransparent(true);
		
		amministratore.setEditable(true);
		via.setEditable(true);
		numero.setEditable(true);
		comune.setEditable(true);
		provincia.setEditable(true);
		telefono.setEditable(true);
		cf.setEditable(true);
	
		amministratore.setText("");
		via.setText("");
		numero.setText("");
		comune.setText("");
		provincia.setText("");
		telefono.setText("");
		cf.setText("");
	}
	
	public void confermaAggiungi(){
		try {
			String q = "insert into ammin (cognomea, indirizzoa, numeroa, comunea, provinciaa, telefonoa, cfivaa) values ('"+amministratore.getText()+"', '"+via.getText()+"', '"+numero.getText()+"', '"+comune.getText()+"', '"+provincia.getText()+"', '"+telefono.getText()+"', '"+cf.getText()+"')";
			System.out.println(q);
			stm.execute(q);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		refreshTabella("select * from ammin order by cognomea");

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
		
		amministratore.setEditable(false);
		via.setEditable(false);
		numero.setEditable(false);
		comune.setEditable(false);
		provincia.setEditable(false);
		telefono.setEditable(false);
		cf.setEditable(false);
	}
}


