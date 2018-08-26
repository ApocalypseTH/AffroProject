package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class CaldaiaAnalisiController implements Initializable{
	
	static Stage stage;
	
	private Connection connection;
	private ResultSet rs;
	private Statement stm;
	
	private Label[][] griglia = new Label[6][4]; //Y;X
	
	static int codiceu;
	static AnalisiController ac;
	
	@FXML
	private Label c1;
	@FXML
	private Label c1ditta;
	@FXML
	private Label c1modello;
	@FXML
	private Label c1matricola;
	@FXML
	private Label c1combustibile;
	@FXML
	private Label c2;
	@FXML
	private Label c2ditta;
	@FXML
	private Label c2modello;
	@FXML
	private Label c2matricola;
	@FXML
	private Label c2combustibile;
	@FXML
	private Label c3;
	@FXML
	private Label c3ditta;
	@FXML
	private Label c3modello;
	@FXML
	private Label c3matricola;
	@FXML
	private Label c3combustibile;
	@FXML
	private Label c4;
	@FXML
	private Label c4ditta;
	@FXML
	private Label c4modello;
	@FXML
	private Label c4matricola;
	@FXML
	private Label c4combustibile;
	@FXML
	private Label c5;
	@FXML
	private Label c5ditta;
	@FXML
	private Label c5modello;
	@FXML
	private Label c5matricola;
	@FXML
	private Label c5combustibile;
	@FXML
	private Label c6;
	@FXML
	private Label c6ditta;
	@FXML
	private Label c6modello;
	@FXML
	private Label c6matricola;
	@FXML
	private Label c6combustibile;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		griglia[0][0]=c1ditta;
		griglia[0][1]=c1modello;
		griglia[0][2]=c1matricola;
		griglia[0][3]=c1combustibile;
		
		griglia[1][0]=c2ditta;
		griglia[1][1]=c2modello;
		griglia[1][2]=c2matricola;
		griglia[1][3]=c2combustibile;
		
		griglia[2][0]=c3ditta;
		griglia[2][1]=c3modello;
		griglia[2][2]=c3matricola;
		griglia[2][3]=c3combustibile;
		
		griglia[3][0]=c4ditta;
		griglia[3][1]=c4modello;
		griglia[3][2]=c4matricola;
		griglia[3][3]=c4combustibile;
		
		griglia[4][0]=c5ditta;
		griglia[4][1]=c5modello;
		griglia[4][2]=c5matricola;
		griglia[4][3]=c5combustibile;
		
		griglia[5][0]=c6ditta;
		griglia[5][1]=c6modello;
		griglia[5][2]=c6matricola;
		griglia[5][3]=c6combustibile;
		
		String q = "select ";
		for(int i=1; i<=6; i++) {
			q=q+"dittac"+i+", modelloc"+i+", matric"+i+", combc"+i+(i==6?" ":", ");
		}
		q=q+"from utenti where codiceu='"+codiceu+"'";
		
		System.out.println(q);
		
		try {
			ConnDB conn = new ConnDB();
			connection = conn.getConnection();
			stm = connection.createStatement();
			rs = stm.executeQuery(q);
			rs.next();
			for (int i = 0; i < griglia.length; i++) {
				griglia[i][0].setText(rs.getString("dittac"+(i+1)));
				griglia[i][1].setText(rs.getString("modelloc"+(i+1)));
				griglia[i][2].setText(rs.getString("matric"+(i+1)));
				griglia[i][3].setText(rs.getString("combc"+(i+1)));
			}
			
			c1.setOnMouseClicked(e -> {
				//utilizzo ac per chiamare il metodo per la nuova analisi e poi chiudo lo stage
				 ac.nuovaAnalisi("c1");
				 stage.close();
			 });
			 c2.setOnMouseClicked(e -> {
				 ac.nuovaAnalisi("c2");
				 stage.close();
			 });
			 c3.setOnMouseClicked(e -> {
				 ac.nuovaAnalisi("c3");
				 stage.close();
			 });
			 c4.setOnMouseClicked(e -> {
				 ac.nuovaAnalisi("c4");
				 stage.close();
			 });
			 c5.setOnMouseClicked(e -> {
				 ac.nuovaAnalisi("c5");
				 stage.close();
			 });
			 c6.setOnMouseClicked(e -> {
				 ac.nuovaAnalisi("c6");
				 stage.close();
			 });
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
