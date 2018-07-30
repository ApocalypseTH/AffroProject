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
           //			System.out.println(rs.getString("CODICEU"));
					TextField source = (TextField) e.getSource();
					int r=gp.getRowIndex(source);
					System.out.println(r);
					System.out.println(source.getText());
					
					Node l = (Node) getNodeByRowColumnIndex(r, 0);
//					System.out.println(l.getText());
        });
        
    }
	
	public Node getNodeByRowColumnIndex (final int row, final int column) {
	    Node result = null;
	    ObservableList<Node> childrens = gp.getChildren();

	    int i=0;
	    for (Node node : childrens) {
		        if(gp.getRowIndex(node) == row && gp.getColumnIndex(node) == column) {
		            result = node;
		            break;
		        }
	    }

	    return result;
	}
	
}
