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
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class RicercaUtentiController implements Initializable{
	
	static Stage primaryStage;
	static boolean storico = false;
	private Connection connection;
	public Statement stm;
	public ResultSet amministratori;
	public ResultSet caldaie;
	public ResultSet bruciatori;
	public ResultSet installatori;
	final ToggleGroup orologio = new ToggleGroup();
	final ToggleGroup edificio = new ToggleGroup();
	final ToggleGroup pulizia = new ToggleGroup();
	final ToggleGroup analisi = new ToggleGroup();
	final ToggleGroup bollino = new ToggleGroup();
	final ToggleGroup imp = new ToggleGroup();
	final ToggleGroup ora = new ToggleGroup();
	final ToggleGroup ordine = new ToggleGroup();
	Boolean e;
	Boolean or;
	
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
	private DatePicker puliziacbData;
	@FXML
	private RadioButton puliziacbEseguita;
	@FXML
	private RadioButton puliziacbNonEseguita;
	@FXML
	private RadioButton analisiEseguita;
	@FXML
	private RadioButton analisiNonEseguita;
	@FXML
	private RadioButton bollinoEseguito;
	@FXML
	private RadioButton bollinoNonEseguito;
	@FXML
	private RadioButton impAcceso;
	@FXML
	private RadioButton impSpento;
	@FXML
	private RadioButton legale;
	@FXML
	private RadioButton solare;
	@FXML
	private DatePicker messaInFunzione;
	@FXML
	private RadioButton ordineCN;
	@FXML
	private RadioButton ordineCI;
	@FXML
	private TextField analisiAnno;
	@FXML
	private TextField bollinoAnno;
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub		
		automatico.setToggleGroup(orologio);
		manuale.setToggleGroup(orologio);
		
		condTerzoResponsabile.setToggleGroup(edificio);
		condConAmministratore.setToggleGroup(edificio);
		privato.setToggleGroup(edificio);
		altroDitta.setToggleGroup(edificio);
		altroTerzoResponsabile.setToggleGroup(edificio);
		
		puliziacbEseguita.setToggleGroup(pulizia);
		puliziacbNonEseguita.setToggleGroup(pulizia);
		analisiEseguita.setToggleGroup(analisi);
		analisiNonEseguita.setToggleGroup(analisi);
		bollinoEseguito.setToggleGroup(bollino);
		bollinoNonEseguito.setToggleGroup(bollino);
		impAcceso.setToggleGroup(imp);
		impSpento.setToggleGroup(imp);
		legale.setToggleGroup(ora);
		solare.setToggleGroup(ora);
		ordineCI.setToggleGroup(ordine);
		ordineCN.setToggleGroup(ordine);
		
//		String connectionString="jdbc:mysql://127.0.0.1:3306/affro?user=root&password=";
//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}

		try {
			
//			Connection connection = DriverManager.getConnection(connectionString);
			ConnDB conn = new ConnDB();
			connection = conn.getConnection();
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
	
	public String andCheck() {
		if(e)
			return " and";
		e=true;
		return "";
	}
	public String orCheck() {
		if(or)
			return " or";
		or=true;
		return "";
	}
	
	public void conferma() {
		e = false;
		String q = "select distinct u.codiceu, u.nomeu, u.cognomeu, u.indirizzou, u.comuneu, u.telefonou, u.imptipo from utenti as u left join ricint as r on u.codiceu=r.codiceu left join analisi as a on u.codiceu=a.codiceu where";
		
		if(!(cognome.getText()).equals("")) {
			q=q.concat(andCheck());
			q=q.concat(" cognomeu like '%"+cognome.getText()+"%'");
		}
		if(!(nome.getText()).equals("")) {
			q=q.concat(andCheck());
			q=q.concat(" nomeu like '%"+nome.getText()+"%'");
		}
		if(!(viaU.getText()).equals("")) {
			q=q.concat(andCheck());
			q=q.concat(" indirizzoU like '%"+viaU.getText()+"%'");
		}
		if(!(numeroU.getText()).equals("")) {
			q=q.concat(andCheck());
			q=q.concat(" numeroU like '%"+numeroU.getText()+"%'");
		}
		if(!(localita.getText()).equals("")) {
			q=q.concat(andCheck());
			q=q.concat(" localitau like '%"+localita.getText()+"%'");
		}
		if(!(cap.getText()).equals("")) {
			q=q.concat(andCheck());
			q=q.concat(" capu like '%"+cap.getText()+"%'");
		}
		if(!(comuneU.getText()).equals("")) {
			q=q.concat(andCheck());
			q=q.concat(" comuneU like '%"+comuneU.getText()+"%'");
		}
		if(!(provU.getText()).equals("")) {
			q=q.concat(andCheck());
			q=q.concat(" provinciaU like '%"+provU.getText()+"%'");
		}
		if(!(telefonoU.getText()).equals("")) {
			q=q.concat(andCheck());
			q=q.concat(" telefonoU like '%"+telefonoU.getText()+"%'");
		}
		if(!(cellulareU.getText()).equals("")) {
			q=q.concat(andCheck());
			q=q.concat(" cellulareU like '%"+cellulareU.getText()+"%'");
		}
		if(!(cfU.getText()).equals("")) {
			q=q.concat(andCheck());
			q=q.concat(" cfivaU like '%"+cfU.getText()+"%'");
		}
		
		
		if(condTerzoResponsabile.isSelected()) {
			q=q.concat(andCheck());
			q=q.concat(" imptipo='Condominio con terzo responsabile'");
		}
		if(condConAmministratore.isSelected()) {
			q=q.concat(andCheck());
			q=q.concat(" imptipo='Condominio con amministratore'");
		}
		if(privato.isSelected()) {
			q=q.concat(andCheck());
			q=q.concat(" imptipo='Privato'");
		}
		if(altroDitta.isSelected()) {
			q=q.concat(andCheck());
			q=q.concat(" imptipo='Altro (Ditta)'");
		}
		if(altroTerzoResponsabile.isSelected()) {
			q=q.concat(andCheck());
			q=q.concat(" imptipo='Altro (con terzo responsabile)'");
		}
		
		if(automatico.isSelected()) {
			q=q.concat(andCheck());
			q=q.concat(" orologio='Automatico'");
		}
		if(manuale.isSelected()) {
			q=q.concat(andCheck());
			q=q.concat(" orologio='Manuale'");
		}
		
		if(circuiti.isSelected()) {
			q=q.concat(andCheck());
			q=q.concat(" impcirc='Circuiti'");
		}
		if(termoregolato.isSelected()) {
			q=q.concat(andCheck());
			q=q.concat(" impterm='Termoregolato'");
		}
		if(contacalorie.isSelected()) {
			q=q.concat(andCheck());
			q=q.concat(" impcont='Contacalorie'");
		}
		if(superiore35.isSelected()) {
			q=q.concat(andCheck());
			q=q.concat(" impsu35='Superiore 35 KW'");
		}
		if(superiore116.isSelected()) {
			q=q.concat(andCheck());
			q=q.concat(" impsu116='Superiore 116 KW'");
		}
		if(superiore350.isSelected()) {
			q=q.concat(andCheck());
			q=q.concat(" impsupe='Superiore 350 KW'");
		}
		
		if(!cAmministratore.isSelected()) {
			q=q.concat(andCheck());
			q=q.concat(" cognomea='"+amministratore.getValue()+"'");
		}
		if(!cCaldaiaModello.isSelected() && !cCaldaiaDitta.isSelected()) {
			or=false;
			q=q.concat(andCheck());
			q=q.concat(" (");
			for (int i=1; i<=6; i++) {
				q=q.concat(orCheck());
				q=q.concat(" modelloc"+i+"='"+modelloCaldaia.getValue()+"'");
			}
			q=q.concat(" )");
		}
		else if(!cCaldaiaDitta.isSelected()) {
			or=false;
			q=q.concat(andCheck());
			q=q.concat(" (");
			for (int i=1; i<=6; i++) {
				q=q.concat(orCheck());
				q=q.concat(" dittac"+i+"='"+marcaCaldaia.getValue()+"'");
			}
			q.concat(" )");
		}
		if(!cBruciatoreModello.isSelected() && !cBruciatoreDitta.isSelected()) {
			or=false;
			q=q.concat(andCheck());
			q=q.concat(" (");
			for (int i=1; i<=6; i++) {
				q=q.concat(orCheck());
				q=q.concat(" modellob"+i+"='"+modelloBruciatore.getValue()+"'");
			}
			q=q.concat(" )");
		}
		else if(!cBruciatoreDitta.isSelected()) {
			or=false;
			q=q.concat(andCheck());
			q=q.concat(" (");
			for (int i=1; i<=6; i++) {
				q=q.concat(orCheck());
				q=q.concat(" dittab"+i+"='"+marcaBruciatore.getValue()+"'");
			}
			q=q.concat(" )");
		}
		if(!cInstallatore.isSelected()) {
			q=q.concat(andCheck());
			q=q.concat(" dittai='"+installatore.getValue()+"'");
		}
		if(!cCertConf.isSelected()) {
			q=q.concat(andCheck());
			q=q.concat(" certconfv='"+certConf.getValue()+"'");
		}
		
		if(!(codManu.getText()).equals("")) {
			q=q.concat(andCheck());
			q=q.concat(" codmanu='"+codManu.getText()+"'");
		}
		if(!nAnalisi.getSelectionModel().isEmpty()) {
			q=q.concat(andCheck());
			q=q.concat(" n_analisi='"+nAnalisi.getValue()+"'");
		}
		
		System.out.println(puliziacbData.getValue());
		
		if(puliziacbData.getValue() != null) {
			if (puliziacbEseguita.isSelected()) {
				q=q.concat(andCheck());
				q=q.concat(" ( motivoch like '%pulizie%' and datach='"+puliziacbData.getValue()+"' )");
			}
			else if(puliziacbNonEseguita.isSelected()) {
				q=q.concat(andCheck());
				q=q.concat(" ( motivoch like '%pulizie%' and datach<>'"+puliziacbData.getValue()+"' )");
			}
		}
		if( (analisiAnno.getText()).length() == 4 ) {
			if (analisiEseguita.isSelected()) {
				q=q.concat(andCheck());
				q=q.concat(" a.data between '"+analisiAnno.getText()+"-01-01' and '"+analisiAnno.getText()+"-12-31'");
			}
			else if(analisiNonEseguita.isSelected()) {
				q=q.concat(andCheck());
				q=q.concat(" a.data not between '"+analisiAnno.getText()+"-01-01' and '"+analisiAnno.getText()+"-12-31'");
			}
		}
		if( (bollinoAnno.getText()).length() == 4 ) {
			if (bollinoEseguito.isSelected()) {
				q=q.concat(andCheck());
				q=q.concat(" bollino = '"+bollinoAnno.getText()+"'");
			}
			else if(bollinoNonEseguito.isSelected()) {
				q=q.concat(andCheck());
				q=q.concat(" bollino <> '"+bollinoAnno.getText()+"'");
			}
		}
		if(impAcceso.isSelected()) {
			q=q.concat(andCheck());
			q=q.concat(" impacceso='SI'");
		}
		else if(impSpento.isSelected()) {
			q=q.concat(andCheck());
			q=q.concat(" impacceso='NO'");
		}
		if(legale.isSelected()) {
			q=q.concat(andCheck());
			q=q.concat(" ora='Legale'");
		}
		else if(solare.isSelected()) {
			q=q.concat(andCheck());
			q=q.concat(" ora='Solare'");
		}
		if(messaInFunzione.getValue() != null) {
			or=false;
			q=q.concat(andCheck());
			q=q.concat(" (");
			for (int i=1; i<=6; i++) {
				q=q.concat(orCheck());
				q=q.concat(" mfc"+i+"='"+messaInFunzione.getValue()+"'");
			}
			q=q.concat(" )");
		}
		
//		raggruppato per tutti i campi necessari
//		q=q.concat(" group by ");
		
		if(!e) {
			q="select * from utenti as u";
		}
		
		if(ordineCN.isSelected()) {
			q=q.concat(" order by u.cognomeu, u.nomeu");
		}
		else if(ordineCI.isSelected()) {
			q=q.concat(" order by u.comuneu, u.indirizzou");
		}
		
		try {
			System.out.println(q);
			
			stm.execute(q);
			if(storico) {
				StoricoPerUtente su = new StoricoPerUtente(q);
				su.start(primaryStage);
			}
			else {
				RisultatiRicercaUtenti ru = new RisultatiRicercaUtenti(q);
				ru.start(primaryStage);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Attenzione");
			alert.setHeaderText("Non inserire caratteri sensibili come ' o /");
			alert.showAndWait();
		}
			
	}
	
	public void annulla() {
		Main su = new Main();
		try {
			su.start(primaryStage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void azzera() {
		cognome.setText("");
		nome.setText("");
		viaU.setText("");
		numeroU.setText("");
		localita.setText("");
		cap.setText("");
		comuneU.setText("");
		provU.setText("");
		telefonoU.setText("");
		cellulareU.setText("");
		cfU.setText("");
		
		condTerzoResponsabile.setSelected(false);
		condConAmministratore.setSelected(false);
		privato.setSelected(false);
		altroDitta.setSelected(false);
		altroTerzoResponsabile.setSelected(false);
		
		circuiti.setSelected(false);
		termoregolato.setSelected(false);
		contacalorie.setSelected(false);
		superiore35.setSelected(false);
		superiore116.setSelected(false);
		superiore350.setSelected(false);
		
		automatico.setSelected(false);
		manuale.setSelected(false);
		
		cAmministratore.setSelected(true);
		checkAmministratore();
		cCaldaiaDitta.setSelected(true);
		checkCaldaiaDitta();
		cCaldaiaModello.setSelected(true);
		checkCaldaiaModello();
		cBruciatoreDitta.setSelected(true);
		checkBruciatoreDitta();
		cBruciatoreModello.setSelected(true);
		checkBruciatoreModello();
		cInstallatore.setSelected(true);
		checkInstallatore();
		cCertConf.setSelected(true);
		checkCertConf();
		
		codManu.setText("");
		nAnalisi.setValue("");
		
		puliziacbEseguita.setSelected(false);
		puliziacbNonEseguita.setSelected(false);
		puliziacbData.setValue(null);
		analisiEseguita.setSelected(false);
		analisiNonEseguita.setSelected(false);
		analisiAnno.setText("");
		bollinoEseguito.setSelected(false);
		bollinoNonEseguito.setSelected(false);
		bollinoAnno.setText("");
		impAcceso.setSelected(false);
		impSpento.setSelected(false);
		legale.setSelected(false);
		solare.setSelected(false);
		messaInFunzione.setValue(null);
		ordineCI.setSelected(false);
		ordineCN.setSelected(false);
		
	}

	

}
