package test;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
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
		
		
			//String nameRptFile = "rep_2_3_Case";
			//System.out.println("qweqwe    rep_myrep10/".matches(".*\\s{4}"+nameRptFile+"/$"));
			//System.out.println("Печать этикетки ящика    rep_2_3_Case/".replaceFirst("\\s{4}"+nameRptFile+"/$", ""));
			//char s = '\u0020';
			//System.out.println("Печать этикетки ящика" +'\u0020'+'\u0020'+'\u0020'+'\u0020'+ "rep_2_3_Case" );

			SmbFile reportsFolder = getSmbFileObject(repoPathToReportsFolder("BOYARD/"));
			SmbFile[] listOfFiles = reportsFolder.listFiles();
			for (SmbFile smbFile : listOfFiles) {
				//String s2= "Печать этикетки ящика    rep_2_3_Case    dnm/";
				Matcher m2 = Pattern.compile("^(.+)" +'\u0020'+'\u0020'+'\u0020'+'\u0020'+ "(.+)" +'\u0020'+'\u0020'+'\u0020'+'\u0020'+".+/$").matcher(smbFile.getName());
				//Matcher m = Pattern.compile("(.+)"+'\u0020'+'\u0020'+'\u0020'+'\u0020'+".+/$").matcher("Печать этикетки ящика    rep_2_3_Case    dnm/");
				if (m2.find()) {
					if (m2.group(1).equals("Печать этикетки ящика") && m2.group(2).equals("rep_2_3_Case")) {
						
						SmbFile folderVersionReport = getSmbFileObject(smbFile.toString()+ m2.group(1)+'\u0020'+'\u0020'+'\u0020'+'\u0020'+m2.group(2)+'\u0020'+'\u0020'+'\u0020'+'\u0020'+getNextVersion(smbFile));
						System.out.println(folderVersionReport);
					}
					
				
				}
			}
	
			
	}

}
