package files_repository;

import java.io.BufferedReader;
import java.io.File;
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
	//smb://10.1.5.66/файловый обмен/_MISHA/UPDATE_SCE10
	public static Vector<String> listNamesFolderProject() throws Exception {
		String repPathDir = MyProperties.getProperty("repPathDir");
		String password = MyProperties.getProperty("repPassword"); 
		String userName = MyProperties.getProperty("repUsername"); 
		NtlmPasswordAuthentication passwordAuthentication = new NtlmPasswordAuthentication(null,userName,password);
		
		SmbFile smbFile = new SmbFile("smb:"+repPathDir+"/",passwordAuthentication);
		SmbFile[] listOfFiles = smbFile.listFiles();
		Vector<String> v = new Vector<String>();
		for (SmbFile file : listOfFiles) {
		    if (file.isDirectory() && (file.getName()).matches("[A-ZА-Я]\\w+.*")) {
		    	String name = file.getName();
		        v.add(name.substring(0, name.length()-1));
		    }
		}
		return v;
	}
	public boolean copyFile(File srcFile, String destPath) {
	    boolean successful = false;
	    try{
	        // Создаем объект аутентификатор
			String repPathDir = MyProperties.getProperty("repPathDir");
			String password = MyProperties.getProperty("repPassword"); 
			String userName = MyProperties.getProperty("repUsername"); 
			NtlmPasswordAuthentication passwordAuthentication = new NtlmPasswordAuthentication(null,userName,password);

	        // Читаем содержимое исходного файла
	        InputStream localFile = new FileInputStream(srcFile);

	        // Создаем объект для потока куда мы будем писать наша файл
	        SmbFileOutputStream destFileName = new SmbFileOutputStream(new SmbFile(destPath+File.separator+srcFile.getName(), passwordAuthentication));

	        // Ну и копируем все из исходного потока в поток назначения.
	        BufferedReader brl = new BufferedReader(new InputStreamReader(localFile));
	        String b = null;
	        while((b=brl.readLine())!=null){
	            destFileName.write(b.getBytes());
	        }
	        destFileName.flush();
	        successful = true;
	    } catch (Exception e) {
	        successful = false;
	        e.printStackTrace();
	    }
	    return successful;
	}
	public static void sendFilesToStorage(File selectedFile) throws SmbException, MalformedURLException {
		File parentFile = selectedFile.getParentFile();
		SmbFile[] files = new SmbFile("smb:"+parentFile.toString()+"\\").listFiles(new SmbFileFilter() {
			
			@Override
			public boolean accept(SmbFile file) throws SmbException {
				file.getName();
				return true;
			}
		});
		for (SmbFile f:files)  System.out.println(f.getName());
		
	}
	
	
}
