package com.situ.student.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.situ.student.entity.User;
import com.situ.student.service.IUserService;
import com.situ.student.service.impl.UserServiceImpl;

/**
 * Servlet implementation class LoginServlet2
 */
public class LoginServlet extends BaseServlet {
	/*private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//1.获取参数
		String name = req.getParameter("name");
		String password = req.getParameter("password");
		//2.业务处理
		if ("zhangsan".equals(name) && "123".equals(password)) {
			//登录成功
			//2.1创建Session对象
			HttpSession session = req.getSession();
			//2.2把数据保存到域对象中
			session.setAttribute("userName", name);
			//2.3跳转到列表页面
			resp.sendRedirect(req.getContextPath() + "/student?method=findAll");
		} else {
			//登录失败
			resp.sendRedirect(req.getContextPath() + "/jsp/fail.jsp");
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}*/
	
	private IUserService userService = new UserServiceImpl();
	public void getLoginPage(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		request.getRequestDispatcher("/WEB-INF/jsp/user_login.jsp").forward(request, response);
	}
	
	public void getOnLinePage(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		request.getRequestDispatcher("/WEB-INF/jsp/online_user_list.jsp").forward(request, response);
	}
	public void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		   String checkCode = req.getParameter("checkCode");
		   String checkCodeSession = (String) req.getSession().getAttribute("checkCodeSession");
		   if (checkCode == null 
				   || "".equals(checkCode)
				   || !checkCode.equalsIgnoreCase(checkCodeSession)) {
			   resp.sendRedirect(req.getContextPath() + "/student?method=pageList");
			   return;
		   }
		String name = req.getParameter("name");
		String password = req.getParameter("password");
		User user = userService.login(name, password);
		if (user != null) {
			HttpSession session = req.getSession();
			session.setAttribute("user", user);
			List<User> list = (List<User>) getServletContext().getAttribute("onLineUserList");
			list.add(user);
			System.out.println("用户在线列表" + list);
			resp.sendRedirect(req.getContextPath() + "/student?method=searchByCondition");
			return;
		}else {
			resp.sendRedirect(req.getContextPath() + "/login?method=getLoginPage");
		}
	}
	public void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		User user  = (User) session.getAttribute("user");
		session.removeAttribute("user");
		List<User> list = (List<User>) getServletContext().getAttribute("onLineUserList");
		list.remove(user);
		resp.sendRedirect(req.getContextPath() + "/login?method=getLoginPage");
	}
}
