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

public class CaldaieController implements Initializable{
	public static Stage primaryStage;
	private Connection connection;
	private Statement stm;
	private ResultSet rs;
	private ToggleGroup g1 = new ToggleGroup();
	private ToggleGroup g2 = new ToggleGroup();
	private ToggleGroup g3 = new ToggleGroup();
	private Vector<String> codac;
	private Vector<String> codam;
	private String dittaM;
	private String modelloM;
	

	@FXML
	private ScrollPane sp;
	@FXML
	private GridPane gp;
	@FXML
	private RadioButton murale;
	@FXML
	private RadioButton basamento;
	@FXML
	private RadioButton cameraStagna;
	@FXML
	private RadioButton cameraAperta;
	@FXML
	private RadioButton istantanea;
	@FXML
	private RadioButton conBollitore;
	@FXML
	private RadioButton soloRiscaldamento;
	@FXML
	private TextField ditta;
	@FXML
	private TextField modello;
	@FXML
	private TextField potenzaFocolare;
	@FXML
	private TextField potenzaUtile;
	@FXML
	private TextField rendimentoUtile;
	@FXML
	private TextField combustibile;
	@FXML
	private TextField cert;
	@FXML
	private CheckBox tutte;
	@FXML
	private ComboBox<String> ditte;
	@FXML
	private Label nCaldaie;
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
		
		murale.setToggleGroup(g1);
		basamento.setToggleGroup(g1);
		
		cameraAperta.setToggleGroup(g2);
		cameraStagna.setToggleGroup(g2);
		
		istantanea.setToggleGroup(g3);
		conBollitore.setToggleGroup(g3);
		soloRiscaldamento.setToggleGroup(g3);
		
		murale.setMouseTransparent(true);
		basamento.setMouseTransparent(true);
		
		cameraAperta.setMouseTransparent(true);
		cameraStagna.setMouseTransparent(true);
		
		istantanea.setMouseTransparent(true);
		conBollitore.setMouseTransparent(true);
		soloRiscaldamento.setMouseTransparent(true);
		
		codac= new Vector<String>();
		codam= new Vector<String>();
		
		try {
			rs=stm.executeQuery("select distinct dittac from caldaie order by dittac");
			while (rs.next()) {
				ditte.getItems().add(rs.getString("DITTAC"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		refreshTabella("select * from caldaie order by dittac");
		
		check();
	}
	
	
	public void dittaRefresh() {
		try {
			rs = stm.executeQuery("select count(*) as count from caldaie where dittac='"+ditte.getValue()+"' ");
			rs.next();
			nCaldaie.setText(rs.getString("count"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		refreshTabella("select * from caldaie where dittac='"+ditte.getValue()+"'");
	}
	
	public void check() {
		if(tutte.isSelected()) {
			ditte.setDisable(true);
			refreshTabella("select * from caldaie order by dittac");
			
			try {
				rs = stm.executeQuery("select count(*) as count from caldaie ");
				rs.next();
				nCaldaie.setText(rs.getString("count"));
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
				int r=gp.getRowIndex(source);
				
				String c=codac.get(r);
				String m=codam.get(r);
				
				
				String q="select * from caldaie where dittac=? and modelloc=?;";
				
				PreparedStatement prepStat;
				try {
					
					prepStat = connection.prepareStatement(q);
					prepStat.setString(1, c);
					prepStat.setString(2, m);
					
					rs= prepStat.executeQuery();
					
					rs.next();
					
					if("Basamento".equals(rs.getString("MBC"))) {
						basamento.setSelected(true);
					}
					else {
						murale.setSelected(true);
					}
					
					if("Camera Aperta".equals(rs.getString("CACSC"))) {
						cameraAperta.setSelected(true);
					}
					else {
						cameraStagna.setSelected(true);
					}
					
					if("Solo Riscaldamento".equals(rs.getString("BISRC"))) {
						soloRiscaldamento.setSelected(true);
					}
					else if("Con Bollitore".equals(rs.getString("BISRC"))) {
						conBollitore.setSelected(true);
					}
					else {
						istantanea.setSelected(true);
					}
					
					ditta.setText(rs.getString("DITTAC"));
					modello.setText(rs.getString("MODELLOC"));
					
					potenzaFocolare.setText(rs.getString("PNFC"));
					potenzaUtile.setText(rs.getString("PNUC"));
					rendimentoUtile.setText(rs.getString("RUC"));
					combustibile.setText(rs.getString("COMBC"));
					cert.setText(rs.getString("ECC"));
					
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
			
			codac.clear();
			codam.clear();
			
			int i=0;
			while (rs.next()) {
//				coda.add(Integer.parseInt(rs.getString("CODICEU")));
				
				codac.add(rs.getString("DITTAC"));
				codam.add(rs.getString("MODELLOC"));
				
				Label t1= new Label(" "+i);
				TextField t2= new TextField(rs.getString("DITTAC"));
				TextField t3= new TextField(rs.getString("MODELLOC"));
				
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
	
	public void modifica() {
		
		dittaM = ditta.getText();
		modelloM = modello.getText();
		
		conferma.setVisible(true);
		annulla.setVisible(true);
		
		modifica.setDisable(true);
		cerca.setDisable(true);
		aggiungi.setDisable(true);
		elimina.setDisable(true);
		
		sp.setMouseTransparent(true);
		
		murale.setMouseTransparent(false);
		basamento.setMouseTransparent(false);
		cameraAperta.setMouseTransparent(false);
		cameraStagna.setMouseTransparent(false);
		istantanea.setMouseTransparent(false);
		conBollitore.setMouseTransparent(false);
		soloRiscaldamento.setMouseTransparent(false);
		
		ditta.setEditable(true);
		modello.setEditable(true);
		potenzaFocolare.setEditable(true);
		potenzaUtile.setEditable(true);
		rendimentoUtile.setEditable(true);
		combustibile.setEditable(true);
		cert.setEditable(true);
	}
	
	public void confermaModifica() {
		try {
			String q = "update caldaie set dittac='"+ditta.getText()+"', modelloc='"+modello.getText()+"', pnfc='"+potenzaFocolare.getText()+"', pnuc='"+potenzaUtile.getText()+"', ruc='"+rendimentoUtile.getText()+"', combc='"+combustibile.getText()+"', ecc='"+cert.getText()+"', ";
			if(murale.isSelected()) {
				q=q.concat(" mbc='Murale', ");
			}
			else {
				q=q.concat(" mbc='Basamento', ");
			}
			
			if(cameraAperta.isSelected()) {
				q=q.concat(" cacsc='Camera Aperta', ");
			}
			else {
				q=q.concat(" cacsc='Camera Stagna', ");
			}
			
			if(soloRiscaldamento.isSelected()) {
				q=q.concat(" bisrc='Solo Riscaldamento' ");
			}
			else if(conBollitore.isSelected()) {
				q=q.concat(" bisrc='Con Bollitore' ");
			}
			else {
				q=q.concat(" bisrc='Istantanea' ");
			}
			
			
			stm.execute(q.concat(" where dittac='"+dittaM+"' and modelloc='"+modelloM+"'"));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(tutte.isSelected()) {
			refreshTabella("select * from caldaie order by dittac");
			
			try {
				rs = stm.executeQuery("select count(*) as count from caldaie ");
				rs.next();
				nCaldaie.setText(rs.getString("count"));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		else {
			
			try {
				rs = stm.executeQuery("select count(*) as count from caldaie where dittac='"+ditte.getValue()+"'");
				rs.next();
				nCaldaie.setText(rs.getString("count"));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			refreshTabella("select * from caldaie where dittac='"+ditte.getValue()+"'");
		}
		
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
		
		murale.setMouseTransparent(true);
		basamento.setMouseTransparent(true);
		cameraAperta.setMouseTransparent(true);
		cameraStagna.setMouseTransparent(true);
		istantanea.setMouseTransparent(true);
		conBollitore.setMouseTransparent(true);
		soloRiscaldamento.setMouseTransparent(true);
		
		ditta.setEditable(false);
		modello.setEditable(false);
		potenzaFocolare.setEditable(false);
		potenzaUtile.setEditable(false);
		rendimentoUtile.setEditable(false);
		combustibile.setEditable(false);
		cert.setEditable(false);
	}
	
	public void cercaW(){
		CercaCaldaie c = new CercaCaldaie(this);
		try {
			c.start(primaryStage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void cerca( String ditta, String modello) {
		refreshTabella("select * from caldaie where dittac like '%"+ditta+"%' and modelloc like '%"+modello+"%'");
	}
	
	public void elimina() {
		try {
			stm.execute("delete from caldaie where dittac='"+ditta.getText()+"' and modelloc='"+modello.getText()+"'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		refreshTabella("select * from caldaie order by dittac");
	}
	
public void aggiungi() {
		
		dittaM = ditta.getText();
		modelloM = modello.getText();
		
		confermaAggiunta.setVisible(true);
		annullaAggiunta.setVisible(true);
		
		modifica.setDisable(true);
		cerca.setDisable(true);
		aggiungi.setDisable(true);
		elimina.setDisable(true);
		
		sp.setMouseTransparent(true);
		
		murale.setMouseTransparent(false);
		basamento.setMouseTransparent(false);
		cameraAperta.setMouseTransparent(false);
		cameraStagna.setMouseTransparent(false);
		istantanea.setMouseTransparent(false);
		conBollitore.setMouseTransparent(false);
		soloRiscaldamento.setMouseTransparent(false);
		
		ditta.setEditable(true);
		modello.setEditable(true);
		potenzaFocolare.setEditable(true);
		potenzaUtile.setEditable(true);
		rendimentoUtile.setEditable(true);
		combustibile.setEditable(true);
		cert.setEditable(true);
		
		murale.setSelected(false);
		basamento.setSelected(false);
		cameraAperta.setSelected(false);
		cameraStagna.setSelected(false);
		istantanea.setSelected(false);
		conBollitore.setSelected(false);
		soloRiscaldamento.setSelected(false);
		
		ditta.setText("");
		modello.setText("");
		potenzaFocolare.setText("");
		potenzaUtile.setText("");
		rendimentoUtile.setText("");
		combustibile.setText("");
		cert.setText("");
	}
	
	public void confermaAggiungi(){
		try {
			String q = "insert into caldaie (dittac, modelloc, pnfc, pnuc, ruc, combc, ecc, mbc, cacsc, bisrc) values ('"+ditta.getText()+"', '"+modello.getText()+"', '"+modello.getText()+"', '"+modello.getText()+"', '"+potenzaFocolare.getText()+"', '"+potenzaUtile.getText()+"', '"+rendimentoUtile.getText()+"', '"+combustibile.getText()+"', '"+cert.getText()+"', ";
			if(murale.isSelected()) {
				q=q.concat("'Murale', ");
			}
			else {
				q=q.concat("'Basamento', ");
			}
			
			if(cameraAperta.isSelected()) {
				q=q.concat("'Camera Aperta', ");
			}
			else {
				q=q.concat("'Camera Stagna', ");
			}
			
			if(soloRiscaldamento.isSelected()) {
				q=q.concat("'Solo Riscaldamento' )");
			}
			else if(conBollitore.isSelected()) {
				q=q.concat("'Con Bollitore' )");
			}
			else {
				q=q.concat("'Istantanea' ) ");
			}
			
			
			stm.execute(q);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(tutte.isSelected()) {
			refreshTabella("select * from caldaie order by dittac");
			
			try {
				rs = stm.executeQuery("select count(*) as count from caldaie ");
				rs.next();
				nCaldaie.setText(rs.getString("count"));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		else {
			
			try {
				rs = stm.executeQuery("select count(*) as count from caldaie where dittac='"+ditte.getValue()+"'");
				rs.next();
				nCaldaie.setText(rs.getString("count"));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			refreshTabella("select * from caldaie where dittac='"+ditte.getValue()+"'");
		}
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
		
		murale.setMouseTransparent(true);
		basamento.setMouseTransparent(true);
		cameraAperta.setMouseTransparent(true);
		cameraStagna.setMouseTransparent(true);
		istantanea.setMouseTransparent(true);
		conBollitore.setMouseTransparent(true);
		soloRiscaldamento.setMouseTransparent(true);
		
		ditta.setEditable(false);
		modello.setEditable(false);
		potenzaFocolare.setEditable(false);
		potenzaUtile.setEditable(false);
		rendimentoUtile.setEditable(false);
		combustibile.setEditable(false);
		cert.setEditable(false);
	}
}


