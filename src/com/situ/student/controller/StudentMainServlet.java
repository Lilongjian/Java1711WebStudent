package com.situ.student.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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

public class StudentMainServlet extends BaseServlet {
	private IStudentService studentService = new StudentServiceImpl();
	private IBanjiService banjiService = new BanjiServiceImpl();
	
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
		PageBean pageBean = studentService.getPageBean(pageNo,pageSize);
		System.out.println(pageBean);
		req.setAttribute("pageBean", pageBean);
		System.out.println("新表" + pageBean.getList());
		req.getRequestDispatcher("/WEB-INF/jsp/student_list.jsp").forward(req, resp);
	}

	
	private void searchByCondition(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		System.out.println("StudentMainServlet.searchByCondition()");
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
			String name = req.getParameter("name");
			String age = req.getParameter("age");
			String gender = req.getParameter("gender");
			StudentSearchCondition studentSearchCondition = new StudentSearchCondition(pageNo, pageSize, name, age, gender);
			
			/*PageBean pageBean = studentService.searchByCondition(studentSearchCondition);*/
			PageBean<Student> pageBean = studentService.searchByCondition(studentSearchCondition);
			req.setAttribute("pageBean",pageBean);
			req.setAttribute("searchCondition", studentSearchCondition);
			req.getRequestDispatcher("/WEB-INF/jsp/student_list.jsp").forward(req, resp);
		/*String name = req.getParameter("name");
		String age = req.getParameter("age");
		String gender = req.getParameter("gender");
		StudentSearchCondition studentSearchCondition = new StudentSearchCondition(name, age, gender);
		List<Student> list = studentService.searchByCondition(studentSearchCondition);
		req.setAttribute("list", list);
		req.setAttribute("searchCondition", studentSearchCondition);
		req.getRequestDispatcher("/WEB-INF/jsp/student_list.jsp").forward(req, resp);*/
	}
	
	private void getStudentAdd(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		List<Banji> list = banjiService.findAll(); 
		/*List<Banji> list = new ArrayList<Banji>();
		Banji banji1 = new Banji(1, "大班");
		list.add(banji1);
		Banji banji2 = new Banji(2, "小班");
		list.add(banji2);*/
		req.setAttribute("list", list);
		//req.setAttribute(name, o);
		req.getRequestDispatcher("/WEB-INF/jsp/student_add.jsp").forward(req, resp);
	}
	
	private void checkName(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String name = req.getParameter("name");
		boolean isExist = studentService.checkName(name);
		System.out.println("true或false" + isExist);
		resp.getWriter().write("{\"isExist\":" + isExist + "}");
	}

	private void update(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String idStr = req.getParameter("id");
		String name = req.getParameter("name");
		String age = req.getParameter("age");
		String gender = req.getParameter("gender");
		String address = req.getParameter("address");
		Student student = new Student(Integer.parseInt(idStr), name, Integer.parseInt(age), gender, address, new Date(), new Date());
		if (studentService.update(student)) {
			System.out.println("更新成功");
		} else {
			System.out.println("更新失败");
		}
		resp.sendRedirect(req.getContextPath() + "/student?method=searchByCondition");
	}

	private void toUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//1.获得要修改学生的id
		String idStr = req.getParameter("id");
		int id = Integer.parseInt(idStr);
		//2.把对应id的学生查出来
		Student student = studentService.findById(id);
		//3.把查出来学生放到reques域对象中
		req.setAttribute("student", student);
		//4.转发到edit_student.jsp
		req.getRequestDispatcher("/WEB-INF/jsp/student_edit.jsp").forward(req, resp);
	}

	private void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String idStr = req.getParameter("id");
		int id = Integer.parseInt(idStr);
		boolean result = studentService.deleteById(id);
		if (result) {
			System.out.println("删除成功");
		} else {
			System.out.println("删除失败");
		}
		//resp.sendRedirect("/Java1711Web/findAll.do");
		resp.sendRedirect(req.getContextPath() + "/student?method=searchByCondition");
	}

	private void findByName(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		List<Student> list = studentService.findByName(name);
		req.setAttribute("list", list);
		// 存储转发是给服务器看的，已经在tomcat下面的/Java1711Web下面所以这个"/"代表/Java1711Web
		//req.getRequestDispatcher("/showInfo.do").forward(req, resp);
		req.getRequestDispatcher("/jsp/student_list.jsp").forward(req, resp);
	}
	
	private void deleteAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String[] ids = req.getParameterValues("checkedIds");
		for (String string : ids) {
			System.out.println(string);
		}
		studentService.deleteAll(ids);
		resp.sendRedirect(req.getContextPath() + "/student?method=searchByCondition");
		
	}

	private void add(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// 1.接收参数
		String name = req.getParameter("name");
		System.out.println("name:" + name);
		byte[] bytes = name.getBytes("iso8859-1");
		String newName = new String(bytes, "utf-8");
		System.out.println("newName: " + newName);
		String age = req.getParameter("age");
		String gender = req.getParameter("gender");
		String address = req.getParameter("address");
		String banjiId = req.getParameter("banjiId");
		System.out.println("班级的id" + banjiId);
		Banji banji = new Banji();
		banji.setId(Integer.parseInt(banjiId));
		Student student = new Student(name, Integer.parseInt(age), gender, address, new Date(), new Date());
		student.setBanji(banji);
		System.out.println(student);
		// 2.业务处理
		int result = studentService.add(student);
		// 3.输出响应 Magic number
		resp.setContentType("text/html;charset=utf-8");
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
		resp.sendRedirect(req.getContextPath() + "/student?method=searchByCondition");
	}

	private void findAll(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		// 1.接收请求参数，封装成对象
		// 2.调业务层处理
		List<Student> list = studentService.findAll();
		// 3.控制界面的跳转
		req.setAttribute("list", list);
		/*RequestDispatcher requestDispatcher = req.getRequestDispatcher("");
		requestDispatcher.forward(req, resp);*/
		//req.getRequestDispatcher("/showInfo.do").forward(req, resp);
		req.getRequestDispatcher("/WEB-INF/jsp/student_list.jsp").forward(req, resp);
	}

	private void showInfo(HttpServletRequest req, HttpServletResponse resp) throws IOException {
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
	}
}
