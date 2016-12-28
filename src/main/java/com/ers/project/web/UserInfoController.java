
package com.ers.project.web;

import java.io.IOException;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 
import com.ers.project.beans.UserBean;
import com.ers.project.utils.ServletHelper;
 
@WebServlet(name="UserInfoController", urlPatterns = { "/userInfo" })
public class UserInfoController extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public UserInfoController() {
        super();
    }
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
 
 
        // Check User whether the user has logged in
        UserBean loginedUser = ServletHelper.getLoginedUser(session);
 
  
        // If is not logged in
        if (loginedUser == null) {
       
            // Redirect back to login page.
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
  
        // Store info in request attribute
        request.setAttribute("user", loginedUser);
 
  
        // Logined, forward to /WEB-INF/views/userInfoView.jsp
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/userInfoView.jsp");
        dispatcher.forward(request, response);
 
    }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
 
}