package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import util.MyProperties;
import windows.TabConnectionMSSQLServer;

public class ConnectionMSSQL {
	
private ConnectionMSSQL() {}	
	
public static Connection getInstanceConneectionJDBC() throws SQLException, ClassNotFoundException {
	
		String login = TabConnectionMSSQLServer.getLoginField().getText();
		char[] password = TabConnectionMSSQLServer.getPasswordField().getPassword();
		String connectionTo = MyProperties.getProperty("ipDataBase");
		Connection connection = null;
			 Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			 connection = DriverManager.getConnection("jdbc:sqlserver://"+connectionTo+":1433;databaseName=;user="+login+";password="+String.valueOf(password));
			 MyProperties.saveProperties("login", login, "password", String.valueOf(password));
		return connection;
	}

	

}
