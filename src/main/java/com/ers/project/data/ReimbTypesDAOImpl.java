/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ers.project.data;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;

/**
 *
 * @author ambitious-1
 */
public class ReimbTypesDAOImpl {
    private Connection conn;
    
    public ReimbTypesDAOImpl(Connection conn) {
		super();
		this.conn = conn;
    }
    
    public int getTypeIDByTypeName(String rTypes) throws ClassNotFoundException, SQLException{
		
	int typeID; 
	CallableStatement stmt = conn
			.prepareCall("call GETTYPEIDBYTYPESNAME(?, ?)");
	stmt.setString(1, rTypes); // bind IN param
	stmt.registerOutParameter(2, Types.INTEGER);
	stmt.execute();
	typeID = stmt.getInt(2); // extract the OUT param
	conn.close();
        return typeID;
    }
       
    public String getTypeByTypeID(int id) throws ClassNotFoundException, SQLException{
		
	String typeName = ""; 
	CallableStatement stmt = conn
			.prepareCall("call GETTYPESBYTYPESID(?, ?)");
	stmt.setInt(1, id); // bind IN param
	stmt.registerOutParameter(2, Types.VARCHAR);
	stmt.execute();
	typeName = stmt.getString(2); // extract the OUT param
	conn.close();
	return typeName;
    }
}
