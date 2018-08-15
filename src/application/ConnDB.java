package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnDB {
	
	private static Connection connection;

	public Connection getConnection() {
		
		if(connection == null) {
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
		
		return connection;
		
	}
	
	public static void main(String[] args) {
		
		ConnDB conn = new ConnDB();
		System.out.println(conn.getConnection());
		
	}
	

}