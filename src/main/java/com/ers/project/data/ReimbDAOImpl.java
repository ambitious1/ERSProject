/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ers.project.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.ers.project.beans.ReimbBean;

/**
 *
 * @author ambitious-1
 */
public class ReimbDAOImpl implements ReimbDAO {
        
    
    public ReimbDAOImpl(Connection conn) {
    	super();
    }

    
     public int convertValue(Connection conn, String word, String fieldType) throws ClassNotFoundException, SQLException{
    	//conn = ConnHelper.getConnection();
        String rsfield = word;
        String sql="";
        int id = 0;
        
       switch (fieldType) {
            case "name":
                
                sql = "SELECT ERS_USERS_ID" +
                        " FROM ERS_USERS where (UPPER(USER_FIRST_NAME) LIKE UPPER('%"+ rsfield+"%')) OR" +
                        " (UPPER(USER_LAST_NAME) LIKE UPPER('%"+ rsfield +"%')) OR (CONCAT(CONCAT(user_first_name,' '), "
                         + "user_last_name) LIKE ('%"+ rsfield +"%'))";
                
            try{
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                ResultSet rs = preparedStatement.executeQuery(); 
                while (rs.next()){
                   id = rs.getInt("ERS_USERS_ID");   
                }
             
            rs.close();
             preparedStatement.close();
             //conn.close();
             
            }catch(SQLException e){ e.printStackTrace();}
            
            break;
            
            
            case "type":
                sql = "SELECT REIMB_TYPE_ID" +
                        " FROM ERS_REIMBURSTMENT_TYPE where (UPPER(REIMB_TYPE) LIKE UPPER('%"+rsfield+"%'))";
                try{
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                ResultSet rs = preparedStatement.executeQuery(); 
                while (rs.next()){
                   id = rs.getInt("REIMB_TYPE_ID");   
                }
             
            rs.close();
             preparedStatement.close();
             //conn.close();
             
            }catch(SQLException e){ e.printStackTrace();}
            
            break;
                
            
            case "status":    
                sql = "SELECT REIMB_STATUS_ID" +
                        " FROM ERS_REIMBURSTMENT_STATUS where (UPPER(REIMB_STATUS) LIKE UPPER('%"+rsfield+"%'))";
                try{
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                ResultSet rs = preparedStatement.executeQuery(); 
                while (rs.next()){
                   id = rs.getInt("REIMB_STATUS_ID");   
                }
             
            rs.close();
             preparedStatement.close();
             //conn.close();
             
            }catch(SQLException e){ e.printStackTrace();}
            
            break;  
     
            
            case "full":
        
                 sql = "SELECT ERS_USERS_ID FROM ERS_USERS where "
                         + "(CONCAT(CONCAT(user_first_name,' '), "
                         + "user_last_name) LIKE ('%"+ rsfield +"%'))";
        
             try{
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                ResultSet rs = preparedStatement.executeQuery(); 
                while (rs.next()){
                   id = rs.getInt("ERS_USERS_ID");   
                }
             
            rs.close();
             preparedStatement.close();
             //conn.close();
             
            }catch(SQLException e){ 
                e.printStackTrace();
            }
            
            break;
       }
       
       return id;
    }

    @Override
    public void updateReimb(Connection conn, ReimbBean reimb) {
        
        String query = "update ERS_REIMBURSTMENT set REIMB_STATUS_ID = ?, REIMB_RESOLVER=?, REIMB_RESOLVED=? "
                + "where REIMB_STATUS_ID=2 and REIMB_ID=?";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query);
           
           Calendar calendar = Calendar.getInstance();
            java.sql.Timestamp ourJavaTimestampObject = new java.sql.Timestamp(calendar.getTime().getTime());         
           
           preparedStatement.setInt( 1, reimb.getReimbStatusID());
           preparedStatement.setInt( 2, reimb.getReimbResolver());
           preparedStatement.setTimestamp( 3, ourJavaTimestampObject);
           preparedStatement.setInt( 4, reimb.getReimbID());
           
           preparedStatement.executeUpdate();
           preparedStatement.close();
           //conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    @Override
    public void insertReimb(Connection conn, ReimbBean reimb) throws SQLException{
        String sql = "INSERT INTO ERS_REIMBURSTMENT(REIMB_AMOUNT, REIMB_SUBMITTED, "
                + "REIMB_DESCRIPTION, REIMB_AUTHOR, REIMB_TYPE_ID) VALUES (?, ?, ?, ?, ?)";
        
         PreparedStatement preparedStatement = conn.prepareStatement(sql);
           
           Calendar calendar = Calendar.getInstance();
            java.sql.Timestamp ourJavaTimestampObject = new java.sql.Timestamp(calendar.getTime().getTime()); 
            
           preparedStatement.setDouble(1, reimb.getReimbAmount());
           preparedStatement.setTimestamp( 2, ourJavaTimestampObject);
           preparedStatement.setString( 3, reimb.getReimbDescript());
           preparedStatement.setInt(4, reimb.getReimbAuthor());
           preparedStatement.setInt(5, reimb.getReimbTypeID());
           
           preparedStatement.executeUpdate();
           preparedStatement.close();
           //conn.close();
    }
    
    
    @Override
    public ReimbBean getReimbByID(Connection conn, int reimbID){
      String sql = "select reim.reimb_id, reim.reimb_amount,"
           + "reim.reimb_submitted, reim.reimb_resolved, "
           + "CONCAT(CONCAT(u.user_first_name,' '),"
           + " u.user_last_name) AS \"REIMB_AUTHOR\", "
           + "reim.REIMB_DESCRIPTION, rst.REIMB_STATUS AS \"REIMB_STATUS\", "
           + "rtype.REIMB_TYPE AS \"REIMB_TYPE\", CONCAT(CONCAT(urs.user_first_name,' '), "
           + "urs.user_last_name) AS \"REIMB_RESOLVER\" " 
           + "FROM ERS_REIMBURSTMENT reim " 
           + "LEFT JOIN ERS_USERS u " 
           + "ON u.ERS_USERS_ID = reim.REIMB_AUTHOR " 
           + "LEFT JOIN ERS_REIMBURSTMENT_STATUS rst " 
           + "ON rst.REIMB_STATUS_ID = reim.REIMB_STATUS_ID " 
           + "LEFT JOIN ERS_REIMBURSTMENT_TYPE rtype " 
           + "ON rtype.reimb_type_id = reim.REIMB_TYPE_ID " 
           + "LEFT JOIN ERS_USERS urs " 
           + "ON urs.ERS_USERS_ID = reim.REIMB_RESOLVER " 
           + "WHERE REIMB_ID = ?";
      ReimbBean reimb = new ReimbBean(); 
      
      try {
           PreparedStatement preparedStatement = conn.prepareStatement(sql);
                  preparedStatement.setInt(1, reimbID);
                ResultSet rs = preparedStatement.executeQuery(); 
                
            while(rs.next()){
                
                reimb.setReimbID(rs.getInt("reimb_ID"));
                reimb.setReimbAmount(rs.getDouble("reimb_Amount"));
                reimb.setReimbDescript(rs.getString("reimb_Description"));
                reimb.setReimbSubmitted(rs.getTimestamp("reimb_Submitted"));
                reimb.setReimbAuthor(convertValue(conn, rs.getString("reimb_Author"),"full"));
                reimb.setReimbResolved(rs.getTimestamp("reimb_Resolved"));
                reimb.setReimbResolver(convertValue(conn, rs.getString("reimb_Resolver"),"full"));
                reimb.setReimbStatusID(convertValue(conn, rs.getString("REIMB_STATUS"), "status"));
                reimb.setReimbTypeID(convertValue(conn, rs.getString("REIMB_TYPE"), "type"));
            }   
             rs.close();
             preparedStatement.close();
             //conn4.close();
               
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ReimbDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
      
      return reimb;
    }
    

        @Override
    public List<ReimbBean> getReimbByAuth(Connection conn, String name){
    String sql = "select reim.reimb_id, reim.reimb_amount,"
           + "reim.reimb_submitted, reim.reimb_resolved, "
           + "CONCAT(CONCAT(u.user_first_name,' '),"
           + " u.user_last_name) AS \"REIMB_AUTHOR\", "
           + "reim.REIMB_DESCRIPTION, rst.REIMB_STATUS AS \"REIMB_STATUS\", "
           + "rtype.REIMB_TYPE AS \"REIMB_TYPE\", CONCAT(CONCAT(urs.user_first_name,' '), "
           + "urs.user_last_name) AS \"REIMB_RESOLVER\" " 
           + "FROM ERS_REIMBURSTMENT reim " 
           + "LEFT JOIN ERS_USERS u " 
           + "ON u.ERS_USERS_ID = reim.REIMB_AUTHOR " 
           + "LEFT JOIN ERS_REIMBURSTMENT_STATUS rst " 
           + "ON rst.REIMB_STATUS_ID = reim.REIMB_STATUS_ID " 
           + "LEFT JOIN ERS_REIMBURSTMENT_TYPE rtype " 
           + "ON rtype.reimb_type_id = reim.REIMB_TYPE_ID " 
           + "LEFT JOIN ERS_USERS urs " 
           + "ON urs.ERS_USERS_ID = reim.REIMB_RESOLVER " 
           + "WHERE REIMB_AUTHOR LIKE ?"
           + "ORDER BY reim.reimb_id ";
      List<ReimbBean> reimbList = new ArrayList<>();  
      ReimbBean reimb = new ReimbBean();
      try {
           PreparedStatement preparedStatement = conn.prepareStatement(sql);
                  preparedStatement.setString(1, "%"+name+"%");
                ResultSet rs = preparedStatement.executeQuery(); 
                
            while(rs.next()){
                
                reimb.setReimbID(rs.getInt("reimb_ID"));
                reimb.setReimbAmount(rs.getDouble("reimb_Amount"));
                reimb.setReimbDescript(rs.getString("reimb_Description"));
                reimb.setReimbSubmitted(rs.getTimestamp("reimb_Submitted"));
                reimb.setReimbAuthor(convertValue(conn, rs.getString("reimb_Author"),"full"));
                reimb.setReimbResolved(rs.getTimestamp("reimb_Resolved"));
                reimb.setReimbResolver(convertValue(conn, rs.getString("reimb_Resolver"),"full"));
                reimb.setReimbStatusID(convertValue(conn, rs.getString("REIMB_STATUS"), "status"));
                reimb.setReimbTypeID(convertValue(conn, rs.getString("REIMB_TYPE"), "type"));
                
                reimbList.add(reimb);
            }   
                
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
      
      return reimbList;
    }
   
    @Override
    public List<ReimbBean> getAllReimb(Connection conn) {
  String sql = "select reim.reimb_id, reim.reimb_amount,"
           + "reim.reimb_submitted, reim.reimb_resolved, "
           + "CONCAT(CONCAT(u.user_first_name,' '),"
           + " u.user_last_name) AS \"REIMB_AUTHOR\", "
           + "reim.REIMB_DESCRIPTION, rst.REIMB_STATUS AS \"REIMB_STATUS\", "
           + "rtype.REIMB_TYPE AS \"REIMB_TYPE\", CONCAT(CONCAT(urs.user_first_name,' '), "
           + "urs.user_last_name) AS \"REIMB_RESOLVER\" " 
           + "FROM ERS_REIMBURSTMENT reim " 
           + "LEFT JOIN ERS_USERS u " 
           + "ON u.ERS_USERS_ID = reim.REIMB_AUTHOR " 
           + "LEFT JOIN ERS_REIMBURSTMENT_STATUS rst " 
           + "ON rst.REIMB_STATUS_ID = reim.REIMB_STATUS_ID " 
           + "LEFT JOIN ERS_REIMBURSTMENT_TYPE rtype " 
           + "ON rtype.reimb_type_id = reim.REIMB_TYPE_ID " 
           + "LEFT JOIN ERS_USERS urs " 
           + "ON urs.ERS_USERS_ID = reim.REIMB_RESOLVER " 
           + "ORDER BY reim.reimb_id";
  
      List<ReimbBean> reimbAllList = new ArrayList<ReimbBean>();  
      ReimbBean reimb = new ReimbBean();
      
      try {
           PreparedStatement preparedStatement = conn.prepareStatement(sql);
           
                ResultSet rs = preparedStatement.executeQuery(); 
                
            while(rs.next()){
           
                reimb.setReimbID(rs.getInt("reimb_ID"));
                reimb.setReimbAmount(rs.getDouble("reimb_Amount"));
                reimb.setReimbDescript(rs.getString("reimb_Description"));
                reimb.setReimbSubmitted(rs.getTimestamp("reimb_Submitted"));
                reimb.setReimbAuthor(convertValue(conn, rs.getString("reimb_Author"),"full"));
                reimb.setReimbResolved(rs.getTimestamp("reimb_Resolved"));
                reimb.setReimbResolver(convertValue(conn, rs.getString("reimb_Resolver"),"full"));
                reimb.setReimbStatusID(convertValue(conn, rs.getString("REIMB_STATUS"), "status"));
                reimb.setReimbTypeID(convertValue(conn, rs.getString("REIMB_TYPE"), "type"));
                
                reimbAllList.add(reimb);
            }   
                
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      
      return reimbAllList;
    }

    @Override
    public List<ReimbBean> getAllReimbByStatus(Connection conn, String reimbStatus) {
    String sql = "select reim.reimb_id, reim.reimb_amount,"
           + "reim.reimb_submitted, reim.reimb_resolved, "
           + "CONCAT(CONCAT(u.user_first_name,' '),"
           + " u.user_last_name) AS \"REIMB_AUTHOR\", "
           + "reim.REIMB_DESCRIPTION, rst.REIMB_STATUS AS \"REIMB_STATUS\", "
           + "rtype.REIMB_TYPE AS \"REIMB_TYPE\", CONCAT(CONCAT(urs.user_first_name,' '), "
           + "urs.user_last_name) AS \"REIMB_RESOLVER\" " 
           + "FROM ERS_REIMBURSTMENT reim " 
           + "LEFT JOIN ERS_USERS u " 
           + "ON u.ERS_USERS_ID = reim.REIMB_AUTHOR " 
           + "LEFT JOIN ERS_REIMBURSTMENT_STATUS rst " 
           + "ON rst.REIMB_STATUS_ID = reim.REIMB_STATUS_ID " 
           + "LEFT JOIN ERS_REIMBURSTMENT_TYPE rtype " 
           + "ON rtype.reimb_type_id = reim.REIMB_TYPE_ID " 
           + "LEFT JOIN ERS_USERS urs " 
           + "ON urs.ERS_USERS_ID = reim.REIMB_RESOLVER " 
           + "WHERE REIMB_STATUS LIKE ?"
           + "ORDER BY reim.reimb_id";
      
      List<ReimbBean> reimbStatusList = new ArrayList<ReimbBean>();  
      ReimbBean reimb = new ReimbBean();
      
      try {
           PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1, "%"+reimbStatus+"%");
                ResultSet rs = preparedStatement.executeQuery(); 
                
            while(rs.next()){
           
                reimb.setReimbID(rs.getInt("reimb_ID"));
                reimb.setReimbAmount(rs.getDouble("reimb_Amount"));
                reimb.setReimbDescript(rs.getString("reimb_Description"));
                reimb.setReimbSubmitted(rs.getTimestamp("reimb_Submitted"));
                reimb.setReimbAuthor(convertValue(conn, rs.getString("reimb_Author"),"full"));
                reimb.setReimbResolved(rs.getTimestamp("reimb_Resolved"));
                reimb.setReimbResolver(convertValue(conn, rs.getString("reimb_Resolver"),"full"));
                reimb.setReimbStatusID(convertValue(conn, rs.getString("REIMB_STATUS"), "status"));
                reimb.setReimbTypeID(convertValue(conn, rs.getString("REIMB_TYPE"), "type"));
                
                reimbStatusList.add(reimb);
            }   
                
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
      
      return reimbStatusList;
    }

    @Override
    public List<ReimbBean> getReimbByType(Connection conn, String reimbType) {
      String sql = "select reim.reimb_id, reim.reimb_amount,"
           + "reim.reimb_submitted, reim.reimb_resolved, "
           + "CONCAT(CONCAT(u.user_first_name,' '),"
           + " u.user_last_name) AS \"REIMB_AUTHOR\", "
           + "reim.REIMB_DESCRIPTION, rst.REIMB_STATUS AS \"REIMB_STATUS\", "
           + "rtype.REIMB_TYPE AS \"REIMB_TYPE\", CONCAT(CONCAT(urs.user_first_name,' '), "
           + "urs.user_last_name) AS \"REIMB_RESOLVER\" " 
           + "FROM ERS_REIMBURSTMENT reim " 
           + "LEFT JOIN ERS_USERS u " 
           + "ON u.ERS_USERS_ID = reim.REIMB_AUTHOR " 
           + "LEFT JOIN ERS_REIMBURSTMENT_STATUS rst " 
           + "ON rst.REIMB_STATUS_ID = reim.REIMB_STATUS_ID " 
           + "LEFT JOIN ERS_REIMBURSTMENT_TYPE rtype " 
           + "ON rtype.reimb_type_id = reim.REIMB_TYPE_ID " 
           + "LEFT JOIN ERS_USERS urs " 
           + "ON urs.ERS_USERS_ID = reim.REIMB_RESOLVER "
           + "WHERE REIMB_TYPE LIKE ? " 
           + "ORDER BY reim.reimb_id";     
      
      List<ReimbBean> reimbTypeList = new ArrayList<ReimbBean>();  
      ReimbBean reimb = new ReimbBean();
      
      try {
           PreparedStatement preparedStatement = conn.prepareStatement(sql);
                  preparedStatement.setString(1, "?"+reimbType+"?");
                ResultSet rs = preparedStatement.executeQuery(); 
                
            while(rs.next()){
           
                reimb.setReimbID(rs.getInt("reimb_ID"));
                reimb.setReimbAmount(rs.getDouble("reimb_Amount"));
                reimb.setReimbDescript(rs.getString("reimb_Description"));
                reimb.setReimbSubmitted(rs.getTimestamp("reimb_Submitted"));
                reimb.setReimbAuthor(convertValue(conn, rs.getString("reimb_Author"),"full"));
                reimb.setReimbResolved(rs.getTimestamp("reimb_Resolved"));
                reimb.setReimbResolver(convertValue(conn, rs.getString("reimb_Resolver"),"full"));
                reimb.setReimbStatusID(convertValue(conn, rs.getString("REIMB_STATUS"), "status"));
                reimb.setReimbTypeID(convertValue(conn, rs.getString("REIMB_TYPE"), "type"));
                
                reimbTypeList.add(reimb);
            }   
                
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
      
      return reimbTypeList;
    }


        
    }