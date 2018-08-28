package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.Vector;

import com.mysql.jdbc.PreparedStatement;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class UnioneUtentiController implements Initializable{
	
	private Connection connection;
	private Statement stm;
	private ResultSet rs;
	private DateConverter d;
	private Vector<Integer> coda;
	private int codiceu;
	
	private TextField[][] griglia = new TextField[12][5];
	
	@FXML  
	private TextField c1ditta;
	@FXML
	private TextField c1modello;
	@FXML
	private TextField c1tipo;
	@FXML
	private TextField c1matricola;
	@FXML
	private TextField c1combustibile;
	@FXML
	private TextField c2ditta;
	@FXML
	private TextField c2modello;
	@FXML
	private TextField c2tipo;
	@FXML
	private TextField c2matricola;
	@FXML
	private TextField c2combustibile;
	@FXML
	private TextField c3ditta;
	@FXML
	private TextField c3modello;
	@FXML
	private TextField c3tipo;
	@FXML
	private TextField c3matricola;
	@FXML
	private TextField c3combustibile;
	@FXML
	private TextField c4ditta;
	@FXML
	private TextField c4modello;
	@FXML
	private TextField c4tipo;
	@FXML
	private TextField c4matricola;
	@FXML
	private TextField c4combustibile;
	@FXML
	private TextField c5ditta;
	@FXML
	private TextField c5modello;
	@FXML
	private TextField c5tipo;
	@FXML
	private TextField c5matricola;
	@FXML
	private TextField c5combustibile;
	@FXML
	private TextField c6ditta;
	@FXML
	private TextField c6modello;
	@FXML
	private TextField c6tipo;
	@FXML
	private TextField c6matricola;
	@FXML
	private TextField c6combustibile;
	@FXML
	private TextField b1ditta;
	@FXML
	private TextField b1modello;
	@FXML
	private TextField b1tipo;
	@FXML
	private TextField b1matricola;
	@FXML
	private TextField b1combustibile;
	@FXML
	private TextField b2ditta;
	@FXML
	private TextField b2modello;
	@FXML
	private TextField b2tipo;
	@FXML
	private TextField b2matricola;
	@FXML
	private TextField b2combustibile;
	@FXML
	private TextField b3ditta;
	@FXML
	private TextField b3modello;
	@FXML
	private TextField b3tipo;
	@FXML
	private TextField b3matricola;
	@FXML
	private TextField b3combustibile;
	@FXML
	private TextField b4ditta;
	@FXML
	private TextField b4modello;
	@FXML
	private TextField b4tipo;
	@FXML
	private TextField b4matricola;
	@FXML
	private TextField b4combustibile;
	@FXML
	private TextField b5ditta;
	@FXML
	private TextField b5modello;
	@FXML
	private TextField b5tipo;
	@FXML
	private TextField b5matricola;
	@FXML
	private TextField b5combustibile;
	@FXML
	private TextField b6ditta;
	@FXML
	private TextField b6modello;
	@FXML
	private TextField b6tipo;
	@FXML
	private TextField b6matricola;
	@FXML
	private TextField b6combustibile;
	
	@FXML
	private GridPane gpUtenti;
	@FXML
	private GridPane storico;
	@FXML
	private GridPane analisi;
	
	@FXML
	private TextField codiceUtente;
	@FXML
	private TextField cognome;
	@FXML
	private TextField nome;
	@FXML
	private TextField indirizzo;
	@FXML
	private TextField comune;
	@FXML
	private TextField telefono;
	
	@FXML
	private Button cambioCodice;
	@FXML
	private Button menuPrinc;
	@FXML
	private Button conferma;
	@FXML
	private Button annulla;
	@FXML
	private TextField nuovoCodice;
	@FXML
	private TextField idCaldIniziale;
	@FXML
	private TextField idCaldFinale;
	@FXML
	private AnchorPane cambiaPane;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
				
		try {
			ConnDB conn = new ConnDB();
			connection = conn.getConnection();
			stm = connection.createStatement();
			rs = stm.executeQuery("select * from utenti order by codiceu");

			coda= new Vector<Integer>();
		
			int i=1;
			while (rs.next()) {
				coda.add(Integer.parseInt(rs.getString("CODICEU")));
				
				Label t1= new Label(" "+rs.getString("CODICEU"));
				TextField t2= new TextField(rs.getString("COGNOMEU"));
				TextField t3= new TextField(rs.getString("NOMEU"));
				
				t1.setFont(new Font("System", 12));
				t2.setFont(new Font("System", 12));
				t3.setFont(new Font("System", 12));
				t2.setPrefHeight(30);
				t3.setPrefHeight(30);
				
				t2.setEditable(false);
				t3.setEditable(false);
				
				refresh(t2);
				refresh(t3);
							
				gpUtenti.addRow(i, t1, t2, t3);
				i++;
			}
					
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			
		}
		
		griglia[0][0]=c1ditta;
		griglia[0][1]=c1modello;
		griglia[0][2]=c1tipo;
		griglia[0][3]=c1matricola;
		griglia[0][4]=c1combustibile;
		
		griglia[1][0]=c2ditta;
		griglia[1][1]=c2modello;
		griglia[1][2]=c2tipo;
		griglia[1][3]=c2matricola;
		griglia[1][4]=c2combustibile;
		
		griglia[2][0]=c3ditta;
		griglia[2][1]=c3modello;
		griglia[2][2]=c3tipo;
		griglia[2][3]=c3matricola;
		griglia[2][4]=c3combustibile;
		
		griglia[3][0]=c4ditta;
		griglia[3][1]=c4modello;
		griglia[3][2]=c4tipo;
		griglia[3][3]=c4matricola;
		griglia[3][4]=c4combustibile;
		
		griglia[4][0]=c5ditta;
		griglia[4][1]=c5modello;
		griglia[4][2]=c5tipo;
		griglia[4][3]=c5matricola;
		griglia[4][4]=c5combustibile;
		
		griglia[5][0]=c6ditta;
		griglia[5][1]=c6modello;
		griglia[5][2]=c6tipo;
		griglia[5][3]=c6matricola;
		griglia[5][4]=c6combustibile;
		
		griglia[6][0]=b1ditta;
		griglia[6][1]=b1modello;
		griglia[6][2]=b1tipo;
		griglia[6][3]=b1matricola;
		griglia[6][4]=b1combustibile;
		
		griglia[7][0]=b2ditta;
		griglia[7][1]=b2modello;
		griglia[7][2]=b2tipo;
		griglia[7][3]=b2matricola;
		griglia[7][4]=b2combustibile;
		
		griglia[8][0]=b3ditta;
		griglia[8][1]=b3modello;
		griglia[8][2]=b3tipo;
		griglia[8][3]=b3matricola;
		griglia[8][4]=b3combustibile;
		
		griglia[9][0]=b4ditta;
		griglia[9][1]=b4modello;
		griglia[9][2]=b4tipo;
		griglia[9][3]=b4matricola;
		griglia[9][4]=b4combustibile;
		
		griglia[10][0]=b5ditta;
		griglia[10][1]=b5modello;
		griglia[10][2]=b5tipo;
		griglia[10][3]=b5matricola;
		griglia[10][4]=b5combustibile;

		griglia[11][0]=b6ditta;
		griglia[11][1]=b6modello;
		griglia[11][2]=b6tipo;
		griglia[11][3]=b6matricola;
		griglia[11][4]=b6combustibile;
		
		for(int i=0; i<griglia.length; i++) {
			for(int j=0; j<griglia[i].length; j++) {
				griglia[i][j].setEditable(false);
			}
		}
		
		codiceUtente.setEditable(false);
		cognome.setEditable(false);
		nome.setEditable(false);
		indirizzo.setEditable(false);
		comune.setEditable(false);
		telefono.setEditable(false);
		
		cambiaPane.setDisable(true);
		
	}
	
	public void refresh(TextField t) {
		t.setOnMouseClicked(e -> {
			TextField source = (TextField) e.getSource();
			int r=gpUtenti.getRowIndex(source);
			
			int cod = coda.get(r-1);
						
			refreshTabella(cod);
			
		});
	}
	
	public void refreshTabella(int codiceU) {
		
		if (cambioCodice.isDisabled())
			cambioCodice.setDisable(false);
		
		codiceu = codiceU;

		String s="select * from utenti as u where u.codiceu='"+codiceU+"'";
		
		try {
			rs= stm.executeQuery(s);
			
			rs.next();
			
			codiceUtente.setText(""+rs.getInt("CODICEU"));
			cognome.setText(rs.getString("COGNOMEU"));
			nome.setText(rs.getString("NOMEU"));
			indirizzo.setText(rs.getString("INDIRIZZOU")+" "+rs.getString("NUMEROU"));
			comune.setText(rs.getString("COMUNEU"));
			telefono.setText(rs.getString("TELEFONOU"));
			
			for(int i=0; i<12; i++) {
				if(i<6) {
					griglia[i][0].setText(rs.getString("DITTAC"+(i+1)));
					griglia[i][1].setText(rs.getString("MODELLOC"+(i+1)));
					griglia[i][3].setText(rs.getString("MATRIC"+(i+1)));
					griglia[i][4].setText(rs.getString("COMBC"+(i+1)));
				}
				else {
					griglia[i][0].setText(rs.getString("DITTAB"+(i-5)));
					griglia[i][1].setText(rs.getString("MODELLOB"+(i-5)));
					griglia[i][2].setText(rs.getString("TIPOB"+(i-5)));
					griglia[i][3].setText(rs.getString("MATRIB"+(i-5)));
					griglia[i][4].setText(rs.getString("COMBB"+(i-5)));
				}
			}
			
			storico.getChildren().clear();
			storico.setGridLinesVisible(false);
			storico.setGridLinesVisible(true);
			
			String sql = "select codiceu, datach from ricint where codiceu='"+codiceu+"'";
			rs = stm.executeQuery(sql);
			
			int i=0;
			while(rs.next()) {
				
				Label t1= new Label(" "+rs.getString("CODICEU"));
				TextField t2= new TextField(rs.getString("DATACH"));
				
				t2.setEditable(false);
				
				storico.addRow(i, t1, t2);
				i++;
			}
			
			analisi.getChildren().clear();
			analisi.setGridLinesVisible(false);
			analisi.setGridLinesVisible(true);
			
			String sql2 = "select codiceu, id, data from analisi where codiceu='"+codiceu+"'";
			rs = stm.executeQuery(sql2);
			
			int i2=0;
			while(rs.next()) {
				
				Label t1= new Label(" "+rs.getString("CODICEU"));
				TextField t2= new TextField(rs.getString("DATA"));
				TextField t3= new TextField(rs.getString("ID"));
				
				t2.setEditable(false);
				t3.setEditable(false);
				
				analisi.addRow(i2, t1, t2, t3);
				i2++;
			}
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	
	public void cambialo() {
		
		cambiaPane.setDisable(false);
		cambioCodice.setDisable(true);
		menuPrinc.setDisable(true);	
		gpUtenti.setMouseTransparent(true);
	}
	
	public void confermalo() {
		
		boolean ok = true;
		String CorB = "";
		String IDiniz = idCaldIniziale.getText().toLowerCase();
		String IDfin = idCaldFinale.getText().toLowerCase();
		
		if (nuovoCodice.getText().isEmpty()) {
			ok=false;
			nuovoCodice.setText("");
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Attenzione");
			alert.setHeaderText("Inserire il codice utente");
			alert.showAndWait();
		} else if(!IDiniz.isEmpty() && !IDfin.isEmpty() && ok) {
				if(!((IDiniz.charAt(0) == 'b' || IDiniz.charAt(0) == 'c') && (Integer.parseInt(""+IDiniz.charAt(1))<7)))
					ok = false;
				
				if(!((IDfin.charAt(0) == 'b' || IDfin.charAt(0) == 'c') && (Integer.parseInt(""+IDfin.charAt(1))<7))) 
					ok = false;
				
				if(IDiniz.charAt(0) == IDfin.charAt(0) && IDfin.charAt(0) == 'c') {
					CorB = "C";
				} else if (IDiniz.charAt(0) == IDfin.charAt(0) && IDfin.charAt(0) == 'b') {
					CorB = "B";
				} else {
					ok = false;
					idCaldFinale.setText("");
					idCaldIniziale.setText("");
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("Attenzione");
					alert.setHeaderText("Inserire coppie di codici C-C o B-B");
					alert.showAndWait();
				}
		} else {
			ok = false;
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Attenzione");
			alert.setHeaderText("Inserire tutti gli id");
			alert.showAndWait();
		}
		
		
			
		if(ok) {
			if (CorB.equals("C")) {
				String sql = "select ditta"+IDiniz+", modello"+IDiniz+", "+IDiniz+"catasto, matri"+IDiniz+", comb"+IDiniz+", mf"+IDiniz+" from utenti where codiceu='"+codiceu+"'";
				try {
					rs = stm.executeQuery(sql);
					rs.next();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				try {
					if(!(rs.getString("ditta"+IDiniz).equals("") && rs.getString("modello"+IDiniz).equals(""))) {
						String upSql = "update utenti set ditta"+IDfin+"='"+rs.getString("ditta"+IDiniz)+"', "
								+ "modello"+IDfin+"='"+rs.getString("modello"+IDiniz)+"', "
								+ IDfin+"catasto='"+rs.getString(IDiniz+"catasto")+"', "
								+ "matri"+IDfin+"='"+rs.getString("matri"+IDiniz)+"', "
								+ "comb"+IDfin+"='"+rs.getString("comb"+IDiniz)+"', "
								+ "mf"+IDfin+"='"+rs.getString("mf"+IDiniz)+"' "
										+ "where codiceu='"+nuovoCodice.getText()+"'";
						System.out.println(upSql);

						stm.execute(upSql);
						
						String oldCald = "update utenti set ditta"+IDiniz+"='', "
								+ "modello"+IDiniz+"='', "
								+ IDiniz+"catasto='', "
								+ "matri"+IDiniz+"='', "
								+ "comb"+IDiniz+"='', "
								+ "mf"+IDiniz+"=null "
										+ "where codiceu='"+codiceu+"'";
						stm.execute(oldCald);
						
						String upAnalisi = "update analisi set codiceu='"+nuovoCodice.getText()+"', id='"+IDfin.toUpperCase()+"' where codiceu='"+codiceu+"' and id='"+IDiniz+"'";
						stm.execute(upAnalisi);
						
						String upStorico = "update ricint set codiceu='"+nuovoCodice.getText()+"' where codiceu='"+codiceu+"'";
						stm.execute(upStorico);
						
						refreshTabella(codiceu);
						annullalo();
						
					} else {
						idCaldIniziale.setText("");
						Alert alert = new Alert(AlertType.WARNING);
						alert.setTitle("Attenzione");
						alert.setHeaderText("Nessuna caldaia selezionata");
						alert.showAndWait();
						
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					nuovoCodice.setText("");
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("Attenzione");
					alert.setHeaderText("Inserire un nuovo codice utente valido");
					alert.showAndWait();
				}
				
			} else {
				String sql = "select ditta"+IDiniz+", modello"+IDiniz+", tipo"+IDiniz+", matri"+IDiniz+", comb"+IDiniz+" from utenti where codiceu='"+codiceu+"'";
				try {
					rs = stm.executeQuery(sql);
					rs.next();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				try {
					if(!(rs.getString("ditta"+IDiniz).equals("") && rs.getString("modello"+IDiniz).equals(""))) {
						String upSql = "update utenti set ditta"+IDfin+"='"+rs.getString("ditta"+IDiniz)+"', "
								+ "modello"+IDfin+"='"+rs.getString("modello"+IDiniz)+"', "
								+ "tipo"+IDfin+"='"+rs.getString("tipo"+IDiniz)+"', "
								+ "matri"+IDfin+"='"+rs.getString("matri"+IDiniz)+"', "
								+ "comb"+IDfin+"='"+rs.getString("comb"+IDiniz)+"' "
										+ "where codiceu='"+nuovoCodice.getText()+"'";
						System.out.println(upSql);
						stm.execute(upSql);
						
						String oldBru = "update utenti set ditta"+IDiniz+"='', "
								+ "modello"+IDiniz+"='', "
								+ "tipo"+IDiniz+"='', "
								+ "matri"+IDiniz+"='', "
								+ "comb"+IDiniz+"='' "
										+ "where codiceu='"+codiceu+"'";
						stm.execute(oldBru);
						
						refreshTabella(codiceu);
						annullalo();
						
					} else {
						idCaldIniziale.setText("");
						Alert alert = new Alert(AlertType.WARNING);
						alert.setTitle("Attenzione");
						alert.setHeaderText("Nessuna caldaia selezionata");
						alert.showAndWait();
						
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					nuovoCodice.setText("");
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("Attenzione");
					alert.setHeaderText("Inserire un nuovo codice utente valido");
					alert.showAndWait();
				}
			}
			
			
		} else {
			idCaldFinale.setText("");
			idCaldIniziale.setText("");
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Attenzione");
			alert.setHeaderText("Inserire ID validi: es. C1-C6 / B1-B6");
			alert.showAndWait();
		}
		
	}
	
	public void annullalo() {
		cambiaPane.setDisable(true);
		menuPrinc.setDisable(false);	
		gpUtenti.setMouseTransparent(false);
		idCaldFinale.setText("");
		idCaldIniziale.setText("");
		nuovoCodice.setText("");
	}
	
	public void gotoMenu() {
		Stage stage = (Stage) menuPrinc.getScene().getWindow();
		stage.close();		
	}

}
