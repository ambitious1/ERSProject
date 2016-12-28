package com.ers.project.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import com.ers.project.beans.ReimbBean;
import com.ers.project.beans.UserBean;
import com.ers.project.data.ReimbDAOImpl;
import com.ers.project.utils.ServletHelper;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
 
@WebServlet(name="DoCreateReimbController", urlPatterns = { "/doCreateReimb" })
public class DoCreateReimbController extends HttpServlet {
   private static final long serialVersionUID = 1L;
    private Object errorString;
 
   public DoCreateReimbController() {
       super();
   }
 
   @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
       Connection conn = ServletHelper.getStoredConnection(request);
 
       ReimbDAOImpl newDAO = new ReimbDAOImpl(conn);
       int newStatus = Integer.parseInt(request.getParameter("status"));
       int id = Integer.parseInt(request.getParameter("reimbID"));
       String descript = (String) request.getParameter("descript");
       double amount = Double.parseDouble(request.getParameter("amount"));
       
       HttpSession session = request.getSession(true);
       UserBean user = (UserBean) session.getAttribute("user");
       
       
       String fname = (String) request.getParameter("fname");
       ReimbBean newReimb = new ReimbBean();
       
       newReimb.setReimbAmount(newStatus);
       
       
       try {
           newDAO.insertReimb(conn, newReimb);
       } catch (SQLException ex) {
           Logger.getLogger(DoCreateReimbController.class.getName()).log(Level.SEVERE, null, ex);
       }
       // Product ID is the string literal [a-zA-Z_0-9]
       // with at least 1 character
       
        
       // Store infomation to request attribute, before forward to views.
       request.setAttribute("errorString", errorString);
       
 
       // If error, forward to Edit page.
       if (errorString != null) {
           RequestDispatcher dispatcher = request.getServletContext()
                   .getRequestDispatcher("/WEB-INF/views/createReimbursement.jsp");
           dispatcher.forward(request, response);
       }
 
       // If everything nice.
       // Redirect to the product listing page.            
       else {
           response.sendRedirect(request.getContextPath() + "/myReimbList");
       }
 
   }
 
   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
       doGet(request, response);
   }
 
}