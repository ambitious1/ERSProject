
package com.ers.project.mid;

import java.util.List;
import com.ers.project.beans.ReimbBean;
import com.ers.project.beans.UserBean;
import com.ers.project.conn.ConnHelper;



public abstract class BusinessDelegate {
   protected ReimbBean bean;
   abstract void getService();
   
//    public List<ReimbBean> getAll(){
//        return new ServiceLocator().getAllReimb();
//    }
//    
//    public List<ReimbBean> getUserByID(int id){
//        return new ServiceLocator().getReimbsByID(id);
//    }

}
