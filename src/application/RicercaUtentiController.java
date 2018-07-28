package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RicercaUtentiController implements Initializable{
	
	static Stage primaryStage;
	public Statement stm;
	public ResultSet amministratori;
	public ResultSet caldaie;
	public ResultSet bruciatori;
	public ResultSet installatori;
	
	@FXML
	private MenuItem schedaUtente;

	@FXML
	private MenuItem ricercheUtenti;

	@FXML
	private MenuItem ricercheAnalisi;

	@FXML
	private MenuItem ricercheStorico;
	
	@FXML
	private ComboBox<String> amministratore;
	@FXML
	private ComboBox<String> marcaCaldaia;
	@FXML
	private ComboBox<String> modelloCaldaia; 
	@FXML
	private ComboBox<String> marcaBruciatore;
	@FXML
	private ComboBox<String> modelloBruciatore;
	@FXML
	private ComboBox<String> installatore;
	@FXML
	private ComboBox<String> certConf;
	@FXML
	private CheckBox cAmministratore;
	@FXML
	private CheckBox cCaldaiaDitta;
	@FXML
	private CheckBox cCaldaiaModello;
	@FXML
	private CheckBox cBruciatoreDitta;
	@FXML
	private CheckBox cBruciatoreModello;
	@FXML
	private CheckBox cInstallatore;
	@FXML
	private CheckBox cCertConf;
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		String connectionString="jdbc:mysql://127.0.0.1:3306/affro?user=root&password=";

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

//		Uno statement per ogni result set, se no li chiude
		try {
			
			Connection connection = DriverManager.getConnection(connectionString);
			Statement stm = connection.createStatement();
			amministratori = stm.executeQuery("select * from ammin order by cognomea");
			amministratori.next();
			caldaie= stm.executeQuery("select * from caldaie order by dittac");
			caldaie.next();
			bruciatori = stm.executeQuery("select * from brucia order by dittab");
			bruciatori.next();
			installatori = stm.executeQuery("select * from insta order by dittai");
			installatori.next();
			
			refresh();
			
			cAmministratore.setSelected(true);
			cCaldaiaDitta.setSelected(true);
			cCaldaiaModello.setSelected(true);
			cBruciatoreDitta.setSelected(true);
			cBruciatoreModello.setSelected(true);
			cInstallatore.setSelected(true);
			cCertConf.setSelected(true);
			
			checkAmministratore();
			checkCaldaiaDitta();
			checkCaldaiaModello();
			checkBruciatoreDitta();
			checkBruciatoreModello();
			checkInstallatore();
			checkCertConf();
			

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void schedaU(){
		SchedaUtente su = new SchedaUtente();
		try {
			su.start(primaryStage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void ricercheU(){
		
	}
	
	public void ricercheA(){
		
	}

	public void ricercheS(){
	
	}
	
//	arm non va bene, lo lascia editabile
	public void checkAmministratore() {
		if(cAmministratore.isSelected()) {
			amministratore.setEditable(false);
		}
		else {
			amministratore.arm();
		}
	}
	public void checkCaldaiaDitta() {
		if(cCaldaiaDitta.isSelected()) {
			marcaCaldaia.disarm();
		}
		else {
			marcaCaldaia.arm();
		}
	}
	public void checkCaldaiaModello() {
		if(cCaldaiaModello.isSelected()) {
			modelloCaldaia.disarm();
		}
		else {
			modelloCaldaia.arm();
		}
	}
	public void checkBruciatoreDitta() {
		if(cBruciatoreDitta.isSelected()) {
			marcaBruciatore.disarm();
		}
		else {
			marcaBruciatore.arm();
		}
	}
	public void checkBruciatoreModello() {
		if(cBruciatoreModello.isSelected()) {
			modelloBruciatore.disarm();
		}
		else {
			modelloBruciatore.arm();
		}
	}
	public void checkInstallatore() {
		if(cInstallatore.isSelected()) {
			installatore.disarm();
		}
		else {
			installatore.arm();
		}
	}
	public void checkCertConf() {
		if(cCertConf.isSelected()) {
			certConf.disarm();
		}
		else {
			certConf.arm();
		}
	}
	
	
	public void refresh() {
		try {
			
			while (amministratori.next()) {
				amministratore.getItems().add(amministratori.getString("COGNOMEA"));
			}
			
			
			
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	

}
