package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Vector;

import log.LOg;
import parce_rptdesign.ParamFromRptDesign;
import util.MyProperties;
import util.Params;
import windows.param.ParamFromParamsPanel;

public class ParamsRelatedData {
	
	private ParamsRelatedData() {}
	
	public static boolean isExistTableParams() throws SQLException, ClassNotFoundException {
		String schema = MyProperties.getProperty("schema"); 
		String sql = "USE [SCPRD] IF object_id ('["+schema+"].[PBSRPT_REPORTS_PARAMS]') IS NOT NULL SELECT 1 ELSE SELECT 0";
		try (Connection connection = ConnectionMSSQL.getInstanceConneectionJDBC();
				Statement statement = connection.createStatement();
						ResultSet rs = statement.executeQuery(sql)) {
				rs.next();
				Integer result = rs.getInt(1);
				LOg.logToFile_SQL(sql);
				return (result == 1) ? true : false; 
				
			}
	}
	/**
	 * 
	 * @param PARAM_TYPE - CONST 'Date' or 'Dropdown' or 'MultiSelect' or 'Number' or 'Text' or 'Boolean' or 'Time'
	 * @param PARAM_CONTENTS -  null acceptable
	 * @param PARAM_LABEL - Rus name
	 * @param PARAM_ISREQUIRED - '0'-false '1'-true
	 * */
	
	public static Vector<String> getListOfParamName(String RPT_ID) throws ClassNotFoundException, SQLException {
		String schema = MyProperties.getProperty("schema"); 
		Vector<String> resultVector = new Vector<String>();
		
		String sql = "USE [SCPRD] "
					  + "SELECT [PARAM_NAME] "
					  + "FROM ["+schema+"].[PBSRPT_REPORTS_PARAMS]  "
					  + "WHERE [RPT_ID] = '"+RPT_ID+"'";
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
	
	public static Vector<ParamFromDataBase> getListOfParam(String RPT_ID) throws ClassNotFoundException, SQLException {
		String schema = MyProperties.getProperty("schema"); 
		Vector<ParamFromDataBase> resultVector = new Vector<ParamFromDataBase>();
		
		String sql = "USE [SCPRD] "
					  + "SELECT SEQ_NO, PARAM_NAME, PARAM_LABEL, IS_REQUIRED, PARAM_TYPE, PARAM_CONTENTS ,PARAM_DEFAULT "
					  + "FROM ["+schema+"].[PBSRPT_REPORTS_PARAMS]  "
					  + "WHERE [RPT_ID] = '"+RPT_ID+"'";
		try (Connection connection = ConnectionMSSQL.getInstanceConneectionJDBC();
				Statement statement = connection.createStatement();
						ResultSet rs = statement.executeQuery(sql)) {
			
			while(rs.next()) {
				resultVector.add(new ParamFromDataBase(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)));
			}
		} 
		LOg.logToFile_SQL(sql);
		return resultVector;
	}
	
	public static void insertParam(List<? extends Params> listParams, String RPT_ID) throws Exception {
		if (listParams.size() == 0) return;
		
		String schema = MyProperties.getProperty("schema");
		String insertPBSRPT_REPORTS_PARAMS = getStringINSERT_REPORT_PARAMS(schema, RPT_ID, listParams);
		String insertPBSRPT_REPORTS_PARAMS_enterprise = getStringINSERT_REPORT_PARAMS("enterprise", RPT_ID, listParams);
		
		try (Connection connection = ConnectionMSSQL.getInstanceConneectionJDBC();
				Statement statement = connection.createStatement();
					PreparedStatement insertPar = connection.prepareStatement(insertPBSRPT_REPORTS_PARAMS);
					PreparedStatement insertPar_enterprise = connection.prepareStatement(insertPBSRPT_REPORTS_PARAMS_enterprise)) {
				
				connection.setAutoCommit(false);	
				insertPar.execute();
				insertPar_enterprise.execute();
				connection.commit();
		}	
		LOg.logToFile_SQL(insertPBSRPT_REPORTS_PARAMS);		
	}
	
	public static void deleteParam(List<? extends Params> listParams, String RPT_ID) throws Exception {
		String schema = MyProperties.getProperty("schema");
		
		String deletePBSRPT_REPORTS_PARAMS = getString_deletePBSRPT_REPORTS_PARAMS(schema, RPT_ID);
		String deletePBSRPT_REPORTS_PARAMS_enterprise = getString_deletePBSRPT_REPORTS_PARAMS("enterprise", RPT_ID);

		try (Connection connection = ConnectionMSSQL.getInstanceConneectionJDBC();
				PreparedStatement delPar = connection.prepareStatement(deletePBSRPT_REPORTS_PARAMS);
				PreparedStatement delPar_enterprise = connection.prepareStatement(deletePBSRPT_REPORTS_PARAMS_enterprise);) {
			
			connection.setAutoCommit(false);
			for (Params p : listParams) {
				delPar.setString(1, p.getPARAM_NAME());
				delPar_enterprise.setString(1, p.getPARAM_NAME());
				delPar.addBatch();
				delPar_enterprise.addBatch();
			}
			delPar.executeBatch();
			delPar_enterprise.executeBatch();
			connection.commit();
		} 
		LOg.logToFile_SQL(deletePBSRPT_REPORTS_PARAMS);		
	}
	
	private static String getString_deletePBSRPT_REPORTS_PARAMS(String schema, String RPT_ID) {
		String deletePBSRPT_REPORTS_PARAMS = "USE [SCPRD] DELETE FROM ["+schema+"].[PBSRPT_REPORTS_PARAMS] WHERE RPT_ID = '"+RPT_ID+"'" + " AND PARAM_NAME = ? ";
		return deletePBSRPT_REPORTS_PARAMS;
	}
	
	public static void deleteParam(String RPT_ID) throws Exception {
		String schema = MyProperties.getProperty("schema");
		
		String deleteALL_PBSRPT_REPORTS_PARAMS = "USE [SCPRD] DELETE FROM ["+schema+"].[PBSRPT_REPORTS_PARAMS] " + 
				  "WHERE RPT_ID = '"+RPT_ID+"'";
		try (Connection connection = ConnectionMSSQL.getInstanceConneectionJDBC();
				PreparedStatement delPar = connection.prepareStatement(deleteALL_PBSRPT_REPORTS_PARAMS)) {
			
			delPar.execute();
		}
		LOg.logToFile_SQL(deleteALL_PBSRPT_REPORTS_PARAMS);		
	}
	
	public static void updateParam(List<? extends Params> listParams, String RPT_ID) throws Exception {
		String schema = MyProperties.getProperty("schema");		
		
		String deleteALL_PBSRPT_REPORTS_PARAMS = getString_deleteALL_PBSRPT_REPORTS_PARAMS(schema, RPT_ID);
		String deleteALL_PBSRPT_REPORTS_PARAMS_enterprise = getString_deleteALL_PBSRPT_REPORTS_PARAMS("enterprise", RPT_ID);
		String deleteAllTRANSLATIONLIST_PARAMS = getString_deleteAllTRANSLATIONLIST_PARAMS(schema, RPT_ID);
		String deleteAllTRANSLATIONLIST_PARAMS_enterprise = getString_deleteAllTRANSLATIONLIST_PARAMS("enterprise", RPT_ID);
		
		String insertPBSRPT_REPORTS_PARAMS = null;
		String insertPBSRPT_REPORTS_PARAMS_enterprise = null;
		String insertTRANSLATIONLIST_PARAMS = null;
		String insertTRANSLATIONLIST_PARAMS_enterprise =null;
		
		if (listParams.size() != 0) {
			insertPBSRPT_REPORTS_PARAMS = getStringINSERT_REPORT_PARAMS(schema, RPT_ID, listParams);	
			insertPBSRPT_REPORTS_PARAMS_enterprise = getStringINSERT_REPORT_PARAMS("enterprise", RPT_ID, listParams);
			
			insertTRANSLATIONLIST_PARAMS = getStringINSERT_TRANSLATIONLIST_PARAMS(schema, RPT_ID);			
			insertTRANSLATIONLIST_PARAMS_enterprise = getStringINSERT_TRANSLATIONLIST_PARAMS("enterprise", RPT_ID);
					
			try (Connection connection = ConnectionMSSQL.getInstanceConneectionJDBC();
					PreparedStatement deleteAllPar = connection.prepareStatement(deleteALL_PBSRPT_REPORTS_PARAMS);
					PreparedStatement deleteAllPar_enterprise = connection.prepareStatement(deleteALL_PBSRPT_REPORTS_PARAMS_enterprise);
					PreparedStatement deleteAllParTranslation = connection.prepareStatement(deleteAllTRANSLATIONLIST_PARAMS);
					PreparedStatement deleteAllParTranslation_enterprise = connection.prepareStatement(deleteAllTRANSLATIONLIST_PARAMS_enterprise);
					
					PreparedStatement insertPar_enterprise = connection.prepareStatement(insertPBSRPT_REPORTS_PARAMS_enterprise);		
					PreparedStatement insertPar = connection.prepareStatement(insertPBSRPT_REPORTS_PARAMS);
					PreparedStatement insertParTranslation = connection.prepareStatement(insertTRANSLATIONLIST_PARAMS);
					PreparedStatement insertParTranslation_enterprise = connection.prepareStatement(insertTRANSLATIONLIST_PARAMS_enterprise);
					) {
				
				connection.setAutoCommit(false);
				
				deleteAllPar.execute();
				deleteAllPar_enterprise.execute();
				deleteAllParTranslation.execute();
				deleteAllParTranslation_enterprise.execute();
				
				
				insertPar.execute();
				insertPar_enterprise.execute();
				
				for (Params p : listParams) {
					insertParTranslation.setString(1, p.getPARAM_NAME());
					insertParTranslation.setString(2, p.getPARAM_NAME());
					insertParTranslation.setString(3, p.getPARAM_NAME());
					insertParTranslation.setString(4, p.getPARAM_NAME());
					insertParTranslation.setString(5, p.getPARAM_NAME());
					insertParTranslation.setString(6, p.getPARAM_LABEL());
					
					insertParTranslation_enterprise.setString(1, p.getPARAM_NAME());
					insertParTranslation_enterprise.setString(2, p.getPARAM_NAME());
					insertParTranslation_enterprise.setString(3, p.getPARAM_NAME());
					insertParTranslation_enterprise.setString(4, p.getPARAM_NAME());
					insertParTranslation_enterprise.setString(5, p.getPARAM_NAME());
					insertParTranslation_enterprise.setString(6, p.getPARAM_LABEL());
					
					insertParTranslation.addBatch();
					insertParTranslation_enterprise.addBatch();
				}
				insertParTranslation.executeBatch();
				insertParTranslation_enterprise.executeBatch();
				
				connection.commit();
			}
		} else {
			try (Connection connection = ConnectionMSSQL.getInstanceConneectionJDBC();
					PreparedStatement delPar = connection.prepareStatement(deleteALL_PBSRPT_REPORTS_PARAMS);
					PreparedStatement delPar_enterprise = connection.prepareStatement(deleteALL_PBSRPT_REPORTS_PARAMS);) {
				
				connection.setAutoCommit(false);
				delPar.execute();
				delPar_enterprise.execute();
				connection.commit();
			}
		}
		
		LOg.logToFile_SQL(deleteALL_PBSRPT_REPORTS_PARAMS + "\r\n" + insertPBSRPT_REPORTS_PARAMS);	
	}
	private static String getStringINSERT_TRANSLATIONLIST_PARAMS(String schema, String rPT_ID) {
			
			String RPT_ID = rPT_ID;
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
					"           'PBSRPT_REPORTS_PARAMS'," + 
					"           'ru'," + 
					"           '"+RPT_ID+"'," + 
					"           ?," + 
					"           ?," + 
					"           ?," + 
					"           ?," + 
					"           'PARAM_LABEL'," + 
					"           ?," + 
					"           ?," + 
					"	        N'add_rep'," +
					"	        N'add_rep'" +
					"		   )";
			
			return insertTRANSLATIONLIST;
	}

	private static String getString_deleteALL_PBSRPT_REPORTS_PARAMS(String schema,String RPT_ID) {
		String deleteALL_PBSRPT_REPORTS_PARAMS = "USE [SCPRD] DELETE FROM ["+schema+"].[PBSRPT_REPORTS_PARAMS] WHERE RPT_ID = '"+RPT_ID+"' ";
		return deleteALL_PBSRPT_REPORTS_PARAMS;
	}
	
	private static String getString_deleteAllTRANSLATIONLIST_PARAMS(String schema,String RPT_ID) {
		String deleteTRANSLATIONLIST_PARAMS = 
				"USE [SCPRD] DELETE FROM ["+schema+"].[TRANSLATIONLIST] " +  
				    "WHERE JOINKEY1 = '"+RPT_ID+"' AND TBLNAME = 'PBSRPT_REPORTS_PARAMS' AND LOCALE = 'ru' AND COLUMNNAME = 'PARAM_LABEL'";
		return deleteTRANSLATIONLIST_PARAMS;
	}

	private static String getStringINSERT_REPORT_PARAMS(String schema,String RPT_ID, List<? extends Params> listParams) {		
	
		 String PARAM_SEQNO       = null;
		 String PARAM_NAME   	  = null;
		 String PARAM_LABEL  	  = null;
		 String PARAM_TYPE   	  = null;
		 String PARAM_CONTENTS    = null;
		 String PARAM_CONTENTS_TYPE = "NULL";
		 String PARAM_ISREQUIRED  = null;
		 String PARAM_DEFAULT     = null;
		
		String insertPBSRPT_REPORTS_PARAMS = ""
				+ "USE [SCPRD] "
				+ "INSERT INTO ["+schema+"].[PBSRPT_REPORTS_PARAMS](" + 
				" [RPT_ID] "+
		        ",[PARAM_NAME] "+
		        ",[PARAM_TYPE] "+
		        ",[PARAM_LABEL] "+
		        ",[PARAM_CONTENTS] "+
		        ",[SQL_PARAMS] "+
		        ",[SQL_SCHEMA] "+
		        ",[PARAM_CONTENTS_TYPE] "+
		        ",[PARAM_DEFAULT] "+
		        ",[SEQ_NO] "+
		        ",[PARAM_GROUP] "+
		        ",[PARAM_GROUP_LABEL] "+
		        ",[IS_REQUIRED] "+
				",[ADDWHO] " +
				",[EDITWHO] " +
				
		        ") "+
		        "VALUES ";
		StringBuilder values = new StringBuilder();
		
			for(int i = 0; listParams.size() > i; i++) {
				PARAM_SEQNO  = listParams.get(i).getPARAM_SEQNO();
				PARAM_NAME 	 = listParams.get(i).getPARAM_NAME();
			    PARAM_LABEL	 = listParams.get(i).getPARAM_LABEL();
			    PARAM_TYPE 	 = listParams.get(i).getPARAM_TYPE();
			    PARAM_CONTENTS = listParams.get(i).getPARAM_CONTENTS();
			    PARAM_ISREQUIRED = listParams.get(i).getPARAM_ISREQUIRED();
			    PARAM_DEFAULT = listParams.get(i).getPARAM_DEFAULT();
			    
			  if(PARAM_DEFAULT == null)  PARAM_DEFAULT = "NULL";
			  else PARAM_DEFAULT="'"+PARAM_DEFAULT+"'";
			  if(PARAM_CONTENTS == null) {
				  PARAM_CONTENTS = "NULL";
				  PARAM_CONTENTS_TYPE = "NULL";
			  }
			  else {
				  PARAM_CONTENTS_TYPE = "'SQL'";
			 }
		     values.append("("+
		        "  '"+RPT_ID+"',         "+
		        "  N'"+PARAM_NAME+"',     "+
		        "   '"+PARAM_TYPE+"',    "+
		        "  N'"+PARAM_LABEL+"',   "+
		        "   "+PARAM_CONTENTS+",  "+
		        "   '',                  "+
		        "   NULL,                "+
		        "   "+PARAM_CONTENTS_TYPE+","+
		        "   "+PARAM_DEFAULT+",   "+
		        "   "+PARAM_SEQNO+",     "+
		        "   NULL,                "+
		        "   NULL,                "+
		        "   '"+PARAM_ISREQUIRED+"',  "+
				"	N'add_rep',  " +
				"	N'add_rep'" +
				"),"//+"\n"
		        );
			}
			values.deleteCharAt(values.length()-1);	
		return insertPBSRPT_REPORTS_PARAMS + values.toString();
	}
}
