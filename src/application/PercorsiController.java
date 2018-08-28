package application;

import java.io.File;
import java.net.URL;
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

public class PercorsiController implements Initializable {
	Funz funz = new Funz();
	
	@FXML
	private TextField cartella;
	@FXML
	private TextField foglioLav;
	@FXML
	private TextField allegato2;
	@FXML
	private Button annulla;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		cartella.setText(funz.getCartella());
		foglioLav.setText(funz.getFoglioLavoro());
		allegato2.setText(funz.getAllegato());
		
	}
	
	public void conferma() {
		funz.SetCartella(cartella.getText());
		funz.SetFoglioLavoro(foglioLav.getText());
		funz.SetAllegato(allegato2.getText());
		annulla();
	}
	
	public void annulla() {
		Stage stage = (Stage) annulla.getScene().getWindow();
		stage.close();
	}
	
	public void chooserCartella() {
		DirectoryChooser fileChooser = new DirectoryChooser();
		 fileChooser.setTitle("Open Resource File");
		 File selectedFile = fileChooser.showDialog(new Stage());
		 if (selectedFile != null) {
			 cartella.setText(selectedFile.getAbsolutePath());
		 }
	}
	
	public void chooserFoglio() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Resource File");
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("All Files", "*.*"));
		File selectedFile = fileChooser.showOpenDialog(new Stage());
		if (selectedFile != null) {
			foglioLav.setText(selectedFile.getAbsolutePath());
		}
	}
	public void chooserAllegato() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Resource File");
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("All Files", "*.*"));
		File selectedFile = fileChooser.showOpenDialog(new Stage());
		if (selectedFile != null) {
			allegato2.setText(selectedFile.getAbsolutePath());
		}
	}

}
