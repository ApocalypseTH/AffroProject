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
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class TecniciController implements Initializable{
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
	private TextField tecnico;
	@FXML
	private Button conferma;
	@FXML
	private Button annulla;
	@FXML
	private Button modifica;
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
		
		codac= new Vector<String>();
		
		refreshTabella("select * from tecnici order by nomet");
		
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
				
				codac.add(rs.getString("NOMET"));
				
				Label t1= new Label(" "+(i+1));
				TextField t2= new TextField(rs.getString("NOMET"));
				
				t2.setEditable(false);
				
				refresh(t2);
							
				gp.addRow(i, t1, t2);
				i++;
			}
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
				
				
				String q="select * from tecnici where nomet=?;";
				
				PreparedStatement prepStat;
				try {
					
					prepStat = connection.prepareStatement(q);
					prepStat.setString(1, c);
					
					rs= prepStat.executeQuery();
					
					rs.next();
					
					tecnico.setText(rs.getString("NOMET"));
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
		 });
	}
	
	public void modifica() {
		
		ammin = tecnico.getText();
		
		conferma.setVisible(true);
		annulla.setVisible(true);
		
		modifica.setDisable(true);
		aggiungi.setDisable(true);
		elimina.setDisable(true);
		
		sp.setMouseTransparent(true);
		
		tecnico.setEditable(true);
	}
	
	public void confermaModifica() {
		try {
			String q = "update tecnici set nomet='"+tecnico.getText()+"'";
			
			
			stm.execute(q.concat(" where nomet='"+ammin+"'"));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		refreshTabella("select * from tecnici order by nomet");
		
		annullaModifica();
	}
	public void annullaModifica() {
		
		conferma.setVisible(false);
		annulla.setVisible(false);
		
		modifica.setDisable(false);
		aggiungi.setDisable(false);
		elimina.setDisable(false);
		
		sp.setMouseTransparent(false);
		
		tecnico.setEditable(false);

	}
	
	public void elimina() {
		try {
			stm.execute("delete from tecnici where nomet='"+tecnico.getText()+"'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		refreshTabella("select * from tecnici order by nomet");
	}
	
public void aggiungi() {
		
		ammin = tecnico.getText();
		
		confermaAggiunta.setVisible(true);
		annullaAggiunta.setVisible(true);
		
		modifica.setDisable(true);
		aggiungi.setDisable(true);
		elimina.setDisable(true);
		
		sp.setMouseTransparent(true);
		
		tecnico.setEditable(true);
	
		tecnico.setText("");
	}
	
	public void confermaAggiungi(){
		try {
			String q = "insert into tecnici (nomet) values ('"+tecnico.getText()+"')";
			stm.execute(q);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		refreshTabella("select * from tecnici order by nomet");

		annullaAggiungi();
	}
	public void annullaAggiungi(){
		confermaAggiunta.setVisible(false);
		annullaAggiunta.setVisible(false);
		
		modifica.setDisable(false);
		aggiungi.setDisable(false);
		elimina.setDisable(false);
		
		sp.setMouseTransparent(false);
		
		tecnico.setEditable(false);
	}
}


