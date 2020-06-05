package test;

import java.io.File;

import database.ParamsRelatedData;
import util.parce_rptdesign.ParamFromRptDesign;
import util.parce_rptdesign.ReadXML;

public class MT {

	public static void main(String[] args) throws Exception {

		// <![CDATA[select distinct(USERGROUP) as EMPLOYEE_GROUP from
		// wmwhse1.TASKMANAGERUSER]]>
		// <![CDATA[select s.STORERKEY as OWNERKEY, s.COMPANY as OWNERNAME from
		// wmwhse1.STORER s where s.TYPE = 1]]>

		
		  //System.out.println( ReadXML.getListOfParamsFromRptDesign(new File("C:\\FacilityUserActivity.rptdesign")));
		  for (ParamFromRptDesign p : ReadXML.getListOfParamsFromRptDesign(new File("C:\\FacilityUserActivity.rptdesign"))) {
			  System.out.println(p);
			  System.out.println("==========================================");
		  }
		  System.out.println("==========================================");
		 
		 

	}

}
