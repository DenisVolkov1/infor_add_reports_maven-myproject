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
import java.util.concurrent.Phaser;
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
	
 //	 final static  TimerTask uploadCheckerTimerTask = new TimerTask(){
//
//			File targetWarArchiveFile = Paths.get("C:\\rep\\OOOO\\scprd_scereports.war.isdeploying").toFile();
//			 public void run() {
//				 System.out.println("EXSIST::C:\\\\rep\\\\OOOO\\\\scprd_scereports.war.isdeploying");
//				 if(!targetWarArchiveFile.exists()) {
//					 System.out.println("NOT EXSIST!::C:\\\\rep\\\\OOOO\\\\scprd_scereports.war.isdeploying");
//					 uploadCheckerTimerTask.notify();
//					 uploadCheckerTimerTask.cancel();
//					 
//				 }
//				 
//			 }
//		};
static  Phaser phaser = new Phaser(1);
		 final static  TimerTask uploadCheckerTimerTask = new TimerTask(){
			 
			  {
				 phaser.register();
				 }
			 
				int n = 0;
				 public void run() {
					 
					 System.out.println("N = "+n);
					 
					 if(n > 10) {
						 System.out.println("END!!");
						   System.out.println(uploadCheckerTimerTask.getClass().getName() + " выполняет фазу " + phaser.getPhase());
					        phaser.arriveAndDeregister(); // сообщаем о завершении фаз и удаляем с регистрации объекты 
						 uploadCheckerTimerTask.cancel();
						 
					 }
					 n++;
				 }
			};
			
			
			
	
	public static void main(String[] args) throws Exception {
		
		  //phaser = new Phaser(1);
		  
//			Timer uploadCheckerTimer = new Timer(true);
//			uploadCheckerTimer.scheduleAtFixedRate(uploadCheckerTimerTask, 0, 600L );
//			
//		      // ждем завершения фазы 0
//	        int phase = phaser.getPhase();
//	        phaser.arriveAndAwaitAdvance();
//	        System.out.println("Фаза " + phase + " завершена");
		
		
		  for (ParamFromRptDesign p : ReadXML.getListOfParamsFromRptDesign(new File("C:\\rep_TransportSbornayaSticker.rptdesign"))) {
			 //if (p.getPARAM_TYPE() != null && p.getPARAM_LABEL() != null && p.getPARAM_NAME() != null) ParamsRelatedData.insertParam("27130616", p.getPARAM_NAME(), p.getPARAM_LABEL(), p.getPARAM_TYPE(), p.getPARAM_CONTENTS()).commit();
			  System.out.println(p);
			 
			  
		  }
			
			
			
			
		
			
			

	
			//Thread.sleep(10_222_222);
			
	}
	
	
	
	

}
