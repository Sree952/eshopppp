package com.wipro.eshop.dao; 
 
import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.SQLException; 
import java.util.Properties; 
import java.io.InputStream; 
 
public class ConnectionHandler { 
    public static Connection getConnection() throws SQLException { 
        Properties props = new Properties(); 
        try (InputStream input = ConnectionHandler.class.getClassLoader() 
                .getResourceAsStream("connection.properties")) { 
            if (input == null) { 
                throw new SQLException("connection.properties file not found in 
classpath"); 
            } 
            props.load(input); 
        } catch (Exception e) { 
            throw new SQLException("Failed to load connection.properties: " + 
e.getMessage(), e); 
        } 
 
        String driver = props.getProperty("driver"); 
        String url = props.getProperty("url"); 
        String username = props.getProperty("user"); 
        String password = props.getProperty("password"); 
 
        if (driver == null || url == null || username == null || password == null) 
{ 
            throw new SQLException("Missing required properties in 
connection.properties"); 
        } 
 
        try { 
             
            Connection connection = DriverManager.getConnection(url, username, 
password); 
            if (connection == null) { 
                throw new SQLException("Failed to establish database connection"); 
            } 
            return connection; 
        } catch (SQLException e) { 
            throw new SQLException("Database connection error: " + e.getMessage(), 
e); 
        }
    }
}
        
