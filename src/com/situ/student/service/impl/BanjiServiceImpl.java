package com.situ.student.service.impl;

import java.util.List;

import com.situ.student.controller.BanjiSearchCondition;
import com.situ.student.controller.PageBean;
import com.situ.student.dao.IBanjiDao;
import com.situ.student.dao.impl.BanjiDaoImpl;
import com.situ.student.entity.Banji;
import com.situ.student.entity.Student;
import com.situ.student.service.IBanjiService;
import com.situ.student.util.Constant;

public class BanjiServiceImpl implements IBanjiService {
     private IBanjiDao banjiDao = new BanjiDaoImpl();
	@Override
	public List<Banji> findAll() {
		List<Banji> list = banjiDao.findAll();
		for (Banji banji : list) {
			
		}
		return banjiDao.findAll();
	}

	@Override
	public int add(Banji banji) {
		//判断此用户名是否存在，如果存在就显示：此用户名已经存在
		if (banjiDao.checkName(banji.getName())) {
			return Constant.ADD_NAME_REPEAT;
		} else {//用户名不存在，可以直接添加到数据库
			// return studentDao.add(student) > 0 ? true : false;
			int count = banjiDao.add(banji);
			if (count > 0) {
				return Constant.ADD_SUCCESS;
			} 
			return Constant.ADD_FAIL;
		}
	}

	@Override
	public List<Banji> findByName(String name) {
		
		return banjiDao.findByName(name);
	}

	@Override
	public boolean deleteById(int id) {
		if (banjiDao.deleteById(id) > 0) {
			return true;
		} 
		
		return false;
		
	}

	@Override
	public Banji findById(int id) {
		return banjiDao.findById(id);
	}

	@Override
	public boolean update(Banji banji) {
		if (banjiDao.update(banji) > 0) {
			return true;
		} 
		
		return false;
	}

	@Override
	public boolean deleteAll(String[] ids) {
		return banjiDao.deleteAll(ids);
	}

	@Override
	public PageBean getPageBean(int pageNo, int pageSize) {
		PageBean pageBean = new PageBean();
		pageBean.setPageNo(pageNo);
		pageBean.setPageSize(pageSize);
		int totalCount = banjiDao.getTotalCount();
		pageBean.setTotalCount(totalCount);
		int totalPage=(int)Math.ceil((double)totalCount/pageSize);
		pageBean.setTotalPage(totalPage);
		int offset = (pageNo - 1) * pageSize;
		List<Banji> list = banjiDao.findPageBeanList(offset,pageSize);
		/*List<Banji> list = banjiDao.findAll();*/
		pageBean.setList(list);
		System.out.println("测试" + list);
		return pageBean;
	}

	@Override
	public PageBean<Banji> searchByCondition(BanjiSearchCondition banjiSearchCondition) {
		PageBean pageBean = new PageBean();
		int pageNo = banjiSearchCondition.getPageNo();
		int pageSize = banjiSearchCondition.getPageSize();
		pageBean.setPageNo(pageNo);
		pageBean.setPageSize(pageSize);
		int totalCount = banjiDao.getTotalCount(banjiSearchCondition);
		pageBean.setTotalCount(totalCount);
		System.out.println(totalCount);
		int totalPage = (int)Math.ceil((double)totalCount/pageSize);
		pageBean.setTotalPage(totalPage);
		List<Banji> list = banjiDao.findPageBeanList(banjiSearchCondition);
		System.out.println("第二次" + list);
		pageBean.setList(list);
		return pageBean;
	}

	/*private IStudentDao studentDao = new StudentDaoImpl();

	@Override
	public List<Student> findAll() {
		List<Student> list = studentDao.findAll();
		for (Student student : list) {
			
		}
		return studentDao.findAll();
	}

	@Override
	public int add(Student student) {
		//判断此用户名是否存在，如果存在就显示：此用户名已经存在
		if (studentDao.checkName(student.getName())) {
			return Constant.ADD_NAME_REPEAT;
		} else {//用户名不存在，可以直接添加到数据库
			// return studentDao.add(student) > 0 ? true : false;
			int count = studentDao.add(student);
			if (count > 0) {
				return Constant.ADD_SUCCESS;
			} 
			return Constant.ADD_FAIL;
		}
	}

	@Override
	public List<Student> showStudentAndBanjiInfo() {
		return studentDao.showStudentAndBanjiInfo();
	}

	@Override
	public List<Student> findByName(String name) {
		return studentDao.findByName(name);
	}

	@Override
	public boolean deleteById(int id) {
		if (studentDao.deleteById(id) > 0) {
			return true;
		} 
		
		return false;
		
		if (studentDao.deleteById(id) > 0) {
			return true;
		} else {
			return false;
		}
		
		
		int count = studentDao.deleteById(id);
		if (count > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Student findById(int id) {
		return studentDao.findById(id);
	}

	@Override
	public boolean update(Student student) {
		if (studentDao.update(student) > 0) {
			return true;
		} 
		
		return false;
	}

	@Override
	public List<Student> searchByCondition(StudentSearchCondition studentSearchCondition) {
		// TODO Auto-generated method stub
		return studentDao.searchByCondition(studentSearchCondition);
	}*/

}
