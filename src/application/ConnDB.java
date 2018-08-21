package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnDB {
	
	private static Connection connection;

	public Connection getConnection() {
		
		if(connection == null) {
			System.out.println("new connection");
			Funz funz = new Funz();
			String connectionString = funz.getConnString();
			try {
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection(connectionString);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else 
			System.out.println("Same connection");
		
		return connection;	
	}
	
	public void closeConnection() {	
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//da chiamare quando cambio i parametri di connessione, in modo da crearne una nuova con i nuovi parametri
	public void newConnection() {
		Funz funz = new Funz();
		String connectionString = funz.getConnString();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(connectionString);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
//		ConnDB conn = new ConnDB();
//		System.out.println(conn.getConnection());
		
	}
	

}