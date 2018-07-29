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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class RicercaUtentiController implements Initializable{
	
	static Stage primaryStage;
	public Statement stm;
	public ResultSet amministratori;
	public ResultSet caldaie;
	public ResultSet bruciatori;
	public ResultSet installatori;
	final ToggleGroup orologio = new ToggleGroup();
	final ToggleGroup edificio = new ToggleGroup();
	Boolean e;
	
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
	private ComboBox<String> nAnalisi;
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
	@FXML
	private  TextField nome;
	@FXML
	private  TextField cognome;
	@FXML
	private  TextField viaU;
	@FXML
	private  TextField numeroU;
	@FXML
	private  TextField localita;
	@FXML
	private  TextField cap;
	@FXML
	private  TextField comuneU;
	@FXML
	private  TextField provU;
	@FXML
	private  TextField telefonoU;
	@FXML
	private  TextField cellulareU;
	@FXML
	private  TextField cfU;
	@FXML
	private TextField codManu;
	@FXML
	private  CheckBox circuiti;
	@FXML
	private  CheckBox termoregolato;
	@FXML
	private  CheckBox contacalorie;
	@FXML
	private  CheckBox superiore35;
	@FXML
	private  CheckBox superiore116;
	@FXML
	private  CheckBox superiore350;
	@FXML
	private  RadioButton automatico;
	@FXML
	private  RadioButton manuale;
	@FXML
	private RadioButton condTerzoResponsabile;
	@FXML
	private RadioButton condConAmministratore;
	@FXML
	private RadioButton privato;
	@FXML
	private RadioButton altroDitta;
	@FXML
	private RadioButton altroTerzoResponsabile;
	@FXML
	private DatePicker puliziacb;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		String connectionString="jdbc:mysql://127.0.0.1:3306/affro?user=root&password=";
		
		automatico.setToggleGroup(orologio);
		manuale.setToggleGroup(orologio);
		
		condTerzoResponsabile.setToggleGroup(edificio);
		condConAmministratore.setToggleGroup(edificio);
		privato.setToggleGroup(edificio);
		altroDitta.setToggleGroup(edificio);
		altroTerzoResponsabile.setToggleGroup(edificio);

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try {
			
			Connection connection = DriverManager.getConnection(connectionString);
			stm = connection.createStatement();
			amministratori = stm.executeQuery("select * from ammin order by cognomea");

			while (amministratori.next()) {
				amministratore.getItems().add(amministratori.getString("COGNOMEA"));
			}
			
			caldaie= stm.executeQuery("select * from caldaie order by dittac");

			while (caldaie.next()) {
				marcaCaldaia.getItems().add(caldaie.getString("DITTAC"));
			}
			
			bruciatori = stm.executeQuery("select * from brucia order by dittab");

			while (bruciatori.next()) {
				marcaBruciatore.getItems().add(bruciatori.getString("DITTAB"));
			}
			
			installatori = stm.executeQuery("select * from insta order by dittai");

			while (installatori.next()) {
				installatore.getItems().add(installatori.getString("DITTAI"));
			}
			
			certConf.getItems().addAll("SI","NO");
			nAnalisi.getItems().addAll("Una","Due");
			
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
	
	public void checkAmministratore() {
		if(cAmministratore.isSelected()) {
			amministratore.setDisable(true);
		}
		else {
			amministratore.setDisable(false);
		}
	}
	public void checkCaldaiaDitta() {
		if(cCaldaiaDitta.isSelected()) {
			marcaCaldaia.setDisable(true);
		}
		else {
			marcaCaldaia.setDisable(false);
		}
	}
	public void checkCaldaiaModello() {
		if(cCaldaiaModello.isSelected()) {
			modelloCaldaia.setDisable(true);
		}
		else {
			modelloCaldaia.setDisable(false);
		}
	}
	public void checkBruciatoreDitta() {
		if(cBruciatoreDitta.isSelected()) {
			marcaBruciatore.setDisable(true);
		}
		else {
			marcaBruciatore.setDisable(false);
		}
	}
	public void checkBruciatoreModello() {
		if(cBruciatoreModello.isSelected()) {
			modelloBruciatore.setDisable(true);
		}
		else {
			modelloBruciatore.setDisable(false);
		}
	}
	public void checkInstallatore() {
		if(cInstallatore.isSelected()) {
			installatore.setDisable(true);
		}
		else {
			installatore.setDisable(false);
		}
	}
	public void checkCertConf() {
		if(cCertConf.isSelected()) {
			certConf.setDisable(true);
		}
		else {
			certConf.setDisable(false);
		}
	}
	
	public void caldaiaRefresh() {
		try {	
			
		caldaie= stm.executeQuery("select * from caldaie where dittac='"+marcaCaldaia.getValue()+"' order by modelloc");

		while (caldaie.next()) {
			modelloCaldaia.getItems().add(caldaie.getString("MODELLOC"));
		}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void bruciatoreRefresh() {
		try {	
			
		bruciatori = stm.executeQuery("select * from brucia where dittab='"+marcaBruciatore.getValue()+"' order by modellob");

		while (bruciatori.next()) {
			modelloBruciatore.getItems().add(bruciatori.getString("MODELLOB"));
		}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String andCheck(Boolean e) {
		if(e)
			return " and";
		e=true;
		return "";
	}
	public String orCheck(Boolean e) {
		if(e)
			return " or";
		e=true;
		return "";
	}
	
	public void conferma() {
		e = false;
		String q = "select * from utenti where";
		
		if(!(cognome.getText()).equals("")) {
			q.concat(andCheck(e));
			q.concat(" cognomeu='"+cognome.getText()+"'");
		}
		if(!(nome.getText()).equals("")) {
			q.concat(andCheck(e));
			q.concat(" nomeu='"+nome.getText()+"'");
		}
		if(!(viaU.getText()).equals("")) {
			q.concat(andCheck(e));
			q.concat(" indirizzoU='"+viaU.getText()+"'");
		}
		if(!(numeroU.getText()).equals("")) {
			q.concat(andCheck(e));
			q.concat(" numeroU='"+numeroU.getText()+"'");
		}
		if(!(localita.getText()).equals("")) {
			q.concat(andCheck(e));
			q.concat(" localitau='"+localita.getText()+"'");
		}
		if(!(cap.getText()).equals("")) {
			q.concat(andCheck(e));
			q.concat(" capu='"+cap.getText()+"'");
		}
		if(!(comuneU.getText()).equals("")) {
			q.concat(andCheck(e));
			q.concat(" comuneU='"+comuneU.getText()+"'");
		}
		if(!(provU.getText()).equals("")) {
			q.concat(andCheck(e));
			q.concat(" provinciaU='"+provU.getText()+"'");
		}
		if(!(telefonoU.getText()).equals("")) {
			q.concat(andCheck(e));
			q.concat(" telefonoU='"+telefonoU.getText()+"'");
		}
		if(!(cellulareU.getText()).equals("")) {
			q.concat(andCheck(e));
			q.concat(" cellulareU='"+cellulareU.getText()+"'");
		}
		if(!(cfU.getText()).equals("")) {
			q.concat(andCheck(e));
			q.concat(" cfivaU='"+cfU.getText()+"'");
		}
		
		
		if(condTerzoResponsabile.isSelected()) {
			q.concat(andCheck(e));
			q.concat(" imptipo='Condominio con terzo responsabile'");
		}
		if(condConAmministratore.isSelected()) {
			q.concat(andCheck(e));
			q.concat(" imptipo='Condominio con amministratore'");
		}
		if(privato.isSelected()) {
			q.concat(andCheck(e));
			q.concat(" imptipo='Privato'");
		}
		if(altroDitta.isSelected()) {
			q.concat(andCheck(e));
			q.concat(" imptipo='Altro (Ditta)'");
		}
		if(altroTerzoResponsabile.isSelected()) {
			q.concat(andCheck(e));
			q.concat(" imptipo='Altro (con terzo responsabile)'");
		}
		
		if(automatico.isSelected()) {
			q.concat(andCheck(e));
			q.concat(" orologio='Automatico'");
		}
		if(manuale.isSelected()) {
			q.concat(andCheck(e));
			q.concat(" orologio='Manuale'");
		}
		
		if(circuiti.isSelected()) {
			q.concat(andCheck(e));
			q.concat(" impcirc='Circuiti'");
		}
		if(termoregolato.isSelected()) {
			q.concat(andCheck(e));
			q.concat(" impterm='Termoregolato'");
		}
		if(contacalorie.isSelected()) {
			q.concat(andCheck(e));
			q.concat(" impcont='Contacalorie'");
		}
		if(superiore35.isSelected()) {
			q.concat(andCheck(e));
			q.concat(" impsu35='Superiore 35 KW'");
		}
		if(superiore116.isSelected()) {
			q.concat(andCheck(e));
			q.concat(" impsu116='Superiore 116 KW'");
		}
		if(superiore350.isSelected()) {
			q.concat(andCheck(e));
			q.concat(" impsupe='Superiore 350 KW'");
		}
		
		if(!cAmministratore.isSelected()) {
			q.concat(andCheck(e));
			q.concat(" cognomea='"+amministratore.getValue()+"'");
		}
		if(!cCaldaiaModello.isSelected() && !cCaldaiaDitta.isSelected()) {
			Boolean or=false;
			q.concat(andCheck(e));
			q.concat(" (");
			for (int i=1; i<=6; i++) {
				q.concat(orCheck(or));
				q.concat(" modelloc"+i+"='"+modelloCaldaia.getValue()+"'");
			}
			q.concat(" )");
		}
		else if(!cCaldaiaDitta.isSelected()) {
			Boolean or=false;
			q.concat(andCheck(e));
			q.concat(" (");
			for (int i=1; i<=6; i++) {
				q.concat(orCheck(or));
				q.concat(" dittac"+i+"='"+marcaCaldaia.getValue()+"'");
			}
			q.concat(" )");
		}
		if(!cBruciatoreModello.isSelected() && !cBruciatoreDitta.isSelected()) {
			Boolean or=false;
			q.concat(andCheck(e));
			q.concat(" (");
			for (int i=1; i<=6; i++) {
				q.concat(orCheck(or));
				q.concat(" modellob"+i+"='"+modelloBruciatore.getValue()+"'");
			}
			q.concat(" )");
		}
		else if(!cBruciatoreDitta.isSelected()) {
			Boolean or=false;
			q.concat(andCheck(e));
			q.concat(" (");
			for (int i=1; i<=6; i++) {
				q.concat(orCheck(or));
				q.concat(" dittab"+i+"='"+marcaBruciatore.getValue()+"'");
			}
			q.concat(" )");
		}
		if(!cInstallatore.isSelected()) {
			q.concat(andCheck(e));
			q.concat(" dittai='"+installatore.getValue()+"'");
		}
		if(!cCertConf.isSelected()) {
			q.concat(andCheck(e));
			q.concat(" certconfv='"+certConf.getValue()+"'");
		}
		
		if(!(codManu.getText()).equals("")) {
			q.concat(andCheck(e));
			q.concat(" codmanu='"+codManu.getText()+"'");
		}
		if(!nAnalisi.getSelectionModel().isEmpty()) {
			q.concat(andCheck(e));
			q.concat(" n_analisi='"+nAnalisi.getValue()+"'");
		}
		
		System.out.println(puliziacb.getValue());
		
	}

	

}
