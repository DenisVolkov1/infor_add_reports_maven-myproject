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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Vector;
import java.util.logging.FileHandler;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import exception.InfoException;
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
	
	public static NtlmPasswordAuthentication getAuthentication() {
		String password = MyProperties.getProperty("repPassword"); 
		String userName = MyProperties.getProperty("repUsername"); 
		return new NtlmPasswordAuthentication(null,userName,password);
	}
	private static SmbFile getSmbFileObject(String url) throws Exception {
		SmbFile res = new SmbFile(url,getAuthentication());
		res.setConnectTimeout(10);
		return res;
	}
	
	public static boolean isOpenRepo() throws Exception {
		try {
			getSmbFileObject(repoPathToProjectsFolder()).connect();
			return true;
		} catch (Exception e) {
			LOg.logToFile(e);
				DialogWindows.dialogWindowError(e);
			return false;
		}
	}
	/***
	 * @param nameProgect - example BPYARD/ -example valid name
	 *
	 */
	public static boolean isExistProjectFolder(String nameProgect) throws Exception {
		try {
			SmbFile folderPrj = getSmbFileObject(repoPathToReportsFolder(nameProgect));
			folderPrj.setConnectTimeout(1000);
			if (folderPrj.exists()) return true;
			else return false;
		} catch (Exception e) {
			LOg.logToFile(e);
			throw e;
		}
	}
	
	public static Vector<String> listNamesFolderProject() throws Exception {
		String repPathDir = MyProperties.getProperty("repPathDir");
		SmbFile smbFile = getSmbFileObject("smb:"+repPathDir+'/');
		SmbFile[] listOfFiles = smbFile.listFiles();
		Vector<String> v = new Vector<String>();
		for (SmbFile file : listOfFiles) {
		    if (file.isDirectory() && (file.getName()).matches("[A-ZÀ-ß]\\w+/$")) {
		        v.add(file.getName());
		    }
		}
		return v;
	}
	private static void copyFileToRepoFolder(File srcFile, SmbFile destPath) throws Exception {
		// get data from file 
		byte[] dataFromFile = Files.readAllBytes(srcFile.toPath());
		 // Create output file 
        SmbFileOutputStream destFileName = new SmbFileOutputStream(getSmbFileObject(destPath.toString()+'/'+srcFile.getName()));
        destFileName.write(dataFromFile);
        destFileName.flush();
        destFileName.close();
	}
	/***
	 * @param nameProgect - example BPYARD/ -example valid name
	 */
	public static void sendFilesToStorage(String nameReport ,String nameProgect, File selectedFile) throws Exception {
		String nameFileReport = selectedFile.toPath().getFileName().toString();
		Matcher m = Pattern.compile("(.+)\\.rptdesign$").matcher(nameFileReport);
		if (m.find()) nameFileReport = m.group(1);
		//
		String folderReportName = '/'+ nameReport +"    "+ nameFileReport+'/';
		//Create folder for folders with files report version.
		SmbFile folderReport = getSmbFileObject(repoPathToReportsFolder(nameProgect) + folderReportName);
		if (!folderReport.exists()) folderReport.mkdir();
		//File filter means save only  .rptdesign or .sql files.
		File[] files = selectedFile.getParentFile().listFiles(new FileFilter() {
			@Override
			public boolean accept(File file) {
				return file.getName().matches(".+\\.(sql|rptdesign)$");
			}
		});
	    //Create folder for new version report.   		
		SmbFile folderVersionReport = getSmbFileObject(folderReport.toString()+nameReport+"    "+nameFileReport+"    "+getNextVersion(folderReport));
		folderVersionReport.mkdir();
		//copy new files in folder report
		for (File f:files) {
			copyFileToRepoFolder(f, folderVersionReport);
		}
	}
	
	private static String getNextVersion(SmbFile smbFile) throws SmbException {
		SmbFile[] listOfFiles = smbFile.listFiles();
		Vector<Integer> v = new Vector<Integer>();
		for (SmbFile file : listOfFiles) {
		    if (file.isDirectory() && (file.getName().matches(".+v[0-9]+/$"))) {
		    	String name = file.getName();
		    	Pattern pattern = Pattern.compile(".+v([0-9]+)/$");
		    	Matcher m = pattern.matcher(name);
		       if(m.find()) v.add(Integer.valueOf(m.group(1)));
		    }
		}
		if (v.isEmpty()) return "v0"+'/';
		Integer maxInteger = Collections.max(new ArrayList<Integer>(v));
		return "v"+ (maxInteger+1)+'/';
	}
	/***
	 * if folder exist throw Exception 
	 * ,else -nothing
	 * @param nameProgect - example BPYARD/ -example valid name
	 */
	public static void isNotExistFolderReport(String nameReport, String nameProgect) throws Exception {
		SmbFile smbFile = getSmbFileObject(repoPathToReportsFolder(nameProgect));
		SmbFile[] listOfFoldersReport = smbFile.listFiles();
		Vector<String> v = new Vector<String>();
		for (SmbFile fileReport : listOfFoldersReport) {
		    if (fileReport.isDirectory()) {
		       if (fileReport.getName().matches(nameReport+".*")) throw new InfoException("Report folder in storage with this name alreary exist.");
		    }
		}
	}
	/***
	 * if folder exist -nothing
	 * ,else throw Exception
	 * @param nameProgect - example BPYARD/ -example valid name
	 */
	public static void isExistFolderReport(String nameReport, String nameProgect) throws Exception {
		SmbFile smbFile = getSmbFileObject(repoPathToReportsFolder(nameProgect));
		SmbFile[] listOfFoldersReport = smbFile.listFiles();
		Vector<String> v = new Vector<String>();
		boolean b = false;
		for (SmbFile fileReport : listOfFoldersReport) {
		    if (fileReport.isDirectory()) {
		       if (fileReport.getName().matches(nameReport+".*")) b = true;
		    }
		}
		if (!b) throw new InfoException("Report folder in storage with this name not exist.");
	}

	public static String repoPathToReportsFolder(String nameProgect) {
		return "smb:"+MyProperties.getProperty("repPathDir")+'/'+ nameProgect +MyProperties.getProperty("reportCatalog")+'/';
	}
	public static String repoPathToProjectsFolder() {
		return "smb:"+MyProperties.getProperty("repPathDir")+'/';
	}
}
