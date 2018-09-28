package application;

import java.awt.Desktop;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import com.documents4j.api.DocumentType;
import com.documents4j.api.IConverter;
import com.documents4j.job.LocalConverter;

import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class FoglioLavoro {
	
	private Stage primaryStage;
	private XWPFDocument document;
	FileOutputStream out;
	Desktop desktop;
	Funz funz = new Funz();
	
	public FoglioLavoro(Stage stage) {
		this.primaryStage = stage;
	}
	
	public void replace(int codiceu, String data, String ammin, String iva, String indAmmin, String modelloc, String matri, String utente, String indUtente, String motivo, String note) {
		
		try {
			document = new XWPFDocument(new  FileInputStream(funz.getFoglioLavoro()));
			
			for (XWPFParagraph p : document.getParagraphs()) {
	    	    List<XWPFRun> runs = p.getRuns();
	    	    System.out.println("^^^^^^^^^^^^^^^New Paragraph, with "+runs.size()+" runs^^^^^^^^^^^^^");
	    	    if (runs != null) {
	    	        for (XWPFRun r : runs) {
	    	        	System.out.println("---------New run-------");
	    	            String text = r.getText(0);
	    	            System.out.println("text with position:"+r.getText(0));
	    	            System.out.println("just text:"+r.text());
	    	            if (text != null && text.contains("data")) {
	    	                text = text.replace("data", data);
	    	                r.setText(text, 0);
	    	            }
	    	            if (text != null && text.contains("ammin")) {
	    	                text = text.replace("ammin", ammin);
	    	                r.setText(text, 0);
	    	            } 
	    	            if (text != null && text.contains("iva")) {
	    	                text = text.replace("iva", iva);
	    	                r.setText(text, 0);
	    	            }
	    	            if (text != null && text.contains("indAmmin")) {
	    	                text = text.replace("indAmmin", indAmmin);
	    	                r.setText(text, 0);
	    	            }
	    	            if (text != null && text.contains("elemento")) {
	    	                text = text.replace("elemento", modelloc);
	    	                r.setText(text, 0);
	    	            }
	    	            if (text != null && text.contains("matri")) {
	    	                text = text.replace("matri", matri);
	    	                r.setText(text, 0);
	    	            }
	    	            if (text != null && text.contains("utente")) {
	    	                text = text.replace("utente", utente);
	    	                r.setText(text, 0);
	    	            }
	    	            if (text != null && text.contains("indUtente")) {
	    	                text = text.replace("indUtente", indUtente);
	    	                r.setText(text, 0);
	    	            }
	    	            if (text != null && text.contains("motivo")) {
	    	                text = text.replace("motivo", motivo);
	    	                r.setText(text, 0);
	    	                if (motivo.toUpperCase().contains("PULIZ") || motivo.toUpperCase().contains("VARIAZ") || motivo.toUpperCase().contains("ACCENSIONE IMPIANTO") || motivo.toUpperCase().contains("SPEGNIMENTO IMPIANTO")) {
								r.addCarriageReturn();
								r.addCarriageReturn();
	    	                	String text2="ESECUZIONE DELLE OPERAZIONI DI MANUTENZIONE PROGRAMMATA DELL'IMPIANTO TERMICO COME DA CONTRATTO IN ESSERE CON PULIZIE E VERIFICHE";
	    	                	r.setText(text2, 1);
							}
	    	            }
	    	            if (text != null && text.contains("note")) {
	    	            	if (note.contains("\n")) {
	    	            		text = text.replace("note", note.substring(0, note.indexOf('\n')));
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
								text = text.replace("note", note);
		    	                r.setText(text, 0);
							}
	    	            	
	    	            }
	    	        }
	    	    }
	    	}
			
			 FileChooser fileChooser = new FileChooser();
			 fileChooser.setTitle("Save file");
			 DateConverter dateConv = new DateConverter();
			 fileChooser.setInitialFileName("Intervento_"+dateConv.localToMysql(data));
			 File temp = new File(funz.getCartella()+"/"+codiceu+"/");
			 if (temp.exists())
				 fileChooser.setInitialDirectory(temp);
			 else {
				 File temp2 = new File(funz.getCartella()+"/");
				 if (temp2.exists()) {
					 fileChooser.setInitialDirectory(temp2);
				} else {
					fileChooser.setInitialDirectory(new File("C:/Users/"+System.getProperty("user.name")+"/Documents/"));
				}
			 }
			 fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Word Document", "*.docx"));
			 
			 File file = fileChooser.showSaveDialog(primaryStage);
			 
             if(file != null){
            	 out = new FileOutputStream(file);
            	 document.write(out);
     			out.close();
     			
     			File wordFile = file, target = new File(file.getParent()+"/targetL.pdf");
           	 IConverter converter = LocalConverter.builder()
                     .workerPool(20, 25, 2, TimeUnit.SECONDS)
                     .processTimeout(5, TimeUnit.SECONDS)
                     .build();
           	 
           	 converter.convert(wordFile)
           	 .as(DocumentType.DOCX)
           	 .to(target)
           	 .as(DocumentType.PDF)
           	 .prioritizeWith(1000)
           	 .schedule();
           	 
           	 //stampa del pdf

           	 PDDocument document = PDDocument.load( target );
                PrintService[] printService = PrintServiceLookup.lookupPrintServices(null, null);

                ChoiceDialog dialog = new ChoiceDialog();
                for(int i=0; i<printService.length; i++)
               	 dialog.getItems().add(printService[i]);
                dialog.setHeaderText("Choose the printer!");
                dialog.setContentText("Choose a printer from available printers");
                dialog.setTitle("Printer Choice");
                Optional<PrintService> opt = dialog.showAndWait();
   			 if (opt.isPresent()) {
                   PrintService service = opt.get();    
                   PrinterJob job = PrinterJob.getPrinterJob();
                   job.setPageable(new PDFPageable(document));
                   try {
						job.setPrintService(service);
						job.print(); 
					} catch (PrinterException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} finally {
						document.close();
						
					}     	      	
   			 }
   			 
   			 target.delete();

     			
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
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Attenzione");
			alert.setHeaderText("Inserire la data dell'intervento");
			alert.showAndWait();
		}
		
	}
	
	public static void main(String[] args) {
				
	}
	
}
