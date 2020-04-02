package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.TimeZone;
import java.util.logging.FileHandler;

import files_repository.FilesRepository;
import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileOutputStream;

public class MT {
	
	public static void main(String[] args) throws Exception {
		//        rptdesign
		//        sql
		File srcFile = new File("C:\\WorkLogicon\\rep\\rep_4_6_asn_untuned_sku\\rep_4_6_asn_untuned_sku.sql");
	    InputStream localFile = new FileInputStream(srcFile);

        // Create output file 
       // FileOutputStream destFileName = new FileOutputStream("C:\\WorkLogicon\\sku.sql");

        // Copy from scr to destination 
	   // InputStreamReader isr = new InputStreamReader(localFile, "UTF-8");
	    
	    
        BufferedReader brl = new BufferedReader(new InputStreamReader(localFile));
        String b = null;
        while((b=brl.readLine())!=null) {
        	b = b + (char)13 + (char)10;
        	//destFileName.write(b.getBytes());
        	System.out.print(b);
            //destFileName.write(b.getBytes());
        }
        brl.close();
       // destFileName.flush();
       // destFileName.close();
		
		
		
				     
		
		
		
	
	}


}
