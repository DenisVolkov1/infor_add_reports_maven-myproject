package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import log.LOg;
import util.DialogWindows;
import util.MyProperties;
import windows.TabConnectionMSSQLServer;

public class ConnectionMSSQL {
	public static boolean isGoodLastsConnection = true;
	
private ConnectionMSSQL() {}	
	
public static Connection getInstanceConneectionJDBC() throws SQLException, ClassNotFoundException {
	
		Connection connection = null;
		try {
			String login = TabConnectionMSSQLServer.getLoginField().getText();
			char[] password = TabConnectionMSSQLServer.getPasswordField().getPassword();
			String connectionTo = MyProperties.getProperty("ipDataBase");
			String port = MyProperties.getProperty("portDataBase");
				 Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				 	connection = DriverManager.getConnection("jdbc:sqlserver://"+connectionTo+":"+port+";databaseName=;user="+login+";password="+String.valueOf(password));
				 		MyProperties.saveProperties("login", login, "password", String.valueOf(password));
		} catch (Exception e) {
				isGoodLastsConnection = false;
				throw e;
		}
		isGoodLastsConnection = connection != null ? true : false;
		return connection;
	}
}
