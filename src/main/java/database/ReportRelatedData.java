package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import exception.InfoException;
import log.LOg;
import util.DialogWindows;
import util.MyProperties;

public class ReportRelatedData {
	
	private ReportRelatedData() {}
	
	public static Vector<String> getListOfReportNamesAndTranslation(int categoryId) throws ClassNotFoundException, SQLException {
		String schema = MyProperties.getProperty("schema"); 
		Vector<String> resultVector = new Vector<String>();
		String sql = "USE [SCPRD] "
					  + "SELECT "
					  + "rep.RPT_TITLE, "
					  + "(CASE"
					  + "	WHEN tr.DESCRIPTION IS NULL THEN '-' "
					  + "   ELSE tr.DESCRIPTION "
					  + "END) "
					  + "FROM ["+schema+"].PBSRPT_REPORTS AS rep "
					  + "LEFT JOIN ["+schema+"].TRANSLATIONLIST AS tr "
					  +  "ON rep.RPT_ID = tr.JOINKEY1 "
					  + "AND rep.RPT_ID = tr.JOINKEY2 "
					  + "AND rep.RPT_ID = tr.JOINKEY3 "
					  + "AND rep.RPT_ID = tr.JOINKEY4 "
					  + "AND rep.RPT_ID = tr.JOINKEY5 "
					  + "AND tr.TBLNAME = 'PBSRPT_REPORTS' AND tr.LOCALE = 'ru' AND tr.COLUMNNAME = 'RPT_TITLE' "
					  + "WHERE CATEGORY_ID = "+ categoryId;
		try (Connection connection = ConnectionMSSQL.getInstanceConneectionJDBC();
				Statement statement = connection.createStatement();
						ResultSet rs = statement.executeQuery(sql)) {
			while(rs.next()) {
				String title = rs.getString(1);
				String descr = rs.getString(2);
				if (!resultVector.contains(title)) resultVector.add(title);
				if (!resultVector.contains(descr)) resultVector.add(descr);
			}
		} 
			LOg.logToFile_SQL(sql);
		return resultVector;
	}
	public static Vector<String> getListOfReportNames(int categoryId) throws ClassNotFoundException, SQLException {
		String schema = MyProperties.getProperty("schema"); 
		Vector<String> resultVector = new Vector<String>();
		
		String sql = "USE [SCPRD] "
					  + "SELECT RPT_TITLE "
					  + "FROM ["+schema+"].PBSRPT_REPORTS "
					  + "WHERE CATEGORY_ID = "+ categoryId;
		try (Connection connection = ConnectionMSSQL.getInstanceConneectionJDBC();
				Statement statement = connection.createStatement();
						ResultSet rs = statement.executeQuery(sql)) {
			while(rs.next()) {
				resultVector.add(rs.getString(1));
			}
		} 
			LOg.logToFile_SQL(sql);
		return resultVector;
	}
	public static Vector<String> getListOfReportNames(String nameFileReport) throws ClassNotFoundException, SQLException {
		String schema = MyProperties.getProperty("schema"); 
		Vector<String> resultVector = new Vector<String>();
		
		String sql = "USE [SCPRD] "
					  + "SELECT RPT_TITLE "
					  + "FROM ["+schema+"].PBSRPT_REPORTS  "
					  + "WHERE BIRTRPT_URL = '/frameset?__report=report/"+nameFileReport+"'";
		try (Connection connection = ConnectionMSSQL.getInstanceConneectionJDBC();
				Statement statement = connection.createStatement();
						ResultSet rs = statement.executeQuery(sql)) {
			while(rs.next()) {
				resultVector.add(rs.getString(1));
			}
		} 
			LOg.logToFile_SQL(sql);
		return resultVector;
	}

	public static void insertReport(String RPT_ID, String nameReport, int categoryId, String nameFileReport) throws ClassNotFoundException, SQLException {
		String schema = MyProperties.getProperty("schema");
		if (!nameFileReport.matches(".*.rptdesign")) {
			nameFileReport = nameFileReport+".rptdesign";
		}
		String insertPBSRPT_REPORTS = getStringINSERT_REPORT(schema,RPT_ID,nameReport,categoryId,nameFileReport);
		String insertTRANSLATIONLIST = getStringTRANSLATION_LIST(schema,RPT_ID,nameReport);
		
		String insertPBSRPT_REPORTS_enterprise = getStringINSERT_REPORT("enterprise",RPT_ID,nameReport,categoryId,nameFileReport);
		String insertTRANSLATIONLIST_enterprise = getStringTRANSLATION_LIST("enterprise",RPT_ID,nameReport);
		
		
		try (Connection connection = ConnectionMSSQL.getInstanceConneectionJDBC();
				Statement statement = connection.createStatement();
					PreparedStatement insertRep = connection.prepareStatement(insertPBSRPT_REPORTS);
					PreparedStatement insertTranslate = connection.prepareStatement(insertTRANSLATIONLIST);
					PreparedStatement insertRep_enterprise = connection.prepareStatement(insertPBSRPT_REPORTS_enterprise);
					PreparedStatement insertTranslate_enterprise = connection.prepareStatement(insertTRANSLATIONLIST_enterprise)) {
			connection.setAutoCommit(false);	
			insertRep.execute();
			insertTranslate.execute();
			insertRep_enterprise.execute();
			insertTranslate_enterprise.execute();
			connection.commit();
			connection.setAutoCommit(true);
		}
		LOg.logToFile_SQL(insertPBSRPT_REPORTS + "\r\n "+ insertTRANSLATIONLIST);

	}

	private static String getStringTRANSLATION_LIST(String schema, String RPT_ID, String nameReport) {
		String insertTRANSLATIONLIST = "USE [SCPRD] " +   
				"INSERT INTO ["+schema+"].[TRANSLATIONLIST] " + 
				"           ([WHSEID]     " + 
				"           ,[TBLNAME]    " + 
				"           ,[LOCALE]     " + 
				"           ,[JOINKEY1]   " + 
				"           ,[JOINKEY2]   " + 
				"           ,[JOINKEY3]   " + 
				"           ,[JOINKEY4]   " + 
				"           ,[JOINKEY5]   " + 
				"           ,[COLUMNNAME] " + 
				"           ,[CODE]       " + 
				"           ,[DESCRIPTION]" +
				"			,[ADDWHO]     " +
				"			,[EDITWHO]    " +
				"           )" + 
				"     VALUES" + 
				"          (" + 
				"		   '"+schema+"'," + 
				"           'PBSRPT_REPORTS'," + 
				"           'ru'," + 
				"           '"+RPT_ID+"',   " + 
				"           '"+RPT_ID+"',   " + 
				"           '"+RPT_ID+"',   " + 
				"           '"+RPT_ID+"',   " + 
				"           '"+RPT_ID+"',   " + 
				"           'RPT_TITLE',    " + 
				"           '"+RPT_ID+"',   " + 
				"          N'"+nameReport+"',  " + 
				"	        N'add_rep',  " +
				"	        N'add_rep'" +
				"		   )";
		return insertTRANSLATIONLIST;
	}

	private static String getStringINSERT_REPORT(String schema,String RPT_ID,String nameReport,int categoryId,String nameFileReport) {
		String insertPBSRPT_REPORTS = ""
				+ "USE [SCPRD] "
				+ "INSERT INTO ["+schema+"].[PBSRPT_REPORTS](" + 
				"			[WHSEID], " + 
				"			[RPT_ID], " + 
				"			[RPT_DATAWINDOW], " + 
				"			[RPT_LIBRARY], " + 
				"			[RPT_TITLE], " + 
				"			[RPT_PURPOSE], " + 
				"			[RPT_DESCR], " + 
				"			[RPT_HEADER], " + 
				"			[RPT_ACTIVE], " + 
				"			[RPT_TYPE], " + 
				"			[RPT_WHERE], " + 
				"			[RPT_FILTER], " + 
				"			[RPT_SORT], " + 
				"			[ENABLE_FILTER], " + 
				"			[ENABLE_SORT], " + 
				"			[AUTORETRIEVE], " + 
				"			[CATEGORY_ID], " + 
				"			[SHOW_CRITERIA], " + 
				"			[QUERY_MODE], " + 
				"			[SHARED_RPT_ID], " + 
				"			[RPT_URL], " + 
				"			[CANACCESS], " + 
				"			[BIRTRPT_URL], " + 
				"			[PRINTOPTION], " + 
				"			[DEFAULTPRINTER], " + 
				"			[CUSTOMREPORTTYPE], " + 
				"			[SOURCEVERSION], " + 
				"			[BASE_REPORT], " + 
				"			[COMMENTS], " +
				"			[ADDWHO], " +
				"			[EDITWHO] " +
				") " + 
				"     VALUES " + 
				"           (" + 
				"		   '"+schema+"',        " + 
				"		    '"+RPT_ID+"',      " + 
				"		   'r_appt_eqpt',    " + 
				"		   'report01.pbl',   " + 
				"		  N'"+nameReport+"', " + 
				"		   NULL,             " + 
				"		   NULL,             " + 
				"		   'Y',              " + 
				"		   'Y',              " + 
				"		   1,                " + 
				"		   null,             " + 
				"		   '',               " + 
				"		   null,             " + 
				"		   'Y',              " + 
				"		   'Y',              " + 
				"		   'Y',              " + 
				"	       "+categoryId+",      " + 
				"		   'Y',              " + 
				"		   NULL,             " + 
				"		   NULL,             " + 
				"		   '',               " + 
				"		   'WEBUI',          " + 
				"		   '/frameset?__report=report/"+nameFileReport+"'," + 
				"		   'DIS',            " + 
				"		   null,             " + 
				"		   0,                " + 
				"		   0,                " + 
				"		   '1',              " + 
				"		   null,             " +
				"	       N'add_rep',       " +
				"	       N'add_rep'" +
				"		   )";
		return insertPBSRPT_REPORTS;
	}

	public static String getRPT_ID (String nameReport, int categoryId) throws Exception {
		String schema = MyProperties.getProperty("schema");
		String sql_getRptId = "USE [SCPRD] "
						  +"SELECT RPT_ID " + 
						  "FROM ["+schema+"].PBSRPT_REPORTS " + 
						  "WHERE RPT_TITLE = N'"+nameReport+"' AND CATEGORY_ID = "+categoryId;	
		String result = null;
		try (Connection connection = ConnectionMSSQL.getInstanceConneectionJDBC();
				Statement statement = connection.createStatement();
						ResultSet rs = statement.executeQuery(sql_getRptId)) {	
			while(rs.next()) {
				result = rs.getString(1);
				if (rs.getRow() > 1) throw new InfoException("Two or more reports with equality name is unacceptable!");
			}
		}
		LOg.logToFile_SQL(sql_getRptId);
		return result; 
	}
	public static Boolean getRPT_ACTIVE (String nameReport, int categoryId) throws Exception {
		String schema = MyProperties.getProperty("schema");
		String RPT_ID = getRPT_ID(nameReport, categoryId);
		String sql_getRptActive = "USE [SCPRD] "
							 +"SELECT RPT_ACTIVE " 
							 +"FROM ["+schema+"].PBSRPT_REPORTS " 
							 +"WHERE RPT_ID = '"+RPT_ID+"'";	
		String result = null;
		try (Connection connection = ConnectionMSSQL.getInstanceConneectionJDBC();
				Statement statement = connection.createStatement();
						ResultSet rs = statement.executeQuery(sql_getRptActive)) {	
			while(rs.next()) {
				result = rs.getString(1);
			}
		}
		LOg.logToFile_SQL(sql_getRptActive);
		return result.equals("Y") ? true : false; 
	}
	public static String getReportFilePath(String nameReport, int categoryId) throws Exception {
		String schema = MyProperties.getProperty("schema");
		String RPT_ID = getRPT_ID(nameReport, categoryId);
		String result = null;
		String sql_getBirtrptUrl = "USE [SCPRD] "
							   +"SELECT BIRTRPT_URL " 
				  			   +"FROM ["+schema+"].PBSRPT_REPORTS " 
				               +"WHERE RPT_ID = '"+RPT_ID+"'";
		try (Connection connection = ConnectionMSSQL.getInstanceConneectionJDBC();
				Statement statement = connection.createStatement();
						ResultSet rs = statement.executeQuery(sql_getBirtrptUrl)) {	
			while(rs.next()) {
				result = rs.getString(1);
			}
		}
		LOg.logToFile_SQL(sql_getBirtrptUrl);
		return result;
	}

	public static void updateReport(String nameReport, Integer categoryId, String newRPT_ID, Integer newCategoryId, String newNameReport, String newNameFileReport, Boolean rptActive) throws Exception {
		String schema = MyProperties.getProperty("schema");
		String RPT_ID = getRPT_ID(nameReport, categoryId);
		
		String updatePBSRPT_REPORTS = getString_updatePBSRPT_REPORTS( schema , nameReport,  categoryId,  newRPT_ID,  newCategoryId,  newNameReport,  newNameFileReport,  rptActive );
		//
		String updatePBSRPT_REPORTS_enterprise = getString_updatePBSRPT_REPORTS( "enterprise" , nameReport,  categoryId,  newRPT_ID,  newCategoryId,  newNameReport,  newNameFileReport,  rptActive );
		
		String updateTRANSLATIONLIST=null;
		String updateTRANSLATIONLIST_enterprise=null;
		if (newRPT_ID != null || newNameReport != null) {
			updateTRANSLATIONLIST = getString_updateTRANSLATIONLIST(schema,  RPT_ID,  newRPT_ID, newNameReport);
			updateTRANSLATIONLIST_enterprise = getString_updateTRANSLATIONLIST("enterprise",  RPT_ID,  newRPT_ID, newNameReport);
			
			try (Connection connection = ConnectionMSSQL.getInstanceConneectionJDBC();
					Statement statement = connection.createStatement();
						PreparedStatement updateRep = connection.prepareStatement(updatePBSRPT_REPORTS);
						PreparedStatement updateRep_enterprise = connection.prepareStatement(updatePBSRPT_REPORTS_enterprise);
						PreparedStatement updateTranslate = connection.prepareStatement(updateTRANSLATIONLIST);
						PreparedStatement updateTranslate_enterprise = connection.prepareStatement(updateTRANSLATIONLIST_enterprise)) {
					
					updateRep.execute();
					updateRep_enterprise.execute();
					updateTranslate.execute();
					updateTranslate_enterprise.execute();
					connection.commit();
					connection.setAutoCommit(true);
			}
		} else {
			try (Connection connection = ConnectionMSSQL.getInstanceConneectionJDBC();
					Statement statement = connection.createStatement();
						PreparedStatement updateRep = connection.prepareStatement(updatePBSRPT_REPORTS);
						PreparedStatement updateRep_enterprise = connection.prepareStatement(updatePBSRPT_REPORTS_enterprise)) {
				
					updateRep.execute();
					updateRep_enterprise.execute();
					connection.commit();
					connection.setAutoCommit(true);
			}
		}
		LOg.logToFile_SQL(updatePBSRPT_REPORTS + "\r\n "+ updateTRANSLATIONLIST);
	}
	public static void deleteReport(String nameReport, int categoryId) throws Exception {
		String schema = MyProperties.getProperty("schema");
		String RPT_ID = getRPT_ID(nameReport, categoryId);
		
		String deletePBSRPT_REPORTS = getString_deletePBSRPT_REPORTS(schema, RPT_ID);
		String deletePBSRPT_REPORTS_enterprise = getString_deletePBSRPT_REPORTS("enterprise", RPT_ID);
		
		String deleteTRANSLATIONLIST = getString_deleteTRANSLATIONLIST(schema, RPT_ID);
		String deleteTRANSLATIONLIST_enterprise = getString_deleteTRANSLATIONLIST("enterprise", RPT_ID);
		
		try (Connection connection = ConnectionMSSQL.getInstanceConneectionJDBC();
				Statement statement = connection.createStatement();
					PreparedStatement updateRep = connection.prepareStatement(deletePBSRPT_REPORTS);			
					PreparedStatement updateTranslate = connection.prepareStatement(deleteTRANSLATIONLIST);
					PreparedStatement updateRep_enterprise = connection.prepareStatement(deletePBSRPT_REPORTS_enterprise);
					PreparedStatement updateTranslate_enterprise = connection.prepareStatement(deleteTRANSLATIONLIST_enterprise);
				
				) {
			connection.setAutoCommit(false);	
			
			updateRep.execute();
			updateTranslate.execute();
			updateRep_enterprise.execute();
			updateTranslate_enterprise.execute();
			
			connection.commit();
			connection.setAutoCommit(true);
		}
		LOg.logToFile_SQL(deletePBSRPT_REPORTS + "\r\n "+ deleteTRANSLATIONLIST);
	}
	private static String getString_deleteTRANSLATIONLIST(String schema, String RPT_ID) {
		String deleteTRANSLATIONLIST = "USE [SCPRD] DELETE FROM ["+schema+"].[TRANSLATIONLIST] " +  
				    "WHERE JOINKEY1 = '"+RPT_ID+"' "
				     +"AND JOINKEY2 = '"+RPT_ID+"' "
				     +"AND JOINKEY3 = '"+RPT_ID+"' "
				     +"AND JOINKEY4 = '"+RPT_ID+"' "
				     +"AND JOINKEY5 = '"+RPT_ID+"' "
				     +"AND TBLNAME = 'PBSRPT_REPORTS' AND LOCALE = 'ru' AND COLUMNNAME = 'RPT_TITLE'";
		return deleteTRANSLATIONLIST;
	}
	private static String getString_deletePBSRPT_REPORTS(String schema, String RPT_ID) {
		String deletePBSRPT_REPORTS = "USE [SCPRD] DELETE FROM ["+schema+"].[PBSRPT_REPORTS] WHERE RPT_ID = '"+RPT_ID+"' ";
		return deletePBSRPT_REPORTS;
	}
	/**
	 * @return
	 * <pre>
	 * [0] - RPT_ID
	 * [1] - Category
	 * [2] - name report (RPT_TITLE)
	 * [3] - name file report (*.rptdesign)
	 * </pre>        
	 * */
	public static String[] getReportFields(String nameReport, int categoryId) throws Exception {
		String schema = MyProperties.getProperty("schema");
		String RPT_ID = getRPT_ID(nameReport, categoryId);
		String[] result = new String [4];
		String sql_getBirtrptUrl = "USE [SCPRD] "
							   + "SELECT "   
							   + "r.RPT_ID, "
							   + "(CASE "
							 	+"	WHEN tr.[DESCRIPTION] IS NULL THEN c.CATEGORY "
							 	+"	ELSE tr.[DESCRIPTION] "
							 	+"END) AS [DESCRIPTION], "
							 	+"r.RPT_TITLE, "
							 	+"r.BIRTRPT_URL "
							    +"FROM wmwhse1.PBSRPT_REPORTS AS r "
							 	+"LEFT JOIN ["+schema+"].PBSRPT_CATEGORY AS c "
							 		+"ON r.CATEGORY_ID = c.CATEGORY_ID "
							 	+"LEFT JOIN ["+schema+"].TRANSLATIONLIST AS tr "
								 	+"ON c.CATEGORY_ID = tr.JOINKEY1 "
								 	+"AND c.CATEGORY_ID = tr.JOINKEY2 "
								 	+"AND c.CATEGORY_ID = tr.JOINKEY3 "
								 	+"AND c.CATEGORY_ID = tr.JOINKEY4 "
								 	+"AND c.CATEGORY_ID= tr.JOINKEY5 "
								 	+"AND tr.TBLNAME = 'PBSRPT_CATEGORY' AND tr.LOCALE = 'ru' AND tr.COLUMNNAME = 'CATEGORY' "
								 	+"WHERE RPT_ID = '"+RPT_ID+"'";
		try (Connection connection = ConnectionMSSQL.getInstanceConneectionJDBC();
				Statement statement = connection.createStatement();
						ResultSet rs = statement.executeQuery(sql_getBirtrptUrl)) {	
			while(rs.next()) {
				result[0] = rs.getString(1);
				result[1] = rs.getString(2);
				result[2] = rs.getString(3);
				result[3] = rs.getString(4).replace("/frameset?__report=report/", "").replace(".rptdesign", "");
			}
		}
		LOg.logToFile_SQL(sql_getBirtrptUrl);
		return result;
	}

	public static Map<String,Integer> getNameReports_LIKE_Pattern(String inputPattern) throws ClassNotFoundException, SQLException {
		Map<String,Integer> result = new HashMap<String, Integer>();
		if(!inputPattern.isEmpty()) {
			String schema = MyProperties.getProperty("schema");
			String RPT_TITLE = null;
			Integer CATEGORY_ID = null;
			
			String sql_ = "USE [SCPRD]"
					  +"SELECT RPT_TITLE, MIN(CATEGORY_ID) CATEGORY_ID "
				      +"FROM ["+schema+"].PBSRPT_REPORTS "
				      +"WHERE RPT_TITLE LIKE '%' + '"+inputPattern+"' + '%' "
					  +"GROUP BY RPT_TITLE ";
			try (Connection connection = ConnectionMSSQL.getInstanceConneectionJDBC();
					Statement statement = connection.createStatement();
							ResultSet rs = statement.executeQuery(sql_)) {	
				while(rs.next()) {
					RPT_TITLE = rs.getString(1);
					CATEGORY_ID = rs.getInt(2);
					result.put(RPT_TITLE, CATEGORY_ID);				
				}
			}
			
			LOg.logToFile_SQL(sql_);
		}
		return result;
	}
	
	private static String getString_updatePBSRPT_REPORTS( String schema ,String nameReport, Integer categoryId, String newRPT_ID, Integer newCategoryId, String newNameReport, String newNameFileReport, Boolean rptActive ) throws Exception {
		
		String RPT_ID = getRPT_ID(nameReport, categoryId);
		//
		String setRPT_ID, setCategoryId, setNameReport, setNameFileReport,setRptActive = null;
		if (newRPT_ID != null) setRPT_ID = ",RPT_ID = '"+newRPT_ID+"' ";
		else setRPT_ID = "";
		//
		if (newCategoryId != null) setCategoryId = ",CATEGORY_ID = '"+newCategoryId+"' ";
		else setCategoryId = "";
		//
		if (newNameReport != null) setNameReport = ",RPT_TITLE = N'"+newNameReport+"' ";
		else setNameReport = "";
		//  
		if (newNameFileReport != null) setNameFileReport = ",BIRTRPT_URL = '/frameset?__report=report/"+newNameFileReport+".rptdesign' ";
		else setNameFileReport = "";
		//
		if (rptActive != null) {
			String YN = rptActive ? "Y" : "N";
			setRptActive = ",RPT_ACTIVE = '"+YN+"' ";
		} 
		else setRptActive = "";
		String temp = (setRPT_ID + setCategoryId + setNameReport + setNameFileReport+setRptActive);
		String setFildsReport = temp.substring(1, temp.length());
		
		String updatePBSRPT_REPORTS = "USE [SCPRD] "
				  +"UPDATE [SCPRD].["+schema+"].[PBSRPT_REPORTS] " 
				  +"SET "
				  +setFildsReport
				  +",EDITWHO = N'add_rep' "
				  +",EDITDATE = getutcdate() "
				  +"WHERE RPT_ID = '"+RPT_ID+"'";
		
		return updatePBSRPT_REPORTS;
		
	}
	
	private static String getString_updateTRANSLATIONLIST(String schema, String RPT_ID, String newRPT_ID,String newNameReport) {
		String setJOINKEY12345, setDescription = null;
		if (newRPT_ID != null) setJOINKEY12345 = ",JOINKEY1 = '"+newRPT_ID+"' ,JOINKEY2 = '"+newRPT_ID+"' ,JOINKEY3 = '"+newRPT_ID+"' ,JOINKEY4 = '"+newRPT_ID+"' ,JOINKEY5 = '"+newRPT_ID+"' ,CODE = '"+newRPT_ID+"' ";                                                   
		else setJOINKEY12345 = "";
		//
		if (newNameReport != null) setDescription = ",[DESCRIPTION] = N'"+newNameReport+"'"; 
		else setDescription = "";
		String temp2 = (setJOINKEY12345 + setDescription);
		String setFildsTrans = temp2.substring(1, temp2.length());
		
		  String updateTRANSLATIONLIST = "USE [SCPRD] "
									   +"UPDATE [SCPRD].["+schema+"].[TRANSLATIONLIST] "
				  					   +"SET "+setFildsTrans
				  					   +",EDITWHO = N'add_rep' "
									   +",EDITDATE = getutcdate() "
				  					   +"WHERE JOINKEY1 = '"+RPT_ID+"' "
			  					   	     +"AND JOINKEY2 = '"+RPT_ID+"' "
			  					   	     +"AND JOINKEY3 = '"+RPT_ID+"' "
			  					   	     +"AND JOINKEY4 = '"+RPT_ID+"' "
			  					   	     +"AND JOINKEY5 = '"+RPT_ID+"' "
			  					   	     +"AND TBLNAME = 'PBSRPT_REPORTS' AND LOCALE = 'ru' AND COLUMNNAME = 'RPT_TITLE'";
		return updateTRANSLATIONLIST;
	}
}
