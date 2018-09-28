package application;

import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceDialog;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class Allegato2 {
	
	private Stage primaryStage;
	private XWPFDocument document;
	FileOutputStream out;
	Funz funz = new Funz();
			
	public Allegato2(Stage stage) {
		this.primaryStage = stage;
	}
	
	public void replace(int codiceu, String codiceCatasto, String comuneU, String provU, String indrizzoU, String nU, String ragSocA, String ivaA, String indirizzoA, String nA, String comuneA, String provA, String nC, String dittaC, String modelloC, String matriC, String potFocC, String data, String nomeCognTec) {
		
		try {
			document = new XWPFDocument(new  FileInputStream(funz.getAllegato()));
			
			for (XWPFParagraph p : document.getParagraphs()) {
	    	    List<XWPFRun> runs = p.getRuns();
	    	    System.out.println("^^^^^^^^^^^^^^^New Paragraph, with "+runs.size()+" runs^^^^^^^^^^^^^");
	    	    if (runs != null) {
	    	        for (XWPFRun r : runs) {
	    	        	System.out.println("---------New run-------");
	    	            String text = r.getText(0);
	    	            System.out.println("text with position:"+r.getText(0));
	    	            System.out.println("just text:"+r.text());
	    	            if (text != null && text.contains("codiceCatasto")) {
	    	                text = text.replace("codiceCatasto", codiceCatasto);
	    	                r.setText(text, 0);
	    	            }
	    	            if (text != null && text.contains("comuneU")) {
	    	                text = text.replace("comuneU", comuneU);
	    	                r.setText(text, 0);
	    	            } 
	    	            if (text != null && text.contains("provU")) {
	    	                text = text.replace("provU", provU);
	    	                r.setText(text, 0);
	    	            }
	    	            if (text != null && text.contains("indirizzoU")) {
	    	                text = text.replace("indirizzoU", indrizzoU);
	    	                r.setText(text, 0);
	    	            }
	    	            if (text != null && text.contains("nU")) {
	    	                text = text.replace("nU", nU);
	    	                r.setText(text, 0);
	    	            }
	    	            if (text != null && text.contains("ragioneSocialeA")) {
	    	                text = text.replace("ragioneSocialeA", ragSocA);
	    	                r.setText(text, 0);
	    	            }
	    	            if (text != null && text.contains("ivaA")) {
	    	                text = text.replace("ivaA", ivaA);
	    	                r.setText(text, 0);
	    	            }
	    	            if (text != null && text.contains("indirizzoA")) {
	    	                text = text.replace("indirizzoA", indirizzoA);
	    	                r.setText(text, 0);
	    	            }
	    	            if (text != null && text.contains("nA")) {
	    	                text = text.replace("nA", nA);
	    	                r.setText(text, 0);
	    	            }
	    	            if (text != null && text.contains("comuneA")) {
	    	                text = text.replace("comuneA", comuneA);
	    	                r.setText(text, 0);
	    	            }
	    	            if (text != null && text.contains("provA")) {
	    	                text = text.replace("provA", provA);
	    	                r.setText(text, 0);
	    	            }
	    	            if (text != null && text.contains("nC")) {
	    	                text = text.replace("nC", nC);
	    	                r.setText(text, 0);
	    	            }
	    	            if (text != null && text.contains("dittaC")) {
	    	                text = text.replace("dittaC", dittaC);
	    	                r.setText(text, 0);
	    	            }
	    	            if (text != null && text.contains("modelloC")) {
	    	                text = text.replace("modelloC", modelloC);
	    	                r.setText(text, 0);
	    	            }
	    	            if (text != null && text.contains("matriC")) {
	    	                text = text.replace("matriC", matriC);
	    	                r.setText(text, 0);
	    	            }
	    	            if (text != null && text.contains("potFocC")) {
	    	                text = text.replace("potFocC", potFocC);
	    	                r.setText(text, 0);
	    	            }
	    	            if (text != null && text.contains("dataIntervento")) {
	    	                text = text.replace("dataIntervento", data);
	    	                r.setText(text, 0);
	    	            }
	    	            if (text != null && text.contains("nomeCognTecnico")) {
	    	                text = text.replace("nomeCognTecnico", nomeCognTec);
	    	                r.setText(text, 0);
	    	            }
	    	            
	    	            }
	    	        }
	    	    }
			
			 FileChooser fileChooser = new FileChooser();
			 fileChooser.setTitle("Save file");
			 fileChooser.setInitialFileName("allegato2");
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
            	 
    			//conversione in pdf
    			
            	 File wordFile = file, target = new File(file.getParent()+"/targetAL.pdf");
            	 IConverter converter = LocalConverter.make();
            	 
            	 Future<Boolean> conversion = converter
            	   .convert(wordFile)
            	 .as(DocumentType.DOCX)
            	 .to(target)
            	 .as(DocumentType.PDF)
            	 .prioritizeWith(1000)
            	 .schedule();
            	 
            	 try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            	 
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
		}
		
	}
	
	public static void main(String[] args) {
				
	}
	
}
