package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CercaBruciatoreController {

	static BruciatoreController bc;
	
	static Stage PrimaryStage;
	
	@FXML
	private TextField ditta;
	@FXML
	private TextField modello;
	@FXML
	private Button cerca;
	
	public void cerca() {
		bc.cerca(ditta.getText(), modello.getText());
		
		Stage stage = (Stage) cerca.getScene().getWindow();
		stage.close();
	}
	

}
