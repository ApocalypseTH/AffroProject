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

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class StoricoPerUtenteController implements Initializable{
	static String query; 
	public static Integer i;
	public static String id;
	private Connection connection;
	private Statement stm;
	private ResultSet rs;
	private Vector<Integer> coda;
	
	@FXML
	private GridPane gp;
	@FXML
	private GridPane analisi;
	@FXML
	private ScrollPane sp;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
//		String connectionString="jdbc:mysql://127.0.0.1:3306/affro?user=root&password=";
//
//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}

		
		try {
			
//			connection = DriverManager.getConnection(connectionString);
			ConnDB conn = new ConnDB();
			connection = conn.getConnection();
			stm = connection.createStatement();
			rs= stm.executeQuery(query);
			
			coda= new Vector<Integer>();
			
			int i=1;
			while (rs.next()) {
				coda.add(Integer.parseInt(rs.getString("CODICEU")));
				
				Label t1= new Label(" "+rs.getString("CODICEU"));
				TextField t2= new TextField(rs.getString("COGNOMEU"));
				TextField t3= new TextField(rs.getString("NOMEU"));
				TextField t4= new TextField(rs.getString("INDIRIZZOU"));
				TextField t5= new TextField(rs.getString("COMUNEU"));
				TextField t6= new TextField(rs.getString("TELEFONOU"));
				TextField t7= new TextField(rs.getString("IMPTIPO"));
				
				t2.setEditable(false);
				t3.setEditable(false);
				t4.setEditable(false);
				t5.setEditable(false);
				t6.setEditable(false);
				t7.setEditable(false);
				
				refresh(t2);
				refresh(t3);
				refresh(t4);
				refresh(t5);
				refresh(t6);
				refresh(t7);
							
				gp.addRow(i, t1, t2, t3, t4, t5, t6, t7);
				i++;
			}
					
		} catch (SQLException e) {
			e.printStackTrace();
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Attenzione");
			alert.setHeaderText("Non inserire caratteri sensibili come ' o /");
			alert.showAndWait();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			
		}
	}
	
	private void refresh(TextField t) {
        t.setOnMouseClicked(e -> {
					TextField source = (TextField) e.getSource();
					int r=gp.getRowIndex(source);
					
					int cod = coda.get(r-1);
					
					String s="select * from utenti as u join ricint as r on u.codiceu=r.codiceu where u.codiceu=? order by r.datach desc";
					
					
					try {
//						rs= stm.executeQuery(s);
						
						PreparedStatement prepStat = connection.prepareStatement(s);
						prepStat.setInt(1, cod);
						
						rs = prepStat.executeQuery();		
						
						
						analisi.getChildren().clear();
						analisi.setGridLinesVisible(false);
						analisi.setGridLinesVisible(true);
						
						int i=0;
						while (rs.next()) {							
							Label t1= new Label(" "+rs.getString("r.CODICEU"));
							TextField t2= new TextField(" "+rs.getString("r.DATACH"));
							TextField t3= new TextField(" "+rs.getString("r.CODMANU"));
							TextField t4= new TextField(" "+rs.getString("r.MOTIVOCH"));
							TextField t5= new TextField(" "+rs.getString("r.COGNOMECH"));
							TextField t6= new TextField(" "+rs.getString("r.TELECH"));
							TextField t7= new TextField(" "+rs.getString("r.DATAINT"));
							TextField t8= new TextField(" "+rs.getString("r.TECNICO"));
							
							t2.setEditable(false);
							t3.setEditable(false);
							t4.setEditable(false);
							t5.setEditable(false);
							t6.setEditable(false);
							t7.setEditable(false);
							t8.setEditable(false);
										
							analisi.addRow(i, t1, t2, t3, t4, t5, t6, t7, t8);
							i++;
						}						
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
        });
        
    }
	
}
