package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class RicercaStoricoController implements Initializable{
	public static Stage primaryStage;
	private Connection connection;
	private Statement stm;
	private ToggleGroup group  = new ToggleGroup();
	
	@FXML
	private TextField anno;
	@FXML
	private TextField mese;
	@FXML
	private RadioButton eseguita;
	@FXML
	private RadioButton nonEseguita;
	
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
		RicercaUtenti su = new RicercaUtenti();
		try {
			su.start(primaryStage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void ricercheA(){
		RicercaAnalisi su = new RicercaAnalisi();
		try {
			su.start(primaryStage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void ricercheS(){
		RicercaStorico su = new RicercaStorico();
		try {
			su.start(primaryStage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void storicoPerUtente(){
		RicercaUtenti ru = new RicercaUtenti(true);
		try {
			ru.start(primaryStage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void storicoPerAmministratore(){
		StoricoPerAmministratore su = new StoricoPerAmministratore();
		try {
			su.start(primaryStage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void archivioCaldaie(){
		Caldaie su = new Caldaie();
		try {
			su.start(primaryStage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void archivioInstallatori(){
		Installatori su = new Installatori();
		try {
			su.start(primaryStage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void archivioBruciatori() {
		Bruciatore b = new Bruciatore();
		try {
			b.start(primaryStage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void archivioAmministratori(){
		Amministratori su = new Amministratori();
		try {
			su.start(primaryStage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void elencoTecnici(){
		Tecnici su = new Tecnici();
		try {
			su.start(primaryStage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void anagraficaDitta(){
		AnagraficaDitta su = new AnagraficaDitta();
		try {
			su.start(primaryStage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void unioneUtenti(){
		UnioneUtenti su = new UnioneUtenti();
		try {
			su.start(primaryStage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void parametriConnessione(){
		Connessione su = new Connessione();
		try {
			su.start(primaryStage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void percorsi(){
		Percorsi su = new Percorsi();
		try {
			su.start(primaryStage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void backup(){
		Backup su = new Backup();
		try {
			su.start(primaryStage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void conferma() {
		String q="select u.codiceu, u.cognomeu, u.nomeu, r.dataint, r.motivoch from ricint as r join utenti as u on r.codiceu=u.codiceu where motivoch like '%puliz%'";
		
		if(!"".equals(anno.getText())) {
			if(!"".equals(mese.getText())) {
				if(eseguita.isSelected())
					q=q.concat(" and r.dataint between '"+anno.getText()+"-"+mese.getText()+"-01' and '"+anno.getText()+"-"+mese.getText()+"-31'");
				else if(nonEseguita.isSelected())
					q=q.concat(" and r.dataint<'"+anno.getText()+"-"+mese.getText()+"-01' or r.dataint>'"+anno.getText()+"-"+mese.getText()+"-31'");
			} else {
				if(eseguita.isSelected())
					q=q.concat(" and r.dataint between '"+anno.getText()+"-01-01' and '"+anno.getText()+"-12-31'");
				else if(nonEseguita.isSelected())
					q=q.concat(" and r.dataint<'"+anno.getText()+"-01-01' or r.dataint>'"+anno.getText()+"-12-31'");
			}

		}
		
		q=q.concat(" order by r.dataint desc");
		
//		System.out.println(q);
		
		RisultatiRicercaStorico su = new RisultatiRicercaStorico(q);
		try {
			su.start(primaryStage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void annulla() {
		Main su = new Main();
		try {
			su.start(new Stage());
			Stage s = (Stage) eseguita.getScene().getWindow();
			s.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void azzera() {
		anno.setText("");
		eseguita.setSelected(false);
		nonEseguita.setSelected(false);
	}

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
//
//		
//		try {
//			
//			connection = DriverManager.getConnection(connectionString);
//			ConnDB conn = new ConnDB();
//			connection = conn.getConnection();
//			stm = connection.createStatement();
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
//		
		eseguita.setToggleGroup(group);
		nonEseguita.setToggleGroup(group);
		
	}
}
