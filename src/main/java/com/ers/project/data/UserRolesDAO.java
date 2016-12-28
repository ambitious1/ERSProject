/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ers.project.data;

/**
 *
 * @author ambitious-1
 */
public interface UserRolesDAO {
    //public List<UserBean> getUserByRoles(String userRole);
    //public List<UserBean> getAllReimbByRoles(UserBean user);
    public boolean validateRole(int userID);
    
}
