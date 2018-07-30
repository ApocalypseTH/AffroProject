package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class RisultatiRicercaUtentiController implements Initializable{
	public static String query;
	public static Integer i;
	public static String id;
	private Connection connection;
	private Statement stm;
	private ResultSet rs;
	
	@FXML
	private GridPane gp;
	
	

	


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		String connectionString="jdbc:mysql://127.0.0.1:3306/affro?user=root&password=";

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		
		try {
			
			connection = DriverManager.getConnection(connectionString);
			stm = connection.createStatement();
			rs= stm.executeQuery(query);

			
			int i=1;
			while (rs.next()) {
				Label t1= new Label(rs.getString("CODICEU"));
				TextField t2= new TextField(rs.getString("COGNOMEU"));
				TextField t3= new TextField(rs.getString("NOMEU"));
				
				refresh(t2);
				refresh(t3);
							
				gp.addRow(i, t1, t2, t3);
				i++;
			}
					
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	private void refresh(TextField t) {
        t.setOnMouseClicked(e -> {
					TextField source = (TextField) e.getSource();
					int r=gp.getRowIndex(source);
					System.out.println(r);
					String txt=source.getText();
					
					String s="";
					
					if(r==1) {
						s="select * from utenti where cognomeu='"+txt+"'";
					}
					else if(r==2) {
						s="select * from utenti where nomeu='"+txt+"'";
					}
					
					try {
						rs= stm.executeQuery(s);
						
//						riempire con i dati da inserire nei textfield
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
        });
        
    }
	
}
