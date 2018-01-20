package com.situ.student.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.situ.student.entity.Banji;
import com.situ.student.entity.Course;
import com.situ.student.entity.Manager;
import com.situ.student.entity.Student;
import com.situ.student.service.IManagerService;
import com.situ.student.service.impl.ManagerServiceImpl;
import com.situ.student.util.Constant;

public class ManagerMainServlet extends BaseServlet{
	private IManagerService managerService = new ManagerServiceImpl();
    public void  getManagerPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	List<Map<String, Object >> list = managerService.findAll();
    	System.out.println(list);
    	request.setAttribute("list", list);
    	request.getRequestDispatcher("/WEB-INF/jsp/manager_list.jsp").forward(request, response);
    }
    
    private void searchByCondition(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		System.out.println("ManagerMainServlet.searchByCondition()");
			String studentName = req.getParameter("studentName");
			String banjiName = req.getParameter("banjiId");
			String courseName = req.getParameter("courseId");
			ManagerSearchCondition managerSearchCondition = new ManagerSearchCondition(studentName, banjiName, courseName);
			List<Map<String, Object >> list = managerService.searchByCondition(managerSearchCondition);
			 req.setAttribute("list", list);
			 req.setAttribute("searchCondition", managerSearchCondition);
			req.getRequestDispatcher("/WEB-INF/jsp/manager_list.jsp").forward(req, resp);
	}
    private void xuanke(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		    System.out.println("ManagerMainServlet.xuanke()");
			String banjiName = req.getParameter("banjiId");
			String courseName = req.getParameter("courseId");
			ManagerSearchCondition managerSearchCondition = new ManagerSearchCondition(banjiName, courseName);
			List<Banji> banji = managerService.searchByBanjiName(banjiName);
			int banjiid = 0;
			for (Banji banji2 : banji) {
				System.out.println("第二次选课" + banji2);
				banjiid = banji2.getId();
			}
			System.out.println("数字" + banjiid);
			List<Course> course = managerService.searchByCourseName(courseName);
			int courseid=0;
			for (Course course2 : course) {
				System.out.println("课程选择" + course2);
				courseid = course2.getId();
			}
			System.out.println("课程ID" +courseid);
			int addCourse = managerService.addCourse(banjiid,courseid);
			if (addCourse > 0) {
				System.out.println("添加成功");
			}else {
				System.out.println("添加失败");
			}
			resp.sendRedirect(req.getContextPath() + "/managerServlet?method=getManagerPage");
			 /*req.setAttribute("list", banji);
			 req.setAttribute("searchCondition", managerSearchCondition);
			req.getRequestDispatcher("/WEB-INF/jsp/manager_list.jsp").forward(req, resp);*/
	}
}
