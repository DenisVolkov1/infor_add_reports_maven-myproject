package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import log.LOg;

public class ServiceWindow {
	
	private ServiceWindow() {}

	public static void stopService(String nameService) {
		  String[] command = {"cmd.exe", "/c", "net", "stop", nameService}; 
		  try {
			  Process process2 = new ProcessBuilder(command).start(); 
			  BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process2.getInputStream(), "IBM866"));
			  StringBuilder sb = new StringBuilder();
			  String line;
			  while ((line = bufferedReader.readLine()) != null) {
				  sb.append(line); 
			  }
		  }
		  catch (Exception ex) {
			  DialogWindows.dialogWindowError(ex);
			  	LOg.logToFile(ex);
		  }
	}
	public static void startService(String nameService) {
		  String[] command = {"cmd.exe", "/c", "net", "start", nameService}; 
		  try {
			  Process process2 = new ProcessBuilder(command).start(); 
			  BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process2.getInputStream(), "IBM866"));
			  StringBuilder sb = new StringBuilder();
			  String line = bufferedReader.readLine();
			  if (line == null) {
				  throw new Exception("For restart service. Run with administrator permission!");
			  } else {
				  while ((line = bufferedReader.readLine()) != null) {
					  sb.append(line);
				}
			  }
		  }
		  catch(Exception ex) {
			  DialogWindows.dialogWindowError(ex);
			  LOg.logToFile(ex);
		  }
	}
}
