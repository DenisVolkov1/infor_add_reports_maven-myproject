package test;

import java.io.File;

import util.ReadXML;

public class MT {
	
	public static void main(String[] args) throws Exception {
	
		
		//<![CDATA[select distinct(USERGROUP) as EMPLOYEE_GROUP from wmwhse1.TASKMANAGERUSER]]>
		//<![CDATA[select s.STORERKEY as OWNERKEY, s.COMPANY as OWNERNAME from wmwhse1.STORER s where s.TYPE = 1]]>
		
		System.out.println( ReadXML.getQueryText(new File("C:\\FacilityUserActivity.rptdesign")).keySet());
		System.out.println("==========================================");
		System.out.println( ReadXML.getQueryText(new File("C:\\FacilityUserActivity.rptdesign")).entrySet());
		
		
				     
		
		
		
	
	}


}
