package com.ers.project.beans;

import java.sql.Timestamp;


public class ReimbBean {
    private int reimbID;
    private double reimbAmount;
    private Timestamp reimbSubmitted;
    private Timestamp reimbResolved;
    private String reimbDescript;
    private int reimbAuthor; /*UserBean*/
    private int reimbResolver; /*UserBean*/
    private int reimbStatusID; /*ReimbStatusBean*/
    private int reimbTypeID; /*ReimbTypesBean*/
    //private Timestamp timestamp;
    private String reimbAuthFullName;
    private String reimbResolvFullName;
    

//    public ReimbBean(int reimbID, double reimbAmount, Timestamp reimbSubmitted, Timestamp reimbResolved, String reimbDescript, int reimbAuthor, int reimbResolver, int reimbStatusID, int reimbTypeID, Timestamp timestamp, String reimbAuthFullName, String reimbResolvFullName) {
//        this.reimbID = reimbID;
//        this.reimbAmount = reimbAmount;
//        this.reimbSubmitted = reimbSubmitted;
//        this.reimbResolved = reimbResolved;
//        this.reimbDescript = reimbDescript;
//        this.reimbAuthor = reimbAuthor;
//        this.reimbResolver = reimbResolver;
//        this.reimbStatusID = reimbStatusID;
//        this.reimbTypeID = reimbTypeID;
//        this.timestamp = timestamp;
//        this.reimbAuthFullName = reimbAuthFullName;
//        this.reimbResolvFullName = reimbResolvFullName;
//    }
//    
    public ReimbBean(int reimbID, double reimbAmount, Timestamp reimbSubmitted, Timestamp reimbResolved, String reimbDescript, /*UserBean*/int reimbAuthor, /*UserBean*/int reimbResolver, /*ReimbStatusBean*/ int reimbStatusID, /*ReimbTypesBean*/ int reimbTypeID) {
        this.reimbID = reimbID;
        this.reimbAmount = reimbAmount;
        this.reimbSubmitted = reimbSubmitted;
        this.reimbResolved = reimbResolved;
        this.reimbDescript = reimbDescript;
        this.reimbAuthor = reimbAuthor;
        this.reimbResolver = reimbResolver;
        this.reimbStatusID = reimbStatusID;
        this.reimbTypeID = reimbTypeID;
    }

   

    public int getReimbID() {
        return reimbID;
    }

    public void setReimbID(int reimbID) {
        this.reimbID = reimbID;
    }

    public double getReimbAmount() {
        return reimbAmount;
    }

    public void setReimbAmount(double reimbAmount) {
        this.reimbAmount = reimbAmount;
    }

    public Timestamp getReimbSubmitted() {
        return reimbSubmitted;
    }

    public void setReimbSubmitted(Timestamp reimbSubmitted) {
        this.reimbSubmitted = reimbSubmitted;
    }

    public Timestamp getReimbResolved() {
        return reimbResolved;
    }

    public void setReimbResolved(Timestamp reimbResolved) {
        this.reimbResolved = reimbResolved;
    }


    public String getReimbDescript() {
        return reimbDescript;
    }

    public void setReimbDescript(String reimbDescript) {
        this.reimbDescript = reimbDescript;
    }

    public int getReimbAuthor() { /*UserBean*/
        return reimbAuthor;
    }

    public void setReimbAuthor(int reimbAuthor) {
        this.reimbAuthor = reimbAuthor;
    }

    public /*UserBean*/int getReimbResolver() {
        return reimbResolver;
    }

    public void setReimbResolver(/*UserBean*/int reimbResolver) {
        this.reimbResolver = reimbResolver;
    }

    public /*ReimbStatusBean*/int getReimbStatusID() {
        return reimbStatusID;
    }
    
//    public void setReimbTime(Timestamp timestamp){
//        this.timestamp = timestamp;
//    }
//    
//    public Timestamp getReimbTime(){
//        return timestamp;
//    }

    public void setReimbStatusID(/*ReimbStatusBean*/ int reimbStatusID) {
        this.reimbStatusID = reimbStatusID;
    }

    public /*ReimbTypesBean*/int getReimbTypeID() {
        return reimbTypeID;
    }

    public void setReimbTypeID(/*ReimbTypesBean*/int reimbTypeID) {
        this.reimbTypeID = reimbTypeID;
    }

    @Override
    public String toString() {
        return "ReimbBean{" + "reimbID=" + reimbID + ", reimbAmount=" + reimbAmount + ", reimbSubmitted=" + reimbSubmitted + ", reimbResolved=" + reimbResolved + ", reimbDescript=" + reimbDescript + ", reimbAuthor=" + reimbAuthor + ", reimbResolver=" + reimbResolver + ", reimbStatusID=" + reimbStatusID + ", reimbTypeID=" + reimbTypeID + '}';
    }

    public ReimbBean() {
        super();
    }

//    public void setTimestamp(Timestamp timestamp) {
//        this.timestamp = timestamp;
//    }

    public String getReimbAuthFullName() {
        return reimbAuthFullName;
    }

    public void setReimbAuthFullName(String reimbAuthFullName) {
        this.reimbAuthFullName = reimbAuthFullName;
    }

    public String getReimbResolvFullName() {
        return reimbResolvFullName;
    }

    public void setReimbResolvFullName(String reimbResolvFullName) {
        this.reimbResolvFullName = reimbResolvFullName;
    }
    

   
}
