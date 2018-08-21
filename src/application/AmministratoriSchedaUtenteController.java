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

public class AmministratoriSchedaUtenteController implements Initializable{
	public static Stage primaryStage;
	private Connection connection;
	private Statement stm;
	private ResultSet rs;
	private Vector<String> codac;
	private String ammin;
	static SchedaUtenteController su;
	

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
	private Button cerca;
	@FXML
	private Button confermaAggiunta;
	@FXML
	private Button annullaAggiunta;
	@FXML
	private Button annullaRicerca;

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

	public void conferma() {
		try {
			su.amministratore.setText(rs.getString("COGNOMEA"));
			su.viaA.setText(rs.getString("INDIRIZZOA"));
			su.numeroA.setText(rs.getString("NUMEROA"));
			su.comuneA.setText(rs.getString("COMUNEA"));
			su.provA.setText(rs.getString("PROVINCIAA"));
			su.telefonoA.setText(rs.getString("TELEFONOA"));
			su.cfA.setText(rs.getString("CFIVAA"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Stage stage = (Stage) cerca.getScene().getWindow();
	    stage.close();
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
		cerca.setDisable(true);
	}
	public void annullaCerca() {
		annullaRicerca.setVisible(false);

		cerca.setDisable(false);
		
		refreshTabella("select * from ammin order by cognomea");
	}
}


