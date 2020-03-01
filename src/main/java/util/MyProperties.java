package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Properties;

import javax.swing.JOptionPane;

import log.LOg;
import windows.MainRunWindow;

public class MyProperties {

	public static void saveProperties (String...props) {
		if (props.length % 2 != 0) throw new IllegalArgumentException("Even number of arguments!!!(Key, Value , Key, Value ...)");
		Properties prop = new Properties();
		
		try (InputStream input = new FileInputStream("config.properties")) {
			prop.load(input);
		} catch (IOException io) {
			DialogWindows.dialogWindowError(io);
				LOg.logToFile(io);
					return;
		}
		
		for (int i = 0;i < props.length;i = i + 2) {
			if (!prop.containsKey(props[i])) throw new IllegalArgumentException("Unknown Key!!!");
		}
		try (OutputStream output = new FileOutputStream("config.properties")) { //Archive file
			for (int i = 0; i < props.length;i = i + 2) {
				prop.setProperty(props[i], props[i+1]);
			}
			prop.store(output, null);
		} catch (IOException io) {
			DialogWindows.dialogWindowError(io);
				LOg.logToFile(io);
		} 
	}
	public static String getProperty(String key) {
		File propertiesFile = new File("config.properties");
		if (!propertiesFile.exists()) {
			Properties prop = new Properties();
			java.io.File propFile = new File("config.properties");
			// Settings default properties to file 'config.properties'
				prop.setProperty("schema", "wmwhse1");
				prop.setProperty("pathArchiveWar", "C:\\infor\\sce\\jboss-as-7.2.0.Final\\scprd-reports1\\deployments");
				prop.setProperty("password", "sql");
				prop.setProperty("login", "sa");
				prop.setProperty("ipDataBase", "localhost");
				prop.setProperty("repPassword", "-None-");
				prop.setProperty("repUsername", "-None-");
				prop.setProperty("repPathDir", "-None-");
			//
			
		    try {
		    	prop.store(new FileOutputStream(propFile), "defult properties");
		    } catch (IOException ex) {
		    	ex.printStackTrace();
		    		LOg.logToFile(ex);
		    }
		}
		Properties prop = new Properties();

		try (InputStream input = new FileInputStream("config.properties")) {
			prop.load(input);
			
		} catch (Exception io) {
			DialogWindows.dialogWindowError(io);
				LOg.logToFile(io);
		}
		return prop.getProperty(key);
	}
	
}
