/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ers.project.data;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.ers.project.beans.ReimbBean;

/**
 *
 * @author ambitious-1
 */
public interface ReimbDAO {
    public ReimbBean getReimbByID(Connection conn, int reimbID);
    public List<ReimbBean> getReimbByAuth(Connection conn, String name);//*
    public List<ReimbBean> getReimbByType(Connection conn, String reimbType);
    public List<ReimbBean> getAllReimb(Connection conn);
    public List<ReimbBean> getAllReimbByStatus(Connection conn, String reimbStatus);
    public void insertReimb(Connection conn, ReimbBean reimb) throws SQLException;//*
    public void updateReimb(Connection conn, ReimbBean reimb);//*
    
}
