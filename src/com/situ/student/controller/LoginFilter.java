package com.situ.student.controller;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class LoginFilter
 */
public class LoginFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		   HttpServletRequest req = (HttpServletRequest) request;
		   HttpServletResponse resp = (HttpServletResponse) response;
		   String uri = req.getRequestURI();
		   System.out.println(uri);
		   String servletPath = req.getServletPath();
		   System.out.println(servletPath);
		   int lastIndex = servletPath.lastIndexOf(".");
		   String extension = "";
		   if (lastIndex != -1) {
			extension = servletPath.substring(lastIndex);		}
		   /*if (!"/login".equals(servletPath)
				   || ".js".equalsIgnoreCase(extension)
				   || ".css".equalsIgnoreCase(extension)) {
			HttpSession session = req.getSession();
			Object user = session.getAttribute("user");
			System.out.println("346" + user);
			if (user == null) {
				req.getRequestDispatcher("/WEB-INF/jsp/user_login.jsp").forward(request, response);
				return;
			}
		}
		   chain.doFilter(request, response);*/
		   
		   if ("/login".equals(servletPath)
				   || "/checkImg".equalsIgnoreCase(servletPath)
				   || ".js".equalsIgnoreCase(extension)
				   || ".css".equalsIgnoreCase(extension)) {
			       
				chain.doFilter(request, response);
			}else{
				HttpSession session = req.getSession();
				Object user = session.getAttribute("user");
				System.out.println("346" + user);
				if (user == null) {
					req.getRequestDispatcher("/WEB-INF/jsp/user_login.jsp").forward(request, response);
					return;
			}
				chain.doFilter(request, response);
		}
		   
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
}
