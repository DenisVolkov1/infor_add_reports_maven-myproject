package test;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;
import java.util.concurrent.Phaser;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.joda.time.DateTime;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import database.ParamsRelatedData;
import exception.InfoException;
import files_repository.FilesRepository;
import jcifs.smb.SmbFile;
import parce_rptdesign.ParamFromRptDesign;
import parce_rptdesign.ReadXML;
import windows.param.ParamFromParamsPanel;
import static files_repository.FilesRepository.*;

public class MT {
	


	public static void main(String[] args) throws Exception {
		
		
		
		  File file = new File("C:\\RBWWORKSPACE\\RBWWORKSPACE\\WMReports\\WMBIRT\\_develop\\GRAT_VEST\\rep_2_13_ABC_XYZ.rptdesign");

			Vector<ParamFromRptDesign> listParams = new Vector<ParamFromRptDesign>();
			String name = null;
			String promptText = null;
			String dataType = null;
			String paramType = null;
			String controlType = null;
			String dataSetName = null;
			String queryText = null;
			String hidden = null;
			String isRequired = null;
			String _default = null;
			ParamFromRptDesign param = null;
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();  
		DocumentBuilder db= dbf.newDocumentBuilder();
		Document doc = db.parse(file);  
		doc.getDocumentElement().normalize(); 
		
		NodeList nodeList = doc.getElementsByTagName("scalar-parameter");
		int seqNoParamOnFileDesign = 1;
		NEXT_PARAMETR :
			for (int itr = 0; itr < nodeList.getLength(); itr++) {  
				 name = null; promptText = null; dataType = null; paramType = null; controlType = null; dataSetName = null; queryText = null;hidden = null;isRequired= null;
				 
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
											case "isRequired": 
												isRequired = eElement2.getTextContent();
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
					NodeList nodeListDefaultValuesProperty = eElement.getElementsByTagName("simple-property-list");	
					
							for (int itr2 = 0; itr2 < nodeListDefaultValuesProperty.getLength(); itr2++) {
								Node node2 = nodeListDefaultValuesProperty.item(itr2); 
									Element eElement2 = (Element) node2;
									
									System.out.println(eElement2.getAttribute("name"));
									if ((eElement2.getAttribute("name")).equals("defaultValue")) {
										_default = eElement2.getTextContent().trim();
									}
							}
				}
				
				isRequired = (isRequired == null) ? "true" : isRequired; // bug property name="isRequired" maybe is not exists.

				param = new ParamFromRptDesign(
						Integer.toString(seqNoParamOnFileDesign++),
						name,
						promptText,
						dataType,
						paramType,
						controlType,
						isRequired,
						_default,
						dataSetName);
				
				listParams.add(param);
				//end loop
			}
		//Map<String,String> mapQueryText = getQueryText(file);
		for(ParamFromRptDesign p : listParams) {
	
			if(p.getDataSetName() != null) {
				String queryString = "mapQueryText";//.get(p.getDataSetName());
				p.setQueryText(queryString);
			}
		} 
		
		for(ParamFromRptDesign p : listParams) {
			System.out.println("--START");
			System.out.println(p);
			System.out.println("--END");
		} 
			  
		  
			
			
			
			
		
			
			


			
	}
}
