package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Vector;

import util.MyProperties;
import util.Params;
import util.parce_rptdesign.ParamFromRptDesign;
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
				return (result == 1) ? true : false; 
			}
	}
	/**
	 * !!!Close object connection after commit.!!!
	 * 
	 * @param PARAM_TYPE - CONST 'Date' or 'Dropdown' or 'MultiSelect' or 'Number' or 'Text' or 'Boolean' or 'Time'
	 * @param PARAM_CONTENTS -  null acceptable
	 * @param PARAM_LABEL - Rus name
	 * @return - Connection object return for commit transaction. -connection.commit();
	 * */
	public static void insertParam(String RPT_ID, String PARAM_NAME,String PARAM_LABEL, String PARAM_TYPE,String PARAM_CONTENTS) throws ClassNotFoundException, SQLException {
		String schema = MyProperties.getProperty("schema");
		String PARAM_CONTENTS_TYPE = "NULL";
		if(PARAM_CONTENTS == null) PARAM_CONTENTS = "NULL";
		else {
			PARAM_CONTENTS_TYPE = "'SQL'";
			PARAM_CONTENTS = "'" +PARAM_CONTENTS.replace("'", "''")+ "'";
		}
		
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
		        ") "+
		        "VALUES("+
		        "  '"+RPT_ID+"',         "+
		        "  '"+PARAM_NAME+"',     "+
		        "   '"+PARAM_TYPE+"',    "+
		        "   '"+PARAM_LABEL+"',   "+
		        "   "+PARAM_CONTENTS+",  "+
		        "   '',                  "+
		        "   NULL,                "+
		        "   "+PARAM_CONTENTS_TYPE+","+
		        "   NULL,                "+
		        "   1,                   "+
		        "   NULL,                "+
		        "   NULL,                "+
		        "   '1'                  "+
				")";
		Connection connection = ConnectionMSSQL.getInstanceConneectionJDBC();
		try (Statement statement = connection.createStatement();
				PreparedStatement insertPar = connection.prepareStatement(insertPBSRPT_REPORTS_PARAMS)) {
			
			connection.setAutoCommit(false);	
			insertPar.execute();
			connection.commit();
			
		}
		
	}
	
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
		return resultVector;
	}
	public static Vector<ParamFromDataBase> getListOfParam(String RPT_ID) throws ClassNotFoundException, SQLException {
		String schema = MyProperties.getProperty("schema"); 
		Vector<ParamFromDataBase> resultVector = new Vector<ParamFromDataBase>();
		
		String sql = "USE [SCPRD] "
					  + "SELECT PARAM_NAME, PARAM_LABEL, PARAM_TYPE, PARAM_CONTENTS "
					  + "FROM ["+schema+"].[PBSRPT_REPORTS_PARAMS]  "
					  + "WHERE [RPT_ID] = '"+RPT_ID+"'";
		try (Connection connection = ConnectionMSSQL.getInstanceConneectionJDBC();
				Statement statement = connection.createStatement();
						ResultSet rs = statement.executeQuery(sql)) {
			
			while(rs.next()) {
				resultVector.add(new ParamFromDataBase(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
			}
		} 
		return resultVector;
	}
	
	public static void insertParam(List<? extends Params> listParams, String RPT_ID) throws Exception {
		
		String schema = MyProperties.getProperty("schema");
		String PARAM_CONTENTS_TYPE = "NULL";
		
		 String PARAM_NAME   	= null;
		 String PARAM_LABEL  	= null;
		 String PARAM_TYPE   	= null;
		 String PARAM_CONTENTS  = null;
			
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
			        ") "+
			        "VALUES ";
			StringBuilder values = new StringBuilder();
			
				for(int i = 0; listParams.size() > i; i++) {
					
					PARAM_NAME 	 = listParams.get(i).getPARAM_NAME();
				    PARAM_LABEL	 = listParams.get(i).getPARAM_LABEL();
				    PARAM_TYPE 	 = listParams.get(i).getPARAM_TYPE();
				    PARAM_CONTENTS = listParams.get(i).getPARAM_CONTENTS();
				
				  if(PARAM_CONTENTS == null) {
					  PARAM_CONTENTS = "NULL";
					  PARAM_CONTENTS_TYPE = "NULL";
				  }
				  else {
					  PARAM_CONTENTS_TYPE = "'SQL'";
					  PARAM_CONTENTS = "'" +PARAM_CONTENTS.replace("'", "''")+ "'";
				 }
			     values.append("("+
			        "  '"+RPT_ID+"',         "+
			        "  '"+PARAM_NAME+"',     "+
			        "   '"+PARAM_TYPE+"',    "+
			        "   '"+PARAM_LABEL+"',   "+
			        "   "+PARAM_CONTENTS+",  "+
			        "   '',                  "+
			        "   NULL,                "+
			        "   "+PARAM_CONTENTS_TYPE+","+
			        "   NULL,                "+
			        "   1,                   "+
			        "   NULL,                "+
			        "   NULL,                "+
			        "   '1'                  "+
					"),"
			        );
				}
				values.deleteCharAt(values.length()-1);	
				insertPBSRPT_REPORTS_PARAMS = insertPBSRPT_REPORTS_PARAMS + values.toString();
			
			
				try (Connection connection = ConnectionMSSQL.getInstanceConneectionJDBC();
						Statement statement = connection.createStatement();
						PreparedStatement insertPar = connection.prepareStatement(insertPBSRPT_REPORTS_PARAMS)) {
						
						connection.setAutoCommit(false);	
						insertPar.execute();
						connection.commit();
				}	
		    
	
	}
}
