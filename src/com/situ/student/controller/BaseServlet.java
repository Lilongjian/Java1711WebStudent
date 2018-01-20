package com.situ.student.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BaseServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//1.得到Session
		//req.getSession(false);没有JSESSIONID返回null，如果有JSESSIONID，就去Session里面找是否有
		//对应的Session，如果没有返回null，如果有就返回HttpSession
		HttpSession session = req.getSession();
		//得到Session里面数据
		String userName = (String) session.getAttribute("userName");
		/*if (userName == null) {
			//2.重定向到登录界面
			resp.sendRedirect(req.getContextPath() + "/jsp/login.jsp");
			return;
		}*/
		
		System.out.println(req.getRequestURI());
		System.out.println(req.getContextPath());
		String servletPath = req.getServletPath();
		System.out.println(servletPath);
		//处理post请求乱码
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=utf-8");
		String methodName = req.getParameter("method");
		Class clazz = this.getClass();
		try {
			//Method method = clazz.getDeclaredMethod(methodName, new Class[]{HttpServletRequest.class,HttpServletRequest.class});
	        Method method = clazz.getDeclaredMethod(methodName, new Class[]{HttpServletRequest.class, HttpServletResponse.class});
			method.setAccessible(true);
			
			method.invoke(this, new Object[]{req,resp});
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*if ("/add.do".equals(servletPath)) {
			add(req, resp);
		} else if ("/findAll.do".equals(servletPath)) {
			findAll(req, resp);
		} else if ("/findByName.do".equals(servletPath)) {
			findByName(req, resp);
		} else if ("/showInfo.do".equals(servletPath)) {
			showInfo(req, resp);
		} else if ("/delete.do".equals(servletPath)) {
			delete(req, resp);
		} else if ("/toUpdate.do".equals(servletPath)) {
			toUpdate(req, resp);
		} else if ("/update.do".equals(servletPath)) {
			update(req, resp);
		} else if ("/getStudentAdd.do".equals(servletPath)) {
			getStudentAdd(req, resp);
		}*/ catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
