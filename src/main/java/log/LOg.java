package log;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LOg {
	
	private LOg() {}
	
	public static void logToFile(Throwable e) {
		  File logFolder = new File("log");
		  if (!logFolder.exists()) logFolder.mkdir();
		  
		Logger logger = Logger.getLogger("MyLog");
		   FileHandler fh = null;
		        SimpleDateFormat format = new SimpleDateFormat("dd-MM-YYYY HH_mm_ss");
		
		        try {
		            fh = new FileHandler("log/"+format.format(Calendar.getInstance().getTime()) + ".log");
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
}
