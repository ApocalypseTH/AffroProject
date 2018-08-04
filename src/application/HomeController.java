package application;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class HomeController {
	
	static Stage primaryStage;
	
	@FXML
	private MenuItem schedaUtente;

	@FXML
	private MenuItem ricercheUtenti;

	@FXML
	private MenuItem ricercheAnalisi;

	@FXML
	private MenuItem ricercheStorico;


	public void schedaU(){
		SchedaUtente su = new SchedaUtente();
		try {
			su.start(primaryStage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void ricercheU(){
		RicercaUtenti su = new RicercaUtenti();
		try {
			su.start(primaryStage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void ricercheA(){
		RicercaAnalisi su = new RicercaAnalisi();
		try {
			su.start(primaryStage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void ricercheS(){
		RicercaStorico su = new RicercaStorico();
		try {
			su.start(primaryStage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
