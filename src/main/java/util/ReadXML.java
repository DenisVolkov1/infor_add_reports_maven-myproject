package util;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import log.LOg;

public class ReadXML {
	
	private ReadXML() {}
	
	public static String getIpDataSource(File file) throws Exception {
		String res = null;

			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();  
			DocumentBuilder db= dbf.newDocumentBuilder();
			
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
		return res;
	}
	/**
	 * 
	 * @param file .rptdesign file
	 * @return map: key DataSet , value QueryText
	 * @throws Exception
	 */
	public static Map<String, String> getQueryText(File file) throws Exception {
		
		HashMap<String, String> res = new HashMap<String, String>();
		String valueQueryText = null;
		String keyNameDataSet = null;		
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();  
		DocumentBuilder db= dbf.newDocumentBuilder();
		
		Document doc = db.parse(file);  
		doc.getDocumentElement().normalize(); 
		
		NodeList nodeList = doc.getElementsByTagName("oda-data-set");
		
		for (int itr = 0; itr < nodeList.getLength(); itr++) {  
			Node node = nodeList.item(itr);  
			if (node.getNodeType() == Node.ELEMENT_NODE) {  
				Element eElement = (Element) node;  
				keyNameDataSet = eElement.getAttribute("name");
				
				NodeList nodeList2 = eElement.getElementsByTagName("xml-property");
				
						for (int itr2 = 0; itr2 < nodeList2.getLength(); itr2++) {
							Node node2 = nodeList2.item(itr2); 
								Element eElement2 = (Element) node2;
								if ((eElement2.getAttribute("name")).equals("queryText")) {
									valueQueryText = eElement2.getTextContent();
									res.put(keyNameDataSet, valueQueryText);
								}
						}
			}
		}
		return res;
		
	}
		
}
