package application;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import java.io.*;
import java.net.URL;
import java.sql.Connection;

public class Funz {
	
	public static void conn() {
		String ip="";
		String port="";
		String db="";
		String user="";
		String pwd="";
		
		File file = new File("application/dati.xml");
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder;
		try {
			documentBuilder = documentBuilderFactory.newDocumentBuilder();
		
			Document document = documentBuilder.parse(file);
			
			ip = document.getElementsByTagName("ip").item(0).getTextContent();
			port = document.getElementsByTagName("port").item(0).getTextContent();
			db = document.getElementsByTagName("db").item(0).getTextContent();
			user = document.getElementsByTagName("user").item(0).getTextContent();
			pwd = document.getElementsByTagName("password").item(0).getTextContent();
			
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
		
		String connectionString="jdbc:mysql://"+ip+":"+port+"/"+db+"?user="+user+"&password="+pwd;
		
		System.out.println(connectionString);
		
	}
	
	public static void main(String[] args) {
		conn();
	}

}
