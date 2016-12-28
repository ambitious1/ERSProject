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
import java.util.List;

import com.ers.project.beans.UserBean;
import com.ers.project.conn.ConnHelper;

public class UserDAOImpl {
    private static Connection conn;
    
    public UserDAOImpl(Connection conn){
	super();
        this.conn=conn;
    }
	public static int convertUserValue(Connection conn, String word, String fieldType) throws ClassNotFoundException, SQLException {
		conn = ConnHelper.getConnection();
		String ufield = word;
		String sql = "";
		int id = 0;

		switch (fieldType) {
		case "role":

			sql = "SELECT ERS_USER_ROLES_ID" + " FROM ERS_USER_ROLE where (UPPER(ERS_USER_ROLE) LIKE UPPER('%" + ufield
					+ "%'))";

			try {
				PreparedStatement preparedStatement = conn.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				while (rs.next()) {
					id = rs.getInt("ERS_USER_ROLES_ID");
				}

				rs.close();
				preparedStatement.close();
				conn.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}

			break;

		case "fullToFirstName":

			String fname = "";
			sql = "SELECT REGEXP_SUBSTR('%" + ufield
					+ "%','[^ ]+',1,1) AS \"USER_FIRST_NAME\" from ERS_USERS WHERE ERS_USERS_ID>0";

			try {
				PreparedStatement preparedStatement = conn.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				while (rs.next()) {
					fname = rs.getString("USR_FIRST_NAME");
				}

				rs.close();
				preparedStatement.close();
				conn.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}

			break;

		case "FullTolastname":

			String lname = "";
			sql = "SELECT REGEXP_SUBSTR('%" + ufield + "%','[^ ]+',1,2)) AS \"USER_LAST_NAME\" LIKE USER_LAST_NAME";

			try {
				PreparedStatement preparedStatement = conn.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				while (rs.next()) {
					id = rs.getInt("USER_LAST_NAME");
				}

				rs.close();
				preparedStatement.close();
				conn.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}

			break;
		}

		return id;
	}

	public static List<UserBean> getAllUsers(Connection conn) throws SQLException, ClassNotFoundException {
		List<UserBean> usersList = new ArrayList<>();

                conn = ConnHelper.getConnection();
			String sql = "SELECT u.ers_users_id, " + "u.ers_username, " + "u.ers_password, "
					+ "CONCAT(CONCAT(user_first_name,' '), " + "user_last_name) AS full_Name, " + "u.user_email, "
					+ "rles.USER_ROLE " + "FROM ERS_USERS u " + "LEFT JOIN ERS_USER_ROLES rles "
					+ "ON u.USER_ROLE_ID = rles.ERS_USER_ROLE_ID";

			java.sql.Statement stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				UserBean user = new UserBean();
				// UserRolesBean role = new UserRolesBean();

				user.setUserID(rs.getInt("ers_user_ID"));
				user.setUsername(rs.getString("ers_username"));
				user.setPassword(rs.getString("ers_password"));
				user.setEmail(rs.getString("user_email"));
				// user.setFirstName(convertUserValue(rs.getString("full_Name"),
				// "fname"));
				user.setRole(convertUserValue(conn, rs.getString("user_role"), "role"));
				usersList.add(user);
			}
			rs.close();
			stmt.close();


		return usersList;
	}

	public static UserBean getUserByUname(Connection conn, String username) throws SQLException, ClassNotFoundException {
		UserBean user = new UserBean();
		// UserRolesBean role = new UserRolesBean();
                conn = ConnHelper.getConnection();
		
			String sql = "SELECT u.ers_users_id, " + "u.ers_username, " + "u.ers_password, "
					+ "CONCAT(CONCAT(user_first_name,' '), " + "user_last_name) AS full_Name, " + "u.user_email, "
					+ "rles.USER_ROLE " + "FROM ERS_USERS u " + "LEFT JOIN ERS_USER_ROLES rles "
					+ "ON u.USER_ROLE_ID = rles.ERS_USER_ROLE_ID " + "where ers_username = ?"
					+ " ORDER BY u.ers_username";

			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, username);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				user.setUserID(rs.getInt("ers_user_ID"));
				user.setFullName(rs.getString("full_Name"));
				user.setUsername(rs.getString("ers_username"));
				user.setPassword(rs.getString("ers_password"));
				user.setEmail(rs.getString("user_email"));
				// role.setUserRole(rs.getString("userRole"));
				// user.getRoleID(rs.getInt("roleID")).getUserRole();
				user.setRole(convertUserValue(conn, rs.getString("user_role"), "role"));
                                return user;
			}
//			rs.close();
//			preparedStatement.close();
		return null;
	}

	
	public static UserBean getUserByID(Connection conn, int id) throws SQLException, ClassNotFoundException {
		UserBean user = new UserBean();
		// UserRolesBean role = new UserRolesBean();
                    conn = ConnHelper.getConnection();
			String sql = "SELECT u.ers_users_id, " + "u.ers_username, " + "u.ers_password, "
					+ "CONCAT(CONCAT(user_first_name,' '), " + "user_last_name) AS full_Name, " + "u.user_email, "
					+ "rles.USER_ROLE " + "FROM ERS_USERS u " + "LEFT JOIN ERS_USER_ROLES rles "
					+ "ON u.USER_ROLE_ID = rles.ERS_USER_ROLE_ID " + "+ where ers_users_id=?";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				user.setUserID(rs.getInt("ers_user_ID"));
				user.setFullName(rs.getString("full_Name"));
				user.setUsername(rs.getString("ers_username"));
				user.setPassword(rs.getString("ers_password"));
				user.setEmail(rs.getString("user_email"));
				user.setRole(convertUserValue(conn, rs.getString("user_role"), "role"));
				return user;
			}

		return null;
	}

	public static UserBean getUserByName(Connection conn, String name) throws SQLException, ClassNotFoundException {
		UserBean user = new UserBean();
		// UserRolesBean role = new UserRolesBean();
                    conn = ConnHelper.getConnection();
		
			String sql = "SELECT u.ers_users_id, " + "u.ers_username, " + "u.ers_password, "
					+ "CONCAT(CONCAT(user_first_name,' '), " + "user_last_name) AS full_Name, " + "u.user_email, "
					+ "rles.USER_ROLE " + "FROM ERS_USERS u " + "LEFT JOIN ERS_USER_ROLES rles "
					+ "ON u.USER_ROLE_ID = rles.ERS_USER_ROLE_ID " + "where UPPER(full_name) LIKE UPPER(?)";

			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, "%" + name + "%");
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				user.setUserID(rs.getInt("ers_user_id"));
				user.setFullName(rs.getString("full_Name"));
				user.setUsername(rs.getString("ers_username"));
				user.setPassword(rs.getString("ers_password"));
				user.setEmail(rs.getString("user_email"));
				user.setRole(convertUserValue(conn, rs.getString("user_role"), "role"));
				return user;
			}
			
		return null;
	}

	public static List<UserBean> getUsersByname(Connection conn, String like) throws SQLException, ClassNotFoundException {
                conn = ConnHelper.getConnection();
		List<UserBean> results = new ArrayList<>();
		String sql = "SELECT u.ers_users_id, " + "u.ers_username, " + "u.ers_password, "
				+ "CONCAT(CONCAT(user_first_name,' '), " + "user_last_name) AS full_Name, " + "u.user_email, "
				+ "rles.USER_ROLE " + "FROM ERS_USERS u " + "LEFT JOIN ERS_USER_ROLES rles "
				+ "ON u.USER_ROLE_ID = rles.ERS_USER_ROLE_ID " + "WHERE FULL_NAME LIKE ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, "%" + like + "%");
		ResultSet rs = stmt.executeQuery();
		mapRows(rs, results);
		return results;
	}

	private static void mapRows(ResultSet rs, List<UserBean> results) throws SQLException, ClassNotFoundException {
		Connection conn1 = ConnHelper.getConnection();
		//new UserRolesBean();
		// String role = rle.getUserRole();
		while (rs.next()) {
			// get values from row
			int id = rs.getInt("ers_user_ID");
			String username = rs.getString("ers_username");
			String password = rs.getString("ers_password");
			String fullName = rs.getString("full_Name");
			String email = rs.getString("user_email");
			int role = convertUserValue(conn1, rs.getString("user_role"), "role");

			// rle = rs.getObject("USERROLES", UserRolesBean);
			// create object using those values

			UserBean obj = new UserBean(id, username, password, fullName, email, role);

			// add object to list
			results.add(obj);
		}
	}
	
	public static UserBean findUser(Connection conn, String userName, String password) throws SQLException, ClassNotFoundException {
	     
              conn = ConnHelper.getConnection();
	      String sql = "Select * from ERS_USERS where ERS_USERNAME=? AND ERS_PASSWORD=?";
	 
	      PreparedStatement pstm = conn.prepareStatement(sql);
	      pstm.setString(1, userName);
	      pstm.setString(2, password);
	   
              ResultSet rs = pstm.executeQuery();
	 
	      if (rs.next()) {
	          String email = rs.getString("USER_EMAIL");
	          String firstname = rs.getString("USER_FIRST_NAME");
	          String lastname = rs.getString("USER_LAST_NAME");
	          int roleID = rs.getInt("USER_ROLE_ID");
	          int userID = rs.getInt("ERS_USERS_ID");
	          UserBean user = new UserBean();
	          user.setUsername(userName);
	          user.setPassword(password);
	          user.setEmail(email);
	          user.setFirstName(firstname);
	          user.setLastName(lastname);
	          user.setRole(roleID);
	          user.setUserID(userID);
	          return user;
	      }
	      return null;
	  }
	 
	  public static UserBean findUser(Connection conn, String userName) throws SQLException, ClassNotFoundException{
              conn = ConnHelper.getConnection();
	      String sql = "Select * from ERS_USERS " + " where ERS_USERNAME = ? ";
	 
	      PreparedStatement pstm = conn.prepareStatement(sql);
	      pstm.setString(1, userName);
	 
	      ResultSet rs = pstm.executeQuery();
	 
	      if (rs.next()) {
	    	  String email = rs.getString("USER_EMAIL");
	    	  String password = rs.getString("ERS_PASSWORD");
	          String firstname = rs.getString("USER_FIRST_NAME");
	          String lastname = rs.getString("USER_LAST_NAME");
	          int roleID = rs.getInt("USER_ROLE_ID");
	          int userID = rs.getInt("ERS_USERS_ID");
	          UserBean user = new UserBean();
	          user.setUsername(userName);
	          user.setPassword(password);
	          user.setEmail(email);
	          user.setFirstName(firstname);
	          user.setLastName(lastname);
	          user.setRole(roleID);
	          user.setUserID(userID);
	          return user;
	      }
	      return null;
	  }
	 
}
