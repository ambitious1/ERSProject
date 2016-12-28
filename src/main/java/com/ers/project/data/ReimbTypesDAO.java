
package com.ers.project.data;

import com.ers.project.beans.ReimbTypesBean;


public interface ReimbTypesDAO {
   
    public int getTypeIDByTypeName(String rTypes);
    public String getTypeByTypeID(int rTypesId);
    
}
