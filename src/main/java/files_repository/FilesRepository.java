package files_repository;

import java.io.File;
import java.io.FileFilter;
import java.lang.reflect.Field;
import java.net.URLConnection;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.SwingWorker;

import exception.InfoException;
import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbException;
import jcifs.smb.SmbFile;
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
		    if (file.isDirectory() && (file.getName()).matches("[A-Z¿-ﬂ]\\w+/$")) {
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
	 * @param nameRptFile - name Rpt design file
	 */
	public static void checkExistFolderReport(String nameRptFile, String nameProgect) throws Exception {
		SmbFile smbFile = getSmbFileObject(repoPathToReportsFolder(nameProgect));
		SmbFile[] listOfFoldersReport = smbFile.listFiles();
		boolean isExist = false;
		boolean double—oincidence = false;
		for (SmbFile fileReport : listOfFoldersReport) {
		    if (fileReport.isDirectory()) {
		    	System.out.println(fileReport.getName());
		       if (fileReport.getName().matches(".*\\s{4}"+nameRptFile+"/$")) {
		    	   if (isExist) double—oincidence = true;
		    	   isExist = true;
		       }
		    }
		}
		if (double—oincidence) throw new InfoException("Exist two folder in storage with this name RPT name!\r\n\n("+nameRptFile+")");
		if (!isExist) throw new InfoException("Report folder in storage with this RPT ("+nameRptFile+") name not exist.");
	}
	public static String getNameReport(String nameRptFile, String nameProgect) throws Exception {
		SmbFile smbFile = getSmbFileObject(repoPathToReportsFolder(nameProgect));
		SmbFile[] listOfFoldersReport = smbFile.listFiles();
		boolean isExist = false;
		boolean double—oincidence = false;
		String nameFolderReport = null;
		for (SmbFile fileReport : listOfFoldersReport) {
		    if (fileReport.isDirectory()) {
		       if (fileReport.getName().matches(".*\\s{4}"+nameRptFile+"/$")) {
		    	   if (isExist) double—oincidence = true;
		    	   isExist = true;
		    	   nameFolderReport = fileReport.getName();
		       }
		    }
		}
		if (double—oincidence) throw new InfoException("Exist double folder in storage with this RPT name!\r\n\n("+nameRptFile+")");
		if (nameFolderReport == null) return null;
		return nameFolderReport.replaceFirst("\\s{4}"+nameRptFile+"/$", "");
	}

	public static String repoPathToReportsFolder(String nameProgect) {
		return "smb:"+MyProperties.getProperty("repPathDir")+'/'+ nameProgect +MyProperties.getProperty("reportCatalog")+'/';
	}
	public static String repoPathToProjectsFolder() {
		return "smb:"+MyProperties.getProperty("repPathDir")+'/';
	}
}
