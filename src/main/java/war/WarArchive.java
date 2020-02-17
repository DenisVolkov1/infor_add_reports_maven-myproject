package war;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import exception.InfoException;
import util.MyProperties;

public class WarArchive {
	
	private static final byte[] BUFFER = new byte[4096 * 1024];
	
	private WarArchive() {}
	/**
	 * 
	 * @return List reports name from archive -example 'rep_4_5_load_task.rptdesign'
	 * @throws IOException
	 */
	public static Vector<String> getListOfReportFilesNames() throws Exception {
		checkPathArchive();
        String warPath = MyProperties.getProperty("pathArchiveWar") + "\\scprd_scereports.war";
		Path originalPathArchive = Paths.get(warPath);
		final Vector<String> resultList = new Vector<>();
		try (FileSystem fs = FileSystems.newFileSystem(originalPathArchive, null)) {
			
			 final PathMatcher matcher = fs.getPathMatcher("glob:/report/*.rptdesign");
			 FileVisitor<Path> fv = new SimpleFileVisitor<Path>() {
					@Override
					public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
						if(matcher.matches(file)) {
							resultList.add(file.getFileName().toString());
						}
						return FileVisitResult.CONTINUE;
					}
			    };
			    Files.walkFileTree(fs.getPath("\\report\\"),  fv);
		}
		return resultList;	    
	} 

	public static void createBackup(File selectedFile) throws Exception {
		checkPathArchive();
		String desPath = selectedFile.getParent();
		  String warPathOrigin = MyProperties.getProperty("pathArchiveWar") + "\\scprd_scereports.war";
	      	new File(desPath + "\\Backup").mkdir();
		  Path originalPathArchive = Paths.get(warPathOrigin);
		  Path destinationPath = Paths.get(desPath + "\\Backup\\scprd_scereports.war");
		  
		Files.copy(originalPathArchive, destinationPath, StandardCopyOption.REPLACE_EXISTING);
	}
	
	
	public static void addOrUpdateReportFileInArchive(final File selectedFile) throws Exception {
		checkPathArchive();
		String nameFile = selectedFile.toPath().getFileName().toString();
		String pathFile = selectedFile.getAbsolutePath();
		  //Copy archive from origin directory in file report dir with name 'scprd_scereports$$$$_temp.war'.
		  String warPathOrigin = MyProperties.getProperty("pathArchiveWar") + "\\scprd_scereports.war";
		  Path originalPathArchive = Paths.get(warPathOrigin);
		  Path destinationPath = Paths.get(selectedFile.getParent() + "\\scprd_scereports$$$$_temp.war");
		Files.copy(originalPathArchive, destinationPath, StandardCopyOption.REPLACE_EXISTING);
		
		//From temporary archive rewrite all files except added file if it update archive.
		//else add extra file
		//
		File tempWarArchiveFile = Paths.get(selectedFile.getParent() + "\\scprd_scereports$$$$_temp.war").toFile();
		File targetWarArchiveFile = Paths.get(selectedFile.getParent() + "\\scprd_scereports.war").toFile();
			if(targetWarArchiveFile.exists()) targetWarArchiveFile.delete(); //delete if exist old file archive.
		//
		try (final ZipFile war = new ZipFile(tempWarArchiveFile);
				FileOutputStream stream = new FileOutputStream(targetWarArchiveFile);     
					final ZipOutputStream append = new ZipOutputStream(stream)) {
			// first, copy contents from existing war
	        Enumeration<? extends ZipEntry> entries = war.entries();
	        while (entries.hasMoreElements()) {
	            ZipEntry e = entries.nextElement();
	            	//don`t copy update file
	            	if (e.getName().equals("report/"+nameFile)) continue;
	            		append.putNextEntry(e);
	            if (!e.isDirectory()) {
	            	 copy(war.getInputStream(e), append);
	            }
	            append.closeEntry();
	        }
	        // now append some extra content
	        ZipEntry e = new ZipEntry("report/"+nameFile);
	        append.putNextEntry(e);
	        File addedFile = new File(pathFile);
		        //added file as array bytes
		        byte[] data = Files.readAllBytes(addedFile.toPath());
		        append.write(data);
		        append.closeEntry();
		}
		//delete temp archive.
		tempWarArchiveFile.delete();
        //Replace existing origin file archive
		  Path originalPathArchiveAfter = Paths.get(selectedFile.getParent() + "\\scprd_scereports.war");
		  Path destinationPathAfter = Paths.get(warPathOrigin);
		  
		Files.copy(originalPathArchiveAfter, destinationPathAfter, StandardCopyOption.REPLACE_EXISTING); 
		//delete target archive.
		targetWarArchiveFile.delete();
	}

	/**
	 * 
	 * @param nameFile -example 'rep_4_5_load_task'
	 */
	public static void deleteReportFileFromArchive(final String nameFile) throws Exception {
		checkPathArchive();
		Path originalPathArchive = Paths.get(MyProperties.getProperty("pathArchiveWar") + "\\scprd_scereports.war"); 			
		try (FileSystem fs = FileSystems.newFileSystem(originalPathArchive, null)) {
			final PathMatcher matcher = fs.getPathMatcher("glob:/report/*.rptdesign");	
			 FileVisitor<Path> fv =  new SimpleFileVisitor<Path>() {
				   @Override
				   public FileVisitResult visitFile(Path file, BasicFileAttributes attributes) throws IOException {
					   if(matcher.matches(file)) {
						   if (file.getFileName().toString().equals(nameFile+".rptdesign")) {
							   Files.delete(file);
						   }
					   }
					   return FileVisitResult.CONTINUE;
				   }
			}; 
			Files.walkFileTree(fs.getPath("\\report\\"),  fv);
		}
	}
	private static void copy(InputStream input, OutputStream output) throws IOException {
	        int bytesRead;
	        while ((bytesRead = input.read(BUFFER))!= -1) {
	            output.write(BUFFER, 0, bytesRead);
	       }
	}
	private static void checkPathArchive() throws Exception {
		String warPathOrigin = MyProperties.getProperty("pathArchiveWar") + "\\scprd_scereports.war";
		Path originalPathArchive = Paths.get(warPathOrigin);
		//check archive path
		if (!originalPathArchive.toFile().exists()) throw new InfoException("Archive file path is incorect.");
		if(!Files.isWritable(originalPathArchive)) throw new InfoException("Archive read-only! Run with administrator permission.");	
	}
}
