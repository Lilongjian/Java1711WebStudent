package com.situ.student.service.impl;

import java.util.List;
import java.util.Map;

import com.situ.student.controller.ManagerSearchCondition;
import com.situ.student.controller.PageBean;
import com.situ.student.dao.IManagerDao;
import com.situ.student.dao.impl.ManagerDaoImpl;
import com.situ.student.entity.Banji;
import com.situ.student.entity.Course;
import com.situ.student.entity.Manager;
import com.situ.student.entity.Student;
import com.situ.student.service.IManagerService;

public class ManagerServiceImpl implements IManagerService {
	private IManagerDao managerDao = new ManagerDaoImpl();
	@Override
	public List<Map<String, Object>> findAll() {
		return managerDao.findAll();
	}
	@Override
	public List<Map<String, Object>> searchByCondition(ManagerSearchCondition managerSearchCondition) {
		//PageBean pageBean = new PageBean();
		/*int pageNo = studentSearchCondition.getPageNo();
		int pageSize = studentSearchCondition.getPageSize();
		pageBean.setPageNo(pageNo);
		pageBean.setPageSize(pageSize);
		int totalCount = studentDao.getTotalCount(studentSearchCondition);
		System.out.println(totalCount);
		int totalPage = (int)Math.ceil((double)totalCount/pageSize);
		pageBean.setTotalPage(totalPage);*/
		List<Map<String, Object>> list = managerDao.findPageBeanList(managerSearchCondition);
		System.out.println("第二次" + list);
		//pageBean.setList(list);
		return list;
	}
	@Override
	public List<Banji> searchByBanjiName(String banjiName) {
		// TODO Auto-generated method stub
		return managerDao.searchByBanjiName(banjiName);
	}
	@Override
	public List<Course> searchByCourseName(String courseName) {
		// TODO Auto-generated method stub
		return managerDao.searchByCourseName(courseName);
	}
	@Override
	public int addCourse(int banjiid, int courseid) {
		return managerDao.addCourse(banjiid,courseid);
	}

}
