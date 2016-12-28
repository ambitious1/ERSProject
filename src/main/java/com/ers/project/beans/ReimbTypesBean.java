package com.ers.project.beans;

/**
 * @author ambitious-1
 */
public class ReimbTypesBean {
    private int reimb_Type_ID;
    private String reimb_Type;

    public ReimbTypesBean(int reimb_Type_ID, String reimb_Type) {
        this.reimb_Type_ID = reimb_Type_ID;
        this.reimb_Type = reimb_Type;
    }

    public int getReimb_Type_ID() {
        return reimb_Type_ID;
    }

    public void setReimb_Type_ID(int reimb_Type_ID) {
        this.reimb_Type_ID = reimb_Type_ID;
    }

    @Override
    public String toString() {
        return "ReimbTypesBean{" + "reimb_Type_ID=" + reimb_Type_ID + ", reimb_Type=" + reimb_Type + '}';
    }

    public String getReimb_Type() {
        return reimb_Type;
    }

    public void setReimb_Type(String reimb_Type) {
        this.reimb_Type = reimb_Type;
    }
    
    public ReimbTypesBean() {
        super();
    }
}
