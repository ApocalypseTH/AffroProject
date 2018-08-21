package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CercaBruciatoreController {

	static BruciatoreController bc;
	static BruciatoreSchedaUtenteController b;
	static int id;
	
	static Stage PrimaryStage;
	
	@FXML
	private TextField ditta;
	@FXML
	private TextField modello;
	@FXML
	private Button cerca;
	
	public void cerca() {
		if(id==1)
			bc.cerca(ditta.getText(), modello.getText());
		if(id==0)
			b.cerca(ditta.getText(), modello.getText());
		
		Stage stage = (Stage) cerca.getScene().getWindow();
		stage.close();
	}
	

}
