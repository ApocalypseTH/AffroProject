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
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class BruciatoreSchedaUtenteController implements Initializable{

	public static Stage primaryStage;
	private Connection connection;
	private Statement stm;
	private ResultSet rs;
	private ToggleGroup g1 = new ToggleGroup();
	private Vector<String> codab;
	private Vector<String> codam;
	private String dittaB;
	private String modelloB;
	static SchedaUtenteController su;
	static int id;
	static String mat;
	

	@FXML
	private ScrollPane sp;
	@FXML
	private GridPane gp;
	@FXML
	private RadioButton monostadio;
	@FXML
	private RadioButton bistadio;
	@FXML
	private RadioButton modulante;
	@FXML
	private CheckBox emulsione;
	@FXML
	private TextField ditta;
	@FXML
	private TextField modello;
	@FXML
	private TextField tipo;
	@FXML
	private TextField potenzaMin;
	@FXML
	private TextField potenzaMax;
	@FXML
	private TextField combustibile;
	@FXML
	private TextField certificazione;
	@FXML
	private TextField matricola;
	@FXML
	private CheckBox tutte;
	@FXML
	private ComboBox<String> ditte;
	@FXML
	private Label nBruciatori;
	@FXML
	private Button cerca;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
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
		
		monostadio.setToggleGroup(g1);
		bistadio.setToggleGroup(g1);
		modulante.setToggleGroup(g1);
		
		monostadio.setMouseTransparent(true);
		bistadio.setMouseTransparent(true);
		modulante.setMouseTransparent(true);
		emulsione.setMouseTransparent(true);
		
		matricola.setText(mat);
		
		codab = new Vector<String>();
		codam = new Vector<String>();
		
		try {
			rs=stm.executeQuery("select distinct dittab from brucia order by dittab");
			while (rs.next()) {
				ditte.getItems().add(rs.getString("DITTAB"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		refreshTabella("select * from brucia order by dittab");
		
		check();
		
	}
	
	public void dittaRefresh() {
		try {
			rs = stm.executeQuery("select count(*) as count from brucia where dittab='"+ditte.getValue()+"' ");
			rs.next();
			nBruciatori.setText(rs.getString("count"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		refreshTabella("select * from brucia where dittab='"+ditte.getValue()+"'");
	}
	
	public void check() {
		if(tutte.isSelected()) {
			ditte.setValue("");
			ditte.setDisable(true);
			refreshTabella("select * from brucia order by dittab");
			
			try {
				rs = stm.executeQuery("select count(*) as count from brucia ");
				rs.next();
				nBruciatori.setText(rs.getString("count"));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		else {
			ditte.setDisable(false);
		}
	}
	
	public void refresh(TextField t) {
		 t.setOnMouseClicked(e -> {
				TextField source = (TextField) e.getSource();
				int r = gp.getRowIndex(source);
				
				String b = codab.get(r);
				String m = codam.get(r);
				
				
				String q="select * from brucia where dittab=? and modellob=?;";
				
				PreparedStatement prepStat;
				try {
					
					prepStat = connection.prepareStatement(q);
					prepStat.setString(1, b);
					prepStat.setString(2, m);
					
					rs= prepStat.executeQuery();
					
					rs.next();
					
					if("Monostadio".equals(rs.getString("TIPOBRU"))) {
						monostadio.setSelected(true);
					}
					else if ("Bistadio".equals(rs.getString("TIPOBRU"))){
						bistadio.setSelected(true);
					} else {
						modulante.setSelected(true);
					}
				
					if(rs.getInt("EMULB") == 1) {
						emulsione.setSelected(true);
					} else {
						emulsione.setSelected(false);
					}
					
					ditta.setText(rs.getString("DITTAB"));
					modello.setText(rs.getString("MODELLOB"));
					tipo.setText(rs.getString("TIPOB"));
					potenzaMin.setText(rs.getString("POTMINB"));
					potenzaMax.setText(rs.getString("POTMAXB"));
					combustibile.setText(rs.getString("COMBB"));
					certificazione.setText(rs.getString("ECB"));
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
		 });
	}
	
	public void refreshTabella(String q) {
		try {
			
			rs= stm.executeQuery(q);
			
			gp.getChildren().clear();
			gp.setGridLinesVisible(false);
			gp.setGridLinesVisible(true);
			
			codab.clear();
			codam.clear();
			
			int i=0;
			while (rs.next()) {
				
				codab.add(rs.getString("DITTAB"));
				codam.add(rs.getString("MODELLOB"));
				
				Label t1= new Label(" "+i);
				TextField t2= new TextField(rs.getString("DITTAB"));
				TextField t3= new TextField(rs.getString("MODELLOB"));
				
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
	public void cercaB() {
		CercaBruciatore b = new CercaBruciatore(this);
		try {
			b.start(primaryStage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void cerca(String ditta, String modello) {
		refreshTabella("select * from brucia where dittab like '%"+ditta+"%' and modellob like '%"+modello+"%'");
		tutte.setSelected(false);
		check();
	}
	
	public void conferma() {
		try {
			switch(id) {
			case 1:
				su.b1ditta.setText(rs.getString("DITTAB"));
				su.b1modello.setText(rs.getString("MODELLOB"));
				su.b1matricola.setText(matricola.getText());
				su.b1tipo.setText(rs.getString("TIPOB"));
				su.b1combustibile.setText(rs.getString("COMBB"));
				break;
			
			case 2:
				su.b2ditta.setText(rs.getString("DITTAB"));
				su.b2modello.setText(rs.getString("MODELLOB"));
				su.b2matricola.setText(matricola.getText());
				su.b2tipo.setText(rs.getString("TIPOB"));
				su.b2combustibile.setText(rs.getString("COMBB"));
				break;
				
			case 3:
				su.b3ditta.setText(rs.getString("DITTAB"));
				su.b3modello.setText(rs.getString("MODELLOB"));
				su.b3matricola.setText(matricola.getText());
				su.b3tipo.setText(rs.getString("TIPOB"));
				su.b3combustibile.setText(rs.getString("COMBB"));
				break;
				
			case 4:
				su.b4ditta.setText(rs.getString("DITTAB"));
				su.b4modello.setText(rs.getString("MODELLOB"));
				su.b4matricola.setText(matricola.getText());
				su.b4tipo.setText(rs.getString("TIPOB"));
				su.b4combustibile.setText(rs.getString("COMBB"));
				break;
				
			case 5:
				su.b5ditta.setText(rs.getString("DITTAB"));
				su.b5modello.setText(rs.getString("MODELLOB"));
				su.b5matricola.setText(matricola.getText());
				su.b5tipo.setText(rs.getString("TIPOB"));
				su.b5combustibile.setText(rs.getString("COMBB"));
				break;
				
			case 6:
				su.b6ditta.setText(rs.getString("DITTAB"));
				su.b6modello.setText(rs.getString("MODELLOB"));
				su.b6matricola.setText(matricola.getText());
				su.b6tipo.setText(rs.getString("TIPOB"));
				su.b6combustibile.setText(rs.getString("COMBB"));
				break;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Stage stage = (Stage) cerca.getScene().getWindow();
	    stage.close();
	}
}