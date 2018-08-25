package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RichiestaInterventoController implements Initializable{
	public static String cod;
	public static String id;
	public static String ut;
	private Connection connection;
	private Statement stm;
	static ResultSet res;
	static int i;
	
	static SchedaUtenteController suc;
	
	@FXML
	private TextArea note;
	@FXML
	private TextField codice;
	@FXML
	private TextField utente;
	@FXML
	private TextField codManu;
	@FXML
	private TextField motivoChiamata;
	@FXML
	private TextField richiedente;
	@FXML
	private TextField telefono;
	@FXML
	private ComboBox<String> tecnico;
	@FXML
	private DatePicker dataChiamata;
	@FXML
	private DatePicker dataIntervento;
	@FXML
	private CheckBox stampaNote;
	@FXML
	private Button conferma;
	@FXML
	private Button stampa;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub		
		try {
			ConnDB conn = new ConnDB();
			connection = conn.getConnection();
			stm = connection.createStatement();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		if(i==0) {
			codice.setText(id);
			utente.setText(ut);
			codManu.setText(cod);
			try {
				ResultSet rs=stm.executeQuery("select CURRENT_DATE as d");
				rs.next();
				dataChiamata.setValue(LOCAL_DATE(rs.getString("d")));
				dataIntervento.setValue(LOCAL_DATE(rs.getString("d")));
				
				rs=stm.executeQuery("select * from tecnici");
				while(rs.next())
					tecnico.getItems().add(rs.getString("NOMET"));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(i==1) {
			stampa.setDisable(false);
			try {
				codice.setText(res.getString("CODICEU"));
				utente.setText(res.getString("COGNOMECH"));
				codManu.setText(res.getString("CODMANU"));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
	public static final LocalDate LOCAL_DATE (String dateString){
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    LocalDate localDate = LocalDate.parse(dateString, formatter);
	    return localDate;
	}
	
	public void conferma() {
		if(i==0) {
			try {
				stm.execute("insert into ricint(codiceu, datach, motivoch, dataint, codmanu, cognomech, telech, noteint, tecnico) values ("+id+", '"+dataChiamata.getValue()+"', '"+motivoChiamata.getText()+"', '"+dataIntervento.getValue()+"', '"+codManu.getText()+"', '"+richiedente.getText()+"', '"+telefono.getText()+"', '"+note.getText()+"', '"+tecnico.getValue()+"')");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			stampa.setDisable(false);

			ResultSet rs;
			try {
				rs = stm.executeQuery("select * from utenti");


				rs.next();
				while(Integer.parseInt(rs.getString("CODICEU")) != Integer.parseInt(codice.getText())) {
					rs.next();
				}

				if(rs.getString("ANNOPREC1")==null) {
					stm.execute("update utenti set ANNOPREC1=CURRENT_TIMESTAMP where codiceu="+codice.getText());
				}
				else if(rs.getString("ANNOPREC2")==null) {
					stm.execute("update utenti set ANNOPREC2=CURRENT_TIMESTAMP where codiceu="+codice.getText());
				}
				else if(rs.getString("ANNOCOR1")==null) {
					stm.execute("update utenti set ANNOCOR1=CURRENT_TIMESTAMP where codiceu="+codice.getText());
				}
				else if(rs.getString("ANNOCOR2")==null) {
					stm.execute("update utenti set ANNOCOR2=CURRENT_TIMESTAMP where codiceu="+codice.getText());
				}
				else if(rs.getString("ANNOPREC2")!=null) {
					stm.execute("update utenti set ANNOPREC1=CURRENT_TIMESTAMP, ANNOPREC2=NULL, ANNOCOR1=NULL, ANNOCOR2=NULL where codiceu="+codice.getText());
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			suc.requery(suc.getRow());
			suc.refresh();
			conferma.setDisable(true);
		}
		else {
			try {
				stm.execute("update ricint set (codiceu, datach, motivoch, dataint, codmanu, cognomech, telech, noteint, tecnico) values ("+id+", '"+dataChiamata.getValue()+"', '"+motivoChiamata.getText()+"', '"+dataIntervento.getValue()+"', '"+codManu.getText()+"', '"+richiedente.getText()+"', '"+telefono.getText()+"', '"+note.getText()+"', '"+tecnico.getValue()+"')");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	public void annulla() {
		Stage stage = (Stage) codice.getScene().getWindow();
	    // do what you have to do
		//Ok I'll do what i want to do XOXO by Eddie
	    stage.close();
	}
}
