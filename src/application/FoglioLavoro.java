package application;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class FoglioLavoro {
	
	private Stage primaryStage;
	private XWPFDocument document;
	FileOutputStream out;
	Desktop desktop;
	
	public FoglioLavoro(Stage stage) {
		this.primaryStage = stage;
	}
	
	public void replace(String data, String ammin, String iva, String indAmmin, String modelloc, String matri, String utente, String indUtente, String motivo, String note) {
		
		try {
			document = new XWPFDocument(new  FileInputStream("C:/Users/Architetto/Desktop/WordTest/modello_lav.docx"));
			
			for (XWPFParagraph p : document.getParagraphs()) {
	    	    List<XWPFRun> runs = p.getRuns();
	    	    System.out.println("^^^^^^^^^^^^^^^New Paragraph, with "+runs.size()+" runs^^^^^^^^^^^^^");
	    	    if (runs != null) {
	    	        for (XWPFRun r : runs) {
	    	        	System.out.println("---------New run-------");
	    	            String text = r.getText(0);
	    	            System.out.println("text with position:"+r.getText(0));
	    	            System.out.println("just text:"+r.text());
	    	            if (text != null && text.contains("%data")) {
	    	                text = text.replace("%data", data);
	    	                r.setText(text, 0);
	    	            }
	    	            if (text != null && text.contains("%ammin")) {
	    	                text = text.replace("%ammin", ammin);
	    	                r.setText(text, 0);
	    	            } 
	    	            if (text != null && text.contains("%iva")) {
	    	                text = text.replace("%iva", iva);
	    	                r.setText(text, 0);
	    	            }
	    	            if (text != null && text.contains("%indAmmin")) {
	    	                text = text.replace("%indAmmin", indAmmin);
	    	                r.setText(text, 0);
	    	            }
	    	            if (text != null && text.contains("%elemento")) {
	    	                text = text.replace("%elemento", modelloc);
	    	                r.setText(text, 0);
	    	            }
	    	            if (text != null && text.contains("%matri")) {
	    	                text = text.replace("%matri", matri);
	    	                r.setText(text, 0);
	    	            }
	    	            if (text != null && text.contains("%utente")) {
	    	                text = text.replace("%utente", utente);
	    	                r.setText(text, 0);
	    	            }
	    	            if (text != null && text.contains("%indUtente")) {
	    	                text = text.replace("%indUtente", indUtente);
	    	                r.setText(text, 0);
	    	            }
	    	            if (text != null && text.contains("%motivo")) {
	    	                text = text.replace("%motivo", motivo);
	    	                r.setText(text, 0);
	    	                if (motivo.contains("PULIZ") || motivo.contains("variazione orologio ad ora") || motivo.contains("accensione impianto") || motivo.contains("spegnimento impianto")) {
								r.addCarriageReturn();
								r.addCarriageReturn();
	    	                	text+="ESECUZIONE DELLE OPERAZIONI DI MANUTENZIONE PROGRAMMATA DELL'IMPIANTO TERMICO COME DA CONTRATTO IN ESSERE CON PULIZIE E VERIFICHE";
	    	                	r.setText(text, 1);
							}
	    	            }
	    	            if (text != null && text.contains("%note")) {
	    	            	if (note.contains("\n")) {
	    	            		text = text.replace("%note", note.substring(0, note.indexOf('\n')));
	    	            		r.setText(text, 0);
	    	            		if (note.indexOf('\n')+1 <= note.length()-1) {
	    	            			note = note.substring(note.indexOf('\n')+1, note.length());
	    	            			if(note.contains("\n")) {
		    	            			do { r.addCarriageReturn();
											 r.setText(note.substring(0, note.indexOf('\n')));
											 if (note.indexOf('\n')+1 <= note.length()-1)
					    	            		note = note.substring(note.indexOf('\n')+1, note.length());
										} while(note.contains("\n") && note.indexOf('\n')+1 <= note.length()-1);
									}
	    	            			if(!note.contains("\n")) {
	    	            				r.addCarriageReturn();
										r.setText(note);
	    	            			}
	    	            		}
	    	            			
							} else {
								text = text.replace("%note", note);
		    	                r.setText(text, 0);
							}
	    	            	
	    	            }
	    	        }
	    	    }
	    	}
			
			 FileChooser fileChooser = new FileChooser();
			 fileChooser.setTitle("Save file");
			 fileChooser.setInitialFileName("rich");
			 fileChooser.setInitialDirectory(new File("C:/Users/"+System.getProperty("user.name")+"/Documents/"));
			 fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Word Document", "*.docx"));
			 
			 File file = fileChooser.showSaveDialog(primaryStage);
			 
             if(file != null){
            	 out = new FileOutputStream(file);
            	 document.write(out);
     			out.close();
     			desktop = Desktop.getDesktop();
     			if (desktop.isSupported(Desktop.Action.PRINT))
     			    desktop.print(new File(file.getAbsolutePath()));     			
             } else {
            	 System.out.println("save cancelled");
             }
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Attenzione");
			alert.setHeaderText("Il file è attualmente in uso, chiudere il file aperto e riprovare");
			alert.showAndWait();
		}
		
	}
	
	public static void main(String[] args) {
				
	}
	
}
