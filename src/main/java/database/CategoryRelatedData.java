package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JOptionPane;

import log.LOg;
import util.CategoryAndId;
import util.MyProperties;
import windows.MainRunWindow;

public class CategoryRelatedData {
	
	private CategoryRelatedData() {}
	
	public static Vector<CategoryAndId> getCategories() throws SQLException, ClassNotFoundException {	
		String schema = MyProperties.getProperty("schema"); 
		Vector<CategoryAndId> resultVector = new Vector<CategoryAndId>();
				String sql = "USE [SCPRD] "
							  + "SELECT cat.CATEGORY_ID, "
							  + "(CASE"
							  + "	WHEN tr.DESCRIPTION IS NULL THEN cat.CATEGORY "
							  + "	ELSE tr.DESCRIPTION "
							  + "END)"
							  + "FROM ["+schema+"].PBSRPT_CATEGORY AS cat "
							  + "LEFT JOIN ["+schema+"].TRANSLATIONLIST AS tr "
							  + "ON CAST(cat.CATEGORY_ID AS nvarchar) = tr.JOINKEY1 "
							  + "AND CAST(cat.CATEGORY_ID AS nvarchar) = tr.JOINKEY2 "
							  + "AND CAST(cat.CATEGORY_ID AS nvarchar) = tr.JOINKEY3 "
							  + "AND CAST(cat.CATEGORY_ID AS nvarchar) = tr.JOINKEY4 "
							  + "AND CAST(cat.CATEGORY_ID AS nvarchar) = tr.JOINKEY5 AND tr.TBLNAME = 'PBSRPT_CATEGORY' AND tr.LOCALE = 'ru' AND tr.COLUMNNAME = 'CATEGORY' "
							  + "ORDER BY tr.DESCRIPTION";
		try (Connection connection = ConnectionMSSQL.getInstanceConneectionJDBC();
				Statement statement = connection.createStatement();
					ResultSet rs = statement.executeQuery(sql)) {
			
			while(rs.next()) {
				resultVector.add(new CategoryAndId(rs.getInt(1), rs.getString(2)));
			}
		} 
		LOg.logToFile_SQL(sql);
		return resultVector;
	}
	
	public static int getMaxCategory() throws ClassNotFoundException, SQLException {	
		String schema = MyProperties.getProperty("schema"); 
		int result = 0;
		   String sql = "USE [SCPRD] "
					  + "SELECT MAX(CATEGORY_ID) "
					  + "FROM ["+schema+"].PBSRPT_CATEGORY AS cat ";
		try (Connection connection = ConnectionMSSQL.getInstanceConneectionJDBC();
				Statement statement = connection.createStatement();
					ResultSet rs = statement.executeQuery(sql)) {
			
			while(rs.next()) {
				result = rs.getInt(1);
			}
		}
		LOg.logToFile_SQL(sql);
		return result;
	}
	public static Vector<String> getListOfCategoryNames() throws ClassNotFoundException, SQLException {
		String schema = MyProperties.getProperty("schema"); 
		Vector<String> resultVector = new Vector<String>();
		
		String sql = "USE [SCPRD] "
				  + "SELECT cat.CATEGORY, "
				  + "(CASE"
				  + "	WHEN tr.DESCRIPTION IS NULL THEN '-' "
				  + "	ELSE tr.DESCRIPTION "
				  + "END) "
				  + "FROM ["+schema+"].PBSRPT_CATEGORY AS cat "
				  + "LEFT JOIN ["+schema+"].TRANSLATIONLIST AS tr "
				  + "ON CAST(cat.CATEGORY_ID AS nvarchar) = tr.JOINKEY1 "
				  + "AND CAST(cat.CATEGORY_ID AS nvarchar) = tr.JOINKEY2 "
				  + "AND CAST(cat.CATEGORY_ID AS nvarchar) = tr.JOINKEY3 "
				  + "AND CAST(cat.CATEGORY_ID AS nvarchar) = tr.JOINKEY4 "
				  + "AND CAST(cat.CATEGORY_ID AS nvarchar) = tr.JOINKEY5 AND tr.TBLNAME = 'PBSRPT_CATEGORY' AND tr.LOCALE = 'ru' AND tr.COLUMNNAME = 'CATEGORY'";

		try (Connection connection = ConnectionMSSQL.getInstanceConneectionJDBC();
				Statement statement = connection.createStatement();
						ResultSet rs = statement.executeQuery(sql)) {
			while(rs.next()) {
				String cat = rs.getString(1);
				String descr = rs.getString(2);
				if (!resultVector.contains(cat)) resultVector.add(cat);
				if (!resultVector.contains(descr)) resultVector.add(descr);
			}
		} 
		LOg.logToFile_SQL(sql);
		return resultVector;
	}
	public static boolean insertCategory(String newCategory) throws ClassNotFoundException, SQLException {
		String schema = MyProperties.getProperty("schema");
		int CATEGORY_ID = getMaxCategory() + 1;
		String insertPBSRPT_CATEGORY = "USE [SCPRD] " +
				"INSERT INTO ["+schema+"].[PBSRPT_CATEGORY] (" + 
				"	[WHSEID], " + 
				"	[CATEGORY_ID], " + 
				"	[CATEGORY], " +
				"	[ADDWHO], " +
				"	[EDITWHO] " +
				") " + 
				"VALUES (" + 
				"	N'"+schema+"',  " + 
				"	"+CATEGORY_ID+",  " + 
				"	N'"+newCategory+"',  " + 
				"	N'add_rep',  " +
				"	N'add_rep'" +
				")";
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
				"			,[ADDWHO] " +
				"			,[EDITWHO] " +
				"           )" + 
				"     VALUES" + 
				"          (" + 
				"		   '"+schema+"'," + 
				"           'PBSRPT_CATEGORY'," + 
				"           'ru'," + 
				"           N'"+CATEGORY_ID+"',  " + 
				"           N'"+CATEGORY_ID+"',  " + 
				"           N'"+CATEGORY_ID+"',  " + 
				"           N'"+CATEGORY_ID+"',  " + 
				"           N'"+CATEGORY_ID+"',  " + 
				"           'CATEGORY',    " + 
				"           '"+CATEGORY_ID+"',  " + 
				"           N'"+newCategory+"',  " +
				"	        N'add_rep',  " +
				"	        N'add_rep'" +
				"		   )";
		
		try (Connection connection = ConnectionMSSQL.getInstanceConneectionJDBC();
				Statement statement = connection.createStatement();
				PreparedStatement insertCat = connection.prepareStatement(insertPBSRPT_CATEGORY);
				PreparedStatement insertTranslate = connection.prepareStatement(insertTRANSLATIONLIST)) {
			
			connection.setAutoCommit(false);	
			
			insertCat.execute();
			insertTranslate.execute();
			
			connection.commit();
			connection.setAutoCommit(true);
		} 
		LOg.logToFile_SQL(insertPBSRPT_CATEGORY + "\r\n " + insertTRANSLATIONLIST);
		return true;		
	}
	public static void updateCategory(String newNameCategory, int categoryId) throws Exception {
		String schema = MyProperties.getProperty("schema");
		
		String updatePBSRPT_CATEGORY = "USE [SCPRD] UPDATE ["+schema+"].[PBSRPT_CATEGORY] " + 
									  "SET "
									   +"CATEGORY = N'"+newNameCategory+"', "
									   +"EDITWHO = N'add_rep', "
									   +"EDITDATE = getutcdate() "
									   +"WHERE CATEGORY_ID = "+categoryId;
		
		String updateTRANSLATIONLIST = "USE [SCPRD] UPDATE ["+schema+"].[TRANSLATIONLIST] " + 
				  					   "SET "
				  					   +"[DESCRIPTION] = N'"+newNameCategory+"' "
				  					   +",EDITWHO = N'add_rep' "
									   +",EDITDATE = getutcdate() "
				  					   +"WHERE JOINKEY1 = N'"+categoryId+"' "
				  					   +"AND JOINKEY2 = N'"+categoryId+"' "
				  					   +"AND JOINKEY3 = N'"+categoryId+"' "
				  					   +"AND JOINKEY4 = N'"+categoryId+"' "
				  					   +"AND JOINKEY5 = N'"+categoryId+"' AND TBLNAME = 'PBSRPT_CATEGORY' AND LOCALE = 'ru' AND COLUMNNAME = 'CATEGORY'";
		
		try (Connection connection = ConnectionMSSQL.getInstanceConneectionJDBC();
				Statement statement = connection.createStatement();
				PreparedStatement updateRep = connection.prepareStatement(updatePBSRPT_CATEGORY);
				PreparedStatement updateTranslate = connection.prepareStatement(updateTRANSLATIONLIST)) {
			
			connection.setAutoCommit(false);	
			
			updateRep.execute();
			updateTranslate.execute();
			
			connection.commit();
			connection.setAutoCommit(true);
			
		}
		LOg.logToFile_SQL(updatePBSRPT_CATEGORY + "\r\n " + updateTRANSLATIONLIST);
	}
	
}
