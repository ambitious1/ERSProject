package com.ers.project.conn;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnHelper {

	   public static Connection getConnection()
	             throws ClassNotFoundException, SQLException {
	      
	       // Here I using Oracle Database.
	       return ConnUtils.getOracleConnection();
	        
	       // return MySQLConnUtils.getMySQLConnection();
	       // return SQLServerConnUtils_JTDS.getSQLServerConnection_JTDS();
	       // return SQLServerConnUtils_SQLJDBC.getSQLServerConnection_SQLJDBC();
	   }
	    
	   public static void closeQuietly(Connection conn1) {
	       try {
	           conn1.close();
	       } catch (Exception e) {
	       }
	   }
	 
	   public static void rollbackQuietly(Connection conn) {
	       try {
	           conn.rollback();
	       } catch (Exception e) {
	       }
	   }
	}