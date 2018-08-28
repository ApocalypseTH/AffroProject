package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class BackupController {
	Funz f= new Funz();
	
	@FXML
	private TextField dump;
	@FXML
	private TextField restore;
	
	public void chiudi() {
		Stage stage = (Stage) dump.getScene().getWindow();
		stage.close();
	}
	
	public void dump() {
		DirectoryChooser fileChooser = new DirectoryChooser();
		 fileChooser.setTitle("Open Resource File");
		 File selectedFile = fileChooser.showDialog(new Stage());
		 if (selectedFile != null) {
			 dump.setText(selectedFile.getAbsolutePath());
		 }
	}
	
	public void restore() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Resource File");
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("SQL files", "*.sql"));
		File selectedFile = fileChooser.showOpenDialog(new Stage());
		if (selectedFile != null) {
			restore.setText(selectedFile.getAbsolutePath());
		}
	}
	
	public void confBackup() {
		try {
			String s;
			if(f.getPassword().equals("")) {
				 s="C:\\xampp\\mysql\\bin\\mysqldump.exe -u "+f.getUser()+" "+f.getDB()+" -r "+dump.getText()+"\\IgnisBackup.sql";
			}
			else {
				 s="C:\\xampp\\mysql\\bin\\mysqldump.exe -u "+f.getUser()+" -p"+f.getPassword()+" "+f.getDB()+" -r "+dump.getText()+"\\IgnisBackup.sql";
			}
			Process r = Runtime.getRuntime().exec(s);
			System.out.println(r.waitFor());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void confRestore() {
		try {
			String s="";
			if(f.getPassword().equals("")) {
				s="C:\\xampp\\mysql\\bin\\mysql.exe -u "+f.getUser()+" < "+restore.getText();
			}
			else {
				s="C:\\xampp\\mysql\\bin\\mysql.exe -u "+f.getUser()+" -p"+f.getPassword()+" < "+restore.getText();
			}
			Process r = Runtime.getRuntime().exec(s);
			System.out.println(r.waitFor());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		ConnDB c = new ConnDB();
//		Connection co= c.getConnection();
//		try {
//			Statement s= co.createStatement();
//			executeDBScripts(restore.getText(), s);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
 catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	public boolean executeDBScripts(String aSQLScriptFilePath, Statement stmt) throws IOException,SQLException {
		boolean isScriptExecuted = false;
		try {
			BufferedReader in = new BufferedReader(new FileReader(aSQLScriptFilePath));
			String str;
			StringBuffer sb = new StringBuffer();
			while ((str = in.readLine()) != null) {
				sb.append(str+" ");
			}
			in.close();
			stmt.executeUpdate(sb.toString());
			isScriptExecuted = true;
		} catch (Exception e) {
			System.err.println("Failed to Execute" + aSQLScriptFilePath +". The error is"+ e.getMessage());
		}
		return isScriptExecuted;
	}

}
