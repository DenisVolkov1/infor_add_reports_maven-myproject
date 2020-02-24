package files_repository;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Vector;

import jcifs.smb.SmbFile;
import util.MyProperties;

public class FilesRepository {
	
	private FilesRepository() {}
	
	public static boolean isOpenRepo() throws Exception {
		String path = "smb:/USER-DEN/Users";
		return new SmbFile(path).exists();
	}
	public static Vector<String> listNamesFolderProject() {
		File[] listOfFiles = new File("\\\\USER-DEN\\Users").listFiles();
		Vector<String> v = new Vector<String>();
		for (File file : listOfFiles) {
		    if (file.isDirectory()) {
		        v.add(file.getName());
		    }
		}
		return v;
	}
	
	
}
