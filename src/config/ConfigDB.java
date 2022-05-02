package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConfigDB {
    
private static String URL = "jdbc:oracle:thin:@localhost:1521:xe";
private static String USERNAME ="system";
private static String PASSWORD = "123456";

public static Connection getConnection() throws SQLException{
    Connection connection = 
    DriverManager.getConnection(URL, USERNAME, PASSWORD);
    return connection;
}

}
