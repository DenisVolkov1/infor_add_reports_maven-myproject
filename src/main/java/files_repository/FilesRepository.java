package files_repository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Vector;

import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbException;
import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileFilter;
import jcifs.smb.SmbFileOutputStream;
import log.LOg;
import util.DialogWindows;
import util.MyProperties;

public class FilesRepository {
	
	private FilesRepository() {}
	
	public static boolean isOpenRepo() {
		try {
			String repPathDir = MyProperties.getProperty("repPathDir");
			String password = MyProperties.getProperty("repPassword"); 
			String userName = MyProperties.getProperty("repUsername"); 
			NtlmPasswordAuthentication passwordAuthentication = new NtlmPasswordAuthentication(null,userName,password);
			return new SmbFile("smb:"+repPathDir+"/",passwordAuthentication).exists();
		} catch (Exception e) {
			DialogWindows.dialogWindowError(e);
			LOg.logToFile(e);
			return false;
		}
	}
	//smb://10.1.5.66/פאיכמגûי מבלום/_MISHA/UPDATE_SCE10
	public static Vector<String> listNamesFolderProject() throws Exception {
		String repPathDir = MyProperties.getProperty("repPathDir");
		String password = MyProperties.getProperty("repPassword"); 
		String userName = MyProperties.getProperty("repUsername"); 
		NtlmPasswordAuthentication passwordAuthentication = new NtlmPasswordAuthentication(null,userName,password);
		
		SmbFile smbFile = new SmbFile("smb:"+repPathDir+"/",passwordAuthentication);
		SmbFile[] listOfFiles = smbFile.listFiles();
		Vector<String> v = new Vector<String>();
		for (SmbFile file : listOfFiles) {
		    if (file.isDirectory() && (file.getName()).matches("[A-Zְ-ß]\\w+.*")) {
		    	String name = file.getName();
		        v.add(name.substring(0, name.length()-1));
		    }
		}
		return v;
	}
	public static void copyFileToRepo(File srcFile, String destPath) throws Exception {
	        // Create the authentication object 
			String repPathDir = MyProperties.getProperty("repPathDir");
			String password = MyProperties.getProperty("repPassword"); 
			String userName = MyProperties.getProperty("repUsername"); 
			NtlmPasswordAuthentication passwordAuthentication = new NtlmPasswordAuthentication(null,userName,password);

	        // Read src file.
	        InputStream localFile = new FileInputStream(srcFile);

	        // Create output file 
	        SmbFileOutputStream destFileName = new SmbFileOutputStream(new SmbFile("smb:"+destPath+File.separator+srcFile.getName(), passwordAuthentication));

	        // Copy from scr to destination 
	        BufferedReader brl = new BufferedReader(new InputStreamReader(localFile));
	        String b = null;
	        while((b=brl.readLine())!=null){
	            destFileName.write(b.getBytes());
	        }
	        destFileName.flush();
	}
	public static void sendFilesToStorage(File selectedFile) throws Exception {
		
		File[] files = selectedFile.getParentFile().listFiles(new FileFilter() {
			
			@Override
			public boolean accept(File file) {
				return file.getName().matches(".+\\.(sql|rptdesign)$");
			}
		});
		//10.1.5.66/פאיכמגûי מבלום/_MISHA/UPDATE_SCE10/־עק¸עû
		
		for (File f:files) {
			copyFileToRepo(f, "//10.1.5.66/פאיכמגûי מבלום/_MISHA/UPDATE_SCE10/־עק¸עû");
		}
		
		
	}
	
	
}
