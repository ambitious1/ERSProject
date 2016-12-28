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
import javax.servlet.http.HttpSession;
 
import com.ers.project.beans.UserBean;
import com.ers.project.data.UserDAOImpl;
import com.ers.project.utils.ServletHelper;
import java.util.logging.Level;
import java.util.logging.Logger;
 
@WebServlet(name="DoLoginController", urlPatterns = { "/doLogin" })
public class DoLoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public DoLoginController() {
        super();
    }
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String rememberMeStr = request.getParameter("rememberMe");
        boolean remember= "Y".equals(rememberMeStr);
 
         
        UserBean user = null;
        boolean hasError = false;
        String errorString = null;
 
        if (userName == null || password == null || userName.length() == 0 || password.length() == 0) {
            hasError = true;
            errorString = "Required username and password!";
        } else {
            Connection conn = ServletHelper.getStoredConnection(request);
            try {
                
                user = UserDAOImpl.findUser(conn, userName, password);
                 
                if (user == null) {
                    hasError = true;
                    errorString = "User Name or password invalid";
                }
            } catch (SQLException e) {
                e.printStackTrace();
                hasError = true;
                errorString = e.getMessage();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(DoLoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
        if (hasError) {
            user = new UserBean();
            user.setUsername(userName);
            user.setPassword(password);
             
        
            // Store information in request attributes from user onbjectand errorflag status, before forward.
            request.setAttribute("errorString", errorString);
            request.setAttribute("user", user);
 
       
            // Forward to /WEB-INF/views/login.jsp
            RequestDispatcher dispatcher //
            = this.getServletContext().getRequestDispatcher("/WEB-INF/views/loginView.jsp");
 
            dispatcher.forward(request, response);
        }
     
        // If no error
        // Store user object in Session variable
        // And use it for the userInfo page.
        else {
            HttpSession session = request.getSession();
            ServletHelper.storeLoginedUser(session, user);
            
             
             // If user checked "Remember me". save cookie
            if(remember)  {
                ServletHelper.storeUserCookie(response,user);
            }
    
            // Else delete cookie.
            else  {
                ServletHelper.deleteUserCookie(response);
            }                       
      
            if (user.getUserID() > 3){
            // Redirect to employee Info page.
            response.sendRedirect(request.getContextPath() + "/WEB-INF/views/userInfo.jsp");
            } else {
                //redirect to manager info view page
                response.sendRedirect(request.getContextPath()+"/WEB-INF/views/admin.jsp");
            }
        }
    }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
 
}