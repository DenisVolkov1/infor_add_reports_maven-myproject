package test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.logging.FileHandler;

import files_repository.FilesRepository;
import jcifs.smb.SmbFile;

public class MT {
	
	public static void main(String[] args) throws Exception {
		
		String repPathDir = "--//10.1.5.66/�������� �����/_MISHA/UPDATE_SCE10";
		SmbFile smbFile = new SmbFile("smb:"+repPathDir+'/',FilesRepository.getAuthentication());
				     
		
		
		
	
	}


}
