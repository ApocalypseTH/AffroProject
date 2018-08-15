package application;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public class Funz {
	
	private static File file;
	private static DocumentBuilderFactory documentBuilderFactory;
	private static DocumentBuilder documentBuilder;
	private static Document document;
	
	private static TransformerFactory transformerFactory;
	private static Transformer transformer;
	private static DOMSource source;
	private static StreamResult result;
	
	public Funz() {
		file = new File("src/dati.xml");
		documentBuilderFactory = DocumentBuilderFactory.newInstance();
		try {
			documentBuilder = documentBuilderFactory.newDocumentBuilder();
			document = documentBuilder.parse(file);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		transformerFactory = TransformerFactory.newInstance();
		try {
			transformer = transformerFactory.newTransformer();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
		
	public String getConnString() {
	
		String ip = document.getElementsByTagName("ip").item(0).getTextContent();
		String port = document.getElementsByTagName("port").item(0).getTextContent();
		String db = document.getElementsByTagName("db").item(0).getTextContent();
		String user = document.getElementsByTagName("user").item(0).getTextContent();
		String pwd = document.getElementsByTagName("password").item(0).getTextContent();
		
		return "jdbc:mysql://"+ip+":"+port+"/"+db+"?user="+user+"&password="+pwd;
	}
	
	public void setIp(String ip) {
		
		source = new DOMSource(document);
		result = new StreamResult(new File("src/dati.xml"));
		
		Node nip = document.getElementsByTagName("ip").item(0);
		if(nip.hasChildNodes())
			nip.replaceChild(document.createTextNode(ip), nip.getFirstChild());
		else
			nip.appendChild(document.createTextNode(ip));
		
		try {
			transformer.transform(source, result);
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void SetPort(int port) {
		source = new DOMSource(document);
		result = new StreamResult(new File("src/dati.xml"));
		
		Node nport = document.getElementsByTagName("port").item(0);
		if(nport.hasChildNodes())
			nport.replaceChild(document.createTextNode(""+port), nport.getFirstChild());
		else
			nport.appendChild(document.createTextNode(""+port));
		
		try {
			transformer.transform(source, result);
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void SetDB(String db) {
		source = new DOMSource(document);
		result = new StreamResult(new File("src/dati.xml"));
		
		Node ndb = document.getElementsByTagName("db").item(0);
		if(ndb.hasChildNodes())
			ndb.replaceChild(document.createTextNode(db), ndb.getFirstChild());
		else
			ndb.appendChild(document.createTextNode(db));
		
		try {
			transformer.transform(source, result);
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void SetUser(String user) {
		source = new DOMSource(document);
		result = new StreamResult(new File("src/dati.xml"));
		
		Node nuser= document.getElementsByTagName("user").item(0);
		if(nuser.hasChildNodes())
			nuser.replaceChild(document.createTextNode(user), nuser.getFirstChild());
		else
			nuser.appendChild(document.createTextNode(user));
		
		try {
			transformer.transform(source, result);
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void SetPassword(String password) {
		source = new DOMSource(document);
		result = new StreamResult(new File("src/dati.xml"));
		
		Node npassword = document.getElementsByTagName("password").item(0);
		if(npassword.hasChildNodes())
			npassword.replaceChild(document.createTextNode(password), npassword.getFirstChild());
		else
			npassword.appendChild(document.createTextNode(password));
		
		try {
			transformer.transform(source, result);
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Funz funz = new Funz();
		funz.setIp("127.0.0.1");
		funz.SetPort(3306);
		funz.SetDB("affro");
		funz.SetUser("root");
		funz.SetPassword("");
		System.out.println(funz.getConnString());
	}

}
