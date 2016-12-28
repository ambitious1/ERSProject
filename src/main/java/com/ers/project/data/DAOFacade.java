/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ers.project.data;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;

import com.ers.project.beans.ReimbBean;
import com.ers.project.beans.UserBean;
import com.ers.project.conn.ConnHelper;

public class DAOFacade {
	private static Connection conn1;

	public void addReimb(double amount, String reimbDescript, String authFullName, String type) {

		Connection conn1 = null;
		try {
			ReimbBean reimb = new ReimbBean();
			UserBean user = new UserBean();
			conn1 = ConnHelper.getConnection();
			conn1.setAutoCommit(false);

			// reimbAmount = reimb.getReimbAmount();
			// reimbDescript = reimb.getReimbDescript();
			// authFullName = reimb.getReimbAuthor().getFullName();
			// type = reimb.getReimbTypeID().getReimb_Type();
			//
			reimb.setReimbAmount(amount);
			reimb.setReimbDescript(reimbDescript);
			reimb.setReimbTypeID(new ReimbDAOImpl(conn1).convertValue(conn1, type, "type"));
			reimb.setReimbAuthor(new ReimbDAOImpl(conn1).convertValue(conn1, authFullName, "name"));

			new ReimbDAOImpl(conn1).insertReimb(conn1, reimb);

			conn1.commit();
		} catch (SQLException | ClassNotFoundException e) {
			try {
				conn1.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				conn1.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void aordReimb(String status, String reslvrName, int reimbID) {

		//Connection conn4 = null;
		try {
			ReimbBean reimb = new ReimbBean();
			UserBean user = new UserBean();
			conn1 = ConnHelper.getConnection();
			conn1.setAutoCommit(false);

			// reimbAmount = reimb.getReimbAmount();
			// reimbDescript = reimb.getReimbDescript();
			// authFullName = reimb.getReimbAuthor().getFullName();
			// type = reimb.getReimbTypeID().getReimb_Type();
			Calendar calendar = Calendar.getInstance();
			java.sql.Timestamp ourJavaTimestampObject = new java.sql.Timestamp(calendar.getTime().getTime());

			reimb.setReimbResolver(new ReimbDAOImpl(conn1).convertValue(conn1, reslvrName, "name"));
			reimb.setReimbStatusID(new ReimbDAOImpl(conn1).convertValue(conn1, status, "status"));
			reimb.setReimbResolved(ourJavaTimestampObject);
			reimb.setReimbID(reimbID);

			new ReimbDAOImpl(conn1).updateReimb(conn1, reimb);

			conn1.commit();
		} catch (SQLException | ClassNotFoundException e) {
			try {
				conn1.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				conn1.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void viewAll() throws ClassNotFoundException {
		//Connection conn5 = null;
		try {

			conn1 = ConnHelper.getConnection();
			conn1.setAutoCommit(false);

			new ReimbDAOImpl(conn1).getAllReimb(conn1);

			conn1.commit();
		} catch (SQLException e) {
			try {
				conn1.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				conn1.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void reimbsByStatus(String status) throws ClassNotFoundException {
		//Connection conn6 = null;
		try {
			ReimbBean reimb = new ReimbBean();
			conn1 = ConnHelper.getConnection();
			conn1.setAutoCommit(false);

			new ReimbDAOImpl(conn1).getAllReimbByStatus(conn1, status);

			conn1.commit();
		} catch (SQLException e) {
			try {
				conn1.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				conn1.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void reimbsByUserID(int id) throws ClassNotFoundException {
		//Connection conn7 = null;
		try {
			ReimbBean reimb = new ReimbBean();
			conn1 = ConnHelper.getConnection();
			conn1.setAutoCommit(false);

			new ReimbDAOImpl(conn1).getReimbByID(conn1, id);

			conn1.commit();
		} catch (SQLException e) {
			try {
				conn1.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				conn1.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void reimbsByName(String empName) {
		//Connection conn1 = null;
		try {
			ReimbBean reimb = new ReimbBean();
			conn1 = ConnHelper.getConnection();
			conn1.setAutoCommit(false);

			new ReimbDAOImpl(conn1).getReimbByAuth(conn1, empName);

			conn1.commit();
		} catch (SQLException | ClassNotFoundException e) {
			try {
				conn1.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				conn1.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void reimbsByType(String type) throws ClassNotFoundException {
		//Connection conn1 = null;
		try {
			ReimbBean reimb = new ReimbBean();
			conn1 = ConnHelper.getConnection();
			conn1.setAutoCommit(false);

			new ReimbDAOImpl(conn1).getReimbByType(conn1, type);

			conn1.commit();
		} catch (SQLException e) {
			try {
				conn1.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				conn1.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

        
        public void findUser(String userName, String password) throws ClassNotFoundException, SQLException {
            
            try{
                UserBean user = new UserBean();
                conn1 = ConnHelper.getConnection();
                conn1.setAutoCommit(false);
                
                new UserDAOImpl(conn1).findUser(conn1, userName, password);
                conn1.commit();
            } catch (SQLException e) {
                try{
                    conn1.rollback();
            } catch (SQLException e1) {
			e1.printStackTrace();
			}
		} finally {
			try {
				conn1.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
        
        }
           
}
