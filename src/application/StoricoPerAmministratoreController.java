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

public class StoricoPerAmministratoreController implements Initializable{
	public static Integer i;
	public static String id;
	private Connection connection;
	private Statement stm;
	private ResultSet rs;
	private Vector<String> coda;
	private Vector<Integer> ut;
	
	@FXML
	private GridPane gp;
	@FXML
	private GridPane utenti;
	@FXML
	private GridPane interventi;
	@FXML
	private Label l1;
	@FXML
	private Label l2;


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
			rs= stm.executeQuery("select * from ammin");
			
			coda= new Vector<String>();
			ut= new Vector<Integer>();
			
			int i=1;
			while (rs.next()) {
				coda.add(rs.getString("COGNOMEA"));
				
				Label t1= new Label(" "+Integer.toString(i));
				TextField t2= new TextField(rs.getString("COGNOMEA"));
				TextField t3= new TextField(rs.getString("TELEFONOA"));
				
				t2.setEditable(false);
				t3.setEditable(false);
				
				refresh(t2);
				refresh(t3);
							
				gp.addRow(i, t1, t2, t3);
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
					
					String cod = coda.get(r-1);
					
					l1.setText("Utenti di "+cod);
					
					String s="select * from utenti as u join ammin as a on u.cognomea=a.cognomea where a.cognomea=?";
					
					
					try {
//						rs= stm.executeQuery(s);
						
						PreparedStatement prepStat = connection.prepareStatement(s);
						prepStat.setString(1, cod);
						
						rs = prepStat.executeQuery();		
						
						utenti.getChildren().clear();
						utenti.setGridLinesVisible(false);
						utenti.setGridLinesVisible(true);
						ut.clear();
						
						int i=0;
						while (rs.next()) {
							
							ut.add(rs.getInt("u.CODICEU"));
							
							Label t1= new Label(" "+rs.getString("u.CODICEU"));
							TextField t2= new TextField(" "+rs.getString("u.COGNOMEU"));
							TextField t3= new TextField(" "+rs.getString("u.NOMEU"));
							
							t2.setEditable(false);
							t3.setEditable(false);
							
							refreshUt(t2);
							refreshUt(t3);
										
							utenti.addRow(i, t1, t2, t3);
							i++;
						}						
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
        });
        
    }
	
	private void refreshUt(TextField t) {
		t.setOnMouseClicked(e -> {
			TextField source = (TextField) e.getSource();
			int r=utenti.getRowIndex(source);
			
			int cod = ut.get(r);
			
			String s="select * from utenti as u join ricint as r on u.codiceu=r.codiceu where u.codiceu=? order by r.datach desc";
			
			
			try {

				
				PreparedStatement prepStat = connection.prepareStatement(s);
				prepStat.setInt(1, cod);
				
				rs = prepStat.executeQuery();		
				
				rs.next();
				l2.setText("Interventi di "+rs.getString("COGNOMEU"));
				rs.previous();
				
				interventi.getChildren().clear();
				interventi.setGridLinesVisible(false);		
				interventi.setGridLinesVisible(true);
				int i=0;
				while (rs.next()) {							
					Label t1= new Label(" "+rs.getString("r.CODICEU"));
					TextField t2= new TextField(" "+rs.getString("r.DATACH"));
					TextField t4= new TextField(" "+rs.getString("r.MOTIVOCH"));

					t2.setEditable(false);
					t4.setEditable(false);
					

								
					interventi.addRow(i, t1, t2, t4);
					i++;
				}						
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			
});
	}
	
}
