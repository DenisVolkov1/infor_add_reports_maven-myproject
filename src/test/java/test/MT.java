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
import java.util.Queue;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;
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
	
	 final static  TimerTask uploadCheckerTimerTask = new TimerTask(){

			File targetWarArchiveFile = Paths.get("C:\\rep\\OOOO\\scprd_scereports.war.isdeploying").toFile();
			 public void run() {
				 System.out.println("EXSIST::C:\\\\rep\\\\OOOO\\\\scprd_scereports.war.isdeploying");
				 if(!targetWarArchiveFile.exists()) {
					 System.out.println("NOT EXSIST!::C:\\\\rep\\\\OOOO\\\\scprd_scereports.war.isdeploying");
					 uploadCheckerTimerTask.cancel();
				 }
				 
			 }
		};
	
	public static void main(String[] args) throws Exception {
	
			Timer uploadCheckerTimer = new Timer(true);
			uploadCheckerTimer.scheduleAtFixedRate(uploadCheckerTimerTask, 0, 600L );
			
			
			
			
//			File targetWarArchiveFile = Paths.get("C:\\rep\\OOOO\\scprd_scereports.war.isdeploying").toFile();
//			if(!targetWarArchiveFile.exists()) {
//				
//			}
//			///C:\rep\OOOO
			//Thread.currentThread().w
			Thread.sleep(10_222_222);
			
	}
	
	
	
	

}
