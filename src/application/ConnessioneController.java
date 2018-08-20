package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ConnessioneController implements Initializable {
	Funz funz = new Funz();
	
	@FXML
	private TextField ip;
	@FXML
	private TextField porta;
	@FXML
	private TextField database;
	@FXML
	private TextField user;
	@FXML
	private PasswordField password;
	@FXML
	private TextField passVisible;
	@FXML
	private CheckBox show;
	@FXML
	private Button annulla;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		passVisible.setVisible(false);

		ip.setText(funz.getIp());
		porta.setText(funz.getPort());
		database.setText(funz.getDB());
		user.setText(funz.getUser());
		password.setText(funz.getPassword());
		
	}
	
	public void mostraPassword() {
		
		if(show.isSelected()) {
			String pass = password.getText();
			password.setVisible(false);
			passVisible.setVisible(true);
			passVisible.setText(pass);
		} else {
			String pass = passVisible.getText();
			passVisible.setVisible(false);
			password.setVisible(true);
			password.setText(pass);
		}
	}
	
	public void conferma() {
		funz.setIp(ip.getText());
		funz.SetPort(Integer.parseInt(porta.getText()));
		funz.SetDB(database.getText());
		funz.SetUser(user.getText());
		funz.SetPassword(password.getText());
		annulla();
	}
	
	public void annulla() {
		Stage stage = (Stage) annulla.getScene().getWindow();
		stage.close();
	}
	
	

}
