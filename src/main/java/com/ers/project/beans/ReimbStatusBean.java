package com.ers.project.beans;

/** 
 *
 * @author ambitious-1
 */
public class ReimbStatusBean {
    private int reimbStatusID;
    private String reimbStatus;

    
    @Override
    public String toString() {
        return "ReimbStatusBean{" + "reimbStatusID=" + reimbStatusID + ", reimbStatus=" + reimbStatus + '}';
    }

    public ReimbStatusBean(int reimbStatusID, String reimbStatus) {
        this.reimbStatusID = reimbStatusID;
        this.reimbStatus = reimbStatus;
    }

    public int getReimbStatusID() {
        return reimbStatusID;
    }

    public void setReimbStatusID(int reimbStatusID) {
        this.reimbStatusID = reimbStatusID;
    }

    public String getReimbStatus() {
        return reimbStatus;
    }

    public void setReimbStatus(String reimbStatus) {
        this.reimbStatus = reimbStatus;
    }

    public ReimbStatusBean() {
        super();
    }

    
}