package parce_rptdesign;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

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
	 * @throws ParserConfigurationException 
	 * @throws IOException 
	 * @throws SAXException 
	 * @throws Exception
	 */
	private static Map<String, String> getQueryText(File file) throws ParserConfigurationException, SAXException, IOException  {
		
		Map<String, String> res = new HashMap<String, String>();
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
	
	public static List<ParamFromRptDesign> getListOfParamsFromRptDesign(File file) throws ParserConfigurationException, SAXException, IOException  {
		
		Vector<ParamFromRptDesign> listParams = new Vector<ParamFromRptDesign>();
			String name = null;
			String promptText = null;
			String dataType = null;
			String paramType = null;
			String controlType = null;
			String dataSetName = null;
			String queryText = null;
			String hidden = null;
			ParamFromRptDesign param = null;
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();  
		DocumentBuilder db= dbf.newDocumentBuilder();
		Document doc = db.parse(file);  
		doc.getDocumentElement().normalize(); 
		
		NodeList nodeList = doc.getElementsByTagName("scalar-parameter");
		
		NEXT_PARAMETR :
			for (int itr = 0; itr < nodeList.getLength(); itr++) {  
				 name = null; promptText = null; dataType = null; paramType = null; controlType = null; dataSetName = null; queryText = null;hidden = null;
				 
				Node node = nodeList.item(itr);  
				if (node.getNodeType() == Node.ELEMENT_NODE) {  
					Element eElement = (Element) node;  
					name = eElement.getAttribute("name");
					
					NodeList nodeListProperty = eElement.getElementsByTagName("property");
					
							for (int itr2 = 0; itr2 < nodeListProperty.getLength(); itr2++) {
								Node node2 = nodeListProperty.item(itr2); 
									Element eElement2 = (Element) node2;
									if ((eElement2.getAttribute("name")).equals("hidden")) {
										if (eElement2.getTextContent().equals("true")) {
											continue NEXT_PARAMETR;
										}
									} else {
										switch (eElement2.getAttribute("name")) {
											case "dataType": 
												dataType = eElement2.getTextContent();
												break;
											case "paramType": 
												paramType = eElement2.getTextContent();
												break;	
											case "controlType": 
												controlType = eElement2.getTextContent();
												break;
											case "dataSetName": 
												dataSetName = eElement2.getTextContent();
												break;	
										}
									}
							}
					NodeList nodeListTextProperty = eElement.getElementsByTagName("text-property");
							
							for (int itr2 = 0; itr2 < nodeListTextProperty.getLength(); itr2++) {
								Node node2 = nodeListTextProperty.item(itr2); 
									Element eElement2 = (Element) node2;
									if ((eElement2.getAttribute("name")).equals("promptText")) {
										promptText = eElement2.getTextContent();
									}
							}		
				}
				param = new ParamFromRptDesign(
						name,
						promptText,
						dataType,
						paramType,
						controlType,
						dataSetName);
				listParams.add(param);
				//end loop
			}
		Map<String,String> mapQueryText = getQueryText(file);
		for(ParamFromRptDesign p : listParams) {
	
			if(p.getDataSetName() != null) {
				String queryString = mapQueryText.get(p.getDataSetName());
				p.setQueryText(queryString);
			}
		}
		return listParams;
	}
}
