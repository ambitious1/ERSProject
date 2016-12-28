package com.ers.project.filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.ers.project.beans.UserBean;
import com.ers.project.data.UserDAOImpl;
import com.ers.project.utils.ServletHelper;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebFilter(filterName = "cookieFilter", urlPatterns = { "/*" })
public class CookieFilter implements Filter {

	public CookieFilter() {
	}

	@Override
	public void init(FilterConfig fConfig) throws ServletException {

	}

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();

		UserBean userInSession = ServletHelper.getLoginedUser(session);

		if (userInSession != null) {
			session.setAttribute("COOKIE_CHECKED", "CHECKED");
			chain.doFilter(request, response);
			return;
		}

		// Connection was created in JDBCFilter.
		Connection conn = ServletHelper.getStoredConnection(request);

		// Flag check cookie
		String checked = (String) session.getAttribute("COOKIE_CHECKED");
		if (checked == null && conn != null) {
			String userName = ServletHelper.getUserNameInCookie(req);
			try {
				UserBean user = UserDAOImpl.findUser(conn, userName);
				ServletHelper.storeLoginedUser(session, user);
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException ex) {
                        Logger.getLogger(CookieFilter.class.getName()).log(Level.SEVERE, null, ex);
                    }

			// Mark checked.
			session.setAttribute("COOKIE_CHECKED", "CHECKED");
		}

		chain.doFilter(request, response);
	}

}