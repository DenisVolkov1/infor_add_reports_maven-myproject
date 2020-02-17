package util;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import log.LOg;

public class ReadXML {
	
	private ReadXML() {}
	
	public static String getIpDataSource(File file) {
		String res = null;
		try {
			 
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();  
			DocumentBuilder db;
			db = dbf.newDocumentBuilder();
			
			Document doc = db.parse(file);  
			doc.getDocumentElement().normalize();  
			NodeList nodeList = doc.getElementsByTagName("oda-data-source");
			
			for (int itr = 0; itr < nodeList.getLength(); itr++) {  
				Node node = nodeList.item(itr);  
				if (node.getNodeType() == Node.ELEMENT_NODE) {  
					Element eElement = (Element) node;  

					NodeList nodeList2 = eElement.getElementsByTagName("property");
							for (int itr2 = 0; itr2 < nodeList2.getLength(); itr2++) {
								Node node2 = nodeList2.item(itr2); 
									Element eElement2 = (Element) node2;
									if ((eElement2.getAttribute("name")).equals("odaURL")) {
										 res = eElement2.getTextContent();
									}
							}
				}  
			}  
		} catch (Exception e1) {
			DialogWindows.dialogWindowError(e1);
				LOg.logToFile(e1);
		}
		return res;
	}
}
