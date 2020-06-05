package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import util.MyProperties;

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
	 * @param PARAM_TYPE - CONST 'Date' or  'Dropdown' or 'MultiSelect' or 'Number' or 'Text'
	 * @param PARAM_CONTENTS -  null acceptable
	 * @param PARAM_LABEL - Rus name
	 * @return - Connection object return for commit transaction. -connection.commit();
	 * */
	public static Connection insertParam(String RPT_ID, String PARAM_NAME,String PARAM_LABEL, String PARAM_TYPE,String PARAM_CONTENTS ) throws ClassNotFoundException, SQLException {
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
			
		}
		return connection;
	}
	
	public static Vector<String> getListOfParamName(String RPT_ID) throws ClassNotFoundException, SQLException {
		String schema = MyProperties.getProperty("schema"); 
		Vector<String> resultVector = new Vector<String>();
		
		String sql = "USE [SCPRD] "
					  + "SELECT [PARAM_NAME] "
					  + "FROM ["+schema+"].[PBSRPT_REPORTS_PARAMS]  "
					  + "WHERE [RPT_ID] = "+RPT_ID;
		try (Connection connection = ConnectionMSSQL.getInstanceConneectionJDBC();
				Statement statement = connection.createStatement();
						ResultSet rs = statement.executeQuery(sql)) {
			while(rs.next()) {
				resultVector.add(rs.getString(1));
			}
		} 
		return resultVector;
	}
	

	

}
