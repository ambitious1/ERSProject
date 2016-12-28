package com.ers.project.data;

public interface ReimbStatusDAO {
    public int getStatusIDByName(String reimbStatus);
    public String getStatusByID(int reimbStatusID);
}
