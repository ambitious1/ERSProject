package com.ers.project.beans;

/**
 *
 * @author ambitious-1
 */
public class UserRolesBean {
    private int userRoleID;
    private String userRole;

    public UserRolesBean(int userRoleID, String userRole) {
        this.userRoleID = userRoleID;
        this.userRole = userRole;
    }

    public UserRolesBean() {
        super();
    }

    
    public int getUserRoleID() {
        return userRoleID;
    }

    public void setUserRoleID(int userRoleID) {
        this.userRoleID = userRoleID;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    @Override
    public String toString() {
        return "UserRolesBean{" + "userRoleID=" + userRoleID + ", userRole=" + userRole + '}';
    }

  
   
    public void getUserRole(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void setUserRoleID(UserRolesBean roleID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
