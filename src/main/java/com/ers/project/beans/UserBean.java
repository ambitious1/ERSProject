package com.ers.project.beans;

/**
 *
 * @author ambitious-1
 */
public class UserBean {
    private int userID;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String fullName;
    private int role;

    public UserBean(){
        super();
    }

    public UserBean(int userID, String username, String password, String firstName, String lastName, String email, int role, String fullName) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
        this.fullName = fullName;
    }
   
    public UserBean(int userID, String username, String password, String fullName, String email, int role){
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.fullName = fullName; 
        this.email = email;   
        this.role = role;
    }
            
    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    
    public String getFullName() {
        return firstName + ' ' + lastName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return "UserBean{" + "userID=" + userID + ", username=" + username + ", password=" + password + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", role=" + role + ", fullName=" + fullName + '}';
    }

//    public UserRolesBean getRole() {
//        return role;
//    }
//
//    public void setRole(UserRolesBean role) {
//        this.role = role;
//    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
    
}
