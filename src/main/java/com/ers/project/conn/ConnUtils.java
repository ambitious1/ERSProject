package com.ers.project.conn;

import java.io.*;
import java.sql.*;
import java.util.Properties;

public class ConnUtils {
		 
	  public static Connection getOracleConnection() throws ClassNotFoundException, SQLException {
		  
		  Connection conn = null;
	          try {
	                
	                Properties prop = new Properties();
	                InputStream inputStream = ConnUtils.class.getClassLoader().getResourceAsStream("db.properties");
	                prop.load(inputStream);
	                String driver = prop.getProperty("driver");
	                String url = prop.getProperty("url");
	                String user = prop.getProperty("user");
	                String password = prop.getProperty("password");
	                Class.forName(driver);
	                conn = DriverManager.getConnection(url, user, password);
	        } catch (ClassNotFoundException | SQLException | IOException e) {
	        }
	        return conn;
	        
	  }
	 
	  
	  public static Connection getOracleConnection(String hostName, String sid,
	          String userName, String password) throws ClassNotFoundException,
	          SQLException, IOException {
	 
		  Properties prop = new Properties();
          InputStream inputStream = ConnUtils.class.getClassLoader().getResourceAsStream("db.properties");
          prop.load(inputStream);
          String driver = prop.getProperty("driver");
          String url = "jdbc:oracle:thin@"+hostName+":1521:"+sid;
          
          Class.forName(driver);
          Connection conn = DriverManager.getConnection(url, userName, password);
		  
	           return conn;
	  }
	}