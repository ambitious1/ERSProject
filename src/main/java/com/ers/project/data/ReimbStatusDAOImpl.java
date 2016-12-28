
package com.ers.project.data;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

/**
 *
 * @author ambitious-1
 */
public class ReimbStatusDAOImpl {
    private Connection conn;
    
    public ReimbStatusDAOImpl(Connection conn) {
		super();
		this.conn = conn;
    }
    
    public int getStatusIDByName(String reimbStatus) throws SQLException {
            int statusID; 
		CallableStatement stmt = conn
				.prepareCall("call GETSTATUSIDBYNAME(?, ?)");
		stmt.setString(1, reimbStatus); 
		stmt.registerOutParameter(2, Types.INTEGER);
		stmt.execute();
		statusID = stmt.getInt(2); 
		conn.close();
		return statusID;
    }
    
    
    
 
}
