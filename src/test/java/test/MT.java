package test;

import files_repository.FilesRepository;

public class MT {
	


	public static void main(String[] args) {
		
		/*
		 * String pattern1 = "(\\w)(\\s+)([\\.,])";
		 * System.out.println("wertw y r              , tr                .".replaceAll(
		 * pattern1, "$1\r\n$3"));
		 */
		
	
		String string = "Violation of PRIMARY KEY constraint 'PK__PBSRPT_R__F6FF1628328654FC'. Cannot insert duplicate key in object 'wmwhse1.PBSRPT_REPORTS'. The duplicate key value is (REP1235).";
		String pattern = "([à-ÿÀ-ßa-zA-Z'])(\\.)([' à-ÿÀ-ßa-zA-Z&&[^wrz]])";	
		String s = string.replaceAll(pattern, "$1\\.\n$3");
		System.out.println(s);
		
	
	}


}
