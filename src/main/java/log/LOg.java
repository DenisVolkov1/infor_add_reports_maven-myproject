package log;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import org.joda.time.DateTime;

public class LOg {
	
	private LOg() {}
	
	public static void logToFile(Throwable e) {
		  File logFolder = new File("log");
		  if (!logFolder.exists()) logFolder.mkdir();
		  
		Logger logger = Logger.getLogger("MyLog");
		   FileHandler fh = null;
		   
		        try {
		            fh = new FileHandler("log/"+new DateTime().toString("dd-MM-YYYY HH_mm_ss") + ".log");
		        } catch (Exception e2) {
		            e2.printStackTrace();
		        }
		        fh.setFormatter(new SimpleFormatter());
		        logger.addHandler(fh);
		        
		        StringWriter sw = new StringWriter();
		        PrintWriter pw = new PrintWriter(sw);
		        	e.printStackTrace(pw);
		        		logger.severe(sw.toString());
		        		fh.close();
	}
	
	public static void logToFile_INFO(String stringLog) {
		  File logFolder = new File("log");
		  if (!logFolder.exists()) logFolder.mkdir();
		  stringLog = (stringLog == null) ? "\r\n " + "NULL stringLog" : "\r\n " + stringLog;
		  
		Logger logger = Logger.getLogger("MyLog");
		   FileHandler fh = null;
		   
		        try {
		            fh = new FileHandler("log/INFO_"+new DateTime().toString("INFO: dd-MM-YYYY") + ".log");
		        } catch (Exception e2) {
		            e2.printStackTrace();
		        }
		        fh.setFormatter(new SimpleFormatter());
		        logger.addHandler(fh);
		        
		        StringWriter sw = new StringWriter();
		        PrintWriter pw = new PrintWriter(sw);
		        pw.write(stringLog);
		        //stringLog.printStackTrace(pw);
		        
		        		logger.info(sw.toString());
		        		fh.close();
	}

	public static void logToFile_SQL(String stringLog) {
		  File logFolder = new File("log");
		  if (!logFolder.exists()) logFolder.mkdir();
		  if(stringLog != null) {
			  stringLog = "\r\n " + stringLog.replaceAll("\t", "");
			  stringLog = "\r\n " + stringLog.replaceAll("  ", "");
			  stringLog = "\r\n " + stringLog.replaceAll("VALUES", "\r\n VALUES ");
			  stringLog = "\r\n " + stringLog.replaceAll("FROM", "\r\n FROM ");
			  stringLog = "\r\n " + stringLog.replaceAll("USE [SCPRD]", "USE [SCPRD] \r\n");

		  }else {
			  stringLog ="NULL stringLog";
		  }
		  stringLog = (stringLog == null) ? "\r\n " + "NULL stringLog" : "\r\n " + stringLog.replaceAll("\t", "");
		  
		Logger logger = Logger.getLogger("MyLog");
		   FileHandler fh = null;
		   
		        try {
		            fh = new FileHandler("log/SQL_"+new DateTime().toString("dd-MM-YYYY")+"_%g" + ".log",true);
		        } catch (Exception e2) {
		            e2.printStackTrace();
		        }
		        fh.setFormatter(new SimpleFormatter());
		        logger.addHandler(fh);
		        
		        StringWriter sw = new StringWriter();
		        PrintWriter pw = new PrintWriter(sw);
		        pw.write(stringLog);
		        //stringLog.printStackTrace(pw);
		        
		        		logger.info(sw.toString());
		        		fh.close();
		
	}
}
