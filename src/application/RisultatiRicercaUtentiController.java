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

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class RisultatiRicercaUtentiController implements Initializable{
	static Stage primaryStage;
	public static String query;
	public static Integer i;
	public static String id;
	private Connection connection;
	private Statement stm;
	private ResultSet rs;
	private Vector<Integer> coda;
	private int cod;
	
	@FXML
	private GridPane gp;
	@FXML
	private TextField cognome;
	@FXML
	private TextField nome;
	@FXML
	private TextField indirizzo;
	@FXML
	private TextField localita;
	@FXML
	private TextField cap;
	@FXML
	private TextField comune;
	@FXML
	private TextField telefono;
	@FXML
	private TextField cellulare;
	@FXML
	private TextField cfiva;
	@FXML
	private TextField c1;
	@FXML
	private TextField c2;
	@FXML
	private TextField c3;
	@FXML
	private TextField c4;
	@FXML
	private TextField c5;
	@FXML
	private TextField c6;
	@FXML
	private TextField b1;
	@FXML
	private TextField b2;
	@FXML
	private TextField b3;
	@FXML
	private TextField b4;
	@FXML
	private TextField b5;
	@FXML
	private TextField b6;
	@FXML
	private TextField amministratore;
	@FXML
	private TextField installatore;
	@FXML
	private TextField codManu;
	@FXML
	private TextField manuProgrammata;
	@FXML
	private TextField analisi;
	@FXML
	private TextField bollino;
	@FXML
	private TextField messaInFunzione;
	@FXML
	private Button schedaUtente;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		String connectionString="jdbc:mysql://127.0.0.1:3306/affro?user=root&password=";

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		
		try {
			
			connection = DriverManager.getConnection(connectionString);
			stm = connection.createStatement();
			rs= stm.executeQuery(query);
			
			System.out.println(query);
			
			coda= new Vector<Integer>();
			
			int i=1;
			while (rs.next()) {				
				Label t1= new Label(" "+rs.getString("CODICEU"));
				TextField t2= new TextField(rs.getString("COGNOMEU"));
				TextField t3= new TextField(rs.getString("NOMEU"));
				
				t2.setEditable(false);
				t3.setEditable(false);

				coda.add(Integer.parseInt(rs.getString("CODICEU")));
				
				refresh(t2);
				refresh(t3);
							
				gp.addRow(i, t1, t2, t3);
				i++;
			}
			
			schedaUtente.setDisable(true);
					
		} catch (SQLException e) {
			e.printStackTrace();
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Attenzione");
			alert.setHeaderText("Non inserire caratteri sensibili come ' o /");
			alert.showAndWait();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			
		}
	}
	
	private void refresh(TextField t) {
        t.setOnMouseClicked(e -> {
					TextField source = (TextField) e.getSource();
					int r=gp.getRowIndex(source);
					
					cod = coda.get(r-1);
					
					String s="select * from utenti where codiceu=?";
					
					try {
//						rs= stm.executeQuery(s);
						
						PreparedStatement prepStat = connection.prepareStatement(s);
						prepStat.setInt(1, cod);
						
						rs = prepStat.executeQuery();
						
						rs.next();
						
						cognome.setText(rs.getString("COGNOMEU"));
						nome.setText(rs.getString("NOMEU"));
						indirizzo.setText(rs.getString("INDIRIZZOU")+" "+rs.getString("NUMEROU"));
						localita.setText(rs.getString("lOCALITAU"));
						cap.setText(rs.getString("CAPU"));
						comune.setText(rs.getString("COMUNEU"));
						telefono.setText(rs.getString("TELEFONOU"));
						cellulare.setText(rs.getString("CELLULAREU"));
						cfiva.setText(rs.getString("CFIVAU"));
						
						c1.setText(rs.getString("DITTAC1")+" "+rs.getString("MODELLOC1"));
						c2.setText(rs.getString("DITTAC2")+" "+rs.getString("MODELLOC2"));
						c3.setText(rs.getString("DITTAC3")+" "+rs.getString("MODELLOC3"));
						c4.setText(rs.getString("DITTAC4")+" "+rs.getString("MODELLOC4"));
						c5.setText(rs.getString("DITTAC5")+" "+rs.getString("MODELLOC5"));
						c6.setText(rs.getString("DITTAC6")+" "+rs.getString("MODELLOC6"));
						
						b1.setText(rs.getString("DITTAB1")+" "+rs.getString("MODELLOB1"));
						b2.setText(rs.getString("DITTAB2")+" "+rs.getString("MODELLOB2"));
						b3.setText(rs.getString("DITTAB3")+" "+rs.getString("MODELLOB3"));
						b4.setText(rs.getString("DITTAB4")+" "+rs.getString("MODELLOB4"));
						b5.setText(rs.getString("DITTAB5")+" "+rs.getString("MODELLOB5"));
						b6.setText(rs.getString("DITTAB6")+" "+rs.getString("MODELLOB6"));
						
						amministratore.setText(rs.getString("COGNOMEA"));
						
						installatore.setText(rs.getString("DITTAI"));
						
						codManu.setText(rs.getString("CODMANU"));
						manuProgrammata.setText(rs.getString("MANPROGM"));
						analisi.setText(rs.getString("ANALCOMB"));
						bollino.setText(rs.getString("BOLLINO"));
						messaInFunzione.setText(rs.getString("CONTRATM"));
						
						schedaUtente.setDisable(false);
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
        });
        
    }
	
	public void gotoSchedaUtente() {
		SchedaUtente su;
		try {
			su = new SchedaUtente(cod);
			su.start(primaryStage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
