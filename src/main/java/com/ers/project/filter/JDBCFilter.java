package com.ers.project.filter;

import java.io.IOException;
import java.sql.Connection;
import java.util.Collection;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;


import com.ers.project.conn.*;
import com.ers.project.utils.*;
import java.sql.SQLException;
 
@WebFilter(filterName = "jdbcFilter", urlPatterns = { "/*" })
public class JDBCFilter implements Filter {
 
 
   @Override
   public void init(FilterConfig fConfig) throws ServletException {
 
   }
 
   @Override
   public void destroy() {
 
   }
 
 
   // Check the target of the request is a servlet?
   private boolean needJDBC(HttpServletRequest request) {
       System.out.println("JDBC Filter");
       
       String servletPath = request.getServletPath();
       // => /abc/mnp
       String pathInfo = request.getPathInfo();
 
       String urlPattern = servletPath;
 
       if (pathInfo != null) {
           // => /spath/*
           urlPattern = servletPath + "/*";
       }
 
       // Key: servletName.
       // Value: ServletRegistration
       
       Map<String, ? extends ServletRegistration> servletRegistrations = request.getServletContext()
               .getServletRegistrations();        
       // Collection of all servlets in my project
       Collection<? extends ServletRegistration> values = servletRegistrations.values();
       for (ServletRegistration sr : values) {
           Collection<String> mappings = sr.getMappings();
           if (mappings.contains(urlPattern)) {
               return true;
           }
       }
       return false;
   }
 
   @Override
   public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
           throws IOException, ServletException {
 
       HttpServletRequest req = (HttpServletRequest) request;
 
       // Only open connections for neccessary purposes such as jsp, servlets, controllers
       //
       // Avoid open connection for miscellaneous requests
       // (for example: image, css, javascript,... )
       //
       if (this.needJDBC(req)) {
           
           System.out.println("Open Connection for: " + req.getServletPath());
 
           Connection conn = null;
           try {
               // Create connection
               conn = ConnHelper.getConnection();
 
               // Set Auto commit to false
//               conn.setAutoCommit(false);
// 
                // Store connection in attribute of request.
               ServletHelper.storeConnection(request, conn);
 
               // Allow request to go forward
               // (Go to the next filter or target)
               chain.doFilter(request, response);
 
                // Commit change.
               conn.commit();
           } catch (IOException | ClassNotFoundException | SQLException | ServletException e) {
               e.printStackTrace();
               ConnHelper.rollbackQuietly(conn);
               throw new ServletException();
           } finally {
        	   ConnHelper.closeQuietly(conn);
           }
       }
 
       // With commons requests (images, css, html, ..)
       // No need to open the connection.        
       else {
 
           // Allow request to go forward
           // (Go to the next filter or target)            
           chain.doFilter(request, response);
       }
 
   }
 
}