package com.situ.student.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.situ.student.entity.Banji;
import com.situ.student.entity.Student;
import com.situ.student.service.IBanjiService;
import com.situ.student.service.IStudentService;
import com.situ.student.service.impl.BanjiServiceImpl;
import com.situ.student.service.impl.StudentServiceImpl;
import com.situ.student.util.Constant;
import com.situ.student.util.JDBCUtil;

public class BanjiMainServlet extends BaseServlet {
	private IBanjiService banjiService = new BanjiServiceImpl();
	
	/*private void searchByCondition(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String name = req.getParameter("name");
		String age = req.getParameter("age");
		String gender = req.getParameter("gender");
		StudentSearchCondition studentSearchCondition = new StudentSearchCondition(name, age, gender);
		List<Student> list = studentService.searchByCondition(studentSearchCondition);
		req.setAttribute("list", list);
		req.setAttribute("searchCondition", studentSearchCondition);
		req.getRequestDispatcher("/WEB-INF/jsp/student_list.jsp").forward(req, resp);
	}*/
		
	private void getStudentAdd(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		req.getRequestDispatcher("/WEB-INF/jsp/banji_add.jsp").forward(req, resp);
	}
	
	private void pageList(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		 String pageNoStr = req.getParameter("pageNo");
		 String pageSizeStr = req.getParameter("pageSize");
		 int pageNo=1;
		 if (pageNoStr != null && !"".equals(pageNoStr)) {
			pageNo = Integer.parseInt(pageNoStr);
		}
		 int pageSize = Constant.DEFAULT_PAGE_SIZE;
		 if (pageSizeStr != null && !"".equals(pageSizeStr)) {
			pageSize = Integer.parseInt(pageSizeStr);
		}
		PageBean pageBean = banjiService.getPageBean(pageNo,pageSize);
		System.out.println(pageBean);
		req.setAttribute("pageBean", pageBean);
		/*System.out.println("新表" + pageBean.getList());*/
		req.getRequestDispatcher("/WEB-INF/jsp/banji_list.jsp").forward(req, resp);
		
	}
     
	private void update(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String idStr = req.getParameter("id");
		String name = req.getParameter("name");
		Banji banji = new Banji(Integer.parseInt(idStr), name);
		if (banjiService.update(banji)) {
			System.out.println("更新成功");
		} else {
			System.out.println("更新失败");
		}
		resp.sendRedirect(req.getContextPath() + "/banji?method=pageList");
	}

	private void toUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//1.获得要修改学生的id
		String idStr = req.getParameter("id");
		int id = Integer.parseInt(idStr);
		//2.把对应id的学生查出来
		Banji banji = banjiService.findById(id);
		//3.把查出来学生放到reques域对象中
		req.setAttribute("banji", banji);
		//4.转发到edit_student.jsp
		req.getRequestDispatcher("/WEB-INF/jsp/banji_edit.jsp").forward(req, resp);
	}

	private void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		String idStr = req.getParameter("id");
		int id = Integer.parseInt(idStr);
		boolean result = banjiService.deleteById(id);
		if (result) {
			System.out.println("删除成功");
		} else {
			System.out.println("删除失败");
		}
		//resp.sendRedirect("/Java1711Web/findAll.do");
		resp.sendRedirect(req.getContextPath() + "/banji?method=findAll");
	}

	/*private void findByName(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		List<Student> list = studentService.findByName(name);
		req.setAttribute("list", list);
		// 存储转发是给服务器看的，已经在tomcat下面的/Java1711Web下面所以这个"/"代表/Java1711Web
		//req.getRequestDispatcher("/showInfo.do").forward(req, resp);
		req.getRequestDispatcher("/jsp/student_list.jsp").forward(req, resp);
	}*/

	private void add(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// 1.接收参数
		String id = req.getParameter("id");
		String name = req.getParameter("name");
		System.out.println("name:" + name);
		/*byte[] bytes = name.getBytes("iso8859-1");
		String newName = new String(bytes, "utf-8");
		System.out.println("newName: " + newName);*/
		Banji banji = new Banji(Integer.parseInt(id),name);
		System.out.println(banji);
		// 2.业务处理
		int result = banjiService.add(banji);
		// 3.输出响应 Magic number
		/*resp.setContentType("text/html;charset=utf-8");*/
		/*PrintWriter printWriter = resp.getWriter();
		if (result == Constant.ADD_SUCCESS) {
			printWriter.println("Add Success");
		} else if (result == Constant.ADD_NAME_REPEAT) {
			printWriter.println("Name Repeat");
		} else {
			printWriter.println("Add Fail");
		}
		printWriter.close();*/

		//重定向是给浏览器看的，所以"/"代表的tomacat的目录
		resp.sendRedirect(req.getContextPath() + "/banji?method=pageList");
	}

	private void findAll(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		// 1.接收请求参数，封装成对象
		// 2.调业务层处理
		List<Banji> list = banjiService.findAll();
		// 3.控制界面的跳转
		req.setAttribute("list", list);
		/*RequestDispatcher requestDispatcher = req.getRequestDispatcher("");
		requestDispatcher.forward(req, resp);*/
		//req.getRequestDispatcher("/showInfo.do").forward(req, resp);
		req.getRequestDispatcher("/WEB-INF/jsp/banji_list.jsp").forward(req, resp);
	}
	
	private void deleteAll(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String[] ids = req.getParameterValues("checked");
		for (String string : ids) {
			System.out.println(string);
		}
		banjiService.deleteAll(ids);
		resp.sendRedirect(req.getContextPath()  + "/banji?method=findAll");
	}
	
	/*private void showInfo(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		List<Student> list = (List<Student>) req.getAttribute("list");
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter printWriter = resp.getWriter();
		printWriter.println("<a href='/Java1711Web/add_student.html'>添加</a>");
		printWriter.println("<table border='1' cellspacing='0'>");
		printWriter.println("<tr>            ");
		printWriter.println("	<th>编号</th>");
		printWriter.println("	<th>姓名</th>");
		printWriter.println("	<th>年龄</th>");
		printWriter.println("	<th>性别</th>");
		printWriter.println("	<th>地址</th>");
		printWriter.println("</tr>           ");

		for (Student student : list) {
			printWriter.println("<tr>            ");
			printWriter.println("	<td>" + student.getId() + "</td>   ");
			printWriter.println("	<td>" + student.getName() + "</td>");
			printWriter.println("	<td>" + student.getAge() + "</td>  ");
			printWriter.println("	<td>" + student.getGender() + "</td>  ");
			printWriter.println("	<td>" + student.getAddress() + "</td>");
			printWriter.println("</tr>           ");
		}

		printWriter.println("</table>");
	}*/
	
	private void searchByCondition(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		System.out.println("BanjiMainServlet.searchByCondition()");
		String pageNoStr = req.getParameter("pageNo");
		String pageSizeStr = req.getParameter("pageSize");
		int pageNo = 1;//默认取第一页的数据
	    if (pageNoStr != null && !"".equals(pageNoStr)) {
				pageNo = Integer.parseInt(pageNoStr);
			}
			int pageSize = Constant.DEFAULT_PAGE_SIZE;//默认每一页条数
			if (pageSizeStr != null && !"".equals(pageSizeStr)) {
				pageSize = Integer.parseInt(pageSizeStr);
			}
			String id  = req.getParameter("id");
			String name = req.getParameter("name");
			BanjiSearchCondition banjiSearchCondition = new BanjiSearchCondition(pageNo, pageSize, name);
			/*PageBean pageBean = studentService.searchByCondition(studentSearchCondition);*/
			PageBean<Banji> pageBean = banjiService.searchByCondition(banjiSearchCondition);
			req.setAttribute("pageBean",pageBean);
			//req.setAttribute("searchCondition", banjiSearchCondition);
			req.getRequestDispatcher("/WEB-INF/jsp/banji_list.jsp").forward(req, resp);
	}
}
