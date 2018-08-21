package application;

import java.io.IOException;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MessaInFunzioneController implements Initializable{
	
	static Stage primaryStage;	
	static int id;
	
	@FXML
	private Label ditta1;
	@FXML
	private Label modello1;
	@FXML
	private DatePicker data1;
	
	@FXML
	private Label ditta2;
	@FXML
	private Label modello2;
	@FXML
	private DatePicker data2;
	
	@FXML
	private Label ditta3;
	@FXML
	private Label modello3;
	@FXML
	private DatePicker data3;
	
	@FXML
	private Label ditta4;
	@FXML
	private Label modello4;
	@FXML
	private DatePicker data4;
	
	@FXML
	private Label ditta5;
	@FXML
	private Label modello5;
	@FXML
	private DatePicker data5;
	
	@FXML
	private Label ditta6;
	@FXML
	private Label modello6;
	@FXML
	private DatePicker data6;
	private Connection connection;
	private Statement stm;
	private ResultSet rs;
	
	public void conferma(){
		 try {
			 String q="update utenti set ";
			 
			 if(data1.getValue()!=null)
				 q=q.concat("mfc1='"+data1.getValue()+"', ");
			 else
				 q=q.concat("mfc1=null, ");
			 
			 if(data2.getValue()!=null)
				 q=q.concat("mfc2='"+data2.getValue()+"', ");
			 else
				 q=q.concat("mfc2=null, ");
			 
			 if(data3.getValue()!=null)
				 q=q.concat("mfc3='"+data3.getValue()+"', ");
			 else
				 q=q.concat("mfc3=null, ");
			 
			 if(data4.getValue()!=null)
				 q=q.concat("mfc4='"+data4.getValue()+"', ");
			 else
				 q=q.concat("mfc4=null, ");
			 
			 if(data5.getValue()!=null)
				 q=q.concat("mfc5='"+data5.getValue()+"', ");
			 else
				 q=q.concat("mfc5=null, ");
			 
			 if(data6.getValue()!=null)
				 q=q.concat("mfc6='"+data6.getValue()+"' ");
			 else
				 q=q.concat("mfc6=null ");
			 
			stm.execute(q.concat("where codiceu="+id));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		annulla();
	}
	public void annulla(){
		Stage stage = (Stage) ditta1.getScene().getWindow();
	    stage.close();		
	}
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
			rs = stm.executeQuery("select * from utenti where codiceu="+id);
			rs.next();
			
			ditta1.setText(rs.getString("DITTAC1"));
			modello1.setText(rs.getString("MODELLOC1"));
			data1.setValue(LOCAL_DATE(rs.getString("MFC1")));
			
			ditta2.setText(rs.getString("DITTAC2"));
			modello2.setText(rs.getString("MODELLOC2"));
			data2.setValue(LOCAL_DATE(rs.getString("MFC2")));
			
			ditta3.setText(rs.getString("DITTAC3"));
			modello3.setText(rs.getString("MODELLOC3"));
			data3.setValue(LOCAL_DATE(rs.getString("MFC3")));
			
			ditta4.setText(rs.getString("DITTAC4"));
			modello4.setText(rs.getString("MODELLOC4"));
			data4.setValue(LOCAL_DATE(rs.getString("MFC4")));
			
			ditta5.setText(rs.getString("DITTAC5"));
			modello5.setText(rs.getString("MODELLOC5"));
			data5.setValue(LOCAL_DATE(rs.getString("MFC5")));
			
			ditta6.setText(rs.getString("DITTAC6"));
			modello6.setText(rs.getString("MODELLOC6"));
			data6.setValue(LOCAL_DATE(rs.getString("MFC6")));

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static final LocalDate LOCAL_DATE (String dateString){
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    LocalDate localDate = LocalDate.parse(dateString, formatter);
	    return localDate;
	}
	
}
