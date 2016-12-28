package com.ers.project.utils;

import java.sql.Connection;

import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ers.project.beans.UserBean;


public class ServletHelper {
	public static final String ATT_FOR_CONNECTION = "ATTRIBUTE_FOR_CONNECTION";
	 
	   private static final String ATT_FOR_USERNAME = "ATTRIBUTE_FOR_STORE_USER_NAME_IN_COOKIE";
	 
	    
	   // Store Connection in request attribute.
	   // (Information stored only exist during requests)
	   public static void storeConnection(ServletRequest request, Connection conn) {
	       request.setAttribute(ATT_FOR_CONNECTION, conn);
	   }
	 
	   // Get the Connection object has been stored in one attribute of the request.
	   public static Connection getStoredConnection(ServletRequest request) {
	       Connection conn = (Connection) request.getAttribute(ATT_FOR_CONNECTION);
	       return conn;
	   }
	 
	   // Store user info in Session.
	   public static void storeLoginedUser(HttpSession session, UserBean loginedUser) {
	 
	       // On the JSP can access ${loginedUser}
	       session.setAttribute("loginedUser", loginedUser);
	   }
	 
	 
	   // Get the user information stored in the session.
	   public static UserBean getLoginedUser(HttpSession session) {
		   UserBean loginedUser = (UserBean) session.getAttribute("loginedUser");
	       return loginedUser;
	   }
	 
	 
	   // Store info in Cookie
	   public static void storeUserCookie(HttpServletResponse response, UserBean user) {
	       System.out.println("Store user cookie");
	       Cookie cookieUserName = new Cookie(ATT_FOR_USERNAME, user.getUsername());
	 
	       // 1 day (Convert to seconds)
	       cookieUserName.setMaxAge(24 * 60 * 60);
	       response.addCookie(cookieUserName);
	   }
	 
	   public static String getUserNameInCookie(HttpServletRequest request) {
	       Cookie[] cookies = request.getCookies();
	       if (cookies != null) {
	           for (Cookie cookie : cookies) {
	               if (ATT_FOR_USERNAME.equals(cookie.getName())) {
	                   return cookie.getValue();
	               }
	           }
	       }
	       return null;
	   }
	 
	 
	   // Delete cookie.
	   public static void deleteUserCookie(HttpServletResponse response) {
	       Cookie cookieUserName = new Cookie(ATT_FOR_USERNAME, null);
	 
	       // 0 seconds (Expires immediately)
	       cookieUserName.setMaxAge(0);
	       response.addCookie(cookieUserName);
	   }
	 
	}
