/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ers.project.web;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import com.ers.project.beans.ReimbBean;
import com.ers.project.data.ReimbDAOImpl;
import com.ers.project.utils.ServletHelper;
 
@WebServlet(urlPatterns = { "/myReimbs" })
public class UsersReimb extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public UsersReimb() {
        super();
    }
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = ServletHelper.getStoredConnection(request);
 
        String errorString = null;
        List<ReimbBean> list = null;
        ReimbDAOImpl function = new ReimbDAOImpl(conn);
        list = function.getReimbByAuth(conn, (String) request.getSession().getAttribute("name"));
   
        // Store info in request attribute, before forward to views
        request.setAttribute("errorString", errorString);
        request.setAttribute("myReimbList", list);
         
     
        // Forward to /WEB-INF/views/productListView.jsp
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/myReimbView.jsp");
        dispatcher.forward(request, response);
    }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
 
}