package test;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.TimeZone;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.joda.time.DateTime;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

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
		
	
		
//		List<String> listNameReports = new ArrayList<String>();
//		SmbFile smbFileReportFolders = getSmbFileObject(repoPathToReportsFolder("Lukoil/"));
//		SmbFile[] listOfFoldersReport = smbFileReportFolders.listFiles();
//		for (SmbFile smbFile : listOfFoldersReport) {
//			Matcher m = Pattern.compile("^(.+" +'\u0020'+'\u0020'+'\u0020'+'\u0020'+ ".+)" +'\u0020'+'\u0020'+'\u0020'+'\u0020'+".+/$").matcher(smbFile.getName());
//			if (m.find()) listNameReports.add(m.group(1));
//		}
//		String inputPattern = "по дням";
//		
//		
//		Queue<String> listFindPatterns = new LinkedList<String>();
//		for (String reportsNames : listNameReports) {
//			if (reportsNames.matches(".*"+inputPattern+".*")) {
//				listFindPatterns.add(reportsNames.replaceFirst(""+'\u0020'+'\u0020'+'\u0020'+'\u0020'+".*$", ""));
//			}
//		}
		System.out.println("Состояние склада по объекту    rep_sostoyanie_sklada_po_object".replaceFirst(""+'\u0020'+'\u0020'+'\u0020'+'\u0020'+".*$", ""));
		
		
		
		

		
		
		
		
	
			
	}
	

}
