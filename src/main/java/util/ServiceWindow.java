package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import exception.InfoException;
import log.LOg;

public class ServiceWindow {
	
	private ServiceWindow() {}

	public static void stopService(String nameService) throws Exception {
		  String[] command = {"cmd.exe", "/c", "net", "stop", nameService}; 

			  Process process2 = new ProcessBuilder(command).start(); 
			  BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process2.getInputStream(), "IBM866"));
			  StringBuilder sb = new StringBuilder();
			  String line;
			  while ((line = bufferedReader.readLine()) != null) {
				  sb.append(line); 
			  }
		  
	}
	public static void startService(String nameService) throws Exception {
		  String[] command = {"cmd.exe", "/c", "net", "start", nameService};
		 
			  Process process2 = new ProcessBuilder(command).start(); 
			  BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process2.getInputStream(), "IBM866"));
			  StringBuilder sb = new StringBuilder();
			  String line = bufferedReader.readLine();
			  if (line == null) {
				  throw new InfoException("For restart service. Run with administrator permission!\nProbably this service is absent");
			  } else {
				  while ((line = bufferedReader.readLine()) != null) {
					  sb.append(line);
				  }
			  }  

	}
	
}
